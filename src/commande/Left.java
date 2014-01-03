package commande;

/**
 * Created by renaud on 03/01/14.
 */
public class Left extends Commande{
    @Override
    public String execute(String parametres) {

        Commande.getPanelDessin().getCurseur().setD(180);
        return null;
    }
}
