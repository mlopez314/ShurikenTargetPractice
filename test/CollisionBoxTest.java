import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public final class CollisionBoxTest {
  
  Posn tl = new Posn(200, 100);
  Posn br = new Posn(300, 150);
  CollisionBox cb = new CollisionBox(tl, br);
  
  Posn out = new Posn(400, 125);
  Posn in = new Posn(250, 125);
  Posn edge = new Posn(200, 150);
  
  @Test
  public void testIntersectsPoint() {
    assertTrue(cb.intersectsPoint(in));
    assertTrue(cb.intersectsPoint(edge));
    assertFalse(cb.intersectsPoint(out));
  }
  
  @Test
  public void testIntersectsCollisionBox() {
    Posn tl2 = new Posn(0, 10);
    Posn br2 = new Posn(210, 105);
    CollisionBox cb2 = new CollisionBox(tl2, br2);
    
    Posn tl3 = new Posn(301, 151);
    Posn br3 = new Posn(700, 1000);
    CollisionBox cb3 = new CollisionBox(tl3, br3);
    
    assertTrue(cb.intersectsCollisionBox(cb2));
    assertFalse(cb.intersectsCollisionBox(cb3));
  }
  
  @Test
  public void testEquals() {
    Posn tl2 = new Posn(200, 100);
    Posn br2 = new Posn(300, 150);
    CollisionBox cb2 = new CollisionBox(tl2, br2);
    
    assertTrue(cb.equals(cb2));
    assertTrue(cb2.equals(cb));
    assertEquals(cb2, cb);
  }
}