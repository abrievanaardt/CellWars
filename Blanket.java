import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to describe the field around cells known as the covered areas
 * @author Michelle Swanepoell 13066294
 * @author Abrie van Aardt 13178840
 */
public class Blanket 
{
    private String owner;
    private Coordinate topLeft;
    private Coordinate bottomRight;
    private ArrayList<Cell> coveredCells;
    
    /**
     * Constructor to create the Cell ArrayList
     */
    public Blanket()
    {
        coveredCells = new ArrayList<Cell>();
    }
    
    /**
     * Function to merge to merge a blanket with this blanket
     * @param merger  the blanket that should be merged with this
     */
    public void merge(Blanket merger)
    {
        ArrayList<Cell> mergerCells = merger.getCoveredCells();
        
        Iterator mergerIter = mergerCells.iterator();
        
        //add all the cells in the merger blanket to this blanket's cell list
        while(mergerIter.hasNext())
        {
            coveredCells.add((Cell) mergerIter.next());
        }
        
        mergerIter = coveredCells.iterator();
        
        //set all the cells' limits to the new number of cells under this blanket
        while(mergerIter.hasNext())
        {
            Cell temp = (Cell) mergerIter.next();
            temp.setMaxMoveLimit(coveredCells.size());
        }
        
        int bottomRightX = Math.max(bottomRight.getX(), merger.bottomRight.getX());
        int bottomRightY = Math.max(bottomRight.getY(), merger.bottomRight.getY());
        
        bottomRight.setCoordinates(bottomRightX, bottomRightY);
        
        int topLeftX = Math.min(topLeft.getX(), merger.topLeft.getX());
        int topLeftY = Math.min(topLeft.getY(), merger.topLeft.getY());
        
        topLeft.setCoordinates(topLeftX, topLeftY);
    }
    
    /**
     * Function to add a cell to this blanket. Inserts the new cell into the coveredCell list
     * @param adder  the cell that should be added
     */
    public void add(Cell adder)
    {
        coveredCells.add(adder);
    }
    
    public Boolean touches(Blanket blanket)
    {
        Coordinate thisBotLeft = new Coordinate(topLeft.getX(), bottomRight.getY());
        Coordinate thisTopRight = new Coordinate(bottomRight.getX(), topLeft.getY());
        
        Coordinate otherBotLeft = new Coordinate(blanket.topLeft.getX(), blanket.bottomRight.getY());
        Coordinate otherTopRight = new Coordinate(blanket.bottomRight.getX(), blanket.topLeft.getY());
        
        
        return true;
        
    }
    
     /**
     * Function to get the topLeft object
     * @return the topLeft Coordinate object
     */
    public Coordinate getTopLeft()
    {
        return topLeft;
    }
    
    /**
     * Function to get the bottomRight object
     * @return the bottomRight Coordinate object
     */
    public Coordinate getBottomRight()
    {
        return bottomRight;
    }
    
    /**
     * Function to change/set the blanket's topLeft and bottomRight coordinates
     * @param topX to set the topLeft Coordinate's x value
     * @param topY to set the topLeft Coordinate's y value
     * @param botX to set the bottomRight Coordinate's x value
     * @param botY to set the bottomRight Coordinate's y value
     */
    public void setCoordinates(int topX, int topY, int botX, int botY)
    {
        topLeft.setCoordinates(topX, topY);
        bottomRight.setCoordinates(botX, botY); 
    }
    
    /**
     * Function to set the owner of the blanket
     * @param _owner the current owner of the blanket
     */
    public void setOwner(String _owner)
    {
        owner = _owner;
    }
    
    /**
     * Function to return the variable of type ArrayList called coveredCells
     * @return the coveredCells arrayList
     */
    public ArrayList getCoveredCells()
    {
        return coveredCells;
    }
}
