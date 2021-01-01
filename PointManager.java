import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.Color;

public class PointManager {

    private static ArrayList<Point> points;
    private static ArrayList<Stroke> library;
    private static ArrayList<Stroke> undoStack;
    
    public static int leftBound = PaintRunner.CANVAS_LOCATION.getX();
    public static int rightBound = PaintRunner.CANVAS_LOCATION.getX() + PaintRunner.CANVAS_SIZE_X;
    public static int upperBound = 0;
    public static int lowerBound = PaintRunner.CANVAS_SIZE_Y;

    public static Color color = Color.BLACK;

    public PointManager() {
        points = new ArrayList<Point>();
        library = new ArrayList<Stroke>();
        undoStack = new ArrayList<Stroke>();
    }

    public static ArrayList<Point> getPoints() {
		return points;
	}

	public static void setPoints(ArrayList<Point> pointsIn) {
		points = pointsIn;
    }
    
    public static void removeDuplicates() {
        ArrayList<Point> newList = new ArrayList<Point>();
        for (Point p : points) {
            if (!contains(p, newList)) newList.add(p);
        }
        points = newList;
    }

    public static boolean contains(Point pIn, ArrayList<Point> listIn) {
        for (Point p : listIn) {
            if (p.equals(pIn)) return true;
        }
        return false;
    }

    //General Add Stroke To Library Method
    public static void addToLib(ArrayList<Point> newPoints) {
        int[] newArrayPointsX = new int[newPoints.size()];
        int[] newArrayPointsY = new int[newPoints.size()];
        for (int i = 0; i < newPoints.size(); i++) {
            newArrayPointsX[i] = newPoints.get(i).getX();
        }
        for (int i = 0; i < newPoints.size(); i++) {
            newArrayPointsY[i] = newPoints.get(i).getY();
        }
        library.add(new Stroke(newArrayPointsX, newArrayPointsY, color, Mode.getMode(), Thickness.getThickness()));
    }

    //Specialized for Fill Algorithm
    public static void addToLib(TreeSet<Point> newPoints) {
        ArrayList<Point> optimized = FillerQuadrification.quadrification(newPoints);
        int[] newArrayPointsX = new int[optimized.size()];
        int[] newArrayPointsY = new int[optimized.size()];
        for (int i = 0; i < optimized.size(); i++) {
            newArrayPointsX[i] = optimized.get(i).getX();
            newArrayPointsY[i] = optimized.get(i).getY();
        }
        library.add(new Stroke(newArrayPointsX, newArrayPointsY, color, Mode.getMode(), 1));
    }

    public static ArrayList<Stroke> getLibrary() {
        return library;
    }

    public static void boundsManager() {
        for (Point p : points) {
            if (p.getX() < leftBound) p.setX(leftBound);
            if (p.getX() > rightBound) p.setX(rightBound);
            if (p.getY() < upperBound) p.setY(upperBound);
            if (p.getY() > lowerBound) p.setY(lowerBound);
        }
    }

    public static Color getColor() {
        return color;
    }
    
    public static void setColor(Color colorIn) {
        color = colorIn;
    }

    public static void addToUndo() {
        if (!library.isEmpty()) {
            undoStack.add(library.get(library.size()-1));
            library.remove(library.size()-1);
        }
    }

    public static void addToUndo(int i) {
        undoStack.add(library.get(i));
        library.remove(i);
    }

    public static void redo() {
        if (!undoStack.isEmpty()) {
            library.add(undoStack.get(undoStack.size()-1));
            undoStack.remove(undoStack.size()-1);
        }
    }

    public static void trash() {
        library = new ArrayList<Stroke>();
        undoStack = new ArrayList<Stroke>();
    }

}
