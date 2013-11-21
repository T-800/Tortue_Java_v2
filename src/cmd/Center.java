package cmd;

import ihm.Dessin;

public class Center extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return "1";
		dessin.curseur.setXY(
			dessin.getWidth() / 2, 
			dessin.getHeight() / 2);
		dessin.curseur.setDirection(0);

		return "0";
	}
}