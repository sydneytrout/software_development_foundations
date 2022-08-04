package cpsc2150.extendedTicTacToe;

import cpsc2150.extendedTicTacToe.TicTacToeView;
import cpsc2150.extendedTicTacToe.GameSetupScreen;
import cpsc2150.extendedTicTacToe.IGameBoard;
import cpsc2150.extendedTicTacToe.GameBoard;
import cpsc2150.extendedTicTacToe.GameBoardMem;
import cpsc2150.extendedTicTacToe.AbsGameBoard;
import cpsc2150.extendedTicTacToe.BoardPosition;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    public static final int MAX_PLAYERS = 10;

    private int numPlayers;

    private Character[] players;

    private boolean ifWin = false;

    private boolean ifTie = false;

    private int turn = 0;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     * numPlayers = np
     * players = {'X', 'O', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F'}
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
        players = new Character[]{'X', 'O', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F'};
        // Some code is needed here.
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        BoardPosition tempBP = new BoardPosition(row,col);
        //checks for a win or tie
        if(ifWin){
            turn = 0;
            ifWin = false;
            this.newGame();
            return;
        }
        if(ifTie){
            turn = 0;
            ifTie = false;
            this.newGame();
            return;
        }

        //makes sure the space clicked is available
        if(!curGame.checkSpace(tempBP)){
            screen.setMessage("That space is unavailable, please pick again");
            return;
        }

        //places and sets the marker
        curGame.placeMarker(tempBP, players[turn]);
        screen.setMarker(row, col, players[turn]);


        //checks for winner or draw
        if(curGame.checkForWinner(tempBP)){
            screen.setMessage("Player " + players[turn] + " wins!\nPress any button to start a new game.");
            ifWin = true;
        }

        else if(curGame.checkForDraw()){
            screen.setMessage("Draw!\nPress any button to start a new game.");
            ifTie = true;
        }


        //returns if there is a win or tie or changes the player if there is not
        if(ifWin || ifTie){
            return;
        }
        else if(turn + 1 < numPlayers){
            turn++;
            screen.setMessage("It is " + players[turn] + "\'s turn.");
        }
        else{
            turn = 0;
            screen.setMessage("It is " + players[turn] + "\'s turn.");
        }

    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}