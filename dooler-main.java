import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//driver class for SketchPanel
public class Main extends JPanel {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {

       //Create and set up frame
      JFrame frame = new JFrame("Sketch Panel Deluxe"); // main window
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 300);

      //create and set up panel
      SketchPanel drawPanel = new SketchPanel(); // drawing window
      frame.getContentPane().add(drawPanel, BorderLayout.CENTER, 0);
      SketchPanel.setOrigin(250, 150);
      drawPanel.setBackground(Color.LIGHT_GRAY);
      
	  //create menu bar
      JMenuBar menuBar = new JMenuBar();
      menuBar.setForeground(Color.BLACK);
      menuBar.setBackground(Color.LIGHT_GRAY);
      frame.setJMenuBar(menuBar);
      
	  //create menu
      JMenu mnFile = new JMenu("File"); // file menu
      mnFile.setBackground(Color.PINK);
      menuBar.add(mnFile);
      
	  //create clear button
      JMenuItem clearDoodle = new JMenuItem("Clear Doodle");
      clearDoodle.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          drawPanel.clearCurves();
        }
      });
      mnFile.add(clearDoodle);
    
      JMenuItem changeOrigin = new JMenuItem("Change Origin");
      changeOrigin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter x-coordinate: "));
          int y = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter y-coordinate: "));
          SketchPanel.setOrigin(x, y);
        }
      });
      mnFile.add(changeOrigin);

      JMenu drawMenu = new JMenu("Auto-Draw"); // Auto-Draw Menu
      menuBar.add(drawMenu);

      
      JMenuItem rectangle = new JMenuItem("rectangle");
      rectangle.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int w = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width of rectangle: "));
          int h = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height of rectangle: "));
          drawPanel.drawRectangle(w, h);
        }
      });
      drawMenu.add(rectangle);

      JMenuItem circle = new JMenuItem("Circle");
      circle.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int cRadius;

          cRadius = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter cirlce radius: :"));

          drawPanel.drawCirlce(SketchPanel.getOrigin(), cRadius);
          System.out.println(cRadius);
        }

      });
      drawMenu.add(circle);

      JMenuItem eqTriangle = new JMenuItem("eq. triangle");
      eqTriangle.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int radius = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter radius of circumscribing circle: "));
          drawPanel.drawEqTriangle(radius);
        }
      });
      drawMenu.add(eqTriangle);

      
      JMenu color = new JMenu("Change Color"); // color menu
      menuBar.add(color);

      JMenuItem black = new JMenuItem("black");
      black.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          drawPanel.setInkColor(Color.black);
        }
      });
      color.add(black);

      JMenuItem green = new JMenuItem("green");
      green.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          drawPanel.setInkColor(Color.green);
        }
      });
      color.add(green);

      JMenuItem magenta = new JMenuItem("magenta");
      magenta.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          drawPanel.setInkColor(Color.magenta);
        }
      });
      color.add(magenta);

      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}