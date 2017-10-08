import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public final class ExceptionHandlerTest {
  
  @Rule
  public ExpectedException ex = ExpectedException.none();
  
  @Test
  public void testCheckNotNullDoesNotFail() {
    String str = "hello";
    assertTrue(doesNotFail(() -> ExceptionHandler.checkNotNull(str, "str is null.")));
  }
  
  @Test
  public void testCheckNotNullFails() {
    String str = null;
    
    ex.expect(IllegalArgumentException.class);
    ex.expectMessage("str is null.");
    ExceptionHandler.checkNotNull(str, "str is null.");
  }
  
  @Test
  public void testIntBoundsDoesNotFail() {
    assertTrue(doesNotFail(() -> ExceptionHandler.checkIntegerBounds(5, 2, 7, "speed")));
    assertTrue(doesNotFail(() -> ExceptionHandler.checkIntegerBounds(2, 2, 7, "speed")));
    assertTrue(doesNotFail(() -> ExceptionHandler.checkIntegerBounds(7, 2, 7, "speed"))); 
  }
  
  @Test
  public void testIntBoundsFails() {
    ex.expect(IllegalArgumentException.class);
    ex.expectMessage("speed (5) must be in between 1 and 3 inclusive.");
    ExceptionHandler.checkIntegerBounds(5, 1, 3, "speed");
  }
  
  /**
   * Determines if the given function can run without failing.
   * 
   * @param function the function used to test for errors
   * @return true if the function does not fail, false otherwise
   */
  private boolean doesNotFail(Runnable function) {
    try {
      function.run();
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
  
}