package commande;


import liste.ListeVariables;

public class Up extends Commande {




    @Override
    public void execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("up")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return;
        }
        getCurseur().setD(90);
        getListeHistorique().addToList(commande,"");
    }
}