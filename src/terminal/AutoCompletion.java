package terminal;

import liste.ListeFonctions;
import liste.ListeVariables;

import java.util.ArrayList;

public class AutoCompletion {

    private ListeFonctions fonctionsListe;
    private ListeVariables variableListe;
    private final String[] commande = { "BACK", "BACKGROUNDCOLOR", "CENTER",
            "CLEAR", "DOWN", "ERASE","FONCTION" ,"HELP", "LEFT", "MOVE", "NEW",
            "OPEN", "PENCOLOR", "PENDOWN", "PENSIZE", "PENUP", "REDO",
            "REMEMBER", "REPEAT", "RIGHT", "SAVE", "TURN", "UNDO", "UP", "VAR","RANDOM" };

    public AutoCompletion(ListeFonctions fonctionsListe, ListeVariables variableListe){

        this.fonctionsListe=fonctionsListe;
        this.variableListe=variableListe;
    }

    public String getLastWord(int currentposition,String phrase){
        String word = "";
        for (int i = 0; i<currentposition;i++){
            if(phrase.charAt(i) == ' ') word = "";
            else {
                word += phrase.charAt(i);
            }
        }
        System.out.print("Word = "+word);
        return word;
    }

    public ArrayList<String> findMatch(int currentposition,String phrase){
        String word = getLastWord(currentposition,phrase);
        ArrayList<String> list = new ArrayList<>();
        switch (word.charAt(0)){
            case ':' :
                for (ListeFonctions.ObjetFonction o : fonctionsListe.getliste()){
                    String s = o.getNom_Fonction();
                    String sub ="";
                    try{
                        sub = s.substring(0,word.length()-1);
                    }
                    catch (IndexOutOfBoundsException e){

                    }
                    if(sub.equalsIgnoreCase(word.substring(1))){
                        System.out.println("ADD : "+sub);
                        list.add(s);
                    }
                }
                break;
            case '_' :
                for (ListeVariables.ObjetVariables o : variableListe.getliste()){
                    String s = o.getNom_Variable();
                    String sub ="";
                    try{
                        sub = s.substring(0,word.length()-1);
                    }
                    catch (IndexOutOfBoundsException e){

                    }
                    if(sub.equalsIgnoreCase(word.substring(1))){
                        System.out.println("ADD : "+sub);
                        list.add(s);
                    }
                }
                break;
            default:
                for (String s : commande){
                    String sub ="";
                    try{
                        sub = s.substring(0,word.length());
                    }
                    catch (IndexOutOfBoundsException e){

                    }
                    if(sub.equalsIgnoreCase(word)){
                        System.out.println("ADD : "+sub);
                        list.add(s);
                    }
                }
        }
        return list;
    }











    public static void main(String [] args){
        //String s = "Salut renaud Adequin",ss=getLastWord(s.length(),s);
        //System.out.print("resultat = "+ss+"*");

    }
}
