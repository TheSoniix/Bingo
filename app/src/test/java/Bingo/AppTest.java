/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Bingo;

import Bingo.Engine.Engine;
import Bingo.Engine.EngineImpl;
import Bingo.Engine.Model.Field;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    Engine engineUnderTest = new EngineImpl();
    EngineImpl engine = new EngineImpl();

    @Test
    public void engineNotNull() {
        assertNotNull("Engine should be exist!", this.engineUnderTest);
    }

    @Test
    public void engineImplNotNull() {
        assertNotNull("EngineImpl should be exist!", this.engine);
    }

    // Nicht so nice
    @Test
    public void playerCardNotNull() {
        assertNotNull("Player Card should be exist", engine.playerCard());
    }

    // Nicht so nice
    @Test
    public void opponentCardNotNull() {
        assertNotNull("Player Card should be exist", engine.opponentCard());
    }

    // Nicht so nice
    @Test
    public void ballsNotNull() {
        assertNotNull("Player Card should be exist", engine.notPulledBalls());
    }

    @Test
    public void checkPlayerCardValues() {
        List<Field> tempList = engine.playerCard();
        String msg = "Valuse of player Card are wrong!";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int currValue = tempList.get((i * 5) + j).getValue();
                assertTrue(msg, currValue > (i * 15) && currValue <= (i * 15) + 15);
            }
        }
    }

    @Test
    public void checkOpponentCardValues() {
        List<Field> tempList = engine.opponentCard();
        String msg = "Values of opponent Card are wrong!";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int currValue = tempList.get((i * 5) + j).getValue();
                assertTrue(msg, currValue > (i * 15) && currValue <= (i * 15) + 15);
            }
        }
    }

    @Test
    public void checkBallsValues() {
        engine.notPulledBalls().forEach(num ->
                assertTrue("Values of balls are wrong!", num > 0 && num <= 75));
    }

    @Test
    public void pulledBallsisEmpty() {
        assertEquals("Pulled balls are not empty!", 0, engine.pulledBalls().size());
    }

    @Test
    public void notPulledBallsisFull() {
        assertEquals("Not pulled balls should be full!", 75, engine.notPulledBalls().size());
    }

    @Test
    public void pullBall() {
        engine.pullBall();
        String msg = "Pulled ball is not added in list of pulled balls!";
        assertEquals(msg, 1, engine.pulledBalls().size());
        msg = "Pulled balls is not removed from not pulled List!";
        assertEquals(msg, 74, engine.notPulledBalls().size());
    }

    @Test
    public void checkValueOfPUlledBall() {
        int currBall = engine.pullBall();
        int inPulledBalls = engine.pulledBalls().get(0);
        assertEquals(currBall, inPulledBalls);
        assertFalse(engine.notPulledBalls().contains(currBall));
    }

    @Test
    public void playerFieldsAreUnmarked() {
        engine.playerCard().forEach( field ->
                assertFalse("Fields in players card should not be marked!", field.isMarked())
        );
    }

    @Test
    public void opponentFieldsAreUnmarked() {
        engine.opponentCard().forEach( field ->
                assertFalse("Fields in opponent card should not be marked!",field.isMarked())
        );
    }

    @Test
    public void checkMarkFieldPlayer() {
        for (int i = 0; i < 75; i++) {
            engine.pullBall();
        }
        engine.markFieldPlayer(1);
        String msg = "It is not possible to mark a field in players card!";
        assertTrue(msg, engine.playerCard().get(1).isMarked());
    }

    @Test
    public void checkMarkFieldOpponent() {
        for (int i = 0; i < 75; i++) {
            engine.pullBall();
        }
        engine.markFieldOpponent(1);
        String msg = "It is not possible to mark a field in opponent card!";
        assertTrue(msg, engine.opponentCard().get(1).isMarked());
    }

    @Test
    public void checkMarkFieldWithWrongIndexPlayer() {
        boolean works;
        try {
            engine.markFieldPlayer(200);
            works = true;
        } catch (AssertionError e) {
            works = false;
        }
        assertFalse(works);
    }

    @Test
    public void checkMarkFieldWithWrongIndexOpponent() {
        boolean works;
        try {
            engine.markFieldOpponent(200);
            works = true;
        } catch (AssertionError e) {
            works = false;
        }
        assertFalse(works);
    }
}
