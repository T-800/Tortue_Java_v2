package terminal;

import commande.*;
import liste.ListeFonctions;
import liste.ListeHistorique;
import java.util.Hashtable;


public class TableCommande {

    public static ListeHistorique listeHistorique;
	public static Hashtable<String, Commande> table = new Hashtable<>();
	/**
	 * déclaration de la table de hashage
	 */
	public TableCommande(ListeFonctions listeFonctions,ListeHistorique listeHistorique) {
        Commande.setListeFonctions(listeFonctions);
        TableCommande.listeHistorique = listeHistorique;

		table.put("BGCOLOR", new BackgroundColor());
		table.put("CENTER", new Center());
		table.put("CLEAR", new Clear());
		table.put("FONCTION", new Fonctions());// tester avec instruction composée
		table.put("GO", new Go());
//		table.put("HELP", new Help());
		table.put("IF", new If());
		table.put("MOVE", new Move()); // move random
        table.put("NEW", new New());
//		table.put("OPEN", new Open(this,listeHistorique));
		table.put("PENCOLOR", new Pencolor());
		table.put("PENSIZE", new PenSize());
//		table.put("REDO", new Redo());
		table.put("REPEAT", new Repeat());
		table.put("SAVE", new Save());
		table.put("TURN", new Turn());
//		table.put("UNDO", new Undo());

	}

    public static Commande searchCmd(String name){
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

	public static String executerCommande(String commande){
        return executerCommande(commande,true);
	}

    public static String executerCommande(String commande,boolean addtohist){

        commande = commande.replaceAll("\\s+", " ");
        String[] commandeTab = commande.split(" ", 2);

        Commande cmd = searchCmd(commandeTab[0]);
        String return_Error_Msg = "";
        if(cmd != null){
           return_Error_Msg = cmd.execute(commande);
           if(addtohist) listeHistorique.addToList(commande,return_Error_Msg);
        }
        else {
            return_Error_Msg = "La commande "+ commandeTab[0]+" n'éxiste pas !";
            if(addtohist)listeHistorique.addToList(commande,return_Error_Msg);
        }
        return return_Error_Msg;
    }
	
	







}
