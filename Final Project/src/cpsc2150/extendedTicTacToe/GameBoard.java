package cpsc2150.extendedTicTacToe;

/**
 * This class contains the information about the game board
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    /**
     * @invariants MIN_VALUE <= numRows <= MAX_VALUE
     *             MIN_VALUE <= numColumns <= MAX_VALUE
     *             MIN_WIN <= numToWin <= MAX_WIN and numToWin <= numRows and numToWin <= numColumns
     * @correspondences self = board[numRows][numColumns]
     */
    private int numRows;
    private int numColumns;
    private int numToWin;
    private char[][] board;

    /**
     * @description this constructor initializes the board to spaces
     * @pre MIN_VALUE <= nr <= MAX_VALUE
     *      MIN_VALUE <= nc <= MAX_VALUE
     *      MIN_WIN <= ntw <= MAX_WIN and ntw <= nr and ntw <= nc
     * @post numRows = nr
     *       numColumns = nc
     *       numToWin = ntw
     *       [entire board = ' ']
     */
    GameBoard(int nr, int nc, int ntw) {
        numRows = nr;
        numColumns = nc;
        numToWin = ntw;
        board = new char[numRows][numColumns];
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numColumns; j++){
                board[i][j] = ' ';
            }
        }
    }

    /**
     * @description places the character of the player on the position of marker
     * @param marker the position specified by the player for marker to be placed
     * @param player a character to be placed
     * @pre [marker is a BoardPosition] and
     *      checkSpace(marker) == true and
     *      [player is a valid player]
     * @post gameBoard[marker.getRow][marker.getColumn] = player
     */
    public void placeMarker(BoardPosition marker, char player){
        board[marker.getRow()][marker.getColumn()] = player;
    }


    /**
     * @description returns the number of rows in the GameBoard
     * @return the number of rows in the GameBoard
     * @pre NONE
     * @post getNumRows = numRows
     *       numRows = #numRows
     */
    public int getNumRows(){
        return numRows;
    }


    /**
     * @description returns the number of columns in the GameBoard
     * @return the number of columns in the GameBoard
     * @pre NONE
     * @post getNumColumns = numColumns
     *       numColumns = #numColumns
     */
    public int getNumColumns(){
        return numColumns;
    }


    /**
     * @description returns the number needed in a row to win the game
     * @return the number needed in a row to win the game
     * @pre NONE
     * @post getNumToWin = ToWin
     *       ToWin = #ToWin
     */
    public int getNumToWin(){
        return numToWin;
    }

    /**
     * @description returns what is in the board at pos
     * @param pos the position specified by the player
     * @return the character at given position
     * @pre [pos is a BoardPosition]
     *      checkSpace(pos) == true
     * @post whatAtPos = gameBoard[pos.getRow()][pos.getColumn()]
     *       board = #board
     */
    public char whatsAtPos(BoardPosition pos){
        return board[pos.getRow()][pos.getColumn()];
    }

}

