package terminal;

import algo.Convert;
import commande.*;
import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;

import java.util.Hashtable;


public class TableCommande {

	public static Hashtable<String, Commande> table = new Hashtable<>();
	/**
	 * déclaration de la table de hashage
	 */
	public TableCommande(Curseur curseur, ListeCommande listeCommande, ListeFonctions listeFonctions,ListeHistorique listeHistorique) {
        Commande.setCurseur(curseur);
        Commande.setListeCommande(listeCommande);
        Commande.setListeFonctions(listeFonctions);
        Commande.setListeHistorique(listeHistorique);
        Commande.setTableCommande(this);


		table.put("BACK", new Back());
//		table.put("BACKGROUNDCOLOR", new BackgroundColor(curseur,listeVariables));
//		table.put("BGCOLOR", new BackgroundColor(curseur,listeVariables));
		table.put("CENTER", new Center());
		table.put("CLEAR", new Clear());
		table.put("DOWN", new Down());
//		table.put("ERASE", new Erase());
		table.put("FONCTION", new Fonctions());// tester avec instruction composée
//		table.put("GO", new Go(curseur,listeVariables));
//		table.put("HELP", new Help());
//		table.put("IF", new If(curseur,listeVariables,this));
		table.put("LEFT", new Left());
		table.put("MOVE", new Move()); // tester avec les variables et refaire random random
//      table.put("NEW", new New(this,listeCommande,curseur,listeHistorique,listeVariables,listeFonctions));
//		table.put("OPEN", new Open(this,listeHistorique));
//		table.put("PENCOLOR", new PenColor(curseur,listeVariables));
//		table.put("PENDOWN", new PenDown(curseur));
//		table.put("PENSIZE", new PenSize(curseur,listeVariables));
//		table.put("PENUP", new PenUp(curseur));
//		table.put("REDO", new Redo());
		table.put("REMEMBER", new Remember());
//		table.put("REPEAT", new Repeat(this,listeFonctions,listeVariables));
		table.put("RIGHT", new Right());
//		table.put("SAVE", new Save(listeHistorique,curseur));
//		table.put("TURN", new Turn(curseur,listeVariables));
//		table.put("UNDO", new Undo());
		table.put("UP", new Up());
		table.put("VAR", new Variables());
//		table.put("WHILE", new While());
		
		

	}


	public boolean executerCommande(String commande, ListeVariables listeVariables){

		/*
		 * On retirre tout les espaces du string
		 * on retire toutes les suites de plus de 1 espace
		 * On met en tableau la commande et chaque arguments
		 */
        commande = commande.replaceAll("\\s+", " ");
		String[] commandeTab = commande.split(" ", 2);

        boolean ok = true;
		if (commande.charAt(0) == ':'){
			ok = table.get("FONCTION").execute(commande,listeVariables);
		}
		else if(commande.charAt(0) == '_'){
            ok = table.get("VAR").execute(commande,listeVariables);
		}
		else{
            commandeTab[0] = commandeTab[0].toUpperCase();
			if (table.containsKey(commandeTab[0])) {
				/* on verifie si la commande existe
				 * 	- si oui on execute la commande en plui envoyant le tableau
				 * on renvoie le code erreur de la commande
				 */
                ok = table.get(commandeTab[0]).execute(commande,listeVariables);
			}
		}
		
		Fenetre.getPanelDessin().repaint();
        return ok;
	}
	
	







}
