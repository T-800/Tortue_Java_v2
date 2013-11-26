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

	public boolean canDoAllCalcule(String s){
		if (bienP(s)){
			int valeur = 0;
			ArrayList<String> list = new ArrayList<String>();
			int g = nbP(s);
			int result;
			for (int j = 0; j<g ; j++) {
				String ss = subParenthese(s);
				list = toList(ss);

				if(!canCalcul(list)) return false;
				result = calculeList(list);
				s = s.replace("("+ss+")",""+result);
				
			}
			try{
				valeur = Integer.parseInt(s);
			}catch (NumberFormatException e1){
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}

	public int doCalcule(String s){
		ArrayList<String> list = new ArrayList<String>();
		int g = nbP(s);
		int result = 0;
		for (int j = 0; j<g ; j++) {
			String ss = subParenthese(s);
			list = toList(ss);
			result = calculeList(list);
			s = s.replace("("+ss+")",""+result);				
		}
		return result;
	}

	private boolean canCalcul(ArrayList<String> s){
		for (int i = 0 ; i<s.size() ;i++ ) {
			s.set(i,s.get(i).trim());
		}

		for (int i = 0 ; i<s.size() ;i++ ) {
			if (i%2==0) {
				if(!(Pattern.matches("(-?[0-9]+)", s.get(i)) || Pattern.matches("^_[a-zA-Z]+)", s.get(i)))){
					return false;
				}

				// TODO : Ajouter le remplacement de variable
			}
			else {
				if(!Pattern.matches("[-+*/%]", s.get(i))){
					return false;
				}
			}
		}
		return true;
	}
	private int nbP(String s){
		int tmp =0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			
		}
		return tmp;
	}
	private int calculeList(ArrayList<String> s){
		int a,b,c;
		for (int i = 0 ; i<s.size() ;i++ ) {
			switch(s.get(i)){
				case "/" :

						a = Integer.parseInt(s.get(i-1));
						b = Integer.parseInt(s.get(i+1));
						c = (a/b);
						s.set(i,""+c);
						s.remove(i-1);
						s.remove(i);
						i--;
					break;
				case "*" :
						a = Integer.parseInt(s.get(i-1));
						b = Integer.parseInt(s.get(i+1));
						c = (a*b);
						s.set(i,""+c);
						s.remove(i-1);
						s.remove(i);
						i--;
					break;
				case "%" :
						a = Integer.parseInt(s.get(i-1));
						b = Integer.parseInt(s.get(i+1));
						c = (a%b);
						s.set(i,""+c);
						s.remove(i-1);
						s.remove(i);
						i--;
					break;
			}
		}
		for (int i = 0 ; i<s.size() ;i++ ) {
			switch(s.get(i)){
				case "+" :

						a = Integer.parseInt(s.get(i-1));
						b = Integer.parseInt(s.get(i+1));
						c = (a+b);
						s.set(i,""+c);
						s.remove(i-1);
						s.remove(i);
						i--;
					break;
				case "-" :
						a = Integer.parseInt(s.get(i-1));
						b = Integer.parseInt(s.get(i+1));
						c = (a-b);
						s.set(i,""+c);
						s.remove(i-1);
						s.remove(i);
						i--;
					break;
				
			}
		}
		return Integer.parseInt(s.get(0));
	}

	private boolean bienP(String s){
		int tmp =0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			else if (s.charAt(i)== ')') tmp--;
			if (tmp < 0) return false;
		}
		return (tmp==0);
	}

	private String subParenthese(String s){
		int begin = 0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') begin = i+1;
			else if (s.charAt(i)== ')') return s.substring(begin,i);
		}
		return null;
	}

	private ArrayList<String> toList(String s){
		ArrayList<String> list = new ArrayList<String>();
		int begin = 0;

		for (int i = 0; i<s.length() ;i++ ) {
			if(s.charAt(i) == '+' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '%'){

				list.add(s.substring(begin,i));
				list.add(""+s.charAt(i));
				begin = i+1;
			}
			else if(s.charAt(i) == '-' && i>0 && (s.charAt(i) == '+' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '%' || s.charAt(i) == '-' || s.charAt(i) == '(')){
				list.add(s.substring(begin,i));
				begin = i;	
			}
			
		}

		list.add(s.substring(begin));
		for (int i = 0 ;i < list.size();i++) {
			if(list.get(i).equals("")) list.remove(i);
		}

		return list;

	}


}
