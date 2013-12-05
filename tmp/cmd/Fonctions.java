package cmd;

import ihm.Dessin;

import java.util.ArrayList;

public class Fonctions extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if (commande[0].charAt(0) == ':') { //affectation
			
		}
		else{ //fonction nom (Déclaration)
			// TODO : si c'est une chaine simple (que des lettre et '-','_')
			declaration(commande[1]);
		}
		
		return "0";
	}

	private void declaration(String nom_Fonction){
		
		ObjetFonction var = new ObjetFonction(nom_Variable);
		liste_Variables.add(var);
	}

	protected class ObjetFonction {
		private String nom_Fonction;
		private ArrayList<String> fonction_Instructions = new ArrayList<String>();

		public ObjetFonction(String nom_Fonction) {
			this.nom_Fonction = nom_Fonction;
		}

		
	}
}