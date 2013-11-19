package commande;

import java.io.IOException;

public class Commentaire implements Commande {

	@Override
	public void execute(String[] parametres, boolean addToHistory)
			throws IOException {
		/*
		 * on ajoute le commentaire a l'historique
		 */
		history.addToHistory(ToString(parametres));

	}

	@Override
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		return parametres.length>2;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
