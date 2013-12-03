package ihm;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class jPanelParametres extends JPanel {
	public jPanelParametres() {
		this.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(Fenetre.largeur - 12,
				Fenetre.hauteur / 100 * 2));
	}
}
