package interfaceGraphique.sud;

import interfaceGraphique.Fenetre;
import interfaceGraphique.ouest.PanelOnglet;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.AutoCompletion;
import terminal.HistoriqueTerm;
import terminal.TableCommande;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PanelTerminal implements KeyListener{
    public JEditorPane jEditorPane;
    int indent = 0;
    int carfinal = 0,carcurrent = 0;
    private ListeHistorique listeHistorique;
    private AutoCompletion autoCompletion;
    private int compterTabulation = 0;
    boolean up = true;
    String rem = "";
    private HistoriqueTerm h = new HistoriqueTerm() ;
    private TableCommande tableCommande;
    private ListeVariables listeVariables;


	public PanelTerminal(TableCommande tableCommande,ListeHistorique listeHistorique, ListeFonctions listeFonctions, ListeVariables listeVariables) {
        this.jEditorPane = new JEditorPane();
        Document doc = this.jEditorPane.getDocument();
        if (doc instanceof PlainDocument) {
            doc.putProperty(PlainDocument.tabSizeAttribute, 2);
        }
        jEditorPane.setBackground(Color.black);
        jEditorPane.setForeground(Color.GREEN);

		Font police = new Font("Arial", Font.BOLD, 14);
        jEditorPane.setFont(police);
        jEditorPane.addKeyListener(this);


        Action anAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {}
        };
        jEditorPane.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"ENTRER");
        jEditorPane.getInputMap().put(KeyStroke.getKeyStroke("TAB"),"TAB");
        jEditorPane.getActionMap().put("ENTER",anAction);
        jEditorPane.getActionMap().put("TAB",anAction);



        this.listeHistorique = listeHistorique;
        this.autoCompletion = new AutoCompletion(listeFonctions,listeVariables);
        this.tableCommande = tableCommande;
        this.listeVariables = listeVariables;

	}







	@Override
	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyChar();
        String keyboard = jEditorPane.getText();
        //carcurrent = jEditorPane.getCaretPosition();
        if(!(e.getKeyCode() == KeyEvent.VK_TAB)) compterTabulation = 0;


        // TODO : pour les commandes sur plusieurs lignes UP/DOWN ne remplace que la ligne ou il y a le curseur
        if ((e.getKeyCode() == KeyEvent.VK_UP)
                && ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)) {
            String aff = h.getPrev();
            if (up && !keyboard.trim().equalsIgnoreCase("")){
                rem = keyboard;
                carcurrent = this.jEditorPane.getCaretPosition();
                up= false;
            }
            this.jEditorPane.setText(aff);
        }

        else if ((e.getKeyCode() == KeyEvent.VK_DOWN)
                && ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)) {


            String s = "";
            s = h.getNext();
            if(s.equalsIgnoreCase("")){
                this.jEditorPane.setText(rem);
                if(!rem.trim().equalsIgnoreCase(""))this.jEditorPane.setCaretPosition(carcurrent);
            }
            else {
                this.jEditorPane.setText(s);
                up=true;
            }
        }

        switch (keyCode){

            case KeyEvent.VK_ENTER:
                carcurrent = jEditorPane.getCaretPosition();
                if(keyboard.trim().length() == 0) return;

                //Si on entre dans un block
                if (keyboard.charAt(jEditorPane.getCaretPosition()-1) == '['){
                    indent++;
                    String sub1 = keyboard.substring(0,carcurrent);
                    String sub2 = keyboard.substring(carcurrent);
                    sub1+="\n";
                    for (int i= 0;i<= indent;i++)sub1+="\t";
                    carfinal = sub1.length()-1;
                    sub1+="\n";
                    for (int i= 0;i< indent-1;i++)sub1+="\t";
                    sub1+="]";
                    jEditorPane.setText(sub1+sub2);
                    jEditorPane.setCaretPosition(carfinal);

                }
                else { //sinon
                    if (jEditorPane.getCaretPosition() == jEditorPane.getText().length()){ // si je suis a la fin de la saisie
                        keyboard = keyboard.trim();
                        keyboard = keyboard.replace("\n","");
                        keyboard = keyboard.replace("\t","");
                        tableCommande.executerCommande(keyboard,listeVariables);

                        PanelOnglet.repaintOnglet();
                        Fenetre.getPanelDessin().repaint();
                        Fenetre.getPanelInfo().repaint();
                        up = true;
                        rem = "";
                        //listeHistorique.printHist();
                        h.addToList(keyboard);
                        jEditorPane.setText("");
                        jEditorPane.setCaretPosition(0);
                    }
                    else {//sinon saut de ligne dans l'indentation
                        String sub1 = keyboard.substring(0,carcurrent);
                        String sub2 = keyboard.substring(carcurrent);
                        sub1+="\n";
                        for (int i= 0;i<= indent;i++)sub1+="\t";
                        carfinal = sub1.length()-1;
                        jEditorPane.setText(sub1+sub2);
                        jEditorPane.setCaretPosition(carfinal);
                    }
                }
                break;
            case KeyEvent.VK_TAB:
                carcurrent = jEditorPane.getCaretPosition();
                this.jEditorPane.replaceSelection("");
                carcurrent = jEditorPane.getCaretPosition();
                keyboard = jEditorPane.getText();
                /*
                * On génere la liste pour l'autocompletion*/
                ArrayList<String> list = autoCompletion.findMatch(this.jEditorPane.getCaretPosition(), keyboard);

                /*
                * on decoupe l'entrée clavier en deux au niveau du cuseur*/
                String sub1 = keyboard.substring(0,carcurrent);
                String sub2 = keyboard.substring(carcurrent);
                /*
                * On récupere la compation du mot en foction du compteur et de la liste */
                if(list==null)return;
                String complete = list.get(compterTabulation).substring(autoCompletion.sizeWord(carcurrent, keyboard));
                /*
                * on affiche dans le JtextField le texte de depart avec la completion qui est surligné*/
                this.jEditorPane.setText(sub1 + complete + sub2);
                this.jEditorPane.select(sub1.length(), sub1.length() + complete.length());

                /*
                * on incrémente le compteur avec comme modulo le la taille de la liste*/
                compterTabulation++;
                compterTabulation %=list.size();
                break;

        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
