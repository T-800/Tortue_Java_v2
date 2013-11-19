package commande;

import ihm.Fenetre;

import java.io.IOException;

public class Variables implements Commande {

	public Variables() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void execute(String[] parametres, boolean addToHistory)
			throws IOException {
		// Syntaxe VAR _ht = 55
		// VAR _ht = _ht / 5
		// taille min 4; max =6

		if (parametres.length == 2) {
			Fenetre.jTextAreaHistory.addVar("****VARIABLES****");
			for (ObjetVariables v : variables) {
				Fenetre.jTextAreaHistory.addVar(v.getNom_Variable() + " = "
						+ v.valeur_Variable);
			}
		}

		Integer val;

		/*
		 * Ajouter variable info
		 */
		int existe = HashTable.variableExiste(parametres[1]);
		ObjetVariables var;
		switch (parametres.length) {
		case 4:

			if (HashTable.isVariable(parametres[3])) {
				int var2 = HashTable.variableExiste(parametres[3]);
				val = variables.get(var2).getValeur_Variable();
			} else {
				val = Integer.parseInt(parametres[3]);
			}

			var = new ObjetVariables(parametres[1], val.intValue());

			if (existe != -1) {

				variables.set(existe, var);
			} else {

				variables.add(var);
			}

			break;

		case 5:
			// val = Integer.parseInt(parametres[3]);
			if (HashTable.isVariable(parametres[4])) {
				int var2 = HashTable.variableExiste(parametres[4]);
				val = variables.get(var2).getValeur_Variable();
			} else {
				val = Integer.parseInt(parametres[4]);
			}

			var = new ObjetVariables(parametres[1], -val.intValue());

			if (existe != -1) {
				// var = new ObjetVariables(parametres[1], -val.intValue());
				variables.set(existe, var);
			} else {

				variables.add(var);
			}

			break;
		case 6:
			/*
			 * Opérateurs possible + - * /
			 */

			int valVar1 = 0;
			int valVar2 = 0;

			if (HashTable.isVariable(parametres[3])) {
				valVar1 = variables
						.get(HashTable.variableExiste(parametres[3]))
						.getValeur_Variable();
			} else
				valVar1 = Integer.parseInt(parametres[3]);

			if (HashTable.isVariable(parametres[5])) {
				valVar2 = variables
						.get(HashTable.variableExiste(parametres[5]))
						.getValeur_Variable();
			} else
				valVar2 = Integer.parseInt(parametres[5]);

			int resutat = 0;
			switch (parametres[4].charAt(0)) {
			case '-':
				resutat = valVar1 - valVar2;
				break;

			case '+':
				resutat = valVar1 + valVar2;
				break;

			case '*':
				resutat = valVar1 * valVar2;
				break;

			case '/':
				try {
					resutat = valVar1 / valVar2;
				} catch (ArithmeticException e1) {
					Fenetre.jTextAreaHistory.addError("/0, \n Tappez HELP VAR");
					return;
				}

				break;
			}

			var = new ObjetVariables(parametres[1], resutat);
			if (existe != -1) {

				variables.set(existe, var);
			} else {

				variables.add(var);
			}

			break;
		}

		for (ObjetVariables v : variables) {
			System.out.println("NOM VARIABLE : " + v.getNom_Variable()
					+ " VALEUR : " + v.getValeur_Variable());
		}
		System.out.println();
		System.out.println("**************");

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
		if (parametres.length == 2) {
			if (parametres[1].equalsIgnoreCase("info")) {

				return true;
			} else {
				Fenetre.jTextAreaHistory.addError("kjhbgvfc, \n Tappez HELP VAR");
				return false;
			}
		}
		if (parametres.length > 6 || parametres.length < 4) {
			Fenetre.jTextAreaHistory.addError("VAR mauvaise syntaxe pour plus d'information, \n Tappez HELP VAR");
			return false;
		}
		if (parametres[1].charAt(0) != '_') {
			Fenetre.jTextAreaHistory.addError("Les variable doivent toujour commencer par _ , \n pour plus d'information tappez HELP VAR");
			return false;
		}
		if (!parametres[2].equals("=")) {
			Fenetre.jTextAreaHistory.addError("VAR mauvaise syntaxe pour plus d'information, \n Tappez HELP VAR");
			return false;
		}

		if (parametres.length == 4) {
			if (HashTable.isVariable(parametres[3])
					&& HashTable.variableExiste(parametres[3]) == -1) {
				Fenetre.jTextAreaHistory.addError("La variable " + parametres[3]
						+ " , \n Tappez HELP VAR");
				return false;
			} else if (!HashTable.isVariable(parametres[3])) {
				try {
					Integer x = Integer.parseInt(parametres[3]);
				} catch (NumberFormatException e1) {
					Fenetre.jTextAreaHistory.addError("veuillez entrer un entier, \n Tappez HELP VAR");
					return false;
				}
			}
		}

		if (parametres.length == 5) {
			if (!parametres[3].equals("-")) {
				Fenetre.jTextAreaHistory.addError("VAR mauvaise syntaxe pour plus d'information, \n Tappez HELP VAR");
				return false;
			}
			if (HashTable.isVariable(parametres[4])
					&& HashTable.variableExiste(parametres[4]) == -1) {
				Fenetre.jTextAreaHistory.addError("VAR mauvaise syntaxe pour plus d'information, \n Tappez HELP VAR");
				return false;
			} else if (!HashTable.isVariable(parametres[4])) {
				try {
					Integer x = Integer.parseInt(parametres[4]);
				} catch (NumberFormatException e1) {
					Fenetre.jTextAreaHistory.addError("veuillez entrer un entier, \n Tappez HELP VAR");
					return false;
				}
			}
		}

		if (parametres.length == 6) {
			if (HashTable.isVariable(parametres[3])
					&& HashTable.variableExiste(parametres[3]) == -1) {
				Fenetre.jTextAreaHistory.addError("VAR mauvaise syntaxe pour plus d'information, \n Tappez HELP VAR");
				return false;
			} else if (!HashTable.isVariable(parametres[3])) {
				try {
					Integer x = Integer.parseInt(parametres[3]);
				} catch (NumberFormatException e1) {
					Fenetre.jTextAreaHistory.addError("veuillez entrer un entier, \n Tappez HELP VAR");
					return false;
				}
			}

			char op;
			if (parametres[4].length() > 1) {
				return false;
			} else
				op = parametres[4].charAt(0);
			if (op != '+' && op != '-' && op != '*' && op != '/') {
				Fenetre.jTextAreaHistory.addError("operateur non autorise, \n Tappez HELP VAR");
				return false;
			}
			if (HashTable.isVariable(parametres[5])
					&& HashTable.variableExiste(parametres[5]) == -1) {
				Fenetre.jTextAreaHistory.addError("VAR mauvaise syntaxe pour plus d'information, \n Tappez HELP VAR");
				return false;
			} else if (!HashTable.isVariable(parametres[5])) {
				try {
					Integer x = Integer.parseInt(parametres[5]);
				} catch (NumberFormatException e1) {
					Fenetre.jTextAreaHistory.addError("veuillez entrer un entier, \n Tappez HELP VAR");
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

	public class ObjetVariables {
		private String nom_Variable;
		private int valeur_Variable;

		public ObjetVariables(String nom_Variable, int valeur_Variable) {
			this.nom_Variable = nom_Variable;
			this.valeur_Variable = valeur_Variable;
		}

		public String getNom_Variable() {
			return nom_Variable;
		}

		public void setNom_Variable(String nom_Variable) {
			this.nom_Variable = nom_Variable;
		}

		public int getValeur_Variable() {
			return valeur_Variable;
		}

		public void setValeur_Variable(int valeur_Variable) {
			this.valeur_Variable = valeur_Variable;
		}
	}

}
