package objetQuiDessine;

import ihm.jPanelParametres;

import java.awt.Color;
import java.awt.Image;

public class CurseurQuiDessine {

	private int direction, abscisse, ordonnee;
	private int rememberAbscisse, rememberOrdonnee, rememberDirection;
	private Color couleur;
	private boolean pendown;
	private int pensize;
	private Color background;
	private boolean drawCurs = true;

	private jPanelParametres info;
	private Image curseurImg;

	public CurseurQuiDessine(jPanelParametres info, int x, int y) {
		this.info = info;
		this.abscisse = x+100;
		this.ordonnee = y+100;
		this.pensize = 1;
		this.couleur = Color.BLACK;
		this.direction = 0;
		this.pendown = true;

	}

	public void new_Curseur(int x, int y) {
		this.abscisse = x;
		this.ordonnee = y;
		this.pensize = 1;
		this.couleur = Color.BLACK;
		this.direction = 0;
		this.pendown = true;
	}
	public void restorXYD(){
		this.abscisse = this.rememberAbscisse;
		this.ordonnee = this.rememberOrdonnee;
		this.direction = this.rememberDirection;
	}

	/*
	 * GET et SET
	 */

	public void setXY(int x, int y){
		this.abscisse=x;
		this.ordonnee=y;
	}


	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonnee() {
		return ordonnee;
	}

	public void setOrdonnee(int ordonnee) {
		this.ordonnee = ordonnee;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public boolean getPendown() {
		return pendown;
	}

	public void setPendown(boolean pendown) {
		this.pendown = pendown;
	}

	public int getPensize() {
		return pensize;
	}

	public void setPensize(int pensize) {
		this.pensize = pensize;
	}

	public int getRememberAbscisse() {
		return rememberAbscisse;
	}

	public void setRememberAbscisse(int rememberAbscisse) {
		this.rememberAbscisse = rememberAbscisse;
	}

	public int getRememberOrdonnee() {
		return rememberOrdonnee;
	}

	public void setRememberOrdonnee(int rememberOrdonnee) {
		this.rememberOrdonnee = rememberOrdonnee;
	}

	public int getRememberDirection() {
		return rememberDirection;
	}

	public void setRememberDirection(int rememberDirection) {
		this.rememberDirection = rememberDirection;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public jPanelParametres getInfo() {
		return info;
	}

	public void setInfo(jPanelParametres info) {
		this.info = info;
	}

	public Image getCurseurImg() {
		return curseurImg;
	}

	public void setCurseurImg(Image curseurImg) {
		this.curseurImg = curseurImg;
	}

	public boolean isDrawCurs() {
		return drawCurs;
	}

	public void setDrawCurs(boolean drawCurs) {
		this.drawCurs = drawCurs;
	}

}