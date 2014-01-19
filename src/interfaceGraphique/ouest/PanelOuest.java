package interfaceGraphique.ouest;

import dessin.Curseur;
import liste.ListeFonctions;
import liste.ListeHistorique;

import terminal.TableCommande;

import javax.swing.*;
import java.awt.*;

public class PanelOuest extends JPanel{

    private PanelOptions panelOptions;
    private PanelOnglet panelOnglet;

    public PanelOuest(Curseur curseur, ListeHistorique historiqueListe, TableCommande table, ListeFonctions fonctionsListe
    ){
        panelOptions = new PanelOptions(curseur,historiqueListe,table);
        panelOnglet= new PanelOnglet(historiqueListe, fonctionsListe);

        this.setLayout(new BorderLayout());

        panelOptions.setPreferredSize(new Dimension(40,40));
        this.add(panelOptions,BorderLayout.NORTH);
        this.add(panelOnglet,BorderLayout.CENTER);
    }

}
