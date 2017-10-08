package shurikenTargetPractice;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Represent a shuriken that will be thrown towards targets.
 */
public class Shuriken {
  
  private Posn pos;
  private Posn sharpPoint;
  private Posn initVelocity;
  private Posn currVelocity;
  private int currSprite;
  private boolean isMoving;
  private BufferedImage img;
  
  /**
   * Constructor for Shuriken that initializes all above variables.
   * 
   * @param pos the initial position of the shuriken
   * @param initVelocity the initial velocity of the shuriken
   * 
   * @throws IllegalArgumentException if pos or velocity is null
   */
  public Shuriken(Posn pos, Posn initVelocity) {
    ExceptionHandler.checkNotNull(pos, "pos is null");
    ExceptionHandler.checkNotNull(initVelocity, "velocity is null");
    
    this.pos = pos;
    this.sharpPoint = new Posn(this.pos.x + 50, this.pos.y + 25);
    this.initVelocity = initVelocity;
    this.currVelocity = initVelocity;
    this.currSprite = 0;
    this.isMoving = true;
    
    try {
      // Image made by Vipul Malhotra: https://thenounproject.com/term/shuriken/104226/
      // Creative Commons License
      this.img = ImageIO.read(Shuriken.class.getResource("/images/shuriken.png"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not load image of shuriken");
    }
  }
  
  /**
   * Checks if the sharp point of the shuriken collides with the target.
   * 
   * @param t the target
   * @return true if shuriken collides with target
   * 
   * @throws IllegalArgumentException if target is null
   */
  public boolean collidesWithTarget(Target t) {  
    return t.getCollisionOfTarget().intersectsPoint(sharpPoint);
  }
  
  /**
   * Gets the point of the shuriken that will be used to check collision
   * with a target.
   * 
   * @return the sharp point of the shuriken
   */
  public Posn getSharpPoint() {
    return sharpPoint;
  }
  
  /**
   * Draws the current state of the shuriken.
   * 
   * @param g the Graphics component
   */
  public void draw(Graphics g) {
    BufferedImage currImg = img.getSubimage(50 * this.currSprite, 0, 50, 50);    
    g.drawImage(currImg, this.pos.xInt(), this.pos.yInt(), null);
  }
  
  /**
   * Checks if the shuriken is out of bounds (i.e. off-screen excluding the top).
   * 
   * @return true if shuriken is out of bounds
   */
  public boolean isOutOfBounds() {
    return this.pos.x < -10 || this.pos.x > 1550
        || this.pos.y > 725 || this.pos.y < 0;
  }
  
  /**
   * Stops the shuriken from moving.
   */
  public void stopMoving() {
    this.isMoving = false;
  }
  
  /**
   * Getter for this.isMoving
   * 
   * @return isMoving
   */
  public boolean isMoving() {
    return this.isMoving;
  }
  
  /**
   * Updates the shuriken based on key inputs and initial throwing velocity.
   * 
   * @param key the key inputs
   * 
   * @throws IllegalArgumentException if keys is null
   */
  public void update(ArrayList<Integer> keys) {
    ExceptionHandler.checkNotNull(keys, "keys is null");

    if (isMoving) {
      this.currVelocity.y += .03;
      this.pos.shift(this.currVelocity.x, this.currVelocity.y);
        
      if (this.currSprite == 3) {
        this.currSprite = 0;
      } else {
        this.currSprite += 1;
      }
      
      
      this.sharpPoint = new Posn(this.pos.x + 50, this.pos.y + 25);
    }
  }
}