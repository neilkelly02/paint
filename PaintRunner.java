import javax.swing.*;

public class PaintRunner extends JFrame {

    private static final long serialVersionUID = 1L;

    static PaintRunner start;
    // Setup JFrame
    static JPanel setup = new JPanel();
    static JLabel label = new JLabel();
    static JLabel label2 = new JLabel();
    static JTextField width = new JTextField();
    static JTextField height = new JTextField();
    static JLabel label3 = new JLabel();
    static JCheckBox background = new JCheckBox();
    static JButton create = new JButton("Create");
    //Paint JFrame
    public static final int BAR_SIZE = 22;
    public static final int C_PANEL_HEIGHT = 100;
    public static int SIZE_X;
    public static int SIZE_Y;
    public static Point CANVAS_LOCATION;
    public static int CANVAS_SIZE_X;
    public static int CANVAS_SIZE_Y;
    JLayeredPane pane;
    static JPanel control;
    static JPanel canvas;

    public PaintRunner() {
        super("Paint Canvas Setup");
        add(setup);
        setup.add(label);
        setup.add(label2);
        setup.add(width);
        setup.add(height);
        setup.add(label3);
        setup.add(background);
        setup.add(create);
        label.setText("Enter the width and height of the canvas.");
        label2.setText("Width must be at least 500 pixels.");
        width.setText("600");
        height.setText("600");
        label3.setText("Trasparent Background");
        create.addActionListener(new StartButtonListener());
    }

    public PaintRunner(int SIZE_XIn, int SIZE_YIn) {
        super("Paint");
        SIZE_X = SIZE_XIn;
        SIZE_Y = SIZE_YIn + BAR_SIZE + C_PANEL_HEIGHT;
        CANVAS_LOCATION = new Point(0, C_PANEL_HEIGHT);
        CANVAS_SIZE_X = SIZE_X - CANVAS_LOCATION.getX();
        CANVAS_SIZE_Y = SIZE_Y - CANVAS_LOCATION.getY() - BAR_SIZE;

        pane = new JLayeredPane();
        add(pane);

        control = new ControlPanel();
        control.setBounds(0, 0, SIZE_X, C_PANEL_HEIGHT);
        pane.add(control);

        canvas = new PaintTools();
        canvas.setBounds(CANVAS_LOCATION.getX(), CANVAS_LOCATION.getY(), CANVAS_SIZE_X, CANVAS_SIZE_Y);
        pane.add(canvas);

        PaintRunner.canvas.addMouseListener(new CanvasListener());
        PaintRunner.canvas.addMouseMotionListener(new CanvasListener());
        PaintRunner.control.addMouseListener(new ControlListener());
        PaintRunner.control.addMouseMotionListener(new ControlListener());
        new Mode();
        new PointManager();
    }

    public static void main(String[] args) {
        start = new PaintRunner();
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setSize(400, 120);
        start.setResizable(false);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
    }

}