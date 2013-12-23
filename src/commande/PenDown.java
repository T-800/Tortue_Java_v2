package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeVariables;

public class PenDown extends Commande {
	
	private Curseur curseur;
	public PenDown(Curseur curseur) {
		this.curseur = curseur;
	}
	
	@Override
	public String execute(String[] commande, ListeVariables listeVariables){
		if(commande.length>1)return "1";
		curseur.setPenDown(true);
		Fenetre.getPanelInfo().repaint();
		return "";

	}
}