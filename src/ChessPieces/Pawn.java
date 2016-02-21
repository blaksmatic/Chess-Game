package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */
public class Pawn extends Piece {

    public boolean firstMove;

    public Pawn(Color col, Location loc) {
        super(col, loc);
        firstMove = true;
    }

    public boolean canMoveTo(Location dest) {
        Movement movement = new Movement();
        int moveDistance = 1;
        if (firstMove) {
            moveDistance = 2;
        }
        if (color == Color.WHITE) {
            //if the color is white, it means the front is up.
            if (movement.isMovingStraightUp(currentLocation, dest) && movement.distanceY(currentLocation, dest) <= moveDistance)
                return true;
            else
                return false;
        } else {// this is the case where color is black and you need to move down instead of up
            if (movement.isMovingStraightDown(currentLocation, dest) && movement.distanceY(currentLocation, dest) <= moveDistance)
                return true;
            else
                return false;
        }

    }

    public boolean canMoveToCapture(Location dest) {
        Movement movement = new Movement();
        if (color == Color.WHITE) {
            if (movement.isMovingDiagonalUp(currentLocation, dest) && movement.diagonalDistance(currentLocation, dest) == 1)
                return true;
            else
                return false;
        } else {
            if (movement.isMovingDiagonalDown(currentLocation, dest) && movement.diagonalDistance(currentLocation, dest) == 1)
                return true;
            else
                return false;
        }

    }
    //change the first move to false
    public void setFirstMove() {
        firstMove = false;
    }

}
