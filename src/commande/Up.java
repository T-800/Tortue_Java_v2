package commande;

import dessin.Curseur;

public class Up extends Commande {
	
	private Curseur curseur;
	
	public Up(Curseur curseur) {
		this.curseur = curseur;
	}


	@Override
	public String execute(String[] commande) {
		if(commande.length>1)return "1";
		curseur.setD(90);
		return "";
	}
}