package commande;

import algo.Convert;
import algo.Verification;
import dessin.Curseur;
import liste.ListeVariables;
import liste.ListeVariables.ObjetVariables;

public class Turn extends Commande {
	
	Curseur curseur;
	ListeVariables listeVariables;
	
	public Turn(Curseur curseur,ListeVariables listeVariables){
		this.curseur = curseur;
		this.listeVariables = listeVariables;
	}

	@Override
	public String execute(String[] commande ){
		if(commande.length!=2)return "1";
		int valeur = 0;

		switch(commande[1].charAt(0)){
			case '+' :
			case '-' :
			case '*' :
			case '/' :
				String s ="("+curseur.getD()+" "+commande[1].charAt(0)+" "+commande[1].substring(1)+")";
				if(Verification.bienP(s)){
						boolean canContinue = true;
						while(Verification.parenthese(s) && canContinue){
							String ss = Convert.subParenthese(s);
							String subS[] = ss.split(" ");
							String cal = Convert.calculeTab(subS,listeVariables);
							try{
								int i = Integer.parseInt(cal);
								s = s.replace("("+ss+")",""+i);
							}catch (NumberFormatException e1){
								canContinue = false;
								return("Impossible de faire le calcul ("+ss+") n'est pas un nombre");
							}
			
						}
						try{
							valeur = Integer.parseInt(s);
						}catch(NumberFormatException e1){
							return "Erreur "+s+" n'est pas un nombre";
						}
					}
				else return "4";

				break;
			case '(' :
				if(Verification.bienP(commande[1])){
						boolean canContinue = true;
						while(Verification.parenthese(commande[1]) && canContinue){
							String ss = Convert.subParenthese(commande[1]);
							String subS[] = ss.split(" ");
							String cal = Convert.calculeTab(subS,listeVariables);
							try{
								int i = Integer.parseInt(cal);
								commande[1] = commande[1].replace("("+ss+")",""+i);
							}catch (NumberFormatException e1){
								canContinue = false;
								return cal;
							}
			
						}
						try{
							valeur = Integer.parseInt(commande[1]);
						}catch(NumberFormatException e1){
							return "Erreur "+commande[1]+" n'est pas un nombre";
						}
					}
				else return "4";
				break;
			case '_' :
				String varNameString = commande[1].substring(1);
				ObjetVariables var = Convert.get_Variable(listeVariables,varNameString);
				if(var == null) return "La variable "+ commande[1]+" n'éxiste pas.";
				valeur = var.getValeur_Variable();
				break;

			default :
				if(commande[1].toLowerCase().equals("random")){
					valeur = 0 + (int)(Math.random()*(3600));
				}
				else {
					try{
						valeur = Integer.parseInt(commande[1]);
					}catch(NumberFormatException e1){
						return "Erreur "+commande[1]+" n'est pas un nombre";
					}
				}
			
		}
		curseur.setD(valeur%360);
		return "";
		

	}
}