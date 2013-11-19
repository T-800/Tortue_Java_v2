package cmd;

import ihm.Dessin;

public class Variables extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if (commande[0].charAt(0) == '_') { //affectation
			
		}
		else{ //VAR nom (Déclaration)
			// TODO : si c'est une chaine simple (que des lettre et '-','_')
			declaration(commande[1]);
		}
		
		return 0;

	}

	private void declaration(String nom_Variable){
		ObjetVariables var = new ObjetVariables(nom_Variable);
		liste_Variables.add(var);
	}

	private int affectation(ObjetVariables var, String sAffectation){
		int valeur = 0;
		if( sAffectation.charAt(0) != '('){
			try{
				valeur = Integer.parseInt(sAffectation);
				var.setValeur_Variable(valeur);

			}catch (NumberFormatException e1){
				return -1;
			}
		}
		else{

		}
		return valeur;
	}

	protected class ObjetVariables {
		private String nom_Variable;
		private int valeur_Variable;

		public ObjetVariables(String nom_Variable) {
			this.nom_Variable = nom_Variable;
		}

		public String getNom_Variable() {
			return nom_Variable;
		}

		public void setNom_Variable(String nom_Variable) {
			this.nom_Variable = nom_Variable;
		}

		public int getValeur_Variable() {
			return valeur_Variable;
		}

		public void setValeur_Variable(int valeur_Variable) {
			this.valeur_Variable = valeur_Variable;
		}
	}

}
