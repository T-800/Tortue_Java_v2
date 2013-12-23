package commande;

import algo.Convert;
import dessin.Curseur;
import interfaceGraphique.Fenetre;
import liste.ListeVariables;


public class Go extends Commande {

    private Curseur curseur;
    private ListeVariables listeVariables;

    public Go(Curseur curseur,ListeVariables listeVariables) {
        this.curseur = curseur;
        this.listeVariables = listeVariables;
    }

    @Override
    public String execute(String[] commande,ListeVariables listeVariables){
        System.out.println("GO");
        if(commande.length < 2 || commande.length > 3)return "1";

        int coor[] = new int[2];
        String argsString[] = new String[commande.length-1];
        for(int i = 0; i<argsString.length; i++){
            argsString[i] = Convert.convertArg(commande[i+1],listeVariables);
        }
        if(argsString.length == 2){
            for(int i = 0; i<argsString.length; i++){
                try{
                    coor[i] = Integer.parseInt(argsString[i]);

                }catch(NumberFormatException e1){
                    return "Erreur "+argsString[0]+" n'est pas un nombre";
                }
            }
            coor[0] += (Fenetre.getCenterDessin()[0]);
            coor[1] = (Fenetre.getCenterDessin()[1])-coor[1];
        }
        else {
            int distance;
            if(argsString[0].toLowerCase().equals("random")){
                coor = randomCoor();
            }
            else {
                try{
                    distance = Integer.parseInt(argsString[0]);
                }catch(NumberFormatException e1){
                    return "Erreur "+argsString[0]+" n'est pas un nombre";
                }

                coor=calculeCoordArrive(distance);
            }

        }


        curseur.setX(coor[0]);
        curseur.setY(coor[1]);

        return "";
    }

    private int [] calculeCoordArrive(int distance){
        int coor[] = new int[2];
        coor[0] = (int)Math.round(curseur.getX() + distance * Math.cos(Math.toRadians(curseur.getD())));
        coor[1] = (int)Math.round(curseur.getY() + distance * Math.sin(Math.toRadians(180 + curseur.getD())));





        return coor;
    }

    private boolean dans_Le_Dessin( int [] coor){
        return !(coor[0] > Fenetre.getMaxDessin()[0] || coor[0] < 0) && !(coor[1] > Fenetre.getMaxDessin()[1] || coor[1] < 0);
    }

    private int[] randomCoor(){
        int maxX,maxY;
        int coor[] = new int[2];
        do{
            maxX =Fenetre.getMaxDessin()[0];
            maxY =Fenetre.getMaxDessin()[1];
            coor[0] = (int)(Math.random()*maxX);
            coor[1] = (int)(Math.random()*maxY);
        }while(! dans_Le_Dessin(coor));
        return coor;
    }
}