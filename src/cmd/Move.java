package cmd;


import java.awt.Color;
import java.awt.Stroke;
import java.util.Random;

import ihm.Dessin;
import cmd.Variables.ObjetVariables;

public class Move extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length!=2)return "1";
		
		
		int coor[] = new int[2];
		String argsString[] = commande[1].split(" ");
		int argsInt[] = new int[argsString.length];
		
		if (argsString.length >2) return "1";

		switch(argsString.length){
			case 1 :
				if (argsString[0].startsWith("_")) { // variable
					String varNameString = argsString[0].substring(1);

					ObjetVariables var = get_Variable(varNameString);
				if(var == null) return "La variable "+ argsString[0]+" n'éxiste pas.";
					argsInt[0] = var.getValeur_Variable();
				}
				else if(argsString[0].toLowerCase().equals("random")){
						argsInt[0] = randomDistance(dessin);
				}
				else{
					try{
						argsInt[0] = Integer.parseInt(argsString[0]);
					}catch(NumberFormatException e1){
						return "Erreur "+argsString[0]+" n'est pas un nombre";
					}
				
				}
				break;
			case 2 :
				for (int i =0; i<argsString.length ;i++ ) {
					if (argsString[i].startsWith("_")) { // variable
						String varNameString = argsString[i].substring(1);

						ObjetVariables var = get_Variable(varNameString);
						if(var == null) return "La variable "+ argsString[i]+" n'éxiste pas.";
						argsInt[i] = var.getValeur_Variable();
						if(i==0)
							argsInt[i] = argsInt[i]+(dessin.getWidth()/2);
						else 
							argsInt[i] = argsInt[i]+(dessin.getHeight()/2);
					}
					else if(argsString[i].toLowerCase().equals("random")){
						if(i==0)
							argsInt[i] = 0 + (int)(Math.random()*(dessin.getWidth()));
						else 
							argsInt[i] = 0 + (int)(Math.random()*(dessin.getHeight()));
					}
					else{
						try{
							argsInt[i] = Integer.parseInt(argsString[i]);
							if(i==0)
								argsInt[i] = argsInt[i]+(dessin.getWidth()/2);
							else 
								argsInt[i] = argsInt[i]+(dessin.getHeight()/2);
						}catch(NumberFormatException e1){
							return "Erreur "+argsString[i]+" n'est pas un nombre";
						}
						
					}
				}
				break;
		}
		
		

		if (argsInt.length == 1) {
			//abscisse = abscisse + distance * cos(direction)
			coor=calculeCoordArrive(dessin,argsInt[0]);
			
		}else { // length == 2 
			coor[0] = argsInt[0];
			coor[1] = argsInt[1];
		}

		if(!dans_Le_Dessin(dessin, coor)) return "Les coordonnées ne sont pas dans le dessin";

		Ligne line = new Ligne(dessin.curseur.getAbscisse(),dessin.curseur.getOrdonnee(), coor[0],coor[1], dessin.curseur.getCouleur());
		dessin.curseur.setAbscisse(coor[0]);
		dessin.curseur.setOrdonnee(coor[1]);
		dessin.cmd.add(line);

		return "";


	}

	private int [] calculeCoordArrive(Dessin dessin, int distance){
		int coor[] = new int[2];
		coor[0] = dessin.curseur.getAbscisse() + distance * (int)Math.cos(Math.toRadians(dessin.curseur.getDirection()));
		coor[1] = dessin.curseur.getOrdonnee() + distance * (int)Math.sin(Math.toRadians(180 + dessin.curseur.getDirection()));
		return coor;
	}

	// TODO : Réecrire Les fonction Random

	private boolean dans_Le_Dessin(Dessin dessin, int [] coor){
		if(coor[0] > dessin.getWidth() || coor[0] < 0)return false;
		if(coor[1] > dessin.getHeight() || coor[1] < 0)return false;
		return true;
	}

	private int randomDistance(Dessin dessin){
		int max,dist, coor[];
		do{
			max = (int)Math.sqrt((dessin.curseur.getAbscisse()*dessin.curseur.getAbscisse())+(dessin.curseur.getOrdonnee()*dessin.curseur.getOrdonnee()));
			dist =  0 + (int)(Math.random()*max);
			coor = calculeCoordArrive(dessin,dist);
		}while(! dans_Le_Dessin(dessin,coor));
		return dist;
	}



	public class Ligne {
		private int xDepart;
		private int yDepart;
		private int xArrivee;
		private int yArrivee;
		private Color color;

		public Ligne(int xd, int yd, int xa, int ya, Color color) {
			this.xDepart = xd;
			this.yDepart = yd;
			this.xArrivee = xa;
			this.yArrivee = ya;
			this.color = color;
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

	}
}