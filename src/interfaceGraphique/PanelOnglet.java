package interfaceGraphique;

import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;

import javax.swing.*;
import java.awt.*;

public class PanelOnglet extends JTabbedPane{
	
	private static OngletHistorique historyPan;
	private static OngletFoctions fonctionPan;
	private static OngletVariables variablePan;
	
	public PanelOnglet(ListeHistorique listeHistorique,ListeFonctions listeFonctions,ListeVariables listeVariables) {
		historyPan = new OngletHistorique(listeHistorique);
		fonctionPan = new OngletFoctions(listeFonctions);
		variablePan = new OngletVariables(listeVariables);
		JScrollPane historiyScroll = new JScrollPane(historyPan,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane fonctionsScroll = new JScrollPane(fonctionPan,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane variableScroll = new JScrollPane(variablePan,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		historiyScroll.setPreferredSize(new Dimension(280,0));
		fonctionsScroll.setPreferredSize(new Dimension(280,0));
		variableScroll.setPreferredSize(new Dimension(280,0));
		
		this.addTab("Historique", historiyScroll);
		this.addTab("Fonctions", fonctionsScroll);
		this.addTab("Variables", variableScroll);
		
		this.setOpaque(true);
	}
	
	public static void repaintOnglet(){
		setText();
		historyPan.repaint();
		fonctionPan.repaint();
		variablePan.repaint();
	}
	private static void setText(){
		historyPan.sett();
		variablePan.sett();
		fonctionPan.sett();
	}
	
	
}
