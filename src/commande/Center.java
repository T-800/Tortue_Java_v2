package commande;


import interfaceGraphique.Fenetre;
import liste.ListeVariables;

public class Center extends Commande {


    @Override
    public void execute(String commande, ListeVariables listeVariables){
        if(!commande.equalsIgnoreCase("center")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return;
        }

        getCurseur().setPos(Fenetre.getCenterDessin());
        getCurseur().setD(0);
        getListeHistorique().addToList(commande,"");
    }
}