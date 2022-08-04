package cpsc2150.extendedTicTacToe;

/**
 * An abstract class that contains an overridden implementation of {@link Object#toString()}
 */
public abstract class AbsGameBoard implements IGameBoard{
    /**
     * @description overrides the toString method to return a string representation of the game board
     * @return string representation of the game board
     * @pre NONE
     * @post toString = [String representation of the game board]
     *       board = #board
     */
    @Override
    public String toString (){
        String x = "";
        x += "   ";
        for(int i = 0; i < getNumColumns(); ++i){
            if(i < 10){
                x = x + " ";
            }
            x = x + i + "|";
        }
        x += "\n";
        for(int i = 0; i < getNumRows(); ++i){
            if(i < 10){
                x = x + " ";
            }
            x = x + i + "|";
            for(int j = 0; j < getNumColumns(); ++j){
                BoardPosition pos = new BoardPosition(i,j);
                x = x + whatsAtPos(pos) + " |";
            }
            x += "\n";
        }
        return x;
    }
}
