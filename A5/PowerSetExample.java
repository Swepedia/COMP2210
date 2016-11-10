import java.util.Iterator;

/**
 * PowerSetExample.java
 * Illustrates how the LinkedSet iterator can be used
 * to generate elements of a set's power set one by
 * one.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @version   2016-06-15
 *
 */
public class PowerSetExample {

   /** Drives execution. */
   public static void main(String[] args) {
      // create the set s = {0,1,2,3,4}
      LinkedSet<Integer> s = new LinkedSet<Integer>();
      for (int i = 0; i < 5; i++) {
         s.add(i);
      }

      // use the powerSetIterator to print out all
      // 32 subsets of s
      int i = 0;
      Iterator<Set<Integer>> ps = s.powerSetIterator();
      while (ps.hasNext()) {
         System.out.print("Subset " + (i++) + " = ");
         System.out.println(ps.next());
      }
   }
}

/*

      R U N T I M E   O U T P U T

Subset 0 = []
Subset 1 = [4]
Subset 2 = [4]
Subset 3 = [3, 4]
Subset 4 = [4]
Subset 5 = [3, 4]
Subset 6 = [3, 4]
Subset 7 = [2, 3, 4]
Subset 8 = [4]
Subset 9 = [3, 4]
Subset 10 = [3, 4]
Subset 11 = [2, 3, 4]
Subset 12 = [3, 4]
Subset 13 = [2, 3, 4]
Subset 14 = [2, 3, 4]
Subset 15 = [1, 2, 3, 4]
Subset 16 = [4]
Subset 17 = [3, 4]
Subset 18 = [3, 4]
Subset 19 = [2, 3, 4]
Subset 20 = [3, 4]
Subset 21 = [2, 3, 4]
Subset 22 = [2, 3, 4]
Subset 23 = [1, 2, 3, 4]
Subset 24 = [3, 4]
Subset 25 = [2, 3, 4]
Subset 26 = [2, 3, 4]
Subset 27 = [1, 2, 3, 4]
Subset 28 = [2, 3, 4]
Subset 29 = [1, 2, 3, 4]
Subset 30 = [1, 2, 3, 4]
Subset 31 = [0, 1, 2, 3, 4]



 */
