package cpsc2150.extendedTicTacToe;

/**
 * A game board containing a set of characters.
 * A game board is a data structure that allows you to add markers
 * in attempt to win the game
 *
 * Initialization ensures:
 *      game board contains nothing
 *
 * Constraints:
 *      MIN_VALUE <= numRows <= MAX_VALUE
 *      MIN_VALUE <= numColumns <= MAX_VALUE
 *      MIN_WIN <= numToWin <= MAX_WIN and numToWin <= numRows and numToWin <= numColumns
 */
public interface IGameBoard {

    public static final int MAX_VALUE = 100;
    public static final int MIN_VALUE = 3;
    public static final int MIN_WIN = 3;
    public static final int MAX_WIN = 25;

    /**
     * @description places the character of the player on the position of marker
     * @param marker the position specified by the player for marker to be placed
     * @param player a character to be placed
     * @pre [marker is a BoardPosition] and
     *      checkSpace(marker) == true and
     *      [player is a valid player]
     * @post [position marker on board] = player
     */
    public void placeMarker(BoardPosition marker, char player);


    /**
     * @description returns what is in the board at pos
     * @param pos the position specified by the player
     * @return the character at given position
     * @pre [pos is a BoardPosition]
     *      checkSpace(pos) == true
     * @post whatAtPos = [player at position]
     *       board = #board
     */
    public char whatsAtPos(BoardPosition pos);


    /**
     * @description returns the number of rows in the GameBoard
     * @return the number of rows in the GameBoard
     * @pre NONE
     * @post getNumRows = numRows
     *       numRows = #numRows
     */
    public int getNumRows();

    /**
     * @description returns the number of columns in the GameBoard
     * @return the number of columns in the GameBoard
     * @pre NONE
     * @post getNumColumns = numColumns
     *       numColumns = #numColumns
     */
    public int getNumColumns();

    /**
     * @description returns the number needed in a row to win the game
     * @return the number needed in a row to win the game
     * @pre NONE
     * @post getNumToWin = ToWin
     *       ToWin = #ToWin
     */
    public int getNumToWin();

