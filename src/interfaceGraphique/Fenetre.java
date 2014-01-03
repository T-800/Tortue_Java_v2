package interfaceGraphique;

import com.intellij.ui.components.JBScrollPane;
import commande.Commande;
import dessin.Curseur;
import interfaceGraphique.ouest.PanelOnglet;
import interfaceGraphique.ouest.PanelOuest;
import interfaceGraphique.sud.PanelInfo;
import interfaceGraphique.sud.PanelSud;
import liste.ListeCommande;
import liste.ListeFonctions;
import liste.ListeHistorique;
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
    private JMenuItem pOuest = new JMenuItem("Afficher Panel");



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
		this.curseur = new Curseur(0, 0);
		this.historiqueListe = new ListeHistorique();
		table = new TableCommande(fonctionsListe,historiqueListe);
	}
	
	
	private void initialisation(){
		this.setTitle("TortueGenial");
		ImageIcon logo = new ImageIcon("./Images/tortue_logo.png");
		this.setIconImage(logo.getImage());
		setSize(940,600); 
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jOuest = new PanelOuest(curseur,historiqueListe,table,fonctionsListe);

        jDessin =new PanelDessin(curseur,commandeListe,table);
        Commande.setPanelDessin(jDessin);

        panelSud = new PanelSud(curseur,table,historiqueListe,fonctionsListe);

        JScrollPane scrollPaneDessin;


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
        pOuest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));

        nouveau.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                table.executerCommande("new");
                PanelOnglet.repaintOnglet();
            }
        });
        ouvrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                table.executerCommande("open");
                PanelOnglet.repaintOnglet();
            }
        });
        saveAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                table.executerCommande("save");
                PanelOnglet.repaintOnglet();
            }
        });
        help.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                table.executerCommande("help");
                PanelOnglet.repaintOnglet();
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
        pOuest.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                jOuest.setVisible(!jOuest.isVisible());
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
        this.option.add(pOuest);

        this.menuBar.add(fichier);
        this.menuBar.add(option);
        this.menuBar.add(h);

        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.add(jOuest,BorderLayout.WEST);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.DARK_GRAY);
        layeredPane.setPreferredSize(new Dimension(jDessin.getPreferredSize().width+10, jDessin.getPreferredSize().height+10));
        JPanel gray = new JPanel();
        gray.setBackground(Color.lightGray);
        layeredPane.add(jDessin, new Integer(1));
        jDessin.setOpaque(true);
        layeredPane.add(gray, new Integer(0));
        gray.setOpaque(true);
        gray.setBounds(0, 0, 40000, 400000);

        jDessin.setBounds(5, 5, jDessin.getPreferredSize().width, jDessin.getPreferredSize().height);
        scrollPaneDessin = new JBScrollPane(layeredPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneDessin.setBounds(0, 0, jDessin.getPreferredSize().width, jDessin.getPreferredSize().height);

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
