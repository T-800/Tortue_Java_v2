package commande;

import dessin.Curseur;
import liste.ListeVariables;

public class Back extends Commande {

	Curseur curseur;
	
	public Back(Curseur curseur) {
		this.curseur = curseur;
	}
	
	@Override
	public String execute(String[] commande, ListeVariables listeVariables){
		if(commande.length>1)return "1";

		curseur.getBAckRemember();
		

		return "";
	}
}