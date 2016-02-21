package ChessPieces;

/**
 * Created by Blaks on 1/29/16.
 */

/* this class represents each piece on the board.*/
public abstract class Piece {
    protected Color color;
    protected Location currentLocation;


    public Piece(Color col, Location location) {
        color = col;
        currentLocation = location;
    }

    /*this function returns the color of the piece*/
    public Color getColor() {
        return color;
    }

    public abstract boolean canMoveTo(Location a);

    /* this function returns the location of King*/
    public Location getLoc() {
        return currentLocation;
    }

    /*this function chanegs the currentlcoaiton*/
    public void setLoc(Location location) {
        currentLocation = location;
    }

}