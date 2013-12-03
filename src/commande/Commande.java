package commande;


public abstract class Commande {

	/**
	 * @param parametres
	 * @return
	 */
	public abstract String execute(String[] parametres);

	/**
	 * @param tab
	 * @return
	 */
	protected String toString(String[] tab){
		String s ="";
		int i=0;
		for (i=0 ; i<tab.length ;i++ ) {
			s += tab[i] + "\n";
		}
		return s;
	}
	

}