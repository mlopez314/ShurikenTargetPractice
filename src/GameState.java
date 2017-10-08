import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the state of the entire game so far. Keeps track of the targets, shurikens,
 * countdown, and score.
 */
public class GameState {

  private int countdown;
  private int points;
  private ArrayList<Target> targets;
  private ArrayList<Shuriken> shurikens;
  private Posn initVelocity;
  private int throwCooldown;

  Random rng = new Random();

  /**
   * Constructor for GameState which initializes all uninitialized variables above.
   * 
   * @param targets the list of Targets
   * @param shurikens the list of Shurikens
   * 
   * @throws IllegalArgumentException if any of the variables are null;
   */
  public GameState(ArrayList<Target> targets, ArrayList<Shuriken> shurikens) {
    ExceptionHandler.checkNotNull(targets, "targets is null");
    ExceptionHandler.checkNotNull(shurikens, "shurikens is null");

    this.countdown = 100;
    this.points = 0;
    this.targets = targets;
    this.shurikens = shurikens;
    this.throwCooldown = 0;
    this.initVelocity = new Posn(10, 3);
  }

  /**
   * This constructor gives the targets and shurikens an empty list.
   */
  public GameState() {
    this(new ArrayList<Target>(), new ArrayList<Shuriken>());
  }

  /**
   * Adds a target to the game.
   * 
   * @param t the Target
   * 
   * @throws IllegalArgumentException if the Target is null.
   */
  public void addTarget(Target t) {
    ExceptionHandler.checkNotNull(t, "target is null");
    this.targets.add(t);
  }

  /**
   * Adds a shuriken to the game.
   * 
   * @param s the Shuriken
   * 
   * @throws IllegalArgumentException if the Shuriken is null
   */
  public void addShuriken(Shuriken s) {
    ExceptionHandler.checkNotNull(s, "shuriken is null");
    this.shurikens.add(s);
  }

  /**
   * Draws the entire state of the game.
   * 
   * @param g the Graphics component
   */
  public void draw(Graphics g) {
    for (Target t : targets) {
      t.draw(g);
    }

    for (Shuriken s : shurikens) {
      s.draw(g);
    }

    g.setColor(Color.BLACK);
    g.setFont(new Font("Consolas", 0, 25));
    g.drawString("Initial Velocity:" + this.initVelocity.toString()
    + " Use arrow keys to adjust.", 0, 25);
    g.drawString("Timer: " + Integer.toString(this.countdown), 0, 50);
    g.drawString("Score: " + Integer.toString(this.points), 0, 75);

    if (this.initVelocity.y <= 0) {
      g.setColor(Color.BLUE);
      g.drawString("Min y-value reached. Adjust x-value to reach bottom targets.", 0, 125);
    } 
    
    if (this.initVelocity.x >= 20) {
      g.setColor(Color.RED);
      g.drawString("Max x-value reached. Adjust y-value to reach top targets.", 0, 100);
    }
    
    if (this.countdown == 0) {
      g.setColor(Color.DARK_GRAY);
      g.setFont(new Font("Consolas", 0, 50));
      g.drawString("Final Score: " + this.points, 200, 400);
      g.drawString("Press 'R' to reset" , 200, 450);
    }
  }

  /**
   * Updates the entire game based on the given key inputs and elapsed time.
   * 
   * @param keys list of key inputs
   * @param elapsedTime elapsed time (i.e. how many times actionPerformed has
   *                    been called)
   *                    
   * @throws IllegalArgumentException if keys is null
   */
  public void update(ArrayList<Integer> keys, int elapsedTime) {
    ExceptionHandler.checkNotNull(keys, "keys is null");

    try {
      if (this.countdown != 0) {
        for (int i = 0; i < this.shurikens.size(); i++) {
          Shuriken s = this.shurikens.get(i);
          s.update(keys);

          if (s.isOutOfBounds()) {
            this.shurikens.remove(i);
            i -= 1;
          } else {
            for (int j = 0; j < this.targets.size(); j++) {
              Target t = this.targets.get(j);

              if (t.getCollisionOfCenter().intersectsPoint(s.getSharpPoint())) {
                this.points += 1;
                this.targets.remove(j);
                this.shurikens.remove(i);
                
                Target newTarget = new Target(new Posn(rng.nextInt(775) + 800, rng.nextInt(600)));
                
                while (newTarget.getCollisionOfTarget().intersectsCollisionBox(this.targets.get(0).getCollisionOfTarget())
                    || this.targets.get(0).getCollisionOfTarget().intersectsCollisionBox(newTarget.getCollisionOfTarget())) {
                  System.err.println("New target moved due to overlap.");
                  newTarget = new Target(new Posn(rng.nextInt(775) + 800, rng.nextInt(600)));
                }
                
                this.addTarget(newTarget);
                i -= 1;
              } else if (s.collidesWithTarget(t)) {
                this.shurikens.remove(i);
                i -= 1;
              }
            }
          }
        }

        if (this.throwCooldown == 0) {
          this.addShuriken(new Shuriken(new Posn(0, 400), new Posn(this.initVelocity.x, -this.initVelocity.y)));
          this.throwCooldown = 50;
        } else {
          this.throwCooldown -= 1;
        }

        if (elapsedTime % 50 == 0) {
          this.countdown -= 1;
        }

        if (keys.contains(KeyEvent.VK_RIGHT)) {
          if (this.initVelocity.x < 20) {
            this.initVelocity.x += .1;
          }
        } else if (keys.contains(KeyEvent.VK_LEFT)) {
          if (this.initVelocity.x > 1) {
            this.initVelocity.x -= .1;
          }
        }

        if (keys.contains(KeyEvent.VK_UP)) {
          if (this.initVelocity.y < 20) {
            this.initVelocity.y += .1;
          }
        } else if (keys.contains(KeyEvent.VK_DOWN)) {
          if (this.initVelocity.y > 0) {
            this.initVelocity.y -= .1;
          }
        }
      }

      if (keys.contains(KeyEvent.VK_R)) {
        this.reset();
      }
    } catch (Exception e) {
      System.err.println("Error has occurred midgame. Resetting...");
      e.printStackTrace();
      this.reset();
    }
  }

  /**
   * Resets the game by setting all variables to the same values
   * as the ones in the constructor for GameState.
   */
  public void reset() {
    Target t1 = new Target(new Posn(1500, 500));
    Target t2 = new Target(new Posn(1575, 50));

    this.countdown = 100;
    this.points = 0;
    this.targets = new ArrayList<Target>();
    this.shurikens = new ArrayList<Shuriken>();
    this.throwCooldown = 0;
    this.initVelocity = new Posn(10, 3);

    this.addTarget(t1);
    this.addTarget(t2);
  }
}