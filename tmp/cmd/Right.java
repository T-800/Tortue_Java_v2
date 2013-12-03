package cmd;

import ihm.Dessin;

public class Right extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return "1";
		dessin.curseur.setDirection(0);
		return "0";

	}
}