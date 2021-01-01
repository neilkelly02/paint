import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

public class StartButtonListener implements ActionListener {

    public static int width = 500;
    public static int height = 200;

    @Override
    public void actionPerformed(ActionEvent e) {
        updateSize();
        transparentBackground();
        PaintRunner run = new PaintRunner(width, height);
        run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        run.setSize(PaintRunner.SIZE_X, PaintRunner.SIZE_Y);
        run.setResizable(false);
        run.setLocationRelativeTo(null);
        run.setVisible(true);
        PaintRunner.start.dispose();
    }

    public static void updateSize() {
        int w = (int) Integer.parseInt(PaintRunner.width.getText());
        int h = (int) Integer.parseInt(PaintRunner.height.getText());
        if (w >= width)
            width = w;
        if (h > height)
            height = h;
    }

    public static void transparentBackground() {
        PaintRunner.background.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Its gonna be transparent");
                } else {
                    System.out.println("Its gonna be filled in white");
                }   
            }
        });
    }

}
