package commande;

import algo.Convert;

/**
 * Created by renaud on 03/01/14.
 */
public class Turn extends Commande{
    @Override
    public String execute(String parametres) {

        /*
        * turn right
        * turn left
        * turn up
        * turn down
        * turn 60
        * turn +50
        * */

        String[] argument = getCmdParam(parametres);
        if (argument.length != 2) return ErrorToString("1",argument[0]);
        int valeur;
        switch (argument[1].toLowerCase()){
            case "right" :
                valeur=0;
                break;
            case "left" :
                valeur=180;
                break;
            case "up" :
                valeur=90;
                break;
            case "down" :
                valeur=270;
                break;
            default :
                String calculString = argument[1];
                if (argument[1].charAt(0) == '+' || argument[1].charAt(0) == '-'||argument[1].charAt(0) == '*'||argument[1].charAt(0) == '/'||argument[1].charAt(0) == '%'){
                    calculString ="("+getPanelDessin().getCurseur().getD()+" "+argument[1].charAt(0)+" "+ argument[1].substring(1)+")";
                }
                if(argument[1].toLowerCase().equals("random")){
                    valeur = (int)(Math.random()*(3600));
                }
                else {
                    try{
                        calculString = Convert.valeurIntArgument(calculString);
                        valeur = Integer.parseInt(calculString);
                    }catch(NumberFormatException e1){
                        return calculString;
                    }
                }
        }
        getPanelDessin().getCurseur().setD(valeur%360);

        return "";
    }
}
