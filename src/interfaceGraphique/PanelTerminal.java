package interfaceGraphique;

import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.AutoCompletion;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PanelTerminal extends JTextField implements KeyListener{
	private ListeHistorique listeHistorique;
	private TableCommande table;
    private AutoCompletion autoCompletion;
    private int compterTabulation = 0;

	public PanelTerminal(TableCommande table,ListeHistorique listeHistorique, ListeFonctions listeFonctions, ListeVariables listeVariables) {
		this.setBackground(Color.black);
		this.setForeground(Color.GREEN);
		this.setPreferredSize(new Dimension(0, 30));
		Font police = new Font("Arial", Font.BOLD, 14);
		setFont(police);
		this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);
		this.listeHistorique = listeHistorique;
        this.autoCompletion = new AutoCompletion(listeFonctions,listeVariables);
		this.table = table;
	}
	
	
	
	
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyboard = this.getText();
            keyboard = keyboard.trim();

			if (keyboard.equals("")) {
				return;
			}

			listeHistorique.addToList(keyboard,"");
            String error = table.executerCommande(keyboard);
            try {
                listeHistorique.setLastErrorMsg(error);
            }catch(ArrayIndexOutOfBoundsException ignored){

            }

			this.setText("");
            for(ListeHistorique.Historique s : listeHistorique.getliste()){
                System.out.println(s.getCommande()+" "+s.getError_msg());
            }
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.setText(listeHistorique.getPrev());
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setText(listeHistorique.getNext());
		}
        else if ((e.getKeyCode() == KeyEvent.VK_TAB)) {
            this.replaceSelection("");
            String keyboard = this.getText();
            if(keyboard.equals(""))return;
            /*
            * On génere la liste pour l'autocompletion*/
            ArrayList<String> list = autoCompletion.findMatch(this.getCaretPosition(), keyboard);

            /*
            * on decoupe l'entrée clavier en deux au niveau du cuseur*/
            String sub1= keyboard.substring(0, this.getCaretPosition());
            String sub2= keyboard.substring(this.getCaretPosition());
            /*
            * On récupere la compation du mot en foction du compteur et de la liste */
            if(list==null)return;
             String complete = list.get(compterTabulation).substring(autoCompletion.sizeWord(getCaretPosition(), keyboard));
            /*
            * on affiche dans le JtextField le texte de depart avec la completion qui est surligné*/
            this.setText(sub1+complete+sub2);
            this.select(sub1.length(),sub1.length()+complete.length());

            /*
            * on incrémente le compteur avec comme modulo le la taille de la liste*/
            compterTabulation++;
            compterTabulation %=list.size();

        }
        else {
            if(!(e.getKeyCode() == KeyEvent.VK_TAB)) compterTabulation = 0;
        }
        PanelOnglet.repaintOnglet();
        Fenetre.getPanelDessin().repaint();
        Fenetre.getPanelInfo().repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
