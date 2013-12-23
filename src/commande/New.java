package commande;


import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;
import javax.swing.*;

public class New extends Commande{

    TableCommande tableCommande;
    ListeHistorique listeHistorique;
    ListeCommande listeCommande;
    ListeFonctions listeFonctions;
    ListeVariables listeVariables;
    private Curseur curseur;

    public New(TableCommande tableCommande, ListeCommande listeCommande, Curseur curseur, ListeHistorique listeHistorique, ListeVariables listeVariables, ListeFonctions listeFonctions){
        this.tableCommande = tableCommande;
        this.listeCommande = listeCommande;
        this.listeHistorique = listeHistorique;
        this.listeFonctions = listeFonctions;
        this.listeVariables = listeVariables;
        this.curseur = curseur;

    }

    @Override
    public String execute(String[] parametres,ListeVariables listeVariables) {
        if(parametres.length>1)return "1";

        if(listeCommande.getliste().size()>0){
            int option = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous sauvegarder vorte dessin ?", "Tortue",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION){
                tableCommande.executerCommande("SAVE",null);
            }
            else if (option == JOptionPane.CANCEL_OPTION){
                return "Annulée";
            }

        }
        tableCommande.executerCommande("Center",null);
        listeCommande.reset();
        listeHistorique.reset();
        listeVariables.reset();
        curseur.reset(Fenetre.getCenterDessin()[0],Fenetre.getCenterDessin()[1]);


        return "";
    }
}