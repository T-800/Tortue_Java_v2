package commande;


import liste.ListeVariables;

public class Down extends Commande {




    @Override
    public boolean execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("down")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return false;
        }
        getCurseur().setD(270);
        getListeHistorique().addToList(commande,"");
        return true;
    }
}