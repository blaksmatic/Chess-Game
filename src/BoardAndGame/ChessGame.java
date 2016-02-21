package BoardAndGame;

import ChessPieces.Color;
import ChessPieces.Location;

/**
 * Created by admin on 1/29/16.
 */

/*this is the main function of the game. */
public class ChessGame {

    private int score;
    private Color turn;
    private Location myPreviousLocation;
    private Location opponentPreviousLocation;

    private void InitializeGame() {
        turn = Color.WHITE;
        score = 0;
    }

    public static void main(String args[]) {
        GUIBoard board = new GUIBoard(new Board(8, 8));

    }

    public void nextTurn() {
        if (turn == Color.WHITE)
            turn = Color.BLACK;
        else
            turn = Color.WHITE;
    }

}
