package commande;

import interfaceGraphique.Fenetre;


public class Clear extends Commande{
    @Override
    public String execute(String parametres) {


        getPanelDessin().getListeCommande().reset();
        getPanelDessin().getCurseur().reset(Fenetre.getCenterDessin()[0], Fenetre.getCenterDessin()[1]);


        return "";
    }

}
