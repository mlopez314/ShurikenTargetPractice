package shurikenTargetPractice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class ShurikenTest {
  
  private Posn pos1 = new Posn(0, 400);
  private Posn pos2 = new Posn(0, 900);
  private Posn pos3 = new Posn(20, -100);
  private Posn vel1 = new Posn(10, 3);
  
  private Shuriken s1 = new Shuriken(pos1, vel1);
  private Shuriken s2 = new Shuriken(pos2, vel1);
  private Shuriken s3 = new Shuriken(pos3, vel1);
  
  @Test
  public void testOutOfBounds() {
    assertFalse(s1.isOutOfBounds());
    assertTrue(s2.isOutOfBounds());
    assertTrue(s3.isOutOfBounds());
  }
}