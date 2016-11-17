import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-11-02
 */
public class Doublets implements WordLadderGame {

   ////////////////////////////////////////////
   // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
   ////////////////////////////////////////////

   // A word ladder with no words. Used as the return value for the ladder methods
   // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            ////////////////////////////////////////////////
            // Add code here to store str in the lexicon. //
            ////////////////////////////////////////////////
            lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

   ///////////////////////////////////////////////////////////////////////////////
   // Fill in implementations of all the WordLadderGame interface methods here. //
   ///////////////////////////////////////////////////////////////////////////////

   public int getHammingDistance(String str1, String str2) {
      if(str1 == null || str2 == null) {
         return -1;
      }
      if(str1.length() != str2.length()) {
         return -1;
      }
      int dist = 0;
      for(int i = 0; i < str1.length(); i++) {
         if(str1.charAt(i) != str2.charAt(i)) {
            dist++;
         }
      }
      return dist;
   }

   public List<String> getLadder(String start, String end) {
      Stack<String> path = new Stack<>();
      TreeSet<String> visited = new TreeSet<>();
      List<String> op = new ArrayList<>();
      
      if(start == null || end == null) {
         return op;
      }
      // if(start.equals(end)) {
         // visited.add(start);
         // return visited;
      // }
      
      path.push(start);
      List<String> neighbors;
      String current = start;
      visited.add(start);
      while(!path.empty() && !path.peek().equals(end)) {
         neighbors = getNeighbors(current);
         if(hasNeighbors(visited, neighbors)) {
            for(int i = 0; i < neighbors.size(); i++) {
               if(!visited.contains(neighbors.get(i))) {
                  visited.add(neighbors.get(i));
                  path.push(neighbors.get(i));
                  break;
               }
            }
            current = path.peek();
         }
         else {
            path.pop();
            if(!path.empty()) {
               current = path.peek();
            }
         }
         
      }
      while(!path.empty()) {
         op.add(0, path.pop());
      }
      return op;
   }
   
   private boolean hasNeighbors(TreeSet<String> visited, List<String> neighbors) {
      for(int i = 0; i < neighbors.size(); i++) {
         if(!visited.contains(neighbors.get(i))) {
            return true;
         }
      }
      return false;
   }

/*
/
/Fix this to find the shortest path
/
*/
   public List<String> getMinLadder(String start, String end) {
      Deque<String> path = new ArrayDeque<>();
      Stack<String> minPath = new Stack<>();
      TreeSet<String> visited = new TreeSet<>();
      List<String> op = new ArrayList<>();
      int distance = 0;
      
      if(start == null) {
         return op;
      }
      
      path.addLast(start);
      visited.add(start);
      List<String> neighbors;
      String current;
      boolean br = false;
      
      while(!path.contains(end) && !path.isEmpty() && !br) {
         current = path.removeFirst();
         for(String c : getNeighbors(current)) {
            if(getNeighbors(current).contains(end)) {
               path.addLast(c);
               distance++;
               br = true;
               break;
            }
            if(!visited.contains(c)) {
               visited.add(c);
               path.addLast(c);
               //current = path.peek();
            }
         }
         distance++;
      }
      while(!path.isEmpty()) {
         op.add(path.removeFirst());
      }
      
      minPath.push(start);
      current = op.get(0);
      visited.clear();
      visited.add(start);
      boolean done = false;
      while(!minPath.peek().equals(end) && !minPath.empty() && !done) {
         neighbors = getNeighbors(current);
         if(hasNeighbors(visited, neighbors)) {
            for(String s : neighbors) {
               if(!visited.contains(s)) {
                  visited.add(s);
                  minPath.push(s);
               }
            }
            current = minPath.peek();
         }
         else {
            minPath.pop();
            current = minPath.peek();
         }
      }
      
      return op;
   }

   public List<String> getNeighbors(String word) {
      List<String> neighbors = new ArrayList<>();
      if(!isWord(word)) {
         return neighbors;
      }
      char[] a0 = word.toCharArray();
      String cp;
      for(int i = 0; i < a0.length; i++) {
         for(char j = 97; j <= 122; j++) {
            a0[i] = j;
            cp = new String(a0);
            if(!cp.equals(word) && isWord(cp)) {
               neighbors.add(cp);
            }
            a0 = word.toCharArray();
         }
      }
      return neighbors;
   }

   public int getWordCount() {
      return lexicon.size();
   }

   public boolean isWord(String str) {
      if(lexicon.contains(str.toUpperCase())) {
         return true;
      }
      return false;
   }

   public boolean isWordLadder(List<String> sequence) {
      if(sequence == null) {
         return false;
      }
      if(sequence.size() < 1) {
         return false;
      }
      for(int i = 0; i < sequence.size() - 1; i++) {
         if(getHammingDistance(sequence.get(i), sequence.get(i + 1)) > 1 || !isWord(sequence.get(i)) || sequence.get(i).equals(sequence.get(i + 1))) {
            return false;
         }
      }
      return true;
   }
}

