package ihm;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class jTextLabelRemember extends JLabel {

	public jTextLabelRemember() {
		super();
		this.setPreferredSize(new Dimension(100, Fenetre.JPanelParametres
				.getHeight()));
	}
}
