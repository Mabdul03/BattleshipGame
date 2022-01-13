/**
 * @author: Meer Abdullah
 * @since: March 29th, 2021
 * Synopsis: The BattleshipGame class is a single player battleship game
 * The user can play battleship and dictate their own decisions towards the game
 * @version 1.3
 */
public class BattleshipGame{
    private BattleshipPlayer player;
    private Ocean ocean;

    /**
     * Constructor for BattleshipGame - initializes 
     * the ocean, and starts the Battleship game.
     * @param player - greets the player and starts the game.
     */
    public BattleshipGame(BattleshipPlayer player){
        this.player = player;
        player.startGame(); //Greets user and asks for name.
        ocean = new Ocean();
        ocean.placeAllBoats(); //Places all boats in Ocean
    }

    /**
     * pre - none
     * post - returns total turns it took for the user to 
     * complete, or fail (returns 100) battleship.
     * This method allows the user to play Battleship. 
     * It contains a loop that gets a shot from the player, 
     * gets the result back from the ocean and updates the player. 
     * It does this until either the game is over (all ships are sunk), 
     * or the user has taken 100 turns. It does not need to do any 
     * checking on whether the position has already been shot at. 
     * It is up to the player to make sure that the same position 
     * is not shot at twice.
     * @return int - total number of turns it takes 
     * the player to finish playing battleship.
     */
    public int play(){
        int turns = 0; // Total turns taken
        do {
            Position newPos = player.shoot();
            // checks to see if newPos is on the boat, and if it is it hits it.
            ocean.shootAt(newPos);
            turns++; // one turn has been taken (firing the shot towards a boat)
            //displays information in console
            player.updatePlayer(newPos, ocean.hit(newPos), 
                                ocean.boatInitial(newPos), 
                                ocean.boatName(newPos),ocean.sunk(newPos), 
                                ocean.allSunk(), turns >= 100, turns);
        } while (turns != 100 && !ocean.allSunk());
        return turns; //total turns user used when trying to destroy all 5 ships
    }
}