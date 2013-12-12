package liste;

import java.util.ArrayList;

public class ListeFonctions {
	
	private  ArrayList<ObjetFonction> liste;

	public ListeFonctions() {
		liste = new ArrayList<ObjetFonction>();
	}

    public ArrayList<ObjetFonction> getliste() {
        return liste;
    }
	
	public void addFonction(String nom_Fonction,int nb_Arg,ArrayList<String> cmdList){
		this.liste.add(new ObjetFonction(nom_Fonction,nb_Arg,cmdList));
	}

    public void reaffectFoction(ObjetFonction fonc,int nb_Arg,ArrayList<String> cmdList){
        fonc.setNb_Armument(nb_Arg);
        fonc.setCmdsFonction(cmdList);
    }
    public void removeFonction(ObjetFonction objetFonction){
        this.liste.remove(objetFonction);
    }

	public ObjetFonction getFonction(String name){
		for(ObjetFonction f : liste){
			if(f.getNom_Fonction().equals(name))return f;
		}
		return null;
	}
	
	public String getHtmlmsg(){
		String msg = "";
		for(ObjetFonction h : this.liste){
			msg = msg+"<font color=#FF0000>  "+ h.getNom_Fonction()+" </font>["+h.getNb_Agument_Fonction()+"] : <br>";
			for(String s : h.getListe_Fonction()){
				if(s.startsWith("//")){
					msg= msg+"&nbsp;&nbsp;-&nbsp;<font color=#007FAE> "+s+"</font><br>";
				}
				else{
					msg = msg+"&nbsp;&nbsp;-&nbsp;"+s+" <br>";
				}
				
			}
		}
		msg = msg+"<br>";
		return msg;
	}
	
	
	
	public void reset(){
		this.liste = new ArrayList<ObjetFonction>();
	}
	
	public class ObjetFonction {
		private String nom_Fonction;
		private int nb_Armument;
		private ArrayList<String> cmdsFonction;



        public ObjetFonction(String nom_Fonction,int nb_Args,ArrayList<String> cmdList) {
			this.nom_Fonction = nom_Fonction;
			this.cmdsFonction = cmdList;
			this.nb_Armument = nb_Args;
		}

		public String getNom_Fonction() {
			return nom_Fonction;
		}
		public int getNb_Agument_Fonction() {
			return nb_Armument;
		}
		public ArrayList<String> getListe_Fonction() {
			return cmdsFonction;
		}
        public void setNb_Armument(int nb_Armument) {
            this.nb_Armument = nb_Armument;
        }

        public void setCmdsFonction(ArrayList<String> cmdsFonction) {
            this.cmdsFonction = cmdsFonction;
        }



    }
	
	
	

}
