package commande;

import ihm.Fenetre;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JOptionPane;

/*
 * Ouvre une nouvelle fenetre de dessin
 */
public class New implements Commande {
	/**
	 * @param i
	 */
	public static int i;

	@Override
	/**
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		// TODO Auto-generated method stub
		/*
		 * @param option
		 * 		Cree les differents choix possible de l'utilisateur :
		 * 		Oui, Non, Annuler
		 */
		int option = JOptionPane.showConfirmDialog(null,
				"Voulez vous enregistrer votre dessin", "Tortue",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		/*
		 * Verifie qu'elle choix a ete fait
		 */
		if (option == JOptionPane.YES_OPTION) {
			/*
			 * le resultat affecte a oui est 1
			 */
			i = 1;
			
			/*
			 * Si le choix est oui, il fait appel a save puis a clear et met
			 * un fond de couleur blanc
			 */
			try {
				HashTable.hgj("SAVE", false);
				Fenetre.jPanelDessin.setBackground(Color.white);
				HashTable.hgj("CLEAR", false);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (option == JOptionPane.NO_OPTION) {
			/*
			 * Si non, 0 est affecte au resultat seul la commande clear est
			 * appelee avec en plus un fond blanc remis
			 */
			i = 0;
			try {
				HashTable.hgj("CLEAR", false);
				Fenetre.jPanelDessin.setBackground(Color.white);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (option == JOptionPane.CANCEL_OPTION) {
			/*
			 * Si le choix est annule le resultat est -1
			 * et rien ne se passe
			 */
			i = -1;
		}
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
	/*
	 * Verifie que la commande n'a aucun argument
	 * (non-Javadoc)
	 * @see commande.Commande#canDoIt(java.lang.String[])
	 */
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		if (Fenetre.jPanelDessin.getBackground() == Color.white
				&& Fenetre.jPanelDessin.cmd.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see commande.Commande#random()
	 */
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
