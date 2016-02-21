package ChessPieces;

/**
 * Created by admin on 1/29/16.
 * this is the class of Bishop
 */
public class Bishop extends Piece {

    public Bishop(Color col, Location loc) {
        super(col, loc);
    }

    /*Bishiop moves in a diagonal*/
    public boolean canMoveTo(Location dest) {
        Movement movement = new Movement();
        return movement.isMovingDiagonal(currentLocation, dest);
    }

}
