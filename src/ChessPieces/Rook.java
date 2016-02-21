package ChessPieces;

/**
 * Created by admin on 1/29/16.
 */
public class Rook extends Piece {

    public Rook(Color col, Location loc){
    super(col,loc);
    }

    public boolean canMoveTo(Location dest){
        Movement movement = new Movement();
        return movement.isMovingStraight(currentLocation,dest);
    }


}
