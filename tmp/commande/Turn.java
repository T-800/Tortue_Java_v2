package commande;

import java.util.Random;

import ihm.Fenetre;

/**
 * Tourne le curseur d'un angle donn�
 */
public class Turn implements Commande {

	@Override
	/**
	 * (non-Javadoc)
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		Integer x;
		char signe = parametres[1].charAt(0);
		if (signe == '+' || signe == '-') {
			String s = parametres[1].substring(1);
			if (s.equalsIgnoreCase("random")) {
				x = random();
			} else {
				x = Integer.parseInt(s);
			}

			switch (signe) {
			case '+':

				x = (Fenetre.jPanelDessin.curseur.getDirection() + x.intValue()) % 360;
				Fenetre.jPanelDessin.curseur.setDirection(x.intValue());
				break;

			case '-':

				x = (Fenetre.jPanelDessin.curseur.getDirection() - x.intValue()) % 360;
				Fenetre.jPanelDessin.curseur.setDirection(x.intValue());
				break;

			}
		} else {
			if (parametres[1].equalsIgnoreCase("random")) {
				x = random();
			}else if(HashTable.isVariable(parametres[1])){
				x =variables.get(HashTable.variableExiste(parametres[1]))
						.getValeur_Variable();
			} else {
				x = Integer.parseInt(parametres[1]);
			}

			x = x % 360;
			Fenetre.jPanelDessin.curseur.setDirection(x.intValue());

		}

		if (addToHistory)
			history.addToHistory(ToString(parametres));

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
	 * (non-Javadoc)
	 * @see commande.Commande#canDoIt(java.lang.String[])
	 */
	public boolean canDoIt(String[] parametres) {

		if (parametres.length != 2) {
			Fenetre.jTextAreaHistory
					.addError("La fonction TURN prend un argument");
			return false;
		}
		char signe = parametres[1].charAt(0);
		if (signe == '+' || signe == '-') {
			String s = parametres[1].substring(1);

			try {
				if (s.equalsIgnoreCase("random")) {

				} else {
					Integer.parseInt(s);
				}

			} catch (NumberFormatException e1) {
				Fenetre.jTextAreaHistory
						.addError("Le parametre de TURN n'est pas correct");
				return false;
			}
		} else {
			try {
				if (parametres[1].equalsIgnoreCase("random")) {

				} else {
					Integer.parseInt(parametres[1]);
				}

			} catch (NumberFormatException e1) {
				Fenetre.jTextAreaHistory
						.addError("Le parametre de TURN n'est pas correct");
				return false;
			}
		}
		return true;
	}

	@Override
	public int random() {
		Random nombre = new Random();
		int valeur = nombre.nextInt(360);

		return valeur;
	}

}
