package interfaceGraphique.ouest;

import com.intellij.ui.Gray;
import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeHistorique;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOptions extends JPanel{
    private JButton jbdessine;
    private JButton jbopen;
    private JButton jbsave;
    private JButton jbnew;
    private JButton jbundo;
    private JButton jbredo;
    private JButton jbpencolor;
    private JButton jbbgcolor;
    private ListeHistorique listeHistorique;
    private TableCommande tableCommande;
    private Curseur curseur;

    public PanelOptions(Curseur curseur,ListeHistorique listeHistorique,TableCommande tableCommande) {
        this.setBackground(Gray._239);
        this.listeHistorique = listeHistorique;
        this.tableCommande = tableCommande;
        GridLayout gl = new GridLayout();
        gl.setColumns(8);
        gl.setRows(1);


        gl.setHgap(0);
        gl.setVgap(0);
        this.setLayout(gl);
        this.curseur = curseur;

        jbopen = new JButton();
        jbopen.setIcon(new ImageIcon("./Images/open.png"));

        jbopen.addActionListener(new Ecouteur());

        jbdessine = new JButton();
        jbdessine.setIcon(new ImageIcon("./Images/dessine.png"));
        jbdessine.addActionListener(new Ecouteur());

        jbsave = new JButton();
        jbsave.setIcon(new ImageIcon("./Images/save.png"));
        jbsave.addActionListener(new Ecouteur());

        jbnew = new JButton();
        jbnew.setIcon(new ImageIcon("./Images/new.png"));
        jbnew.addActionListener(new Ecouteur());


        jbundo = new JButton();
        jbundo.setIcon(new ImageIcon("./Images/undo.png"));
        jbundo.addActionListener(new Ecouteur());


        jbredo = new JButton();
        jbredo.setIcon(new ImageIcon("./Images/redo.png"));
        jbredo.addActionListener(new Ecouteur());

        jbpencolor = new JButton();
        jbpencolor.setIcon(new ImageIcon("./Images/pencolor.gif"));
        jbpencolor.setBackground(curseur.getCouleurCurseur());
        jbpencolor.addActionListener(new Ecouteur());

        jbbgcolor = new JButton();
        jbbgcolor.setIcon(new ImageIcon("./Images/bgcolor.gif"));
        jbbgcolor.setBackground(curseur.getCouleurBg());
        jbbgcolor.addActionListener(new Ecouteur());




        jbnew.setPreferredSize(new Dimension(30, 30));
        jbopen.setPreferredSize(new Dimension(30,30));
        jbsave.setPreferredSize(new Dimension(30, 30));
        jbpencolor.setPreferredSize(new Dimension(30, 30));
        jbdessine.setPreferredSize(new Dimension(30, 30));
        jbundo.setPreferredSize(new Dimension(30, 30));
        jbredo.setPreferredSize(new Dimension(30, 30));
        jbbgcolor.setPreferredSize(new Dimension(30, 30));
        this.add(jbnew);
        this.add(jbopen);
        this.add(jbsave);
        this.add(jbundo);
        this.add(jbredo);
        this.add(jbdessine);
        this.add(jbpencolor);
        this.add(jbbgcolor);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (curseur.isPenDown()){
            jbdessine.setIcon(new ImageIcon("./Images/dessine.png"));
        }
        else {
            jbdessine.setIcon(new ImageIcon("./Images/dessinepas.png"));
        }
        jbbgcolor.setBackground(curseur.getCouleurBg());
        jbpencolor.setBackground(curseur.getCouleurCurseur());

    }


    private class Ecouteur implements ActionListener{


        public void actionPerformed(ActionEvent e) {
            /*if(e.getSource()==jbopen){
                listeHistorique.addToList("open",tableCommande.executerCommande("open",true));
            }
            else if(e.getSource()==jbsave){
                listeHistorique.addToList("save",tableCommande.executerCommande("save",null));
            }
            else if(e.getSource()==jbdessine){
                if (curseur.isPenDown()){
                    listeHistorique.addToList("penup",tableCommande.executerCommande("penup",null));
                }
                else {
                    listeHistorique.addToList("pendown",tableCommande.executerCommande("pendown",null));
                }
            }
            else if(e.getSource()==jbundo){
                listeHistorique.addToList("undo",tableCommande.executerCommande("undo",null));
            }
            else if(e.getSource()==jbredo){
                listeHistorique.addToList("redo",tableCommande.executerCommande("redo",null));
            }
            else if(e.getSource()==jbnew){
                listeHistorique.addToList("new",tableCommande.executerCommande("new",null));
            }
            else if(e.getSource()==jbpencolor){
                Color color = JColorChooser.showDialog(null, "Choisir la couleur du pinceau",null);
                if(color != null){
                    listeHistorique.addToList("pencolor "+color.getRed()+" "+color.getGreen()+" "+color.getBlue()
                            ,tableCommande.executerCommande("pencolor "+color.getRed()+" "+color.getGreen()+" "+color.getBlue(),null));
                }

            }
            else if(e.getSource()==jbbgcolor){
                Color color = JColorChooser.showDialog(null, "Choisir la couleur de l'arrière plan",null);
                if(color != null){
                    listeHistorique.addToList("backgroundcolor "+color.getRed()+" "+color.getGreen()+" "+color.getBlue()
                            ,tableCommande.executerCommande("backgroundcolor "+color.getRed()+" "+color.getGreen()+" "+color.getBlue(),null));
                }


            }    */

            PanelOnglet.repaintOnglet();
            Fenetre.getPanelDessin().repaint();
            Fenetre.getPanelInfo().repaint();
        }
    }
}
