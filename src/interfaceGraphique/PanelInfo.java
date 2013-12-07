package interfaceGraphique;

import com.intellij.ui.Gray;
import dessin.Curseur;
import liste.ListeHistorique;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInfo extends JPanel{
	
	private JLabel jlsouris;
	private JLabel jlcurseur;
	private JLabel jlsaved ;
	private JLabel jltaille;
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

	public PanelInfo(Curseur curseur,ListeHistorique listeHistorique,TableCommande tableCommande) {
		this.setBackground(Gray._239);
		this.listeHistorique = listeHistorique;
		this.tableCommande = tableCommande;
		GridLayout gl = new GridLayout();
		gl.setColumns(7);
		gl.setRows(2);


		gl.setHgap(0);
		gl.setVgap(0);
		this.setLayout(gl);
		this.curseur = curseur;
		
		
		
		
		
		jlsouris = new JLabel("(0;0)");
		jlcurseur = new JLabel("Curseur ("+this.curseur.getX()+";"+this.curseur.getY()+"|°D)", JLabel.LEFT);
		jlsaved = new JLabel("Save (X;Y|°D)", JLabel.LEFT);
		
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
		
		
		
		jltaille = new JLabel("Taille : - "+curseur.getPenSize()+" +", JLabel.LEFT);//Image Texte Image
        this.add(jbnew);
        this.add(jbopen);
        this.add(jbsave);
        this.add(jbpencolor);
		this.add(jlcurseur);
        this.add(jlsaved);
		this.add(jbdessine);
        this.add(jbundo);
        this.add(jbredo);
		this.add(jbbgcolor);
        this.add(jltaille);
        this.add(jlsouris);

		jbopen.setPreferredSize(new Dimension(10, 10)); 
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		setCurseur();
		if (curseur.isPenDown()){
    		jbdessine.setIcon(new ImageIcon("./Images/dessine.png"));
    	}
    	else {
    		jbdessine.setIcon(new ImageIcon("./Images/dessinepas.png"));
    	}
		
	}
	
	public void setCurseur(){
		this.jlsouris.setText("("+(curseur.getSourisX()-Fenetre.getCenterDessin()[0])+";"+(Fenetre.getCenterDessin()[1]-curseur.getSourisY())+")");
		this.jlcurseur.setText("Curseur ("+(curseur.getX()-Fenetre.getCenterDessin()[0])+";"+(Fenetre.getCenterDessin()[1]-curseur.getY())+"|°"+curseur.getD()+")");
		this.jlsaved.setText("Save ("+curseur.getTabRemember()[0]+";"+curseur.getTabRemember()[1]+"|°"+curseur.getTabRemember()[2]+")");
        this.jltaille.setText("Taille : - "+curseur.getPenSize()+" +");//Image Texte Image
		
		//this.jldessine
	}
	
	private class Ecouteur implements ActionListener{
		 
		 
	      public void actionPerformed(ActionEvent e) {
	    	  if(e.getSource()==jbopen){
	    		  listeHistorique.addToList("open",tableCommande.executerCommande("open"));
	    	  }
	    	  else if(e.getSource()==jbsave){
	    		  listeHistorique.addToList("save",tableCommande.executerCommande("save"));
	    	  }
	    	  else if(e.getSource()==jbdessine){
	    		  if (curseur.isPenDown()){
	    			  listeHistorique.addToList("penup",tableCommande.executerCommande("penup"));
	    		  }
	    		  else {
	    			  listeHistorique.addToList("pendown",tableCommande.executerCommande("pendown"));
	    		  }
	    	  }
	    	  else if(e.getSource()==jbundo){
	    		  listeHistorique.addToList("undo",tableCommande.executerCommande("undo"));
	    	  }
	    	  else if(e.getSource()==jbredo){
	    		  listeHistorique.addToList("redo",tableCommande.executerCommande("redo"));
	    	  }
	    	  else if(e.getSource()==jbnew){
	    		  listeHistorique.addToList("new",tableCommande.executerCommande("new"));
	    	  }
	    	  else if(e.getSource()==jbpencolor){
                  //Color color = JColorChooser.showDialog(null, "Choisir la couleur du pinceau",null);
	    		  listeHistorique.addToList("pencolor",tableCommande.executerCommande("pencolor"));
	    	  }
	    	  else if(e.getSource()==jbbgcolor){
                  //Color color = JColorChooser.showDialog(null, "Choisir la couleur de l'arrière plan",null);
	    		  listeHistorique.addToList("backgroundcolor",tableCommande.executerCommande("backgroundcolor"));
	    	  }
	        
	    	  PanelOnglet.repaintOnglet();
	    	  Fenetre.getPanelDessin().repaint();
	    	  Fenetre.getPanelInfo().repaint();
	      }
	   }
}
