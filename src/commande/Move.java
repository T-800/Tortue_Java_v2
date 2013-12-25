package commande;

import algo.Convert;
import interfaceGraphique.Fenetre;
import liste.ListeVariables;


public class Move extends Commande {


    @Override
    public boolean execute(String commande,ListeVariables listeVariables){
        String[] param = getCmdParam(commande);
        Convert.printParam(param);
        if(param.length < 2 || param.length > 3){
            getListeHistorique().addToList(commande,ErrorToString("1",commande));
            return false;
        }
        if(!getCurseur().isPenDown()){
            getListeHistorique().addToList(commande,"Votre crayon est levé!!");
            return false;
        }

        int coor[] = new int[2];
        String argsString[] = new String[param.length-1];
        for(int i = 0; i<argsString.length; i++){
            argsString[i] = Convert.valeurIntArgument(param[i+1],listeVariables);
        }
        if(argsString.length == 2){
            for(int i = 0; i<argsString.length; i++){
                try{
                    coor[i] = Integer.parseInt(argsString[i]);

                }catch(NumberFormatException e1){
                    getListeHistorique().addToList(commande,"Erreur "+argsString[0]+" n'est pas un nombre");
                    return false;
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
                    System.out.println("move : "+argsString[0]+"|");
                }catch(NumberFormatException e1){
                    getListeHistorique().addToList(commande,"Erreur "+argsString[0]+" n'est pas un nombre");
                    return false;
                }

                coor=calculeCoordArrive(distance);
            }

        }
        getListeCommande().addLigne(getCurseur().getX(), getCurseur().getY(), coor[0], coor[1], getCurseur().getCouleurCurseur(), getCurseur().getPenSize());

        getCurseur().setX(coor[0]);
        getCurseur().setY(coor[1]);
        getListeHistorique().addToList(commande,"");
        return true;
    }

    private int[] calculeCoordArrive(int distance){
        int coor[] = new int[2];
        coor[0] = (int)Math.round(getCurseur().getX() + distance * Math.cos(Math.toRadians(getCurseur().getD())));
        coor[1] = (int)Math.round(getCurseur().getY() + distance * Math.sin(Math.toRadians(180 + getCurseur().getD())));





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