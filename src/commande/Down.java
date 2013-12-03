package commande;

import dessin.Curseur;

public class Down extends Commande {
	
	private Curseur curseur;
	
	public Down(Curseur curseur) {
		this.curseur = curseur;
	}


	@Override
	public String execute(String[] commande) {
		if(commande.length>1)return "1";
		curseur.setD(270);
		return "";
	}
}