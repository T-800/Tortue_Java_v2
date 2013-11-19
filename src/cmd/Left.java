package cmd;

import ihm.Dessin;

public class Left extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return 1;
		dessin.curseur.setDirection(180);
		return 0;

	}
}