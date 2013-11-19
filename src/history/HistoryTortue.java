package history;

import ihm.Fenetre;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import commande.HashTable;

public class HistoryTortue implements History {

	private static ArrayList<ObjetHistory> history;

	private static ArrayList<ObjetHistory> undo;

	public HistoryTortue() {
		history = new ArrayList<ObjetHistory>();
		undo = new ArrayList<ObjetHistory>();

	}

	@Override
	public void addToHistory(String Comande) {
		ObjetHistory n = new ObjetHistory(Comande, Fenetre.jPanelDessin);
		history.add(n);
	}

	@Override
	public void removeToHistory() throws IOException {
		if (!this.history.isEmpty()) {
			for (int i = this.history.size() - 1; i >= 0; i--) {
				String s = history.get(i).getHisto()
						.substring(0, history.get(i).getHisto().indexOf(" "));

				if (s.equals("MOVE")) {
					ObjetHistory suppr = this.history.get(i);
					this.undo.add(suppr);
					this.history.remove(i);
					// System.out.println("m : "+this.undo.get(0));
					return;

				} else if (s.equals("CIRCLE")) {
					ObjetHistory suppr = this.history.get(history.size() - 1);
					this.undo.add(suppr);
					this.history.remove(history.size() - 1);
					System.out.println("c : " + this.undo.get(0));
					return;

				}
				if (s.equals("REPEAT")) {
					ObjetHistory suppr = this.history.get(history.size() - 1);
					this.undo.add(suppr);
					this.history.remove(history.size() - 1);
					System.out.println("r : " + this.undo.get(0));
					return;

				}

			}

		}
		ObjetHistory s = this.history.get(this.history.size() - 1);

		this.undo.add(s);

		history.remove(this.history.size() - 1);
	}

	@Override
	public void saveHistory(String chemin) throws IOException {
		/*
		 * Si le chmin a déjà une extension on la retire et on la stock Et on la
		 * rajoute a la fin ;
		 */
		File destination = new File(chemin + ".txt");

		BufferedWriter output;
		output = new BufferedWriter(new FileWriter(destination));

		int ligne = 0;
		while (ligne < history.size()) {
			try {

				output.write(history.get(ligne).getHisto() + "\r\n");
				output.flush();

			} catch (IOException ioe) {
				System.out.println("erreur : " + ioe);
			}
			ligne++;
		}
		output.close();
	}

	public String redo() {
		/*
		 * Save curs
		 */

		int x = Fenetre.jPanelDessin.curseur.getAbscisse();
		int y = Fenetre.jPanelDessin.curseur.getOrdonnee();
		int direction = Fenetre.jPanelDessin.curseur.getDirection();
		Color couleur = Fenetre.jPanelDessin.curseur.getCouleur();

		ObjetHistory redo;
		if (this.undo.size() == 0) {

			return null;

		} else {
			redo = undo.get(undo.size() - 1);
			this.addToHistory(redo.getHisto());
			this.undo.remove(undo.size() - 1);

		}
		Fenetre.jPanelDessin.curseur.setAbscisse(redo.getX());
		Fenetre.jPanelDessin.curseur.setOrdonnee(redo.getY());
		Fenetre.jPanelDessin.curseur.setDirection(redo.getDirection());
		Fenetre.jPanelDessin.curseur.setCouleur(redo.getCouleur());

		try {

			HashTable.hgj(redo.getHisto(), true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Fenetre.jPanelDessin.curseur.setAbscisse(x);
		Fenetre.jPanelDessin.curseur.setOrdonnee(y);
		Fenetre.jPanelDessin.curseur.setDirection(direction);
		Fenetre.jPanelDessin.curseur.setCouleur(couleur);

		return redo.getHisto();
	}

	public void reset() {
		this.history = new ArrayList<ObjetHistory>();
		this.undo = new ArrayList<ObjetHistory>();
	}

	/****
	 * GET et SET
	 */

	public ArrayList<ObjetHistory> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<ObjetHistory> history) {
		this.history = history;
	}

	public ArrayList<ObjetHistory> getUndo() {
		return undo;
	}

	public void setUndo(ArrayList<ObjetHistory> undo) {
		this.undo = undo;
	}

}