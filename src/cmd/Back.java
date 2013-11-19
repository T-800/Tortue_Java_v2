package cmd;

import ihm.Dessin;

public class Back extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return 1;

		dessin.curseur.restorXYD();


		return 0;
	}
}