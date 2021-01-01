import java.awt.Color;
import java.util.ArrayList;

public class Stroke {

    private int[] pointsX;
    private int[] pointsY;
    private Color color;
    private String mode;
    private int thickness;

    public Stroke(int[] pointsXIn, int[] pointsYIn, Color colorIn, String modeIn, int thicknessIn) {
        if (modeIn.equals("r")) rStrokes(pointsXIn, pointsYIn);
        else if (modeIn.equals("o")) oStrokes(pointsXIn, pointsYIn);
        else {
            this.pointsX = pointsXIn;
            this.pointsY = pointsYIn;
        }
        this.color = colorIn;
        this.mode = modeIn;
        this.thickness = thicknessIn;
    }

    public int[] getPointsX() {
        return pointsX;
    }

    public int[] getPointsY() {
        return pointsY;
    }

    public Color getColor() {
        return color;
    }

    public String getMode() {
        return mode;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thicknessIn) {
        this.thickness = thicknessIn;
    }
    
    //Specialized Rectangle Stroke
    public void rStrokes(int[] pointsXIn, int[] pointsYIn) {
        int[] x = new int[7];
        int[] y = new int[7];
        for (int i = 0; i < pointsXIn.length; i++) {
            x[i] = pointsXIn[i];
            y[i] = pointsYIn[i];
        }
        //Top Left
        x[2] = pointsXIn[0];
        y[2] = pointsYIn[0];
        //Top Right
        x[3] = pointsXIn[1];
        y[3] = pointsYIn[0];
        //Bottom Right
        x[4] = pointsXIn[1];
        y[4] = pointsYIn[1];
        //Bottom Left
        x[5] = pointsXIn[0];
        y[5] = pointsYIn[1];
        //Top Left
        x[6] = pointsXIn[0];
        y[6] = pointsYIn[0];
        this.pointsX = x;
        this.pointsY = y;
    }

    //Specialized Oval Stroke
    public void oStrokes(int[] pointsXIn, int[] pointsYIn) {
        ArrayList<Integer> xList = new ArrayList<Integer>();
        ArrayList<Integer> yList = new ArrayList<Integer>();
        //Center of th ellipse
        int cx = (int)(pointsXIn[0] + pointsXIn[1]) / 2; 
        int cy = (int)(pointsYIn[0] + pointsYIn[1]) / 2; 
        //Major and minor axis
        double a = Math.abs(pointsXIn[0] - pointsXIn[1]) / 2;
        double b = Math.abs(pointsYIn[0] - pointsYIn[1]) / 2;
        for (int i = 0; i <= 360; i++) { 
            double x = a * Math.sin(Math.toRadians(i)); 
            double y = b * Math.cos(Math.toRadians(i)); 
            if (i != 0) { 
                xList.add((int)x + cx);
                yList.add((int)y + cy);
            } 
        }
        int[] x = new int[xList.size() + 3];
        int[] y = new int[yList.size() + 3];
        x[0] = pointsXIn[0];
        y[0] = pointsYIn[0];
        x[1] = pointsXIn[1];
        y[1] = pointsYIn[1];
        for (int i = 0; i < xList.size(); i++) {
            x[i + 2] = xList.get(i);
            y[i + 2] = yList.get(i);
        } 
        x[xList.size() + 2] = xList.get(0);
        y[yList.size() + 2] = yList.get(0);
        this.pointsX = x;
        this.pointsY = y;
    }

    //Specialized Spraypaint Stroke
    public void sStrokes(int[] pointsXIn, int[] pointsYIn) {

    }

