import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public final class KeyInputsTest {
  
  private KeyInputs keys;
  
  @Test
  public void testAddKey() {
    initKeys();
    
    keys.addKey(15);
    assertEquals(keys.getKeys(), new ArrayList<Integer>(Arrays.asList(15)));
    keys.addKey(20);
    assertEquals(keys.getKeys(), new ArrayList<Integer>(Arrays.asList(15, 20)));
    keys.addKey(15);
    assertEquals(keys.getKeys(), new ArrayList<Integer>(Arrays.asList(15, 20)));
  }
  
  @Test
  public void testRemoveKey() {
    initKeys();
    
    keys.addKey(15);
    keys.addKey(20);
    assertEquals(keys.getKeys(), new ArrayList<Integer>(Arrays.asList(15, 20)));
    
    keys.removeKey(15);
    assertEquals(keys.getKeys(), new ArrayList<Integer>(Arrays.asList(20)));
    keys.removeKey(20);
    assertEquals(keys.getKeys(), new ArrayList<Integer>());
    
  }
  
  private void initKeys() {
    keys = new KeyInputs();
  }
  
}