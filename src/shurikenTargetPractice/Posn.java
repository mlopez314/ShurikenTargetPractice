package shurikenTargetPractice;

/**
 * Represents a 2D Position.
 */
public class Posn {
  
  public double x;
  public double y;
  
  /**
   * Constructor for Posn which initializes x and y.
   * 
   * @param x the x-position
   * @param y the y-position
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Shifts the Posn by the given Posn.
   * 
   * @param pos the Posn to shift by.
   */
  public void shift(Posn pos) {
    ExceptionHandler.checkNotNull(pos, "pos is null.");
    
    this.x += pos.x;
    this.y += pos.y;
  }
  
  /**
   * Shifts the Posn by the given x and y coordinates
   * 
   * @param x the x-coordinate to shift by
   * @param y the y-coordinate to shift by
   */
  public void shift(double x, double y) {
    this.shift(new Posn(x, y));
  }
 
  /**
   * Returns the x-position casted to an Integer. This is useful for applying Posns
   * to built-in methods that require Integers.
   * 
   * @return the x-position as an Integer
   */
  public int xInt() {
    return (int)x;
  }
  
  /**
   * Returns the y-position casted to an Integer. This is useful for applying Posns
   * to built-in methods that require Integers.
   * 
   * @return the y-position as an Integer
   */
  public int yInt() {
    return (int)y;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Posn) {
      Posn that = (Posn)obj;
      
      return Math.abs(this.x - that.x) < 0.001
          && Math.abs(this.y - that.y) < 0.001;
    } else {
      return false;
    } 
  }
  
  @Override
  public String toString() {
    return "(" + String.format("%1$,.1f", this.x) + ", " + String.format("%1$,.1f", this.y) + ")";
  }
}