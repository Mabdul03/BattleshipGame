/**
 * @author - Meer Abdullah
 * @since: March 3rd, 2021
 * Synopsis: The Ocean class is where Boats can be 
 * created for the BattleShip game project.
 * @version: 1.5
 */
public class Ocean{
    private Boat [] boats;
    private int size;

    /**
     * Constructor to initialize an Ocean. 5 Boats, 
     * the amount in a battleship game, are created.
     */
    public Ocean(){
        boats = new Boat[5];
        size = 0;
    }
    /**
     * This method passes in attributes of a Boat in order to
     * create a boat and place it within the Ocean.
     * Exceptions are thrown when parameters are not valid.
     * i.e, Boat goes off the Ocean or the instance of Position is off 
     * the board initially.
     * In essence, this method adds each Boat to the Ocean.
     */
    public void placeBoat(String boatName, Position pos, 
                          String direction) throws Exception {
        /* Boat being created to place in the ocean */
        Boat boat = new Boat(boatName, pos, direction);
      
        /* Checks for an exception - if boats overlap, an exception is thrown */
        for (Boat b: boats) {
            if (b == null)
                 break;
            for (Position position: boat.area) {
                if (b.onBoat(position))
                    throw new Exception();
            }
        }
      
        /* Checks for the exception; row/col index is negative,
         * or when the row/col index is greater than 9 */
        for (Position position: boat.area) {
            if (position.columnIndex() < 0 || position.rowIndex() < 0 || 
               position.rowIndex() > 9 || position.columnIndex() > 9)
               throw new Exception();
        }

        /* Checks for another exception; if the direction is vertical,
         * then we check if the boat size + the starting position of 
         * the boat is > 9 because the boat can't go over the "Ocean" */
        if (direction.equals("vertical") && 
            boat.size() + pos.rowIndex() > 9)
            throw new Exception();
        else if (direction.equals("horizontal") && 
            boat.size() + pos.columnIndex() > 9)
            throw new Exception();

        for(int i = 0; i < boats.length; i++){
            if (boats[i] == null) {
                /* places a boat in the ocean */
                boats[i] = boat;
                size++;
                /* prints the attributes of the boat being created */
                for (int j = 0; j < boats[i].size(); j++) {
                    System.out.println("A boat, " + boat.name() + 
                                       " has been created at " + 
                                       boats[i].position().row() + "-" + 
                                       boats[i].position().column());
                }
                return;
            }
        }
    }

    /**
     * precondition - none;
     * postcondition- places and updates the Ocean or Grid so 
     * the boats created dont go off the grid or overlap other boats.
     * This method places all the 5 boats onto the 10x10 grid 
     * with no exceptions thrown. In essence, places all the boats.
     */
    public void placeAllBoats(){
        int row, column, i = 0;
        String direction;
        String [] boatNames = {"Aircraft Carrier", "Battleship", 
                               "Cruiser", "Destroyer", "Submarine"};
        while (i < boatNames.length) {
            try{
                row = (int)(Math.random() * 10) + 1;
                column = (int)(Math.random() * 10) + 1;
                direction = (int)(Math.random() * 2) 
                    == 0 ? "horizontal": "vertical";
                placeBoat(boatNames[i], new Position(row, column), 
                          direction); //placeBoat method call w/initialized args
            } catch(Exception e) {
                i--; /* if Exception thrown from overlapping boat in placeBoat,
                      * or outside the grid, this re-does boat i */
            }
            i++; //goes to next boat i.
        }
    }

    /**
     * This method shoots at a particular position in the ocean. 
     * If there is a boat overlapping that position,
     * then Ocean records *that the boat has been hit on that spot.
     * @param pos - Instance of Position to shoot at that spot in the ocean.
     */
    public void shootAt(Position pos){
        for (int i = 0; i < boats.length; i++) {
            /* checks to see if a boat is at the position */
            if (boats[i].onBoat(pos)) {
                /* shoots at position */
                boats[i].hit(pos);
                System.out.println("Hit");
                return;
            } 
        }
        System.out.println("Missed");
    }

    /**
     * This method returns true if the position 
     * has been shot at and hit, false otherwise.
     * @param pos - Instance of Position to determine
     * if a boat was hit at that position in the ocean.
     * @return if a boat was hit or not.
     */
    public boolean hit(Position pos){
        for (int i = 0; i < boats.length; i++) {
            if (boats[i].isHit(pos))
                return true;
        }
        return false;
    }

    /**
     * If the position has been shot at and hit, 
     * this observer method returns the initial of the boat being hit 
     * (‘A’,’B’,’C’,’D’,’S’). Assume this method will not be 
     * called on a position that has not already been hit.
     * @param - Instance of Position to determine
     * the initial of the boat that was hit.
     * @return an asterisk if a boat was not hit,
     * or the initial of the boat that was hit.
     */
    public char boatInitial(Position pos){
        int i = 0;
        while (i < boats.length && !boats[i].onBoat(pos)) {
            i++;
        }
        /* ternary statement - looks nice :) */
        return i == 5 ? '*' : boats[i].abbreviation();
    }
    /**
     * If the position has been shot at and hit, 
     * this observer method returns the name of the boat being hit. 
     * Assume this method will not be called on a position that 
     * has not already been hit.
     * @param pos - Instance of Position to determine
     * the name of the boat that was hit.
     * @return an empty string if a boat was not 
     * hit, or the name of the boat that was hit.
     */
    public String boatName(Position pos){
        int i = 0;
        while (i < boats.length && !boats[i].onBoat(pos)) {
            i++;
        }
        /* ternary statement - looks nice :) */
        return i == 5 ? " " : boats[i].name();
    }

    /**
     * This method returns true if a boat 
     * overlapping the position has been sunk, 
     * and false otherwise.
     * @param pos - Instance of Position to determine if 
     * the boat at that certain position has indeed sunk.
     * @return a boolean that determines if
     * the boat was sunk at the position.
     */
    public boolean sunk(Position pos){
        for (int i = 0; i < boats.length; i++) {
            if (boats[i].onBoat(pos) && boats[i].sunk())
                return true;
        }
        return false;
    }

    /**
     * This method determines if all the boats
     * in Ocean have sunk.
     * @return a boolean that determines if
     * the boats within the ocean have sunk.
     */
    public boolean allSunk(){
        int counter = 0;
        for (Boat m : boats) {
            if (m.sunk())
                counter++;
        }
        return counter == 5;
    }
}
