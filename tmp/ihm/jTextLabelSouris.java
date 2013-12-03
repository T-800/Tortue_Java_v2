package ihm;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class jTextLabelSouris extends JLabel {

	public jTextLabelSouris() {
		super();
		this.setPreferredSize(new Dimension(120, Fenetre.JPanelParametres
				.getHeight()));
	}
}
