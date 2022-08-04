package cpsc2150.extendedTicTacToe;

/**
 * This class keeps track of an individual cell for a board
 */
public class BoardPosition {
    /**
     * @invariants row >= 0 and
     *            column >= 0
     */
    private int row;
    private int column;

    /**
     * @description constructor that initializes row and column
     * @param r the row the position is on
     * @param c the column the position is on
     * @pre r >= 0 and c >= 0
     * @post row = r and column = c
     */
    public BoardPosition(int r, int c){
        row = r;
        column = c;
    }

    /**
     * @desctiption accesses row
     * @return the integer row
     * @pre NONE
     * @post getRow = row
     *       row = #row
     */
    public int getRow(){
        return row;
    }

    /**
     * @description accesses column
     * @return the integer column
     * @pre NONE
     * @post getColumn = column
     *       column = #column
     */
    public int getColumn(){
        return column;
    }

    /**
     * @description overrides the equals operator in order to compare BoardPositions
     * @param pos1 the BoardPosition in the comparison
     * @return true iff pos1's row and row and pos1's column and column are equal, false otherwise
     * @pre [pos1 is a valid BoardPosition]
     * @post equals = true iff pos1.getRow() == row and pos1.getColumn() == column,
     *       otherwise equals = false
     */
    @Override
    public boolean equals(Object pos1){
        if (!(pos1 instanceof BoardPosition)){
            return false;
        }
        else{
            if(((BoardPosition)pos1).getRow() == row && ((BoardPosition)pos1).getColumn() == column) {
                return true;
            }
        }
        return false;
    }

    /**
     * @description overrides the toString method to make a string in the format "row,column"
     * @return string in the format "row,column"
     * @pre NONE
     * @post string = [string in the format "row,column"]
     */
    @Override
    public String toString() {
        String x;
        x = this.getRow() + "," + this.getColumn();
        return x;
    }

}
