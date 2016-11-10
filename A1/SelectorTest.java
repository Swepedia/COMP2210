import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


public class SelectorTest {


   //Edge Cases
   @Test
   public void edge1()
   {
      int[] a = {Integer.MAX_VALUE, 5, 9, 2};
      int expected = 2;
      int actual = Selector.min(a);
      assertEquals(expected, actual);
   }
   
   @Test
   public void edge2()
   {
      int[] a = {Integer.MAX_VALUE, 5, 9, 2};
      int expected = Integer.MAX_VALUE;
      int actual = Selector.max(a);
      assertEquals(expected, actual);
   }
   
   @Test
   public void edge3()
   {
      int[] a = {Integer.MAX_VALUE, 5, 9, 2};
      int k = 2;
      int expected = 5;
      int actual = Selector.kmin(a, k);
      assertEquals(expected, actual);
   }
   
   @Test
   public void edge4()
   {
      int[] a = {Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 9, 2};
      int k = 2;
      int expected = 9;
      int actual = Selector.kmax(a, k);
      assertEquals(expected, actual);
   }
   
   @Test
   public void edge5()
   {
      int[] a = {Integer.MAX_VALUE, Integer.MAX_VALUE, 50, 80, 5, 9, 2};
      int low = 20;
      int high = Integer.MAX_VALUE;
      int[] expected = {Integer.MAX_VALUE, Integer.MAX_VALUE, 50, 80};
      int[] actual = Selector.range(a, low, high);
      System.out.println(Arrays.toString(actual));
      System.out.println(Arrays.toString(expected));
      assertArrayEquals(expected, actual);
   }
}
