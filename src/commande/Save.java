package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeHistorique;
import liste.ListeVariables;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Save extends Commande {

    private ListeHistorique listeHistorique;
    private Curseur curseur;
    private JFileChooser save;

    public Save(ListeHistorique listeHistorique,Curseur curseur){
        this.listeHistorique = listeHistorique;
        this.curseur = curseur;
        save = new JFileChooser();
    }
    @Override
    public String execute(String[] commande, ListeVariables listeVariables) {
        if(commande.length>3 || commande.length == 2)return "1";
        File fichier;
        curseur.setDrawCurs(false);
        listeHistorique.getliste().remove(listeHistorique.getliste().size()-1);
        if (commande.length == 1){ // save txt et jpg avec filechooser
            if (save.showDialog(null, "Choix du fichier") == JFileChooser.APPROVE_OPTION) {
                fichier = save.getSelectedFile();
            }
            else{
                listeHistorique.addToList(commande[0],"");
                return "Annulé";
            }
        }
        else {
            String name;
            String fileString = "";
            String error;
            switch (commande[1]){
                case "a":
                case "all":
                    try {
                        fileString = commande[2].substring(0, commande[2].lastIndexOf("."));
                    } catch (StringIndexOutOfBoundsException e) {

                    }
                    if (!fileString.equals("")) {
                        fichier = new File(fileString);
                    } else {
                        fichier = new File(commande[2]);
                    }
                    error = saveJpg(fichier.getAbsolutePath());
                    if(!error.equalsIgnoreCase("")){
                        listeHistorique.addToList(commande[0],"");
                        return error;
                    }
                    error = saveTxt(fichier.getAbsolutePath());
                    if(!error.equalsIgnoreCase("")){
                        listeHistorique.addToList(commande[0],"");
                        return error;
                    }

                    break;
                case "t":
                case "txt":

                    try {
                        fileString = commande[2].substring(0, commande[2].lastIndexOf("."));
                    } catch (StringIndexOutOfBoundsException e) {

                    }
                    if (!fileString.equals("")) {
                        fichier = new File(fileString);
                    } else {
                        fichier = new File(commande[2]);
                    }
                    error = saveTxt(fichier.getAbsolutePath());
                    if(!error.equalsIgnoreCase("")){
                        listeHistorique.addToList(commande[0],"");
                        return error;
                    }

                    break;
                case "d":
                case "jpg":
                    try {
                        fileString = commande[2].substring(0, commande[2].lastIndexOf("."));
                    } catch (StringIndexOutOfBoundsException e) {

                    }
                    if (!fileString.equals("")) {
                        fichier = new File(fileString);
                    } else {
                        fichier = new File(commande[2]);
                    }
                    error = saveJpg(fichier.getAbsolutePath());
                    if(!error.equalsIgnoreCase("")){
                        listeHistorique.addToList(commande[0],"");
                        return error;
                    }
                    break;
            }

        }


        curseur.setDrawCurs(true);
        return "";
    }


    public String saveTxt(String filePath){
        /*
		 * Si le chmin a déjà une extension on la retire et on la stock Et on la
		 * rajoute a la fin ;
		 */
        File destination = new File(filePath + ".txt");

        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(destination));
        } catch (IOException e) {
            return "erreur ouverture fichier";
        }
        try {
            output.write("#####TORTUE GENIAL IK3#####"+ "\r\n" );
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int ligne = 0;
        while (ligne < listeHistorique.getliste().size()) {
            try {
                if(listeHistorique.getliste().get(ligne).getError_msg().equalsIgnoreCase("")){
                    output.write(listeHistorique.getliste().get(ligne).getCommande() + "\r\n");
                    output.flush();
                }

            } catch (IOException ioe) {
                System.out.println("erreur : " + ioe);
                return "erreur ouverture fichier2";

            }
            ligne++;
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    public String saveJpg(String filePath){
        File destination = new File(filePath);
        BufferedImage bi = new BufferedImage(
                Fenetre.getPanelDessin().getSize().width,
                Fenetre.getPanelDessin().getSize().height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        Fenetre.getPanelDessin().paint(g); // this == JComponent
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File(destination.getAbsolutePath()
                    + ".png"));
        } catch (Exception e) {
            return "error save img";
        }

      return "";
    }
}