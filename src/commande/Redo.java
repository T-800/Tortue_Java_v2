package commande;

import ihm.Fenetre;

public class Redo implements Commande {

	// refait la commande qui a �t� annul�e

	@Override
	public void execute(String[] parametres, boolean addToHistory) {
		history.redo();
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
		if (history.getUndo().size() == 0) {
			Fenetre.jTextAreaHistory.addError("rien n'a ete annulé");
			return false;
		}
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
