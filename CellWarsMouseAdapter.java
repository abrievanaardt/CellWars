
import java.awt.event.MouseAdapter;

/**
 *
 * @author Abrie van Aardt
 */
public class CellWarsMouseAdapter extends MouseAdapter{
    public CellWarsMouseAdapter(Board _board){
        b = _board;
    }
    
    public Board b;
}
