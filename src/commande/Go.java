package commande;

import java.util.Random;

import ihm.Fenetre;

/**
 * Cette classe déplace le curseur sans déssiner
 * 
 * 
 */
public class Go implements Commande {

	/**
	 * 
	 */

	@Override
	/**
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		// TODO Auto-generated method stub
		/*
		 * si cette commande doit être ajouter a l'historique on l'ajoute
		 */
		if (addToHistory)
			history.addToHistory(ToString(parametres));

		/*
		 * 
		 */
		Integer x = 0;
		Integer y = 0;

		if (HashTable.isVariable(parametres[1])) {
			/*
			 * si le parametre est une variable on la remplace par la valeur de
			 * la variable
			 */
			x = variables.get(HashTable.variableExiste(parametres[1]))
					.getValeur_Variable();
		} else if (parametres[1].equalsIgnoreCase("random")) {
			/*
			 * si le parametre est random
			 */
			x = randomX();
		} else {
			/*
			 * sinon on lui donne la valeur du parametre
			 */
			x = Integer.parseInt(parametres[1]);
		}

		if (HashTable.isVariable(parametres[2])) {
			/*
			 * si le parametre est une variable on la remplace par la valeur de
			 * la variable
			 */
			y = variables.get(HashTable.variableExiste(parametres[2]))
					.getValeur_Variable();
		} else if (parametres[2].equalsIgnoreCase("random")) {
			/*
			 * si le parametre est random
			 */
			y = randomY();
		} else {
			/*
			 * sinon on lui donne la valeur du parametre
			 */
			y = Integer.parseInt(parametres[2]);
		}

		/*
		 * on donne a x et y leur vrai valeur avec le point 0;0 en haut a gauche
		 */
		x = Fenetre.jPanelDessin.getWidth() / 2 + x;

		y = Fenetre.jPanelDessin.getHeight() / 2 - y;

		/*
		 * on donne au curseur ses nouvelle coordonnées
		 */
		Fenetre.jPanelDessin.curseur.setAbscisse(x.intValue());
		Fenetre.jPanelDessin.curseur.setOrdonnee(y.intValue());

	}

	@Override
	/**
	 * (non-Javadoc)
	 * @see commande.Commande#ToString(java.lang.String[])
	 */
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	@Override
	/**
	 * on verifie que les parametres de go sont bon et qu'il ne provoque pa d'erreurS
	 * @return true
	 * 
	 * @see commande.Commande#canDoIt(java.lang.String[])
	 */
	public boolean canDoIt(String[] parametres) {
		Integer x;
		Integer y;

		if (parametres.length == 3) {
			try {
				if (HashTable.isVariable(parametres[1])) {
					/*
					 * si le parametre est une variable on la remplace par la
					 * valeur de la variable
					 */
					x = variables.get(HashTable.variableExiste(parametres[1]))
							.getValeur_Variable();
				} else {
					/*
					 * sinon on lui donne la valeur du parametre
					 */
					x = Integer.parseInt(parametres[1]);
				}

				if (HashTable.isVariable(parametres[2])) {
					/*
					 * si le parametre est une variable on la remplace par la
					 * valeur de la variable
					 */
					y = variables.get(HashTable.variableExiste(parametres[2]))
							.getValeur_Variable();
				} else {
					/*
					 * sinon on lui donne la valeur du parametre
					 */
					y = Integer.parseInt(parametres[2]);
				}

			} catch (NumberFormatException e1) {
				return false;

			}

			x = Fenetre.jPanelDessin.getWidth() / 2 + x;

			y = Fenetre.jPanelDessin.getHeight() / 2 - y;

			/*
			 * on regarde si x est plus grand que la largeur du dessin
			 */
			if (x.intValue() > Fenetre.jPanelDessin.getWidth()
					|| x.intValue() < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees x,y se sont pas dans le dessin");
				return false;
			}

			/*
			 * on regarde si y est plus grand que la hauteur du dessin
			 */
			if (y.intValue() > Fenetre.jPanelDessin.getHeight()
					|| y.intValue() < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees x,y se sont pas dans le dessin");
				return false;
			}
			return true;

		} else {
			Fenetre.jTextAreaHistory
					.addError("La fonction GO prend deux arguments");
			return false;
		}
	}

	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int randomX() {
		Random nombre = new Random();
		int valeur = nombre.nextInt(Fenetre.jPanelDessin.getWidth() / 2);
		int signe = nombre.nextInt(4);
		if (signe > 2) {
			valeur = -valeur;
		}

		return valeur;
	}

	private int randomY() {
		Random nombre = new Random();
		int valeur = nombre.nextInt(Fenetre.jPanelDessin.getHeight() / 2);
		int signe = nombre.nextInt(4);
		if (signe > 2) {
			valeur = -valeur;
		}

		return valeur;
	}
}
