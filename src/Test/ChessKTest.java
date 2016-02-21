package Test;

import ChessPieces.ChessK;
import ChessPieces.Color;
import ChessPieces.Location;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/11/16.
 */
public class ChessKTest {

    @Test
    public void testCanMoveTo() throws Exception {
        ChessK k = new ChessK(Color.WHITE, new Location(5, 5));
        assertTrue(k.canMoveTo(new Location(7, 7)));
        assertTrue(k.canMoveTo(new Location(3, 7)));
        assertFalse(k.canMoveTo(new Location(7, 5)));
        assertFalse(k.canMoveTo(new Location(3, 3)));
        assertTrue(k.canMoveTo(new Location(5, 3)));
    }
}