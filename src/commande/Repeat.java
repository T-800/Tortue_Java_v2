package commande;

import algo.Convert;
import terminal.TableCommande;
import java.util.ArrayList;

public class Repeat extends Commande{
    @Override
    public String execute(String parametres) {
        int nb_repeat;
        ArrayList<String> instructions;
        String [] argument = getCmdParam(parametres);

        if (argument.length!= 3){
            return ErrorToString("1",argument[0]);
        }

        try {
            nb_repeat = Integer.parseInt(argument[1]);
        } catch (NumberFormatException e) {
            return argument[1]+" n'est pas un nombre";
        }

        if(!(argument[2].startsWith("[") && argument[2].endsWith("]")) || argument[2].length() < 3){
            return argument[2]+" n'est pas un cops de boucle correct";
        }

        instructions = Convert.valeurStringtArgument(argument[2]);

        for (int i = 0;i<nb_repeat;i++){
            for (String instruction : instructions) {

                String error = TableCommande.executerCommande(instruction, false);

                if (!error.equalsIgnoreCase("")){
                    return "Une erreur est survenue lors du REPEAT : <br>" + error;
                }
            }

        }
        return "";
    }
}