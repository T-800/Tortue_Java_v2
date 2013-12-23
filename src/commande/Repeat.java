package commande;
import algo.Convert;
import liste.ListeFonctions;
import liste.ListeVariables;
import terminal.TableCommande;
import java.util.ArrayList;

public class Repeat extends Commande {

    TableCommande tableCommande;
    ListeFonctions listeFonctions;
    ListeVariables listeVariables;

    public Repeat(TableCommande commande,ListeFonctions listeFonctions, ListeVariables listeVariables) {
        this.tableCommande = commande;
        this.listeFonctions = listeFonctions;
        this.listeVariables = listeVariables;
    }

    @Override
    public String execute(String[] commande,ListeVariables listeVariables) {
        if (commande.length != 3)return "1";
        int nb_repeat;
        ArrayList<String> instructions;
        try {
            nb_repeat = Integer.parseInt(commande[1]);
        } catch (NumberFormatException e) {
            return commande[1]+" n'est pas un nombre";
        }
        if(!(commande[2].startsWith("[") && commande[2].endsWith("]")) || commande[2].length() < 3)return commande[2]+" n'est pas un cops de fonction correct";
        instructions = Convert.complexArgToTab(commande[2]);
        ListeVariables blocLocal = new ListeVariables(listeVariables.getliste());
        for (int i = 0;i<nb_repeat;i++){

            for (String instruction : instructions) {
                String error = tableCommande.executerCommande(instruction,blocLocal);
                if (!error.equals("")) return "Une erreur est survenue lors du REPEAT : <br>" + error;
            }

        }

        return "";
    }





}
