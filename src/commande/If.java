package commande;

import algo.Bool;
import algo.Convert;
import terminal.TableCommande;

import java.util.ArrayList;

public class If extends Commande{
    @Override
    public String execute(String parametres) {


        String [] argument = getCmdParam(parametres);
        if (argument.length != 3 && argument.length != 5) return ErrorToString("1", argument[0]);

        ArrayList<String> instructions = Convert.valeurStringtArgument(argument[2]);

        Bool bool = new Bool(getPanelDessin().getCurseur());
        if(bool.doBooleanTest(argument[1]).equalsIgnoreCase("true")){
            for (String instruction : instructions) {
                String error = TableCommande.executerCommande(instruction,false);
                if (!error.equalsIgnoreCase("")){
                    return "Une erreur est survenue lors du IF/ELSE : <br>" + error;

                }
            }
        }
        else if (argument.length == 5 && argument[3].equalsIgnoreCase("else")){
            instructions = Convert.valeurStringtArgument(argument[4]);
            for (String instruction : instructions) {
                String error = TableCommande.executerCommande(instruction,false);
                if (!error.equalsIgnoreCase("")){
                    return "Une erreur est survenue lors du IF/ELSE : <br>" + instruction;

                }
            }
        }



        return "";
    }

}
