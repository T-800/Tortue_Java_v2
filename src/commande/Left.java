package commande;

import dessin.Curseur;
import liste.ListeVariables;

public class Left extends Commande {
	
	private Curseur curseur;
	
	public Left(Curseur curseur) {
		this.curseur = curseur;
	}


	@Override
	public String execute(String[] commande, ListeVariables listeVariables) {
		if(commande.length>1)return "1";
		curseur.setD(180);
		return "";
	}
}
