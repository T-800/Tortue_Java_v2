package cmd;

import ihm.Dessin;
import java.util.ArrayList;

public class Clear extends Cmd {

	int execute(String[] commande, Dessin dessin, HashTable table){
		if(commande.length>1)return 1;
		String s =table.executerCommande("CENTER");
		history.reset();
		dessin.cmd = new ArrayList<Object>();

		return 0;
	}
}