import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class PosnTest {
  
  @Test
  public void testPosnEquals() {
    Posn pos = new Posn(2, 5);
    
    assertTrue(pos.equals(new Posn(2.0, 5.0)));
    assertTrue(new Posn(2, 5).equals(pos));
  }
  
  @Test
  public void testShift() {
    Posn pos = new Posn(2, 5);
    
    pos.shift(new Posn(.01, -1));
    assertEquals(new Posn(2.01, 4), pos);
    pos.shift(-1, 5);
    assertEquals(new Posn(1.01, 9), pos);
  }
  
  @Test
  public void testToString() {
    Posn pos1 = new Posn(2, 5);
    Posn pos2 = new Posn(2.145, 5.001);
    
    assertEquals("(2.0, 5.0)", pos1.toString());
    assertEquals("(2.1, 5.0)", pos2.toString());
  }
}
