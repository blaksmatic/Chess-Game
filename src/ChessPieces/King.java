package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */
public class King extends Piece {

    private boolean castling;

    public King(Color col, Location loc) {
        super(col,loc);
        castling = false;
    }

    /**King can move any direction for 1 distance*/
    public boolean canMoveTo(Location dest) {
        Movement movement = new Movement();
        if (movement.diagonalDistance(currentLocation, dest) == 1)
            return true;
        else {
            int Xdistance = movement.distanceX(currentLocation, dest);
            int Ydistance = movement.distanceY(currentLocation, dest);
            if ((Xdistance == 0 && Ydistance == 1) || (Xdistance == 1 && Ydistance == 0))
                return true;
        }
        return false;

    }

    /* check if King has castling chance*/
    public boolean canCastling() {
        return !castling;
    }

}
