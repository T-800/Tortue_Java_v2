package commande;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Corriger les erreurs le crochet disparait et ajouter les lettre possible en
 * plus
 * 
 * @author slater
 * 
 */
public class Autocompletion {

	final static String[] commande = { "BACK", "BACKGROUNDCOLOR", "CENTER",
			"CLEAR", "DOWN", "ERASE", "HELP", "LEFT", "MOVE", "NEW",
			"OPEN", "PENCOLOR", "PENDOWN", "PENSIZE", "PENUP", "REDO",
			"REMEMBER", "REPEAT", "RIGHT", "SAVE", "TURN", "UNDO", "UP", "VAR","RANDOM" };
	
	static String[] variable;

	
	/**
	 * Recherch dans le tableau de commande toutes les commande qui commence par 
	 * @param word
	 * @return un arrayliste du resultat de la recherche
	 */
	public static ArrayList<String> search(String word) {
		ArrayList<String> word_find = new ArrayList<String>();

		for (String s : commande) {
			if (!(s.length() < word.length())) {
				String sub = s.substring(0, word.length());
				if (word.equals(sub))
					word_find.add(s);
			}
		}
		return word_find;
	}

	/**
	 * Cherche dand la liste s'il n'y a pas des lettres a rajouter au mot de notre recherche
	 * @param word
	 * @param word_find_list
	 * @return le mot avec ou nom des lettre en plus 
	 */
	public static String extraChar(String word, ArrayList<String> word_find_list) {
		char c;
		for (String s : word_find_list) {
			if (s.length() <= word.length())
				return word;
		}
		c = word_find_list.get(0).charAt(word.length());
		for (String s : word_find_list) {
			if (c != s.charAt(word.length()))
				return word;
		}
		return word + c;
	}
	
	/**
	 * 
	 * @param terminal
	 * @return
	 */

	public static ArrayList<String> complete(String terminal) {
		
		terminal = terminal.toUpperCase();

		String[] decoup_terminal = terminal.split(" ");

		String word_to_search = decoup_terminal[decoup_terminal.length - 1];
		String non_char = word_to_search.substring(0, 1);
		Pattern p = Pattern.compile("\\W");
		Matcher m = p.matcher(non_char);
		if (m.find()) {
			System.out.print("nonchar");
			word_to_search = word_to_search.replaceAll("\\W", " ");
		} else {
			non_char = "";
		}

		ArrayList<String> word_find = search(word_to_search);
		if (word_find.size() == 0)
			return word_find;
		if (word_find.size() == 1) {
			String tmp = "";
			for (int i = 0; i < decoup_terminal.length - 1; i++) {
				tmp += " " + decoup_terminal[i];
			}
			tmp += " " + word_find.get(0);
			word_find.set(0, tmp);
			return word_find;
		}
		System.out.println(word_find.size());

		String s = word_to_search;
		word_to_search = extraChar(word_to_search, word_find);
		while (s.length() < word_to_search.length()) {
			s = word_to_search;
			word_to_search = extraChar(word_to_search, word_find);

		}

		
		String tmp = "";
		for (int i = 0; i < decoup_terminal.length - 1; i++) {
			tmp += " " + decoup_terminal[i];
		}
		tmp += " " + non_char + word_to_search;
		word_to_search = tmp;

		word_find.add(word_to_search);
		System.out.println(word_find.get(word_find.size() - 1));
		return word_find;
	}

}
