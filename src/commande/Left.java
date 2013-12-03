package commande;

import dessin.Curseur;

public class Left extends Commande {
	
	private Curseur curseur;
	
	public Left(Curseur curseur) {
		this.curseur = curseur;
	}


	@Override
	public String execute(String[] commande) {
		if(commande.length>1)return "1";
		curseur.setD(180);
		return "";
	}
}
