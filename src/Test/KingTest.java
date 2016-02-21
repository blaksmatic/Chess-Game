package Test;

import ChessPieces.Color;
import ChessPieces.King;
import ChessPieces.Location;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/2/16.
 */
public class KingTest {

    @Test
    public void testCanMoveTo() throws Exception {
        King king = new King(Color.WHITE,new Location(2,2));
        assertTrue(king.canMoveTo(new Location(1,1)));
        assertTrue(king.canMoveTo(new Location(1,2)));
        assertTrue(king.canMoveTo(new Location(2,3)));
        assertFalse(king.canMoveTo(new Location(2,2)));
        assertTrue(king.canMoveTo(new Location(3,3)));
    }
}