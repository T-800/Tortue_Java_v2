package commande;

import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/*
 * aide pour l'utilisateur
 */
public class Help implements Commande {
	TreeMap<String, String> help = new TreeMap<String, String>();
	
	public Help() {
		// TODO Auto-generated constructor stub
		
		help.put("BACK", "<p><b><u><font color=#FF0000>BACK :</font></u></b><br> " +
				"Cette commande fonctionne en complementarite avec la commande \"REMEMBER\"" +
				"decrite ci dessous. Elle deplace votre tortue au dernier point enregistre" +
				"par \"REMEMBER\" </p>");
		
		help.put("BACKGROUNDCOLOR", "<p><b><u><font color=#FF0000>BACKGROUNDCOLOR :</font></u></b><br> " +
				"Avec cette commande vous pouvez choisir la couleur de votre arriere plan." +
				"Il suffit de lui donner la valeur en hexadecimale de la couleur en argument." +
				"Cette commande peut prendre en argument une couleur, ainsi, vous pouvez taper " +
				"directement en argument red, rouge, white, blanc, black, noir,yellow, jaune," +
				"blue, bleu, cyan, rose, pink, orange, magenta, green, vert, gray ou gris.<br>" +
				"<b>ATTENTION</b> gris fonce (dark gray) et gris clair (light gray) doivent etre ecrit en attache," +
				" exemple : BACKGROUNDCOLOR grisfonce (ou BACKGROUNDCOLOR darkgray)</p>");
		
		help.put("CENTER", "<p><b><u><font color=#FF0000>CENTER :</font></u></b><br> " +
				"Elle deplace votre curseur au centre de l'editeur. Cette commande ne prend aucun argument .</p>");
		
		help.put("CLEAR", "<p><b><u><font color=#FF0000>CLEAR :</font></u></b><br> " +
				"Elle efface tout votre dessin ainsi que l'historique des commandes executees, mais laisse la couleur de fond." +
				"Elle ne prend aucun argument.</p>");
		
		help.put("DOWN", "<p><b><u><font color=#FF0000>DOWN :</font></u></b><br> " +
				"Positionne le curseur vers le bas (angle de 270?). Ne prend aucun argument</p>");
		
		help.put("ERASE", "<p><b><u><font color=#FF0000>ERASE :</font></u></b><br> " +
				"Peut effacer un trait. Prend un ou deux entiers en argument</p>");

		help.put("GO", "<p><b><u><font color=#FF0000>GO :</font></u></b><br> " +
				"Elle deplace le curseur ou vous voulez dans l'editeur.<br>" +
				"Elle prend deux arguments entiers : l'abscisse et l'ordonnee de la nouvelle position du curseur. Exemple : \"GO 100 100\" met le curseur au point de coordonnees (100 , 100)<br>" +
				"Si vous cliquez sur le dessin avec la souris il placera votre tortue genial a l'endroit de votre clique</p>");
		
				
		help.put("HELP", "<p><b><u><font color=#FF0000>HELP :</font></u></b><br> " +
				"Utilisee sans argument elle affiche une fenetre dans laquelle se trouve la description de toutes les commandes. <br>" +
				"Taper HELP  COMMANDE  affiche la description de la commande COMMANDE.</p>");
		
		help.put("IF", "<p><b><u><font color=#FF0000>IF :</font></u></b><br> " +
				"Elle dessine un trait qui va du point actuel jusqu'au point de coordonnees (x,y).<br>" +
				"Vous donnez deux entiers qui representent ces coordonnees en arguments.<br>" +
				"Si vous ne donnez qu'un seul entier x, cette commande vous dessine un trait de longueur x dans l'orientation de votre tortue-geniale.<br>" +
				"Il est aussi possible de d'incrementer ou de decrementer la commande move ex : s'il y a eu MOVE 50 et ensuite move +50 il effectuera un move de 100. Il est possible de remettre l'incrementation a 0 en tappant move 0</p>");
		
		help.put("LEFT", "<p><b><u><font color=#FF0000>LEFT :</font></u></b><br> " +
				"Tourne le curseur vers la gauche (0?) Ne prend aucun parametres</p>");
		
		help.put("MOVE", "<p><b><u><font color=#FF0000>MOVE :</font></u></b><br> " +
				"Elle dessine un trait qui va du point actuel jusqu'au point de coordonnees (x,y). Vous donnez deux entiers qui representent ces coordonnees en arguments. Si vous ne donnez qu'un seul entier x, cette commande vous dessine un trait de longueur x dans l'orientation de votre tortue-geniale . Il est aussi possible de d'incrementer ou de decrementer la commende move.<br>" +
				"Ex : s'il y a eu MOVE 50 et ensuite move +50 il effectura un move de 100. Il est possible de remettre l'incrementation a 0 en tappant move 0.<br>" +
				"Vous pouvez choisir de dessiner al?atoirement, pour cela il faudra saisir : MOVE RANDOM RANDOM</p>");
		
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
		
		help.put("PENDOWN", "<p><b><u><font color=#FF0000>PENDOWN :</font></u></b><br> " +
				"Cette commande ne prend aucun argument. Elle pose votre pinceau sur la feuille de dessin pour pouvoir dessiner par la suite.</p>");
		
		help.put("PENSIZE", "<p><b><u><font color=#FF0000>PENSIZE :</font></u></b><br> " +
				"On peut changer la taille du pinceau grace ?? cette commande, elle prend comme argument un entier compris entre 1 et 99.</p>");
		
		help.put("PENUP", "<p><b><u><font color=#FF0000>PENUP :</font></u></b><br> " +
				"Cette commande leve le pinceau de la feille de dessin. Une fois executee, vous ne pourrez plus dessiner.<br>" +
				"Cette commande ne prend aucun argument.</p>");
		
		help.put("REDO", "<p><b><u><font color=#FF0000>REDO :</font></u></b><br> " +
				"Cette commande reexecute la commande qui lui est precedente. Cette commande ne prend aucun argument.<br>" +
				"<b>Attention:</b> Elle ne reexecute seulement les commandes qui permettent de dessiner, ie MOVE et REPEAT</p>");
		
		help.put("REMEMBER", "<p><b><u><font color=#FF0000>REMEMBER :</font></u></b><br> " +
				"Enregistre les coordonn?es (abscisse, ordonnee et direction) du curseur actuel. Cette commande ne prend aucun argument.</p>");
		
		help.put("REPEAT", "<p><b><u><font color=#FF0000>REPEAT :</font></u></b><br> " +
				"La syntaxe generale de cette commande est \"REPEAT m [ commande1 ; ... ;commande n ]\".<br>" +
				"Cette commande est tout simplement une boucle, elle execute m fois les commandes qui sont entre accolades. Elle prend un entier comme argument , celui-ci represente le nombre de fois que les commandes seront executees.<br>" +
				"Remarque : Il est possible d'imbriquer plusieurs repeat</p>");
		
		help.put("RIGHT", "<p><b><u><font color=#FF0000>RIGHT :</font></u></b><br> " +
				"Cette commande tourne votre tortue geniale vers la droite. Ne prend aucun argument</p>");
		
		help.put("SAVE", "<p><b><u><font color=#FF0000>SAVE :</font></u></b><br> " +
				"Cette commande  sert a?enregistrer votre travail. En l'executant, une fenetre s'ouvre et dans laquelle vous pouvez choisir le nom et l'emplacement de votre fichier, il sauvegarde le dessin au format .png et le fichier texte qui contient les commande executees en .txt.<br>" +
				"Il est possible d'indiquer le chemin du fichier directement a la commande SAVE</p>");
		
		help.put("TURN", "<p><b><u><font color=#FF0000>TURN :</font></u></b><br> " +
				"Cette commande prend un entier m comme argument, elle tourne votre tortue-geniale d'un angle de valeur m dans le sens de rotation des aiguilles d'une montre.<br>" +
				"Il est possible de mettre un signe + ou - a l'argument de TURN ce qui l'ajoutera ou l'enlevera a la direction de votre tortue geniale</p>");
		
		help.put("UNDO", "<p><b><u><font color=#FF0000>UNDO :</font></u></b><br> " +
				"Elle annule la commande qui lui est precedente. Mais uniquement les commandes qui permettent de dessiner, donc uniquement MOVE et REPEAT. Et garde l'angle que vous avez donn?. Cette commande ne prend aucun argument</p>");
		
		help.put("UP", "<p><b><u><font color=#FF0000>UP :</font></u></b><br> " +
				" Cette commande tourne votre tortue genial vers le haut, dans un angle de 90?</p>");
		
		help.put("VAR", "<p><b><u><font color=#FF0000>VAR :</font></u></b><br> " +
				"Cette commande vous permet de declarer des variables et/ou de modifier leurs valeurs. Une d?claration de variable commence toujours par VAR_*, mais pour l'utilisation elle comme toujours par un underscore \"_\" suivi du nom de la variable donn?e. Voici les 3 cas possibles<br>" +
				"<ul><li>La declaration simple : VAR _variable = 33 ou VAR _variable2 = _variable</li>" +
				"<li>Le changement de signe : VAR _variable = - _variable ou VAR _variable2 = - _variable</li>" +
				"<li>Les operations VAR _variable = _variable - 33 ou VAR _variable3 = _variable * _variable2</li>" +
				"</ul>" +
				"Les operations permises sont \"+, -, *, et /\". <br> Pour avoir la liste des variables que vous avez declare VAR info <b>Attention</b> il n'est possible de faire des opération que sur deux variables a la fois</p>");
		
		help.put("COMMENTAIRE", "<p><b><u><font color=#FF0000>COMMENTAIRE :</font></u></b><br> " +
				"Les commentaires sont declares avec // *commentaire*. Ils peuvent etre tres utiles pour vos fichiers de commandes</p>");
		
		help.put("RANDOM", "<p><b><u><font color=#00CC33>RANDOM :</font></u></b><br> " +
				"Random n'est pas une commande mais un argument pour les autres commandes. Il peut etre utilise pour les commande \"GO, MOVE, PENCOLOR, REPEAT et TURN\".<br>Il genere un nombre ou une couleur aleatoire </p>");
		
	}
	
	

	@Override
	/**
	 * (non-Javadoc)
	 * @see commande.Commande#execute(java.lang.String[], boolean)
	 */
	public void execute(String[] parametres, boolean addToHistory) {
		
		
		
		String txt = "";
		JFrame fenetre = new JFrame();
		JScrollPane scroll;
		
		if(parametres.length == 1){
			txt = helpAll();
			fenetre.setSize(800, 600);
		}
		else {
			txt = helpCmd(parametres[1].toUpperCase());
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
	}
	
	private String helpAll(){
		String txt = "";
		
		for(Entry<String, String> entry : help.entrySet()) {
			 txt +=entry.getValue();
		}
		return txt;
	}
	private String helpCmd(String s){
		return help.get(s);
	}
	

	@Override
	public String ToString(String[] tab) {
		String s = "";

		for (int i = 0; i < tab.length; i++) {
			s += tab[i] + " ";
		}
		return s;
	}

	@Override
	public boolean canDoIt(String[] parametres) {
		if (parametres.length==1) {
			return true;
		}
		if (parametres.length>2) {
			return false;
		}
		if(!help.containsKey(parametres[1].toUpperCase())){
			return false;
		}
		return true;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

}
