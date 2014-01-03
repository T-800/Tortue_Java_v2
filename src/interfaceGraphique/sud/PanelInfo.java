package interfaceGraphique.sud;

import dessin.Curseur;
import interfaceGraphique.Fenetre;
import javax.swing.*;
import java.awt.*;

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
		Curseur rem = curseur.getCursRemember();
        if (rem == null)this.jlsaved.setText("Save (0;0|°0)");
        else this.jlsaved.setText("Save ("+curseur.getCursRemember().getPos()[0]+";"+curseur.getCursRemember().getPos()[1]+"|°"+curseur.getCursRemember().getD()+")");
        this.jltaille.setText("Taille : "+curseur.getPenSize());//Image Texte Image
		
		//this.jldessine
	}
	

}
