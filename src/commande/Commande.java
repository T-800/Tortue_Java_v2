package commande;


public abstract class Commande {

	/**
	 * @param parametres h
	 * @return error msg
	 */
	public abstract String execute(String[] parametres);


}