    //Checks intersection between this.stroke and line segment ab
    public boolean intersects(Point a, Point b) {
        double w = a.getX();
        double x = a.getY();
        double y = b.getX();
        double z = b.getY();
        int i = 0;
        if (this.getMode().equals("r") || this.getMode().equals("o")) i = 2;
        if (this.getMode().equals("f")) {
            for (int j = 0; j < this.pointsX.length; j+=2) {
                if (insideStrokeRectangle(this.getPointsX()[j], this.getPointsY()[j], this.getPointsX()[j+1], this.getPointsY()[j+1], a)) {
                    return true;
                }
            }
            return false;
        }
        for ( ; i < this.getPointsX().length - 1; i++) {
            Point c = new Point(this.getPointsX()[i], this.getPointsY()[i]);
            Point d = new Point(this.getPointsX()[i+1], this.getPointsY()[i+1]);
            double s = c.getX();
            double t = c.getY(); 
            double u = d.getX();
            double v = d.getY();
            Point intercept = EquationSolver.eqnSolver(a, b, c, d);
            if (intercept == null) {
                if (this.getPointsX()[i]/Math.sqrt(this.getPointsX()[i]^2+this.getPointsY()[i]^2)==this.getPointsX()[i+1]/Math.sqrt(this.getPointsX()[i+1]^2+this.getPointsY()[i+1]^2)){
                    if ((a.getX()>this.getPointsX()[i]&& a.getX()<this.getPointsX()[i+1])||(a.getX()<this.getPointsX()[i]&& a.getX()>this.getPointsX()[i+1])) 
                    {if ((a.getY()>this.getPointsY()[i]&& a.getY()<this.getPointsY()[i+1])||(a.getY()<this.getPointsY()[i]&& a.getY()>this.getPointsY()[i+1])) return true;}
                }
                else if (this.getPointsY()[i]/Math.sqrt(this.getPointsX()[i]^2+this.getPointsY()[i]^2)==this.getPointsY()[i+1]/Math.sqrt(this.getPointsX()[i+1]^2+this.getPointsY()[i+1]^2)){
                    if ((a.getX()>this.getPointsX()[i]&& a.getX()<this.getPointsX()[i+1])||(a.getX()<this.getPointsX()[i]&& a.getX()>this.getPointsX()[i+1])) 
                    {if ((a.getY()>this.getPointsY()[i]&& a.getY()<this.getPointsY()[i+1])||(a.getY()<this.getPointsY()[i]&& a.getY()>this.getPointsY()[i+1])) return true;}
                }
            }
            else if (w <= y && x <= z) {
                if (intercept.getX() >= w && intercept.getX() <= y && intercept.getY() >= x && intercept.getY() <= z) {
                    if (insideStrokeRectangle(s, t, u, v, intercept)) return true;
                }
            }
            else if (w <= y && z <= x) {
                if (intercept.getX() >= w && intercept.getX() <= y && intercept.getY() >= z && intercept.getY() <= x) {
                    if (insideStrokeRectangle(s, t, u, v, intercept)) return true;
                }
            }
            else if (y <= w && z <= x) {
                if (intercept.getX() >= y && intercept.getX() <=w && intercept.getY() >= z && intercept.getY() <= x) {
                    if (insideStrokeRectangle(s, t, u, v, intercept)) return true;
                }
            }
            else if (y <= w && x <= z) {
                if (intercept.getX() >= y && intercept.getX() <= w && intercept.getY() >= x && intercept.getY() <= z) {
                    if (insideStrokeRectangle(s, t, u, v, intercept)) return true;
                }
            }
        }
        return false;
    }

    //Checks if a point lies within a bounded region
    //Extension of intersection method and used for the Fill Stroke
    public static boolean insideStrokeRectangle(double s, double t, double u, double v, Point intercept) {
        if (s <= u && t <= v) {
            if (intercept.getX() >= s && intercept.getX() <= u && intercept.getY() >= t && intercept.getY() <= v) return true;
        }
        else if (s <= u && v <= t) {
            if (intercept.getX() >= s && intercept.getX() <= u && intercept.getY() >= v && intercept.getY() <= t) return true;
        }
        else if (u <= s && v <= t) {
            if (intercept.getX() >= u && intercept.getX() <= s && intercept.getY() >= v && intercept.getY() <= t) return true;
        }
        else if (u <= s && t <= v) {
            if (intercept.getX() >= u && intercept.getX() <= s && intercept.getY() >= t && intercept.getY() <= v) return true;
        }
        return false;
    }

}
