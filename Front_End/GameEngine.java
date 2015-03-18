package cellwars_frontend;

/**
 *
 * @author Abrie van Aardt 13178840
 * @author Michelle Swanepoel 13066294
 */
public class GameEngine {
    
    public GameEngine(CellWarsUI _ui){
        ui = _ui;        
    }
    
    public void startGame(){
        Board board = new Board();
        ui.initiliaseUI(board);
        
    }
    
    CellWarsUI ui;
}
