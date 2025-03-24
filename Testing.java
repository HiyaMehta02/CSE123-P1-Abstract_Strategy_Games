import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {
    @Test
    @DisplayName("EXAMPLE TEST CASE - Small PaperTennis Example")
    public void firstCaseTest() {
        AbstractStrategyGame g = new PaperTennis();

        // You can add optional error messages that will be displayed if a test fails
        assertEquals(1, g.getNextPlayer(), "Player 1 not next player after construction");
        assertEquals(-1, g.getWinner(), "Winner incorrectly declared after construction");
        assertFalse(g.isGameOver(), "Game over immediately after construction");

        // Weird way we're going to make moves - make our own scanners NOT
        // connected to System.in. Since we can make Scanners over strings this will
        // work the exact way and allow us to control input!
        g.makeMove(new Scanner("20"));
        assertEquals(2, g.getNextPlayer(), "Player 2 not next player after a single move");
        assertEquals(-1, g.getWinner(), "Winner incorrectly declared after a single move");
        assertFalse(g.isGameOver(), "Game over immediately after construction");

        assertThrows(IllegalArgumentException.class, () -> {
            // -1 is an illegal move so our code should throw an IllegalArgumentException
            g.makeMove(new Scanner("-1"));
        }, "IllegalArgumentException not thrown for illegal move");
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - Large PaperTennis Example")
    public void secondCaseTest() {
        // You definitely don't have to get this fancy in your tests!
        AbstractStrategyGame g = new PaperTennis();

        // Going to play a whole game where player 1 bets more money than player 2
        for (int i = 0; i < 18; i++) {
            int player = (i % 2) + 1;
            assertEquals(player, g.getNextPlayer());
            assertFalse(g.isGameOver());

            if (player == 1) {
                g.makeMove(new Scanner("10"));
            } else {
                g.makeMove(new Scanner("2"));
            }
        }

        // At this point, 18 moves have been played, player 1 should 
        // have won all three rounds player 2 should won 0
        assertTrue(g.isGameOver());
        assertEquals(1, g.getWinner());
        assertEquals(-1, g.getNextPlayer());
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - Large PaperTennis Example 2")
    public void thirdCaseTest() {
        // You definitely don't have to get this fancy in your tests!
        AbstractStrategyGame g = new PaperTennis();

        // Going to play a whole game where player 1 plays all the
        // money all three rounds and runs out, player 2 plays $2 
        // every time
        int j = 0;
        for (int i = 0; i < 36; i++) {
            int player = (i % 2) + 1;
            assertEquals(player, g.getNextPlayer());
            assertFalse(g.isGameOver());
            if (player == 1) {
                if (j == 0 || j == 1) {
                    g.makeMove(new Scanner("25"));
                } else {
                    g.makeMove(new Scanner("0"));
                } 
                System.out.println(g);
                if (j == 5) {
                    j = 0;
                } else {
                    j++;
                }
            } else {
                g.makeMove(new Scanner("2"));
                System.out.println(g);
            }
        }

        // At this point, 36 moves have been played, player 1 should have lost all three rounds
        assertTrue(g.isGameOver());
        assertEquals(2, g.getWinner());
        assertEquals(-1, g.getNextPlayer());
    }
}
