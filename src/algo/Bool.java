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
import liste.ListeVariables;

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

    public String doBooleanTest(String expression, ListeVariables listeVariables){

        if(Verification.bienP(expression)){
            boolean canContinue = true;
            while(canContinue && Verification.parenthese(expression)){
                String subExpression = Convert.subParenthese(expression);
                String ss_tmp = subExpression.trim();
                String subS[] = ss_tmp.split(" ");
                String cal = calculeex(subS, listeVariables);
                try{
                    //int i = Integer.parseInt(cal);
                    expression = expression.replace("("+subExpression+")",cal);
                }catch (NumberFormatException e1){
                    canContinue = false;
                    return(cal);
                }

            }
            return expression;
        }
        else return "Ce calcul n'est pas bien parenthésé";
    }

    private String calculeex(String tab[],ListeVariables liste_Variables){
        int a = 0,b = 0;
        //System.out.println("TAB CLAC : "+tab[0]+" "+tab[1]+" "+tab[2]);
        if(tab.length == 1)return tab[0];
        if(tab.length != 3)return "ERROR";
        if(tab[0].charAt(0) == '_' ){
            ListeVariables.ObjetVariables v = Convert.get_Variable(liste_Variables, tab[0].substring(1));
            if(v != null) {
                String s = v.getValeur_Variable();
                a = Integer.parseInt(s);
            }
            else return "Impossible de faire le calcul la variable "+tab[0]+" n'éxiste pas";
        }
        else {
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

        }
        if(tab[2].charAt(0) == '_' ){
            ListeVariables.ObjetVariables v = Convert.get_Variable(liste_Variables,tab[2]);
            if(v != null) {
                String s = v.getValeur_Variable();
                a = Integer.parseInt(s);
            }
            else return "Impossible de faire le calcul la variable "+tab[2]+" n'éxiste pas";
        }
        else {
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
        }
        boolean abis,bbis;
        switch(tab[1]){

            case "==" :
                return ""+(a==b);

            case "!=" :
                return ""+(a!=b);
            case ">" :
                return ""+(a>b);
            case ">=" :
                return ""+(a>=b);
            case "<" :
                return ""+(a<b);
            case "<=" :
                return ""+(a<=b);
            case "||" :

                if(a == 1)abis = true;
                else abis = false;
                if(b == 1)bbis = true;
                else bbis = false;
                return "" + (abis || bbis);
            case "&&" :
                if(a == 1)abis = true;
                else abis = false;
                if(b == 1)bbis = true;
                else bbis = false;
                return "" + (abis && bbis);

            default :
                return "ERRdfsdgh;j;OR";

        }

    }


    public static void main(String [] args){
         Bool bool = new Bool(null);

        String s = "(!(10 < 5) || (false || !false))";
        //System.out.print(bool.doBooleanTest(s,null));

    }


}
