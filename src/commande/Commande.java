package commande;


import dessin.Curseur;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

import java.util.ArrayList;

public abstract class Commande {

    private static Curseur curseur;
    private static ListeHistorique listeHistorique;
    private static ListeCommande listeCommande;
    private static ListeVariables liste_Local_Variables = null;
    private static ListeFonctions listeFonctions;
    private static TableCommande tableCommande;

	public abstract void execute(String parametres, ListeVariables listeVariables);


    public static Curseur getCurseur() {
        return curseur;
    }

    public static ListeHistorique getListeHistorique() {
        return listeHistorique;
    }

    public static ListeCommande getListeCommande() {
        return listeCommande;
    }

    public static ListeVariables getListe_Local_Variables() {
        return liste_Local_Variables;
    }

    public static ListeFonctions getListeFonctions() {
        return listeFonctions;
    }

    public static TableCommande getTableCommande() {
        return tableCommande;
    }

    public static void setTableCommande(TableCommande tableCommande) {
        Commande.tableCommande = tableCommande;
    }

    public static void setListeFonctions(ListeFonctions listeFonctions) {
        Commande.listeFonctions = listeFonctions;
    }

    public static void setCurseur(Curseur curseur) {
        Commande.curseur = curseur;
    }

    public static void setListeHistorique(ListeHistorique listeHistorique) {
        Commande.listeHistorique = listeHistorique;
    }

    public static void setListeCommande(ListeCommande listeCommande) {
        Commande.listeCommande = listeCommande;
    }

    public static void setListe_Local_Variables(ListeVariables liste_Local_Variables) {
        Commande.liste_Local_Variables = liste_Local_Variables;
    }

    public String ErrorToString(String code, String nomCmd){
        switch(code){
            case "" :
                return "";
            case "-1":
                return "La commande " +nomCmd+ " n'éxiste pas";
            case "1":
                return "Paramètre(s) incorrect(s) <br> voir : \"help "+nomCmd+" \"";
            case "2":
                return "Ce code couleur n'éxiste pas <br> voir : \"http://code-couleur.outils-webmaster.com/ \"";
            case "3":
                return "Impossible de re-déclarer une variable existante <br> voir : \"help "+nomCmd+" \"" ;
            case "4":
                return "Impossible d'éffectuer ce calcule" ;
            default:
                return code;
        }
    }

    public String[] getCmdParam(String commande){
        int tmp =0;
        int begin = 0;
        ArrayList<String> list = new ArrayList<>();


        for (int i =0 ; i<commande.length() ;i++ ) {
            if (commande.charAt(i)== '(' || commande.charAt(i)== '[') tmp++;
            else if (commande.charAt(i)== ')' || commande.charAt(i)== ']') tmp--;
            else if ((commande.charAt(i) == ' ' || commande.charAt(i) == '=' ) && tmp==0){
                list.add(commande.substring(begin,i));
                begin = i+1;
            }
        }
        list.add(commande.substring(begin));
        return list.toArray(new String[list.size()]);
    }
}