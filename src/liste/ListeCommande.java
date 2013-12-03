package liste;

import java.awt.*;
import java.util.ArrayList;

public class ListeCommande {
	
	private  ArrayList<Ligne> cmd = new ArrayList<Ligne>();

	public ListeCommande() {
		
	}
	
	public ArrayList<Ligne> getliste() {
		return cmd;
	}
	
	public void addLigne(int xd, int yd, int xa, int ya, Color color, int taille){
		cmd.add(new Ligne(xd, yd, xa, ya, color, taille));
	}
	
	
	public ArrayList<Ligne> getCmd() {
		return cmd;
	}

	public void setCmd(ArrayList<Ligne> cmd) {
		this.cmd = cmd;
	}
	
	public void reset(){
		this.cmd = new ArrayList<Ligne>();
	}














	public class Ligne {
		private int xDepart;
		private int yDepart;
		private int xArrivee;
		private int yArrivee;
		private int taille;
		private Color color;

		public Ligne(int xd, int yd, int xa, int ya, Color color, int taille) {
			this.xDepart = xd;
			this.yDepart = yd;
			this.xArrivee = xa;
			this.yArrivee = ya;
			this.color = color;
			this.taille = taille;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
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

		public int getTaille() {
			return taille;
		}

		public void setTaille(int taille) {
			this.taille = taille;
		}

	}
}
