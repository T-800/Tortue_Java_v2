package commande;

import liste.ListeHistorique;
import terminal.TableCommande;

public class Redo extends Commande{
    @Override
    public String execute(String parametres) {
        ListeHistorique temp_liste = new ListeHistorique(TableCommande.listeHistorique);
        if (temp_liste.getUndoliste().size() != 0){
            for(int i=0; i<temp_liste.getUndoliste().size();i++){
                TableCommande.executerCommande(temp_liste.getUndoliste().get(i).getCommande());
            }
        }
        else{
            System.out.println("Rien a redo !!");
            return "Rien a redo !!";
        }




        return "";
    }
}