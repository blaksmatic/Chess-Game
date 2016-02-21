package Test;

import ChessPieces.ChessJ;
import ChessPieces.Color;
import ChessPieces.Location;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/11/16.
 */
public class ChessJTest {

    @Test
    public void testCanMoveTo() throws Exception {
        ChessJ j = new ChessJ(Color.WHITE,new Location(6,6));
        assertTrue(j.canMoveTo(new Location(7,7)));
        assertTrue(j.canMoveTo(new Location(5,7)));
        assertFalse(j.canMoveTo(new Location(5,5)));
    }
}