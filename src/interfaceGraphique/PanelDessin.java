package interfaceGraphique;

import dessin.Curseur;
import liste.ListeCommande;
import liste.ListeCommande.Ligne;
import liste.ListeHistorique;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelDessin extends JPanel implements MouseListener, MouseMotionListener{
	
	private Curseur curseur;
	private ListeCommande listeCommande;
    private  ListeHistorique listeHistorique;
    private TableCommande tableCommande;

    public PanelDessin(Curseur curseur, ListeCommande listeCommande, ListeHistorique listeHistorique,TableCommande tableCommande) {
		this.curseur = curseur;
		this.listeCommande = listeCommande;
        this.listeHistorique = listeHistorique;
        this.tableCommande = tableCommande;
		int t[] = {this.getSize().width,this.getSize().height,55};
		curseur.setPos(t);
		this.setSize(new Dimension(200, 200));
		
		addMouseMotionListener(this);
        addMouseListener(this);
		setBackground(curseur.getCouleurBg());
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
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
        this.setBackground(curseur.getCouleurBg());
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
        System.out.println("MOUSE");

        int x = e.getX(), y = e.getY();
        if (x > this.getWidth() / 2) {
            x -= this.getWidth() / 2;
        } else {
            x = -this.getWidth() / 2 + x;
        }
        if (y < this.getHeight() / 2) {
            y = this.getHeight() / 2 - y;
        } else {
            y = -(y - this.getHeight() / 2);
        }

        listeHistorique.addToList("GO "+x+" "+y
                ,tableCommande.executerCommande("GO "+x+" "+y));
        PanelOnglet.repaintOnglet();
        Fenetre.getPanelDessin().repaint();
        Fenetre.getPanelInfo().repaint();
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	
}
