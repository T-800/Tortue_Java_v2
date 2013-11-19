package commande;

import java.io.IOException;

public class If implements Commande {

	public If() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String[] parametres, boolean addToHistory)
			throws IOException {
		// TODO Auto-generated method stub
		// connecteur == != <= >= < >
		// Syntaxe IF _var1 == _var2 [cmd1;cmd2]
		System.out.println(ToString(parametres));

	}

	@Override
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
