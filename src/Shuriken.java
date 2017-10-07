import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Shuriken {
  
  private Posn pos;
  private boolean isMoving;
  private double rotation;
  private Posn velocity;
  private int currSprite;
  
  public Shuriken(Posn pos) {
    this.pos = pos;
    this.rotation = 0;
    this.velocity = new Posn(0, 0);
    this.currSprite = 0;
  }
  
  public void draw(Graphics g) {
    BufferedImage img;
    BufferedImage currImg;
    
    try {
      // Image made by Vipul Malhotra: https://thenounproject.com/term/shuriken/104226/
      // Creative Commons License
      img = ImageIO.read(new File("res//shuriken.png"));
      currImg = img.getSubimage(0, 0, 50, 50);
    } catch (IOException e) {
      throw new IllegalArgumentException("could not load image.");
    }
    
    Graphics2D g2d = (Graphics2D)g;
    if (isMoving) {
      this.pos.shift(this.velocity.x, this.velocity.y);
      this.velocity.y += .03;
      
      if (this.currSprite == 3) {
        this.currSprite = 0;
      } else {
        this.currSprite += 1;
      }
      
      currImg = img.getSubimage(50 * this.currSprite, 0, 50, 50);
    }
    
    g2d.drawImage(currImg, this.pos.xInt(), this.pos.yInt(), null);
  }
  
  public void update(ArrayList<Integer> key) {
    if (key.contains(KeyEvent.VK_SPACE) && !isMoving) {
      isMoving = true;
      this.velocity.shift(4.9, -5);
    }
  }
}