package liste;

import java.util.ArrayList;

public class ListeHistorique {
	
	private ArrayList<Historique> liste;
	
	public ListeHistorique() {
		liste = new ArrayList<>();

	}
	
	public void addToList(String cmd,String error_msg){
		this.liste.add(new Historique(cmd, error_msg));
	}

    public void printHist(){
        System.out.println("Hist  :");
        for(Historique h : this.liste){
            System.out.println("\tnom : "+h.getCommande()+" = "+h.getError_msg()+"|");
        }
        System.out.println("Hist  Fin");
    }

	public String getHtmlmsg(){
		String msg = "";
		for(Historique h : this.liste){
			if(h.getCommande().startsWith("//")){
				msg+="<font color=#007FAE> "+h.getCommande()+"</font><br>";
			}
			else{
				msg = msg+ h.getCommande()+"<br>";
				if(!h.getError_msg().equals("")) msg+="<font color=#FF0000> "+h.getError_msg()+"</font><br>";
			}
			
		}
		return msg;
	}

	
	public void reset(){
		this.liste = new ArrayList<>();
	}



    public ArrayList<Historique> getliste() {
        return liste;
    }
	
	public class Historique {
		
		private String commande;
        private String error_msg;




		
		public Historique(String cmd, String error_msg) {
			this.commande = cmd;
			this.error_msg = error_msg;

		}

		public String getCommande() {
			return commande;
		}

		public String getError_msg() {
			return error_msg;
		}
	}

}
