package ChessPieces;

/**
 * Created by admin on 2/8/16.
 */
public class ChessK extends Piece {

    public ChessK(Color col, Location loc) {
        super(col, loc);
    }

    /*Bishiop moves in a diagonal*/
    public boolean canMoveTo(Location dest) {
        Movement movement = new Movement();
        if (color == Color.WHITE)
            return movement.isMovingDiagonalUp(currentLocation, dest) || movement.isMovingStraightDown(currentLocation, dest);
        else
            return movement.isMovingDiagonalDown(currentLocation, dest) || movement.isMovingStraightUp(currentLocation, dest);
    }

}
