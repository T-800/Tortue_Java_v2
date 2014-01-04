package commande;

import terminal.TableCommande;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save extends Commande{
    @Override
    public String execute(String parametres) {

        JFileChooser save = new JFileChooser() {

            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (f.exists() && getDialogType() == SAVE_DIALOG) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Le fichier éxiste déjà Voulez-vous l'écraser?",
                            "Ecrasement", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            cancelSelection();
                            return;
                        default:
                            return;
                    }
                }
                super.approveSelection();
            }
        };

        save.setApproveButtonText("Choix du fichier"); // intitulé du bouton


        if (save.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println(save.getSelectedFile().getAbsolutePath());
            getPanelDessin().getCurseur().setDrawCurs(false);
            String path = "";
            try {
                path = save.getSelectedFile()
                        .getAbsolutePath()
                        .substring(
                                0,
                                save.getSelectedFile().getAbsolutePath()
                                        .lastIndexOf("."));
            } catch (StringIndexOutOfBoundsException ignored) {

            }
            File fichier;
            if (!path.equals("")) {
                fichier = new File(path);
            } else {
                fichier = new File(save.getSelectedFile().getAbsolutePath());
            }

            BufferedImage bi;
            bi = new BufferedImage(
                    getPanelDessin().getSize().width,
                    getPanelDessin().getSize().height,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            getPanelDessin().paint(g); // this == JComponent
            g.dispose();
            try {
                ImageIO.write(bi, "png", new File(fichier.getAbsolutePath()
                        + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveHistory(fichier.getAbsolutePath(),TableCommande.listeHistorique.getSaveHistory());
        }
        getPanelDessin().getCurseur().setDrawCurs(true);


        return "";
    }
    public void saveHistory(String chemin, ArrayList<String> list) {
		/*
		 * Si le chmin a déjà une extension on la retire et on la stock Et on la
		 * rajoute a la fin ;
		 */
        File destination = new File(chemin + ".txt");

        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int ligne = 0;
        try {
            output.write( "###TORTUE_GENIAL###\r\n");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (ligne < list.size()) {
            try {

                output.write(list.get(ligne) + "\r\n");
                output.flush();

            } catch (IOException ioe) {
                System.out.println("erreur : " + ioe);
            }
            ligne++;
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
