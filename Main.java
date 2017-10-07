import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    ExceptionHandler.checkNotNull(args, "args is null");
    
    JFrame frame = new JFrame();
    frame.setSize(1600, 800);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Canvas c = new Canvas();
    frame.add(c);
    frame.setVisible(true);
  }
}