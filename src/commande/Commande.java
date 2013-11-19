package commande;

import history.HistoryTortue;

import java.io.IOException;
import java.util.ArrayList;

import commande.Variables.ObjetVariables;

public interface Commande {

	HistoryTortue history = new HistoryTortue();
	static ArrayList<ObjetVariables> variables = new ArrayList<Variables.ObjetVariables>();

	/**
	 * 
	 * @param parametres
	 * @param dessin
	 * @param curseur
	 * @param addToHistory
	 * @throws IOException
	 */
	public void execute(String[] parametres, boolean addToHistory)
			throws IOException;

	/**
	 * 
	 * @param tab
	 * @return h
	 */
	public String ToString(String[] tab);

	/**
	 * 
	 * @param parametres
	 * @param dessin
	 * @param curseur
	 * @return g
	 */
	public boolean canDoIt(String[] parametres);

	/**
	 * 
	 * @return
	 */
	public int random();

}
