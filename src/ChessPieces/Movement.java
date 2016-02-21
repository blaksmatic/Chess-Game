package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */


/*this class contains all kinds of ovement and can return if a certain movement occurs, based on the
* starting location and the ending location.*/

public class Movement {

    /* check if a Piece is moving straight up*/
    public boolean isMovingStraightUp(Location start, Location dest) {
        return (start.getX() == dest.getX() && start.getY() < dest.getY());
    }

    /* check if a Piece is moving straight down*/
    public boolean isMovingStraightDown(Location start, Location dest) {
        return (start.getX() == dest.getX() && start.getY() > dest.getY());
    }

    public boolean isMovingStraight(Location start, Location dest) {
        return isMovingStraightUp(start, dest) || isMovingStraightDown(start, dest) || start.getY() == dest.getY();
    }

    /* check if it is moving diagonally*/
    public boolean isMovingDiagonal(Location start, Location dest) {
        return (Math.abs(dest.getY() - start.getY()) == Math.abs(dest.getX() - start.getX()) && !(start.getX() == dest.getX() || start.getY() == dest.getY()));
    }

    /*this is the case exclusively for the pawn which can only walk up or down*/
    public boolean isMovingDiagonalUp(Location start, Location dest) {
        return this.isMovingDiagonal(start, dest) && (start.getY() < dest.getY());
    }

    /*this is the case exclusively for the pawn which can only walk up or down*/
    public boolean isMovingDiagonalDown(Location start, Location dest) {
        return this.isMovingDiagonal(start, dest) && (start.getY() > dest.getY());
    }

    /* check if is moving in a L shape*/
    public boolean isMovingLShape(Location start, Location dest) {
        int distanceX = distanceX(start, dest);
        int distanceY = distanceY(start, dest);
        return (distanceX == 2 && distanceY == 1) || (distanceX == 1 && distanceY == 2);
    }

    /**
     * get the distance between two locations
     * @param a
     * @param b
     * @return
     */
    public int distanceX(Location a, Location b) {
        return Math.abs(a.getX() - b.getX());
    }

    public int distanceY(Location a, Location b) {
        return Math.abs(a.getY() - b.getY());
    }

    /* one should only use diagonal distance when they are sure it is moving diagonal, or it will return 0 */
    public int diagonalDistance(Location a, Location b) {
        if (this.isMovingDiagonal(a, b))
            return Math.abs(a.getY() - b.getY());
        else
            return 0;
    }
}
