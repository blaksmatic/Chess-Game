package BoardAndGame;

import ChessPieces.*;
import ChessPieces.Color;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by admin on 2/8/16.
 */

public class GUIBoard {
    //this board is the board where players play
    protected Board board;
    //this panel is the panel for players
    JPanel chessPanel;
    //this keeps all the squares
    JButton Squares[][], StartButton;
    //this actionListener returns which button is pressed
    ButtonPressed ButtonPress[][];
    JPanel informationPanel;
    JPanel numberPanelV;
    JPanel numberPanelH;
    //the score of both sides
    int GlassScore;// glass is white
    int WoodScore;// Wood is Black
    //whose turn is the current turn
    private Color turn;
    //the previous location. Used for Undo
    private Location lastMyPosi;
    //the Opponeng's Previous location, used for Undo.
    private Location lastOppoPosi;
    private Piece lastOppo;
    //these two variables are related to the movement player make
    private Location startPosi;
    //this decides whether we should return the start previledge to the pawn.
    private boolean pawnStart;
    //
    JFrame PlayAgain;
    JLabel info1, info2, info3;
    Timer mytimer;
    private String GlassName = "Glass";
    private String WoodName = "Wood";

    /**
     * This constructor sets up the whole table
     *
     * @param newboard
     */
    public GUIBoard(Board newboard) {
        board = newboard;
        informationPanel = initializeInformation();
        PlayAgain = new JFrame();
        initializeButton();// set up all the buttons
        initializeGame();// set up all other variables
        setupButtonIcon();// show the buttons.
        setUpTimer();//set up the timer
        SetupWindow();// set up the main window
    }

