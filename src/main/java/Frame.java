import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Integer> rolls;

    public Frame() {
        rolls = new ArrayList<>();
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public void setRolls(List<Integer> rolls) {
        this.rolls = rolls;
    }

    public void addRoll(Integer pins) {
        rolls.add(pins);
    }

    public Integer getScoreFrame() {
        int score = 0;
        for (Integer roll : rolls) {
            score += roll;
        }
        return score;

    }
}
