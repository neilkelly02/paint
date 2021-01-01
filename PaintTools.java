import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class PaintTools extends JPanel {

	private static final long serialVersionUID = 1L;

	public PaintTools() {
		super();
	}

	public void paintComponent(Graphics g) {
		Graphics2D paint = (Graphics2D) g;
		if (CanvasListener.isDrawing()) {
			paintLib(paint);
			if (Mode.getMode().equals("d")) draw(paint);
			else if (Mode.getMode().equals("e")) erase();
			else if (Mode.getMode().equals("l")) line(paint);
			else if (Mode.getMode().equals("f")) fill(paint);
			else if (Mode.getMode().equals("r")) rectangle(paint);
			else if (Mode.getMode().equals("o")) oval(paint);
			else if (Mode.getMode().equals("s")) spray(paint);
			else if (Mode.getMode().equals("p")) pick();
		}
		else paintLib(paint);
		if (Mode.getMode().equals("f")) paintLib(paint);
	}

	public void paintLib(Graphics2D paint) {
		for (Stroke s : PointManager.getLibrary()) {
			if (s.getMode().equals("d") || s.getMode().equals("l")) {
				paint.setColor(s.getColor());
				paint.drawPolyline(s.getPointsX(), s.getPointsY(), s.getPointsX().length);
				if (s.getThickness() > 1) {
					Thickness.drawLineThick(paint, s);
				}
				
			}
			if (s.getMode().equals("f")) {
				paint.setColor(s.getColor());
				for (int i = 0; i < s.getPointsX().length; i+=2) {
					paint.fillRect(s.getPointsX()[i], s.getPointsY()[i], s.getPointsX()[i+1]-s.getPointsX()[i]+1, s.getPointsY()[i+1]-s.getPointsY()[i]+1);
				}
			}
			if (s.getMode().equals("r")) {
				paint.setColor(s.getColor());
				int x1 = s.getPointsX()[0];
				int y1 = s.getPointsY()[0];
				int x2 = s.getPointsX()[1];
				int y2 = s.getPointsY()[1];
				if (x1 < x2 && y1 < y2) paint.drawRect(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
				else if (x1 < x2 && y1 > y2) paint.drawRect(x1, y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
				else if (x1 > x2 && y1 < y2) paint.drawRect(x2, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
				else if (x1 > x2 && y1 > y2) paint.drawRect(x2, y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
				if (s.getThickness() > 1) {
					Thickness.drawRectangleThick(paint, s);
				}
			}
			if (s.getMode().equals("o")) {
				paint.setColor(s.getColor());
				int x1 = s.getPointsX()[0];
				int y1 = s.getPointsY()[0];
				int x2 = s.getPointsX()[1];
				int y2 = s.getPointsY()[1];
				if (x1 < x2 && y1 < y2) paint.drawOval(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
				else if (x1 < x2 && y1 > y2) paint.drawOval(x1, y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
				else if (x1 > x2 && y1 < y2) paint.drawOval(x2, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
				else if (x1 > x2 && y1 > y2) paint.drawOval(x2, y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
				if (s.getThickness() > 1) {
					Thickness.drawOvalThick(paint, s);
				}
			}
			if (s.getMode().equals("s")) {
				paint.setColor(s.getColor());
				for (int i = 0; i < s.getPointsX().length; i++) {
					paint.fillRect(s.getPointsX()[i], s.getPointsY()[i], 1, 1);
				}
			}
		}
	}

	public void draw(Graphics2D paint) {
		paint.setColor(PointManager.getColor());
		int size = PointManager.getPoints().size();
		int[] xPoints = new int[size];
		int[] yPoints = new int[size];
		for (int i = 0; i < size; i++) {
			xPoints[i] = PointManager.getPoints().get(i).getX();
			yPoints[i] = PointManager.getPoints().get(i).getY();
		}
		paint.drawPolyline(xPoints, yPoints, size);
		if (Thickness.getThickness() > 1) {
			Thickness.drawLineThick(paint, xPoints, yPoints, Thickness.getThickness());
		}
	}

	public void erase() {
		for (int i = PointManager.getLibrary().size() - 1; i >= 0; i--) {
			Stroke s = PointManager.getLibrary().get(i);
			if (s.intersects(PointManager.getPoints().get(0), PointManager.getPoints().get(1))) {
				PointManager.addToUndo(i);
			}
		}
	}

	public void line(Graphics2D paint) {
		paint.setColor(PointManager.getColor());
		paint.drawLine(PointManager.getPoints().get(0).getX(), PointManager.getPoints().get(0).getY(), PointManager.getPoints().get(1).getX(), PointManager.getPoints().get(1).getY());
		if (Thickness.getThickness() > 1) {
			int[] tempX = {PointManager.getPoints().get(0).getX(), PointManager.getPoints().get(1).getX()};
			int[] tempY = {PointManager.getPoints().get(0).getY(), PointManager.getPoints().get(1).getY()};
			Thickness.drawLineThick(paint, tempX, tempY, Thickness.getThickness());
		}
	}

	public void fill(Graphics2D paint) {
		Mode.turnOffFunctions();
		BufferedImage tempSave = new BufferedImage(PaintRunner.canvas.getWidth(), PaintRunner.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics tempG = tempSave.getGraphics();
		PaintRunner.canvas.paint(tempG);
		Mode.setFillMode();
		PointManager.addToLib(new Filler(tempSave).fill(PointManager.getPoints().get(0).getX(), PointManager.getPoints().get(0).getY()));
	}

	public void rectangle(Graphics2D paint) {
		paint.setColor(PointManager.getColor());
        int x1 = PointManager.getPoints().get(0).getX();
		int y1 = PointManager.getPoints().get(0).getY();
		int x2 = PointManager.getPoints().get(1).getX();
		int y2 = PointManager.getPoints().get(1).getY();
		if (x1<x2 && y1<y2) paint.drawRect(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
		else if (x1<x2 && y1>y2) paint.drawRect(x1, y2, Math.abs(x2-x1), Math.abs(y2-y1));
		else if (x1>x2 && y1<y2) paint.drawRect(x2, y1, Math.abs(x2-x1), Math.abs(y2-y1));
		else if (x1>x2 && y1>y2) paint.drawRect(x2, y2, Math.abs(x2-x1), Math.abs(y2-y1));
		if (Thickness.getThickness() > 1) {
			int[] tempX = {x1, x2};
			int[] tempY = {y1, y2};
			Thickness.drawRectangleThick(paint, tempX, tempY, Thickness.getThickness());
		}  
	}

	public void oval(Graphics2D paint) {
		paint.setColor(PointManager.getColor());
		int x1 = PointManager.getPoints().get(0).getX();
		int y1 = PointManager.getPoints().get(0).getY();
		int x2 = PointManager.getPoints().get(1).getX();
		int y2 = PointManager.getPoints().get(1).getY();
		if (x1<x2 && y1<y2) paint.drawOval(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1));
     	else if (x1<x2 && y1>y2) paint.drawOval(x1, y2, Math.abs(x2-x1), Math.abs(y2-y1));
     	else if (x1>x2 && y1<y2) paint.drawOval(x2, y1, Math.abs(x2-x1), Math.abs(y2-y1));
		else if (x1>x2 && y1>y2) paint.drawOval(x2, y2, Math.abs(x2-x1), Math.abs(y2-y1));
		if (Thickness.getThickness() > 1) {
			int[] tempX = {x1, x2};
			int[] tempY = {y1, y2};
			Thickness.drawOvalThick(paint, tempX, tempY, Thickness.getThickness());
		}  
	}

	public void spray(Graphics2D paint) {
		paint.setColor(PointManager.getColor());
		Sprayer.spray(PointManager.getPoints().get(0).getX(), PointManager.getPoints().get(0).getY());
		for (int i = 0; i < Sprayer.spraySet.size(); i++) {
			paint.fillRect(Sprayer.spraySet.get(i).getX(), Sprayer.spraySet.get(i).getY(), 1, 1);
		}
	}

	public static boolean foundColor = false;
	public void pick() {
		for (int i = PointManager.getLibrary().size() - 1; i >= 0; i--) {
			if (PointManager.getLibrary().get(i).intersects(PointManager.getPoints().get(0), PointManager.getPoints().get(1))) {
				if (foundColor == false) {
					ControlPanel.shiftColorPalet(PointManager.getLibrary().get(i).getColor());
					foundColor = true;
				}
				else ControlPanel.colorPalet[0] = PointManager.getLibrary().get(i).getColor();
				ControlPanel.shiftSelectedColor(200, 50);
                PaintRunner.control.repaint();
                PointManager.setColor(ControlPanel.getColorPalet(0));
			}
		}
	}

	public void caligraphy(Graphics2D paint) {
		for (Point p : PointManager.getPoints()) {
			paint.drawLine(p.getX(), p.getY(), p.getX()+5, p.getY()+5);
		}
	}

	public static void saveImage() throws IOException {
        BufferedImage save = new BufferedImage(PaintRunner.canvas.getWidth(), PaintRunner.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = save.getGraphics();
		PaintRunner.canvas.paint(g);

		//Date currentDate = new Date();
		//String saveName = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(currentDate);

        try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File output = fc.getSelectedFile();
				String path = output.getPath() + ".png";
				ImageIO.write(save, "png", new File(path));
			}
        }
        catch (Exception e) {

        }

	}

}
