import java.util.ArrayList;
import java.util.List;

public class Game {
    private Integer n = 0;
    private Frame frame, tenthFrame;
    private List<Frame> frames;

    public Game() {
        frames = new ArrayList<>();
    }

    public void roll(Integer pins) {
        // Reset du Frame au premier lancer
        if (n < 18) {
            if (n % 2 == 0) // paire (1er aussi)
            {
                frame = new Frame();
            }
            frame.addRoll(pins);

            if (pins == 10 && frame.getRolls().size() == 1) {
                frames.add(frame);
                n++;
            } else if (frame.getRolls().size() == 2) {
                frames.add(frame);
            }
        } else if (n == 18) {
            tenthFrame = new Frame();
            tenthFrame.addRoll(pins);
        } else if (n == 19) {
            tenthFrame.addRoll(pins);
            if (tenthFrame.getScoreFrame() < 10) {
                frames.add(tenthFrame);
            }
        } else if (n == 20 && tenthFrame.getScoreFrame() >= 10) {
            tenthFrame.addRoll(pins);
            frames.add(tenthFrame);
        }
        n++;
    }

    public int score() {
        int totalScore = 0;

        // Pour les 9 frames(0-8)
        for (int i = 0; i < 10; i++) {
            // Si Strike
            if (isStrike(i)) {
                if (i < 8) {
                    totalScore += frames.get(i).getScoreFrame() + frames.get(i + 1).getScoreFrame();
                    // Si Strike again avant 9eme frame
                    if (isStrike(i + 1)) {
                        totalScore += frames.get(i + 2).getRolls().get(0);
                    }
                }
                if (i == 8) {
                    int sum = frames.get(i).getScoreFrame() + frames.get(i + 1).getRolls().get(0) + frames.get(i + 1).getRolls().get(1);
                    totalScore += sum;
                }
                if (i > 8) {
                    totalScore += frames.get(i).getScoreFrame();
                }
                // Si spare
            } else if (frames.get(i).getScoreFrame() == 10) {
                if (i < 9) {
                    totalScore += frames.get(i).getScoreFrame() + frames.get(i + 1).getRolls().get(0);
                }
                if (i == 9) {
                    totalScore += frames.get(i).getScoreFrame();
                }
            } else {
                totalScore += frames.get(i).getScoreFrame();
            }
        }
        return totalScore;
    }

    public boolean isStrike(int i) {
        boolean s = false;
        if (frames.get(i).getRolls().get(0) == 10) {
            s = true;
        }
        return s;
    }

    public boolean isSpare(int i) {
        boolean s = false;
        if (frames.get(i).getScoreFrame() == 10) {
            s = true;
        }
        return s;
    }
}
