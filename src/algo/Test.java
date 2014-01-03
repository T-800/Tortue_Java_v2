package algo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends JFrame {

    public static void main(String[] args) {
        Test test=new Test();
        test.setVisible(true);
    }

    public Test() {
        super("test Pli/Depli");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(200, 400));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        String[] options={"option 1","option 2","option 3"};

        Pano pano1=new Pano("Titre 1",options);
        Pano pano2=new Pano("Titre 2",options);
        Pano pano3=new Pano("Titre 3",options);

        getContentPane().add(pano1);
        getContentPane().add(pano2);
        getContentPane().add(pano3);

        JPanel tampon=new JPanel();
        tampon.setPreferredSize(new Dimension(20, 2000));

        getContentPane().add(tampon);

        pack();
    }

    private class Pano extends JPanel {

        JPanel jPanelTout;
        JPanel jPanelTitre;
        JPanel jPanelOptions;

        public Pano(String titre,String[] options) {
            super();

            jPanelTout=new JPanel();
            jPanelTitre=new JPanel();
            jPanelOptions=new JPanel();

            jPanelTout.setLayout(new BoxLayout(jPanelTout, BoxLayout.Y_AXIS));
            jPanelTitre.setLayout(new BorderLayout());
            jPanelOptions.setLayout(new BoxLayout(jPanelOptions, BoxLayout.Y_AXIS));

            // Panneau Titre

            jPanelTitre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanelTitre.setMaximumSize(new java.awt.Dimension(2000, 50));

            JLabel jLabelTitre=new JLabel();
            jLabelTitre.setText(titre);
            jPanelTitre.add(jLabelTitre, BorderLayout.CENTER);

            JButton bouton=new JButton();
            bouton.setText("Pli/Depli");
            bouton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boutonActionPerformed(evt);
                }
            });

            jPanelTitre.add(bouton,BorderLayout.EAST);
            jPanelTout.add(jPanelTitre);

            // Panneau Options

            jPanelOptions.setLayout(new BorderLayout());
            jPanelOptions.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

            JPanel optionPanel=new JPanel();
            optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
            optionPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));

            JLabel[] optionsLabel=new JLabel[options.length];
            for (int i=0;i<options.length;i++) {
                optionsLabel[i]=new JLabel();
                optionsLabel[i].setText("Option "+titre+"_"+(i+1));
                optionPanel.add(optionsLabel[i]);
            }

            jPanelOptions.add(optionPanel, BorderLayout.CENTER);
            jPanelTout.add(jPanelOptions);

            this.add(jPanelTout);
        }

        private void boutonActionPerformed(ActionEvent evt) {
            this.jPanelOptions.setVisible(!this.jPanelOptions.isVisible());
        }
    }
}