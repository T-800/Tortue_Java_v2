package commande;


import algo.Convert;
import dessin.Curseur;
import liste.ListeVariables;

import java.awt.*;
import java.util.Random;

public class BackgroundColor extends Commande{
    Curseur curseur;
    private ListeVariables listeVariables;

    public BackgroundColor(Curseur curseur,ListeVariables listeVariables) {
        this.curseur = curseur;
        this.listeVariables = listeVariables;
    }

    @Override
    public String execute(String[] commande,ListeVariables listeVariables){

        if(commande.length>4 || commande.length<2)return "1";

        int rgb[] = new int[3];
        if(commande.length==2 && commande[1].equalsIgnoreCase("random")){
            curseur.setCouleurBg(randomColors());
        }
        else {
            String argsString[] = new String[commande.length-1];
            for(int i = 0; i<argsString.length; i++){
                argsString[i] = Convert.convertArg(commande[i + 1], listeVariables);
            }

            for(int i = 0; i<argsString.length; i++){
                if(argsString[i].toLowerCase().equals("random")){
                    rgb[i] = randomColor();
                }
                else {
                    try{
                        rgb[i] = Integer.parseInt(argsString[i]);
                        rgb[i] %=256;
                    }catch(NumberFormatException e1){
                        return "Erreur "+argsString[i]+" n'est pas un nombre";
                    }
                }
            }
            curseur.setCouleurBg(new Color((float)rgb[0]/255,(float)rgb[1]/255,(float)rgb[2]/255));
        }







        return "";
    }

    public Color randomColors() {

        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(255);
        int green = randomGenerator.nextInt(255);
        int blue = randomGenerator.nextInt(255);

        return new Color(red, green, blue);
    }
    public int randomColor() {

        Random randomGenerator = new Random();
        return randomGenerator.nextInt(255);

    }

}