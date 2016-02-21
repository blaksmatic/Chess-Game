package ChessPieces;


/**
 * Created by admin on 1/29/16.
 */
public class Location {
    private int x;
    private int y;

    public Location(int X, int Y) {
        x = X;
        y = Y;
    }

    public Location(Location location) {
        x = location.getX();
        y = location.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
