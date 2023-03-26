import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Integer> rolls;

    public Frame() {
        rolls = new ArrayList<>(2);
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public void setRolls(List<Integer> rolls) {
        this.rolls = rolls;
    }

    public void addRoll(Integer pins){
            rolls.add(pins);
    }
    public Integer getScoreFrame(){
        if(rolls.size() == 2){
            return rolls.get(0) + rolls.get(1);
        } else {
            return rolls.get(0);
        }

    }
}
