package commande;

import algo.Convert;
import liste.ListeFonctions;
import liste.ListeFonctions.ObjetFonction;
import liste.ListeVariables;
import terminal.TableCommande;

import javax.swing.*;
import java.util.ArrayList;

public class Fonction extends Commande {

    TableCommande tableCommande;
	ListeFonctions listeFonctions;
	ListeVariables listeVariables;
	
	public Fonction(TableCommande commande,ListeFonctions listeFonctions, ListeVariables listeVariables) {
		super();
        this.tableCommande = commande;
		this.listeFonctions = listeFonctions;
		this.listeVariables = listeVariables;
	}

	@Override
	public String execute(String[] commande,ListeVariables listeVariables) {
		if(commande[0].charAt(0) == ':'){//appel de fonction
			/* [0] :nom_fonction
			 * [1:] arg
			 * ex1 : :carre 50 "appel fonction carre avec comme argument a la fonction 50"
			 * ex2 : :carrebis "appel fonction carrebis sans argument"
			 */

			/*Pour remplacer les arguments
			 * s2 = s2.replace("$"+1, "5");
			 */
			
			ObjetFonction fonc = listeFonctions.getFonction(commande[0].substring(1));
			if (fonc != null) {
                // TODO: syntaxe des noms de fonction
                if( commande[1].equalsIgnoreCase("remove")) {
                    listeFonctions.removeFonction(fonc);
                }

				if(commande.length-1 != fonc.getNb_Agument_Fonction())return "La fonction "+commande[0]+" a besoin de "+fonc.getNb_Agument_Fonction()+" argument(s)";
                ListeVariables blocLocal = new ListeVariables(listeVariables.getliste());
                for(String s : fonc.getListe_Fonction()){
					String s2 = s;

                    for(int i = 1; i<commande.length;i++){

                        //System.out.println(commande[i]);
                    }
					for(int i = 1; i<commande.length;i++){

						s2 = s2.replace("$"+i, commande[i]);
					}
                    //System.out.println("inst : " + s2);
					String error = "";
					error = tableCommande.ErrorToString(error, tableCommande.executerCommande(s2,blocLocal));
					if(!"".equals(error)){
						return error;
					}
				}
			}
			else {
				return "La fonction "+commande[0]+" n'éxiste pas";
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
			if(commande.length != 4)return "1";
			
			String nom_Fonction = commande[1];
			int nb_Arg;
			ArrayList<String> instructions;
			try {
				nb_Arg = Integer.parseInt(commande[2]);
			} catch (NumberFormatException e) {
				return commande[2]+" n'est pas un nombre";
			}
			if(!(commande[3].startsWith("[") && commande[3].endsWith("]")) || commande[3].length() < 3)return commande[3]+" n'est pas un cops de fonction correct";
			instructions = Convert.complexArgToTab(commande[3]);
            ObjetFonction fonc = listeFonctions.getFonction(commande[1]);
            if (fonc != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "La fonction " + commande[1] + " éxiste déjà. Voulez-vous la remplacer?", "Tortue",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION){
                    listeFonctions.reaffectFoction(fonc,nb_Arg,instructions);
                }
                else return "";
            }
            else{
                listeFonctions.addFonction(nom_Fonction, nb_Arg, instructions);
            }

			
		}
		return "";
	}
	
	
	
	

}
