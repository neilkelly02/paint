import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Filler {

    public BufferedImage img;
    
    public Filler(BufferedImage imgIn) {
    	img = imgIn;
    }

    public TreeSet<Point> fill(int x, int y) {

        Color defaultColor = new Color(img.getRGB(x, y), true);
        int alphaDef = defaultColor.getAlpha();
        int redDef = defaultColor.getRed();
        int greenDef = defaultColor.getGreen();
        int blueDef = defaultColor.getBlue();
        // int defaultColorInt = img.getRGB(x,y);
        // int redDef = (defaultColorInt & 0x00ff0000) >> 16;
        // int greenDef = (defaultColorInt & 0x0000ff00) >> 8;
        // int blueDef = defaultColorInt & 0x000000ff;

        TreeSet<Point> stack = new TreeSet<Point>();
        stack.add(new Point(x,y));
        
        TreeSet<Point> visited = new TreeSet<Point>();

        while(!stack.isEmpty()) {
            Point currentPoint = stack.first();
            stack.remove(stack.first());
            visited.add(currentPoint);

            int tempX = currentPoint.getX();
            int tempY = currentPoint.getY();

            ArrayList<Point> adjacent = new ArrayList<Point>();
            adjacent.add(new Point(tempX-1, tempY));
            adjacent.add(new Point(tempX, tempY+1));
            adjacent.add(new Point(tempX+1, tempY));
            adjacent.add(new Point(tempX, tempY-1));

            for (Point p : adjacent) {
                if (withinBounds(p.getX(), p.getY())) {
                    // int clr = img.getRGB(p.getX(),p.getY());
                    // int red = (clr & 0x00ff0000) >> 16;
                    // int green = (clr & 0x0000ff00) >> 8;
                    // int blue = clr & 0x000000ff;
                    Color clr =  new Color(img.getRGB(p.getX(),p.getY()), true);
                    int alpha = clr.getAlpha();
                    int red = clr.getRed();
                    int green = clr.getGreen();
                    int blue = clr.getBlue();
                    if((red == redDef && green == greenDef && blue == blueDef && alpha == alphaDef)) {
                        if(!visited.contains(p)) {
                            stack.add(p);
                        }
                    }
                }
            }
        }

        return visited;
    }

    public static boolean withinBounds(int x, int y) {
        return x < PaintRunner.CANVAS_SIZE_X && y < PaintRunner.CANVAS_SIZE_Y && x >= 0 && y >= 0;
    }

}
