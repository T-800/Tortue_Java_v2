package commande;

import ihm.Fenetre;

/**
 * Sauvegarde la position actuelle du curseur et pourra �tre r�utiliser ult�rieurement par BACK
 */
public class Remember implements Commande {

	@Override
	/**
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		// TODO Auto-generated method stub
		Fenetre.jPanelDessin.curseur
				.setRememberAbscisse(Fenetre.jPanelDessin.curseur.getAbscisse());
		Fenetre.jPanelDessin.curseur
				.setRememberOrdonnee(Fenetre.jPanelDessin.curseur.getOrdonnee());
		Fenetre.jPanelDessin.curseur
				.setRememberDirection(Fenetre.jPanelDessin.curseur
						.getDirection());
		if (addToHistory)
			history.addToHistory(ToString(parametres));
	}

	@Override
	/**
	 * (non-Javadoc)
	 * 
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
	 * V�rifie que la commande n'a aucun argument
	 * 
	 * @return true
	 * 
	 * @see commande.Commande#canDoIt(java.lang.String[])
	 */
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		/*
		 * Si la commande prend au moins un argument
		 * 
		 * @return false Envoi un message d'erreur
		 */
		if (parametres.length > 2) {
			Fenetre.jTextAreaHistory
					.addError("Cette commande ne prend aucun parametre");
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
