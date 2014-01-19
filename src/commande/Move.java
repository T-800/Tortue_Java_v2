package commande;

import algo.Convert;
import interfaceGraphique.Fenetre;


public class Move extends Commande{
    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);
        int[] coor;
        if (argument.length == 2){
            String valeur = Convert.valeurIntArgument(argument[1]);
            try {
                int distance = Integer.parseInt(valeur);
                coor = calculeCoordArrive(distance);
            }catch (NumberFormatException e){return valeur;}
        }
        else if (argument.length == 3){
             coor = new int[2];
            try {
                coor[0] = Integer.parseInt(argument[1]);
                coor[1] = Integer.parseInt(argument[2]);


            }catch (NumberFormatException e){return ErrorToString("1",argument[0]);}
            coor[0] += (getPanelDessin().getPreferredSize().width/2);
            coor[1] = (getPanelDessin().getPreferredSize().height/2)-coor[1];
        }
        else return ErrorToString("1",argument[1]);
        if (!dans_Le_Dessin(coor)){
          return "coordonnées pas dans le dessin";
        }
        getPanelDessin().getListeCommande().addLigne(getPanelDessin().getCurseur().getX(),getPanelDessin().getCurseur().getY(),coor[0],coor[1],getPanelDessin().getCurseur().getCouleurCurseur(),getPanelDessin().getCurseur().getPenSize());
        getPanelDessin().getCurseur().setPos(coor);

        return "";
    }


    private int[] calculeCoordArrive(int distance){
        int coor[] = new int[2];
        coor[0] = (int)Math.round(getPanelDessin().getCurseur().getX() + distance * Math.cos(Math.toRadians(getPanelDessin().getCurseur().getD())));
        coor[1] = (int)Math.round(getPanelDessin().getCurseur().getY() + distance * Math.sin(Math.toRadians(180 + getPanelDessin().getCurseur().getD())));





        return coor;
    }

    private boolean dans_Le_Dessin( int [] coor){
        return ((coor[0] >= 0 && coor[0] <= getPanelDessin().getSize().width) && (coor[1] >= 0 && coor[1] <= getPanelDessin().getSize().height));
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
