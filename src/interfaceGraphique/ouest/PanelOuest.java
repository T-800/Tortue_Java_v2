package interfaceGraphique.ouest;

import dessin.Curseur;
import liste.ListeFonctions;
import liste.ListeHistorique;
import liste.ListeVariables;
import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;

/**
 * Created by renaud on 02/01/14.
 */
public class PanelOuest extends JPanel{

    private PanelOptions panelOptions;
    private PanelOnglet panelOnglet;

    public PanelOuest(Curseur curseur, ListeHistorique historiqueListe, TableCommande table, ListeFonctions fonctionsListe, ListeVariables variableListe){
        panelOptions = new PanelOptions(curseur,historiqueListe,table);
        panelOnglet= new PanelOnglet(historiqueListe, fonctionsListe, variableListe);

        this.setLayout(new BorderLayout());

        panelOptions.setPreferredSize(new Dimension(40,40));
        this.add(panelOptions,BorderLayout.NORTH);
        this.add(panelOnglet,BorderLayout.CENTER);
    }
}
