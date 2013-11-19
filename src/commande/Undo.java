package commande;

import history.ObjetHistory;
import ihm.Fenetre;

import java.io.IOException;
import java.util.ArrayList;

public class Undo implements Commande {

	// Commande UNDO : annule la denriere commande executer
	// Ajouter le cas ou on veux annuler la lecture d'un fichier ou d'un repeat

	@Override
	public void execute(String[] parametres, boolean addToHistory)
			throws IOException {

		System.out.println("AVANT******************");
		for (ObjetHistory p : history.getHistory()) {
			System.out.println(p);

		}
		System.out.println("AVANT******************");
		history.removeToHistory();
		System.out.println("APRES******************");
		for (ObjetHistory p : history.getHistory()) {
			System.out.println(p);

		}
		System.out.println("APRES******************");
		ArrayList<ObjetHistory> new_history = new ArrayList<ObjetHistory>();
		// System.out.println("HGFDSSD******************");
		for (ObjetHistory p : history.getHistory()) {
			System.out.println(p);
			new_history.add(p);
		}
		// System.out.println("HGFDSSD******************");
		ArrayList<ObjetHistory> new_undo = history.getUndo();
		HashTable.hgj("CLEAR", false);
		history.setUndo(new_undo);
		// dessin.cmd = new ArrayList<Object>();
		// curseur.new_Curseur(dessin.getWidth()/2,dessin.getHeight()/2);
		for (ObjetHistory p : new_history) {
			HashTable.hgj(p.getHisto(), addToHistory);
		}

		// history.show();

	}

	@Override
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		if (history.getHistory().size() == 0) {
			Fenetre.jTextAreaHistory.addError("il n'y a rien a annuler");
			return false;
		}
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
