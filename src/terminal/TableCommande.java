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

	public static Hashtable<String, Commande> table = new Hashtable<String, Commande>();
	/**
	 * déclaration de la table de hashage
	 */
	public TableCommande(Curseur curseur, ListeCommande listeCommande, ListeFonctions listeFonctions,ListeVariables listeVariables,ListeHistorique listeHistorique) {
		

		table.put("BACK", new Back(curseur));
//		table.put("BACKGROUNDCOLOR", new BackgroundColor());
//		table.put("BGCOLOR", new BackgroundColor());
		table.put("CENTER", new Center(curseur));
		table.put("CLEAR", new Clear(this,listeHistorique,listeCommande,listeFonctions,listeVariables));
		table.put("DOWN", new Down(curseur));
//		table.put("ERASE", new Erase());
		table.put("FONCTION", new Fonction(this,listeHistorique,listeCommande,listeFonctions,listeVariables));// tester avec instruction composée
//		table.put("GO", new Go());
//		table.put("HELP", new Help());
//		table.put("IF", new If());
		table.put("LEFT", new Left(curseur));
		table.put("MOVE", new Move(curseur, listeVariables, listeCommande)); // tester avec les variables et refaire random random
		table.put("OPEN", new Open(this,listeHistorique));
//		table.put("PENCOLOR", new PenColor());
		table.put("PENDOWN", new PenDown(curseur));
		table.put("PENSIZE", new PenSize(curseur,listeVariables));
		table.put("PENUP", new PenUp(curseur));
//		table.put("REDO", new Redo());
//		table.put("REMEMBER", new Remember());
//		table.put("REPEAT", new Repeat());
		table.put("RIGHT", new Right(curseur));
//		table.put("SAVE", new Save());
		table.put("TURN", new Turn(curseur,listeVariables));
//		table.put("UNDO", new Undo());		
		table.put("UP", new Up(curseur));
		table.put("VAR", new Variables(listeVariables));
//		table.put("WHILE", new While());
		
		

	}


	public String executerCommande(String commande){

		/*
		 * On retirre tout les espaces du string
		 * on retire toutes les suites de plus de 1 espace
		 * On met en tableau la commande et chaque arguments
		 */ 
		String[] commandeTab = Convert.commandeToTab(commande);

		
		
		
		
		
		
		String codeErreur = "-1";
		if (commande.charAt(0) == ':'){
			codeErreur = table.get("FONCTION").execute(commandeTab);
		}
		else if(commande.charAt(0) == '_'){
			codeErreur = table.get("VAR").execute(commandeTab);
		}
		else if(commande.startsWith("//")){
			codeErreur = "";
		}else{
			commandeTab[0] = commandeTab[0].toUpperCase();
			if (table.containsKey(commandeTab[0])) {
				/* on verifie si la commande existe
				 * 	- si oui on execute la commande en plui envoyant le tableau
				 * on renvoie le code erreur de la commande
				 */
				codeErreur = table.get(commandeTab[0]).execute(commandeTab);
			}
		}
		
		Fenetre.getPanelDessin().repaint();
		return (ErrorToString(codeErreur,commandeTab[0]));
	}
	
	

	public String ErrorToString(String code, String nomCmd){
		switch(code){
			case "" :
				return "";
			case "-1":
				return "La commande " +nomCmd+ " n'éxiste pas";
			case "1":
				return "Paramètre(s) incorrect(s) <br> voir : \"help "+nomCmd+" \"";
			case "2":
				return "Ce code couleur n'éxiste pas <br> voir : \"http://code-couleur.outils-webmaster.com/ \"";
			case "3":
				return "Impossible de re-déclarer une variable existante <br> voir : \"help "+nomCmd+" \"" ;
			case "4":
				return "Impossible d'éffectuer ce calcule" ;
			default:
				return code;
		}
	}





}
