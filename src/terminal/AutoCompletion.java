package terminal;

import commande.Commande;
import commande.ExFonction;
import liste.ListeFonctions;

import java.util.ArrayList;

public class AutoCompletion {

    private ListeFonctions fonctionsListe;
    private final String[] commande = { "BGCOLOR", "CENTER",
            "CLEAR",  "ERASE","FONCTION" ,"HELP",  "MOVE", "NEW",
            "OPEN", "PENCOLOR", "PENSIZE", "REDO",
            "REPEAT","SAVE", "TURN", "UNDO","RANDOM" };

    public AutoCompletion(ListeFonctions fonctionsListe){

        this.fonctionsListe=fonctionsListe;
    }

    private String getLastWord(int currentposition,String phrase){
        String word = "";
        for (int i = 0; i<currentposition;i++){
            if(phrase.charAt(i) == ' '|| phrase.charAt(i) == ';' || phrase.charAt(i) == '[') word = "";
            else {
                word += phrase.charAt(i);
            }
        }
        return word;
    }
    public int sizeWord(int currentposition,String phrase){
        String word = getLastWord(currentposition,phrase);
        return word.length();
    }

    public ArrayList<String> findMatch(int currentposition,String phrase){
        String word = getLastWord(currentposition,phrase);
        ArrayList<String> list = new ArrayList<>();
        if(word.equals(""))return null;
        for (String s : commande){
            String sub ="";
            try{
                sub = s.substring(0,word.length());
            }
            catch (IndexOutOfBoundsException ignored){}
            if(sub.equalsIgnoreCase(word)){
                if (!Character.isUpperCase(word.charAt(word.length()-1))) s = s.toLowerCase();
                list.add(s);
            }
        }
        for(ListeFonctions.ObjetFonction h: Commande.getListeFonctions().getliste()){
            String sub ="";
            sub = h.getNom_Fonction().substring(0,word.length());
            if (sub.equalsIgnoreCase(word)){
                list.add(h.getNom_Fonction());
            }
        }
        return list;
    }











    public static void main(String [] args){
        //String s = "Salut renaud Adequin",ss=getLastWord(s.length(),s);
        //System.out.print("resultat = "+ss+"*");

    }
}
