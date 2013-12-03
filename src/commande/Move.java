package commande;

import algo.Convert;
import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeCommande;
import liste.ListeVariables;


public class Move extends Commande {
	
	private Curseur curseur;
	private ListeVariables listeVariables;
	private ListeCommande listeCommande;
	
	 public Move(Curseur curseur,ListeVariables listeVariables,ListeCommande listeCommande) {
		this.curseur = curseur;
		this.listeVariables = listeVariables;
		this.listeCommande = listeCommande;
	}
	
	@Override
	public String execute(String[] commande){
		if(commande.length < 2 || commande.length > 3)return "1";
		if(!curseur.isPenDown())return "Votre crayon est levé!!";
		
		int coor[] = new int[2];
		String argsString[] = new String[commande.length-1];
		for(int i = 0; i<argsString.length; i++){
			argsString[i] = Convert.convertArg(commande[i+1],listeVariables);
			System.out.println(argsString[i]);
		}

		
		
		if(argsString.length == 1){
			int distance;
			if(argsString[0].toLowerCase().equals("random")){
				distance = randomDistance();
			}
			else {
				try{
					distance = Integer.parseInt(argsString[0]);
				}catch(NumberFormatException e1){
					return "Erreur "+argsString[0]+" n'est pas un nombre";
				}
			}
			coor=calculeCoordArrive(distance);
		}
		else { // == 2
			for(int i = 0; i<argsString.length; i++){
				if(argsString[i].toLowerCase().equals("random")){
					if(i==0)
						coor[i] = 0 + (int)(Math.random()*(Fenetre.getMaxDessin()[0]));
					else 
						coor[i] = 0 + (int)(Math.random()*(Fenetre.getMaxDessin()[1]));
				}
				else {
					try{
						coor[i] = Integer.parseInt(argsString[i]);
						
					}catch(NumberFormatException e1){
						return "Erreur "+argsString[0]+" n'est pas un nombre";
					}
				}
			}
			coor[0] += (Fenetre.getCenterDessin()[0]);
			coor[1] = (Fenetre.getCenterDessin()[1])-coor[1];

		}


		if(!dans_Le_Dessin(coor)) return "Les coordonnées ne sont pas dans le dessin";
		
		listeCommande.addLigne(curseur.getX(), curseur.getY(), coor[0], coor[1], curseur.getCouleurCurseur(), curseur.getPenSize());
				
		curseur.setX(coor[0]);
		curseur.setY(coor[1]);
		return "";
	}

	private int [] calculeCoordArrive(int distance){
		int coor[] = new int[2];
		coor[0] = (int)(curseur.getX() + distance * Math.cos(Math.toRadians(curseur.getD())));
		coor[1] = (int)(curseur.getY() + distance * Math.sin(Math.toRadians(180 + curseur.getD())));
		return coor;
	}

	private boolean dans_Le_Dessin( int [] coor){
		if(coor[0] > Fenetre.getMaxDessin()[0] || coor[0] < 0)return false;
		if(coor[1] > Fenetre.getMaxDessin()[1] || coor[1] < 0)return false;
		return true;
	}

	private int randomDistance(){
		int max,dist, coor[];
		do{
			max = (int)Math.sqrt((Fenetre.getMaxDessin()[0]*Fenetre.getMaxDessin()[0])+(Fenetre.getMaxDessin()[1]*Fenetre.getMaxDessin()[1]));
			dist =  0 + (int)(Math.random()*max);
			coor = calculeCoordArrive(dist);
		}while(! dans_Le_Dessin(coor));
		return dist;
	}
}