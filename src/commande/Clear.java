package commande;


import interfaceGraphique.Fenetre;
import liste.ListeVariables;

public class Clear extends Commande {


    @Override
    public void execute(String commande, ListeVariables listeVariables){
        if(!commande.equalsIgnoreCase("clear")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return;
        }

        getTableCommande().executerCommande("Center", null);

        getListeCommande().reset();
        getListeHistorique().reset();
        listeVariables.reset();
        getCurseur().reset(Fenetre.getCenterDessin()[0], Fenetre.getCenterDessin()[1]);
        //getListeHistorique().addToList(commande,"");
    }
}