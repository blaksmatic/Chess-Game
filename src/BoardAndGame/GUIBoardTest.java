package BoardAndGame;


import ChessPieces.Color;
import ChessPieces.Location;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by admin on 2/10/16.
 */
public class GUIBoardTest {

    @Test
    public void testGUI() throws Exception {
        GUIBoard GUIboard = new GUIBoard(new Board(8, 8));
        Board board = GUIboard.getBoard();
        GUIboard.clickLocation(new Location(1, 2));
        assertFalse(GUIboard.clickLocation(new Location(1, 7)));
        GUIboard.clickLocation(new Location(3, 2));
        assertFalse(GUIboard.clickLocation(new Location(3, 4)));
        GUIboard.clickLocation(new Location(3, 4));
        assertTrue(GUIboard.clickLocation(new Location(3, 5)));
        assertTrue(GUIboard.clickLocation(new Location(3, 6)));
        assertTrue(GUIboard.clickLocation(new Location(4, 2)));
        assertTrue(GUIboard.clickLocation(new Location(4, 4)));

        // test undo
        GUIboard = new GUIBoard(new Board(8, 8));
        board = GUIboard.getBoard();
        assertTrue(GUIboard.clickLocation(new Location(7, 2)));
        assertTrue(GUIboard.clickLocation(new Location(7, 3)));
        GUIboard.undo();
        assertTrue(board.goTo(new Location(7, 2), new Location(7, 3)));
        GUIboard.clickLocation(new Location(2, 2));
        assertTrue(GUIboard.clickLocation(new Location(2, 4)));
        GUIboard.undo();
        GUIboard.clickLocation(new Location(2, 2));
        assertTrue(GUIboard.clickLocation(new Location(2, 4)));


        //another test for undo
        GUIboard = new GUIBoard(new Board(8, 8));
        board = GUIboard.getBoard();
        GUIboard.clickLocation(new Location(2, 2));
        assertTrue(GUIboard.clickLocation(new Location(2, 4)));
        GUIboard.clickLocation(new Location(2, 7));
        assertTrue(GUIboard.clickLocation(new Location(2, 5)));
        GUIboard.clickLocation(new Location(2, 4));
        assertTrue(GUIboard.clickLocation(new Location(2, 5)));
        GUIboard.undo();
        GUIboard.clickLocation(new Location(2, 4));
        assertTrue(GUIboard.clickLocation(new Location(2, 5)));

        //another test for game over
        GUIboard = new GUIBoard(new Board(8, 8));
        board = GUIboard.getBoard();
        GUIboard.GameOver(Color.WHITE);
        assertEquals(GUIboard.GlassScore, 1);
        assertEquals(GUIboard.WoodScore, 0);
        GUIboard.GameOver(Color.BLACK);
        assertEquals(GUIboard.GlassScore, 1);
        assertEquals(GUIboard.WoodScore, 1);
        GUIboard.GameOver(Color.WHITE);
        assertEquals(GUIboard.GlassScore, 2);
        assertEquals(GUIboard.WoodScore, 1);
        GUIboard.GameOver(Color.BLACK);
        assertEquals(GUIboard.GlassScore, 2);
        assertEquals(GUIboard.WoodScore, 2);

        //another Test for the peace
        GUIboard.seekForPeace();
        GUIboard.loseTheGame(Color.WHITE);
        GUIboard.loseTheGame(Color.BLACK);

        //Some specific test
        GUIboard = new GUIBoard(new Board(8, 8));
        board = GUIboard.getBoard();
        assertTrue(GUIboard.clickLocation(new Location(4, 2)));
        assertTrue(GUIboard.clickLocation(new Location(4, 3)));
        assertTrue(GUIboard.clickLocation(new Location(8, 7)));
        assertTrue(GUIboard.clickLocation(new Location(8, 6)));
        assertTrue(GUIboard.clickLocation(new Location(2, 2)));
        assertTrue(GUIboard.clickLocation(new Location(2, 3)));
        assertTrue(GUIboard.clickLocation(new Location(3, 7)));
        assertTrue(GUIboard.clickLocation(new Location(3, 6)));
        assertTrue(GUIboard.clickLocation(new Location(6, 3)));
        ArrayList<Location> a = board.getRoutes(new Location(6, 3), new Location(2, 7));
        System.out.println(a.size());
        System.out.println(a.get(0).getX() + " " + a.get(0).getY() + a.get(1).getX() + " " + a.get(1).getY() + a.get(2).getX() + " " + a.get(2).getY());
        System.out.println(board.getPiece(a.get(0)) + " " + board.getPiece(a.get(1)) + " " + board.getPiece(a.get(2)) + " " + board.getPiece(new Location(3, 7)) + " " + board.getPiece(new Location(3, 6)));


        assertFalse(GUIboard.clickLocation(new Location(2, 7)));

        GUIboard = new GUIBoard(new Board(8, 8));
        board = GUIboard.getBoard();
        // Another interesting test
        assertTrue(GUIboard.clickLocation(new Location(2, 2)));
        assertTrue(GUIboard.clickLocation(new Location(2, 4)));
        assertTrue(GUIboard.clickLocation(new Location(4, 7)));
        assertTrue(GUIboard.clickLocation(new Location(4, 5)));
        assertTrue(GUIboard.clickLocation(new Location(6, 3)));
        assertTrue(GUIboard.clickLocation(new Location(2, 4)));

        GUIboard = new GUIBoard(new Board(8, 8));
        board = GUIboard.getBoard();
        //TestCases 5,6
        assertTrue(GUIboard.clickLocation(new Location(4,2)));
        assertTrue(GUIboard.clickLocation(new Location(4,4)));
        assertTrue(GUIboard.clickLocation(new Location(1,4)));
        assertTrue(GUIboard.clickLocation(new Location(1,4)));
        assertTrue(GUIboard.clickLocation(new Location(3,5)));
        assertTrue(GUIboard.clickLocation(new Location(3,6)));
        assertTrue(GUIboard.clickLocation(new Location(4,1)));
        assertTrue(GUIboard.clickLocation(new Location(4,3)));
        assertTrue(GUIboard.clickLocation(new Location(5,7)));
        assertTrue(GUIboard.clickLocation(new Location(5,5)));
        ArrayList<Location> r = board.getRoutes(new Location(6, 3), new Location(2, 7));
        assertTrue(GUIboard.clickLocation(new Location(6,3)));
        assertTrue(GUIboard.clickLocation(new Location(3,6)));
        assertTrue(GUIboard.clickLocation(new Location(3,7)));
        assertTrue(GUIboard.clickLocation(new Location(3,6)));
        ArrayList<Location> w = board.getRoutes(new Location(3, 3), new Location(4, 7));
        assertTrue(GUIboard.clickLocation(new Location(4,4)));
        assertTrue(GUIboard.clickLocation(new Location(5,5)));
        assertTrue(GUIboard.clickLocation(new Location(6,7)));
        assertTrue(GUIboard.clickLocation(new Location(6,6)));
        assertTrue(GUIboard.clickLocation(new Location(5,5)));
        assertTrue(GUIboard.clickLocation(new Location(5,6)));
        assertTrue(GUIboard.clickLocation(new Location(4,7)));
        assertTrue(GUIboard.clickLocation(new Location(5,6)));
        assertTrue(GUIboard.clickLocation(new Location(4,3)));
        assertTrue(GUIboard.clickLocation(new Location(4,8)));
        assertTrue(GUIboard.clickLocation(new Location(3,6)));
        assertTrue(GUIboard.clickLocation(new Location(3,5)));
        assertTrue(GUIboard.clickLocation(new Location(3,3)));
        assertTrue(GUIboard.clickLocation(new Location(5,5)));
        assertTrue(GUIboard.clickLocation(new Location(6,5)));
        assertTrue(GUIboard.clickLocation(new Location(3,2)));
        assertTrue(GUIboard.clickLocation(new Location(5,5)));
        assertTrue(GUIboard.clickLocation(new Location(4,6)));
        assertTrue(GUIboard.clickLocation(new Location(6,6)));
        assertTrue(GUIboard.clickLocation(new Location(6,5)));
        assertTrue(GUIboard.clickLocation(new Location(6,2)));
        assertTrue(GUIboard.clickLocation(new Location(6,4)));
        ArrayList<Location> c = board.getRoutes(new Location(6, 3), new Location(2, 7));
        assertTrue(GUIboard.clickLocation(new Location(6,5)));
        assertTrue(GUIboard.clickLocation(new Location(6,4)));
        assertTrue(GUIboard.clickLocation(new Location(7,2)));
        assertTrue(GUIboard.clickLocation(new Location(7,4)));
        assertTrue(GUIboard.clickLocation(new Location(5,6)));
        assertTrue(GUIboard.clickLocation(new Location(5,5)));
        assertTrue(GUIboard.clickLocation(new Location(7,4)));
        assertTrue(GUIboard.clickLocation(new Location(7,5)));
        assertTrue(GUIboard.clickLocation(new Location(7,5)));
        assertTrue(GUIboard.clickLocation(new Location(7,6)));
        assertTrue(GUIboard.clickLocation(new Location(6,4)));
        assertTrue(GUIboard.clickLocation(new Location(6,3)));
        assertTrue(GUIboard.clickLocation(new Location(7,5)));
        assertTrue(GUIboard.clickLocation(new Location(7,6)));
        assertTrue(GUIboard.clickLocation(new Location(7,7)));
        assertTrue(GUIboard.clickLocation(new Location(7,6)));
        assertTrue(GUIboard.clickLocation(new Location(8,2)));
        assertTrue(GUIboard.clickLocation(new Location(8,4)));
        assertTrue(GUIboard.clickLocation(new Location(3,5)));
        assertTrue(GUIboard.clickLocation(new Location(3,4)));
        assertTrue(GUIboard.clickLocation(new Location(2,2)));
        assertTrue(GUIboard.clickLocation(new Location(2,4)));
        assertTrue(GUIboard.clickLocation(new Location(7,6)));
        assertTrue(GUIboard.clickLocation(new Location(7,5)));
        assertTrue(GUIboard.clickLocation(new Location(8,4)));
        assertTrue(GUIboard.clickLocation(new Location(7,5)));
        assertTrue(GUIboard.clickLocation(new Location(7,8)));
        assertTrue(GUIboard.clickLocation(new Location(6,6)));
        assertTrue(GUIboard.clickLocation(new Location(7,5)));
        assertTrue(GUIboard.clickLocation(new Location(6,6)));

    }
}