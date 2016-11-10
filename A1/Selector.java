import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   mch0048@auburn.edu (YOU@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2016-08-18
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      
      if (a.length > 1) {
         for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
               min = a[i];
            }
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      
      if (a.length > 1) {
         for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
               max = a[i];
            }
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) throws IllegalArgumentException {
      if (ktest(a, k)) {
         throw new IllegalArgumentException();
      }
      int[] array = Arrays.copyOf(a, a.length);
      int increment = 1;
      int element;
      
      Arrays.sort(array);
      element = array[0];
      
      for (int i = 0; i < array.length; i++) {
         if (element < array[i]) {
            element = array[i];
            increment ++;
         }
         if (k == increment) {
            return element;
         }
      }
      throw new IllegalArgumentException();
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) throws IllegalArgumentException {
      if (ktest(a, k)) {
         throw new IllegalArgumentException();
      }
      int[] array = Arrays.copyOf(a, a.length);
      int element;
      int increment = 1;
      
      Arrays.sort(array);
      element = array[array.length - 1];
      
      for (int i = array.length - 1; i > -1; i--) {
         if (element > array[i]) {
            element = array[i];
            increment ++;
         }
         if (k == increment) {
            return element;
         }
      }
      throw new IllegalArgumentException();
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int increment = 0;
      
      for (int i : a) {
         if (i >= low && i <= high) {
            increment++;
         }
      }
      
      int[] array = new int[increment];
      increment = 0;
      
      //for(int i = 0; i < a.length; i++)
      for (int i : a) {
         if (i >= low && i <= high) {
            array[increment] = i;
            increment++;
         }
      }
      
      return array;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int candidate = Integer.MAX_VALUE;
      boolean happen = false;
      
      for (int i : a) {
         if (i >= key && i <= candidate) {
            candidate = i;
            happen = true;
         }
      }
      
      if (happen) {
         return candidate;
      }
      throw new IllegalArgumentException();
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int candidate = Integer.MIN_VALUE;
      boolean happen = false;
      
      for (int i : a) {
         if (i <= key && i >= candidate) {
            candidate = i;
            happen = true;
         }
      }
      
      if (happen) {
         return candidate;
      }
      throw new IllegalArgumentException();
   }


   private static boolean ktest(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         return true;
      }
      int[] array = Arrays.copyOf(a, a.length) ;
      Arrays.sort(array);
      int element = Integer.MIN_VALUE;
      int increment = 1;
      for (int i = 0; i < array.length; i++) {
         if (array[i] > element || array[i] == element) {
            increment ++;
            element = array[i];
         }
      }
      if (k > increment) {
         return true;
      }
      return false;
   }
}