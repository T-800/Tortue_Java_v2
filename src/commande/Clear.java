package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

public class Clear extends Commande {

	TableCommande tableCommande;
	ListeHistorique listeHistorique;
	ListeCommande listeCommande;
	ListeFonctions listeFonctions;
	ListeVariables listeVariables;
    Curseur curseur;

	
	public Clear(Curseur curseur,TableCommande commande,ListeHistorique listeHistorique, ListeCommande listeCommande,ListeFonctions listeFonctions, ListeVariables listeVariables) {
		
		this.tableCommande = commande;
		this.listeCommande = listeCommande;
		this.listeHistorique = listeHistorique;
		this.listeFonctions = listeFonctions;
		this.listeVariables = listeVariables;
        this.curseur = curseur;
		
	}
	
	@Override
	public String execute(String[] commande,ListeVariables listeVariables){
		if(commande.length>1)return "1";

		tableCommande.executerCommande("Center",null);

		listeCommande.reset();
		listeHistorique.reset();
		listeVariables.reset();
        curseur.reset(Fenetre.getCenterDessin()[0],Fenetre.getCenterDessin()[1]);

		return "";
	}
}