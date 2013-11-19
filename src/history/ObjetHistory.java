package history;

import ihm.Dessin;

import java.awt.Color;

public class ObjetHistory {

	private String histo;
	private int x;
	private int y;
	private int direction;
	private Color couleur;

	public ObjetHistory(String histo, Dessin dessin) {
		this.histo = histo;
		this.x = dessin.curseur.getAbscisse();
		this.y = dessin.curseur.getOrdonnee();
		this.direction = dessin.curseur.getDirection();
		this.couleur = dessin.curseur.getCouleur();
	}

	public String getHisto() {
		return histo;
	}

	public void setHisto(String histo) {
		this.histo = histo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

}