    /**
     * This function sets up timers and gives player a sense what time it is
     */
    private void setUpTimer() {
        mytimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info3.setText(null);
            }
        });
        mytimer.setRepeats(false);
    }

    /**
     * This function sets up the window of the game. Including the size and
     * the layout
     */
    private void SetupWindow() {
        JFrame window = new JFrame("Chess GUI");
        window.setPreferredSize(new Dimension(1200, 847));
        window.setLayout(new BorderLayout());
        window.add(chessPanel, BorderLayout.WEST);
        window.add(informationPanel, BorderLayout.CENTER);
        setUpMenu(window);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }


    /**
     * This function initializes the information panel at the side and add information to the panel
     *
     * @return
     */
    private JPanel initializeInformation() {
        numberPanelH = new JPanel();
        numberPanelV = new JPanel();
        numberPanelH.setPreferredSize(new Dimension(800, 30));
        numberPanelV.setPreferredSize(new Dimension(30, 800));
        info1 = new JLabel();
        info1.setText("Current Score: Glass " + GlassScore + ": Wood " + WoodScore);
        info2 = new JLabel();
        info2.setText("Turn : " + GlassName);
        info3 = new JLabel();
        info3.setText("Make yor move!");
        info1.setPreferredSize(new Dimension(280, 100));
        info2.setPreferredSize(new Dimension(280, 100));
        info3.setPreferredSize(new Dimension(280, 100));
        JPanel newpanel = new JPanel();
        newpanel.setPreferredSize(new Dimension(400, 800));
        newpanel.setLayout(new FlowLayout());
        newpanel.add(info1);
        newpanel.add(info2);
        newpanel.add(info3);
        newpanel.setBackground(java.awt.Color.LIGHT_GRAY);
        return newpanel;
    }

    /**
     * This function initlizes the buttons and place them on the board
     */
    private void initializeButton() {
        chessPanel = new JPanel();
        chessPanel.setPreferredSize(new Dimension(800, 800));
        chessPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        chessPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        Squares = new JButton[board.column + 1][board.row + 1];
        ButtonPress = new ButtonPressed[board.column + 1][board.row + 1];
        /*Use a for loop to add everything to the board*/
        for (int i = board.row; i > 0; i--)
            for (int j = board.column; j > 0; j--) {
                Squares[j][i] = new JButton();
                Squares[j][i].setPreferredSize(new Dimension(99, 99));
                Squares[j][i].setMargin(new Insets(0, 0, 0, 0));
                Squares[j][i].addActionListener(new ButtonPressed(j, i));
                Squares[j][i].addMouseListener(new Hover(j, i));
                chessPanel.add(Squares[j][i]);
                if (board.getSquare(new Location(j, i)).getColor() == Color.BLACK)
                    Squares[j][i].setBackground(java.awt.Color.gray);
                else
                    Squares[j][i].setBackground(java.awt.Color.white);
                Squares[j][i].setOpaque(true);
                Squares[j][i].setBorderPainted(false);
            }
    }

    /**
     * this function sets up the icons on the button. it determines which position has which icon, and gives icon to it.
     * doesn't know if there are functions that can shorten the length of the codes
     */
    private void setupButtonIcon() {
        for (int i = 1; i <= board.column; i++)
            for (int j = 1; j <= board.row; j++) {
                Location nextLocation = new Location(i, j);
                Squares[i][j].setIcon(null);
                /*All the cases of the chess pieces. set them up.*/
                if (board.getPiece(nextLocation) instanceof King) {
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_kdt45.svg.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/Chess_klt45.svg.png"));
                } else if (board.getPiece(nextLocation) instanceof Queen)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_qdt45.svg.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_qlt45.svg.png"));
                else if (board.getPiece(nextLocation) instanceof Bishop)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_bdt45.svg.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/Chess_blt45.svg.png"));
                else if (board.getPiece(nextLocation) instanceof Knight)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_ndt45.svg.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_nlt45.svg.png"));
                else if (board.getPiece(nextLocation) instanceof Pawn)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/Chess_pdt45.svg.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/Chess_plt45.svg.png"));
                else if (board.getPiece(nextLocation) instanceof Rook)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/45px-Chess_rdt45.svg.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/Chess_rlt45.svg.png"));
                else if (board.getPiece(nextLocation) instanceof ChessJ)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/ChessJB.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/ChessJW.png"));
                else if (board.getPiece(nextLocation) instanceof ChessK)
                    if (board.getPiece(nextLocation).getColor() == Color.BLACK)
                        Squares[i][j].setIcon(new ImageIcon("Pictures/ChessKB.png"));
                    else
                        Squares[i][j].setIcon(new ImageIcon("Pictures/ChessKW.png"));
            }

    }

    /**
     * This function initializes all other booleans and variables
     * It will be reused in other cases
     */
    private void initializeGame() {
        turn = Color.WHITE;
        startPosi = null;
        lastMyPosi = null;
        lastOppoPosi = null;
        lastOppo = null;
    }

    /**
     * This function sets up the menus of the game.
     * It includes the restart forfeit and undo
     *
     * @param window
     */
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("settings");
        JMenuItem forfeit = new JMenuItem("Forfeit");
        forfeit.addActionListener(new StartPressed());
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(new UndoPressed());
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(new restartPressed());
        file.add(forfeit);
        file.add(undo);
        file.add(restart);
        menubar.add(file);
        window.setJMenuBar(menubar);
    }

    /**
     * Get the next turn. and update the nametag in the game.
     */
    public void nextTurn() {
        String name;
        if (turn == Color.WHITE) {
            name = WoodName;
            turn = Color.BLACK;
        } else {
            turn = Color.WHITE;
            name = GlassName;
        }
        info2.setText("Turn: " + name);
    }

    /**
     * set an invalid move text to tell the user.
     */
    public void invalidMoveCannotReach(Location dest) {
        info3.setText("This is a invalid move, please redo your move.");
        java.awt.Color startc = getSquare(startPosi).getBackground();
        java.awt.Color endc = getSquare(dest).getBackground();
        getSquare(startPosi).setBackground(java.awt.Color.red);
        getSquare(dest).setBackground(java.awt.Color.red);


    }

    /**
     * Simply restart the game where a king is killed
     *
     * @param color
     */
    public void loseTheGame(Color color) {
        info3.setText("You lost!");
        restartGame();
    }

    /**
     * This function return the Jbutton of the location
     *
     * @param a
     * @return
     */
    public JButton getSquare(Location a) {
        return Squares[a.getX()][a.getY()];
    }


    /**
     * This undo function that will undo player's movement at any time. I think it is more fair for player to do this.
     */
    public void undo() {
        info3.setText("Let's undo this step...But only one!");
        mytimer.start();
        //return the player back
        board.setPieceLocation(board.getPiece(lastOppoPosi), lastMyPosi);
        board.getSquare(lastOppoPosi).deletePiece();
        if (pawnStart)
            ((Pawn) board.getPiece(lastMyPosi)).firstMove = true;
        if (lastOppo != null)
            board.setPieceLocation(lastOppo, lastOppoPosi);
        setupButtonIcon();
        nextTurn();
    }


    /**
     * The function creates a windows and asks for peace between you and your opponents
     */
    public void seekForPeace() {
        /* a new JFrame */
        PlayAgain = new JFrame();
        PlayAgain.setPreferredSize(new Dimension(350, 100));
        PlayAgain.setLayout(new FlowLayout());
        PlayAgain.setTitle("Play Again?");

		/* the message */
        JLabel infoPlayAgain = new JLabel("Restart with your opponent without punishment?");
        PlayAgain.add(infoPlayAgain);
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        PlayAgain.add(yes);
        PlayAgain.add(no);

		/* attach actioning to buttons */
        yes.addActionListener(new PlayAgain());
        no.addActionListener(new NO());
        PlayAgain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/* pack it up */
        PlayAgain.pack();
        PlayAgain.setVisible(true);
    }

    /**
     * If it is the first time a location is pressed, it will store it. If it is the second time, it will return
     * if you can move to that position.
     *
     * @param nextLocation
     */
    public boolean clickLocation(Location nextLocation) {
   /*     if (board.getSquare(nextLocation).hasPiece())
            System.out.println("It is a " + board.getPiece(nextLocation).getClass().getSimpleName());
    */
        if (startPosi == null) {
            if (board.getSquare(nextLocation).hasPiece())
                if (turn == board.getPiece(nextLocation).getColor())//the color of the Piece is the same as the turn
                    startPosi = nextLocation;

        } else {//we already have a Chess in startPosi
            if (board.getSquare(nextLocation).hasPiece()) {
                if (board.isOpponent(startPosi, nextLocation)) {
                    if (board.canMoveTo(startPosi, nextLocation)) {
                        movePiece(nextLocation);
                        return true;

                    } else { //not able to capture it
                        invalidMoveCannotReach(nextLocation);
                        info2.setText("You cannot reach him. Invalid move");
                        startPosi = null;
                        return false;
                    }
                }
            } else {//there is no Piece at the distination.
                if (board.canMoveTo(startPosi, nextLocation)) {
                    movePiece(nextLocation);
                    return true;
                } else {
                    invalidMoveCannotReach(nextLocation);
                    startPosi = null;
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function is to go to the next turn. The undo information will be stored here.
     *
     * @param nextLoc
     */
    private void movePiece(Location nextLoc) {
        String myName;
        String opponentName;
        if (turn == Color.WHITE) {
            myName = GlassName;
            opponentName = WoodName;
        } else {
            opponentName = GlassName;
            myName = WoodName;
        }
        lastOppoPosi = nextLoc;
        lastOppo = board.getPiece(nextLoc);
        lastMyPosi = startPosi;
        if (board.getPiece(startPosi) instanceof Pawn)
            if (((Pawn) board.getPiece(startPosi)).firstMove)
                pawnStart = true;
            else
                pawnStart = false;
        else
            pawnStart = false;
        if (board.getSquare(nextLoc).hasPiece()) {
            if (board.getPiece(nextLoc) instanceof King) {
                GameOver(turn);
                return;
            }
            info3.setText(myName + " moves to " + nextLoc.getX() + " " + nextLoc.getY() + " and catches "
                    + board.getPiece(nextLoc).getClass().getSimpleName());
            board.captureOpponent(startPosi, nextLoc);
        } else {
            board.goTo(startPosi, nextLoc);
        }
        nextTurn();
        startPosi = null;
        setupButtonIcon();
        if (board.inCheck(turn))
            info3.setText("Caution! " + opponentName + " In Check!");
        if (board.checkMate(turn))
            info3.setText("Caution! " + opponentName + " CheckMate!");
    }

    /**
     * This class extends actionlistener so that we know which button is pressed, and what effect we will have
     * on that particular buttom.
     */
    public class ButtonPressed implements ActionListener {
        int x, y;

        public ButtonPressed(int X, int Y) {
            x = X;
            y = Y;
        }

        @Override
        /**
         * When a button is pressed, it will firstly check what action the user is doing. If the user is pressing the
         * button which makes the first move for the chess, it will wait for the second move. If it is the second move,
         *
         */
        public void actionPerformed(ActionEvent e) {
   //         System.out.println("assertTrue(GUIboard.clickLocation(new Location(" + x + "," + y + ")));");
            Location nextLocation = new Location(x, y);
            clickLocation(nextLocation);
        }

    }

    /**
     * When Undo button is pressed, the last step is reverted, and the pawn's first step is also reverted.
     * Undo  can be done at any time after the first step because I think it is more fair if you are going to
     * play with some one horroble.
     */
    public class UndoPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            undo();
        }
    }

    /**
     * When restart button is pressed, the game restarts immediately and he loses.
     */
    public class StartPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (turn == Color.WHITE)
                WoodScore++;
            else
                GlassScore++;
            restartGame();
        }
    }

    /**
     * A player can forfeit the game and check if the opponents say No to punishment
     */
    public class restartPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            seekForPeace();
        }
    }

    /**
     * The opponent says yes.
     */
    public class PlayAgain implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent exitgame) {
            /* close the windows */
            PlayAgain.setVisible(false);
            restartGame();
        }
    }

    /**
     * What happens if the No buttons is pressed
     */
    public class NO implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PlayAgain.setVisible(false);
            if (turn == Color.WHITE)
                WoodScore++;
            else
                GlassScore++;
            restartGame();
        }
    }

    /**
     * This function restarts the game and reset everything.
     */
    public void restartGame() {
        board = new Board(8, 8);
        turn = Color.WHITE;
        info2.setText("Turn: Glass");
        pawnStart = false;
        info1.setText("Current Score: Glass " + GlassScore + ": Glass " + WoodScore);
        setupButtonIcon();
        initializeGame();
    }


    /**
     * A function calls to restart the game and add credits for the winner
     *
     * @param a
     */
    public void GameOver(Color a) {
        String name;
        if (a == Color.WHITE) {
            GlassScore++;
            name = GlassName;
        } else {
            WoodScore++;
            name = WoodName;
        }
        info3.setText("Game Over! " + name + " Wins!");
        restartGame();
    }

    /**
     * This is the class for the hoverring effect
     */
    public class Hover implements MouseListener {
        int x;
        int y;

        public Hover(int I, int J) {
            x = I;
            y = J;
        }

        /**
         * When mouse enters the buttons, you will know where you can go.
         *
         * @param e
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            if (!board.getSquare(new Location(x, y)).hasPiece())
                return;
            if (board.getPiece(new Location(x, y)).getColor() == turn)
                for (int i = 1; i <= 8; i++)
                    for (int j = 1; j <= 8; j++) {
                        if (board.canMoveTo(new Location(x, y), new Location(i, j))) {
                            Squares[i][j].setBackground(java.awt.Color.ORANGE);
                        } else
                            Squares[i][j].setBackground(java.awt.Color.WHITE);
                    }
        }

        @Override
        public void mousePressed(MouseEvent me) {

        }

        @Override
        public void mouseClicked(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {

        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (!board.getSquare(new Location(x, y)).hasPiece())
                return;
            if (board.getPiece(new Location(x, y)).getColor() == turn)
                for (int i = 1; i <= 8; i++)
                    for (int j = 1; j <= 8; j++) {
                        if (board.getSquare(new Location(i, j)).getColor() == Color.WHITE)
                            Squares[i][j].setBackground(java.awt.Color.WHITE);
                        else
                            Squares[i][j].setBackground(java.awt.Color.GRAY);
                    }
        }
    }

    /*it will return the board. For test only*/
    public Board getBoard() {
        return board;
    }

    public static void main(String args[]) {
        GUIBoard board = new GUIBoard(new Board(8, 8));
    }
}


