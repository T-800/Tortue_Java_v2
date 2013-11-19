package commande;

import ihm.Fenetre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Repeat implements Commande {

	/**
	 * La syntaxe de repeat est REPEAT nbFois [cmd1;cmd2;cmd3]
	 */
	static int compteur = 0;
	static int CDICompteur = 0;

	public Repeat() {

	}

	@Override
	public void execute(String[] parametres, boolean addToHistory) {

		compteur++;

		Object[] tab = convertTo(parametres);
		String f = (String) tab[0];
		Integer x;
		if (f.equalsIgnoreCase("random")) {
			x = random();
		} else if(HashTable.isVariable(f)){
			x =variables.get(HashTable.variableExiste(parametres[1]))
			.getValeur_Variable();
		}else {

			x = Integer.parseInt(f);
			/*
			 * if(x.intValue() >500){ x = 500; }
			 */
		}

		for (int i = 0; i < x.intValue(); i++) {
			for (int j = 1; j < tab.length; j++) {
				try {

					HashTable.hgj((String) tab[j], false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		compteur--;
		if (compteur == 0) {
			Move.resetMove();
		}
		if (addToHistory)
			history.addToHistory(ToString(parametres));

	}

	public Object[] convertTo(String[] parametres) {
		ArrayList<String> e = new ArrayList<String>();
		int first = 0, compteur = 0;

		for (int i = 0; i < parametres[2].length(); i++) {
			// System.out.println("gg"+compteur);
			if (compteur == 0 && parametres[2].charAt(i) == ';') {
				e.add(parametres[2].substring(first, i));
				first = i + 1;
			}
			if (parametres[2].charAt(i) == '[')
				compteur++;
			if (parametres[2].charAt(i) == ']')
				compteur--;
		}
		e.add(parametres[2].substring(first, parametres[2].length()));

		for (int i = 0; i < e.size(); i++) {
			String s = e.get(i);
			s = s.trim();
			e.set(i, s.trim());

		}
		for (String s : e) {
			
			if (s.equals(""))
				e.remove(s);
		}

		Object[] tab = new Object[e.size() + 1];

		tab[0] = parametres[1];
		for (int i = 1; i < tab.length; i++) {
			tab[i] = e.get(i - 1);
		}

		return tab;
	}

	@Override
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			if (i == 2)
				s += "[";
			s += tab[i] + " ";
		}
		return s + "]";
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		// TODO Auto-generated method stub
		CDICompteur++;

		Object[] tab = convertTo(parametres);
		String f = (String) tab[0];
		Integer x;
		if (f.equalsIgnoreCase("random")) {
			x = random();
		}else if(HashTable.isVariable(f)){
			x =variables.get(HashTable.variableExiste(parametres[1]))
			.getValeur_Variable();
		}
		else {

			x = Integer.parseInt(f);
			if(x.intValue() >400){
				Fenetre.jTextAreaHistory.addError("dépasser les 400 repeat");
				return false;
			}
			
		}
		
		for (int j = 1; j < tab.length; j++) {
			String [] s = HashTable.splitSpace((String)tab[j]);
			if(!HashTable.hashCommande.containsKey(s[0].toUpperCase())){
				Fenetre.jTextAreaHistory.addError("La commande "+s[0]+" dans votre repeat n'éxiste pas");
				return false;
			}
		}

		for (int i = 0; i < x.intValue(); i++) {
			for (int j = 1; j < tab.length; j++) {
				String [] s = HashTable.splitSpace((String)tab[j]);
				s[0] = s[0].toUpperCase();
				
				if(!HashTable.itsOK(s)){
					Fenetre.jTextAreaHistory.addError("Votre repeat ne pourra pas etre éxécuté jusqu'a la fin");
					return false;
				
				}
				
			}

		}
		CDICompteur--;
		if (CDICompteur == 0) {
			Move.resetMove();
		}
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		Random nombre = new Random();
		int valeur = nombre.nextInt(500);

		return valeur;
	}

}
