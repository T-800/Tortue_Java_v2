package commande;

import algo.Convert;
import liste.ListeFonctions.ObjetFonction;
import liste.ListeVariables;

import javax.swing.*;
import java.util.ArrayList;

public class Fonctions extends Commande {

    @Override
    public boolean execute(String commande,ListeVariables listeVariables) {
        String[] param = getCmdParam(commande);
        Convert.printParam(param);
        setListe_Local_Variables(listeVariables);
        ListeVariables list =getListe_Local_Variables();

        if(param[0].charAt(0) == ':'){//appel de fonction
                        /* [0] :nom_fonction
                         * [1:] arg
                         * ex1 : :carre 50 "appel fonction carre avec comme argument a la fonction 50"
                         * ex2 : :carrebis "appel fonction carrebis sans argument"
                         */
            ObjetFonction fonc = getListeFonctions().getFonction(param[0].substring(1));
            if (fonc != null) {
                if( param[1].equalsIgnoreCase("remove")) {
                    getListeFonctions().removeFonction(fonc);
                }
                if(param.length-1 != fonc.getNb_Agument_Fonction()){
                    getListeHistorique().addToList(commande,"La fonction "+param[0]+" a besoin de "+fonc.getNb_Agument_Fonction()+" argument(s)");
                    return false;
                }
                boolean error;
                for(String s : fonc.getListe_Fonction()){
                    String s2 = s;
                    for(int i = 1; i<param.length;i++){
                        s2 = s2.replace("$"+i, param[i]);
                    }
                    //System.out.println("inst : " + s2);
                    error = getTableCommande().executerCommande(s2,list);
                    if(!error)return false;
                }
            }
            else {
                getListeHistorique().addToList(commande,"La fonction "+param[0]+" n'éxiste pas");
                return false;
            }

        }
        else { //déclaration de fonction
                        /* [0] = Fonction
                         * [1] = nom Fonction
                         * [2] = nb Araguments
                         * [3] = Instructions
                         * ex : Fonction carre 1 [//carre de $1*$1;up;move $1;right;move $1;down;move $1;left;move $1]
                         * ex2: fonction rectangle 2 [//rectangle de $1*$2;up;move $1;right;move $2;down;move $1;left;move $2]
                         */
            if(param.length != 4){
                getListeHistorique().addToList(commande,ErrorToString("1",commande));
                return false;
            }
            // TODO: syntaxe des noms de fonction
            String nom_Fonction = param[1];
            int nb_Arg;
            ArrayList<String> instructions;
            try {
                nb_Arg = Integer.parseInt(param[2]);
            } catch (NumberFormatException e) {
                getListeHistorique().addToList(commande,param[2]+" n'est pas un nombre/");

                return false;
            }
            if(!(param[3].startsWith("[") && param[3].endsWith("]")) || param[3].length() < 3){
                getListeHistorique().addToList(commande,param[3]+" n'est pas un cops de fonction correct");

                return false;
            }
            instructions = Convert.valeurStringtArgument(param[3]);
            ObjetFonction fonc = getListeFonctions().getFonction(param[1]);
            if (fonc != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "La fonction " + param[1] + " éxiste déjà. Voulez-vous la remplacer?", "Tortue",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION){
                    getListeFonctions().reaffectFoction(fonc,nb_Arg,instructions);
                }
                else{
                    getListeHistorique().addToList(commande,"déclaration annulée");

                    return false;
                }
            }
            else{
                getListeFonctions().addFonction(nom_Fonction, nb_Arg, instructions);
            }
        }
        getListeHistorique().addToList(commande,"");
        return true;
    }





}