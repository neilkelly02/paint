public class Mode {

    private static boolean isDrawing;
    private static boolean isErasing;
    private static boolean isLine;
    private static boolean isPreFilling;
    private static boolean isFilling;
    private static boolean isRectangle;
    private static boolean isOval;
    private static boolean isSpraying;
    private static boolean isPicking;

    public Mode() {
        isDrawing = true;
        isErasing = false;
        isLine = false;
        isPreFilling = false;
        isFilling = false;
        isRectangle = false;
        isOval = false;
        isSpraying = false;
        isPicking = false;
    }

    public static String getMode() {
        if (isDrawing == true) return "d";
        else if (isErasing == true) return "e";
        else if (isLine == true) return "l";
        else if (isPreFilling == true) return "pf";
        else if (isFilling == true) return "f";
        else if (isRectangle == true) return "r";
        else if (isOval == true) return "o";
        else if (isSpraying == true) return "s";
        else if (isPicking == true) return "p";
        else return "none";
    }

    public static void setDrawMode() {
        turnOffFunctions();
        isDrawing = true;
    }

    public static void setEraseMode() {
    	turnOffFunctions();
    	isErasing = true;
    }

    public static void setLineMode() {
    	turnOffFunctions();
    	isLine = true;
    }

    public static void setPreFillMode() {
        turnOffFunctions();
        isPreFilling = true;
    }
    
    public static void setFillMode() {
    	turnOffFunctions();
    	isFilling = true;
    }
    
    public static void setRectangleMode() {
    	turnOffFunctions();
    	isRectangle = true;
    }

    public static void setOvalMode() {
    	turnOffFunctions();
    	isOval = true;
    }

    public static void setSprayingMode() {
        turnOffFunctions();
        isSpraying = true;
    }

    public static void setPickingMode() {
        turnOffFunctions();
        isPicking = true;
    }

    public static void turnOffFunctions() {
        isDrawing = false;
        isErasing = false;
        isLine = false;
        isPreFilling = false;
        isFilling = false;
        isRectangle = false;
        isOval = false;
        isSpraying = false;
        isPicking = false;
    }

}
