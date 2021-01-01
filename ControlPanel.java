import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ControlPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public static BufferedImage controlPanel;
    public static BufferedImage selectedTool;
    public static BufferedImage selectedColor;
    public static BufferedImage selectedIndicator;
    public static int panelShift = 0;
    public static int selectedToolX = 0;
    public static int selectedToolY = 0;
    public static int selectedColorX = 300;
    public static int selectedColorY = 0;
    public static int selectedIndicatorX = 0;
    public static int selectedIndicatorY = 0;
    public static int aestheticShift = 13;
    public static boolean indicatorVisible = false;
    public static Color[] colorPalet = {Color.RED, Color. BLUE, Color.YELLOW, Color.GREEN};

    public ControlPanel() {
        super();
        try {                
            controlPanel = ImageIO.read(getClass().getClassLoader().getResource("Control_Panel.png"));
            selectedTool = ImageIO.read(getClass().getClassLoader().getResource("selected.png"));
            selectedColor = ImageIO.read(getClass().getClassLoader().getResource("selected.png"));
            selectedIndicator = ImageIO.read(getClass().getClassLoader().getResource("selected.png"));
            panelShift = (PaintRunner.SIZE_X - controlPanel.getWidth()) / 2;
        } 
        catch (IOException e) {

        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D paint = (Graphics2D) g;
        drawPanel(paint);
        drawPreSetColors(paint);
        drawColorPalet(paint);
        drawSelectImage(paint);
    }

    public void drawPanel(Graphics2D paint) {
        //Draw Control Panel Image
        paint.setColor(Color.LIGHT_GRAY);
        paint.fillRect(0, 0, PaintRunner.SIZE_X, PaintRunner.C_PANEL_HEIGHT);
        paint.drawImage(controlPanel, panelShift, 0, this);
    }

    public void drawPreSetColors(Graphics2D paint) {
         //Pre Set Black Color
         paint.setColor(Color.BLACK);
         paint.fillRect(300 + aestheticShift + panelShift, aestheticShift, 20, 20);
         paint.setColor(Color.WHITE);
         paint.drawRect(300 + aestheticShift + panelShift, aestheticShift, 20, 20);
         //Pre Set White Color
         paint.setColor(Color.WHITE);
         paint.fillRect(350 + aestheticShift + panelShift, aestheticShift, 20, 20);
         paint.setColor(Color.BLACK);
         paint.drawRect(350 + aestheticShift + panelShift, aestheticShift, 20, 20);
    }

    public void drawColorPalet(Graphics2D paint) {
        //Color Option 1
        paint.setColor(colorPalet[0]);
        paint.fillRect(200 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        if (colorPalet[0].getRed() + colorPalet[0].getRed() + colorPalet[0].getRed() > 382.5) paint.setColor(Color.BLACK);
        else paint.setColor(Color.WHITE);
        paint.drawRect(200 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        //Color Option 2
        paint.setColor(colorPalet[1]);
        paint.fillRect(250 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        if (colorPalet[1].getRed() + colorPalet[1].getRed() + colorPalet[1].getRed() > 382.5) paint.setColor(Color.BLACK);
        else paint.setColor(Color.WHITE);
        paint.drawRect(250 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        //Color Option 3
        paint.setColor(colorPalet[2]);
        paint.fillRect(300 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        if (colorPalet[2].getRed() + colorPalet[2].getRed() + colorPalet[2].getRed() > 382.5) paint.setColor(Color.BLACK);
        else paint.setColor(Color.WHITE);
        paint.drawRect(300 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        //Color Option 4
        paint.setColor(colorPalet[3]);
        paint.fillRect(350 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
        if (colorPalet[3].getRed() + colorPalet[3].getRed() + colorPalet[3].getRed() > 382.5) paint.setColor(Color.BLACK);
        else paint.setColor(Color.WHITE);
        paint.drawRect(350 + aestheticShift + panelShift, 50 + aestheticShift, 20, 20);
    }

    public void drawSelectImage(Graphics2D paint) {
        //Paint Tool Select Image
        paint.drawImage(selectedTool, selectedToolX + panelShift, selectedToolY, this);
        //Paint Color Select Image
        paint.drawImage(selectedColor, selectedColorX + panelShift, selectedColorY, this);
        //Paint Selected Indicator if necessary
        if (indicatorVisible) paint.drawImage(selectedIndicator, selectedIndicatorX + panelShift, selectedIndicatorY, this);
    }

    public static void shiftSelectedTool(int x, int y) {
        selectedToolX = x;
        selectedToolY = y;
    }

    public static void shiftSelectedColor(int x, int y) {
        selectedColorX = x;
        selectedColorY = y;
    }

    public static void shiftSelectedIndicator(int x, int y) {
        selectedIndicatorX = x;
        selectedIndicatorY = y;
    }

    public static void shiftColorPalet(Color newColor) {
        for (int i = colorPalet.length - 1; i > 0; i--) {
            colorPalet[i] = colorPalet[i-1];
        }
        colorPalet[0] = newColor;
    }

    public static void adjustColorPalet(Color newColor) {
        colorPalet[0] = newColor;
    }

    public static Color getColorPalet(int i) {
        return colorPalet[i];
    }
}
