package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeVariables;

public class PenUp extends Commande {
	
	private Curseur curseur;
	public PenUp(Curseur curseur) {
		this.curseur = curseur;
	}
	
	@Override
	public String execute(String[] commande, ListeVariables listeVariables){
		if(commande.length>1)return "1";
		curseur.setPenDown(false);
		Fenetre.getPanelInfo().repaint();
		return "";

	}
}