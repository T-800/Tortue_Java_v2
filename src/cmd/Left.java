package cmd;

import ihm.Dessin;

public class Left extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return "1";
		dessin.curseur.setDirection(180);
		return "0";

	}
}