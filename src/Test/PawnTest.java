package Test;

import ChessPieces.Color;
import ChessPieces.Location;
import ChessPieces.Pawn;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/2/16.
 */
public class PawnTest {

    @Test
    public void testCanMove() throws Exception {
        Pawn pawn = new Pawn(Color.WHITE,new Location(3,3));
        assertTrue(pawn.canMoveTo(new Location(3,5)));
        assertFalse(pawn.canMoveTo(new Location(3,6)));
        assertTrue(pawn.canMoveToCapture(new Location(4,4)));
        assertTrue(pawn.canMoveToCapture(new Location(2,4)));
        assertTrue(pawn.canMoveTo(new Location(3,4)));
        pawn = new Pawn(Color.BLACK, new Location(3,3));
        assertTrue(pawn.canMoveTo(new Location(3,1)));
        assertTrue(pawn.canMoveTo(new Location(3,2)));
        assertFalse(pawn.canMoveToCapture(new Location(4,4)));
        assertFalse(pawn.canMoveToCapture(new Location(2,4)));
        assertFalse(pawn.canMoveTo(new Location(3,4)));
    }
}