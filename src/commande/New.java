package commande;

import interfaceGraphique.Fenetre;
import terminal.TableCommande;

import javax.swing.*;

public class New extends Commande{
    @Override
    public String execute(String parametres) {

        if (getPanelDessin().getListeCommande().getliste().size() !=0){
            int option = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous enregister votre dessin ?" , "Tortue",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION){
                String error = TableCommande.executerCommande("SAVE",false);
                if (!error.equalsIgnoreCase("")) return error;
            }
        }
        getPanelDessin().getListeCommande().reset();
        TableCommande.listeHistorique.reset();
        getPanelDessin().getCurseur().reset(Fenetre.getCenterDessin()[0], Fenetre.getCenterDessin()[1]);


        return "";
    }

}
