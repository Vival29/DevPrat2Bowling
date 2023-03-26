import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
   @Test
    public void twentyRoll_given0_shouldGive0(){
       int points =0;
       Game g = new Game();
       for(int i = 0; i<20; i++){
           g.roll(0);
       }
       Assertions.assertEquals(0 ,g.score() );
   }
   @Test
    public void twentyRoll_given1_shouldGive20(){
       int points =0;
       Game g = new Game();
       for(int i = 0; i<20; i++){
           g.roll(1);
       }
       Assertions.assertEquals(20 ,g.score() );
   }
    @Test
    public void twoRoll_givenSparePlus3_shouldGive16(){
        int points =0;
        Game g = new Game();
        g.roll(5);
        g.roll(5);
        g.roll(3);
        for(int i = 0; i<17; i++){
            g.roll(0);
        }
        Assertions.assertEquals(16 ,g.score() );
    }
    @Test
    public void twoRoll_givenStrikePlus3Plus4_shouldGive24(){
        int points =0;
        Game g = new Game();
        g.roll(10);
        g.roll(3);
        g.roll(4);
        for(int i = 0; i<16; i++){
            g.roll(0);
        }
        Assertions.assertEquals(24 ,g.score() );
    }


}
