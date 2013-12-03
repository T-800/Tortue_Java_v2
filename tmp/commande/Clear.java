package commande;

import ihm.Fenetre;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Efface le dessin et l'historique qui �tait en cours Mais laisse la couleur de
 * fond.
 */
public class Clear implements Commande {

	@Override
	/**
	 * (non-Javadoc)
	 * 
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		try {
			HashTable.hgj("CENTER", false);
			Fenetre.jPanelDessin.curseur.new_Curseur(
					Fenetre.jPanelDessin.getWidth() / 2,
					Fenetre.jPanelDessin.getHeight() / 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		history.reset();
		Fenetre.jPanelDessin.cmd = new ArrayList<Object>();

	}

	@Override
	/**
	 * (non-Javadoc)
	 * @see commande.Commande#ToString(java.lang.String[])
	 */
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	@Override
	/**
	 * V�rifie que la commande n'a aucun argument
	 * @return true
	 * 
	 * @see commande.Commande#canDoIt(java.lang.String[])
	 */
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		if (parametres.length > 2) {
			/*
			 * @return false Si la commande a un argument et affiche un message
			 * d'erreur
			 */
			Fenetre.jTextAreaHistory
					.addError("Cette commande ne prend aucun argument");
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