/**
 * @author Meer Abdullah
 * @since: April 5th, 2021
 * Synopsis: Creates a computer player for the Battleship game.
 * Shoots at randomized grid positions and updates grid with the results.
 * @version: 1.1
 */
public class ComputerBattleshipPlayer extends BattleshipPlayer{
    private String name;

    /**
     * Constructor initializes the computers name to "Computer Player"
     */
    public ComputerBattleshipPlayer(){
        name = playerName();
    }

    /**
     * Accessor method to retrieve the name for the computer
     * @return computer name - "Computer Player"
     */
    public String playerName(){
        return "Computer Player";
    }

    /**
     * This method starts the battleship game for the computer
     */
    public void startGame(){
        grid = new BattleshipGrid();
        System.out.println("The computer will be playing Battleship today !");
    }

    /**
     * This method initializes a position with valid parameters, and returns it
     * @return a position that the computer will use to shoot at at the grid.
     */
    public Position shoot(){
        char row;
        int column;
        do{
            row = (char)((int)(65 + Math.random() * 10));
            column = (int)(1 + Math.random() * 10);
        } while ((row < 'A' || row > 'J') || (column < 1 || column > 10)); 
            return new Position(row, column);
    }

    /**
     * A method for the game to update the computer player with the shot status.
     * @param pos The position that has been shot.
     * @param hit Whether the shot was successful or not.
     * @param initial The initial of the boat at that position.
     */
    public void updatePlayer(Position pos, boolean hit, char initial){
        super.updateGrid(pos, hit, initial); //call on updateGrid()
    }
}