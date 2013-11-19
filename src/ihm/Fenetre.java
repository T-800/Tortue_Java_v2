package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import objetQuiDessine.CurseurQuiDessine;

import commande.HashTable;
import commande.New;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	static GraphicsEnvironment ge = GraphicsEnvironment
			.getLocalGraphicsEnvironment();
	static Rectangle bounds = ge.getMaximumWindowBounds();
	public static int hauteur = (int) bounds.getHeight()-5;
	public static int largeur = (int) bounds.getWidth()-5;

	static CurseurQuiDessine Curseur;
	public static Dessin jPanelDessin;
	public static ihm.PanelHistory jTextAreaHistory;
	private JTextField jInviteDeCommande;
	private static JLabel jLabelTextCurseur;
	private static JLabel jLabelTextSouris;
	private static JLabel jLabelTextPensize;
	private static JLabel jLabelTextDessine;
	private static JLabel jLabelTextRemember;
	private static SuperLabel s1;
	public static JButton jButtonCouleur;
	public static jPanelParametres JPanelParametres;

	public static void Infos() {
		char degre = '\u00B0';
		int x = Curseur.getAbscisse(), y = Curseur.getOrdonnee();
		if (x > jPanelDessin.getWidth() / 2) {
			x -= jPanelDessin.getWidth() / 2;
		} else {
			x = -jPanelDessin.getWidth() / 2 + x;
		}
		if (y < jPanelDessin.getHeight() / 2) {
			y = jPanelDessin.getHeight() / 2 - y;
		} else {
			y = -(y - jPanelDessin.getHeight() / 2);
		}
		jLabelTextCurseur.setText("(" + x + ";"
				+ y + ")");
		jLabelTextSouris.setText("("
				+ String.format("%04d", (int) jPanelDessin.getxSouris()) + ";"
				+ String.format("%04d", (int) jPanelDessin.getySouris()) + "|"
				+ String.format("%03d", Curseur.getDirection()) + degre + ")");

		if (s1.getI() == 2) {
			
			Curseur.setPensize(Curseur.getPensize() + 1);
			try {
				HashTable.hgj("PENSIZE "+Curseur.getPensize(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s1.setI(3);
		} else if (s1.getI() == 1) {
			if (Curseur.getPensize() == 1) {

			} else {
				Curseur.setPensize(Curseur.getPensize() - 1);
				try {
					HashTable.hgj("PENSIZE "+Curseur.getPensize(), true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s1.setI(3);
			}
		} else {

		}
		jLabelTextPensize.setText(Curseur.getPensize() + "");
		if (Curseur.getPendown()) {
			jLabelTextDessine.setText("oui");
		} else {
			jLabelTextDessine.setText("non");
		}
		
		int xR = Curseur.getRememberAbscisse(), yR = Curseur.getRememberOrdonnee();
		if (xR > jPanelDessin.getWidth() / 2) {
			xR -= jPanelDessin.getWidth() / 2;
		} else {
			xR = -jPanelDessin.getWidth() / 2 + xR;
		}
		if (yR < jPanelDessin.getHeight() / 2) {
			yR = jPanelDessin.getHeight() / 2 - yR;
		} else {
			yR = -(yR - jPanelDessin.getHeight() / 2);
		}
		jLabelTextRemember.setText("("
				+ String.format("%04d", xR) + ";"
				+ String.format("%04d", yR) + "|"
				+ String.format("%03d", Curseur.getRememberDirection()) + degre
				+ ")");
	}

	public static void refresh() {
		Infos();
	}

	public Fenetre() {
		// this.setUndecorated(true);

		this.setResizable(false);
		if(hauteur==768 && largeur==1360)
		{
			this.setSize(largeur -5, hauteur-5);
			
		}		
		else if(hauteur==768 && largeur==1280){
			this.setSize(largeur -5, hauteur-5);
			
		}
		else if(hauteur==720 && largeur==1280){
			this.setSize(largeur -15, hauteur-30);
		}
		else if(hauteur==768 && largeur==1024){
			this.setSize(largeur -30, hauteur-30);
		}
		else if(hauteur==600 && largeur==800){
			this.setSize(largeur -5, hauteur-5);
		}
		else{
			this.setSize(largeur -30, hauteur-15);
		}
		this.setTitle("TortueGenial");
		ImageIcon img = new ImageIcon("../Image/logo.gif");
		this.setIconImage(img.getImage());
		this.setSize(largeur, hauteur);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		jTextAreaHistory = new PanelHistory();
		jPanelDessin = new Dessin(JPanelParametres);
		jInviteDeCommande = new jInviteDeCommande(jTextAreaHistory,
				jPanelDessin);
		final JScrollPane scroller = new JScrollPane(jTextAreaHistory,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(largeur / 5 + 30,
				hauteur / 100 * 95));
		Component blanc = Box
				.createRigidArea(new Dimension((largeur / 100), 0));
		JPanelParametres = new jPanelParametres();

		JLabel jLabelSouris = new jLabelSouris();
		JPanelParametres.add(jLabelSouris);
		jLabelTextSouris = new jTextLabelSouris();
		JPanelParametres.add(jLabelTextSouris);
		JPanelParametres.add(blanc);

		JLabel jLabelCurseur = new jLabelCurseur();
		jLabelTextCurseur = new jTextLabelCurseur();
		JPanelParametres.add(jLabelCurseur);
		JPanelParametres.add(jLabelTextCurseur);

		JPanelParametres.add(blanc);

		s1 = new SuperLabel();
		JLabel jLabelPensize = new JLabel("Pensize:");
		JPanelParametres.add(jLabelPensize);
		jLabelTextPensize = new JLabel();

		JPanelParametres.add(s1.getMoins());
		JPanelParametres.add(jLabelTextPensize);
		JPanelParametres.add(s1.getPlus());

		JPanelParametres.add(Box.createRigidArea(new Dimension(
				(largeur / 100 * 3), 0)));

		JLabel jLabelDessine = new JLabel("Dessine:");
		jLabelDessine.setPreferredSize(new Dimension(50,
				Fenetre.JPanelParametres.getHeight()));
		JPanelParametres.add(jLabelDessine);
		jLabelTextDessine = new JLabel();
		JPanelParametres.add(jLabelTextDessine);

		JPanelParametres.add(Box.createRigidArea(new Dimension(
				(largeur / 100 * 3), 0)));

		JLabel jLabelRemember = new jLabelRemember();
		jLabelTextRemember = new jTextLabelRemember();
		JPanelParametres.add(jLabelRemember);
		JPanelParametres.add(jLabelTextRemember);

		JPanelParametres.add(Box.createRigidArea(new Dimension(
				(largeur / 100 * 3), 0)));

		JLabel jLabelCouleur = new JLabel("Couleur:");
		JPanelParametres.add(jLabelCouleur);

		jButtonCouleur = new JButton();
		jButtonCouleur.setBackground(Color.black);
		jButtonCouleur.addActionListener(new ActionListenColor());
		jButtonCouleur.setPreferredSize(new Dimension(20, JPanelParametres
				.getHeight()));

		JPanelParametres.add(jButtonCouleur);
		JPanelParametres.add(Box.createHorizontalGlue());

		jPanelDessin = new Dessin(JPanelParametres);
		jInviteDeCommande = new jInviteDeCommande(jTextAreaHistory,
				jPanelDessin);

		// Le conteneur principal
		JPanel content = new JPanel();
		// Sa Taille
		content.setPreferredSize(new Dimension(largeur, hauteur));
		// Sa couleur
		content.setBackground(Color.WHITE);
		// On definit le layout manager
		content.setLayout(new GridBagLayout());

		// L'objet servant a positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();

		// On positionne la case de depart du composant
		gbc.gridx = 0;
		gbc.gridy = 0;
		// La taille en hauteur et en largeur
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		content.add(jPanelDessin, gbc);
		// ---------------------------------------------
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		content.add(scroller, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		content.add(JPanelParametres, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		content.add(jInviteDeCommande, gbc);

		// On ajoute le conteneur
		Curseur = new CurseurQuiDessine(JPanelParametres,
				jPanelDessin.getWidth() / 2, jPanelDessin.getHeight() / 2);
		this.setContentPane(content);
		this.setVisible(true);
		refresh();
		this.pack();
		Curseur = new CurseurQuiDessine(JPanelParametres,
				jPanelDessin.getWidth() / 2, jPanelDessin.getHeight() / 2);
		jPanelDessin.curseur = Curseur;
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				try {
					HashTable.hgj("NEW", false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (New.i != -1) {
					System.exit(0);
				}

			}
		});
	}
}
