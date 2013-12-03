package commande;

import algo.Convert;
import algo.Verification;
import liste.ListeVariables;
import liste.ListeVariables.ObjetVariables;

public class Variables extends Commande {
	
	private ListeVariables listeVariables;
	
	public Variables(ListeVariables listeVariables) {
		this.listeVariables = listeVariables;
	}
	
	@Override
	public String execute(String[] commande){
		System.out.print(toString(commande));
		if (commande[0].charAt(0) == '_') { //affectation
			String cmd = commande[0]+" "+commande[1];
			String tabArg[] = cmd.split("=");
			if (tabArg.length!=2) {
				return "1";
			}
			ObjetVariables var = Convert.get_Variable(listeVariables,tabArg[0].substring(1).trim());
			if (var != null) {
				return affectation(var,tabArg[1].trim());
			}

		}
		else{ //VAR nom (Déclaration)
			// TODO : si c'est une chaine simple (que des lettre et '-','_')
			declaration(commande[1]);
		}
		return "";

	}

	private void declaration(String nom_Variable){
		this.listeVariables.add(nom_Variable);
	}

	private String affectation(ObjetVariables var, String sAffectation){
		int valeur = 0;
		if( sAffectation.charAt(0) != '('){
			try{
				valeur = Integer.parseInt(sAffectation);
				var.setValeur_Variable(sAffectation);

			}catch (NumberFormatException e1){
				return sAffectation+"n'est pas une affectation valide";
			}
		}
		else if(Verification.bienP(sAffectation)){
			boolean canContinue = true;
			while(Verification.parenthese(sAffectation) && canContinue){
				String ss = Convert.subParenthese(sAffectation);
				String subS[] = ss.split(" ");
				String cal = Convert.calculeTab(subS,listeVariables);
				try{
					int i = Integer.parseInt(cal);
					sAffectation = sAffectation.replace("("+ss+")",""+i);
				}catch (NumberFormatException e1){
					canContinue = false;
					System.out.println("Impossible de faire le calcul ("+ss+") n'est pas un nombre");
				}			
			}
			try{
				valeur = Integer.parseInt(sAffectation);
				var.setValeur_Variable(sAffectation);

			}catch (NumberFormatException e1){
				return sAffectation+"n'est pas une affectation valide";
			}
		}
		else {
			return sAffectation+"n'est pas une affectation valide";
		}
		return "";
	}
	
}
