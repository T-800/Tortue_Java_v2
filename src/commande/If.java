package commande;

import algo.Bool;
import algo.Convert;
import liste.ListeVariables;

import java.util.ArrayList;


public class If extends Commande {


    @Override
    public boolean execute(String commande,ListeVariables listeVariables){
        System.out.println ("IFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        String[] param = getCmdParam(commande);
        //Convert.printParam(param);
        setListe_Local_Variables(new ListeVariables());
        ListeVariables list =getListe_Local_Variables();

        Bool bool = new Bool(getCurseur());
        if(param.length < 3 ){
            getListeHistorique().addToList(commande,ErrorToString("1",commande));
            return false;
        }
        boolean telse = false;
        ArrayList<String> instructions = null;
        boolean doit = false;
        int i = 0;
        while (i< param.length && instructions == null){

            String boolTest = "";
            switch (i%3){
                case 0 :
                    if(param[i].equalsIgnoreCase("else")) telse = true;
                    break;
                case 1 :
                    if(telse){
                        instructions = Convert.valeurStringtArgument(param[i]);
                    }
                    else{
                        boolTest = bool.doBooleanTest(param[i],list);
                        //System.out.println (param[i]+" =bt= "+boolTest);
                        if(boolTest.trim().equalsIgnoreCase("true")){
                            doit = true;
                        }
                        //System.out.println (doit);
                    }
                    break;
                case 2:
                    if(doit) instructions = Convert.valeurStringtArgument(param[i]);

                    break;

            }

            i++;
        }
        System.out.println("I = "+i);
        if (instructions != null){
            for (String instruction : instructions) {
                System.out.println("inst -> "+instruction);
                boolean error = getTableCommande().executerCommande(instruction, list);
                if (!error){
                    getListeHistorique().addToList(commande,"Une erreur est survenue lors du IF/ELSE : <br>" + instruction);
                    return false;
                }
            }
        }
        else {
            System.out.println ("ELSE");
        }

        getListeHistorique().addToList(commande,"");
        return true;
    }
}