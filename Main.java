/**
 * @author Meer Abdullah
 * @since March 10th, 2021
 * Synopsis: The main class/method that allows the 
 * user to interact with the CPU to play Battleship :)
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        BattleshipPlayer player = new BattleshipPlayer();
        BattleshipGame game = new BattleshipGame(player);
        game.play();
    }
}
