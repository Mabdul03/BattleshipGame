/**
 *  @author Meer Abdullah
 *  @since: February 25th, 2021
 *  Synopsis: Creates a boat object with a name, start position, 
 *  direction, and size given the name. Tracks positions occupied 
 *  by the boat and whether those positions have been hit.
 *  @version 1.3
 */
import java.util.ArrayList;
public class Boat{
    // attributes for a Boat
    private String name, direction;
    private Position start;
    public ArrayList<Position> area;
    private ArrayList<Position> hit;

    /**
	 * A constructor for all Boat objects in the Battleship game.
	 * @param name The Boat name ("Aircraft Carrier", "Battleship", 
     *                            "Cruiser", "Destroyer", or "Submarine").
     * The name of the Boat determines its respective size.
     * @param start - The Boat's starting position
     * @param direction The Boat's direction ("horizontal", "vertical").
     * This determines the boat's area.
     */
    public Boat(String name, Position start, String direction){
        this.name = name;
        this.start = start;
        this.direction = direction;

        area = new ArrayList<Position>();
        hit = new ArrayList<Position>();
        for (int i = 0; i < size(); i++) {
            /* checks for the direction of the boat to 
             * initialize position on the grid accordingly. */
            if (direction.equals("horizontal")) {
                area.add(new Position(this.start.rowIndex(), 
                                      this.start.columnIndex() + i));
            } else if (direction.equals("vertical")) {
                area.add(new Position(this.start.rowIndex() + i, 
                                      this.start.columnIndex()));
            }
        }
    }

    /**
     * Accessor method for the name of the boat.
     * @return The Boat's name.
     */
    public String name(){
        return name;
    }

    /**
     * Accessor method for the initial of the boat name.
     * @return boat name initial.
     */
    public char abbreviation(){
        return Character.toUpperCase(name.charAt(0));
    }

    /**
     * This method determines the size of the boat 
     * given the initial returned from abbreviation().
     * @return the size of the boat given its initial.
     * "Aircraft Carrier" = 5, "Battleship" = 4, 
     * "Cruiser"/"Submarine" = 3, "Destroyer" = 2.
     */
    public int size(){
        switch(abbreviation()){
            case 'A':
                return 5;
            case 'B':
                return 4;
            case 'C':
                return 3;
            case 'S':
                return 3;
            default:
                return 2;
        }
    }

    /**
     * This method determines if the position that 
     * was given from the user is on a boat.
     * @param pos - Position of where the user thinks the boat is.
     * @return a boolean determining if position is on the boat or not.
     */
    public boolean onBoat(Position pos){
        //Traverses through area attribute for boat
        for (Position p: area) {
            if (p.equals(pos))
                return true;
        }
        return false;
    }

    /**
     * This method determines if the boat was hit at a given position.
     * @param pos - Position to check if the boat is hit.
     * @return a boolean determining if the boat was hit or not.
     */
    public boolean isHit(Position pos){
        for (Position p: hit) {
            if (p.equals(pos))
                return true;
        }
        return false;
    }

    /**
	 * Records a given position on the boat as a hit.
	 * @param pos The position at which to record the boat as hit.
	 */
    public void hit(Position pos){
        if (onBoat(pos) && !isHit(pos))
            hit.add(pos);
    }

    /**
	 * Determines if the boat has been sunk 
     * (if every position in the area attribute has been hit).
	 * @return a boolean determining whether every position has been hit
	 */
    public boolean sunk(){
        return size() == hit.size();
    }

    /**
     * Accessor method for the position instance variable in Boat.
     * @return the start position of the boat.
     */
    public Position position(){
        return start;
    }

    /**
     * Accessor method for the direction instance variable in Boat.
     * @return the direction of the boat.
     */
    public String direction(){
        return direction;
    }

    /**
     * toString method for a Boat.
     * @return a String describing the attributes of a given boat.
     */
    public String toString(){
        return "The boat, " + name + ", is at position " + start.toString() + 
               " initially, and is facing " + direction + "ly.";
    }
}