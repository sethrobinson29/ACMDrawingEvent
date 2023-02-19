import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

//creates JPanel object that allows user to doodle in a single color and generate shapes
public class SketchPanel extends JPanel {
  
	//list to store points to draw
	private static List<List<Point>> curves = new ArrayList<>(); 

	//LEAVE VARIABLES, GET/SET, CLEARCURVES, EMPTTLIST
	//static variables and methods to establish a global origin
	private static int myX = 0; 
	private static int myY = 0;
	public static Point getOrigin() { return new Point(myX, myY); }
	public static void setOrigin(int x, int y) { myX = x; myY = y; }
	
	private Color ink = Color.BLACK;
	
	public void setInkColor(Color c) {
		this.ink = c;
	}
	
	private void emptyList() {												//private method to clear drawing
		curves.removeAll(curves);
		repaint(0,0,getWidth(), getHeight());
	}
	
	public void clearCurves() {												//public access to clear drawing
		emptyList();
	}

  //paintComponent override for repaint()
  public void paintComponent(Graphics g) {												//Override JComponent function
		super.paintComponent(g);
		g.setColor(ink);
		 //for list of lists
		for (List<Point> curve: curves) {
			Point previousPoint = curve.get(0);
			//for list
			for (Point point: curve) {
				//draw
				//g.fillOval(point.x, point.y, 15, 15);
				g.drawLine(previousPoint.x, previousPoint.y, point.x, point.y);
				previousPoint = point;
			}
		}
	}
	//constructor
	public SketchPanel() {
		addMouseListener(new MouseAdapter() {						//listens for either mouse button to be pressed
			//mouse listener
			public void mousePressed(MouseEvent e) {
				ArrayList<Point> newCurve = new ArrayList<Point>();
				newCurve.add(new Point(e.getX(), e.getY()));
				curves.add(newCurve);
			}
		});
		
		//mouse motion listener
		addMouseMotionListener(new MouseMotionAdapter()  {		//detects mouse movement when either mouse is down
			public void mouseDragged(MouseEvent e) {
				curves.get(curves.size() - 1).add(new Point(e.getX(), e.getY()));
				repaint(0,0,getWidth(), getHeight());
			}
		});
		
	}
	
	public void drawRectangle(double w, double h) {        //draws clockwise from origin
		ArrayList<Point> aCurve = new ArrayList<Point>();
		int x = myX;
		int y = myY;
		
		for (int i = 0; i < 5; i++) {
			aCurve.add(new Point(x,y));
			curves.add(aCurve);
			if (i == 0) {
				y += h;
			} else if (i == 1) {
				x += w;
			} else if (i == 2) {
				y = myY;
			} else if ( i == 3) {
				x = myX;
			}
		}
		repaint(0,0, getWidth(), getHeight());
	}
	
	/*
  *  Takes radius of circle and calculates points
  *    for equilateral triagle that would be inscribed in it
  *
  */
	public void drawEqTriangle(int r) {
		//emptyList();
		ArrayList<Point> aCurve = new ArrayList<Point>();
		int x = myX; int y = myY-r;
		
		for (int i = 0; i < 4; i++) {
			aCurve.add(new Point(x, y));
			curves.add(aCurve);
			if (i == 0) {
				x -= (r/2)*Math.sqrt(3);																													
				y += (.86*r)*Math.sqrt(3);							
			} else if (i == 1) {
				x += r*Math.sqrt(3);
			} else if (i == 2) {
				x = myX; y = myY - r;
			}

			
		}
		repaint(0,0, getWidth(), getHeight());
	}
	/*
	 * radius is measured in pixels
	 * numLines is the number of radii drawn from the origin, resulting in the same number of sides
	 */
	public void drawCirlce(Point p, int r) {
		ArrayList<Point> circlePoints = new ArrayList<Point>();
		int x = (int) p.getX();
		int y = (int) p.getY();
		 //draws cirlce
        int radius = r;
        int numLines = 50;
        for (int i = 0; i < numLines; i++) {
        	int x1 = x + (int) (radius * Math.cos(i*(2*Math.PI/numLines)));
        	int y1 = y + (int) (radius * Math.sin(i*(2*Math.PI/numLines)));
        	int x2 = x + (int) (radius * Math.cos((i+1)*(2*Math.PI/numLines)));
        	int y2 = y + (int) (radius * Math.sin((i+1)*(2*Math.PI/numLines)));
        	circlePoints.add(new Point(x1,y1));
        	circlePoints.add(new Point(x2,y2));
        	curves.add(circlePoints);
        }
        repaint(0,0, getWidth(), getHeight());
	}
	

}