    /**
     * @description checks to see if the player is at pos
     * @param pos the position specified by the player
     * @param player a character to be checked
     * @return true iff the player is at pos, false otherwise
     * @pre [pos is a BoardPosition] and
     *      [player is a valid player]
     *      checkSpace(pos) == true
     * @post isPlayerAtPos = true iff whatAtPos(pos) == player,
     *       otherwise isPlayerAtPos = false
     *       board = #board
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player){
        BoardPosition pos1 = new BoardPosition(pos.getRow(),pos.getColumn());
        if (whatsAtPos(pos1) == player){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @description checks to see if last marker placed caused 5 in a row horizontally
     * @param lastPos the last position a marker was placed
     * @param player the character of player who last placed a marker
     * @return true iff horizontal win, false otherwise
     * @pre [lastPos is a BoardPosition and was the last placed] and
     *      checkSpace(lastPos) == true and
     *      [player is a valid player]
     * @post checkHorizontalWin = true iff [lastPos made numToWin in a row horizontally],
     *       otherwise checkHorizontalWin = false
     *       board = #board
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player){
        int check = 0;
        int row = lastPos.getRow();
        for(int i = lastPos.getColumn(); i >= 0; i--){
            BoardPosition pos = new BoardPosition(row,i);
            if (whatsAtPos(pos) != player){
                break;
            }
            check++;
        }
        for(int i = lastPos.getColumn() + 1; i < getNumColumns(); i++){
            BoardPosition pos = new BoardPosition(row,i);
            if (whatsAtPos(pos) != player){
                break;
            }
            check++;
        }
        if (check >= getNumToWin()){
            return true;
        }
        return false;
    }

    /**
     * @description checks to see if last marker placed caused 5 in a row vertically
     * @param lastPos the last position a marker was placed
     * @param player a character of player who last placed a marker
     * @return true iff vertical win, false otherwise
     * @pre [lastPos is a BoardPosition and was the last placed] and
     *      checkSpace(lastPos) == true and
     *      [player is a valid player]
     * @post checkVerticalWin = true iff [lastPos made numToWin in a row vertically],
     *       otherwise checkVerticalWin = false
     *       board = #board
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player){
        int check = 0;
        int col = lastPos.getColumn();
        //incrementing check if position equals player
        for(int i = 0; i < getNumRows(); i++){
            BoardPosition pos = new BoardPosition(i,col);
            if(whatsAtPos(pos) == player){
                check++;
            }
        }
        if (check == getNumToWin()){
            return true;
        }
        return false;
    }

    /**
     * @description checks to see if last marker placed caused 5 in a row diagonally
     * @param lastPos the last position a marker was placed
     * @param player a character of player who last placed a marker
     * @return true iff diagonal win, false otherwise
     * @pre [lastPos is a BoardPosition] and
     *      checkSpace(lastPos) == true and
     *      [player is a valid player]
     * @post checkDiagonalWin = true iff [lastPos made numToWin in a row diagonally],
     *       otherwise checkDiagonalWin = false
     *       board = #board
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player){
        int check = 0;
        int check2 = 0;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        //checking for a diagonal win going right and up
        while(row >= 0 && col >= 0){
            BoardPosition pos = new BoardPosition(row,col);
            if(whatsAtPos(pos) == player){
                check++;
            }
            row--;
            col--;
        }
        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() + 1;
        //checking for a diagonal win going right and down
        while(row < getNumRows() && col < getNumColumns()){
            BoardPosition pos = new BoardPosition(row,col);
            if(whatsAtPos(pos) == player){
                check++;
            }
            row++;
            col++;
        }
        row = lastPos.getRow();
        col = lastPos.getColumn();
        //checking for a diagonal win going left and up
        while(row >= 0 && col < getNumColumns()){
            BoardPosition pos = new BoardPosition(row,col);
            if(whatsAtPos(pos) == player){
                check2++;
            }
            row--;
            col++;
        }
        row = lastPos.getRow();
        col = lastPos.getColumn();
        //checking for a diagonal win going left and down
        while(row < getNumRows() && col >= 0) {
            BoardPosition pos = new BoardPosition(row,col);
            if (whatsAtPos(pos) == player) {
                check2++;
            }
            row++;
            col--;
        }
        if(check == getNumToWin()){
            return true;
        }
        if(check2 == getNumToWin()+1){
            return true;
        }
        return false;
    }

    /**
     * @description checks to ensure that pos is available and in the bounds
     * @param pos the position on the board specified by the player
     * @return true if the position is available, false otherwise
     * @pre [pos is a BoardPosition]
     * @post checkSpace = true iff whatAtPos is ' ' and
     *       0 <= pos.getRow() <= getNumRows() and 0 <= pos.getColumn() <= getNumColumns(),
     *       otherwise checkSpace = false
     */
    default public boolean checkSpace(BoardPosition pos){
        if(pos.getRow() < 0 || pos.getRow() >= getNumRows()){
            return false;
        }
        if(pos.getColumn() < 0 || pos.getColumn() >= getNumColumns()){
            return false;
        }
        BoardPosition tempPos = new BoardPosition(pos.getRow(),pos.getColumn());
        if(whatsAtPos(tempPos) != ' '){
            return false;
        }
        return true;
    }

    /**
     * @description checks to see if the lastPos placed led to a win
     * @param lastPos the last position a marker was placed
     * @return true if there has been a win, false otherwise
     * @pre [lastPos is a BoardPosition] and
     *      checkSpace(lastPos) == true
     * @post checkForWinner = true iff checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player) ||
     *       checkHorizontalWin(lastPos, player),
     *       otherwise checkForWinner = false
     */
    default public boolean checkForWinner(BoardPosition lastPos){
        if(checkHorizontalWin(lastPos, whatsAtPos(lastPos))){
            return true;
        }
        if(checkVerticalWin(lastPos, whatsAtPos(lastPos))){
            return true;
        }
        if(checkDiagonalWin(lastPos, whatsAtPos(lastPos))){
            return true;
        }
        return false;
    }

    /**
     * @description checks to see if the game is a draw
     * @return true if game ended in a draw, false otherwise
     * @pre checkForWinner == false
     * @post checkForDraw = true iff no positions are ' ',
     *       otherwise checkForDraw = false
     */
    default public boolean checkForDraw(){
        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumColumns(); j++){
                BoardPosition pos = new BoardPosition(i,j);
                if(whatsAtPos(pos) == ' '){
                    return false;
                }
            }
        }
        return true;
    }


}
