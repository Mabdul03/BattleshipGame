import java.awt.*;
  /**
   * BoatStatus enum in which is used for the grid 
   * object in the BattleshipGrid class
   * Enum - HIT, MISS, EMPTY.
   * Color and character represent a spot on the grid.
   */
public enum BoatStatus{
    HIT(Color.RED, 'x'), MISS(Color.BLUE, 'o'), EMPTY(Color.WHITE, '.');

    //Attributes of a BoatStatus object
    private Color color;
    private char status;
    
    //BoatStatus constructor
    private BoatStatus(Color color, char status){
        this.color = color;
        this.status = status;
    }
}