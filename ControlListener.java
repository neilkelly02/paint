import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class ControlListener extends MouseInputAdapter {

    public static MiscelaneousFunctions miscPanel = new MiscelaneousFunctions();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() - ControlPanel.panelShift;
        int y = e.getY();
        if (y < PaintRunner.C_PANEL_HEIGHT / 2) {
            if (x > 0 && x < 50) {
                ControlPanel.shiftSelectedTool(0, 0);
                PaintRunner.control.repaint();
                Mode.setDrawMode();
            } 
            else if (x > 50 && x < 100) {
                ControlPanel.shiftSelectedTool(50, 0);
                PaintRunner.control.repaint();
                Mode.setLineMode();
            } 
            else if (x > 100 && x < 150) {
                ControlPanel.shiftSelectedTool(100, 0);
                PaintRunner.control.repaint();
                Mode.setRectangleMode();
            } 
            else if (x > 150 && x < 200) {
                ControlPanel.shiftSelectedTool(150, 0);
                PaintRunner.control.repaint();
                Mode.setSprayingMode();
            } 
            else if (x > 200 && x < 250) {
                ControlPanel.shiftSelectedColor(200, 0);
                PaintRunner.control.repaint();
                JFrame colorWheel = new JFrame("Color Selector");
                colorWheel.addFocusListener(new FocusListener() {
                    private boolean gained = false;

                    public void focusGained(FocusEvent e) {
                        gained = true;
                    }

                    public void focusLost(FocusEvent e) {
                        if (gained) {
                            colorWheel.dispose();
                        }
                    }
                } );
                colorWheel.setSize(256, 256);
                colorWheel.setResizable(false);
                colorWheel.setLocationRelativeTo(null);
                colorWheel.setVisible(true);
                colorWheel.add(new ColorWheel());
                colorWheel.addMouseListener(new ColorListener());
                colorWheel.addMouseMotionListener(new ColorListener());
            } 
            else if (x > 250 && x < 300) {
                ControlPanel.shiftSelectedTool(250, 0);
                PaintRunner.control.repaint();
                Mode.setPickingMode();
            } 
            else if (x > 300 && x < 350) {
                ControlPanel.shiftSelectedColor(300, 0);
                PaintRunner.control.repaint();
                PointManager.setColor(Color.BLACK);
                PaintRunner.canvas.repaint();
            } 
            else if (x > 350 && x < 400) {
                ControlPanel.shiftSelectedColor(350, 0);
                PaintRunner.control.repaint();
                PointManager.setColor(Color.WHITE);
                PaintRunner.canvas.repaint();
            } 
            else if (x > 400 && x < 450) {
                ControlPanel.shiftSelectedIndicator(400, 0);
                ControlPanel.indicatorVisible = true;
                PaintRunner.control.repaint();
                PointManager.addToUndo();
                PaintRunner.canvas.repaint();
            } 
            else if (x > 450 && x < 500) {
                ControlPanel.shiftSelectedIndicator(450, 0);
                ControlPanel.indicatorVisible = true;
                PaintRunner.control.repaint();
                try {
                    PaintTools.saveImage();
                } catch (IOException ioException) {

                }
            }
        }
        else if (y > PaintRunner.C_PANEL_HEIGHT / 2) {
            if (x > 0 && x < 50) {
                ControlPanel.shiftSelectedTool(0, 50);
                PaintRunner.control.repaint();
                Mode.setEraseMode();
            } 
            else if (x > 50 && x < 100) {
                ControlPanel.shiftSelectedTool(50, 50);
                PaintRunner.control.repaint();
                Mode.setPreFillMode();
            }
            else if (x > 100 && x < 150) {
                ControlPanel.shiftSelectedTool(100, 50);
                PaintRunner.control.repaint();
                Mode.setOvalMode();
            }
            else if (x > 150 && x < 200) {
                JFrame miscFunctions = new JFrame("Miscellaneous Functions");
                miscFunctions.addFocusListener(new FocusListener() {
                    private boolean gained = false;

                    public void focusGained(FocusEvent e) {
                        gained = true;
                    }

                    public void focusLost(FocusEvent e) {
                        if (gained) {
                            miscFunctions.dispose();
                        }
                    }
                } );
                miscFunctions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                miscFunctions.setSize(300, 300 + PaintRunner.BAR_SIZE);
                miscFunctions.setResizable(false);
                miscFunctions.setLocationRelativeTo(null);
                miscFunctions.setVisible(true);
                miscFunctions.add(miscPanel);
            }
            else if (x > 200 && x < 250) {
                ControlPanel.shiftSelectedColor(200, 50);
                PaintRunner.control.repaint();
                PointManager.setColor(ControlPanel.getColorPalet(0));
                PaintRunner.canvas.repaint();
            }
            else if (x > 250 && x < 300) {
                ControlPanel.shiftSelectedColor(250, 50);
                PaintRunner.control.repaint();
                PointManager.setColor(ControlPanel.getColorPalet(1));
                PaintRunner.canvas.repaint();
            }
            else if (x > 300 && x < 350) {
                ControlPanel.shiftSelectedColor(300, 50);
                PaintRunner.control.repaint();
                PointManager.setColor(ControlPanel.getColorPalet(2));
                PaintRunner.canvas.repaint();
            }
            else if (x > 350 && x < 400) {
                ControlPanel.shiftSelectedColor(350, 50);
                PaintRunner.control.repaint();
                PointManager.setColor(ControlPanel.getColorPalet(3));
                PaintRunner.canvas.repaint();
            }
            else if (x > 400 && x < 450) {
                ControlPanel.shiftSelectedIndicator(400, 50);
                ControlPanel.indicatorVisible = true;
                PaintRunner.control.repaint();
                PointManager.redo();
                PaintRunner.canvas.repaint();
            }
            else if (x > 450 && x < 500) {
                ControlPanel.shiftSelectedIndicator(450, 50);
                ControlPanel.indicatorVisible = true;
                PaintRunner.control.repaint();
                PointManager.trash();
                PaintRunner.canvas.repaint();
            }
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
        if (ControlPanel.indicatorVisible) {
            ControlPanel.indicatorVisible = false;
            PaintRunner.control.repaint();
        }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        
	}
	
}
