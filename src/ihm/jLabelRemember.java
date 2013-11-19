package ihm;

import java.awt.Dimension;

import javax.swing.JLabel;

public class jLabelRemember extends JLabel {

	public jLabelRemember() {
		super("Remember:");
		this.setPreferredSize(new Dimension(70, Fenetre.JPanelParametres
				.getHeight()));
	}
}
