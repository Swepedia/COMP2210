/**
 * HandoutExamples.java
 * Generates the examples in the assignment handout.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2016-08-17
 *
 */
public class HandoutExamples {

   /** Drives execution. */
   public static void main(String[] args) {
      int[] a1 = {2, 8, 7, 3, 4};
      int[] a2 = {5, 9, 1, 7, 3};
      int[] a3 = {8, 7, 6, 5, 4};
      int[] a4 = {2, 8, 8, 7, 3, 3, 4};
   
      // min
      System.out.println(toString(a1) + " min = " + Selector.min(a1));
      System.out.println(toString(a2) + " min = " + Selector.min(a2));
      System.out.println(toString(a3) + " min = " + Selector.min(a3));
      System.out.println(toString(a4) + " min = " + Selector.min(a4));
      System.out.println();
   
      // max
      System.out.println(toString(a1) + " max = " + Selector.max(a1));
      System.out.println(toString(a2) + " max = " + Selector.max(a2));
      System.out.println(toString(a3) + " max = " + Selector.max(a3));
      System.out.println(toString(a4) + " max = " + Selector.max(a4));
      System.out.println();
   
      // kmin
      System.out.println(toString(a1) + " 1 kmin = " + Selector.kmin(a1, 1));
      System.out.println(toString(a2) + " 3 kmin = " + Selector.kmin(a2, 3));
      System.out.println(toString(a3) + " 5 kmin = " + Selector.kmin(a3, 5));
      System.out.println(toString(a4) + " 3 kmin = " + Selector.kmin(a4, 3));
      System.out.println();
   
      // kmax
      System.out.println(toString(a1) + " 1 kmax = " + Selector.kmax(a1, 1));
      System.out.println(toString(a2) + " 3 kmax = " + Selector.kmax(a2, 3));
      System.out.println(toString(a3) + " 5 kmax = " + Selector.kmax(a3, 5));
      System.out.println(toString(a4) + " 3 kmax = " + Selector.kmax(a4, 3));
      System.out.println();
   
      // range
      System.out.println(toString(a1) + " 1,5 range = " + toString(Selector.range(a1, 1, 5)));
      System.out.println(toString(a2) + " 3,5 range = " + toString(Selector.range(a2, 3, 5)));
      System.out.println(toString(a3) + " 4,8 range = " + toString(Selector.range(a3, 4, 8)));
      System.out.println(toString(a4) + " 3,7 range = " + toString(Selector.range(a4, 3, 7)));
      System.out.println();
   
      // ceiling
      System.out.println(toString(a1) + " 1 ceiling = " + Selector.ceiling(a1, 1));
      System.out.println(toString(a2) + " 7 ceiling = " + Selector.ceiling(a2, 7));
      System.out.println(toString(a3) + " 0 ceiling = " + Selector.ceiling(a3, 0));
      System.out.println(toString(a4) + " 5 ceiling = " + Selector.ceiling(a4, 5));
      System.out.println();
   
      // floor
      System.out.println(toString(a1) + " 6 floor = " + Selector.floor(a1, 6));
      System.out.println(toString(a2) + " 1 floor = " + Selector.floor(a2, 1));
      System.out.println(toString(a3) + " 9 floor = " + Selector.floor(a3, 9));
      System.out.println(toString(a4) + " 5 floor = " + Selector.floor(a4, 5));
      System.out.println();
   }

   /**
    * Creates a string representation of an array.
    * @param  a the provided array
    * @return   a string representation of array a
    */
   static String toString(int[] a) {
      StringBuilder s = new StringBuilder();
      s.append("[");
      System.out.println(s);
      for (int i : a) {
         s.append(i + ", ");
      }
      s.delete(s.length() - 2, s.length());
      s.append("]");
      return s.toString();
   }
}
