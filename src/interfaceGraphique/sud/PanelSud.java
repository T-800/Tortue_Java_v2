package interfaceGraphique.sud;

import com.intellij.ui.components.JBScrollPane;
import dessin.Curseur;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;

public class PanelSud extends JPanel {

    public PanelTerminal panelTerminal;
    public PanelInfo panelInfo;

    public PanelSud(Curseur curseur,TableCommande tableCommande, ListeHistorique listeHistorique, ListeFonctions listeFonctions, ListeVariables listeVariables){
        this.panelTerminal = new PanelTerminal(tableCommande,listeHistorique,listeFonctions,listeVariables);
        this.panelInfo = new PanelInfo(curseur);
        this.setLayout(new BorderLayout());


        JScrollPane scrollPane = new JBScrollPane(panelTerminal.jEditorPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 70));
        this.panelTerminal.jEditorPane.setPreferredSize(new Dimension(0,50));
        panelInfo.setPreferredSize(new Dimension(0 , 15));
        this.add(scrollPane,BorderLayout.NORTH);
        this.add(panelInfo,BorderLayout.CENTER);

    }


}
