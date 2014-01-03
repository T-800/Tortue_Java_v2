package commande;

/**
 * Created by renaud on 03/01/14.
 */
public class Up extends Commande{
    @Override
    public String execute(String parametres) {

        Commande.getPanelDessin().getCurseur().setD(90);
        return null;
    }
}
