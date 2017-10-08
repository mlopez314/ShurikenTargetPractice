/**
 * Represents a basic collision box that only keeps track of the position
 * of the top-left corner and the position of the bottom-right corner.
 */
public class CollisionBox {
  
  public Posn topLeft;
  public Posn bottomRight;
  
  /**
   * Constructor for CollisionBox that initializes the top-left and
   * bottom-right corners.
   * 
   * @param topLeft the position of the top-left corner
   * @param bottomRight the position of the bottom-right corner
   * 
   * @throws IllegalArgumentException if any variables are null
   */
  public CollisionBox(Posn topLeft, Posn bottomRight) {
    ExceptionHandler.checkNotNull(topLeft, "topLeft is null");
    ExceptionHandler.checkNotNull(bottomRight, "bottomRight is null");
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }
  
  /**
   * Checks if the collision box touches or surrounds the given point,
   * 
   * @param pos the position of the given point
   * @return true if the point is inside or touches the collision box
   * 
   * @throws IllegalArgumentException if pos is null
   */
  public boolean intersectsPoint(Posn pos) {
    ExceptionHandler.checkNotNull(pos, "pos is null");
    return pos.x >= topLeft.x && pos.x <= bottomRight.x
        && pos.y >= topLeft.y && pos.y <= bottomRight.y;
  }
  
  /**
   * Checks if this collision box and the given collision box touches or
   * intersects each other.
   * 
   * @param cb the given collision box
   * 
   * @return true if collision boxes intersect each other
   * 
   * @throws IllegalArgumentException if given CollisionBox is null
   */
  public boolean intersectsCollisionBox(CollisionBox cb) {
    ExceptionHandler.checkNotNull(cb, "cb is null");
    
    return this.intersectsPoint(cb.topLeft)
        || this.intersectsPoint(cb.bottomRight)
        || this.intersectsPoint(new Posn(cb.bottomRight.x, cb.topLeft.y))
        || this.intersectsPoint(new Posn(cb.topLeft.x, cb.bottomRight.y));
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CollisionBox) {
      CollisionBox that = (CollisionBox)obj;
      
      return this.topLeft.equals(that.topLeft)
          && this.bottomRight.equals(that.bottomRight);
    } else {
      return false;
    }
  }
}