package ihm;

import java.awt.Color;

import javax.swing.JEditorPane;

public class PanelHistory extends JEditorPane {
	String text = "";

	public PanelHistory() {
		this.setBackground(Color.white);
		this.setEditable(false);
		this.setContentType("text/html");

	}

	public void addTexte(String txt) {
		text += "> " + txt.replace("\n", "<br>") + "<br>";

		this.setContenu();
	}

	public void addTexteIndent(String txt) {
		text += "&nbsp;&nbsp;&nbsp;" + txt.replace("\n", "<br>");

		this.setContenu();
	}

	public void addError(String txt) {
		text += "<font color=#FF0000> " + txt + "</font><br>";
		this.setContenu();
	}

	public void addCom(String txt) {
		text += "<font color=#007FAE> " + txt + "</font><br>";
		this.setContenu();
	}

	public void addVar(String txt) {
		text += "<font color=#00FF00> " + txt + "</font><br>";
		this.setContenu();
	}

	public void clear() {
		text = "";
		this.setContenu();
	}

	private void setContenu() {

		this.setText("<html><head></head><body><p style=\"margin-top: 0\">"
				+ text + "</p></body></html>");
		// System.out.println("<html><head></head><body><p style=\"margin-top: 0\">"+text+"</p></body></html>");
	}

	public void addToPanelHistory(String nomCmd , String cmd,String errorMessage){
		System.out.println("errorMessage : "+errorMessage);
		if (errorMessage == "") {
			switch(nomCmd){
				case "/*" :
					text += "<font color=#007FAE> " + cmd + "</font><br>";
					break;
				case "VAR" :
					text += "<font color=#00FF00> " + cmd + "</font><br>";
					break;
	
				case "REPEAT" :
					text += "<font color=#00FF00> " + cmd + "</font><br>";
					break;
				default :
					text += "> " + cmd.replace("\n", "<br>") + "<br>";
					break;

			}
		}
		else {
			text += cmd + "<br>" + "<font color=#FF0000> " + errorMessage + "</font><br>";
		}
		
		this.setContenu();
	}

}
