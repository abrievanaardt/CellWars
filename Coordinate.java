/**
 * Class to encapsulate a coordinate of a cell
 * @author Michelle Swanepoell 13066294
 * @author Abrie van Aardt 13178840
 */
public class Coordinate
{
    private int xCoord;
    private int yCoord;
    
    /**
     * Constructor to initialise the x and y values of this coordinate object
     * @param _xCoord is the value of the x-coordinate
     * @param _yCoord is the value of the x-coordinate
     */
    Coordinate(int _xCoord, int _yCoord)
    {
        xCoord = _xCoord;
        yCoord = _yCoord;
    }

    /**
     * Function to set/change the coordinates of this object
     * @param topX the x coordinate
     * @param topY  the y coordinate
     */
    public void setCoordinates(int topX, int topY)
    {
       xCoord = topX;
       yCoord = topY;
    }
    
    /**
     * Function to get the x coordinate
     * @return int the x coordinate
     */
    public int getX()
    {
        return xCoord;
    }
    
    /**
     * Function to get the y coordinate
     * @return int the y coordinate
     */
    public int getY()
    {
        return yCoord;
    }
    
    /**
     * Function to override the equals function, to check if two Coordinate objects are equal
     * @param other The Coordinate with which "this" must be compared
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object other)
    {
        Coordinate otherCoord = (Coordinate) other;
        int otherX = otherCoord.getX();
        int otherY = otherCoord.getY();
        
        if(otherX == xCoord && otherY == yCoord)
            return true;
        
        return false;
    }
    
    public int compareTo(Coordinate other)
    {
        if ((this.xCoord < other.xCoord) && (this.yCoord < other.yCoord))
            return -1;
        else if (this.equals(other))
            return 0;
        else
            return 1;
    }

}
