package commande;

import java.awt.*;


public class BackgroundColor extends Commande{
    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);
        if (argument.length != 2 && argument.length != 5) return ErrorToString("0",argument[0]);
        int color_tab[] = new int[3];
        if(argument.length == 2){
            if (argument[1].equalsIgnoreCase("random")){
                Color color = new Color((int)(Math.random()*(255)),(int)(Math.random()*(255)),(int)(Math.random()*(255)));
                Commande.getPanelDessin().getCurseur().setCouleurBg(color);



                return "";
            }
            if(argument[1].length() == 6){
                color_tab = stringHexTotab(argument[1]);
            }else if (argument[1].length() == 9){
                color_tab[0] = (int) Long.parseLong(argument[1].substring(0, 3),10);
                color_tab[1] = (int) Long.parseLong(argument[1].substring(3, 6),10);
                color_tab[2] = (int) Long.parseLong(argument[1].substring(6),10);
            }
            else return ErrorToString("2",argument[0]);
        }else {

        }
        Color color = new Color(color_tab[0],color_tab[1],color_tab[2]);
        Commande.getPanelDessin().getCurseur().setCouleurBg(color);



        return "";
    }

    public static int[] stringHexTotab(String s){
        int tab[] = new int[3];
        tab[0] = (int) Long.parseLong(s.substring(0,2),16);
        tab[1] = (int) Long.parseLong(s.substring(2,4),16);
        tab[2] = (int) Long.parseLong(s.substring(4),16);
        return tab;

    }
}
