package interfaceGraphique;

import dessin.Curseur;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Fenetre Principal
 * @author renaud
 *
 */
public class Fenetre extends JFrame{
	
	private static PanelDessin jDessin;
	private PanelTerminal jTerminal;
	private static PanelOnglet jOnglet;
	private static PanelInfo jInfos;
	
	private TableCommande table;
	private Curseur curseur;

	private ListeCommande commandeListe;
	private ListeHistorique historiqueListe;
	private ListeFonctions fonctionsListe;
	private ListeVariables variableListe;
	/**
	 * Constructeur de la fenetre initialise 
	 */
	public Fenetre() {
		initialisationComposant();
		initialisation();
	}
	
	private void initialisationComposant(){
		this.commandeListe = new ListeCommande();

		this.fonctionsListe = new ListeFonctions();
		this.variableListe = new ListeVariables();
		this.curseur = new Curseur(0, 0);
		this.historiqueListe = new ListeHistorique();
		this.table = new TableCommande(curseur,commandeListe,fonctionsListe,variableListe,historiqueListe);
	}
	
	
	private void initialisation(){
		this.setTitle("TortueGenial");
		ImageIcon logo = new ImageIcon("./Images/tortue_logo.png");
		this.setIconImage(logo.getImage());
		setSize(940,600); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		jDessin =new PanelDessin(curseur,commandeListe,table);
		jTerminal = new PanelTerminal(table,historiqueListe,fonctionsListe,variableListe);
		jOnglet = new PanelOnglet(historiqueListe,fonctionsListe,variableListe);
		jInfos = new PanelInfo(curseur,historiqueListe,table);
		jInfos.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		jDessin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(jDessin, BorderLayout.CENTER);
		content.add(jTerminal, BorderLayout.SOUTH);
		content.add(jOnglet, BorderLayout.EAST);
		content.add(jInfos, BorderLayout.NORTH);
		
		this.setContentPane(content);
		setVisible(true);
		jTerminal.requestFocus(); // Donne le focus au terminal
		int t[] = {Fenetre.getPanelDessin().getSize().width/2,Fenetre.getPanelDessin().getSize().height/2,0};
		curseur.setPos(t);
	}
	
	public static int[] getCenterDessin(){
		int t[] = {Fenetre.getPanelDessin().getSize().width/2,Fenetre.getPanelDessin().getSize().height/2};
		return t;
	}
	public static int[] getMaxDessin(){
		int t[] = {Fenetre.getPanelDessin().getSize().width,Fenetre.getPanelDessin().getSize().height,0};
		return t;
	}
	
	public static PanelInfo getPanelInfo(){
		return jInfos;
	}
	
	public static PanelDessin getPanelDessin(){
		return jDessin;
	}
	
	public static PanelOnglet getPanelOnglet(){
		return jOnglet;
	}
}
