package BoardAndGame;

import ChessPieces.Color;
import ChessPieces.Location;
import ChessPieces.Piece;

/**
 * Created by admin on 1/29/16.
 */

/*the square class represents the squares on the board*/
public class Square {
    private Location location;
    private Color color;
    private Piece piece;


    public Square(Location loc, Color col) {
        this.location = loc;
        color = col;
        piece = null;
    }

    public void deletePiece() {
        piece = null;
    }

    public void setPiece(Piece newPiece) {
        piece = newPiece;

    }


    public void setColor(Color col) {
        color = col;
    }

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        if (hasPiece())
            return piece;
        else
            return null;
    }

    public boolean hasPiece() {
        return piece != null;
    }
}

