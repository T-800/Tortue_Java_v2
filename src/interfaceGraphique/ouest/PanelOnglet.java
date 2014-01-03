package interfaceGraphique.ouest;

import liste.ListeFonctions;
import liste.ListeHistorique;

import javax.swing.*;
import java.awt.*;

public class PanelOnglet extends JTabbedPane{
	
	private static OngletHistorique historyPan;
	private static OngletFoctions fonctionPan;
	
	public PanelOnglet(ListeHistorique listeHistorique,ListeFonctions listeFonctions) {
		historyPan = new OngletHistorique(listeHistorique);
		fonctionPan = new OngletFoctions(listeFonctions);

		JScrollPane historiyScroll = new JScrollPane(historyPan,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane fonctionsScroll = new JScrollPane(fonctionPan,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		

		historiyScroll.setPreferredSize(new Dimension(280,0));
		fonctionsScroll.setPreferredSize(new Dimension(280,0));
		
		this.addTab("Historique", historiyScroll);
		this.addTab("Fonctions", fonctionsScroll);
		
		this.setOpaque(true);
	}
	
	public static void repaintOnglet(){
		setText();
		historyPan.repaint();
		fonctionPan.repaint();
	}
	private static void setText(){
		historyPan.sett();
		fonctionPan.sett();
	}
	
	
}
