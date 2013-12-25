package commande;


import liste.ListeVariables;

public class Right extends Commande {




    @Override
    public void execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("right")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return;
        }
        getCurseur().setD(0);
        getListeHistorique().addToList(commande,"");
    }
}