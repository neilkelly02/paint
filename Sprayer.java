import java.util.ArrayList;

public class Sprayer {

    public static ArrayList<Point> spraySet = new ArrayList<Point>();
    public static int radius = Thickness.getThickness()*6;
    public static int number = (int)(radius*1.2);
    
    public static void spray(int x, int y) {
        for (int i = 0; i < number; i++) {
            double temp = Math.random();
            double hypotenuse;
            if (temp > 0.2) hypotenuse = temp*radius;
            else hypotenuse = Math.random()*radius;
            double a = Math.random()*hypotenuse;
            double b = Math.sqrt((hypotenuse*hypotenuse)-(a*a));
            if (Math.random() > 0.5) a = -a;
            if (Math.random() > 0.5) b = -b;
            if (Math.random() > 0.5) spraySet.add(new Point(x+(int)a, y+(int)b));
            else spraySet.add(new Point(x+(int)b, y+(int)a));
        }
    }

    public static void clearSpraySet() {
        spraySet = new ArrayList<Point>();
    }

    public static void setRadius(int thickness) {
        radius = thickness*6;
        number = (int)(radius*1.2);
    }

}