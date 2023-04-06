import org.example.TableauAffichage;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Integer n = 0;
    private Frame frame, tenthFrame;
    private List<Frame> frames;
    private final TableauAffichage tab;
    int as = 0;


    public Game(TableauAffichage tab) {
        frames = new ArrayList<>();
        this.tab = tab;
        tab.seConnecter();
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

        for (int i = 0; i < 10; i++) {
            // Si Strike
            if (isStrike(i)) {
                showStrikeNumber();
                if (i < 8) {
                    totalScore += frames.get(i).getScoreFrame() + frames.get(i + 1).getScoreFrame();

                    // Si Strike again avant 9eme frame
                    if (isStrike(i + 1)) {
                        totalScore += frames.get(i + 2).getRolls().get(0);
                    }
                }
                if (i == 8) {
                    showStrikeNumber();
                    int sum = frames.get(i).getScoreFrame() + frames.get(i + 1).getRolls().get(0) + frames.get(i + 1).getRolls().get(1);
                    totalScore += sum;
                }
                if (i > 8) {
                    showStrikeNumber();
                    totalScore += frames.get(i).getScoreFrame();
                }
                //Si c'est un strike on affiche strike selon le nombre de fois que c'est strike.

                // Si spare
            } else if (isSpare(i)) {

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
        if (frames.get(i).getRolls().get(0) + frames.get(i).getRolls().get(1) == 10) {
            s = true;
            tab.showSpare();
        }
        return s;
    }

    public void showStrikeNumber() {
        as++;
        if (as == 1) {
            tab.showStrike(TableauAffichage.StrikeSerie.PREMIER);
        }
        if (as == 2) {
            tab.showStrike(TableauAffichage.StrikeSerie.SECOND);
        }
        if (as > 2) {
            tab.showStrike(TableauAffichage.StrikeSerie.TROISIEME_ET_PLUS);
        }
    }

    public void endGame(int score) {
        if (tab.bestScores().stream().anyMatch(i -> i < score)) {
            tab.updateBestScores(score);
        }
        //System.out.println(tab.bestScores());
    }
}
