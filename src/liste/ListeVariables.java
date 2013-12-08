package liste;

import java.util.ArrayList;

public class ListeVariables {

	private  ArrayList<ObjetVariables> cmd;

	public ListeVariables() {
		cmd = new ArrayList<>();
	}
	
	public ArrayList<ObjetVariables> getliste() {
		return cmd;
	}
	
	public void add(String nom){
		cmd.add(new ObjetVariables(nom));
	}
	
	public ObjetVariables getVar(String name)  {
        for(ObjetVariables h : this.cmd){
            if (h.getNom_Variable().equalsIgnoreCase(name))return h;
        }
        return null;
    }
	public String getHtmlmsg(){
		String msg = "";
		for(ObjetVariables h : this.cmd){
			msg = msg+"<font color=#006923> "+ h.getNom_Variable()+" = ";
			msg = msg+h.getValeur_Variable()+"</font><br>";
		}
		return msg;
	}
	
	public void reset(){
		this.cmd = new ArrayList<>();
	}

	
	public class ObjetVariables {
		private String nom_Variable;
		private String valeur_Variable = "0";

		public ObjetVariables(String nom_Variable) {
			this.nom_Variable = nom_Variable;
		}

		public String getNom_Variable() {
			return nom_Variable;
		}

		public String getValeur_Variable() {
			return valeur_Variable;
		}

		public void setValeur_Variable(String valeur_Variable) {
			this.valeur_Variable = valeur_Variable;
		}
	}
}
