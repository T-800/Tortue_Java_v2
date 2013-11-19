package history;

import java.io.IOException;

public interface History {

	/**
	 * Ajoute la derniere commande au fichier History.tmp. Sauf pour les
	 * commandes (SAVE; UNDO; OPEN)
	 * 
	 * @param Comande
	 *            Derniere commande entrée par l'utilisateur
	 * @throws IOException
	 */
	public void addToHistory(String comande) throws IOException;

	/**
	 * Supprime la derniere ligne du fichier Utiliser par la commande UNDO et
	 * EARASE Comme ça les commandes annulées par UNDO ne feront pas partie de
	 * l'istorique Pour EARASE cette commande s'appelle recursivement avec la
	 * fonction {@link #empty()} en test
	 * 
	 * @throws IOException
	 * @see #empty()
	 */
	public void removeToHistory() throws IOException;

	/**
	 * Sauvegarde le fichier History.tmp dans un fichier NomDuDessin.txt Dans le
	 * chemain donné en parametre
	 * 
	 * @param NomDessin
	 *            le fichier History aura le meme nom que le dessin
	 * @param chemin
	 *            Chemin où sera sauvegardé le fichier
	 * @throws IOException
	 */
	public void saveHistory(String chemin) throws IOException;

}
