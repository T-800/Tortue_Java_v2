package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListen implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Fenetre.refresh();
	}

}