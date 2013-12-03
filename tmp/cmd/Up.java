package cmd;

import ihm.Dessin;

public class Up extends Cmd {

	String execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return "1";
		dessin.curseur.setDirection(90);
		return "0";

	}
}