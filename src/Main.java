import javax.swing.JFrame;

/**
 * This is the class that contains the main method.
 */
public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Shuriken Target Practice");
    frame.setSize(1600, 800);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Canvas c = new Canvas();
    frame.add(c);
    frame.setVisible(true);
  }
}