package BoardAndGame;

import ChessPieces.Color;
import ChessPieces.Location;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by admin on 2/3/16.
 */
public class BoardTest {

    Board board = new Board(8, 8);

    @Test
    public void testgoTo() throws Exception {
        assertFalse(board.goTo(new Location(1, 1), new Location(1, 5)));
        assertFalse(board.goTo(new Location(2, 2), new Location(0, 0)));
        assertTrue(board.goTo(new Location(3, 3), new Location(1, 5)));
        assertTrue(board.goTo(new Location(2, 2), new Location(2, 4)));
        assertTrue(board.goTo(new Location(2, 4), new Location(2, 5)));


    }

    @Test
    public void testNoPieceInTheMiddleStraight() throws Exception {
        board = new Board(8, 8);
        assertTrue(board.goTo(new Location(1, 2), new Location(1, 4)));
        assertTrue(board.noBarrier(new Location(1, 2), new Location(1, 4)));
        assertFalse(board.noBarrier(new Location(3, 7), new Location(3, 2)));
        assertFalse(board.noBarrier(new Location(2, 3), new Location(2, 1)));
        assertFalse(board.noBarrier(new Location(7, 7), new Location(1, 7)));
        assertTrue(board.noBarrier(new Location(3, 5), new Location(5, 7)));
        assertFalse(board.noBarrier(new Location(1, 3), new Location(4, 6)));
        assertFalse(board.noBarrier(new Location(1, 1), new Location(3, 3)));
        assertFalse(board.noBarrier(new Location(3, 1), new Location(5, 3)));
    }


    @Test
    public void testIsInsideBoard() throws Exception {
        assertTrue(board.isInsideBoard(new Location(5, 5)));
        assertFalse(board.isInsideBoard(new Location(0, 0)));
        assertFalse(board.isInsideBoard(new Location(9, 9)));
    }

