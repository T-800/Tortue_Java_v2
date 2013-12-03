import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		String s = "(((5 / 2) + 5) - ((20 % 10) - -200)) 55 () tetqetqtefeq zrqdssd";
		String[] sf = RR(s);
		for (int i =0 ; i<sf.length ;i++ ) {
			System.out.println(sf[i]);
		}//move (5 * 8) (10 * 9)
	}

	public static String [] RR(String s){
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

	public static boolean parenthese(String s){
		for(int i = 0; i<s.length(); i++){
			if(s.charAt(i)=='(')return true;
		}
		return false;
	}
	private static String calculeTab(String tab[]){
		int a = 0,b = 0;
		if(tab.length != 3)return "ERROR";

		if(tab[0].charAt(0) == '_' ){
			//~ ObjetV v = getVar(tab[0]);
			//~ if(v != null) a = v.getValeur();
			//~ else return "Impossible de faire le calcul la variable "+tab[0]+" n'éxiste pas";
		}
		else {
			try{
				a = Integer.parseInt(tab[0]);
			}catch (NumberFormatException e1){
				return "Impossible de faire le calcul "+tab[0]+" n'est pas un nombre";
			}
		}
		if(tab[2].charAt(0) == '_' ){
			//~ ObjetV v = getVar(tab[2]);
			//~ if(v != null) b = v.getValeur();
			//~ else return "Impossible de faire le calcul la variable "+tab[2]+" n'éxiste pas";
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
				return "ERROR";

		}


	}
	public static boolean canCalcul(ArrayList<String> s){
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
	public static boolean bienP(String s){
		int tmp =0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			else if (s.charAt(i)== ')') tmp--;
			if (tmp < 0) return false;
		}
		return (tmp==0);
	}
	public static String subParenthese(String s){
		int begin = 0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') begin = i+1;
			else if (s.charAt(i)== ')') return s.substring(begin,i);
		}
		return null;
	}


}
