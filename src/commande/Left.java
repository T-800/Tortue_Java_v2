package commande;


import liste.ListeVariables;

public class Left extends Commande {




    @Override
    public void execute(String commande, ListeVariables listeVariables) {
        if(!commande.equalsIgnoreCase("left")){
            getListeHistorique().addToList(commande,this.ErrorToString("1",commande.split(" ",2)[0]));
            return;
        }
        getCurseur().setD(180);
        getListeHistorique().addToList(commande,"");
    }
}