package commande;

import interfaceGraphique.Fenetre;

/**
 * Created by renaud on 03/01/14.
 */
public class Center extends Commande{
    @Override
    public String execute(String parametres) {

        Commande.getPanelDessin().getCurseur().setPos(Fenetre.getCenterDessin());
        return "";
    }
}
