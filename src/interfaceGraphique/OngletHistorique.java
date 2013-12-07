package interfaceGraphique;

import liste.ListeHistorique;

import javax.swing.*;

public class OngletHistorique extends JTextPane{
	
	private ListeHistorique liste;
	
	OngletHistorique(ListeHistorique liste){
		this.liste = liste;
		this.setEditable(false);
		
		this.setContentType("text/html");
		this.setText("<html><head></head><body><p style=\"margin-top: 0\">"
				+ liste.getHtmlmsg() + "</p></body></html>");
	}
	
	public void sett(){
		setText("<html><head></head><body><p style=\"margin-top: 0\"><font size=\"4\" face=\"Arial\"><b>"
				+ liste.getHtmlmsg() + "</b></font></p></body></html>");
	}
}
