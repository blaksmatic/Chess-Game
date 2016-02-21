package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */

/* the knight class*/
public class Knight extends Piece {

    public Knight(Color col, Location loc) {
        super(col, loc);
    }

    public boolean canMoveTo(Location dest) {
        Movement movement = new Movement();
        return movement.isMovingLShape(currentLocation, dest);
    }

}
