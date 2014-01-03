package terminal;

import commande.*;
import dessin.Curseur;
import interfaceGraphique.PanelDessin;
import liste.ListeFonctions;
import liste.ListeHistorique;

import java.util.Hashtable;


public class TableCommande {

    private static ListeHistorique listeHistorique;
	public static Hashtable<String, Commande> table = new Hashtable<>();
	/**
	 * déclaration de la table de hashage
	 */
	public TableCommande(ListeFonctions listeFonctions,ListeHistorique listeHistorique) {
        Commande.setListeFonctions(listeFonctions);
        TableCommande.listeHistorique = listeHistorique;


//		table.put("BACK", new Back());
//		table.put("BACKGROUNDCOLOR", new BackgroundColor(curseur,listeVariables));
//		table.put("BGCOLOR", new BackgroundColor(curseur,listeVariables));
		table.put("CENTER", new Center());
//		table.put("CLEAR", new Clear());
		table.put("DOWN", new Down());
//		table.put("ERASE", new Erase());
//		table.put("FONCTION", new Fonctions());// tester avec instruction composée
//		table.put("GO", new Go(curseur,listeVariables));
//		table.put("HELP", new Help());
//		table.put("IF", new If());
		table.put("LEFT", new Left());
		table.put("MOVE", new Move()); // tester avec les variables et refaire random random
//      table.put("NEW", new New(this,listeCommande,curseur,listeHistorique,listeVariables,listeFonctions));
//		table.put("OPEN", new Open(this,listeHistorique));
//		table.put("PENCOLOR", new PenColor(curseur,listeVariables));
//		table.put("PENSIZE", new PenSize(curseur,listeVariables));
//		table.put("REDO", new Redo());
//		table.put("REPEAT", new Repeat());
		table.put("RIGHT", new Right());
//		table.put("TURN", new Turn());
//		table.put("UNDO", new Undo());
		table.put("UP", new Up());
//		table.put("VAR", new Variables());
//		table.put("WHILE", new While());

	}

    public Commande searchCmd(String name){
        Commande cmd = table.get(name.toUpperCase());

        if (cmd == null) {
            for(ListeFonctions.ObjetFonction h: Commande.getListeFonctions().getliste()){
                if (h.getNom_Fonction().equalsIgnoreCase(name)){
                    return new ExFonction();
                }
            }
        }
        else return cmd;


        return null;

    }

	public void executerCommande(String commande){
        executerCommande(commande,true);
	}

    public void executerCommande(String commande,boolean addtohist){

        commande = commande.replaceAll("\\s+", " ");
        String[] commandeTab = commande.split(" ", 2);

        Commande cmd = searchCmd(commandeTab[0]);

        if(cmd != null){
           String return_Error_Msg = cmd.execute(commande);
           listeHistorique.addToList(commande,return_Error_Msg);
        }
        else listeHistorique.addToList(commande,"La commande "+ commandeTab[0]+" n'éxiste pas !");
    }
	
	







}
