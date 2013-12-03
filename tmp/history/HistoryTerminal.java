package history;

import java.util.ArrayList;

/**
 * Cette classe gère l'historique du "Terminal". Tout ce qui est tappée dans le
 * terminal et validée avec entrer est stocké dans un ArrayList
 * 
 * @author Renaud
 * 
 */
public class HistoryTerminal {

	/**
	*
	*/
	private static ArrayList<String> history;
	private static  int current,last;

	public HistoryTerminal() {
		history = new ArrayList<String>();
		current = 0;
		last = 0 ;

	}

	public void addToHistory(String Comande) {
		history.add(Comande);
		last++;
		this.reset();
	}

	public String showUp() {
		if (current <= 0) {
			this.current = 0;
			if (history.isEmpty()) {
				return "";
			} else
				return history.get(0);
		}
		this.current--;
		return history.get(current);
	}

	public String showDown() {
		if (history.isEmpty()) {
			return "";
		}
		this.current++;
		if (current >= history.size()) {
			this.current = history.size() - 1;
			return "";
		}
		return history.get(current);
	}

	public void reset() {
		current = last;
	}

}
