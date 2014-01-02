package commande;


import liste.ListeVariables;

public class Up extends Commande {




    @Override
    public boolean execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("up")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return false;
        }
        getCurseur().setD(90);
        getListeHistorique().addToList(commande,"");
        return true;
    }
}