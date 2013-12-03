package commande;

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
	
	public Clear(TableCommande commande,ListeHistorique listeHistorique, ListeCommande listeCommande,ListeFonctions listeFonctions, ListeVariables listeVariables) {
		
		this.tableCommande = commande;
		this.listeCommande = listeCommande;
		this.listeHistorique = listeHistorique;
		this.listeFonctions = listeFonctions;
		this.listeVariables = listeVariables;
		
	}
	
	@Override
	public String execute(String[] commande){
		if(commande.length>1)return "1";

		tableCommande.executerCommande("Center");
		listeCommande.reset();
		listeHistorique.reset();
		listeVariables.reset();

		return "";
	}
}