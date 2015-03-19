
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * Class to describe the board, where most of the game will take place, which has Blanket objects and also Cell objects
 * @author Michelle Swanepoell 13066294
 * @author Abrie van Aardt 13178840
 */
public class Board 
{
    private final Cell[] cells;
    private ArrayList<Blanket> blankets;
    private final int dimensions;
    private final int totCellCount;
    private Coordinate activeFrom;
    private Coordinate activeTo;
    private final CellWarsUI GUI;
    
    /**
     * Constructor to initialise the dimensions, totCellCount, Cell array and Blanket array
     * @param ui User interface object used to display the board state
     * @param _dimensions the number of rows and columns of the board specified by the user
     * @param _CellCount  the number of cells of each player as specified by the user
     */
    public Board(CellWarsUI ui, int _dimensions, int _CellCount)
    {
        dimensions = _dimensions;
        totCellCount = _CellCount * 2;
        cells = new Cell[totCellCount];
        blankets = new ArrayList<Blanket>();
        activeFrom = null;
        activeTo = null;
        GUI = ui;
    }
    
    /**
     * Function to initialise the board for the first time with the number of cells for each player stated by the user
     * Uses the Math.random() function to have the cells at a random place every time
     * Each cell has an owner which is specified and all the cells for a player starts on one side of the board
     * The cells which are generated with the random coordinates are added to the cells list
     */
    public void initialiseBoard()
    {
        Random r = new Random(new Date().getTime());
        Boolean placed;
      //initialise cell arrayList    
        int i;
        for(i = 0; i < totCellCount/2; i++)
        {
            placed = false;
            while(!placed)
            {
                placed = true;
                int x = r.nextInt(dimensions/2 -1);
                int y = r.nextInt(dimensions);
                
                Cell tempCell = new Cell(x,y, "p1");
                
                if(isOccupied(tempCell.getCoordinate()))
                {                
                    placed = false;
                }
                else
                {
                    cells[i] = tempCell;
                }
                   
            }
        }
        
        for(int j = i; j < totCellCount; j++)
        {
            placed = false;
            while(!placed)
            {
                placed = true;
                int x = (dimensions/2 +1) + r.nextInt(dimensions - dimensions/2 - 1);//possible issue
                int y = r.nextInt(dimensions);
                
                Cell tempCell = new Cell(x,y, "p2");
                
                if(isOccupied(tempCell.getCoordinate()))
                {
                    placed = false;
                }
                else
                {
                    cells[j] = tempCell;
                }
                   
            }
        }
        
        
        //intialise blanket array
//        for(Cell cell : cells)
//        {
//            Blanket temp = new Blanket();
//            cell.setMaxMoveLimit(1);
//            temp.add(cell);
//            temp.setOwner(cell.getOwner());
//            
//            int topx = cell.getCoordinate().getX();
//            int topy = cell.getCoordinate().getY();
//            
//            if(topx-1 >= 0)
//            {
//                topx = topx-1;
//            }
//            if(topy-1 >= 0)
//            {
//                topy = topy-1;
//            }
//            
//            int botx = cell.getCoordinate().getX();
//            int boty = cell.getCoordinate().getY();
//            
//            if(botx+1 < dimensions)
//            {
//                botx = botx + 1;
//            }
//            if(boty+1 < dimensions)
//            {
//                boty = boty + 1;
//            }
//            
//            temp.setCoordinates(topx,topy,botx,boty);
//            
//            blankets.add(temp);          
//        }
        
        identifyBlankets();
    }
    
    /**
     * Function that takes a coordinate and assigns it to either the from Coordinate or the to Coordinate, if it is the to coordinate, the move function is also called to move the cell
     * @param coord is the coordinate to where it wants to move or from where it wants to move
     */
    public void acceptCoordinate(Coordinate coord)
    {
        
        if(getCell(coord) != null)
        {
            activeFrom = coord;
        }
        else
        {
            if(activeFrom != null)
            {
                activeTo = coord;
                move(activeFrom, activeTo);
            }
        }
    }
    /**
     * Function to initiate a move and also do maintenance after the move, first do checks and then move and also recalculate blankets
     * @param from the Coordinate from where the cell is moving
     * @param to  the Coordinate to which the cell wants to move 
     */
    public void move(Coordinate from, Coordinate to)
    {
        if(!isWithinLimit(from,to) || isOccupied(to) || !isInBoundaries(to) || !isStraight(from,to))
            return;
        
        Cell cell = getCell(from);
        
        //change coordinates
        cell.setCoordinate(to.getX(), to.getY());
        
        identifyBlankets();
        //capture cells
        
        GUI.updateUI(this);
        
        activeFrom = null;
        activeTo = null;
        
        
    }
    
    /**
     * Function to check if the move that the user is trying to make will cause the cell to exit the board (out of bounds)
     * @param from The coordinate from where it wants to move
     * @param to    The coordinate to where it wants to move
     * @return a Boolean value, false if it is not in the boundaries
     */
    private Boolean isInBoundaries(Coordinate to)
    {
        if(to.getX() < dimensions && to.getX()>= 0 && to.getY() < dimensions && to.getY()>= 0)
            return true;
        
        return false;
    }
    
