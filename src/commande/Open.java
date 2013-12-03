package commande;


import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.ArrayList;

public class Open extends Commande {


    JFileChooser open = new JFileChooser();
    BufferedReader reader;
    ArrayList<String> listOpenCmd = new ArrayList<String>();

    ListeHistorique listeHistorique;
    TableCommande tableCommande;

    public Open(TableCommande commande,ListeHistorique listeHistorique) {
        this.tableCommande = commande;
        this.listeHistorique = listeHistorique;
        FileFilter filter1 = new ExtensionFileFilter("Fichier texte",
                new String[] { "txt" });
        open.setFileFilter(filter1);
        open.setApproveButtonText("Choix du fichier"); // intitulé du bouton
    }

    @Override
    public String execute(String[] commande) {
        File fichier = new File(toString());

        if (open.showDialog(null, "Choix du fichier") == JFileChooser.APPROVE_OPTION) {
            fichier = open.getSelectedFile();
            System.out.println(fichier);
            if (rightExtention(fichier.getPath()) && fichier.exists()) {
                try {
                    reader = new BufferedReader(new FileReader(fichier));
                } catch (FileNotFoundException e) {

                }
            }
        }


        String ligne = null;
        try {
            ligne = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        while (ligne != null) {
			/*
			 * pour toutes les ligne du fichier
			 */
            ligne = ligne.trim();
			/*
			 * si la ligne n'est pas vide on l'joute a une arrayliste
			 */
            if (!ligne.equals("")) {
                listOpenCmd.add(ligne);
            }
            try {
                ligne = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
        for (String l : this.listOpenCmd) {
			/*
			 * on execute toute les commande presente dans le fichier
			 */
            String error = tableCommande.executerCommande(l);
            listeHistorique.addToList(l, error);
            if(!error.equals("")) return "Une erreur est survenue lors de l'ouverture du fichier : "+fichier.getName()+"<br>"+error;

        }
        return "";
    }

    public boolean rightExtention(String way) {
        String sub = way.substring(way.length() - 3, way.length());
        return sub.equals("txt");
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



}
