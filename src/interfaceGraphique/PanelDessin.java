package interfaceGraphique;

import dessin.Curseur;
import liste.ListeCommande;
import liste.ListeCommande.Ligne;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelDessin extends JPanel implements MouseListener, MouseMotionListener{
	
	private Curseur curseur;
	private ListeCommande listeCommande;
	private TableCommande tableCommande;
	
	public PanelDessin(Curseur curseur, ListeCommande listeCommande,TableCommande tableCommande) {
		this.tableCommande = tableCommande;
		this.curseur = curseur;
		this.listeCommande = listeCommande;
		int t[] = {this.getSize().width,this.getSize().height,55};
		curseur.setPos(t);
		this.setSize(new Dimension(200, 200));
		
		addMouseMotionListener(this);
		setBackground(curseur.getCouleurBg());
		
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        //g2.fillRect(10, 10, getWidth()-50, getHeight()-50);
        
        for (Ligne l : this.listeCommande.getCmd()) {
			g2.setColor(l.getColor());
			if(l.getTaille() != 1){
				
				g2.setStroke(new BasicStroke(l.getTaille(),
						BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
			}
			g2.drawLine(l.getxDepart(), l.getyDepart(), l.getxArrivee(),
						l.getyArrivee());

		}
	}
	

	

	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		curseur.setSourisX(e.getX());
		curseur.setSourisY(e.getY());
		Fenetre.getPanelInfo().repaint();
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
