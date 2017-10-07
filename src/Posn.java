public class Posn {
  
  public double x;
  public double y;
  
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public void shift(Posn pos) {
    ExceptionHandler.checkNotNull(pos, "pos is null.");
    
    this.x += pos.x;
    this.y += pos.y;
  }
  
  public void shift(double x, double y) {
    this.shift(new Posn(x, y));
  }
 
  public int xInt() {
    return (int)x;
  }
  
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
    return "(" + this.x + ", " + this.y + ")";
  }
}