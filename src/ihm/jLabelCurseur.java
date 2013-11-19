package ihm;

import java.awt.Dimension;

import javax.swing.JLabel;

public class jLabelCurseur extends JLabel {

	public jLabelCurseur() {
		super("Curseur:");
		this.setPreferredSize(new Dimension(50, Fenetre.JPanelParametres
				.getHeight()));
	}
}
