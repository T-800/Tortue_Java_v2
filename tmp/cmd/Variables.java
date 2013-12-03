package cmd;

import java.util.ArrayList;

import ihm.Dessin;

public class Variables extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if (commande[0].charAt(0) == '_') { //affectation
			String cmd = commande[0]+" "+commande[1];
			String tabArg[] = cmd.split("=");
			if (tabArg.length!=2) {
				return "1";
			}
			ObjetVariables var = get_Variable(tabArg[0].substring(1).trim());
			if (var != null) {
				affectation(var,tabArg[1].trim());
			}

		}
		else{ //VAR nom (Déclaration)
			// TODO : si c'est une chaine simple (que des lettre et '-','_')
			declaration(commande[1]);
		}

		afficheVar();
		
		return "";

	}

	private void declaration(String nom_Variable){
		// TODO : tester si la variable n'éxiste pas deja
		ObjetVariables var = new ObjetVariables(nom_Variable);
		liste_Variables.add(var);
	}

	private boolean affectation(ObjetVariables var, String sAffectation){
		int valeur = 0;
		if( sAffectation.charAt(0) != '('){
			try{
				valeur = Integer.parseInt(sAffectation);
				var.setValeur_Variable(valeur);

			}catch (NumberFormatException e1){
				return false;
			}
		}
		else if(bienP(sAffectation)){
			boolean canContinue = true;
			while(parenthese(sAffectation) && canContinue){
				String ss = subParenthese(sAffectation);
				String subS[] = ss.split(" ");
				String cal = calculeTab(subS);
				try{
					int i = Integer.parseInt(cal);
					sAffectation = sAffectation.replace("("+ss+")",""+i);
				}catch (NumberFormatException e1){
					canContinue = false;
					System.out.println("Impossible de faire le calcul ("+ss+") n'est pas un nombre");
				}			
			}
		}
		else {
			return false;
		}
		return true;
	}




	protected class ObjetVariables {
		private String nom_Variable;
		private int valeur_Variable = 0;

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
