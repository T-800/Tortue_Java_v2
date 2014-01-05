package commande;

import interfaceGraphique.Fenetre;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public class Help extends Commande{

    TreeMap<String, String> help = new TreeMap<String, String>();

    public Help() {

        /*ok*/help.put("BGCOLOR", "<p><b><u><font color=#FF0000>BGCOLOR :</font></u></b><br> " +
                "Avec cette commande vous pouvez choisir la couleur de votre arriere plan." +
                "Il suffit de lui donner la valeur en hexadecimale ou decimale de la couleur en argument." +
                " exemple : BGCOLOR 255255000, BGCOLOR FF00FF, BGCOLOR 255 0 255</p>");

        /*ok*/help.put("CENTER", "<p><b><u><font color=#FF0000>CENTER :</font></u></b><br> " +
                "Elle deplace votre curseur au centre de l'editeur. Cette commande ne prend aucun argument .</p>");

        /*ok*/help.put("CLEAR", "<p><b><u><font color=#FF0000>CLEAR :</font></u></b><br> " +
                "Elle efface tout votre dessin ainsi que l'historique des commandes executees." +
                "Elle ne prend aucun argument.</p>");

        /*ok*/help.put("GO", "<p><b><u><font color=#FF0000>GO :</font></u></b><br> " +
                "Elle deplace le curseur ou vous voulez dans le dessin.<br>" +
                "Elle prend deux arguments entiers : l'abscisse et l'ordonnee de la nouvelle position du curseur. Exemple : \"GO 100 100\" " +
                "met le curseur au point de coordonnees (100 , 100)<br>" +
                "Si vous cliquez sur le dessin avec la souris il placera votre tortue genial a l'endroit de votre clique</p>");

        /*ok*/help.put("HELP", "<p><b><u><font color=#FF0000>HELP :</font></u></b><br> " +
                "Utilisee sans argument elle affiche une fenetre dans laquelle se trouve la description de toutes les commandes. <br>" +
                "Taper HELP  COMMANDE  affiche la description de la commande COMMANDE.</p>");

        help.put("IF", "<p><b><u><font color=#FF0000>IF :</font></u></b><br> " +
                "Elle dessine un trait qui va du point actuel jusqu'au point de coordonnees (x,y).<br>" +
                "Vous donnez deux entiers qui representent ces coordonnees en arguments.<br>" +
                "Si vous ne donnez qu'un seul entier x, cette commande vous dessine un trait de longueur x dans l'orientation de votre tortue-geniale.<br>" +
                "Il est aussi possible de d'incrementer ou de decrementer la commande move ex : s'il y a eu MOVE 50 et ensuite move +50 il effectuera un move de 100. Il est possible de remettre l'incrementation a 0 en tappant move 0</p>");

        help.put("MOVE", "<p><b><u><font color=#FF0000>MOVE :</font></u></b><br> " +
                "Elle dessine un trait qui va du point actuel jusqu'au point de coordonnees (x,y). Vous donnez deux entiers qui representent ces coordonnees en arguments. " +
                "Si vous ne donnez qu'un seul entier x, cette commande vous dessine un trait de longueur x dans l'orientation de votre tortue-geniale . " +
                "Il est aussi possible de d'incrementer ou de decrementer la commende move.<br>" +
                "Ex : s'il y a eu MOVE 50 et ensuite move +50 il effectura un move de 100. Il est possible de remettre l'incrementation a 0 en tappant move 0.<br>" +
                "Vous pouvez choisir de dessiner aléatoirement, pour cela il faudra saisir : MOVE RANDOM</p>");

        help.put("NEW", "<p><b><u><font color=#FF0000>NEW :</font></u></b><br> " +
                "Commande qui cr?e un nouvel ?diteur. A la saisie de la commande une boite de dialogue est ouverte et vous propose, de sauvegarder ou non votre dessin ou d'annuler la commande. <br>" +
                "Si vous souhaitez sauvegarder, une autre fenetre vous proposera l'endroit o? vous voudrez l'enregistrer. Sinon, l'historique de votre dessin et le dessin sera supprim? et vous reviendrez sur un ?diteur de fond blanc.<br>" +
                "Dans le cas o? vous annulez la commande rien ne se passe.<br>" +
                "<b>Attention</b>: cette commande ne prend aucun argument. Pour ouvrir un noveau fichier il faudra passer par la commande OPEN.</p>");

        help.put("OPEN", "<p><b><u><font color=#FF0000>OPEN :</font></u></b><br> " +
                "Elle ouvre un fichier txt de commande et les ajoutes a notre dessin.<br>" +
                "Si vous taper juste OPEN il affiche une boite de dialogue pour choisir votre fichier, mais il est possible d'indiquer le chemin du fichier directement a OPEN.<br>" +
                "Mais si vous voulez faire un nouveau dessin ? partir du fichier ouvert vous pouvez indiquer l'option NEW, ex: OPEN NEW ou bien OPEN NEW C:\\chemin_du_fichier.txt</p>");

        help.put("PENCOLOR", "<p><b><u><font color=#FF0000>PENCOLOR :</font></u></b><br> " +
                "Avec cette commande vous pouvez choisir la couleur de votre pinceau. Il suffit de lui donner la valeur en hexadecimale de la couleur en argument. <br>" +
                "Cette commande peut aussi prendre une chaine de caractere correspondant ? une couleur, ainsi, vous pouvez taper directement en argument red, rouge, white, blanc, black, noir,yellow, jaune,blue, bleu, cyan, rose, pink, orange, magenta, green, vert, gray ou gris. <br>" +
                "ATTENTION gris fonce (dark gray) et gris clair (light gray) doivent ?tre ?crit en attach?, exemple : PENCOLOR grisfonce (PENCOLOR 	darkgray) </p>");

        help.put("PENSIZE", "<p><b><u><font color=#FF0000>PENSIZE :</font></u></b><br> " +
                "On peut changer la taille du pinceau grace ?? cette commande, elle prend comme argument un entier compris entre 1 et 99.</p>");

        help.put("REDO", "<p><b><u><font color=#FF0000>REDO :</font></u></b><br> " +
                "Cette commande reexecute la commande qui lui est precedente. Cette commande ne prend aucun argument.<br>" +
                "<b>Attention:</b> Elle ne reexecute seulement les commandes qui permettent de dessiner, ie MOVE et REPEAT</p>");

        help.put("REPEAT", "<p><b><u><font color=#FF0000>REPEAT :</font></u></b><br> " +
                "La syntaxe generale de cette commande est \"REPEAT m [ commande1 ; ... ;commande n ]\".<br>" +
                "Cette commande est tout simplement une boucle, elle execute m fois les commandes qui sont entre accolades. Elle prend un entier comme argument , celui-ci represente le nombre de fois que les commandes seront executees.<br>" +
                "Remarque : Il est possible d'imbriquer plusieurs repeat</p>");

        help.put("SAVE", "<p><b><u><font color=#FF0000>SAVE :</font></u></b><br> " +
                "Cette commande  sert a?enregistrer votre travail. En l'executant, une fenetre s'ouvre et dans laquelle vous pouvez choisir le nom et l'emplacement de votre fichier, il sauvegarde le dessin au format .png et le fichier texte qui contient les commande executees en .txt.<br>" +
                "Il est possible d'indiquer le chemin du fichier directement a la commande SAVE</p>");

        help.put("TURN", "<p><b><u><font color=#FF0000>TURN :</font></u></b><br> " +
                "Cette commande prend un entier m comme argument, elle tourne votre tortue-geniale d'un angle de valeur m dans le sens de rotation des aiguilles d'une montre.<br>" +
                "Il est possible de mettre un signe + ou - a l'argument de TURN ce qui l'ajoutera ou l'enlevera a la direction de votre tortue geniale</p>");

        help.put("UNDO", "<p><b><u><font color=#FF0000>UNDO :</font></u></b><br> " +
                "Elle annule la commande qui lui est precedente. Mais uniquement les commandes qui permettent de dessiner, donc uniquement MOVE et REPEAT. Et garde l'angle que vous avez donn?. Cette commande ne prend aucun argument</p>");

        help.put("COMMENTAIRE", "<p><b><u><font color=#FF0000>COMMENTAIRE :</font></u></b><br> " +
                "Les commentaires sont declares avec // *commentaire*. Ils peuvent etre tres utiles pour vos fichiers de commandes</p>");

        help.put("RANDOM", "<p><b><u><font color=#00CC33>RANDOM :</font></u></b><br> " +
                "Random n'est pas une commande mais un argument pour les autres commandes. Il peut etre utilise pour les commande \"GO, MOVE, PENCOLOR, REPEAT et TURN\".<br>Il genere un nombre ou une couleur aleatoire </p>");

    }



    @Override
    public String execute(String parametres) {

        String [] argument = getCmdParam(parametres);
        String txt = "";
        JFrame fenetre = new JFrame();
        JScrollPane scroll;

        if(argument.length == 1){
            txt = helpAll();
            fenetre.setSize(800, 600);
        }
        else {
            txt = helpCmd(argument[1].toUpperCase());
            fenetre.setSize(350, 200);
        }
        JEditorPane cont = null;
        cont = new JEditorPane();
        cont.setEditable(false);
        cont.setContentType("text/html");
        cont.setText(txt);

        scroll = new JScrollPane(cont,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        fenetre.getContentPane().add(scroll);
        cont.setEditable(false);
        cont.setContentType("text/html");
        fenetre.setVisible(true);
        return "";
    }


    private String helpAll(){
        String txt = "";

        for(Map.Entry<String, String> entry : help.entrySet()) {
            txt +=entry.getValue();
        }
        return txt;
    }
    private String helpCmd(String s){
        return help.get(s);
    }

}
