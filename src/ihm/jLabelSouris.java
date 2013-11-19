package ihm;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class jLabelSouris extends JLabel {

	public jLabelSouris() {
		super("Souris:");
		this.setPreferredSize(new Dimension(40, Fenetre.JPanelParametres
				.getHeight()));
	}
}