    /**
     * Function to test whether the move is straight or diagonal
     * @param from The coordinate from where the cell is moving
     * @param to The Coordinate to where the cell wants to go
     * @return true if the move is a straight move
     */
    private Boolean isStraight(Coordinate from, Coordinate to)
    {
        if(from.getX() == (to.getX()) || from.getY() == to.getY())
        {
            return true;
        }
        return false;
    }
    /**
     * Function to check if the move that the user is trying to make will cause the cell to exceed its moveLimit that it is allowed
     * @param from The coordinate from where it wants to move
     * @param to The coordinate to where it wants to move
     * @return a Boolean value, false if it is not in the boundaries
     */
    private Boolean isWithinLimit(Coordinate from, Coordinate to)
    {
        int xMove = Math.abs(from.getX() - to.getX());
        int yMove = Math.abs(from.getY() - to.getY());
        
        Cell thisCell = getCell(from);
        
        return !(xMove > thisCell.getMaxMoveLimit() || yMove > thisCell.getMaxMoveLimit());
    }
    
    /**
     * Function to check if the next possible space is taken up by another cell already
     * @param to The coordinate where there must be checked for a cell
     * @return Boolean, whether there is a cell already occupying the space
     */
    private Boolean isOccupied(Coordinate to)
    {
        for (Cell cell : cells) 
        {
            if(cell != null)
            {
                if (cell.getCoordinate().equals(to)) 
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Function to get the cell with the specified coordinate
     * @param source the coordinate where th cell is which we are looking for
     * @return the cell at the coordinate space
     */
    private Cell getCell(Coordinate source)
    {
        for (Cell cell : cells) 
        {
            if (cell.getCoordinate().equals(source)) 
            {
                return cell;
            }
        }
        
        return null;
    }
    
    /**\
     * Function to identify all the small blankets, created by seeing which cells are close enough, then merge all small blankets into big ones by seeing if they touch
     */
    private void identifyBlankets()
    {
        blankets = new ArrayList<Blanket>();
        
        //unmark all cells
        for(Cell cell: cells)
        {
            cell.unmark();
        }
        
        //for all unmarked cells, get neighbours and put under one blanket
        for(Cell cell: cells)
        {
            Blanket blank = new Blanket();
            if(!cell.isMarked())
            {
                cell.addNeighboursToBlanket(cells, blank);
            }
            
            Iterator coveredCells = blank.getCoveredCells().iterator();
            //set all the cells' limits to the new number of cells under this blanket
            while(coveredCells.hasNext())
            {
                Cell temp = (Cell) coveredCells.next();
                temp.setMaxMoveLimit(blank.getCoveredCells().size());
            }
            
            //determine new blanket size (coordinates)
            Iterator cellsIter = blank.getCoveredCells().iterator();
            int bottomRightX = -1;
            int bottomRightY = -1;
            int topLeftX = dimensions;
            int topLeftY = dimensions;                    
            int cellTopLeftX;
            int cellTopLeftY;
            int cellBottomRightX;
            int cellBottomRightY;
                    
            Cell currentCell;
            while (cellsIter.hasNext())
            {
                currentCell = (Cell) cellsIter.next();
                cellTopLeftX = currentCell.getCoordinate().getX() == 0 ? 0 : currentCell.getCoordinate().getX()-1;
                cellTopLeftY = currentCell.getCoordinate().getY() == 0 ? 0 : currentCell.getCoordinate().getY()-1;
                cellBottomRightX = currentCell.getCoordinate().getX() == dimensions-1 ? dimensions-1 : currentCell.getCoordinate().getX()+1;
                cellBottomRightY = currentCell.getCoordinate().getY() == dimensions-1 ? dimensions-1 : currentCell.getCoordinate().getY()+1;

                bottomRightX = Math.max(cellBottomRightX, bottomRightX);
                bottomRightY = Math.max(cellBottomRightY, bottomRightY);

                topLeftX = Math.min(cellTopLeftX, topLeftX);
                topLeftY = Math.min(cellTopLeftY, topLeftY);
            }
            
            blank.setCoordinates(topLeftX, topLeftY, bottomRightX, bottomRightY);
            blankets.add(blank);
        }
        
        //unmark all cells
//        for(Cell cell: cells)
//        {
//            cell.unmark();
//        }
        
        //merge all new blankets to maybe form bigger ones
        Boolean didMerge = true;
        while(didMerge)
        {
            didMerge = false;
            
            Iterator iterBlankOne = blankets.iterator();
            Iterator iterBlankTwo = blankets.iterator();
            Blanket blanketOne;
            Blanket blanketTwo;
            
            
            while(iterBlankOne.hasNext())
            {
                blanketOne = (Blanket) iterBlankOne.next();
                while(iterBlankTwo.hasNext())
                {
                    blanketTwo = (Blanket) iterBlankTwo.next();
                    if(!blanketTwo.equals(blanketOne))//possible issue
                    {
                        if(blanketOne.touches(blanketTwo))
                        {
                            blanketOne.merge(blanketTwo);
                            blankets.remove(blanketTwo);//possible issue
                            didMerge = true;
                        }
                    }
                    if (didMerge)
                        break;
                }
                if(didMerge)
                    break;
            }
        }
    }
   
    public ArrayList getBlankets()
    {
        return blankets;
    }
    
    public Cell[] getCells()
    {
        return cells;
    }
            
}
