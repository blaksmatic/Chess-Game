package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */
public class Queen extends Piece {

    public Queen(Color col, Location loc) {
        super(col, loc);
    }

    public boolean canMoveTo(Location dest) {
        Movement movement = new Movement();
        return movement.isMovingDiagonal(currentLocation, dest) || movement.isMovingStraight(currentLocation, dest);
    }


}
