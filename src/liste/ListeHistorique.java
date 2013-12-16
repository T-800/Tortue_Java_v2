package liste;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListeHistorique {
	
	private ArrayList<Historique> liste;

    private int size, index;
	
	public ListeHistorique() {
		liste = new ArrayList<>();

        this.size = 0;
        this.index = 0;
	}
	
	public void addToList(String cmd,String error_msg){
		this.liste.add(new Historique(cmd, error_msg));
        size++;
        index = size;
	}
    public void addToList(String cmd,String error_msg,boolean save){
        this.liste.add(new Historique(cmd, error_msg,save));
        size++;
        index = size;
    }
    public void  setLastErrorMsg(String msg){
        liste.get(liste.size()-1).setError_msg(msg);
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

    public String getListeI(int i){
        return this.liste.get(i).getCommande();
    }

    public boolean isEmpty(){
        return (this.liste.size() == 0);
    }

	
	public void reset(){
		this.liste = new ArrayList<>();
        this.size = 0;
        this.index = 0;
	}

    public String getPrev(){
        if (index <= 0) {
            this.index = 0;
            if (this.size==0) {
                return "";
            } else
                return this.getListeI(0);
        }
        this.index--;
        return this.getListeI(index);
    }
    public String getNext(){
        if (this.isEmpty()) {
            return "";
        }
        this.index++;
        if (index >= size) {
            this.index = size - 1;
            return "";
        }
        return this.getListeI(index);
    }

    public void saveHistory(String chemin) throws IOException {
		/*
		 * Si le chmin a déjà une extension on la retire et on la stock Et on la
		 * rajoute a la fin ;
		 */
        File destination = new File(chemin + ".txt");

        BufferedWriter output;
        output = new BufferedWriter(new FileWriter(destination));

        int ligne = 0;
        while (ligne < liste.size()) {
            try {

                output.write(liste.get(ligne).getCommande() + "\r\n");
                output.flush();

            } catch (IOException ioe) {
                System.out.println("erreur : " + ioe);
            }
            ligne++;
        }
        output.close();
    }
	
	public class Historique {
		
		private String commande;
        private String error_msg;
        private boolean save = true;




		
		public Historique(String cmd, String error_msg) {
			this.commande = cmd;
			this.error_msg = error_msg;

		}
        public Historique(String cmd, String error_msg,boolean save) {
            this.commande = cmd;
            this.error_msg = error_msg;
            this.save = false;

        }

        public void setError_msg(String error_msg) {
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
