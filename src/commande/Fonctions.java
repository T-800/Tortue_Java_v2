package commande;

import algo.Convert;
import liste.ListeFonctions;

import javax.swing.*;
import java.util.ArrayList;

public class Fonctions extends Commande{
    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);
        //déclaration de fonction
        /* [0] = Fonction
        * [1] = nom Fonction
        * [2] = nb Araguments
        * [3] = Instructions
        * ex : Fonction carre 1 [//carre de $1*$1;up;move $1;right;move $1;down;move $1;left;move $1]
        * ex2:  fonction rectangle 2 [//rectangle de $1*$2;up;move $1;right;move $2;down;move $1;left;move $2]
        */
        if(argument.length != 4){
            return ErrorToString("1",argument[0]);
        }
        // TODO: syntaxe des noms de fonction
        String nom_Fonction = argument[1];
        int nb_Arg;
        ArrayList<String> instructions;
        try {
            nb_Arg = Integer.parseInt(argument[2]);
        } catch (NumberFormatException e) {
            return argument[2]+" n'est pas un nombre/";
        }
        if(!(argument[3].startsWith("[") && argument[3].endsWith("]")) || argument[3].length() < 3){
            return argument[3]+" n'est pas un cops de fonction correct";
        }
        instructions = Convert.valeurStringtArgument(argument[3]);
        ListeFonctions.ObjetFonction fonc = Commande.getListeFonctions().getFonction(argument[1]);
        if (fonc != null) {
            int option = JOptionPane.showConfirmDialog(null,
                    "La fonction " + argument[1] + " éxiste déjà. Voulez-vous la remplacer?", "Tortue",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION){
                getListeFonctions().reaffectFoction(fonc,nb_Arg,instructions);
            }
            else{
                return  "déclaration annulée";
            }
        }
        else{
            Commande.getListeFonctions().addFonction(nom_Fonction, nb_Arg, instructions);
        }
        return "";
    }
}



