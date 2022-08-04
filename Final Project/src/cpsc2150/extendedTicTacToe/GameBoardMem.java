package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class contains the information about the game board
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    /**
     * @invariants  MIN_VALUE <= numRows <= MAX_VALUE
     *              MIN_VALUE <= numColumns <= MAX_VALUE
     *              MIN_WIN <= numToWin <= MAX_WIN and numToWin <= numRows and numToWin <= numColumns
     * @coreespondance self = GBMap<Character, List<BoardPosition>>
     */

    private int numRows;
    private int numColumns;
    private int numToWin;
    private List<BoardPosition> list;
    private Map<Character, List<BoardPosition>> GBMap;

    /**
     * @description this constructor initializes the board map
     * @pre MIN_VALUE <= nr <= MAX_VALUE
     *      MIN_VALUE <= nc <= MAX_VALUE
     *      MIN_WIN <= ntw <= MAX_WIN and ntw <= nr and ntw <= nc
     * @post numRows = nr
     *       numColumns = nc
     *       numToWin = ntw
     *       [GBMap initialized]
     */
    public GameBoardMem(int nr, int nc, int ntw){
        numRows = nr;
        numColumns = nc;
        numToWin = ntw;
        GBMap = new HashMap<Character, List<BoardPosition>>();
    }

    /**
     * @description places the character of the player on the position of marker
     * @param marker the position specified by the player for marker to be placed
     * @param player a character to be placed
     * @pre [marker is a BoardPosition] and
     *      checkSpace(marker) == true and
     *      [player is a valid player]
     * @post [add marker to the list of player in GBMap]
     */
    public void placeMarker(BoardPosition marker, char player){
        if(!GBMap.containsKey(player)){
            list = new ArrayList<BoardPosition>();
            list.add(marker);
            GBMap.put(player,list);
        }
        else {
            GBMap.get(player).add(marker);
        }
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
     * @post whatAtPos = [player character key that holds pos]
     *       board = #board
     */
    public char whatsAtPos(BoardPosition pos){
        for(Map.Entry<Character, List<BoardPosition>> m : GBMap.entrySet()){
            if (isPlayerAtPos(pos, m.getKey())) {
                return m.getKey();
            }
        }
        return ' ';
    }

    /**
     * @description checks to see if the player is at pos
     * @param pos the position specified by the player
     * @param player a character to be checked
     * @return true iff the player is at pos, false otherwise
     * @pre [pos is a BoardPosition] and
     *      [player is a valid player]
     *      checkSpace(pos) == true
     * @post isPlayerAtPos = true iff pos is in the list associated with player,
     *       otherwise isPlayerAtPos = false
     *       board = #board
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        if (!GBMap.containsKey(player)) {
            return false;
        }
        for(BoardPosition temp : GBMap.get(player)){
            if(temp.equals(pos)){
                return true;
            }
        }
        return false;
    }
}
