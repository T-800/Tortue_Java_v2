package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import objetQuiDessine.CurseurQuiDessine;

import cmd.HashTable;

import cmd.Move.Ligne;
import commande.Pensize.Size;

@SuppressWarnings("serial")
public class Dessin extends JPanel implements MouseMotionListener,
		MouseListener {
	public ArrayList<Object> cmd = new ArrayList<Object>();
	public CurseurQuiDessine curseur;
	public jPanelParametres JPanelParametres;
	private Color background;
	double ySouris,xSouris;
	int i = 0;

	public Dessin(jPanelParametres JPanelParametres) {
		addMouseMotionListener(this);
		addMouseListener(this);
		this.JPanelParametres = JPanelParametres;

		this.setOpaque(true);
		background = Color.white;
		this.setBackground(background);
		this.setPreferredSize(new Dimension((Fenetre.largeur / 100) * 80,
				Fenetre.hauteur / 100 * 95));
		this.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		curseur = new CurseurQuiDessine(JPanelParametres, this.getWidth() / 2,
				this.getHeight());

	}

	

	public double getySouris() {
		return ySouris;
	}

	public void setySouris(double ySouris) {
		this.ySouris = ySouris;
	}

	public double getxSouris() {
		return xSouris;
	}

	public void setxSouris(double xSouris) {
		this.xSouris = xSouris;
	}

	

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		// curseur.setCurseurImg(replaceImage(curseur.getCurseurImg(),90.0));

		Graphics2D g2 = (Graphics2D) g;
		// g2.drawImage(curseur.getCurseurImg(), curseur.getAbscisse(),
		// curseur.getOrdonnee(), this);
		g2.setColor(curseur.getCouleur());

		g2.setStroke(new BasicStroke(1));
		for (Object p : this.cmd) {
			if (p.getClass().equals(Ligne.class)) {
				Ligne l = (Ligne) p;
				g2.setColor(l.getColor());
				g2.drawLine(l.getxDepart(), l.getyDepart(), l.getxArrivee(),
						l.getyArrivee());

			}

			else if (p.getClass().equals(Size.class)) {
				Size s = (Size) p;
				g2.setStroke(new BasicStroke(s.getStrockeSize(),
						BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

			}
		}
		g2.setColor(curseur.getCouleur());
		if (curseur.isDrawCurs()) {
			g2.setColor(Color.DARK_GRAY);
			g2.fillPolygon(this.drawCursOmbre());
			g2.setColor(curseur.getCouleur());
			g2.fillPolygon(this.drawCurs());
		}
		Fenetre.JPanelParametres.repaint();

	}

	/*
	 * x = (int) Math.round(curseur.getAbscisse()+ dist*
	 * Math.cos(Math.toRadians(curseur.getDirection()))); y = (int)
	 * Math.round(curseur.getOrdonnee()+ dist* Math.sin(Math.toRadians(180 +
	 * curseur.getDirection())));
	 */

	private Polygon drawCurs() {

		int Ax, Ay, Bx, By, Cx, Cy;

		Ax = (int) Math.round(curseur.getAbscisse() + 15
				* Math.cos(Math.toRadians(curseur.getDirection())));
		Ay = (int) Math.round(curseur.getOrdonnee() + 15
				* Math.sin(Math.toRadians(180 + curseur.getDirection())));

		Bx = (int) Math.round(curseur.getAbscisse() + 5
				* Math.cos(Math.toRadians(curseur.getDirection() + 90)));
		By = (int) Math.round(curseur.getOrdonnee() + 5
				* Math.sin(Math.toRadians(180 + curseur.getDirection() + 90)));

		Cx = (int) Math.round(curseur.getAbscisse() + 5
				* Math.cos(Math.toRadians(curseur.getDirection() - 90)));
		;
		Cy = (int) Math.round(curseur.getOrdonnee() + 5
				* Math.sin(Math.toRadians(180 + curseur.getDirection() - 90)));

		int[] xPoints = { Ax, Bx, Cx };
		int[] yPoints = { Ay, By, Cy };
		Polygon curs = new Polygon(xPoints, yPoints, 3);

		return curs;
	}

	private Polygon drawCursOmbre() {

		int Ax, Ay, Bx, By, Cx, Cy;

		Ax = (int) Math.round(curseur.getAbscisse() + 15
				* Math.cos(Math.toRadians(curseur.getDirection())));
		Ay = (int) Math.round(curseur.getOrdonnee() + 15
				* Math.sin(Math.toRadians(180 + curseur.getDirection())));

		Bx = (int) Math.round(curseur.getAbscisse() + 5
				* Math.cos(Math.toRadians(curseur.getDirection() + 90)));
		By = (int) Math.round(curseur.getOrdonnee() + 5
				* Math.sin(Math.toRadians(180 + curseur.getDirection() + 90)));

		Cx = (int) Math.round(curseur.getAbscisse() + 5
				* Math.cos(Math.toRadians(curseur.getDirection() - 90)));
		;
		Cy = (int) Math.round(curseur.getOrdonnee() + 5
				* Math.sin(Math.toRadians(180 + curseur.getDirection() - 90)));

		int[] xPoints = { Ax + 1, Bx + 1, Cx + 1 };
		int[] yPoints = { Ay + 1, By + 1, Cy + 1 };
		Polygon curs = new Polygon(xPoints, yPoints, 3);

		return curs;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
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
		xSouris = x;
		ySouris = y;
		Fenetre.JPanelParametres.repaint();
		Fenetre.refresh();

	}

	public boolean egaux(Ligne l1, Ligne l2) {
		if (l1.getxArrivee() != l2.getxArrivee())
			return false;
		if (l1.getxDepart() != l2.getxDepart())
			return false;
		if (l1.getyArrivee() != l2.getyArrivee())
			return false;
		if (l1.getyDepart() != l2.getyDepart())
			return false;
		if (!(l1.getColor().getRGB() != l2.getColor().getRGB()))
			return false;
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
		table.executerCommande("GO " + x + " " + y);
		

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
