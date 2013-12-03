package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;

public class Center extends Commande {
	Curseur curseur;
	
	public Center(Curseur curseur) {
		this.curseur = curseur;
	}
	
	@Override
	public String execute(String[] commande){
		if(commande.length>1)return "1";
		
		curseur.setPos(Fenetre.getCenterDessin());
		curseur.setD(0);

		return "";
	}
}