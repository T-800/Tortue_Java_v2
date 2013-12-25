package commande;

import liste.ListeVariables;

public class Back extends Commande {


    @Override
    public void execute(String commande, ListeVariables listeVariables){
        if(!commande.equalsIgnoreCase("back")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return;
        }

        getCurseur().setCurseur(getCurseur().getCursRemember());
        getListeHistorique().addToList(commande,"");
    }
}