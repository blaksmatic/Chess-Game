package Test;

import ChessPieces.Color;
import ChessPieces.Location;
import ChessPieces.Queen;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/3/16.
 */
public class QueenTest {

    @Test
    public void testCanMoveTo() throws Exception {
        Queen queen = new Queen(Color.WHITE,new Location(4,4));
        assertTrue(queen.canMoveTo(new Location(5,4)));
        assertTrue(queen.canMoveTo(new Location(4,7)));
        assertFalse(queen.canMoveTo(new Location(6,7)));
        assertTrue(queen.canMoveTo(new Location(6,6)));
        assertTrue(queen.canMoveTo(new Location(2,2)));
        assertFalse(queen.canMoveTo(new Location(8,9)));
        assertFalse(queen.canMoveTo(new Location(1,2)));

    }
}