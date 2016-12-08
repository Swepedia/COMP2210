import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Maxwell Heeschen (mch0048@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2016-11-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   private int textSize;
   private String first;
   private Random generate;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      textSize = sourceText.length();
      first = sourceText.substring(0, K);
      generate = new Random();
      for(int i = 0; i < sourceText.length() - K; i++) {
         String key = sourceText.substring(i,i + K);
         String vals = model.get(key);
         if(vals == null) {
            vals = "";
         }
         model.put(key, vals.concat(String.valueOf(sourceText.charAt(i + K))));
         if(model.get(key) == null) {
            model.put(key, "");
         }
      }
      char temp = '\u0000';
      model.put(model.get(sourceText.substring(sourceText.length() - K - 1)), String.valueOf(temp));
      if(textSize == K) {
         model.put(sourceText, "");
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      //String set = model.keySet().iterator().next();
      //return set.substring(0, set.length() - 1);
      //return model.keySet().iterator().next();
      return first;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      //String start = "";
      //int keySize = model.keySet().iterator().next().length();
      int keySize = model.keySet().size();
      if(textSize == model.keySet().iterator().next().length()) {
         return model.keySet().iterator().next();
      }
      int loc = generate.nextInt(keySize);// % keySize;
      if(loc < 0) {
         loc *= -1;
      }
      //char[] keys = model.keySet().toArray();
      // for(int i = loc; i < keySize; i++) {
         // start.concat(String.valueOf(model.keySet().toArray()[i]));
      // }
      Iterator<String> iterate = model.keySet().iterator();
      for(int i = loc; i < keySize - 1; i++) {
         iterate.next();
      }
      //start = iterate.next();
      return iterate.next();
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      // int loc = new Random().nextInt() % model.get(kgram).length();
      //System.out.println(model.get(kgram));
      int loc;
      String temp = model.get(getRandomKgram());
      //String perm;
      if(model.get(kgram) == String.valueOf('\u0000')) {
         while(temp == String.valueOf('\u0000')) {
            temp = model.get(getRandomKgram());
         }
         //loc = generate.nextInt(model.get(getRandomKgram()).length());
         //perm = temp;
         loc = generate.nextInt(temp.length());
      }
      else {
         temp = kgram;
         loc = generate.nextInt(model.get(kgram).length());
      }
      if(loc < 0) {
         loc *= -1;
      }
      char value = model.get(temp).charAt(loc);
      return value;
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
