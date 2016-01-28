package com.tests.utils.recorder;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class ScreensRecorder extends JFrame {
	private static final long serialVersionUID = 1L;
	private ImagePanel imagePanel;
	private JScrollPane scrollPane;
	private BufferedImage capture;
	private boolean ctrlDown = false;
    private Point origin;
	public ScreensRecorder(){
		super();
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		captureScreen();
		imagePanel = new ImagePanel(capture);
		scrollPane = new JScrollPane(imagePanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		setContentPane(scrollPane);
		setVisible(true);
		imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                origin = new Point(e.getPoint());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            	if (ctrlDown) {
					try {
	            		int w = Math.abs(e.getX() - origin.x), h =  Math.abs(e.getY() - origin.y);
						int imagesCounter = 0;
						for (String file : new File("resources/").list()) {
							if (file.endsWith(".png"))
								imagesCounter++;
						}
						File imageFile = new File("resources/image"+imagesCounter+".png");
						ImageIO.write(capture.getSubimage((int)origin.getX(), (int)origin.getY(), w, h), "png", imageFile);
						addState(imagesCounter, (int)origin.getX(), (int)origin.getY());
						System.out.println(imagesCounter+" "+ (int)origin.getX()+ ", "+(int)origin.getY());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
            }
        });
		imagePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if ((origin != null)&&(!ctrlDown)) {
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, imagePanel);
                    if (viewPort != null) {
                        int deltaX = origin.x - e.getX();
                        int deltaY = origin.y - e.getY();
                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX;
                        view.y += deltaY;
                        imagePanel.scrollRectToVisible(view);
                    }
                }
            }
        });
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	ctrlDown = (ctrlDown)||(e.getKeyCode() == KeyEvent.VK_CONTROL);
            	if (e.getKeyCode() == KeyEvent.VK_N){

    				try {
						TimeUnit.MILLISECONDS.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
            		updteImagePanel();
            	}
            }
            @Override
            public void keyReleased(KeyEvent e) {
            	if ((ctrlDown)&&(e.getKeyCode() == KeyEvent.VK_CONTROL))
            		ctrlDown = false;
            }
		});
	}
	public void captureScreen() {
		try {
			Robot robot = new Robot();
			Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
			capture = robot.createScreenCapture(new Rectangle(screenDimension));
		} catch (Exception e) { e.printStackTrace(); }
	}
	public void updteImagePanel() {
		captureScreen();
		imagePanel.setBufferedImage(capture);
		imagePanel.update(imagePanel.getGraphics());
	}
	public static void addState(int stateId, int x, int y) {
		try {
			String stateString = "\t\tthis.statesList.put("+stateId+", new ScreenState("+stateId+", "+x+", "+y+"));";
			Path path = new File("src/com/tests/utils/StatesList.java").toPath();
			List<String> lines = Files.readAllLines(path , StandardCharsets.UTF_8);
			lines.add(11, stateString);
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		/*try {
			Robot robot = new Robot();
			ScreenState tmp = new ScreenState(2, 10, 280);
			
			final long startTime = System.currentTimeMillis();
			boolean iseq = tmp.isInState(new Robot());
			final long endTime = System.currentTimeMillis();
			System.out.println("Total execution time: " + (endTime - startTime) + " and: "+iseq );
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		new ScreensRecorder();
	}
}
