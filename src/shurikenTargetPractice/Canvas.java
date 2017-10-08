package shurikenTargetPractice;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the canvas that will draw the entire state of the game and keep track
 * of key inputs and timely updates.
 */
public class Canvas extends JPanel implements KeyListener, ActionListener {

  private static final long serialVersionUID = 1L;
  
  
  private KeyInputs keys;
  private Timer tm;
  private int currTick;
  
  private Target t1 = new Target(new Posn(1500, 500));
  private Target t2 = new Target(new Posn(1525, 50));
  
  private GameState gs = new GameState();
  
  private long currTime;
  private long elapsedTime;
  
  /**
   * Constructor for Canvas.
   */
  public Canvas() {
    addKeyListener(this);
    this.keys = new KeyInputs();
    this.tm = new Timer(10, this);
    this.currTick = 0;
    gs.addTarget(t1);
    gs.addTarget(t2);
    tm.start();
    this.currTime = System.nanoTime();
    this.elapsedTime = 0;
  }
  
  @Override
  public void addNotify() {
    super.addNotify();
    requestFocus();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 1600, 800);
    gs.draw(g);
  }
  
  @Override
  public void actionPerformed(ActionEvent action) {
    gs.update(keys.getKeys(), currTick);
    this.currTick += 1;
    repaint();
    this.elapsedTime = System.nanoTime() - this.currTime;
    this.currTime = System.nanoTime();
    
    if (this.elapsedTime / 1000000000.0 > .025 && tm.getDelay() != 1) {
      tm.setDelay(tm.getDelay() - 1);
      System.out.println("New Timer Delay: " + tm.getDelay());
    } else if (this.elapsedTime / 1000000000.0 < .015) {
      tm.setDelay(tm.getDelay() + 1);
      System.out.println("New Timer Delay: " + tm.getDelay());
    }
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