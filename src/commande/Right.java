package commande;


import liste.ListeVariables;

public class Right extends Commande {




    @Override
    public boolean execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("right")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return false;
        }
        getCurseur().setD(0);
        getListeHistorique().addToList(commande,"");
        return true;
    }
}