import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameState {
  
  private ArrayList<Target> targets;
  private ArrayList<Shuriken> shurikens;
  
  public GameState(ArrayList<Target> targets, ArrayList<Shuriken> shurikens) {
    ExceptionHandler.checkNotNull(targets, "targets is null");
    ExceptionHandler.checkNotNull(shurikens, "shurikens is null");
    
    this.targets = targets;
    this.shurikens = shurikens;
  }
  
  public GameState() {
    this(new ArrayList<Target>(), new ArrayList<Shuriken>());
  }
  
  public void addTarget(Target t) {
    ExceptionHandler.checkNotNull(t, "target is null");
    this.targets.add(t);
  }
  
  public void addShuriken(Shuriken s) {
    ExceptionHandler.checkNotNull(s, "shuriken is null");
    this.shurikens.add(s);
  }
  
  public void draw(Graphics g) {
    for (Target t : targets) {
      t.draw(g);
    }
    
    for (Shuriken s : shurikens) {
      s.draw(g);
    }
  }
  
  public void update(ArrayList<Integer> keys, boolean addShuriken) {
    for (Shuriken s : shurikens) {
      s.update(keys);
    }
    
    if (keys.contains(KeyEvent.VK_SPACE) && addShuriken) {
      this.addShuriken(new Shuriken(new Posn(0, 700)));
    }
  }
}