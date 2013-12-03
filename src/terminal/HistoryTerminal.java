package terminal;



import liste.ListeHistorique;

public class HistoryTerminal {
	
	private ListeHistorique historyList;
	private int size, index;
	
	public HistoryTerminal(){
		this.historyList= new ListeHistorique();
		this.size = 0;
		this.index = 0;
	}
	
	public void addToHistoryTerminal(String s){
		historyList.addToList(s, "");;
		size++;
		index = size;
	}
	
	/*public String getPrev(){
		if (index <= 0) {
			this.index = 0;
			if (this.size==0) {
				return "";
			} else
				return historyList.get(0);
		}
		this.index--;
		return historyList.get(index);
	}
	public String getNext(){
		if (historyList.isEmpty()) {
			return "";
		}
		this.index++;
		if (index >= size) {
			this.index = size - 1;
			return "";
		}
		return historyList.get(index);
	}*/
	

}
