import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		String s = "(5*5)";
		String sf = "(5+2*4/5*52%2)";
		int g = nbP(s);
		for (int j = 0; j<g ; j++) {
			

			String ss = subParenthese(s);
			
			list = toList(ss);
			System.out.println(canCalcul(list));

			int i = calculeList(list);
			s = s.replace("("+ss+")",""+i);
			
		}
		System.out.println(s+" = "+(((10*5+2)+7*5)*(-150-(-99))));

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
	public static int nbP(String s){
		int tmp =0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			
		}
		return tmp;
	}
	public static int calculeList(ArrayList<String> s){
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

	public static ArrayList<String> toList(String s){
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