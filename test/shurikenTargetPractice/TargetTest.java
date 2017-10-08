package shurikenTargetPractice;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public final class TargetTest {
  
  Target t1 = new Target(new Posn(1500, 500));
  
  @Test
  public void testGetCollisionOfTarget() {
    CollisionBox cb = t1.getCollisionOfTarget();
    
    assertEquals(new Posn(1500, 500), cb.topLeft);
    assertEquals(new Posn(1525, 700), cb.bottomRight);
  }
  
  @Test
  public void testGetCollisionOfCenter() {
    CollisionBox cb = t1.getCollisionOfCenter();
    
    assertEquals(new Posn(1500, 575), cb.topLeft);
    assertEquals(new Posn(1525, 625), cb.bottomRight);
  }
  
  @Test
  public void testEquals() {
    assertTrue(t1.equals(new Target(new Posn(1500, 500))));
    assertTrue(new Target(new Posn(1500, 500)).equals(t1));
    assertFalse(t1.equals(new Target(new Posn(1501, 499))));
  }
}