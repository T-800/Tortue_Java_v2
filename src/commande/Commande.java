package commande;




import interfaceGraphique.PanelDessin;
import liste.ListeFonctions;

import java.util.ArrayList;

public abstract class Commande {

    private static PanelDessin panelDessin;
    private static ListeFonctions listeFonctions;

    public abstract String execute(String parametres);

    /**GET**/
    public static PanelDessin getPanelDessin() {
        return panelDessin;
    }

    public static ListeFonctions getListeFonctions() {
        return listeFonctions;
    }

    /**SET**/
    public static void setPanelDessin(PanelDessin panelDessin) {
        Commande.panelDessin = panelDessin;
    }

    public static void setListeFonctions(ListeFonctions listeFonctions) {
        Commande.listeFonctions = listeFonctions;
    }

    public String ErrorToString(String code, String nomCmd) {
        switch (code) {
            case "":
                return "";
            case "-1":
                return "La commande " + nomCmd + " n'éxiste pas";
            case "1":
                return "Paramètre(s) incorrect(s) <br> voir : \"help " + nomCmd + " \"";
            case "2":
                return "Ce code couleur n'éxiste pas <br> voir : \"http://code-couleur.outils-webmaster.com/ \"";
            case "3":
                return "Impossible de re-déclarer une variable existante <br> voir : \"help " + nomCmd + " \"";
            case "4":
                return "Impossible d'éffectuer ce calcule";
            default:
                return code;
        }
    }

    public String[] getCmdParam(String commande) {
        int tmp = 0;
        int begin = 0;
        ArrayList<String> list = new ArrayList<>();


        for (int i = 0; i < commande.length(); i++) {
            if (commande.charAt(i) == '(' || commande.charAt(i) == '[') tmp++;
            else if (commande.charAt(i) == ')' || commande.charAt(i) == ']') tmp--;
            else if ((commande.charAt(i) == ' ' || commande.charAt(i) == '=') && tmp == 0) {
                list.add(commande.substring(begin, i));
                begin = i + 1;
            }
        }
        list.add(commande.substring(begin));
        return list.toArray(new String[list.size()]);
    }
}