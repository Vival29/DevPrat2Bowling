import java.util.ArrayList;
import java.util.List;

public class Game {
    private Integer n = 0;
    private Frame frame;
    private List<Frame> frames;
    public Game(){
        frames = new ArrayList<>(10);
    }
    public void roll(Integer pins) {
        // Reset du Frame au premier lancer
        if(n%2 == 0) // paire (1er aussi)
        {
            frame = new Frame();
        }
        frame.addRoll(pins);

        if(pins == 10 || frame.getRolls().size() == 2 || (!frames.isEmpty() && frame.getRolls().size() == 1)) {
            frames.add(frame);
        }
        n++;
    }

    public int score() {
        int totalScore = 0;
        for (int i = 0; i < 2; i++) {
            //if(frames.get(i).getRolls().get(0) == 10 ){
                //totalScore += 10 + frames.get(i+1).getScoreFrame();
            if(frames.get(i).getScoreFrame() == 10 ){
                totalScore += 10 + frames.get(i+1).getRolls().get(0);
            }else {
                totalScore += frames.get(i).getScoreFrame();
            }
        }

        //return frames.stream().reduce(0,(subtotal, element)-> subtotal + element);

        return totalScore;
    }
}
