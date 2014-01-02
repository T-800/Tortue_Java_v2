package interfaceGraphique.ouest;

import liste.ListeVariables;

import javax.swing.*;

public class OngletVariables extends JTextPane{

private ListeVariables liste;
	
	public OngletVariables(ListeVariables liste){
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
