import java.util.Iterator;
import java.lang.Math;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-10-19
 *
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if(element != null)
      {
         Node tmp = new Node(element);
         if(size == 0)
         {
            front = tmp;
            rear = tmp;
            size++;
            return true;
         }
         
         // if(size == 0)
         // {
            // front = tmp;
            // rear = tmp;
            // size++;
            // return true;
         // }
         else
         {
            Node working = front;
            for(int i = 0; i < size; i++)
            {
               if(tmp.element.compareTo(working.element) == 0)
               {
                  return false;
               }
               else if(tmp.element.compareTo(working.element) < 0)
               {
                  tmp.prev = working.prev;
                  tmp.next = working;
                  if(working != front)
                  {
                     working.prev.next = tmp;
                  }
                  working.prev = tmp;
                  if(tmp.prev == null)
                  {
                     front = tmp;
                  }
                  /*if(tmp.next == null)
                  {
                      rear = tmp;
                  }
                  */
                  size++;
                  return true;
               }
               if(size - i > 1)
               {
                  working = working.next;
               }
            }
            working.next = tmp;
            tmp.prev = working;
            rear = tmp;
            size++;
            return true;
         }
      }
      return false;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if(element != null)
      {
         Node working = front;
         for(int i = 0; i < size; i++)
         {
            if(working.element.compareTo(element) == 0)
            {
               if(working == front && working != rear)
               {
                  working.next.prev = null;
                  front = working.next;
                  size--;
                  return true;
               }
               else if(working != front && working == rear)
               {
                  working.prev.next = null;
                  rear = working.prev;
                  size--;
                  return true;
               }
               else if(working != front)
               {
                  working.prev.next = working.next;
                  working.next.prev = working.prev;
                  size--;
                  return true;
               }
               else
               {
                  front = null;
                  rear = null;
                  size--;
                  return true;
               }
            }
            if(size - i > 1)
            {
               working = working.next;
            }
         }
         return false;
      }
      return false;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if(element != null)
      {
         Node working = front;
         for(int i = 0; i < size; i ++)
         {
            if(working.element.compareTo(element) == 0)
            {
               return true;
            }
            if(size - i > 1)
            {
               working = working.next;
            }
         }
         return false;
      }
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if(s.size() != this.size())
      {
         return false;
      }
      Node working = front;
      for(int i = 0; i < size; i++)
      {
         if(!s.contains(working.element))
         {
            return false;
         }
         working = working.next;
      }
      return true;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if(s.size() != this.size())
      {
         return false;
      }
      Node working = front;
      Node working2 = s.front;
      for(int i = 0; i < size; i++)
      {
         if(working.element.compareTo(working2.element) != 0)
         {
            return false;
         }
         working = working.next;
         working2 = working2.next;
      }
      return true;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      if(s == null)
      {
         throw new IllegalArgumentException();
      }
      Set<T> combo = new LinkedSet<>();
      Node working = front;
      Iterator<T> iterate = s.iterator(); 
   
      for(int i = 0; i < size; i++)
      {
         combo.add(working.element);
         working = working.next;
      }
   
      for(int i = 0; i < s.size(); i++)
      {
         combo.add(iterate.next());
      }
      return combo;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      if(s == null) {
         throw new IllegalArgumentException();
      }
      if(this == s) {
         return this;
      }
      if(isEmpty()) {
         return s;
      }
      if(s.isEmpty()) {
         return this;
      }
      LinkedSet<T> combo = new LinkedSet<>();
       
      Node working = this.front;
      Node working2 = s.front;
      Node working3 = null;
   
      int j = 0;
   
      if(size >= s.size) {
         for(int i = 0; i < this.size(); i++) {
            if(j == s.size) {
               for(int k = i; k <= size; k++) {
                  combo.add(working.element);
                  working = working.next;
               }
               return combo;
            }
            if(working.element.compareTo(working2.element) <= 0) {
               combo.add(working.element);
               working = working.next;
               j++;
            }
            
            else {
               for(; j < s.size; j++) {
                  if(working2.element.compareTo(working.element) <= 0) {
                     combo.add(working2.element);
                     working2 = working2.next;
                  } 
                  else {
                     break;
                  }
               }
            }
         }
         return combo;
      }
      else {
         for(int i = 0; i < s.size; i++) {
            if(j == this.size()) {
               for(int k = i; k <= s.size; k++) {
                  combo.add(working2.element);
                  working2 = working2.next;
               }
               return combo;
            }
            if(working2.element.compareTo(working.element) <= 0) {
               combo.add(working2.element);
               working = working.next;
               j++;
            }
            else {
               for(; j < this.size; j++) {
                  if(working.element.compareTo(working2.element) <= 0) {
                     combo.add(working.element);
                     working = working.next;
                  }
                  else {
                     break;
                  }
               }
            }
         }
         return combo;
      }
      
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if(s == null) {
         throw new IllegalArgumentException();
      }
      if(s == this) {
         return s;
      }
      if(isEmpty()) {
         return new LinkedSet<T>();
      }
      if(s.isEmpty()) {
         return new LinkedSet<T>();
      }
      
      Set<T> junction = new LinkedSet<>();
      Node working = front;
      for(int i = 0; i < this.size(); i++)
      {
         if(s.contains(working.element))
         {
            junction.add(working.element);
         }
         working = working.next;
      }
      return junction;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      if(s == null) {
         throw new IllegalArgumentException();
      }
      if(s == this) {
         return s;
      }
      if(isEmpty()) {
         return new LinkedSet<T>();
      }
      if(s.isEmpty()) {
         return new LinkedSet<T>();
      }
      
      LinkedSet<T> junction = new LinkedSet<>();
      Node working = front;
      Node working2 = s.front;
      Node working3 = null;
   
      int j = 0;
      for(int i = 0; i < this.size(); i++) {
         if(j >= s.size) {
            break;
         }
         if(working.element.compareTo(working2.element) == 0) {
            junction.add(working.element);
            working = working.next;
            working2 = working2.next;
            j++;
         }
         
         else if(working.element.compareTo(working2.element) > 0) {
            for(; j < s.size(); j++) {
               if(working.element.compareTo(working2.element) <= 0) {
                  break;
               }
               working2 = working2.next;
            }
            if(j < s.size && working.element.equals(working2.element)) {
               junction.add(working.element);
               working = working.next;
            }
         }
      }
      return junction;
      
      
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      if(s == null) {
         throw new IllegalArgumentException();
      }
      if(s.size() == 0) {
         return this;
      }
      if(s == this) {
         return new LinkedSet<T>();
      }
      if(isEmpty()) {
         return new LinkedSet<T>();
      }
      if(s.isEmpty()) {
         return this;
      }
      
      Set<T> good = new LinkedSet<>();
      Node working = front;
   
      for(int i = 0; i < size; i++) {
         if(!s.contains(working.element)) {
            good.add(working.element);
         }
         working = working.next;
      }
      return good;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      if(s == null) {
         throw new IllegalArgumentException();
      }
      if(s.size() == 0) {
         return this;
      }
      if(s == this) {
         return new LinkedSet<T>();
      }
      if(isEmpty()) {
         return new LinkedSet<T>();
      }
      if(s.isEmpty()) {
         return this;
      }
      
      LinkedSet<T> good = new LinkedSet<>();
      Node working = front;
      Node working2 = s.front;
      Node working3 = null;
      int j = 0;
   
      for(int i = 0; i < size; i++) {
         if(j >= s.size()) {
            for(; i <= size; i++) {
               good.add(working.element);
               working = working.next;
            }
            return good;
         }
         if(working.element.compareTo(working2.element) < 0) {
            good.add(working.element);
            working = working.next;
         }
         else if(working.element.compareTo(working2.element) > 0) {
            for(; j < s.size; j++) {
               if(working.element.compareTo(working2.element) <= 0) {
                  break;
               }
               working2 = working2.next;
            }
            if(j >= s.size()) {
               for(; i <= size; i++) {
                  good.add(working.element);
                  working = working.next;
               }
               return good;
            }
            if(working.element.compareTo(working2.element) < 0) {
               good.add(working.element);
               working = working.next;
            }
         }
         else {
            j++;
            if(working.element.equals(working2.element)) {
               working2 = working2.next;
               working = working.next;
            }
         }
      }
      return good;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   @SuppressWarnings("unchecked")
   public Iterator<T> iterator() {
      class LinkedIterator implements Iterator {
         private Node current;
         public LinkedIterator() {
            current = front;
         }
      
         public boolean hasNext() {
            return current != null;
         }
      
         public T next() {
            if (!hasNext()) {
               throw new NoSuchElementException();
            }
         
            T result = current.element;
            current = current.next;
            return result;
         }
      
         public void remove() {
            throw new UnsupportedOperationException();
         }
      }
      return new LinkedIterator();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   @SuppressWarnings("unchecked")
   public Iterator<T> descendingIterator() {
      class LinkedIterator implements Iterator {
         private Node current;
         public LinkedIterator() {
            current = rear;
         }
      
         public boolean hasNext() {
            return current != null;
         }
      
         public T next() {
            if (!hasNext()) {
               throw new NoSuchElementException();
            }
         
            T result = current.element;
            current = current.prev;
            return result;
         }
      
         public void remove() {
            throw new UnsupportedOperationException();
         }
      }
      return new LinkedIterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   @SuppressWarnings("unchecked")
   public Iterator<Set<T>> powerSetIterator() {
      class PowerIterator implements Iterator {
         private int current;
         private Node working;
         public PowerIterator() {
            current = 0;
            working = front;
         }
      
         @Override
           public boolean hasNext() {
            if(current < Math.pow(2, size) && front != null) {
               return true;
            }
            return false;
         }
         @Override
           public void remove() {
            throw new UnsupportedOperationException();
         }
         @Override
           public Set<T> next() {
            if(!hasNext()) {
               throw new NoSuchElementException();
            }
            working = front;
            StringBuilder binary = new StringBuilder();
            binary.append(Integer.toBinaryString(current).toCharArray());
         
            Set<T> stuff = new LinkedSet<>();
            for(; binary.length() < size;) {
               binary.insert(0, 0);
            }
            for(int i = 0; i < binary.length(); i++) {
               if(binary.charAt(i) == '1') {
                  stuff.add(working.element);
               }
               working = working.next;
            }
            current++;
            return stuff;
         }
      }
      return new PowerIterator();
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
