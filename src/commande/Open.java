package commande;

import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Open extends Commande {

    private JFileChooser open;
    private TableCommande tableCommande;
    BufferedReader reader;
    private ListeHistorique listeHistorique;

    public Open(TableCommande tableCommande, ListeHistorique listeHistorique) {
        open = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        open.setFileFilter(filter);
        this.tableCommande = tableCommande;
        this.listeHistorique =  listeHistorique;
    }

    @Override
    public String execute(String[] commande, ListeVariables listeVariables) {

        if(commande.length>2)return "1";
        File fichier;
        listeHistorique.getliste().remove(listeHistorique.getliste().size()-1);
        if (commande.length == 1){ // open
            if (open.showDialog(null, "Choix du fichier") == JFileChooser.APPROVE_OPTION) {
                fichier = open.getSelectedFile();
            }
            else{
                listeHistorique.addToList(commande[0],"");
                return "Annulé";
            }
        }
        else{ // open path
            fichier = new File(commande[1]);
        }
        if(!fichier.isFile()){
            listeHistorique.addToList(commande[0]+" "+commande[1],"");
            return "le fichier "+commande[1]+" n'éxiste pas!";
        }
        int i = fichier.getAbsolutePath().lastIndexOf('.');
        String extension = "";
        if (i > 0) {
            extension = fichier.getAbsolutePath().substring(i+1);
        }
        if (extension.equalsIgnoreCase("txt")) {
            try {
                reader = new BufferedReader(new FileReader(fichier));
            } catch (FileNotFoundException ignored) {

            }
        }
        String ligne = "";
        try {
            ligne = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!ligne.equalsIgnoreCase("#####TORTUE GENIAL IK3#####"))return "Fichier incorrecte";

        try {
            ligne = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        int nbligne = 2;
        while (ligne != null){
            ligne = ligne.trim();
			/*
			 * si la ligne n'est pas vide on l'ajout a une arraylist
			 */
            if (!ligne.equals("")) {
                String error = tableCommande.executerCommande(ligne,listeVariables);
                if(!error.equals("")){
                    listeHistorique.addToList(commande[0],"");
                    return "Une erreur est survenue lors de l'ouverture du fichier : "+fichier.getName()+"   Ligne :"+nbligne+"<br>"+error;
                }
                listeHistorique.addToList(ligne, "");
            }
            try {
                ligne = reader.readLine();
                nbligne++;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return "";
    }

}
