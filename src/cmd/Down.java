package cmd;

import ihm.Dessin;
import java.util.ArrayList;

public class Down extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return 1;

		dessin.curseur.setDirection(270);


		return 0;
	}
}