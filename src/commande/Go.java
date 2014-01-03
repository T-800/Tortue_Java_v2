package commande;

public class Go extends Commande{
    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);
        if (argument.length != 3) return ErrorToString("1", argument[0]);
        int[] coor;
         coor = new int[2];
         try {
             coor[0] = Integer.parseInt(argument[1]);
             coor[1] = Integer.parseInt(argument[2]);
         }catch (NumberFormatException e){return ErrorToString("1",argument[0]);}
        coor[0] += (getPanelDessin().getPreferredSize().width/2);
        coor[1] = (getPanelDessin().getPreferredSize().height/2)-coor[1];

        if (!dans_Le_Dessin(coor)){
            return "coordonnées pas dans le dessin";
        }

        getPanelDessin().getCurseur().setPos(coor);

        return "";
    }

    private boolean dans_Le_Dessin( int [] coor){
        return (coor[0] >= 0 && coor[0] <= getPanelDessin().getPreferredSize().width) && (coor[1] >= 0 || coor[1] <= getPanelDessin().getPreferredSize().height);
    }
}
