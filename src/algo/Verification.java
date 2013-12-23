package algo;

public class Verification {

	
	/**
	 * @param s
	 * @return
	 */
	public static boolean bienP(String s){
		int tmp =0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') tmp++;
			else if (s.charAt(i)== ')') tmp--;
			if (tmp < 0) return false;
		}
        //System.out.println(tmp);
		return (tmp==0);
	}
	
	/**
	 * @param s
	 * @return
	 */
	public static boolean parenthese(String s){
		for(int i = 0; i<s.length(); i++){
			if(s.charAt(i)=='(')return true;
		}
		return false;
	}
}
