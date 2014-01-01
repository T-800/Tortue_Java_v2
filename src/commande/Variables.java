package commande;

import algo.Convert;
import liste.ListeVariables;
import liste.ListeVariables.ObjetVariables;

import javax.swing.*;

public class Variables extends Commande {

    private String [] getTaAffection(String commande){
        String tab[];

        tab = commande.split("=");
        for(int i = 0 ; i<tab.length;i++){
            tab[i] = tab[i].trim();
        }
        tab[0] = tab[0].substring(1);
        if (tab.length != 2 )return null;
        return tab;
    }
    @Override
    public boolean execute(String commande, ListeVariables listeVariables){
        String[] param = getCmdParam(commande);
        //Convert.printParam(param);

        //declaration("aaaaa", listeVariables);
        //setListe_Local_Variables(new ListeVariables(listeVariables.getliste()));
        ListeVariables list =getListe_Local_Variables();
        //list.printVar("glob");
        if(list == null){

            list = listeVariables;
        }
        ObjetVariables variable;
        if (param[0].charAt(0) == '_'){
            variable = Convert.get_Variable(list,param[0].substring(1));
            if(variable == null) {
                getListeHistorique().addToList(commande,"La variable "+param[0].substring(1)+"");
                return false;
            }
            param = getTaAffection(commande);
            String valeur = Convert.valeurIntArgument(param[1],list);
            try{
                Integer.parseInt(valeur);
                variable.setValeur_Variable(valeur);

            }catch (NumberFormatException ignored){}
            //System.out.println("calcule avant = "+param[1]);
            //System.out.println("calcule apres = "+valeur);
            //faire l'affectation
        }
        else {
            if(!param[0].equalsIgnoreCase("var")){
                getListeHistorique().addToList(commande,this.ErrorToString("1",param[0]));
                return false;
            }

            //list.printVar("local");
            variable = Convert.get_Variable(list, param[1]);
            if (variable == null) declaration(param[1], list);
            else {
                int option = JOptionPane.showConfirmDialog(null,
                        "La variable " + param[1] + " éxiste déjà. Voulez-vous la remplacer?", "Tortue",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION)variable.setValeur_Variable("0");
                else {
                    getListeHistorique().addToList(commande,"déclaration annulée");
                    return false;
                }
            }
            if(param.length == 3){
                variable = Convert.get_Variable(list, param[1]);
                try{
                    Integer.parseInt(param[2]);
                    variable.setValeur_Variable(param[2]);

                }catch (NumberFormatException ignored){
                    getListeHistorique().addToList(commande,"Affectation inccorecte :"+param[2]);
                    list.getliste().remove(variable);
                }
            }

            //list.printVar("glob");
        }
        list.printVar("var");
        getListeHistorique().addToList(commande,"");
        return true;
    }

    private void declaration(String nom_Variable,ListeVariables listeVariables){

        listeVariables.add(nom_Variable);
    }
}