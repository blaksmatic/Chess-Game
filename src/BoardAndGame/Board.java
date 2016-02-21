package BoardAndGame;

import ChessPieces.*;

import java.util.ArrayList;

/**
 * Created by Blaks on 1/29/16.
 */
public class Board {
    // all the square are initiated in array
    protected Square[][] squares;
    //the number of columns in this board
    protected int column;
    // the number of rows in a column
    protected int row;
    // the arrayist of white Piece
    protected ArrayList<Piece> whitePieces;
    //the arraylist of black pieces
    protected ArrayList<Piece> blackPieces;
    //store the king from both sides
    protected Piece whiteKing;
    protected Piece blackKing;

    /*the initialization of the board. Note that we will use 1 to the column number instead of 0 to column-1*/
    public Board(int x, int y) {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        column = x;
        row = y;
        squares = new Square[column + 1][row + 1];
        this.giveSquareColorsAndLocation();
        this.initilizeChessPieces();
        this.initializePieces();

    }

    /**
     * This function gives colors to the squares
     */
    /*Note that the squares start from 1,1 and ends at row and column. Zero is not used in the
     *the game, because it will confuse the progrmmmers in the future*/
    private void giveSquareColorsAndLocation() {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                squares[i][j] = new Square(new Location(i, j), Color.BLACK);
                if ((i + j) % 2 == 0)
                    squares[i][j].setColor(Color.BLACK);
                else
                    squares[i][j].setColor(Color.WHITE);
            }
        }
    }

    /**
     * This function initialize the chess pieces and put them on the board
     */
    private void initilizeChessPieces() {
        //set up the Rook on the second line on both side
        for (int i = 1; i <= column; i++) {
            squares[i][2].setPiece(new Pawn(Color.WHITE, new Location(i, 2)));
            squares[i][row - 1].setPiece(new Pawn(Color.BLACK, new Location(i, row - 1)));
        }


        //set up other Pieces for White
        squares[1][1].setPiece(new Rook(Color.WHITE, new Location(1, 1)));
        squares[2][1].setPiece(new Knight(Color.WHITE, new Location(2, 1)));
        squares[3][1].setPiece(new Bishop(Color.WHITE, new Location(3, 1)));
        squares[4][1].setPiece(new Queen(Color.WHITE, new Location(4, 1)));
        squares[5][1].setPiece(new King(Color.WHITE, new Location(5, 1)));
        squares[6][1].setPiece(new Bishop(Color.WHITE, new Location(6, 1)));
        squares[7][1].setPiece(new Knight(Color.WHITE, new Location(7, 1)));
        squares[8][1].setPiece(new Rook(Color.WHITE, new Location(8, 1)));
        squares[6][3].setPiece(new ChessJ(Color.WHITE, new Location(6, 3)));
        squares[3][3].setPiece(new ChessK(Color.WHITE, new Location(3, 3)));

        //set up some Pieces in Black
        squares[1][row].setPiece(new Rook(Color.BLACK, new Location(1, row)));
        squares[2][row].setPiece(new Knight(Color.BLACK, new Location(2, row)));
        squares[3][row].setPiece(new Bishop(Color.BLACK, new Location(3, row)));
        squares[4][row].setPiece(new Queen(Color.BLACK, new Location(4, row)));
        squares[5][row].setPiece(new King(Color.BLACK, new Location(5, row)));
        squares[6][row].setPiece(new Bishop(Color.BLACK, new Location(6, row)));
        squares[7][row].setPiece(new Knight(Color.BLACK, new Location(7, row)));
        squares[8][row].setPiece(new Rook(Color.BLACK, new Location(8, row)));
        squares[6][5].setPiece(new ChessJ(Color.BLACK, new Location(6, 5)));
        squares[3][5].setPiece(new ChessK(Color.BLACK, new Location(3, 5)));

        whiteKing = squares[5][1].getPiece();
        blackKing = squares[5][row].getPiece();
    }

    /*this function loads pieces into the arrays for us to check*/
    private void initializePieces() {
        for (int i = 1; i <= column; i++)
            for (int j = 1; j <= row; j++) {
                if (squares[i][j].hasPiece()) {
                    if (squares[i][j].getPiece().getColor() == Color.WHITE) {
                        whitePieces.add(squares[i][j].getPiece());
                    } else {
                        blackPieces.add(squares[i][j].getPiece());
                    }
                }
            }
    }

    /**
     * @param start
     * @param dest
     * @return
     */
    /*this function checks whether there is another chess in the middle of the movement
    * this function will be called after checking straight and diagnol*/
    public boolean noBarrier(Location start, Location dest) {
        ArrayList<Location> locations = getRoutes(start, dest);
        for (Location loc : locations) {
            if (getSquare(loc).hasPiece())
                return false;
        }
        return true;
    }


    /*it will return if a Piece is inside the board*/
    public boolean isInsideBoard(Location dest) {
        return (1 <= dest.getY() && dest.getY() <= row && 1 <= dest.getX() && dest.getX() <= column);
    }

    /**
     * this function returns whether one can capture your opponent's Piece. It will return true it succeed
     *
     * @param start
     * @param dest
     * @return
     */
    public boolean captureOpponent(Location start, Location dest) {
        if (!this.canMoveTo(start, dest)) {
            return false;
        }
        if (isOpponent(start, dest)) {
            if (getPiece(dest).getColor() == Color.WHITE)
                whitePieces.remove(getSquare(dest).getPiece());
            else
                blackPieces.remove(getSquare(dest).getPiece());
            getPiece(start).setLoc(dest);
            getSquare(dest).setPiece(getPiece(start));
            getSquare(start).deletePiece();
            if (getPiece(dest) instanceof Pawn)
                ((Pawn) getPiece(dest)).setFirstMove();

            return true;
        }
        return false;
    }

    /**
     * @param start
     * @param dest
     * @return
     */
    /*From a position goto another position*/
    public boolean goTo(Location start, Location dest) {
        if (this.canMoveTo(start, dest)) {
            getPiece(start).setLoc(dest);
            getSquare(dest).setPiece(getPiece(start));
            getSquare(start).deletePiece();
            if (getPiece(dest) instanceof Pawn)
                ((Pawn) getPiece(dest)).setFirstMove();
            return true;
        }
        return false;
    }

    /**
     * @param OpponentColor
     * @return
     */
    /*this function checks whether it is possible to checkmate an Opponent's King*/
    public boolean checkMate(Color OpponentColor) {

        /*firstly we need to get the opponent King and distinguish which side we are in*/
        Piece opponentKing;
        ArrayList<Piece> myPieces;
        ArrayList<Piece> opponentPieces;
        if (OpponentColor == Color.WHITE) {
            opponentKing = whiteKing;
            myPieces = blackPieces;
            opponentPieces = whitePieces;
        } else {
            opponentKing = blackKing;
            myPieces = whitePieces;
            opponentPieces = blackPieces;
        }
        return inCheck(opponentKing, myPieces, opponentPieces) && checkMate(opponentKing, myPieces, opponentPieces);
    }


    /**
     * @param oppoKing
     * @param myPieces
     * @param oppoPiece
     * @return
     */
    /*this function will only be called after verifying checkmate with single king*/
    public boolean checkMate(Piece oppoKing, ArrayList<Piece> myPieces, ArrayList<Piece> oppoPiece) {
    /*when arriving here, it means all positions can be reached by opponents. Then we need to consider if your pieces can block the way*/
        for (Piece piece : myPieces) {
            if (canMoveTo(piece.getLoc(), oppoKing.getLoc()) && !canMoveCrash(piece.getLoc(), oppoKing.getLoc(), oppoPiece)) {
                //now check if there is another of my chess can reach the king directly
                for (Piece anotherPiece : myPieces) {
                    if (piece != anotherPiece) {
                        if (canMoveTo(anotherPiece.getLoc(), oppoKing.getLoc())) {
                            return true;
                        }
                    }
                }
            }
        }
        //this means the block succeed.
        return false;
    }

    /**
     * /*then we check if the opponent king cannot move to a safe place.
     * every square around it can be attacked
     * another situation is that, there are more than one Piece on the board, so the king can stay where it is and
     * do not have to move.
     */
        /* ther are two situations where checkmate is valid. firstly, no pieces can blcok in the middle of the attack, so
        * the king has to move or stay. The second situation is that one Piece can block, but another opponent Piece can
        * checkmate the king.
     * @param opponentKing
     * @param myPieces
     * @param opponentPieces
     * @return
     */
    public boolean inCheck(Piece opponentKing, ArrayList<Piece> myPieces, ArrayList<Piece> opponentPieces) {
        ArrayList<Location> allLoc = this.getAroundLoc(opponentKing.getLoc());
        if (opponentPieces.size() > 1)
            allLoc.add(opponentKing.getLoc());
        int possibility = 0;
        for (Location location : allLoc) {
            for (Piece piece : myPieces) {//check out if some of my pieces can hit that position
                if (canMoveTo(piece.getLoc(), location)) {
                    possibility++;
                    break;
                }
            }
        }
        return (possibility == allLoc.size());
    }

    /**
     * This is a poly function aims at simplifing the possible
     * difficult of calling it outside this class. It will called by the GUIboard.
     *
     * @param OpponentColor
     * @return
     */

    public boolean inCheck(Color OpponentColor) {
        Piece opponentKing;
        ArrayList<Piece> myPieces;
        ArrayList<Piece> opponentPieces;
        if (OpponentColor == Color.WHITE) {
            opponentKing = whiteKing;
            myPieces = blackPieces;
            opponentPieces = whitePieces;
        } else {
            opponentKing = blackKing;
            myPieces = whitePieces;
            opponentPieces = blackPieces;
        }
        return inCheck(opponentKing, myPieces, opponentPieces);
    }

    /**
     * this function checks whether a Piece on a place can move to another place
     *
     * @param start
     * @param dest
     * @return
     */
    public boolean canMoveTo(Location start, Location dest) {
        if (!getSquare(start).hasPiece()) {
            return false;
        }
        Piece myPiece = getPiece(start);
        if (!this.isInsideBoard(dest))//check if it is out of border
        {
            return false;
        }
        if (getSquare(dest).hasPiece() && !isOpponent(start, dest))
            return false;
        if (myPiece instanceof Pawn) {
            Pawn pawn = (Pawn) myPiece;
            if (getSquare(dest).hasPiece() && (!isOpponent(start, dest)))
                return false;
            if (getSquare(dest).hasPiece() && isOpponent(start, dest)) {
                if (!(pawn.canMoveTo(dest) || pawn.canMoveToCapture(dest)))
                    return false;
            } else
                return (pawn.canMoveTo(dest) && noBarrier(start, dest));

        } else if (!myPiece.canMoveTo(dest)) {//check if it can move (vertically or diagonally)
            return false;
        }
        if (!(myPiece instanceof Knight))
            if (!noBarrier(start, dest)) {
                return false;
            }
        return true;
    }

    /*
    if (myPiece instanceof Pawn) {
            Pawn pawn = (Pawn) myPiece;
            if (getSquare(dest).hasPiece() && (!isOpponent(start, dest)))
            return false;
            if (getSquare(dest).hasPiece() && isOpponent(start, dest)) {
            if (!(pawn.canMoveTo(dest) || pawn.canMoveToCapture(dest)))
            return false;
            } else
            return (pawn.canMoveTo(dest) && noBarrier(start, dest));
    */
    /*this functions determines whether opponent Piece can block inside the location*/
    public boolean canMoveCrash(Location start, Location dest, ArrayList<Piece> opponentChess) {
        if (canMoveTo(start, dest)) {
            // first determine if opponent can capture my Piece
            for (Piece piece : opponentChess) {
                if (canMoveTo(piece.getLoc(), start))
                    return false;
            }
            if (getPiece(start) instanceof Knight)
                return true;// if it is a knight, no one can block it.
            ArrayList<Location> locations = getRoutes(start, dest);
            for (Piece opponent : opponentChess) {
                if (!(opponent instanceof King))// if it is not a king, then determine whether it can block
                    for (Location temp : locations)
                        if (canMoveTo(opponent.getLoc(), temp)) {
                            return false;
                        }
            }
            return true;
        }
        return false;
    }

    /**
     * Return the Piece
     *
     * @param location
     * @return
     */
    public Piece getPiece(Location location) {
        return squares[location.getX()][location.getY()].getPiece();
    }


    /**
     * Return the Square
     *
     * @param location
     * @return
     */
    public Square getSquare(Location location) {
        return squares[location.getX()][location.getY()];
    }

    /**
     * Return all the locations around a position. Will be used for King only
     *
     * @param location
     * @return
     */
    /* get the location around a Piece, used to determine the checkmate status*/
    public ArrayList<Location> getAroundLoc(Location location) {
        ArrayList<Location> toReturn = new ArrayList<>();
        for (int i = location.getX() - 1; i <= location.getX() + 1; i++)
            for (int j = location.getY() - 1; j <= location.getY() + 1; j++) {
                if (i != location.getX() && j != location.getY())
                    if (isInsideBoard(new Location(i, j)) && !(getSquare(new Location(i, j)).hasPiece()))
                        toReturn.add(new Location(i, j));
            }
        return toReturn;
    }

    /**
     * this function returns the chess between two pieces. This function will be used to check if one pieces can
     * reach another Piece
     *
     * @param start
     * @param dest
     * @return
     */

    public ArrayList<Location> getRoutes(Location start, Location dest) {
        Movement movement = new Movement();
        int startX = start.getX() < dest.getX() ? start.getX() : dest.getX();
        int destX = start.getX() > dest.getX() ? start.getX() : dest.getX();
        int startY = start.getY() < dest.getY() ? start.getY() : dest.getY();
        int destY = start.getY() > dest.getY() ? start.getY() : dest.getY();
        ArrayList<Location> toReturn = new ArrayList<>();

        if (movement.isMovingStraight(start, dest)) {
            if (startX == destX) {
                for (int i = startY + 1; i < destY; i++)
                    toReturn.add(new Location(startX, i));
            } else if (startY == destY) {
                for (int i = startX + 1; i < destX; i++)
                    toReturn.add(new Location(i, startY));
            }
            // now we put start at the top and dest at the buttom. order doesn't matter
        } else if (movement.isMovingDiagonal(start, dest)) {
            if (start.getY() < dest.getY()) {
                Location a = start;
                start = dest;
                dest = a;
            }
            if (start.getX() > dest.getX()) {
                for (int i = dest.getX() + 1, j = dest.getY() + 1; j < start.getY(); i++, j++)
                    toReturn.add(new Location(i, j));
            } else {
                for (int i = dest.getX() - 1, j = dest.getY() + 1; j < start.getY(); i--, j++)
                    toReturn.add(new Location(i, j));
            }
        }
        return toReturn;

    }

    /**
     * this function is for test only. It will forcefully move a Piece on a position to another position
     */
    public void testGoTo(Location start, Location dest) {
        getPiece(start).setLoc(dest);
        getSquare(dest).setPiece(getPiece(start));
        getSquare(start).deletePiece();
    }

    /**
     * Check whether two pieces have the different color.
     * If there is no chess there, then it is obvious no opponent
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isOpponent(Location a, Location b) {
        if (getPiece(a) == null || getPiece(b) == null)
            return false;
        return (getPiece(a).getColor() != getPiece(b).getColor());
    }

    /**
     * This method will only be used in Undo because it is too powerful
     *
     * @param a
     * @param location
     */
    public void setPieceLocation(Piece a, Location location) {
        getSquare(location).setPiece(a);
        a.setLoc(location);
    }

    public Color getColor(Location a) {
        return squares[a.getX()][a.getY()].getColor();
    }

    /*test only. It will forcefully clear a position*/
    public void testDelete(Location a) {
        getSquare(a).deletePiece();
    }

    public ArrayList<Piece> testGetWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> testGetBlackPieces() {
        return blackPieces;
    }
}
