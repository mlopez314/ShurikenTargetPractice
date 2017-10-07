import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Target {
  
  public Posn pos;
  
  public Target(Posn pos) {
    this.pos = pos;
  }
  
  
  
  public void draw(Graphics g) {
    Image img;
    
    try {
      img = ImageIO.read(new File("res//target.png"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not load image for target");
    }
    
    g.drawImage(img, this.pos.xInt(), this.pos.yInt(), null);
  }
}