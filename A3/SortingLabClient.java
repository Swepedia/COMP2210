/**
 * SortingLabClient.java
 *
 * Provides a simple example client of SortingLab.java.
 *
 * NOTE: The generic type of SortingLab must be bound
 *       to a Comparable type. The sorting methods in
 *       SortingLab use the natural ordering of the
 *       elements in the parameter array.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @version   2016-09-12
 *
 */
public final class SortingLabClient {

   /** Drives execution. */
   public static void main(String[] args) {
      
      
      // instantiate the SortingLab class
      // using your Banner ID number as the key value
      int key = 903532323;
      SortingLab<String> sls = new SortingLab<String>(key);
      
      // run each sort a few times before trying to
      // collect timing data
      String[] as = {"D", "B", "E", "C", "A"};
      for (int i = 0; i < 10; i++) {
         sls.sort1(as);
         sls.sort2(as);
         sls.sort3(as);
         sls.sort4(as);
         sls.sort5(as);
      }
   
      // generate timing data for one sort method using
      // the "doubling strategy" from lecture and lab
      SortingLab<Integer> sli = new SortingLab<Integer>(key);
      int M = 20000000; // max capacity for array
      int N = 10000;   // initial size of array
      double start;
      double elapsedTime;
      double prevElapsedTime = -1;
      
      // System.out.println("Using already sorted arrays\n\nN\t\tTime\t\t\tR");
      // for(; N < M; N *= 2)
      // {
         // Integer[] sorted = getSortedIntArray(N);
         // start = System.nanoTime();
         // sli.sort1(sorted);
         // elapsedTime = (System.nanoTime() - start) / 1000000000d;
         // System.out.print(N + "\t");
         // System.out.printf("%4.3f\t\t", elapsedTime);
         // System.out.printf("%4.3f", (elapsedTime/prevElapsedTime));
         // System.out.println();
         // prevElapsedTime = elapsedTime;
      // }
   //    
      // prevElapsedTime = -1;
      // N = 10000;
      // System.out.println("\nUsing reverse sorted arrays\n\nN\t\tTime\t\t\tR");
      // for(; N < M; N *= 2)
      // {
         // Integer[] sorted = getReverseArray(N);
         // start = System.nanoTime();
         // sli.sort1(sorted);
         // elapsedTime = (System.nanoTime() - start) / 1000000000d;
         // System.out.print(N + "\t");
         // System.out.printf("%4.3f\t\t", elapsedTime);
         // System.out.printf("%4.3f", (elapsedTime/prevElapsedTime));
         // System.out.println();
         // prevElapsedTime = elapsedTime;
      // }
   //    
      // prevElapsedTime = -1;
      // N = 10000;
      // System.out.println("\nAverage cases\n\nN\t\tTime\t\t\tR");
      // for (; N < M; N *= 2) {
         // Integer[] ai = getIntegerArray(N, Integer.MAX_VALUE);
         // start = System.nanoTime();
         // sli.sort1(ai);
         // elapsedTime = (System.nanoTime() - start) / 1000000000d;
         // System.out.print(N + "\t");
         // System.out.printf("%4.3f\t\t", elapsedTime);
         // System.out.printf("%4.3f", (elapsedTime/prevElapsedTime));
         // System.out.println();
         // prevElapsedTime = elapsedTime;
      // }
      
      System.out.println("Going to try using my own class to test for stability");
      SortingLab<Test> tsi = new SortingLab<Test>(key);
      Test[] stable = {new Test(1, 4), new Test(1, 1), new Test(2, 7), new Test(2, 2)};
      tsi.sort4(stable);
   }

   /** return an array of random integer values. */
   private static Integer[] getIntegerArray(int N, int max) {
      Integer[] a = new Integer[N];
      java.util.Random rng = new java.util.Random();
      for (int i = 0; i < N; i++) {
         a[i] = rng.nextInt(max);
      }
      return a;
   }
   
   //returns a sorted array for getting time complexity of best and worst cases
   private static Integer[] getSortedIntArray(int size)
   {
      Integer[] sorted = new Integer[size];
      
      for(int i = 0; i < size; i++)
      {
         sorted[i] = i;
      }
      return sorted;
   }
   
   //returns a reverse sorted array
   private static Integer[] getReverseArray(int size)
   {
      Integer[] sorted = new Integer[size];
      
      for(int i = size; i > 0; i--)
      {
         sorted[i - 1] = i;
      }
      return sorted;
   }

}