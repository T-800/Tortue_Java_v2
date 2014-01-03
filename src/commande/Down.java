package commande;

/**
 * Created by renaud on 03/01/14.
 */
public class Down extends Commande{
    @Override
    public String execute(String parametres) {

        Commande.getPanelDessin().getCurseur().setD(270);
        return null;
    }
}
