import java.io.File;
import java.io.IOException;

/**
 * TextGenerator.java. Creates an order K Markov model of the supplied source
 * text, and then outputs M characters generated according to the model.
 *
 * @author     Maxwell Heeschen (mch0048@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2016-11-17
 *
 */
public class TextGenerator {

   /** Drives execution. */
   public static void main(String[] args) {
   
      if (args.length < 3) {
         System.out.println("Usage: java TextGenerator k length input");
         return;
      }
   
      // No error checking! You may want some, but it's not necessary.
      int K = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      if ((K < 0) || (M < 0)) {
         System.out.println("Error: Both K and M must be non-negative.");
         return;
      }
   
      File text;
      try {
         text = new File(args[2]);
         if (!text.canRead()) {
            throw new Exception();
         }
      }
      catch (Exception e) {
         System.out.println("Error: Could not open " + args[2] + ".");
         return;
      }
   
   
      // instantiate a MarkovModel with the supplied parameters and
      // generate sample output text ...
   
      // try {
         // MarkovModel model = new MarkovModel(K, text);
      // }
      // catch(Exception e) {
         // try {
            // MarkovModel model = new MarkovModel(K, args[2]);
         // }
         // catch(Exception f) {
            // System.out.println("Error: Make sure to use a text file or include a string for the source.");
            // return;
         // }
      // }
      MarkovModel model = new MarkovModel(K, text);
      
      StringBuilder out = new StringBuilder();
      out.append(model.getRandomKgram());
      //System.out.println(out);
      for(int i = 0; i <= M; i++) {
         out.append(model.getNextChar(out.substring(i)));
         //System.out.println(out + "\n\n\n");
         //System.out.println(out.substring(i + 1));
         //System.out.println(model);
         //System.out.println(model.getNextChar(out.substring(i + 1)));
      }
      System.out.println(out);
      return;
   }
}
