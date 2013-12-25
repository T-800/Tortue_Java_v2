package commande;


import liste.ListeVariables;

public class Left extends Commande {




    @Override
    public boolean execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("left")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return false;
        }
        getCurseur().setD(180);
        getListeHistorique().addToList(commande,"");
        return true;
    }
}