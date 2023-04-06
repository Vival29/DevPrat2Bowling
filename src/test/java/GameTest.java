import org.example.TableauAffichage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;


public class GameTest {
    Game g;
    TableauAffichage tab = Mockito.mock(TableauAffichage.class);

    @BeforeEach
    public void setup() {
        g = new Game(tab);
    }

    @Test
    public void twentyRoll_given0_shouldGive0() {
        for (int i = 0; i < 20; i++) {
            g.roll(0);
        }
        Assertions.assertEquals(0, g.score());
    }

    @Test
    public void twentyRoll_given1_shouldGive20() {
        for (int i = 0; i < 20; i++) {
            g.roll(1);
        }
        Assertions.assertEquals(20, g.score());
    }

    @Test
    public void twoRoll_givenSparePlus3_shouldGive16() {
        g.roll(5);
        g.roll(5);
        g.roll(3);
        for (int i = 0; i < 17; i++) {
            g.roll(0);
        }
        Assertions.assertEquals(16, g.score());
    }

    @Test
    public void twoRoll_givenStrikePlus3Plus4_shouldGive24() {
        g.roll(10);
        g.roll(3);
        g.roll(4);
        for (int i = 0; i < 16; i++) {
            g.roll(0);
        }
        Assertions.assertEquals(24, g.score());
    }

    @Test
    public void fullGame_given12Strike_shouldGive300() {
        for (int i = 0; i < 12; i++) {
            g.roll(10);
        }
        Assertions.assertEquals(300, g.score());
    }

    @Test
    public void checkStrikeOrSpare_given0and10_shouldGiveSpare() {
        g.roll(0);
        g.roll(10);
        g.roll(4);
        for (int i = 0; i < 17; i++) {
            g.roll(0);
        }
        Assertions.assertEquals(18, g.score());
    }

    @Test
    public void checkSpareLastFrame_givenSpare_shouldCountThirdRoll() {
        for (int i = 0; i < 18; i++) {
            g.roll(0);
        }
        g.roll(2);
        g.roll(8);
        g.roll(9);
        Assertions.assertEquals(19, g.score());
    }
// TEST avec MOCKITO

    @Test
    public void game_givenConnexion_shouldGiveTrue() {
        Mockito.verify(tab).seConnecter();
    }

    @Test
    public void showSpare_given1spare_shouldShowSpare() {
        g.roll(5);
        g.roll(5);
        for (int i = 0; i < 18; i++) {
            g.roll(0);
        }
        g.score();
        Mockito.verify(tab).showSpare();
    }

    @Test
    public void showStrike_given1stStrike_shouldCallShowStrikePREMIER() {
        g.roll(10);
        g.roll(2);
        for (int i = 0; i < 18; i++) {
            g.roll(0);
        }
        g.score();
        Mockito.verify(tab).showStrike(TableauAffichage.StrikeSerie.PREMIER);
    }

    @Test
    public void showStrike_givenSecondStrike_shouldCallShowStrikeSECOND() {
        g.roll(10);
        g.roll(2);
        g.roll(5);
        g.roll(10);
        for (int i = 0; i < 16; i++) {
            g.roll(0);
        }
        g.score();
        Mockito.verify(tab).showStrike(TableauAffichage.StrikeSerie.SECOND);
    }

    @Test
    public void showStrike_given12Strike_shouldCall10ShowStrikeTHIRDndMORE() {
        for (int i = 0; i < 12; i++) {
            g.roll(10);
        }
        g.score();
        Mockito.verify(tab, times(10)).showStrike(TableauAffichage.StrikeSerie.TROISIEME_ET_PLUS);
    }

    @Test
    public void endGame_givenEnd_shouldCallendGame() {
        for (int i = 0; i < 20; i++) {
            g.roll(2);
        }
        int score = g.score();
        g.endGame(score);

        Mockito.verify(tab).bestScores();
    }

    @Test
    public void endGame_givenBetterScore_shouldCallUpdateBestScore() {
        Mockito.when(tab.bestScores()).thenReturn(Arrays.asList(100, 230, 180));
        for (int i = 0; i < 12; i++) {
            g.roll(10);
        }
        int bestScore = g.score();
        g.endGame(bestScore);

        Mockito.verify(tab).updateBestScores(bestScore);
    }


}
