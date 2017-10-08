import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a 2D target.
 */
public class Target {
  
  public Posn pos;
  
  /**
   * Constructor for Target that initializes the position.
   * 
   * @param pos the initial position
   * 
   * @throws IllegalArgumentException if pos is null
   */
  public Target(Posn pos) {
    ExceptionHandler.checkNotNull(pos, "pos is null");
    this.pos = pos;
  }
  
  /**
   * Get the collision box of the entire target.
   * 
   * @return the collision box of the target
   */
  public CollisionBox getCollisionOfTarget() {
    Posn topLeft = this.pos;
    Posn bottomRight = new Posn(this.pos.x + 30, this.pos.y + 200);
    
    return new CollisionBox(topLeft, bottomRight);
  }
  
  /**
   * Gets the collision box of the center of the target.
   * 
   * @return the collision box of the center of the target
   */
  public CollisionBox getCollisionOfCenter() {
    Posn topLeft = new Posn(this.pos.x, this.pos.y + 75);
    Posn bottomRight = new Posn(this.pos.x + 30, this.pos.y + 125);
    
    return new CollisionBox(topLeft, bottomRight);
  }
  
  /**
   * Draws the target.
   * 
   * @param g the graphics component
   */
  public void draw(Graphics g) {
    Image img;
    
    try {
      img = ImageIO.read(new File("res//target.png"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not load image for target");
    }
    
    g.drawImage(img, this.pos.xInt(), this.pos.yInt(), null);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Target) {
      Target that = (Target)obj;
      return this.pos.equals(that.pos);
    } else {
      return false;
    }
  }
}