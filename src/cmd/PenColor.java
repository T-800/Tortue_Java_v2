package cmd;

import ihm.Dessin;

import java.awt.Color;
import java.util.Random;

public class PenColor extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length != 2)return 1;
		commande[1] = commande[1].toLowerCase();
		switch(commande[1]){
			case "noir":
			case "black":
			case "#000000":
				dessin.curseur.setCouleur(Color.black);
				break;
			case "blanc":
			case "white":
			case "#ffffff":
				dessin.curseur.setCouleur(Color.white);
				break;
			case "bleu":
			case "blue":
			case "#0000ff":
				dessin.curseur.setCouleur(Color.blue);
				break;
			case "cyan":
			case "#00ffff":
				dessin.curseur.setCouleur(Color.cyan);
				break;
			case "jaune":
			case "yellow":
			case "#ffff00":
				dessin.curseur.setCouleur(Color.yellow);
				break;
			case "rouge":
			case "red":
			case "#ff0000":
				dessin.curseur.setCouleur(Color.red);
				break;
			case "rose":
			case "pink":
			case "#fd6c9e":
				dessin.curseur.setCouleur(Color.pink);
				break;
			case "orange":
			case "#ed7f10":
				dessin.curseur.setCouleur(Color.orange);
				break;
			case "magenta":
			case "#ff00ff":
				dessin.curseur.setCouleur(Color.magenta);
				break;
			case "vert":
			case "green":
			case "#00ff00":
				dessin.curseur.setCouleur(Color.green);
				break;

			case "lightgray":
			case "gris clair":
			case "#afafaf":
				dessin.curseur.setCouleur(Color.lightGray);
				break;

			case "gray":
			case "gris":
			case "#606060":
				dessin.curseur.setCouleur(Color.gray);
				break;
			case "random":
				dessin.curseur.setCouleur(randomColor());
				break;

			default :
				try {
					Integer c = Integer.parseInt(commande[1].replaceFirst("#", ""), 16);
					Color f = new Color(c.intValue());
					dessin.curseur.setCouleur(f);
				} catch (NumberFormatException e1) {
					return 2;
				}
		}
		return 0;

	}

	public Color randomColor() {
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(255);
		int green = randomGenerator.nextInt(255);
		int blue = randomGenerator.nextInt(255);

		Color randomColour = new Color(red, green, blue);
		
		return randomColour;
	}
}