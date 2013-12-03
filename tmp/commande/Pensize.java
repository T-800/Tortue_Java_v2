package commande;

import ihm.Fenetre;

/*
 * Regle la taille du pinceau
 */
public class Pensize implements Commande {

	@Override
	/**
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		// TODO Auto-generated method stub
		/*
		 * @param x Transforme parametre[1] en un entier
		 */
		Integer x = Integer.parseInt(parametres[1]);

		/*
		 * Affecte, la taille donnee, au curseur
		 */
		Fenetre.jPanelDessin.curseur.setPensize(x.intValue());
		Size s = new Size(x.intValue());
		Fenetre.jPanelDessin.cmd.add(s);
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
	 * Verifie l'argument donne
	 * 
	 * @param x
	 * 
	 * @return true
	 * 
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#canDoIt(java.lang.String[])
	 */
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		/*
		 * @param x parametre a laquelle sera affect� parametres[1] c'est a dire
		 * la taille du curseur voulu
		 */
		Integer x;
		try {
			/*
			 * Transforme le paremetres[1] en un entier
			 */
			x = Integer.parseInt(parametres[1]);

		} catch (NumberFormatException e1) {
			/*
			 * @return false Si ce n'est pas un entier
			 */
			return false;

		}

		/*
		 * Verifie que l'entier entre est compris entre 1 et 99
		 * 
		 * @return true
		 */
		if (x.intValue() > 0 && x.intValue() < 100) {
			return true;
		}
		return true;
	}

	public class Size {
		private int strokeSize;

		public Size(int strokeSize) {
			this.strokeSize = strokeSize;
		}

		public int getStrockeSize() {
			return strokeSize;
		}

		public void setyArrivee(int strokeSize) {
			this.strokeSize = strokeSize;
		}

	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
