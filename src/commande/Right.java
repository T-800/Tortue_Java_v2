package commande;

/**
 * Created by renaud on 03/01/14.
 */
public class Right extends Commande{
    @Override
    public String execute(String parametres) {

        Commande.getPanelDessin().getCurseur().setD(0);
        return null;
    }
}