    @Test
    public void testInCheck() throws Exception {
        board = new Board(8, 8);
        board.testGoTo(new Location(5, 1), new Location(5, 4));
        assertFalse(board.inCheck(board.getPiece(new Location(5, 4)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        board.testGoTo(new Location(5, 4), new Location(5, 6));
        assertTrue(board.inCheck(board.getPiece(new Location(5, 6)), board.testGetBlackPieces(), board.testGetWhitePieces()));
    }

    @Test
    public void testCheckMateWithOpponent() throws Exception {
        board = new Board(8, 8);
        board.testGoTo(new Location(5, 1), new Location(5, 5));
        assertTrue(board.canMoveCrash(new Location(5, 7), new Location(5, 5), board.testGetWhitePieces()));
        assertFalse(board.inCheck(board.getPiece(new Location(5, 5)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        board.testGoTo(new Location(5, 5), new Location(5, 6));
        assertTrue(board.inCheck(board.getPiece(new Location(5, 6)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        board.testGoTo(new Location(1, 1), new Location(1, 6));
        board.testGoTo(new Location(5, 6), new Location(5, 5));
        assertFalse(board.canMoveCrash(new Location(5, 7), new Location(5, 5), board.testGetWhitePieces()));
        board.testGoTo(new Location(1, 6), new Location(1, 5));
        assertTrue(board.canMoveCrash(new Location(5, 7), new Location(5, 5), board.testGetWhitePieces()));
        assertFalse(board.checkMate(board.getPiece(new Location(5, 5)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        board.testGoTo(new Location(4, 1), new Location(5, 3));
        assertFalse(board.checkMate(board.getPiece(new Location(5, 5)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        board.testGoTo(new Location(5, 3), new Location(4, 5));
        System.out.println(board.getPiece(new Location(4, 5)).getClass().getSimpleName());
        assertTrue(board.canMoveTo(new Location(4, 5), new Location(5, 6)));
        assertFalse(board.checkMate(board.getPiece(new Location(5, 5)), board.testGetBlackPieces(), board.testGetWhitePieces()));
    }


    @Test
    public void testCaptureOpponent() throws Exception {
        System.out.println(board.getPiece(new Location(3, 7)).getClass().getSimpleName());
        System.out.println(board.getPiece(new Location(4, 8)).getClass().getSimpleName());
        assertFalse(board.captureOpponent(new Location(1, 2), new Location(1, 7)));
        assertFalse(board.goTo(new Location(3, 2), new Location(3, 4)));
        assertFalse(board.goTo(new Location(3, 4), new Location(3, 5)));
        assertTrue(board.goTo(new Location(3, 5), new Location(3, 6)));
        assertFalse(board.captureOpponent(new Location(3, 6), new Location(3, 7)));
        assertFalse(board.captureOpponent(new Location(3, 7), new Location(4, 8)));
        assertFalse(board.captureOpponent(new Location(4, 8), new Location(5, 8)));
        board.testGoTo(new Location(4, 1), new Location(6, 8));
        assertTrue(board.captureOpponent(new Location(6, 8), new Location(6, 7)));
        assertFalse(board.captureOpponent(new Location(6, 8), new Location(5, 5)));
        System.out.println(board.getPiece(new Location(4, 8)).getClass().getSimpleName());
        board.testGoTo(new Location(4, 8), new Location(4, 5));
        board.testGoTo(new Location(4, 7), new Location(4, 2));
        System.out.println("here we are");
        assertFalse(board.captureOpponent(new Location(4, 5), new Location(4, 2)));
        assertTrue(board.captureOpponent(new Location(4, 2), new Location(3, 1)));
        // start test 2
        board = new Board(8, 8);
        board.testGoTo(new Location(5, 8), new Location(5, 3));
        System.out.println(board.getPiece(new Location(5, 3)).getClass().getSimpleName());
        System.out.println(board.getPiece(new Location(5, 2)).getClass().getSimpleName());
        assertTrue(board.captureOpponent(new Location(5, 3), new Location(5, 2)));
        assertTrue(board.captureOpponent(new Location(5, 2), new Location(4, 2)));
        board.testGoTo(new Location(4, 2), new Location(5, 3));
        assertTrue(board.captureOpponent(new Location(6, 2), new Location(5, 3)));
    }

    @Test
    public void testCheckMate() throws Exception {
        board.testGoTo(new Location(5, 1), new Location(5, 6));
        System.out.println(board.getPiece(new Location(5, 6)).getClass().getSimpleName());
        assertTrue(board.inCheck(board.getPiece(new Location(5, 6)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        assertFalse(board.checkMate(Color.BLACK));
        assertTrue(board.checkMate(Color.WHITE));
        board.testGoTo(new Location(5, 6), new Location(5, 4));
        assertFalse(board.checkMate(Color.BLACK));
        assertFalse(board.checkMate(Color.WHITE));
        board.testGoTo(new Location(5, 4), new Location(5, 5));
        board.testGoTo(new Location(1, 1), new Location(1, 5));
        assertFalse(board.checkMate(Color.WHITE));
        GUIBoard GUIb = new GUIBoard(board);

        board = new Board(8, 8);
        board.testGoTo(new Location(5, 1), new Location(5, 5));
        board.testGoTo(new Location(4, 1), new Location(5, 4));
        board.testGoTo(new Location(5, 7), new Location(5, 6));
        board.testGoTo(new Location(4, 7), new Location(4, 6));
        board.testGoTo(new Location(6, 7), new Location(6, 6));
        assertFalse(board.inCheck(board.getPiece(new Location(5, 5)), board.testGetBlackPieces(), board.testGetWhitePieces()));
        assertFalse(board.checkMate(Color.WHITE));

        board = new Board(8, 8);
        board.testGoTo(new Location(5, 1), new Location(6, 6));
        assertTrue(board.checkMate(Color.WHITE));
        board.testGoTo(new Location(6, 6), new Location(5, 5));

        board = new Board(8, 8);
        board.testGoTo(new Location(5, 8), new Location(6, 3));
        assertTrue(board.checkMate(Color.BLACK));
        board.testGoTo(new Location(6, 3), new Location(6, 4));
        board.testGoTo(new Location(4, 1), new Location(6, 3));
        board.testGoTo(new Location(6, 1), new Location(5, 5));
        assertFalse(board.checkMate(Color.BLACK));

    }

    @Test
    public void testCanMoveTo() throws Exception {
        assertFalse(board.canMoveTo(new Location(6, 2), new Location(6, 3)));
        assertFalse(board.canMoveTo(new Location(6, 2), new Location(6, 4)));
        assertFalse(board.canMoveTo(new Location(6, 1), new Location(6, 3)));
    }

    @Test
    public void testCanMoveToWithBlock() throws Exception {
        assertFalse(board.canMoveCrash(new Location(7, 7), new Location(7, 6), board.testGetWhitePieces()));
        board.testGoTo(new Location(2, 1), new Location(3, 3));
        board.testGoTo(new Location(6, 1), new Location(6, 3));
        board.testGoTo(new Location(6, 8), new Location(4, 5));
        assertFalse(board.canMoveCrash(new Location(4, 5), new Location(3, 3), board.testGetWhitePieces()));
        board = new Board(8, 8);
    }

}