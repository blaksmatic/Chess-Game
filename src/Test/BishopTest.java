package Test;

import ChessPieces.Bishop;
import ChessPieces.Color;
import ChessPieces.Location;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/2/16.
 */
public class BishopTest {

    @Test
    public void testCanMoveTo() throws Exception {
        Bishop bishop = new Bishop(Color.WHITE,new Location(1,1));
        assertFalse(bishop.canMoveTo(new Location(1,5)));
        assertFalse(bishop.canMoveTo(new Location(1,10001)));
        assertFalse(bishop.canMoveTo(new Location(1,1)));
        assertTrue(bishop.canMoveTo(new Location(2,2)));
        assertTrue(bishop.canMoveTo(new Location(0,0)));
        assertTrue(bishop.canMoveTo(new Location(6,6)));
    }
}