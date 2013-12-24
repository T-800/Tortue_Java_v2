package interfaceGraphique;

import com.intellij.ui.components.JBScrollPane;
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		jDessin =new PanelDessin(curseur,commandeListe,historiqueListe,table);
        PanelTerminal jTerminal = new PanelTerminal(table,historiqueListe, fonctionsListe, variableListe);
        PanelOnglet jOnglet = new PanelOnglet(historiqueListe, fonctionsListe, variableListe);
		jInfos = new PanelInfo(curseur,historiqueListe,table);
		jInfos.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		jDessin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JBScrollPane(jTerminal.jEditorPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		content.add(jDessin, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(300, 70));

        content.add(scrollPane, BorderLayout.SOUTH);
		content.add(jOnglet, BorderLayout.EAST);
		content.add(jInfos, BorderLayout.NORTH);
		
		this.setContentPane(content);
		setVisible(true);
		jTerminal.jEditorPane.requestFocus(); // Donne le focus au terminal
		curseur.setPos(getCenterDessin());
        /*this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String error = table.executerCommande("NEW");
                System.out.print("efgrh");
                if (!error.equalsIgnoreCase("cancel")) {
                    System.exit(0);
                }

            }
        });  */
	}

    public static int[] getCenterDessin(){
        return new int[]{getPanelDessin().getSize().width/2, getPanelDessin().getSize().height/2};
	}
	public static int[] getMaxDessin(){
        return new int[]{getPanelDessin().getSize().width, getPanelDessin().getSize().height};
	}
	
	public static PanelInfo getPanelInfo(){
		return jInfos;
	}
	
	public static PanelDessin getPanelDessin(){
		return jDessin;
	}
}
