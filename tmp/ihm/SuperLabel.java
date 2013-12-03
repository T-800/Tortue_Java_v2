package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SuperLabel implements ActionListener {

	private JButton plus;
	private JButton moins;
	int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public SuperLabel() {
		plus = new JButton("+");
		moins = new JButton("-");
		plus.addActionListener(new ActionListenPlus());
		moins.addActionListener(this);
	}

	public JButton getPlus() {
		return plus;
	}

	public JButton getMoins() {
		return moins;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		i = 1;
		Fenetre.refresh();
	}

	public class ActionListenPlus implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			i = 2;
			Fenetre.refresh();
		}

	}

}
