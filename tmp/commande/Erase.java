package commande;

import ihm.Fenetre;

import java.awt.Color;
import java.io.IOException;

/**
 * La commande ERASE fonctionne comme une gomme
 * elle prend deux entiers qui permet d'effacer le trait ou cercle dessiner
 * Elle reprend les memes propri�t�s que le curseur sauf la couleur de la gomme
 * qui sera la meme que le fond actuel
 * 
 * @see commande.Commande
 * @see objetQuiDessine.CurseurQuiDessine
 * @see objetQUiDessine.GommeQuiEfface
 */
public class Erase implements Commande {

	@Override
	/**
	 * 
	 * @see commande.Commande#execute(java.lang.String[], ihm.Dessin,
	 * objetQuiDessine.CurseurQuiDessine, objetQuiDessine.GommeQuiEfface,
	 * boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		// TODO Auto-generated method stub
		/**
		 * Envoi la commande dans l'historique
		 */
		if (addToHistory) {
			history.addToHistory(ToString(parametres));
		}
		/**
		 * @param oldcurseur Sauvegarde la couleur du curseur actuel
		 */
		Color oldcurseur = Fenetre.jPanelDessin.curseur.getCouleur();

		/**
		 * @param couleurfond R�cup�re la couleur de fond
		 */
		Color couleurfond = Fenetre.jPanelDessin.getBackground();

		/**
		 * Donne la couleur du fond au curseur
		 */
		Fenetre.jPanelDessin.curseur.setCouleur(couleurfond);

		/**
		 * @param d R�cup�re la direction du curseur
		 */
		int d = Fenetre.jPanelDessin.curseur.getDirection();

		/**
		 * Met la direction du curseur en SON n�gatif
		 */
		Fenetre.jPanelDessin.curseur.setDirection(d + 180);

		/**
		 * Lorsque les parametres rentr�s sont de deux
		 */
		if (parametres.length == 3) {
			try {
				/*
				 * Fait appel a la fonction MOVE avec les deux parametres entr�s
				 * en prenant les nouvelles valeurs du curseur
				 */
				HashTable.hgj("MOVE" + " " + parametres[1] + " "
						+ parametres[2], false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * Fin execution de la fonction move, reprend les valeurs initiales
			 * du curseur
			 */
			Fenetre.jPanelDessin.curseur.setCouleur(oldcurseur);
			Fenetre.jPanelDessin.curseur.setDirection(d);
		}
		/*
		 * Lorsque un seul parametre est rentr�
		 */
		if (parametres.length == 2) {
			try {
				/*
				 * Fait appel a la fonction MOVE avec les deux parametres entr�s
				 * en prenant les nouvelles valeurs du curseur
				 */
				HashTable.hgj("MOVE" + " " + parametres[1], false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * Fin execution de la fonction move, reprend les valeurs initiales
			 * du curseur
			 */
			Fenetre.jPanelDessin.curseur.setCouleur(oldcurseur);
			Fenetre.jPanelDessin.curseur.setDirection(d);
		}

		Fenetre.jPanelDessin.repaint();

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#ToString(java.lang.String[])
	 */
	@Override
	public String ToString(String[] tab) {
		String s = "";
		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	/*
	 * V�rifie que l'on peut dessiner donc que le curseur est en pendown, qu'il
	 * y a le bon nombre d'argument, et que ce sont bien des entiers
	 * 
	 * @see commande.Commande#canDoIt(java.lang.String[], ihm.Dessin,
	 * objetQuiDessine.CurseurQuiDessine)
	 */
	@Override
	public boolean canDoIt(String[] parametres) {
		/*
		 * abscisse x ordonnee y
		 */
		Integer x;
		Integer y;

		/*
		 * V�rifie que le curseur est bien sur le dessin
		 * 
		 * @return 4 Si le curseur n'est pas en pendown
		 */
		if (!Fenetre.jPanelDessin.curseur.getPendown()) {
			Fenetre.jTextAreaHistory
					.addError("Votre curseur n'est pas pose (Vous ne dessinez pas)");
			return false;
		}

		/*
		 * Si le nombre d'arguments rentr� est correct (donc la longueur du
		 * tableau est 3) v�rifier si les coordonn�es sont biendes entiers et
		 * s'ils sont dans le dessin
		 */
		if (parametres.length == 3) {
			try {
				/*
				 * Transforme le parametres[1] et parametres[2] en un int et les
				 * affecte respectivement � x et y;
				 */
				x = Integer.parseInt(parametres[1]);
				y = Integer.parseInt(parametres[2]);
			} catch (NumberFormatException e1) {
				/*
				 * @return 3 Si un des parametres n'est pas un entier
				 */
				Fenetre.jTextAreaHistory
						.addError("un des parametres n'est pas un entier");
				return false;
			}

			if (x > Fenetre.jPanelDessin.getWidth() / 2) {
				x = Fenetre.jPanelDessin.getWidth() / 2 - x;
			} else {
				x = Fenetre.jPanelDessin.getWidth() / 2 + x;
			}

			if (y < Fenetre.jPanelDessin.getHeight() / 2) {
				y = Fenetre.jPanelDessin.getHeight() / 2 - y;
			} else {
				y = Fenetre.jPanelDessin.getHeight() / 2 + y;
			}

			if (x.intValue() > Fenetre.jPanelDessin.getWidth()
					|| x.intValue() < 0
					|| y.intValue() > Fenetre.jPanelDessin.getHeight()
					|| y.intValue() < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees x,y se sont pas dans le dessin");
				return false;
			}
		}

		/*
		 * Si il n'y a qu'un seul parametres
		 */
		else if (parametres.length == 2) {
			try {
				/*
				 * Transforme le parametre[1] en un integer
				 */
				x = Integer.parseInt(parametres[1]);

			} catch (NumberFormatException e) {
				/*
				 * @return 3 Si ce n'est pas un entier
				 */

				Fenetre.jTextAreaHistory.addError("le parametres "
						+ parametres[1] + " n'est pas un entier");
				return false;
			}
		} else {
			Fenetre.jTextAreaHistory
					.addError("La fonction ERASE prend un ou deux arguments");
			return false;
		}
		/*
		 * @return 0 Si aucune erreur
		 */
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}