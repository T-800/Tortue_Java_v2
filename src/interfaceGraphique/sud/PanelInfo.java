package interfaceGraphique.sud;

import com.intellij.ui.Gray;
import dessin.Curseur;
import interfaceGraphique.Fenetre;
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
	private Curseur curseur;

	public PanelInfo(Curseur curseur) {
		this.setBackground(new Color(0xE6,0xE6,0xE6));
		this.curseur = curseur;
		setLayout(new GridLayout());
		
		
		
		
		jlsouris = new JLabel("(0;0)");
		jlcurseur = new JLabel("Curseur ("+this.curseur.getX()+";"+this.curseur.getY()+"|°D)", JLabel.LEFT);
		jlsaved = new JLabel("Save (X;Y|°D)", JLabel.LEFT);
		

		
		
		
		jltaille = new JLabel("Taille : "+curseur.getPenSize());//Image Texte Image
		this.add(jlcurseur);
        this.add(jlsaved);
        this.add(jltaille);
        this.add(jlsouris);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		setCurseur();
		
	}
	
	public void setCurseur(){
		this.jlsouris.setText("("+(curseur.getSourisX()- Fenetre.getCenterDessin()[0])+";"+(Fenetre.getCenterDessin()[1]-curseur.getSourisY())+")");
		this.jlcurseur.setText("Curseur ("+(curseur.getX()-Fenetre.getCenterDessin()[0])+";"+(Fenetre.getCenterDessin()[1]-curseur.getY())+"|°"+curseur.getD()+")");
		this.jlsaved.setText("Save ("+curseur.getTabRemember()[0]+";"+curseur.getTabRemember()[1]+"|°"+curseur.getTabRemember()[2]+")");
        this.jltaille.setText("Taille : "+curseur.getPenSize());//Image Texte Image
		
		//this.jldessine
	}
	

}
