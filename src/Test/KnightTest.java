package Test;

import ChessPieces.Color;
import ChessPieces.Knight;
import ChessPieces.Location;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/2/16.
 */
public class KnightTest {

    @Test
    public void testCanMoveTo() throws Exception {
        Knight knight = new Knight(Color.WHITE,new Location(3,3));
        assertFalse(knight.canMoveTo(new Location(1,3)));
        assertTrue(knight.canMoveTo(new Location(1,2)));
        assertTrue(knight.canMoveTo(new Location(2,1)));
        assertTrue(knight.canMoveTo(new Location(4,5)));
    }
}