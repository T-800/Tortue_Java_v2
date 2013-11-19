package cmd;

import ihm.Dessin;

public class PenUp extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return 1;
		dessin.curseur.setPendown(false);
		return 0;

	}
}