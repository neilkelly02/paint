public class EquationSolver {
    
    private static final double EPSILON = 1e-10;

    // Gaussian elimination with partial pivoting
    public static double[] lsolve(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular or line
            if (Math.abs(A[p][p]) <= EPSILON) {
                return null;
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }



// Function to find the line given two points 
public static double[] lineFromPoints(Point P, Point Q) 
{ 
 double a = (P.getY()-Q.getY())/1.0;
 double b = (Q.getX()-P.getX())/1.0; 
    double c = a*(P.getX()) + b*(P.getY()); 
    
    double[] line= new double[3];
    line[0]=a/Math.sqrt(a*a+b*b+c*c);
    line[1]=b/Math.sqrt(a*a+b*b+c*c);
    line[2]=c/Math.sqrt(a*a+b*b+c*c);
    return line;
} 

public static Point eqnSolver(Point A, Point B, Point C, Point D){
    double[][] MatrixA = {
        { lineFromPoints(A,B)[0], lineFromPoints(A,B)[1] },
        { lineFromPoints(C,D)[0], lineFromPoints(C,D)[1] }        };
    double[] Matrixb = { lineFromPoints(A,B)[2], lineFromPoints(C,D)[2] };
    double[] Matrix_x = lsolve(MatrixA, Matrixb);
    if (Matrix_x==null){
        if (lineFromPoints(A,B)[0]==lineFromPoints(C,D)[0]&&lineFromPoints(A,B)[1]==lineFromPoints(C,D)[1]&&lineFromPoints(A,B)[2]==lineFromPoints(C,D)[2]){
            Point Overlap=new Point(-1,-1);
            return Overlap;
        }
        return null;
    }

    Point Intersect= new Point((int)Matrix_x[0],(int)Matrix_x[1]);
    return Intersect;
}

    // sample client
    public static void main(String[] args) {
       // int n = 2;
        Point p1 = new Point( 0,0);
        Point p2 = new Point( 1,0);
        Point p3 = new Point( 0,1);
        Point p4 = new Point( 1,1);
        Point Sol=eqnSolver(p1, p2, p3, p4);
       //lineFromPoints(p3, p4);
        System.out.println(Sol);
        // double[][] A = {
        //     { 1, 1 },
        //     { 1, 2 }        };
        // double[] b = { 7, 11 };
        // double[] x = lsolve(A, b);


        // print results
        // for (int i = 0; i < n; i++) {
        //     System.out.println(x[i]);
        // }

    }

}