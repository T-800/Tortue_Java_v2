package dessin;


import java.awt.*;

public class Curseur {
	
	
	private int[] pos;
	private int[] souris;
	private Color couleurCurseur;
	private Color couleurBg;
	private int penSize;

    private boolean drawCurs;
	
	public Curseur(int x, int y) {
		this.pos = new int[3];
		this.pos[0] = x;
		this.pos[1] = y;
		this.pos[2] = 0;
		this.souris=new int[2];
		this.couleurCurseur = Color.BLACK;
		this.couleurBg = Color.WHITE;
        this.penSize = 1;
        this.drawCurs = true;

	}

    public void setCurseur(Curseur curseur) {
        this.pos = new int[3];
        this.pos[0] = curseur.getPos()[0];
        this.pos[1] = curseur.getPos()[1];
        this.pos[2] = curseur.getPos()[2];
        this.souris=new int[2];
        this.couleurCurseur = curseur.getCouleurCurseur();
        this.couleurBg = curseur.getCouleurBg();
        this.penSize = curseur.getPenSize();
        this.drawCurs = curseur.DrawCurs();
    }

    public void reset(int x, int y){
        this.pos = new int[3];
        this.pos[0] = x;
        this.pos[1] = y;
        this.pos[2] = 0;
        this.souris=new int[2];
        this.couleurCurseur = Color.BLACK;
        this.couleurBg = Color.WHITE;
        this.penSize = 1;
        this.drawCurs = true;
    }
	
	public Color getCouleurBg(){
		return this.couleurBg;
	}
	public int getX(){
		return this.pos[0];
	}
	public int getY(){
		return this.pos[1];
	}
	
	public int getD(){
		return this.pos[2];
	}
	
	public void setX(int x){
		this.pos[0] = x;
	}
	public void setY(int y){
		this.pos[1] = y;
	}
	public void setD(int d){
		this.pos[2] = d;
	}

	public int[] getPos() {
		return pos;
	}

	public void setPos(int[] pos) {
		this.pos[0] = pos[0];
		this.pos[1] = pos[1];
	}

	public Color getCouleurCurseur() {
		return couleurCurseur;
	}

	public void setCouleurCurseur(Color couleurCurseur) {
		this.couleurCurseur = couleurCurseur;
	}



	public int getPenSize() {
		return penSize;
	}

	public void setPenSize(int penSize) {
		this.penSize = penSize;
	}

	public void setCouleurBg(Color couleurBg) {
		this.couleurBg = couleurBg;
	}

	public int getSourisX(){
		return this.souris[0];
	}
	public int getSourisY(){
		return this.souris[1];
	}
	
	public void setSourisX(int x){
		this.souris[0] = x;
	}
	public void setSourisY(int y){
		this.souris[1] = y;
	}

    public boolean DrawCurs() {
        return drawCurs;
    }

    public void setDrawCurs(boolean drawCurs) {
        this.drawCurs = drawCurs;
    }




}
