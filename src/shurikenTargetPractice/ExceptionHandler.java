package shurikenTargetPractice;

/**
 * This class uses static methods to handle exceptions on Objects and primitives.
 */
public class ExceptionHandler {
  
  /**
   * Ensures that the given Object is not null by throwing an exception if so.
   * 
   * @param o the Object
   * @param message the error message
   * 
   * @throws IllegalArgumentException if o is null
   */
  public static void checkNotNull(Object o, String message) {
    if (o == null) {
      throw new IllegalArgumentException(message);
    }
  }
  
  /**
   * Ensures that the given Integer is within the bounds of the given min and max Integer.
   * 
   * @param num the Integer
   * @param min the minimum
   * @param max the maximum
   * @param varName the variable name
   * 
   * @throws IllegalArgumentException if the given Integer is out of bounds
   */
  public static void checkIntegerBounds(int num, int min, int max, String varName) {
    if (num < min || num > max) {
      throw new IllegalArgumentException(varName + " (" + num + ") must be in between "
          + min + " and " + max + " inclusive.");
    }
  }
  
}