package interfaceGraphique;

import liste.ListeHistorique;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelTerminal extends JTextField implements KeyListener{
	private ListeHistorique listeHistorique;
	private TableCommande table;

	public PanelTerminal(TableCommande table,ListeHistorique listeHistorique) {
		this.setBackground(Color.black);
		this.setForeground(Color.GREEN);
		this.setPreferredSize(new Dimension(0, 30));
		Font police = new Font("Arial", Font.BOLD, 14);
		setFont(police);
		this.addKeyListener(this);
		
		this.listeHistorique = listeHistorique;
		this.table = table;
	}
	
	
	
	
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		String keyboard;
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			keyboard = this.getText();
			keyboard = keyboard.trim();

			if (keyboard.equals("")) {
				return;
			}
			listeHistorique.addToList(keyboard,table.executerCommande(this.getText()));
			PanelOnglet.repaintOnglet();
			Fenetre.getPanelDessin().repaint();
			Fenetre.getPanelInfo().repaint();
			this.setText("");
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			//this.setText(historyTerminal.getPrev());
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			//this.setText(historyTerminal.getNext());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
