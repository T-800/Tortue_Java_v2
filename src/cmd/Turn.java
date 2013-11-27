package cmd;

import ihm.Dessin;

public class Turn extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length!=2)return "1";
		int valeur = 0;

		switch(commande[1].charAt(0)){
			case '+' :
			case '-' :
			case '*' :
			case '/' :
				String s ="("+dessin.curseur.getDirection()+" "+commande[1].charAt(0)+" "+commande[1].substring(1)+")";

				System.out.println(s);
				if(bienP(s)){
						boolean canContinue = true;
						while(parenthese(s) && canContinue){
							String ss = subParenthese(s);
							String subS[] = ss.split(" ");
							String cal = calculeTab(subS);
							try{
								int i = Integer.parseInt(cal);
								s = s.replace("("+ss+")",""+i);
							}catch (NumberFormatException e1){
								canContinue = false;
								System.out.println("Impossible de faire le calcul ("+ss+") n'est pas un nombre");
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
				if(bienP(commande[1])){
						boolean canContinue = true;
						while(parenthese(commande[1]) && canContinue){
							String ss = subParenthese(commande[1]);
							String subS[] = ss.split(" ");
							String cal = calculeTab(subS);
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
		dessin.curseur.setDirection(valeur%360);
		return "";
		

	}
}