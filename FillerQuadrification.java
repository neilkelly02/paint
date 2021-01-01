import java.util.ArrayList;
import java.util.TreeSet;

public class FillerQuadrification {

    public static ArrayList<Point> squareification(TreeSet<Point> unOptimized) {
        ArrayList<Point> optimized = new ArrayList<Point>();
        while (!unOptimized.isEmpty()) {
            Point temp = unOptimized.first();
            int layer = 1;
            boolean breakLoop = false;
            while (true) {
                for (int i = 0; i <= layer; i++) {
                    if (!unOptimized.contains(new Point(temp.getX() + i,temp.getY() + layer))) {
                        breakLoop = true;
                        break;
                    }
                    if (!unOptimized.contains(new Point(temp.getX() + layer,temp.getY() + i))) {
                        breakLoop = true;
                        break;
                    }
                }
                if (breakLoop) break;
                layer++;
            }
            for (int i = 0; i < layer; i++) {
                for (int j = 0; j < layer; j++) {
                    unOptimized.remove(new Point(temp.getX() + i, temp.getY() + j));
                }
            }
            optimized.add(new Point(temp.getX(), temp.getY()));
            optimized.add(new Point(temp.getX() + layer-1, temp.getY() + layer-1));
        }
        System.out.println(optimized.size()/2);
        return optimized;
    }

    public static ArrayList<Point> quadrification(TreeSet<Point> unOptimized) {
        ArrayList<Point> optimized = new ArrayList<Point>();
        while (!unOptimized.isEmpty()) {
            Point temp = unOptimized.first();
            int layerX = 1;
            int layerY = 1;
            boolean breakLoop = false;
            while (true) {
                while (true) {
                    if (!unOptimized.contains(new Point(temp.getX() + layerX, temp.getY()))) {
                        break;
                    }
                    layerX++;
                }
                while (true) {
                    for (int k = 0; k < layerX; k++) {
                        if (!unOptimized.contains(new Point(temp.getX() + k, temp.getY() + layerY))) {
                            breakLoop = true;
                            break;
                        }
                    }
                    if (breakLoop) break;
                    layerY++;
                }
                if (breakLoop) break;
            }
            for (int i = 0; i < layerX; i++) {
                for (int j = 0; j < layerY; j++) {
                    unOptimized.remove(new Point(temp.getX() + i, temp.getY() + j));
                }
            }
            optimized.add(new Point(temp.getX(), temp.getY()));
            optimized.add(new Point(temp.getX() + layerX-1, temp.getY() + layerY-1));
        }
        return optimized;
    }
    
}