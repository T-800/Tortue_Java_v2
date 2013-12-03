package commande;

import ihm.Fenetre;

import java.awt.Color;
import java.awt.Stroke;
import java.util.Random;

public class Move implements Commande {
	static int lastMove = 0;
	static int cDImove = 0;

	public static void resetMove() {
		lastMove = 0;
		cDImove = 0;
	}

	@Override
	public void execute(String[] parametres, boolean addToHistory) {

		if (addToHistory)
			history.addToHistory(ToString(parametres));

		Integer x = 0;
		Integer y = 0;

		// si move 10 10
		if (parametres.length == 3) {
			if (HashTable.isVariable(parametres[1])) {
				x = variables.get(HashTable.variableExiste(parametres[1]))
						.getValeur_Variable();
			} else if (parametres[1].equalsIgnoreCase("random")) {
				x = randomX();
			} else {
				x = Integer.parseInt(parametres[1]);
			}

			if (HashTable.isVariable(parametres[2])) {
				y = variables.get(HashTable.variableExiste(parametres[2]))
						.getValeur_Variable();
			} else if (parametres[2].equalsIgnoreCase("random")) {
				y = randomY();
			} else {
				y = Integer.parseInt(parametres[2]);
			}

			x = Fenetre.jPanelDessin.getWidth() / 2 + x;

			y = Fenetre.jPanelDessin.getHeight() / 2 - y;

		}
		// move 10
		else {
			Integer dist;
			try {

				if (HashTable.isVariable(parametres[1])) {
					dist = variables.get(
							HashTable.variableExiste(parametres[1]))
							.getValeur_Variable();
				} else {
					dist = Integer.parseInt(parametres[1]);
				}
			} catch (NumberFormatException e1) {
				if (HashTable.isVariable(parametres[1].substring(1))) {
					dist = variables
							.get(HashTable.variableExiste(parametres[1]
									.substring(1))).getValeur_Variable();
					if (parametres[1].charAt(0) == '-')
						dist = -dist;
				} else {
					dist = Integer.parseInt(parametres[1].substring(1));
					if (parametres[1].charAt(0) == '-')
						dist = -dist;
				}
			}
			switch (parametres[1].charAt(0)) {
			case '+':
				dist = lastMove + dist;
				break;
			case '-':
				dist = lastMove + dist;
				break;
			}

			lastMove = dist;
			cDImove = dist;

			x = (int) Math.round(Fenetre.jPanelDessin.curseur.getAbscisse()+ dist
					* Math.cos(Math.toRadians(Fenetre.jPanelDessin.curseur.getDirection())));

			y = (int) Math.round(Fenetre.jPanelDessin.curseur.getOrdonnee()+ dist
					* Math.sin(Math.toRadians(180 + Fenetre.jPanelDessin.curseur.getDirection())));

		}

		Ligne l = new Ligne(Fenetre.jPanelDessin.curseur.getAbscisse(),
				Fenetre.jPanelDessin.curseur.getOrdonnee(), x.intValue(),
				y.intValue(), Fenetre.jPanelDessin.curseur.getCouleur(), null);
		Fenetre.jPanelDessin.curseur.setAbscisse(x.intValue());
		Fenetre.jPanelDessin.curseur.setOrdonnee(y.intValue());
		

		Fenetre.jPanelDessin.cmd.add(l);
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		Integer x;
		Integer y;
		if (!Fenetre.jPanelDessin.curseur.getPendown()) {
			Fenetre.jTextAreaHistory
					.addError("Votre curseur n'est pas pose (Vous ne dessinez pas)");
			return false;
		}
		if (parametres.length == 3) {
			try {
				if (HashTable.isVariable(parametres[1])) {
					x = variables.get(HashTable.variableExiste(parametres[1]))
							.getValeur_Variable();
				} else if (parametres[1].equalsIgnoreCase("random")) {
					x = randomX();
				} else {
					x = Integer.parseInt(parametres[1]);
				}

				if (HashTable.isVariable(parametres[2])) {
					y = variables.get(HashTable.variableExiste(parametres[2]))
							.getValeur_Variable();
				} else if (parametres[2].equalsIgnoreCase("random")) {
					y = randomY();
				} else {
					y = Integer.parseInt(parametres[2]);
				}

			} catch (NumberFormatException e1) {
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
					|| x.intValue() < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees x,y se sont pas dans le dessin");
				return false;
			}
			if (y.intValue() > Fenetre.jPanelDessin.getHeight()
					|| y.intValue() < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees x,y se sont pas dans le dessin");
				return false;
			}
			return true;
		} else if (parametres.length == 2) {
			Integer dist;
			try {
				if (HashTable.isVariable(parametres[1])) {
					dist = variables.get(
							HashTable.variableExiste(parametres[1]))
							.getValeur_Variable();
				} else {
					dist = Integer.parseInt(parametres[1]);
				}
			} catch (NumberFormatException e1) {
				try {
					// dist = Integer.parseInt(parametres[1].substring(1));

					if (HashTable.isVariable(parametres[1].substring(1))) {
						dist = variables.get(
								HashTable.variableExiste(parametres[1]
										.substring(1))).getValeur_Variable();
						if (parametres[1].charAt(0) == '-')
							dist = -dist;
					} else {
						dist = Integer.parseInt(parametres[1].substring(1));
						if (parametres[1].charAt(0) == '-')
							dist = -dist;
					}
				} catch (NumberFormatException e) {
					Fenetre.jTextAreaHistory.addError("le parametres "
							+ parametres[1] + " n'est pas un entier");
					return false;
				}

			}
			switch (parametres[1].charAt(0)) {
			case '+':
				dist = cDImove + dist;
				break;
			case '-':

				dist = cDImove + dist;
				break;
			}
			if (dist < 0) {
				Fenetre.jTextAreaHistory
						.addError("La fonction ne peut pas prendre de valeur negative");
				return false;
			}
			double nX = Fenetre.jPanelDessin.curseur.getAbscisse()
					+ dist
					* Math.cos(Math.toRadians(Fenetre.jPanelDessin.curseur
							.getDirection()));
			double nY = Fenetre.jPanelDessin.curseur.getOrdonnee()
					+ dist
					* Math.sin(Math
							.toRadians(180 + Fenetre.jPanelDessin.curseur
									.getDirection()));
			if (nX > Fenetre.jPanelDessin.getWidth() || nX < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees x se sont pas dans le dessin");
				return false;
			}
			if (nY > Fenetre.jPanelDessin.getHeight() || nY < 0) {
				Fenetre.jTextAreaHistory
						.addError("Les coordonnees y se sont pas dans le dessin");
				return false;
			}
			cDImove = dist;

			return true;

		} else {
			Fenetre.jTextAreaHistory
					.addError("La fonction MOVE prend un ou deux arguments");
			return false;
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

	public class Ligne {
		private int xDepart;
		private int yDepart;
		private int xArrivee;
		private int yArrivee;
		private Stroke strok;
		private Color color;

		public Ligne(int xd, int yd, int xa, int ya, Color color, Stroke strok) {
			this.xDepart = xd;
			this.yDepart = yd;
			this.xArrivee = xa;
			this.yArrivee = ya;
			this.color = color;
			this.strok = strok;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public Stroke getStrok() {
			return strok;
		}

		public void setStrok(Stroke strok) {
			this.strok = strok;
		}

		public int getxDepart() {
			return xDepart;
		}

		public void setxDepart(int xDepart) {
			this.xDepart = xDepart;
		}

		public int getyDepart() {
			return yDepart;
		}

		public void setyDepart(int yDepart) {
			this.yDepart = yDepart;
		}

		public int getxArrivee() {
			return xArrivee;
		}

		public void setxArrivee(int xArrivee) {
			this.xArrivee = xArrivee;
		}

		public int getyArrivee() {
			return yArrivee;
		}

		public void setyArrivee(int yArrivee) {
			this.yArrivee = yArrivee;
		}

	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int randomX() {
		Random nombre = new Random();
		int valeur = nombre.nextInt(Fenetre.jPanelDessin.getWidth() / 2);
		int signe = nombre.nextInt(4);
		if (signe > 2) {
			valeur = -valeur;
		}

		return valeur;
	}

	public int randomY() {
		Random nombre = new Random();
		int valeur = nombre.nextInt(Fenetre.jPanelDessin.getHeight() / 2);
		int signe = nombre.nextInt(4);
		if (signe > 2) {
			valeur = -valeur;
		}

		return valeur;
	}

}
