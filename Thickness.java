import java.awt.Graphics2D;

public class Thickness {

    private static int globalThickness = 1;

    public static int getThickness() {
        return globalThickness;
    }

    public static void setThickness(int globalThicknessIn) {
        globalThickness = globalThicknessIn;
    }
    
    public static void drawLineThick(Graphics2D paint, Stroke stroke) {
        drawLineThick(paint, stroke.getPointsX(), stroke.getPointsY(), stroke.getThickness());
    }

    public static void drawLineThick(Graphics2D paint, int[] x, int[] y, int thickness) {
        for (int i = 0; i < x.length-1; i++) {
            for (int j = 0; j < thickness; j++) {
                for (int k = 0; k < thickness; k++) {
                    paint.drawLine(x[i] + j, y[i] + k, x[i+1] + j, y[i+1] + k);
                }
            }
        }
    }

    public static void drawRectangleThick(Graphics2D paint, Stroke stroke) {
        drawRectangleThick(paint, stroke.getPointsX(), stroke.getPointsY(), stroke.getThickness());
    }

    public static void drawRectangleThick(Graphics2D paint, int[] x, int[] y, int thickness) {
        int x1 = x[0];
        int x2 = x[1];
        int y1 = y[0];
        int y2 = y[1];
        for (int i = 1; i < thickness; i++) {
            if (x1<x2 && y1<y2) {
                paint.drawRect(x1+i, y1+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i));
                paint.drawRect(x1-i, y1-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i));
            }
            else if (x1<x2 && y1>y2) {
                paint.drawRect(x1+i, y2+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i));
                paint.drawRect(x1-i, y2-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i));
            }
            else if (x1>x2 && y1<y2) {
                paint.drawRect(x2+i, y1+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i));
                paint.drawRect(x2-i, y1-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i));
            }
            else if (x1>x2 && y1>y2) {
                paint.drawRect(x2+i, y2+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i)); 
                paint.drawRect(x2-i, y2-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i)); 
            } 
        }
    }

    public static void drawOvalThick(Graphics2D paint, Stroke stroke) {
        drawOvalThick(paint, stroke.getPointsX(), stroke.getPointsY(), stroke.getThickness());
    }

    public static void drawOvalThick(Graphics2D paint, int[] x, int[] y, int thickness) {
        int x1 = x[0];
        int x2 = x[1];
        int y1 = y[0];
        int y2 = y[1];
        for (int i = 1; i < thickness; i++) {
            if (x1<x2 && y1<y2) {
                paint.drawOval(x1+i, y1+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i));
                paint.drawOval(x1-i, y1-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i));
            }
            else if (x1<x2 && y1>y2) {
                paint.drawOval(x1+i, y2+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i));
                paint.drawOval(x1-i, y2-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i));
            }
            else if (x1>x2 && y1<y2) {
                paint.drawOval(x2+i, y1+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i));
                paint.drawOval(x2-i, y1-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i));
            }
            else if (x1>x2 && y1>y2) {
                paint.drawOval(x2+i, y2+i, Math.abs(x2-x1)-(2*i), Math.abs(y2-y1)-(2*i)); 
                paint.drawOval(x2-i, y2-i, Math.abs(x2-x1)+(2*i), Math.abs(y2-y1)+(2*i)); 
            } 
        }
    }
    
}