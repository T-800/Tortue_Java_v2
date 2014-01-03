package interfaceGraphique;

import com.intellij.ui.components.JBScrollPane;
import dessin.Curseur;
import interfaceGraphique.ouest.PanelOuest;
import interfaceGraphique.sud.PanelInfo;
import interfaceGraphique.sud.PanelSud;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Fenetre Principal
 * @author renaud
 *
 */
public class Fenetre extends JFrame{

    private static PanelOuest jOuest;
    private static PanelDessin jDessin;
    private static PanelSud panelSud;


	private static TableCommande table;
	private Curseur curseur;

	private ListeCommande commandeListe;
	private ListeHistorique historiqueListe;
	private ListeFonctions fonctionsListe;
	private static ListeVariables variableListe;






    private JMenuBar menuBar;
    private JMenu fichier = new JMenu("Fichier");
    private JMenu option = new JMenu("Option");
    private JMenu save = new JMenu("Save");
    private JMenu h = new JMenu("?");
    private JMenuItem nouveau = new JMenuItem("Nouveau");
    private JMenuItem ouvrir = new JMenuItem("Ouvrir");
    private JMenuItem quitter = new JMenuItem("Quitter");
    private JMenuItem saveAll = new JMenuItem("Save All");
    private JMenuItem saveTxt = new JMenuItem("Save texte");
    private JMenuItem saveJpg = new JMenuItem("Save Jpg");

    private JMenuItem help = new JMenuItem("Help");
    private JMenuItem about = new JMenuItem("À Propos");
	/**
	 * Constructeur de la fenetre initialise 
	 */
	public Fenetre() {
		initialisationComposant();
		initialisation();
	}
	
	private void initialisationComposant(){
		this.commandeListe = new ListeCommande();
         this.curseur = new Curseur(0,0);
		this.fonctionsListe = new ListeFonctions();
		this.variableListe = new ListeVariables();
		this.curseur = new Curseur(0, 0);
		this.historiqueListe = new ListeHistorique();
		this.table = new TableCommande(curseur,commandeListe,fonctionsListe,historiqueListe);
	}
	
	
	private void initialisation(){
		this.setTitle("TortueGenial");
		ImageIcon logo = new ImageIcon("./Images/tortue_logo.png");
		this.setIconImage(logo.getImage());
		setSize(940,600); 
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jOuest = new PanelOuest(curseur,historiqueListe,table,fonctionsListe,variableListe);

        jDessin =new PanelDessin(curseur,commandeListe,historiqueListe,table);
        panelSud = new PanelSud(curseur,table,historiqueListe,fonctionsListe,variableListe);

        JScrollPane scrollPaneDessin = new JBScrollPane(jDessin,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        menuBar = new JMenuBar();


        this.save.add(saveAll);
        this.save.add(saveTxt);
        this.save.add(saveJpg);

        nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        saveAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        saveTxt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        saveJpg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));

        nouveau.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                historiqueListe.addToList("new","");
                table.executerCommande("new",variableListe);
            }
        });
        ouvrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                historiqueListe.addToList("open", "");
                table.executerCommande("open",variableListe);
            }
        });
        saveAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                historiqueListe.addToList("save","");
                table.executerCommande("save",variableListe);
            }
        });
        help.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                historiqueListe.addToList("help","");
                table.executerCommande("help",variableListe);
            }
        });
        about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame fenetre = new JFrame();

                 String txt = "<font color=#FF0000 size=6>À Propos :</font><br> Tortue Génial <br><font color=#000000 size=5>ADEQUIN Renaud<br>" +
                         "GHANDRI Ahmed<br>" +
                         "LIM Steffie<br>" +
                         "YOUNG Johnathan</font>";
                 fenetre.setSize(275, 200);

                JEditorPane cont = new JEditorPane();
                cont.setEditable(false);
                cont.setContentType("text/html");
                cont.setText(txt);


                fenetre.getContentPane().add(cont);
                fenetre.setVisible(true);
            }
        });
        quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });

        this.fichier.add(nouveau);
        this.fichier.add(ouvrir);
        this.fichier.add(save);
        this.fichier.addSeparator();
        this.fichier.add(quitter);
        this.h.add(help);
        this.h.addSeparator();
        this.h.add(about);


        this.menuBar.add(fichier);
        this.menuBar.add(option);
        this.menuBar.add(h);

        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.add(jOuest,BorderLayout.WEST);
        this.add(scrollPaneDessin,BorderLayout.CENTER);
        this.add(panelSud, BorderLayout.SOUTH);



        setVisible(true);
        panelSud.panelTerminal.jEditorPane.requestFocus(); // Donne le focus au terminal
        curseur.setPos(getCenterDessin());

	}

    public static int[] getCenterDessin(){
        return new int[]{getPanelDessin().getSize().width/2, getPanelDessin().getSize().height/2};
	}
	public static int[] getMaxDessin(){
        return new int[]{getPanelDessin().getSize().width, getPanelDessin().getSize().height};
	}

	public static PanelInfo getPanelInfo(){
		return panelSud.panelInfo;
	}
	
	public static PanelDessin getPanelDessin(){
		return jDessin;
	}
}
