package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;

public class PenDown extends Commande {
	
	private Curseur curseur;
	public PenDown(Curseur curseur) {
		this.curseur = curseur;
	}
	
	@Override
	public String execute(String[] commande){
		if(commande.length>1)return "1";
		curseur.setPenDown(true);
		Fenetre.getPanelInfo().repaint();
		return "";

	}
}