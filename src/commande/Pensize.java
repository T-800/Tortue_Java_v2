package commande;

import algo.Convert;
import dessin.Curseur;
import liste.ListeVariables;

public class PenSize extends Commande {
    private Curseur curseur;
    private ListeVariables listeVariables;

    public PenSize(Curseur curseur, ListeVariables listeVariables){
        this.curseur = curseur;
        this.listeVariables = listeVariables;
    }


    @Override
    public String execute(String[] commande) {
        if(commande.length!=2)return "1";
        int valeur = 0;
        String s = commande[1];
        switch(commande[1].charAt(0)){
            case '+' :
            case '-' :
            case '*' :
            case '/' :
                s ="("+curseur.getPenSize()+" "+commande[1].charAt(0)+" "+  commande[1].substring(1)+")";
        }
        if(commande[1].toLowerCase().equals("random")){
            valeur = 0 + (int)(Math.random()*(100));
        }
        else {
            try{
                s = Convert.convertArg(s,listeVariables);
                valeur = Integer.parseInt(s);
            }catch(NumberFormatException e1){
                return "Erreur "+commande[1]+" n'est pas un nombre";
            }
        }
        curseur.setPenSize(valeur%360);
        return "";
    }
}
