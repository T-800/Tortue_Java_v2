package commande;

import liste.ListeHistorique;
import terminal.TableCommande;

public class Undo extends Commande{
    @Override
    public String execute(String parametres) {

        TableCommande.listeHistorique.addToUndoList();
        TableCommande.listeHistorique.printHist();
        ListeHistorique temp_liste = new ListeHistorique(TableCommande.listeHistorique);
        TableCommande.executerCommande("Clear");
        TableCommande.listeHistorique.reset();
        System.out.println("TABLE");
        TableCommande.listeHistorique.printHist();
        System.out.println("TMP");
        temp_liste.printHist();
        for(int i=0; i<temp_liste.getliste().size();i++){
            TableCommande.executerCommande(temp_liste.getliste().get(i).getCommande());
        }



        return "";
    }
}