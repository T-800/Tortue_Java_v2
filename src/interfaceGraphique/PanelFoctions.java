package interfaceGraphique;

import liste.ListeFonctions;

import javax.swing.*;

public class PanelFoctions extends JTextPane{

	ListeFonctions listeFonctions;
	
	PanelFoctions(ListeFonctions listeFonctions){
		this.listeFonctions = listeFonctions;
		this.setEditable(false);
		
		this.setContentType("text/html");
		this.setText("<html><head></head><body><p style=\"margin-top: 0\">"
				+ listeFonctions.getHtmlmsg() + "</p></body></html>");
	}
	
	public void sett(){
		setText("<html><head></head><body><p style=\"margin-top: 0\"><font size=\"4\" face=\"Arial\"><b>"
				+ listeFonctions.getHtmlmsg() + "</b></font></p></body></html>");
	}
}
