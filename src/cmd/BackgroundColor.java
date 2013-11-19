package cmd;

import ihm.Dessin;

import java.awt.Color;
import java.util.Random;

public class BackgroundColor extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length != 2)return 1;
		commande[1] = commande[1].toLowerCase();
		switch(commande[1]){
			case "noir":
			case "black":
			case "#000000":
				dessin.setBackground(Color.black);
				break;
			case "blanc":
			case "white":
			case "#ffffff":
				dessin.setBackground(Color.white);
				break;
			case "bleu":
			case "blue":
			case "#0000ff":
				dessin.setBackground(Color.blue);
				break;
			case "cyan":
			case "#00ffff":
				dessin.setBackground(Color.cyan);
				break;
			case "jaune":
			case "yellow":
			case "#ffff00":
				dessin.setBackground(Color.yellow);
				break;
			case "rouge":
			case "red":
			case "#ff0000":
				dessin.setBackground(Color.red);
				break;
			case "rose":
			case "pink":
			case "#fd6c9e":
				dessin.setBackground(Color.pink);
				break;
			case "orange":
			case "#ed7f10":
				dessin.setBackground(Color.orange);
				break;
			case "magenta":
			case "#ff00ff":
				dessin.setBackground(Color.magenta);
				break;
			case "vert":
			case "green":
			case "#00ff00":
				dessin.setBackground(Color.green);
				break;

			case "lightgray":
			case "gris clair":
			case "#afafaf":
				dessin.setBackground(Color.lightGray);
				break;

			case "gray":
			case "gris":
			case "#606060":
				dessin.setBackground(Color.gray);
				break;
			case "random":
				dessin.setBackground(randomColor());
				break;

			default :
				try {
					Integer c = Integer.parseInt(commande[1].replaceFirst("#", ""), 16);
					Color f = new Color(c.intValue());
					dessin.setBackground(f);
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