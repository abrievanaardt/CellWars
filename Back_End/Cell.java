/**
 * Class to describe the cells owned by a player
 * @author Michelle Swanepoell 13066294
 * @author Abrie van Aardt 13178840
 */
public class Cell 
{
    private int maxMoveLimit;
    private Coordinate coordinate;
    private Boolean marked;
    private String owner;
    
    /**
     * Constructor to create a new Coordinate object for a cell and initialise the marked variable and also owner
     * @param x the initial x coordinate of the cell
     * @param y the initial y coordinate of the cell
     * @param _owner to initialise the current owner
     */
    public Cell(int x, int y, String _owner)
    {
        coordinate = new Coordinate(x,y);
        marked = false;
        owner = _owner;
    }
    
    
    /**
     * Function to set/change the coordinates of this cell
     * @param x the new x coordinate of the cell
     * @param y the new y coordinate of the cell
     */
    public void setCoordinate(int x, int y)
    {
        coordinate.setCoordinates(x,y);
    }
    
    /**
     * Function to get the coordinate object of this cell
     * @return the Coordinate object
     */
    public Coordinate getCoordinate()
    {
        return coordinate;
    }
    
    /**
     * Function to change the value of marked to true
     */
    public void mark()
    {
        marked = true;
    }
    
    /**
     * Function to change the value if marked to false
     */
    public void unmark()
    {
        marked = false;
    }
    
    /**
     * Function to get the value of marked
     * @return Boolean, whether marked is true or false
     */
    public Boolean isMarked()
    {
        return marked;
    }
    
    public void addNeighboursToBlanket(Cell[] allCells, Blanket blanket)
    {
        //implement
    }
    
    public void getUnmarkedImmedNeigbours(Cell[] allCells)
    {
        //implement
    }
    
    /**
     * get the current owner
     * @return String, the current owner
     */
    public String getOwner()
    {
        return owner;
    }
    
    /**
     * Function to change/set the owner
     * @param _owner 
     */
    public void setOwner(String _owner)
    {
        owner = _owner;
    }
    
    /**
     * Function to get the current limit of the cell
     * @return int, the current limit
     */
    public int getMaxMoveLimit()
    {
        return maxMoveLimit;
    }
    
    /**
     * Change the limit that this cell may move in a single move
     * @param _limit the new limit
     */
    public void setMaxMoveLimit(int _limit)
    {
        maxMoveLimit = _limit;
    }
    
}
