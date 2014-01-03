package algo;

/*
 * cette classe contient les fonction d'évaluation d'expression
 * test d'entiers (variables et/ou calcules)
 * On peut aussi connecter deux ou plus expressions avec des connecteurs
 *
 * Syntaxe :
 * 	(((A == B) && (C >= A)) || (C <= B))
 *
 * Évaluation :
 * 	==
 * 	!=
 * 	>
 * 	>=
 * 	<
 * 	>=
 *
 * Connecteur :
 * 	&&
 * 	||
 *
 * Négation :
 * 	!(A == B) == (A != B)
 *
 * 	CONSTANTE :
 *
 * 	PENSIZE
 * 	WIDTH
 * 	HEIGHT
 */

import dessin.Curseur;
import interfaceGraphique.Fenetre;

public class Bool {
    private int WIDTH;
    private int HEIGHT;
    private int PENSIZE;
    private  Curseur curseur;

    public Bool(Curseur curseur){
        this.curseur = curseur;
    }



    private void majConstantes(){
        this.WIDTH = Fenetre.getMaxDessin()[0];
        this.HEIGHT = Fenetre.getMaxDessin()[1];
        this.PENSIZE = curseur.getPenSize();
    }

    /*
    * au départ je recupere un String expression qui doit etre une expression boolean
    * pour commencer :
    * je verifie la syntaxe :
    *   nb parentese
    *   bien parentheses
    * ensuite je prend les parentheses les plus a l'intérieur rec:
    *   je fais le test boolean
    *   je remplace ces parentheses par le resultat du test [TRUE,FALSE]
    *
    * a la fin de l'algo il ne reste que [TRUE,FALSE,ERROR]
    *
    * */

    public String doBooleanTest(String expression){

        if(Verification.bienP(expression)){
            while(Verification.parenthese(expression)){
                String subExpression = Convert.subParenthese(expression);
                String ss_tmp = subExpression.trim();
                String subS[] = ss_tmp.split(" ");
                String cal = calculeExpr(subS);
                expression = expression.replace("("+subExpression+")",cal);

            }
            return expression;
        }
        else return "Pas bien parenthésé";
    }

    private String calculeExpr(String tab[]){
        //System.out.println("---debut----");
        /*for (String s : tab){
            System.out.println(s);
        }
        System.out.println("---fin----"); */
        int a,b;
        //System.out.println("TAB CLAC : "+tab[0]+" "+tab[1]+" "+tab[2]);
        if(tab.length == 1)return tab[0];
        if(tab.length != 3)return "Erreur de syntaxe!";
        if(tab[0].charAt(0) == '!' && tab[0].substring(1).equalsIgnoreCase("false"))a = 1;
            else if(tab[0].charAt(0) == '!' && tab[0].substring(1).equalsIgnoreCase("true"))a = 0;
            else if (tab[0].equalsIgnoreCase("true"))a = 1;
            else if (tab[0].equalsIgnoreCase("false"))a = 0;
            else{
                try{
                    a = Integer.parseInt(tab[0]);
                }catch (NumberFormatException e1){
                    return "Impossible de faire le calcul "+tab[0]+" n'est pas un nombre";
                }
            }
        if(tab[2].charAt(0) == '!' && tab[2].substring(1).equalsIgnoreCase("false"))b = 1;
        else if(tab[2].charAt(0) == '!' && tab[2].substring(1).equalsIgnoreCase("true"))b = 0;
            else if (tab[2].equalsIgnoreCase("true"))b = 1;
            else if (tab[2].equalsIgnoreCase("false"))b = 0;
            else{
                try{
                    b = Integer.parseInt(tab[2]);
                }catch (NumberFormatException e1){
                    return "Impossible de faire le calcul "+tab[2]+" n'est pas un nombre";
                }
            }
        boolean abis,bbis;
        switch(tab[1]){

            case "==" :
                System.out.println(""+(a==b));
                return ""+(a==b);

            case "!=" :
                System.out.println(""+(a!=b));
                return ""+(a!=b);
            case ">" :
                System.out.println(""+(a>b));
                return ""+(a>b);
            case ">=" :
                System.out.println(""+(a>=b));
                return ""+(a>=b);
            case "<" :
                System.out.println(""+(a<b));
                return ""+(a<b);
            case "<=" :
                System.out.println(""+(a<=b));
                return ""+(a<=b);
            case "||" :

                abis = a == 1;
                bbis = b == 1;
                return "" + (abis || bbis);
            case "&&" :
                abis = a == 1;
                bbis = b == 1;
                return "" + (abis && bbis);

            default :
                return Convert.calculeTab(tab);

        }

    }


    public static void main(String [] args){
         Bool bool = new Bool(null);

        String s = "(((100 - 20) - 20) >= 0)";
        //System.out.print(bool.doBooleanTest(s,null)+"\nres : "+(((100 - 20) -20) >= 0));

    }


}
