package commande;

import ihm.Fenetre;
import ihm.PanelHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Cette classe fait le lien entre l'interpreteur de commande et les differentes
 * fonctions possible
 * 
 * 
 */
public class HashTable {

	public static Hashtable<String, Commande> hashCommande = new Hashtable<String, Commande>();

	/**
	 * déclaration de la table de hashage
	 */
	public HashTable(PanelHistory panel) {

		/*hashCommande.put("BACK", new Back());
		hashCommande.put("BACKGROUNDCOLOR", new BackgroundColor());
		hashCommande.put("CENTER", new Center());
		hashCommande.put("CLEAR", new Clear());
		hashCommande.put("DOWN", new Down());
		hashCommande.put("GO", new Go());
		hashCommande.put("ERASE", new Erase());
		hashCommande.put("HELP", new Help());
		hashCommande.put("LEFT", new Left());
		hashCommande.put("MOVE", new Move());
		hashCommande.put("NEW", new New());
		hashCommande.put("OPEN", new Open());
		hashCommande.put("PENCOLOR", new PenColor());
		hashCommande.put("PENDOWN", new PenDown());
		hashCommande.put("PENSIZE", new Pensize());
		hashCommande.put("PENUP", new PenUp());
		hashCommande.put("REDO", new Redo());
		hashCommande.put("REMEMBER", new Remember());
		hashCommande.put("REPEAT", new Repeat());
		hashCommande.put("RIGHT", new Right());
		hashCommande.put("SAVE", new Save());
		hashCommande.put("TURN", new Turn());
		hashCommande.put("UNDO", new Undo());
		hashCommande.put("UP", new Up());
		hashCommande.put("VAR", new Variables());
		hashCommande.put("/*", new Commentaire());*/

	}

	public static void hgj(String commande, boolean add) throws IOException {

		/*
		 * Supprime tout les espace en trop
		 */
		commande = commande.replaceAll("\\s+", " ");

		/*
		 * On transforme la commande en tableau
		 */
		String[] nomCmd = splitSpace(commande);
		
		/*
		 * On met la premiere case du tableau en majuscule
		 */
		nomCmd[0] = nomCmd[0].toUpperCase();
		if (add)
			sendToPanel(nomCmd, commande);

		/*
		 * Si la commande existe
		 */
		if (hashCommande.containsKey(nomCmd[0])) {
			/*
			 * si elle peut etre executé
			 */
			if (hashCommande.get(nomCmd[0]).canDoIt(nomCmd)) {
				
				hashCommande.get(nomCmd[0]).execute(nomCmd, add);
				Fenetre.jPanelDessin.repaint();
			}
		} else {
			/*
			 * si elle n'éxiste pas on affiche l'erreur
			 */
			Fenetre.jTextAreaHistory.addError("La commande "
					+ nomCmd[0].replace("\n", "<br>") + " n'existe pas");
		}

	}

	/**
	 * cette fonction test si la commande peut etre execute
	 * @param parametre 
	 * @return 
	 */
	public static boolean itsOK(String[] parametre) {

		return hashCommande.get(parametre[0]).canDoIt(parametre);

	}
	
	/**
	 * on test si la commande s est un repeat
	 * @param s
	 * @return
	 */

	private static boolean isRepeat(String s) {
		if (s.length() < 6)
			return false;
		String d = s.substring(0, 6);
		d = d.toUpperCase();
		return d.equals("REPEAT");
	}
	
	
	/**
	 * fonction de découpage de la commande en tableau 
	 * case 0 pour le nom de la commande et les autres pour les arguments
	 * @param commande
	 * @return
	 */

	public static String[] splitSpace(String commande) {
		ArrayList<String> tab = new ArrayList<String>();
		String[] split = commande.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (!split[i].equals(" "))
				tab.add(split[i]);
		}

		if (isRepeat(tab.get(0))) {

			/*
			 * On retire les crochets du "premier" repeat
			 */
			String d = tab.get(2).replaceFirst("\\W", "");
			tab.set(2, d);

			d = tab.get(tab.size() - 1);
			d = d.substring(0, d.length() - 1);
			tab.set(tab.size() - 1, d);

			/*
			 * Si une case de l'arraylist est vide on la retire
			 */
			for (int i = 0; i < tab.size(); i++) {
				if (tab.get(i).equals(""))
					tab.remove(i);
			}

			String[] re = new String[3];

			re[0] = tab.get(0);
			re[1] = tab.get(1);
			d = "";
			for (int i = 2; i < tab.size(); i++) {
				if (i != 2)
					d += " ";
				d += tab.get(i);
			}
			re[2] = d;
			split = re;
		}

		return split;

	}
	/**
	 * 
	 * @param c
	 * @param commande
	 */

	public static void sendToPanel(String[] c, String commande) {

		if (isRepeat(commande)) {
			Fenetre.jTextAreaHistory.addTexte(commande);
			Fenetre.jTextAreaHistory.addTexte("[");
			for (int i = 2; i < c.length; i++) {
				Fenetre.jTextAreaHistory.addTexteIndent(c[i]);
				Fenetre.jTextAreaHistory.addTexteIndent("\n");
			}
			Fenetre.jTextAreaHistory.addTexte("]");
		} else if (c[0].equals("/*")) {
			Fenetre.jTextAreaHistory.addCom(commande);
		} else {
			Fenetre.jTextAreaHistory.addTexte(commande);
		}
	}

	public static boolean isVariable(String s) {
		return s.charAt(0) == '_';
	}

	public static int variableExiste(String nom) {
		for (int i = 0; i < Commande.variables.size(); i++) {
			if (Commande.variables.get(i).getNom_Variable().equals(nom)) {
				return i;
			}
		}
		return -1;
	}

}
