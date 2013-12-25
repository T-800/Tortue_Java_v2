package algo;

import liste.ListeVariables;
import liste.ListeVariables.ObjetVariables;

import java.util.ArrayList;

public class Convert {

    public static void main(String [] args){
        String[] tab = {"(5 + 5)","((1 + 1) * (( 10 + 10) % 3)) ()","(5+4)"};
        for (String s : tab){
            System.out.println(s + " = " +valeurIntArgument(s, null));
        }
    }

    public static String valeurIntArgument(String arg,ListeVariables listeVariables){
        switch (arg.charAt(0)){
            case '(' : // forme (5 + 5)
                // test la syntaxe

                if(Verification.bienP(arg)){
                    while(Verification.parenthese(arg)){
                        String ss = subParenthese(arg);
                        String ss_tmp = ss.trim();
                        String subS[] = ss_tmp.split(" ");
                        String cal = Convert.calculeTab(subS,listeVariables);
                        try{
                            int i = Integer.parseInt(cal);
                            arg = arg.replace("("+ss+")",""+i);
                        }catch (NumberFormatException e1){
                            return("Impossible de faire le calcul! : "+cal);
                        }

                    }
                    return arg;
                }
                else return "Pas bien parenthésé";
            case '_':// forme _variable
                String varNameString = arg.substring(1);
                ObjetVariables var = Convert.get_Variable(listeVariables,varNameString);
                if(var == null) return "La variable "+ arg+" n'éxiste pas.";
                return ""+var.getValeur_Variable();
            default : // 10 ou valeur inccorecte foo
                return arg;
        }
    }

    public static ArrayList<String> valeurStringtArgument(String s){

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



	public static  ObjetVariables get_Variable(ListeVariables liste_Variables,String nom_Variable){
		for (ObjetVariables var : liste_Variables.getliste()) {
				if(var.getNom_Variable().equals(nom_Variable)) {
					return var;
				}
		}
		return null;
	}

    public static void printParam(String[] commande){
        System.out.println("Param Cmd :");
        for (String aCommande : commande) {
            System.out.println("\t" + aCommande);
        }
        System.out.println("Param Cmd Fin");
    }

	private static String calculeTab(String tab[],ListeVariables liste_Variables){
		int a,b;
		if(tab.length != 3)return "Pas bien parenthésé";

		if(tab[0].charAt(0) == '_' ){
			ObjetVariables v = Convert.get_Variable(liste_Variables, tab[0].substring(1));
			if(v != null) {
                String s = v.getValeur_Variable();
                a = Integer.parseInt(s);
            }
			else return "La variable "+tab[0]+" n'éxiste pas";
		}
		else {
			try{
				a = Integer.parseInt(tab[0]);
			}catch (NumberFormatException e1){
				return tab[0]+" n'est pas un nombre";
			}
		}
		if(tab[2].charAt(0) == '_' ){
			ObjetVariables v = Convert.get_Variable(liste_Variables,tab[2].substring(1));
			if(v != null) {
                String s = v.getValeur_Variable();
                b = Integer.parseInt(s);
            }
			else return tab[2]+" n'éxiste pas";
		}
		else {
			try{
				b = Integer.parseInt(tab[2]);
			}catch (NumberFormatException e1){
				return tab[2]+" n'est pas un nombre";
			}
		}
		switch(tab[1]){
			case "/" :

				try{
					return ""+(a/b);
				}catch (ArithmeticException e1){
					return tab[2];
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
	 * @param s   g
	 * @return    g
	 */
	protected static String subParenthese(String s){
		int begin = 0;
		for (int i =0 ; i<s.length() ;i++ ) {
			if (s.charAt(i)== '(') begin = i+1;
			else if (s.charAt(i)== ')') return s.substring(begin,i);
		}
		return null;
	}



}
