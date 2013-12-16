package commande;

import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeHistorique;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Save extends Commande {

    private ListeHistorique listeHistorique;
    private Curseur curseur;
    private JFileChooser save;

    public Save(ListeHistorique listeHistorique,Curseur curseur){
        this.listeHistorique = listeHistorique;
        this.curseur = curseur;
        save = new JFileChooser() {

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
    }
    @Override
    public String execute(String[] parametres) {
        curseur.setDrawCurs(false);

        if (parametres.length == 1) {
            if (save.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println(save.getSelectedFile().getAbsolutePath());
                String s = "";
                try {
                    s = save.getSelectedFile()
                            .getAbsolutePath()
                            .substring(
                                    0,
                                    save.getSelectedFile().getAbsolutePath()
                                            .lastIndexOf("."));
                } catch (StringIndexOutOfBoundsException e) {

                }
                File fichier;
                if (!s.equals("")) {
                    fichier = new File(s);
                } else {
                    fichier = new File(save.getSelectedFile().getAbsolutePath());
                }

                BufferedImage bi = new BufferedImage(
                        Fenetre.getPanelDessin().getSize().width,
                        Fenetre.getPanelDessin().getSize().height,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                Fenetre.getPanelDessin().paint(g); // this == JComponent
                g.dispose();
                try {
                    ImageIO.write(bi, "png", new File(fichier.getAbsolutePath()
                            + ".png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    listeHistorique.saveHistory(fichier.getAbsolutePath());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else if (parametres.length == 2) {

            String s = "";
            try {
                s = parametres[1].substring(0, parametres[1].lastIndexOf("."));
            } catch (StringIndexOutOfBoundsException e) {

            }
            File fichier;
            if (!s.equals("")) {
                fichier = new File(s);
            } else {
                fichier = new File(parametres[1]);
            }
			/*
			 * Si le chmin a déjà une extension on la retire et on la stock Et
			 * on la rajoute a la fin ;
			 */

            if (fichier.exists()) {
                BufferedImage bi = new BufferedImage(
                        Fenetre.getPanelDessin().getSize().width,
                        Fenetre.getPanelDessin().getSize().height,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                Fenetre.getPanelDessin().paint(g); // this == JComponent
                g.dispose();
                try {
                    ImageIO.write(bi, "png", new File(fichier.getAbsolutePath()
                            + ".png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    listeHistorique.saveHistory(fichier.getAbsolutePath());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
				/*
				 * séparer fichier et pathName
				 */
				/*
				 * The prefix concept is used to handle root directories on UNIX
				 * platforms, and drive specifiers, root directories and UNC
				 * pathnames on Microsoft Windows platforms, as follows:
				 *
				 * For UNIX platforms, the prefix of an absolute pathname is
				 * always "/". Relative pathnames have no prefix. The abstract
				 * pathname denoting the root directory has the prefix "/" and
				 * an empty name sequence.
				 *
				 *
				 * For Microsoft Windows platforms, the prefix of a pathname
				 * that contains a drive specifier consists of the drive letter
				 * followed by ":" and possibly followed by "\\" if the pathname
				 * is absolute. The prefix of a UNC pathname is "\\\\"; the
				 * hostname and the share name are the first two names in the
				 * name sequence. A relative pathname that does not specify a
				 * drive has no prefix.
				 */
                File gg = fichier.getParentFile();

                gg.mkdirs();

                BufferedImage bi = new BufferedImage(
                        Fenetre.getPanelDessin().getSize().width,
                        Fenetre.getPanelDessin().getSize().height,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                Fenetre.getPanelDessin().paint(g); // this == JComponent
                g.dispose();
                try {
                    ImageIO.write(bi, "png", new File(fichier.getAbsolutePath()
                            + ".png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    listeHistorique.saveHistory(fichier.getAbsolutePath());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        } else {
            // erreur
        }
        curseur.setDrawCurs(true);
        return "";
    }
}
