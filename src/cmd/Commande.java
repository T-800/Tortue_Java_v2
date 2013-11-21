package cmd;

import ihm.Dessin;
import java.util.ArrayList;
import history.HistoryTortue;

import cmd.Variables.ObjetVariables;
abstract class Cmd {

	//protected HistoryTortue history = new HistoryTortue();
	static ArrayList<ObjetVariables> liste_Variables = new ArrayList<ObjetVariables>();
	HistoryTortue history = new HistoryTortue();
	//static ArrayList<String> hist = new ArrayList<String>();
	/**
	 * 
	 * @param parametres
	 * @param dessin
	 * @param curseur
	 * @param addToHistory
	 * @throws IOException
	 */
	abstract String execute(String[] parametres, Dessin dessin, HashTable table);

	/**
	 * 
	 * @param tab
	 * @return h
	 */
	protected String toString(String[] tab){
		String s ="";
		int i=0;
		for (i=0 ; i<tab.length ;i++ ) {
			s += tab[i] + " ";
		}
		return s;
	}

	protected ObjetVariables get_Variable(String nom_Variable){
		for (ObjetVariables var : liste_Variables) {
				if(var.getNom_Variable().equals(nom_Variable)) return var;
		}
		return null;
	}



}
