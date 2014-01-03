package commande;

import liste.ListeFonctions;
import terminal.TableCommande;


public class ExFonction extends Commande {

    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);

        ListeFonctions.ObjetFonction fonc = Commande.getListeFonctions().getFonction(argument[0]);
        if (fonc != null) {

            if (argument.length>1){
                if( argument[1].equalsIgnoreCase("remove")) {
                    getListeFonctions().removeFonction(fonc);
                }
            }
            if(argument.length-1 != fonc.getNb_Agument_Fonction()){
                return "La fonction "+argument[0]+" a besoin de "+fonc.getNb_Agument_Fonction()+" argument(s)";
            }
            String error_msg;
            for(String instruction : fonc.getListe_Fonction()){
                String instruction1 = instruction;
                for(int i = 1; i<argument.length;i++){
                    instruction1 = instruction1.replace("$"+i, argument[i]);
                }
                error_msg = TableCommande.executerCommande(instruction1, false);
                if(!error_msg.equalsIgnoreCase(""))return "Une erreur est survenue Dans la Fonction : <br>" + error_msg;
            }
        }
        else {
            return "La fonction "+argument[0]+" n'éxiste pas";
        }
        return "";
    }
}
