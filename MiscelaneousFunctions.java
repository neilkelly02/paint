import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MiscelaneousFunctions extends JPanel {

    private static final long serialVersionUID = 1L;

    public static BufferedImage miscFunctionsPanel;
    public static BufferedImage selectedThickness;
    public static BufferedImage selectedMiscTool;
    public static int selectedThicknessX = 0;
    public static int selectedThicknessY = 0;
    public static int selectedMiscToolX = 0;
    public static int selectedMiscToolY = 0;

    public MiscelaneousFunctions() {
        super();
        try {                
            miscFunctionsPanel = ImageIO.read(getClass().getResource("Misc_Functions_Panel.png"));
            selectedThickness = ImageIO.read(getClass().getResource("selected.png"));
            selectedMiscTool = ImageIO.read(getClass().getResource("selected.png"));
        } 
        catch (IOException e) {

        }
        addMouseListener(new MiscelaneousFunctionsListener());
        addMouseMotionListener(new MiscelaneousFunctionsListener());
    }

    public void paintComponent(Graphics g) {
        Graphics2D paint = (Graphics2D) g;
        paint.drawImage(miscFunctionsPanel, 0, 0, this);
        paint.drawImage(selectedThickness, selectedThicknessX, selectedThicknessY, this);
        //paint.drawImage(selectedMiscTool, selectedMiscToolX, selectedMiscToolY, this);
    }

	public static void shiftSelectedThickness(int x, int y) {
        selectedThicknessX = x;
        selectedThicknessY = y;
    }
    
    public static void shiftSelectedMiscTool(int x, int y) {
        selectedMiscToolX = x;
        selectedMiscToolY = y;
	}

}
