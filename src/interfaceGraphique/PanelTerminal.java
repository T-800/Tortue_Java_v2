package interfaceGraphique;

import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.AutoCompletion;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PanelTerminal extends JTextField implements KeyListener{
	private ListeHistorique listeHistorique;
	private TableCommande table;
    private AutoCompletion autoCompletion;
    private ListeFonctions listeFonctions;
    private ListeVariables listeVariables;
    private String keyboard = "";
    private int pos = 0;

	public PanelTerminal(TableCommande table,ListeHistorique listeHistorique, ListeFonctions listeFonctions, ListeVariables listeVariables) {
		this.setBackground(Color.black);
		this.setForeground(Color.GREEN);
		this.setPreferredSize(new Dimension(0, 30));
		Font police = new Font("Arial", Font.BOLD, 14);
		setFont(police);
		this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);
		this.listeFonctions = listeFonctions;
        this.listeVariables = listeVariables;
		this.listeHistorique = listeHistorique;
        this.autoCompletion = new AutoCompletion(listeFonctions,listeVariables);
		this.table = table;
	}
	
	
	
	
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            keyboard = this.getText();
            keyboard = keyboard.trim();

			if (keyboard.equals("")) {
				return;
			}

			listeHistorique.addToList(keyboard,"");
            String error = table.executerCommande(this.getText());
            try {
                listeHistorique.setLastErrorMsg(error);
            }catch(ArrayIndexOutOfBoundsException ignored){

            }
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.setText(listeHistorique.getPrev());
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setText(listeHistorique.getNext());
		}
        else if ((e.getKeyCode() == KeyEvent.VK_TAB)
                && ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)) {
            keyboard = this.getText();
            keyboard = keyboard.trim();
            System.out.println("keyB : "+keyboard+" keyL : "+this.getCaretPosition());
            //System.out.print("resultat = "+this.getCaretPosition()+"*"+this.getText());
            //String s = autoCompletion.getLastWord(this.getCaretPosition(),this.getText());
            ArrayList<String> list = autoCompletion.findMatch(this.getCaretPosition(),keyboard);
            for (String s : list){
                listeHistorique.addToList(s,"",false);
            }


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
