package commande;

import algo.Convert;
import liste.ListeVariables;

public class Turn extends Commande {

    @Override
    public boolean execute(String commande,ListeVariables listeVariables ){
        String[] param = getCmdParam(commande);
        //Convert.printParam(param);
        if(param.length!=2){
            getListeHistorique().addToList(commande,ErrorToString("1",commande));
            return false;
        }
        int valeur;
        String s = param[1];
        switch(param[1].charAt(0)){
            case '+' :
            case '-' :
            case '*' :
            case '/' :
                s ="("+getCurseur().getD()+" "+param[1].charAt(0)+" "+ param[1].substring(1)+")";
        }
        if(param[1].toLowerCase().equals("random")){
            valeur = (int)(Math.random()*(3600));
        }
        else {
            try{
                s = Convert.valeurIntArgument(s,listeVariables);
                valeur = Integer.parseInt(s);
            }catch(NumberFormatException e1){
                getListeHistorique().addToList(commande,"Erreur "+param[1]+" n'est pas un nombre");
                return false;
            }
        }
        getCurseur().setD(valeur%360);
        getListeHistorique().addToList(commande,"");
        return true;


    }
}