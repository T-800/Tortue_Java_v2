package commande;

import terminal.TableCommande;

import javax.swing.*;
import java.io.*;

public class Open extends Commande{
    @Override
    public String execute(String parametres) {

        BufferedReader reader;
        JFileChooser open = new JFileChooser() {

            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (!(f.exists() && getDialogType() == OPEN_DIALOG)) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Le fichier n'éxiste pas Voulez-vous recomencer?",
                            "", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:

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

        open.setApproveButtonText("Choix du fichier"); // intitulé du bouton


        if (open.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fichier = open.getSelectedFile();
            try {
                reader = new BufferedReader(new FileReader(fichier));
            } catch (FileNotFoundException e) {
                return "Error file!";
            }
            String ligne = null;
            try {
                ligne = reader.readLine();
                if(!ligne.equalsIgnoreCase( "###TORTUE_GENIAL###")) return "fichier incorrecte";
                ligne = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (ligne != null) {
                TableCommande.executerCommande(ligne);
                try {
                    ligne = reader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return "";
        }
        else return "Opération annulé";

    }


}
