import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel implements KeyListener, ActionListener {
  
  private KeyInputs keys;
  private Timer tm;
  
  private Target t1 = new Target(new Posn(1500, 500));
  private Shuriken s1 = new Shuriken(new Posn(0, 700));
  
  private GameState gs = new GameState();
  private int sDelay;
  
  public Canvas() {
    addKeyListener(this);
    this.keys = new KeyInputs();
    this.tm = new Timer(10, this);
    gs.addTarget(t1);
    gs.addShuriken(s1);
    sDelay = 0;
    tm.start();
  }
  
  public void addNotify() {
    super.addNotify();
    requestFocus();
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    gs.draw(g);
  }
  
  @Override
  public void actionPerformed(ActionEvent action) {
    gs.update(keys.getKeys(), sDelay == 0);
    
    if (sDelay <= 0) {
      sDelay = 50;
    } else {
      sDelay -= 1;
    }
    
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent key) {
    this.keys.addKey(key.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent key) {
    this.keys.removeKey(key.getKeyCode());
  }

  @Override
  public void keyTyped(KeyEvent key) {
    // TODO Auto-generated method stub
  }  
}