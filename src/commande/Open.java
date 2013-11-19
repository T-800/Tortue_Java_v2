package commande;

import history.HistoryTerminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * Permet d'ouvrir des fichier de commande
 *
 */
public class Open implements Commande {

	JFileChooser open = new JFileChooser();
	BufferedReader reader;
	ArrayList<String> list = new ArrayList<String>();

	/*
	 * Limite le choix d'ouverture des fichiers aux fichiers .txt
	 */
	public Open() {
		FileFilter filter1 = new ExtensionFileFilter("Fichier texte",
				new String[] { "txt" });
		open.setFileFilter(filter1);
		open.setApproveButtonText("Choix du fichier"); // intitulé du bouton
	}

	/**
	 * determine le type d'open utilisé
	 * @param parametres
	 * @return
	 */
	private int args(String[] parametres) {
		if (parametres.length == 1)
			return 0; // open
		if (parametres[1].equalsIgnoreCase("new")) {
			if (parametres.length == 2)
				return 1; // open new
			else
				return 2; // open new path
		} else
			return 3; // open path
	}

	@Override
	/**
	 * (non-Javadoc)
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory)
			throws IOException {

		/*
		 * fichier a ouvrir
		 */
		File fichier;

		int arg = args(parametres);
		switch (arg) {

		case 0:// OPEN
			/*
			 * on ouvre une boite de dialogue pour choisir le fichier
			 */
			int ret = open.showDialog(null, "Choix du fichier");

			if (ret == JFileChooser.APPROVE_OPTION) {
				fichier = open.getSelectedFile();
				System.out.println(fichier);
				if (rightExtention(fichier.getPath()) && fichier.exists()) {
					try {
						reader = new BufferedReader(new FileReader(fichier));
					} catch (FileNotFoundException e) {

					}
				}
			}
			break;

		case 1:// OPEN NEW
			
			/*
			 * on efface le dessin
			 */
			HashTable.hgj("NEW", false);
			if (New.i != -1)
				/*
				 * si la commande new n'a pas été annulé on rappel open mais le case 0
				 */
				HashTable.hgj("OPEN", false);

			break;

		case 2: // OPEN NEW PATH
			/*
			 * on efface le dessin
			 */
			HashTable.hgj("NEW", false);
			if (New.i != -1)
				/*
				 * si la commande new n'a pas été annulé on rappel open mais le case 3
				 */
				HashTable.hgj("OPEN " + parametres[2], false);
			break;

		case 3: // OPEN PATH
			/*
			 * on ouvre le fichier passé en parametre
			 */
			fichier = new File(parametres[1]);
			reader = new BufferedReader(new FileReader(fichier));
			break;
		}

		String ligne = reader.readLine();

		while (ligne != null) {
			/*
			 * pour toutes les ligne du fichier
			 */
			ligne = ligne.trim();
			/*
			 * si la ligne n'est pas vide on l'joute a une arrayliste
			 */
			if (!ligne.equals("")) {
				list.add(ligne);
			}
			ligne = reader.readLine();
		}

		for (String l : this.list) {
			/*
			 * on teste si toute les ligne peuvent etre éxécuté
			 */
			String[] cmd = HashTable.splitSpace(l);
			try {
				if (!HashTable.itsOK(cmd)) {
					list = new ArrayList<String>();
					return;
				}
			} catch (NullPointerException e1) {

			}

		}
		for (String l : this.list) {
			/*
			 * on execute toute les commande presente dans le fichier
			 */
			HashTable.hgj(l, true);
			//HistoryTerminal.addToHistory(l);

		}
		list = new ArrayList<String>();

	}

	public boolean rightExtention(String way) {
		String sub = way.substring(way.length() - 3, way.length());
		return sub.equals("txt");
	}

	@Override
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	class ExtensionFileFilter extends FileFilter {
		String description;

		String extensions[];

		public ExtensionFileFilter(String description, String extension) {
			this(description, new String[] { extension });
		}

		public ExtensionFileFilter(String description, String extensions[]) {
			if (description == null) {
				this.description = extensions[0];
			} else {
				this.description = description;
			}
			this.extensions = extensions.clone();
			toLower(this.extensions);
		}

		private void toLower(String array[]) {
			for (int i = 0, n = array.length; i < n; i++) {
				array[i] = array[i].toLowerCase();
			}
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		public boolean accept(File file) {
			if (file.isDirectory()) {
				return true;
			} else {
				String path = file.getAbsolutePath().toLowerCase();
				for (int i = 0, n = extensions.length; i < n; i++) {
					String extension = extensions[i];
					if ((path.endsWith(extension) && (path.charAt(path.length()
							- extension.length() - 1)) == '.')) {
						return true;
					}
				}
			}
			return false;
		}
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
