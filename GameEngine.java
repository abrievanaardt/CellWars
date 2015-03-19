/**
 *
 * @author Abrie van Aardt 13178840
 * @author Michelle Swanepoel 13066294
 */
 
public class GameEngine {
    
    public GameEngine(CellWarsUI _ui, int _dimensions, int _cellsPerPlayer){
        ui = _ui;  
        DIMENSIONS = _dimensions;
        CELLS_PER_PLAYER = _cellsPerPlayer;
    }
    
    public void startGame(){
        Board board = new Board(ui, DIMENSIONS, CELLS_PER_PLAYER);
        board.initialiseBoard();
        ui.initiliaseUI(board);        
    }
    
    CellWarsUI ui;
    private final int DIMENSIONS;
    private final int CELLS_PER_PLAYER;
}
