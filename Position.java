/**
 * @author Meer Abdullah
 * @since: February 18th, 2021
 * Synopsis: Creates a grid position with an alphabetic row 
 * and numeric column or numeric row and column indexes (i.e., A-5 or 1-10).
 * @version 1.1
 */
public class Position{

    private char row;
    private int column;

    /** 
     * Constructor takes parameters for row (char) and column (int),
     * and assigns them to the instance variables.
     * @param row - alphabetical row that the user enters (A-J).
     * @param column - integer column that the user enters (0-9).
     */
    public Position(char row, int column){
        this.row = row;
        this.column = column;
    }

    /** 
     * Overloaded constructor: takes in parameters for row and column, 
     * and assigns them to the instance variables.
     * @param rowIndex - int row that the user enters (0-9).
     * @param columnIndex - int column that the user enters (1-10).
     */
    public Position(int rowIndex, int columnIndex){
        row = (char)(rowIndex + 65);
        column = columnIndex + 1;
    }

    /**
     * Accessor method for the positions row - alphabetic 
     * @return the alphabetical row (A-J).
     */
    public char row(){
        return row;
    }

    /** 
     * Accessor method for the positions column - numeric
     * @return the numeric column (0-9).
     */
    public int column(){
        return column;
    }

    /**
     * Accessor method for the positions column - numeric
     * Note: offset by 1 in comparison to column (subtracting 1 from 1-10 range)
     * @return the offset of column which represents the columnIndex.
     */
    public int columnIndex(){
        return column - 1;
    }

    /**
     * Accessor emthod for the positions column - numeric
     * Note: 'A' in ASCII is 65. Offset by 65 to set 'A' to 0.
     * @return offset of ASCII character which represents the rowIndex
     */
    public int rowIndex(){
        return row - 65;
    }

    /** 
     * toString method for the Position class.
     * @return a string in which is formatted such as "(CHAR)-(INT)"
     */
    public String toString(){
        return row + "-" + column;
    }
}