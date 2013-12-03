package ihm;

import history.HistoryTerminal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

import commande.Autocompletion;
import cmd.HashTable;

public class jInviteDeCommande extends JTextField {

	public HistoryTerminal historyTerminal;
	public HashTable table = new HashTable();

	public jInviteDeCommande(PanelHistory panelHistory, Dessin dessin) {
		this.setBackground(Color.black);

		this.setPreferredSize(new Dimension(Fenetre.largeur - 10, 30));

		this.setForeground(Color.white);
		historyTerminal = new HistoryTerminal();
		this.setFocusTraversalKeysEnabled(false);
		addKeyListener(new KeyListenTerminal(this));
	}

	public class KeyListenTerminal implements KeyListener {
		public jInviteDeCommande terminal;

		public KeyListenTerminal(jInviteDeCommande terminal) {
			this.terminal = terminal;

		}

		@Override
		public void keyPressed(KeyEvent e) {

			String keyboard;
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				terminal.historyTerminal.reset();

				keyboard = terminal.getText();
				keyboard = keyboard.trim();

				if (keyboard.equals("")) {
					return;
				}
				terminal.setText("");
				historyTerminal.addToHistory(keyboard);


				String panelHistoryMessage = table.executerCommande(keyboard);

				Fenetre.jTextAreaHistory.addToPanelHistory(keyboard.split(" " ,2 )[0].toUpperCase(),keyboard,panelHistoryMessage);


			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				terminal.setText(historyTerminal.showUp());
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				terminal.setText(historyTerminal.showDown());
			} else if ((e.getKeyCode() == KeyEvent.VK_TAB)) {
				historyTerminal.reset();
				keyboard = terminal.getText();
				keyboard = keyboard.trim();

				ArrayList<String> tab = Autocompletion.complete(keyboard);
				if (tab.size() > 1) {
					terminal.setText(tab.get(tab.size() - 1).trim());
					for (int i = 0; i < tab.size() - 1; i++) {
						Fenetre.jTextAreaHistory.addTexteIndent("- " + tab.get(i)
								+ "\n");
					}
				} else if (tab.size() == 1) {
					terminal.setText(tab.get(tab.size() - 1).trim());
				}

			} else if ((e.getKeyCode() == KeyEvent.VK_L)
					&& ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)) {
				Fenetre.jTextAreaHistory.clear();

			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
