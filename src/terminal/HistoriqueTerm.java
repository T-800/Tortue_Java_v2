package terminal;

import java.util.ArrayList;


public class HistoriqueTerm {

    private ArrayList<String> cmd;
    private int curr;

    public HistoriqueTerm(){
        this.cmd = new ArrayList<>();
        this.curr = 0;
    }

    public void addToList(String cmd){
        this.cmd.add(cmd);

        curr = this.cmd.size();
    }

    public String getPrev(){
        if (curr <= 0) {
            this.curr = 0;
            if (this.cmd.size()==0) {
                return "";
            } else
                return this.cmd.get(0);
        }
        this.curr--;
        return this.cmd.get(curr);
    }
    public String getNext(){
        if (this.cmd.size() == curr) {
            return "";
        }
        this.curr++;
        if (curr >= this.cmd.size()) {
            return "";
        }
        return this.cmd.get(curr);
    }
}
