package cellwars_frontend;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Abrie van Aardt 13178840
 * @author Michelle Swanepoel 13066294
 */
public class CellComponent extends JPanel {
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth() , this.getHeight());        
    }
}
