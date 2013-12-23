import interfaceGraphique.Fenetre;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
            ArrayList list1 = new ArrayList();
            list1.add("coco");
            list1.add("ca vas ");
            ArrayList list2 = new ArrayList(list1);
            System.out.println("avant ajout : "+list2.size());
            list1.add("amigos");
            System.out.println("après ajout : "+list2.size());
            Fenetre f = new Fenetre();
	}

}
