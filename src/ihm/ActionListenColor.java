package ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class ActionListenColor implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Color color = JColorChooser.showDialog(null, "Choose a color",
				Fenetre.jButtonCouleur.getBackground());
		Fenetre.jButtonCouleur.setBackground(color);
		Fenetre.Curseur.setCouleur(color);
		Fenetre.jPanelDessin.repaint();
	}

}
