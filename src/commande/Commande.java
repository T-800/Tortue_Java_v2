package commande;


import liste.ListeVariables;

import java.util.ArrayList;

public abstract class Commande {

	/**
	 * @param parametres h
	 * @return error msg
     *
	 */

	public abstract String execute(String[] parametres, ListeVariables listeVariables);

}