package commande;

import liste.ListeVariables;

public class Remember extends Commande {


    @Override
    public boolean execute(String commande, ListeVariables listeVariables){
        if(!commande.equalsIgnoreCase("remember")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return false;
        }
        getCurseur().setCursRemember(getCurseur());
        getListeHistorique().addToList(commande,"");
        return true;
    }
}