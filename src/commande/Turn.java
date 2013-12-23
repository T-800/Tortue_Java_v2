package commande;

import algo.Convert;
import algo.Verification;
import dessin.Curseur;
import liste.ListeVariables;
import liste.ListeVariables.ObjetVariables;

public class Turn extends Commande {
	
	Curseur curseur;
	ListeVariables listeVariables;
	
	public Turn(Curseur curseur,ListeVariables listeVariables){
		this.curseur = curseur;
		this.listeVariables = listeVariables;
	}

	@Override
	public String execute(String[] commande,ListeVariables listeVariables ){
		if(commande.length!=2)return "1";
		int valeur = 0;
        String s = commande[1];
		switch(commande[1].charAt(0)){
			case '+' :
			case '-' :
			case '*' :
			case '/' :
				s ="("+curseur.getD()+" "+commande[1].charAt(0)+" "+  commande[1].substring(1)+")";
		}
        if(commande[1].toLowerCase().equals("random")){
            valeur = 0 + (int)(Math.random()*(3600));
        }
        else {
            try{
                s = Convert.convertArg(s,listeVariables);
                valeur = Integer.parseInt(s);
            }catch(NumberFormatException e1){
                return "Erreur "+commande[1]+" n'est pas un nombre";
            }
        }
		curseur.setD(valeur%360);
		return "";
		

	}
}