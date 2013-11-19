package cmd;

import ihm.Fenetre;
import ihm.Dessin;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Cette classe fait le lien entre l'interpreteur de commande et les differentes
 * fonctions possible
 * 
 * 
 */
public class HashTable {

	public static Hashtable<String, Cmd> hashCommande = new Hashtable<String, Cmd>();
	public Dessin dessin = Fenetre.jPanelDessin;
	/**
	 * déclaration de la table de hashage
	 */
	public HashTable() {

		hashCommande.put("BACK", new Back());
		hashCommande.put("UP", new Up());
		hashCommande.put("CENTER", new Center());
		hashCommande.put("CLEAR", new Clear());
		hashCommande.put("DOWN", new Down());
		hashCommande.put("LEFT", new Left());
		hashCommande.put("RIGHT", new Right());
		hashCommande.put("BACKGROUNDCOLOR", new BackgroundColor());
		hashCommande.put("PENCOLOR", new PenColor());
		hashCommande.put("PENUP", new PenUp());
		hashCommande.put("PENDOWN", new PenDown());
		hashCommande.put("VAR", new Variables());
		hashCommande.put("FONCION", new Fonctions());
		/*
		hashCommande.put("BACKGROUNDCOLOR", new BackgroundColor());
		hashCommande.put("GO", new Go());
		hashCommande.put("ERASE", new Erase());
		hashCommande.put("HELP", new Help());
		hashCommande.put("MOVE", new Move());
		hashCommande.put("NEW", new New());
		hashCommande.put("OPEN", new Open());
		hashCommande.put("PENDOWN", new PenDown());
		hashCommande.put("PENSIZE", new Pensize());
		hashCommande.put("REDO", new Redo());
		hashCommande.put("REMEMBER", new Remember());
		hashCommande.put("REPEAT", new Repeat());
		hashCommande.put("RIGHT", new Right());
		hashCommande.put("SAVE", new Save());
		hashCommande.put("TURN", new Turn());
		hashCommande.put("UNDO", new Undo());
		hashCommande.put("VAR", new Variables());
		hashCommande.put("/*", new Commentaire());
		*/

	}


	public String executerCommande(String commande){
		int codeErreur = -1;

		String [] cmdTab = stringCmdToTab(commande);
		

		/*
		 * Si la commande existe
		 */
		if (cmdTab[0].charAt(0) == ':'){
			codeErreur = hashCommande.get(cmdTab[0]).execute(cmdTab,dessin,this);
		}else if(cmdTab[0].charAt(0) == '_'){
			codeErreur = hashCommande.get("VAR").execute(cmdTab,dessin,this);
		}
		if (hashCommande.containsKey(cmdTab[0])) {
			codeErreur = hashCommande.get(cmdTab[0]).execute(cmdTab,dessin,this);
		}

		dessin.repaint();
		return (ErrorToString(codeErreur,cmdTab[0]));
		/*if (codeErreur == 0) {
			System.out.println(hashCommande.get(cmdTab[0]).toString(cmdTab));
		}else {
			System.out.println(commande +"\n"+ErrorToString(codeErreur));
		}*/
		

		//addToPanel(nomCmd[0],messageErreur,codeErreur);
	}

	private String [] stringCmdToTab(String commande){
		/*
		 * Supprime tout les espace en trop
		 */
		commande = commande.replaceAll("\\s+", " ");

		/*
		 * On transforme la commande en tableau de 2 cases [0] Nom Commande "[1] args"
		 */
		String[] cmdTab = commande.split(" " ,2 );
		/*
		 * On met la premiere case du tableau en majuscule
		 */
		cmdTab[0] = cmdTab[0].toUpperCase();

		return cmdTab;
	}

	private String ErrorToString(int code, String nomCmd){
		switch(code){
			case 0 :
				return "";
			case -1:
				return "Cette Commande n'éxiste pas";
			case 1:
				return "Paramètre(s) incorrect(s) <br> voir : \"help "+nomCmd+" \"";
			case 2:
				return "Ce code couleur n'éxiste pas <br> voir : \"http://code-couleur.outils-webmaster.com/ \"";
			case 3:
				return "Impossible de re-déclarer une variable existante <br> voir : \"help "+nomCmd+" \"" ;
			default:
				return "default";
		}
	}





}
