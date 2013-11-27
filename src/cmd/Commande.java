package cmd;

import ihm.Dessin;
import history.HistoryTortue;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cmd.Variables.ObjetVariables;
abstract class Cmd {

	//protected HistoryTortue history = new HistoryTortue();
	static ArrayList<ObjetVariables> liste_Variables = new ArrayList<ObjetVariables>();
	HistoryTortue history = new HistoryTortue();
	//static ArrayList<String> hist = new ArrayList<String>();
	/**
	 * 
	 * @param parametres
	 * @param dessin
	 * @param curseur
	 * @param addToHistory
	 * @throws IOException
	 */
	abstract String execute(String[] parametres, Dessin dessin, HashTable table);

	/**
	 * 
	 * @param tab
	 * @return h
	 */
	protected String toString(String[] tab){
		String s ="";
		int i=0;
		for (i=0 ; i<tab.length ;i++ ) {
			s += tab[i] + " ";
		}
		return s;
	}

	public String [] splitArgs(String s){
		int tmp =0;
		int begin = 0;
		ArrayList<String> list = new ArrayList<String>();
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			else if (s.charAt(i)== ')') tmp--;
			else if (s.charAt(i) == ' ' && tmp==0){
				list.add(s.substring(begin,i));
				begin = i+1;
			}
		}
		list.add(s.substring(begin));
		return list.toArray(new String[list.size()]);
	}

	public void afficheVar(){
		for (ObjetVariables o : liste_Variables ) {
			System.out.println("nom = "+o.getNom_Variable()+" val = "+o.getValeur_Variable());
		}
	}

	public ObjetVariables get_Variable(String nom_Variable){
		for (ObjetVariables var : liste_Variables) {
				if(var.getNom_Variable().equals(nom_Variable)) {
					System.out.println("Z");
					return var;
				}
		}
		return null;
	}

	public boolean parenthese(String s){
		for(int i = 0; i<s.length(); i++){
			if(s.charAt(i)=='(')return true;
		}
		return false;
	}
	public String calculeTab(String tab[]){
		int a = 0,b = 0;
		if(tab.length != 3)return "ERROR";

		if(tab[0].charAt(0) == '_' ){

			System.out.println("VAR11");
			ObjetVariables v = get_Variable(tab[0].substring(1));
			if(v != null) a = v.getValeur_Variable();
			else return "Impossible de faire le calcul la variable "+tab[0]+" n'éxiste pas";
		}
		else {
			System.out.println("VAR12");
			try{
				a = Integer.parseInt(tab[0]);
			}catch (NumberFormatException e1){
				return "Impossible de faire le calcul "+tab[0]+" n'est pas un nombre";
			}
		}
		if(tab[2].charAt(0) == '_' ){
			System.out.println("VAR21");
			ObjetVariables v = get_Variable(tab[2]);
			if(v != null) a = v.getValeur_Variable();
			else return "Impossible de faire le calcul la variable "+tab[2]+" n'éxiste pas";
		}
		else {
			System.out.println("VAR22");
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
				return "ERROR";

		}


	}
	public boolean canCalcul(ArrayList<String> s){
		for (int i = 0 ; i<s.size() ;i++ ) {
			s.set(i,s.get(i).trim());
		}

		for (int i = 0 ; i<s.size() ;i++ ) {
			if (i%2==0) {
				if(!(Pattern.matches("(-?[0-9]+)", s.get(i)) || Pattern.matches("^_[a-zA-Z]+)", s.get(i)))){
					return false;
				}
			}
			else {
				if(!Pattern.matches("[-+*/%]", s.get(i))){
					return false;
				}
			}
		}
		return true;
	}
	public boolean bienP(String s){
		int tmp =0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			else if (s.charAt(i)== ')') tmp--;
			if (tmp < 0) return false;
		}
		return (tmp==0);
	}
	public String subParenthese(String s){
		int begin = 0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') begin = i+1;
			else if (s.charAt(i)== ')') return s.substring(begin,i);
		}
		return null;
	}


}
