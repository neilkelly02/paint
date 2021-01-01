import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MiscelaneousFunctionsListener extends MouseInputAdapter {
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (y > 0 && y < 50) {
            if (x > 0 && x < 50) {
                MiscelaneousFunctions.shiftSelectedThickness(0, 0);
                ControlListener.miscPanel.repaint();
                Thickness.setThickness(1);
            }
            else if (x > 50 && x < 100) {
                MiscelaneousFunctions.shiftSelectedThickness(50, 0);
                ControlListener.miscPanel.repaint();
                Thickness.setThickness(2);
            } 
            else if (x > 100 && x < 150) {
                MiscelaneousFunctions.shiftSelectedThickness(100, 0);
                ControlListener.miscPanel.repaint();
                Thickness.setThickness(3);
            } 
            else if (x > 150 && x < 200) {
                MiscelaneousFunctions.shiftSelectedThickness(150, 0);
                ControlListener.miscPanel.repaint();
                Thickness.setThickness(4);
            } 
            else if (x > 200 && x < 250) {
                MiscelaneousFunctions.shiftSelectedThickness(200, 0);
                ControlListener.miscPanel.repaint();
                Thickness.setThickness(5);
            } 
            else if (x > 250 && x < 300) {
                MiscelaneousFunctions.shiftSelectedThickness(250, 0);
                ControlListener.miscPanel.repaint();
                Thickness.setThickness(6);
            } 
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        
    }
    
}