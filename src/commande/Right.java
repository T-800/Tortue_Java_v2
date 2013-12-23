package commande;

import dessin.Curseur;
import liste.ListeVariables;

public class Right extends Commande {
	
	private Curseur curseur;
	
	public Right(Curseur curseur) {
		this.curseur = curseur;
	}


	@Override
	public String execute(String[] commande, ListeVariables listeVariables) {
		if(commande.length>1)return "1";
		curseur.setD(0);
		return "";
	}
}