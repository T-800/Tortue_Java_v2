public class Test {

	public static void main(String[] args) {
		String s = "(((10*5+2)+7*5)*(15-99))";
		String sf = "(5+5)";
		System.out.println("bp "+ bienP(sf));
		System.out.println("s2 "+ subParenthese(sf));
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

	on test chaque char 
}