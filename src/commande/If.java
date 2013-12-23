package commande;

import algo.Bool;
import algo.Convert;
import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeCommande;
import liste.ListeVariables;
import terminal.TableCommande;

import java.util.ArrayList;


public class If extends Commande {

    private Curseur curseur;
    private ListeVariables listeVariables;
    private TableCommande tableCommande;

    public If(Curseur curseur,ListeVariables listeVariables, TableCommande tableCommande) {
        this.curseur = curseur;
        this.listeVariables = listeVariables;
        this.tableCommande= tableCommande;
    }

    @Override
    public String execute(String[] commande,ListeVariables listeVariables){
        Bool bool = new Bool(curseur);
        if(commande.length < 3 )return "1";
        boolean telse = false;

        boolean doit = false;
        for (int i = 0 ; i< commande.length; i++){
            ArrayList<String> instructions = null;
            String boolTest = "";
            switch (i%3){
                case 0 :
                    if(commande[i].equalsIgnoreCase("else")) telse = true;
                    break;
                case 1 :
                    if(telse){
                        instructions = Convert.complexArgToTab(commande[i]);
                    }
                    else{
                        boolTest = bool.doBooleanTest(commande[i],listeVariables);
                        System.out.println (commande[i]+" = "+boolTest);
                        if(boolTest.trim().equalsIgnoreCase("true")){
                            doit = true;
                        }
                        System.out.println (doit);
                    }
                    break;
                case 2:
                    if(doit) instructions = Convert.complexArgToTab(commande[i]);
                    break;
            }

            if (instructions != null){
                ListeVariables blocLocal = new ListeVariables(listeVariables.getliste());
                for (String instruction : instructions) {
                    System.out.println(instruction);
                    String error = tableCommande.executerCommande(instruction,blocLocal);
                    if (!error.equals("")) return "Une erreur est survenue lors du REPEAT : <br>" + error;
                }
                return "";
            }
            else System.out.println("Pas de else");


        }

        return "";
    }
}