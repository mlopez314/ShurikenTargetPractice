import java.util.ArrayList;

/**
 * This class keeps track of the current key inputs.
 */
public class KeyInputs {
  
  private ArrayList<Integer> currKeys;
  
  public KeyInputs() {
    this.currKeys = new ArrayList<Integer>();
  }
  
  /**
   * Adds a key to the list if the key is not there.
   * 
   * @param key the key code
   */
  public void addKey(int key) {
    if (!this.currKeys.contains(key)) {
      this.currKeys.add(key);
    }
  }
  
  /**
   * Removes a key from the list.
   * 
   * @param key the key code
   */
  public void removeKey(int key) {
    if (this.currKeys.contains(key)) {
      this.currKeys.remove(Integer.valueOf(key));
    }
  }
  
  /**
   * Getter for current key inputs.
   * 
   * @return the current key inputs
   */
  public ArrayList<Integer> getKeys() {
    return currKeys;
  }
}