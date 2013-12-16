package commande;


import dessin.Curseur;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

public class New extends Commande{

    TableCommande tableCommande;
    ListeHistorique listeHistorique;
    ListeCommande listeCommande;
    ListeFonctions listeFonctions;
    ListeVariables listeVariables;
    private Curseur curseur;

    public New(TableCommande tableCommande, ListeCommande listeCommande, Curseur curseur, ListeHistorique listeHistorique, ListeVariables listeVariables, ListeFonctions listeFonctions){
        this.tableCommande = tableCommande;
        this.listeCommande = listeCommande;
        this.listeHistorique = listeHistorique;
        this.listeFonctions = listeFonctions;
        this.listeVariables = listeVariables;
        this.curseur = curseur;

    }

    @Override
    public String execute(String[] parametres) {
        if(parametres.length>1)return "1";

        if(listeCommande.getliste().size()>0){
            tableCommande.executerCommande("SAVE");
        }
        tableCommande.executerCommande("Center");
        listeCommande.reset();
        listeHistorique.reset();
        listeVariables.reset();


        return "";
    }
}