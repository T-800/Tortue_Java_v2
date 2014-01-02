package commande;
import algo.Convert;
import liste.ListeFonctions;
import liste.ListeVariables;
import terminal.TableCommande;
import java.util.ArrayList;

public class Repeat extends Commande {



    @Override
    public boolean execute(String commande,ListeVariables listeVariables) {
        String[] param = getCmdParam(commande);
        //Convert.printParam(param);
        setListe_Local_Variables(listeVariables);
        ListeVariables list =getListe_Local_Variables();
        if (param.length!= 3){
            getListeHistorique().addToList(commande,ErrorToString("1",commande));
            return false;
        }

        int nb_repeat;
        ArrayList<String> instructions;
        try {
            nb_repeat = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            getListeHistorique().addToList(commande,param[1]+" n'est pas un nombre");
            return false;
        }
        if(!(param[2].startsWith("[") && param[2].endsWith("]")) || param[2].length() < 3){
            getListeHistorique().addToList(commande,param[2]+" n'est pas un cops de fonction correct");
            return false;
        }
        instructions = Convert.valeurStringtArgument(param[2]);
        ListeVariables blocLocal = new ListeVariables(listeVariables.getliste());
        for (int i = 0;i<nb_repeat;i++){

            for (String instruction : instructions) {
                boolean error = getTableCommande().executerCommande(instruction, blocLocal);
                if (!error){
                    getListeHistorique().addToList(commande,"Une erreur est survenue lors du REPEAT : <br>" + error);
                    return false;
                }
            }

        }
        getListeHistorique().addToList(commande,"");
        return true;
    }





}