package commande;

public class If extends Commande{
    @Override
    public String execute(String parametres) {


        String [] argument = getCmdParam(parametres);
        if (argument.length != 3 || argument.length != 5) return ErrorToString("1", argument[0]);




        return "";
    }

}
