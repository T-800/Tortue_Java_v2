package algo;

import liste.ListeVariables;
import liste.ListeVariables.ObjetVariables;

import java.util.ArrayList;

public class Convert {
	
	
	public static void main(String args[]){
		
		String s ="Move renaud (1(1)) (2)   (3)   [4 (4 ) ] [5    ((5)5)[5]5]";
		String[] ss = commandeToTab(s);
        for (String s1 : ss) {
            System.out.println(s1);
        }
		
		String s2 = " Fonction carre 1 [//carre de $1*$1;move $1;turn +90]";
		System.out.println("Avant : "+s2);
		//ss = complexArgToTab(s2);
		s2 = s2.substring(1, s2.length()-1);
		s2 = s2.replace("$"+1, "5");
		System.out.println("Apres : "+s2);
        for (String s1 : ss) {
            System.out.println(s1);
        }
	}
	
	public static String[] commandeToTab(String commande){
		/*
		 * Supprime tout les espace en trop
		 */
		commande = commande.replaceAll("\\s+", " ");
		
		int tmp =0;
		int begin = 0;
		ArrayList<String> list = new ArrayList<String>();
		
		
		for (int i =0 ; i<commande.length() ;i++ ) {
			if (commande.charAt(i)== '(' || commande.charAt(i)== '[') tmp++;
			else if (commande.charAt(i)== ')' || commande.charAt(i)== ']') tmp--;
			else if (commande.charAt(i) == ' ' && tmp==0){
				list.add(commande.substring(begin,i));
				begin = i+1;
			}
		}
		list.add(commande.substring(begin));
		return list.toArray(new String[list.size()]);
	}
	
	/**Cette fonction retourne l'ObjetVariable de la varible Passer en argument depuis la listeVariable  
	 * @param liste_Variables
	 * @param nom_Variable
	 * @return ObjetVariable
	 */
	public static  ObjetVariables get_Variable(ListeVariables liste_Variables,String nom_Variable){
		for (ObjetVariables var : liste_Variables.getliste()) {
				if(var.getNom_Variable().equals(nom_Variable)) {
					return var;
				}
		}
		return null;
	}
	
	/**
	 * @param tab
	 * @param liste_Variables
	 * @return
	 */
	public static String calculeTab(String tab[],ListeVariables liste_Variables){
		int a = 0,b = 0;
        if(tab.length == 1)return tab[0];
		if(tab.length != 3)return "ERROR";

		if(tab[0].charAt(0) == '_' ){
			ObjetVariables v = Convert.get_Variable(liste_Variables, tab[0].substring(1));
			if(v != null) {
                String s = v.getValeur_Variable();
                a = Integer.parseInt(s);
            }
			else return "Impossible de faire le calcul la variable "+tab[0]+" n'éxiste pas";
		}
		else {
			try{
				a = Integer.parseInt(tab[0]);
			}catch (NumberFormatException e1){
				return "Impossible de faire le calcul "+tab[0]+" n'est pas un nombre";
			}
		}
		if(tab[2].charAt(0) == '_' ){
			ObjetVariables v = Convert.get_Variable(liste_Variables,tab[2]);
			if(v != null) {
                String s = v.getValeur_Variable();
                a = Integer.parseInt(s);
            }
			else return "Impossible de faire le calcul la variable "+tab[2]+" n'éxiste pas";
		}
		else {
			try{
				b = Integer.parseInt(tab[2]);
			}catch (NumberFormatException e1){
				return "Impossible de faire le calcul "+tab[2]+" n'est pas un nombre";
			}
		}
		switch(tab[1]){
			case "/" :

				try{
					return ""+(a/b);
				}catch (ArithmeticException e1){
					return "Impossible de faire le calcul "+tab[2];
				}

			case "*" :
				return ""+(a*b);
			case "%" :
				return ""+(a%b);
			case "+" :
				return ""+(a+b);
			case "-" :
			 	return ""+(a-b);
			default :
				return "ERRdfsdgh;j;OR";

		}
	}


    /**
     * Cette fonction prend en argument un string s qui représente un argument de fonction
     * et retourne la valeur string de cette argument ou le string erreur
     * les arguments valides sont :
     *      - Soit une variable
     *      - Soit un entier
     *      - Soit un calcul
     * @param s
     * @return String la valeur string de cette argument ou le string erreur
     */
	
	public static String convertArg(String s,ListeVariables listeVariables){
		switch (s.charAt(0)){
			case '(': 
				if(Verification.bienP(s)){
					boolean canContinue = true;
					while(canContinue && Verification.parenthese(s)){
						String ss = subParenthese(s);
                        String ss_tmp = ss.trim();
						String subS[] = ss_tmp.split(" ");
						String cal = Convert.calculeTab(subS,listeVariables);
						try{
							int i = Integer.parseInt(cal);
							s = s.replace("("+ss+")",""+i);
						}catch (NumberFormatException e1){
							canContinue = false;
							return(cal);
						}
			
					}
					return s;
				}
				else return "Ce calcul n'est pas bien parenthésé";
			case '_':
				
				String varNameString = s.substring(1);
				ObjetVariables var = Convert.get_Variable(listeVariables,varNameString);
				if(var == null) return "La variable "+ s+" n'éxiste pas.";
				return ""+var.getValeur_Variable();
			default:
				return s;
		}
	}
 
	/**
	 * @param s
	 * @return
	 */
	public static String subParenthese(String s){
		int begin = 0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') begin = i+1;
			else if (s.charAt(i)== ')') return s.substring(begin,i);
		}
		return null;
	}

	public static ArrayList<String> complexArgToTab(String s){
		
		s = s.substring(1, s.length()-1);//on retire les crochets de debut et de fin
		int crochets = 0, begin = 0;
		ArrayList<String> list = new ArrayList<>();
		
		
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '[') crochets++;
			else if (s.charAt(i)== ']') crochets--;
			else if (s.charAt(i) == ';' && crochets==0){
				list.add(s.substring(begin,i));
				begin = i+1;
			}
		}
		list.add(s.substring(begin));
		for (int i =0 ; i<list.size() ;i++ ) {
			list.set(i, list.get(i).trim());
		}
		return list;
	}

}
