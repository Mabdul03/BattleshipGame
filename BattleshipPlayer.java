/**
 * @author: Meer Abdullah
 * @since: March 26, 2021
 * Synopsis: This class allows the user to play Battleship.
 * BattleshipPlayer prints out, and updates the board as the user plays.
 * @version 1.3
 */
import java.util.Scanner;
public class BattleshipPlayer{
    private Scanner scan;
    private BattleshipGrid grid;
    private String name;

    /**
     * Constructor for Battleship Player
     * initializes scanner to prompt user
     * for their name when the game starts
     */
    public BattleshipPlayer(){
        scan = new Scanner(System.in);
        name = null;
    }

    /**
     * pre - none
     * post - welcomes the user and is the beginning of the Battleship game.
     * This modifier method is called each time a new game is started. 
     * It creates the BattleshipGrid used to keep the user informed. 
     * If name is null, it asks the user to 
     * enter a name and welcomes the user to the game.
     */
    public void startGame(){
        grid = new BattleshipGrid();
        if(name == null){
            System.out.println("Hello there! What's your name ?");
            name = scan.nextLine();
        }
        System.out.println("Welcome " + name + 
                           "! Let's play some Battleship !");
    }

    /**
     * pre - none
     * post - returns the name of the player.
     * Accessor method to retrieve the name of the player.
     * @return name of player
     */
    public String playerName(){
        return name;
    }

    /**
     * pre - none
     * post - returns the position that the user is guessing.
     * The shoot() method prompts the user for a row and column, 
     * then returns the instance of Position in which the respective 
     * row and column values are used.
     * Prompts the user repeatedly until there are valid position arguments.
     * @return instance of Position that the user is guessing to shoot at.
     */
    public Position shoot(){
        char row;
        int column;
    do {
        System.out.println("Enter a row, A-J!");
        row = scan.next().charAt(0);
        System.out.println("Enter a column, 1-10!");
        column = scan.nextInt();
      /* do while loop to make sure the arguments for the 
       * instance of position that is returned are valid. */
    } while ((row < 'A' || row > 'J') || (column < 1 || column > 10)); 
        return new Position(row, column);
    }

    /**
     * pre - pos row being >= 'A' and <= 'J'
     * This modifier method updates the grid based on the results of a shot. 
     * Note that this can be done automatically by 
     * calling the shotAt method of the BattleshipGrid class
     * @param pos - Instance of Position
     * @param hit - determines whether the boat was hit or not.
     * @param initial - initial of the boat that is supposedly hit.
     * @return an updated board.
     */
    protected void updateGrid(Position pos, boolean hit, char initial){
        grid.shotAt(pos, hit, initial);
    }

      /**
       * pre - valid arguments for position row and column.
       * post - displays to the user the updates 
       * that are occuring while playing Battleship
       * This method informs the user whether their shot was a hit or miss, 
       * which ship that was hit, if the ship has sunk, if the game is over, 
       * and if the game has been going on for too long.
       * @param pos - instance of Position on wherre the boat is supposedly hit
       * @param hit - determines whether the boat was hit or not.
       * @param initial - initial of the boat that is supposedly hit
       * @param boatName - name of the boat
       * @param sunk - Whether or not the boat has sunk
       * @param gameOver - determines if the game is over if all 
       * ships have been sunk or too many turns were taken
       * @param tooManyTurns - determines whether too 
       * many turns were taken shooting at the boats.
       * @param turns - amount of times the user took a shot at the ship
       * @return updated board to User
       */
      public void updatePlayer(Position pos, boolean hit, char initial,
                               String boatName, boolean sunk, boolean gameOver, 
                               boolean tooManyTurns, int turns){
        updateGrid(pos, hit, initial); //call on updateGrid()
        System.out.println("  1   2   3   4   5   6   7   8   9   10");
        for (char row = 'A' ; row <= 'J'; row++) {
            System.out.print(row + " ");
            for (int column = 1; column <= 10; column++) {
                Position newPos = new Position(row, column);
                if (grid.empty(newPos))
                    System.out.print(".   "); //updates board with miss
                else if (grid.miss(newPos))
                    System.out.print("*   "); //updates board with miss
                else if (grid.hit(newPos)) 
                    //updates board with initial
                    System.out.print(grid.boatInitial(newPos) + "   ");
            }
            System.out.println();
        }

        // Prints information of the boat and turns based on different scenarios
        if (hit) {
            System.out.println("Turn #" + turns + ": Your shot at " + 
                               pos.toString() + " was a hit.");
            if (sunk)
                System.out.println("The boat " + boatName + " has sunk from 
                                   hitting it at position " + pos + "!");
        } else
            System.out.println("Turn #" + turns + ": Your shot at "
                               + pos.toString() + " was a miss.");
        /* game is over if the boolean is true */
        if (gameOver) {
            System.out.println("The game is over !");
            return;
        }
        /* If over 100 turns are taken, the game is over - too many turns */
        if (tooManyTurns) {
            System.out.println("There has been too many turns !");
            return;
        }
    }
}