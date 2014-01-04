package commande;

public class PenSize extends Commande{
    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);
        if (argument.length != 2) return ErrorToString("1",argument[0]);
        try {
            int taille = Integer.parseInt(argument[1]);
            Commande.getPanelDessin().getCurseur().setPenSize(taille);
        } catch (NumberFormatException e){}


        return "";
    }
}
