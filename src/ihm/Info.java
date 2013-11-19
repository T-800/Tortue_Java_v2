package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetQuiDessine.CurseurQuiDessine;

public class Info extends JPanel implements ActionListener{

	private JLabel curseur,souris,dessine,remember,couleur;
	private JButton jButtonCouleur;
	public CurseurQuiDessine curs;
	
	
	
	public JButton getjButtonCouleur() {
		return jButtonCouleur;
	}



	public void setjButtonCouleur(JButton jButtonCouleur) {
		this.jButtonCouleur = jButtonCouleur;
	}



	public Info() {
		//this.setBackground(Color.green);
		
		curseur = new JLabel();
		souris = new JLabel();
		dessine = new JLabel();
		remember = new JLabel();
		couleur = new JLabel();
		couleur.setText("Couleur : ");
		
		jButtonCouleur=new JButton();
		jButtonCouleur.setBackground(Color.black);
		jButtonCouleur.addActionListener(this);
		
		
		this.setLayout(new GridBagLayout());
		
	    //L'objet servant � positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
		
	    //On positionne la case de d�part du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    this.add(curseur, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    this.add(dessine, gbc);
	    gbc.gridx = 2;
	    this.add(remember,gbc);
	    gbc.gridx = 3;
	    this.add(souris, gbc);
	    gbc.gridx = 4;
	    this.add(couleur, gbc);
	    gbc.gridx = 5;
	    this.add(jButtonCouleur, gbc);
		
	}
	
	
	
	public JLabel getCurseur() {
		return curseur;
	}
	public void setCurseur(int x,int y,int dir) {
		this.curseur.setText("Curseur ( "+x+";"+y+"| "+dir+"? )");
	}
	public JLabel getSouris() {
		return souris;
	}
	public void setSouris(int x,int y) {
		this.souris.setText("Souris ("+x+";"+y+")");
		
	}
	public JLabel getDessine() {
		return dessine;
	}
	public void setDessine(boolean dessine) {
		if(dessine)this.dessine.setText("Dessine : Oui");
		else this.dessine.setText("Dessine : Non");
	}
	public JLabel getRemember() {
		return remember;
	}
	public void setRemember(int x,int y,int dir) {
		this.remember.setText("Remember ( "+x+";"+y+"| "+dir+"? )");
	}
	public JLabel getCouleur() {
		return couleur;
	}
	public void setCouleur(JLabel couleur) {
		this.couleur = couleur;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		Color color = JColorChooser.showDialog(this, "Choose a color", Color.black);
		curs.setCouleur(color);
		jButtonCouleur.setBackground(color);
		
		
		
	}
	
}
