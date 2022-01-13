/**
 * @author: Meer Abdullah
 * @since: 3/12/2021
 * Synopsis: The BattleshipGrid class is where the user determines the fate of 
 * the battlehship game. They are in full control of what the board looks like, 
 * and they can see the board as well.
 * @version 1.4
 */
public class BattleshipGrid {
    public char [][] boatGrid; //What the player sees
    private BoatStatus[][] grid; //Stores hit, miss, empty

    /**
     * Pre Condition - none.
     * Post Condition - Initializes Board with empty cells or indices.
     * This constructor initializes both of the boards.
     */
    public BattleshipGrid(){
        grid = new BoatStatus[10][10];
        boatGrid = new char[10][10];
        for (int i = 0; i < boatGrid.length; i++) {
            for (int j = 0; j < boatGrid[i].length; j++) {
                grid[i][j] = BoatStatus.EMPTY;
                boatGrid[i][j] = '.';
            }
        }
    }

    /**
     * Pre Condition - 3 parameters passed in.
     * Post Condition - Updated Board.
     * This method is called when a player has shot at a position.
     * Hit is a Boolean that is true if the shot was a hit,
     * and false if it was a miss.
     * @param Position pos - instance of position that is being checked.
     * @param boolean hit - was the boat hit ?
     * @param char initial - initial name of the Boat.
     */
    public void shotAt(Position pos, boolean hit, char initial){
        if (hit) {
            boatGrid[pos.rowIndex()][pos.columnIndex()] = initial;
            grid[pos.rowIndex()][pos.columnIndex()] = BoatStatus.HIT; 
        } else {
            boatGrid[pos.rowIndex()][pos.columnIndex()] = '*';
            grid[pos.rowIndex()][pos.columnIndex()] = BoatStatus.MISS;
        }
    }

    /**
     * Pre Condition - 1 parameter passed in - Position pos.
     * Post Condition - Boolean received on if the boat was hit at pos.
     * This method returns true if the position has been shot at 
     * and is a hit, false otherwise.
     * @param Position pos - instance of position that is being checked.
     * @return Boolean if the boat was hit at the passed in position.
     */
    public boolean hit(Position pos){
        return Character.isLetter(boatGrid[pos.rowIndex()][pos.columnIndex()]);
    }

    /**
     * Pre Condition - 1 parameter passed in - Position pos.
     * Post Condition - Boolean received on if the boat was a miss at pos.
     * This method returns true if the position has been shot at 
     * and is a miss, false otherwise.
     * @param Position pos - instance of position that is being checked.
     * @return Boolean on if the boat was a miss.
     */
    public boolean miss(Position pos){
        return boatGrid[pos.rowIndex()][pos.columnIndex()] == '*';
    }

    /**
     * Pre Condition - 1 parameter passed in - Position pos.
     * Post Condition - Boolean received on if the boat is empty at pos.
     * This method returns true if the position has not been shot at.
     * @param Position pos - instance of position that is being checked.
     * @return Boolean on if the boat has not been shot at.
     */
    public boolean empty(Position pos){
        return boatGrid[pos.rowIndex()][pos.columnIndex()] == '.';
    }

    /**
     * Pre Condition - 1 parameter passed in - Position pos.
     * Post Condition - char received that is the initial of the Boat.
     * This method should only be called if position has been shot at and hit. 
     * It returns the initial of the boat that has been hit.
     * @param Position pos - instance of position that is being checked.
     * @return The boat's initial, or an empty char if no boat is at pos.
     */
    public char boatInitial(Position pos){
        return Character.isLetter(boatGrid[pos.rowIndex()][pos.columnIndex()]) ? 
               boatGrid[pos.rowIndex()][pos.columnIndex()]: ' ';
    }
}