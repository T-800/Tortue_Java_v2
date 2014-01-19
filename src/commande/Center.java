package commande;

import interfaceGraphique.Fenetre;


public class Center extends Commande{
    @Override
    public String execute(String parametres) {

        Commande.getPanelDessin().getCurseur().setPos(Fenetre.getCenterDessin());
        return "";
    }
}
