package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */
/* this class is the color of the Piece of chess. It can be either black or white */

public enum Color {
    // to simplify the naming process, black also means dark and white means light in the board.
    BLACK, WHITE;

    public boolean isDifferent(Color a, Color b) {
        return a != b;
    }

    public boolean isWhite(Color a) {
        return a == WHITE;
    }

    public boolean isBlack(Color a) {
        return a == BLACK;
    }

}
