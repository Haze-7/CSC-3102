package dateorganizer;

import java.util.*;

/**
 * This class describes a priority min-queue that uses an array-list-based min binary heap 
 * that implements the PQueueAPI interface. The array holds objects that implement the 
 * parameterized Comparable interface.
 * @author Duncan
 * @param <E> the priority queue element type. 
 * @since 99-99-9999
 */
public class PQueue<E extends Comparable<E>> implements PQueueAPI<E>
{    
   /**
    * A complete tree stored in an array list representing the 
    * binary heap
    */
   private ArrayList<E> tree;
   /**
    * A comparator lambda function that compares two elements of this
    * heap when rebuilding it; cmp.compare(x,y) gives 1. negative when x less than y
    * 2. positive when x greater than y 3. 0 when x equal y
    */   
   private Comparator<? super E> cmp;
   
   /**
    * Constructs an empty PQueue using the compareTo method of its data type as the 
	* comparator
    */
   public PQueue()
   {
	  this.tree = new ArrayList<>();
	  this.cmp = (x,y) -> x.compareTo(y);
   }
   
   /**
    * A parameterized constructor that uses an externally defined comparator    
    * @param fn - a trichotomous integer value comparator function   
    */
   public PQueue(Comparator<? super E> fn)
   {
	   this.tree = new ArrayList<>();
	  if (fn != null)
	  {
		  cmp = fn;
	  }
	  else
	  {
		  cmp = (x,y) -> x.compareTo(y);
	  }
	   
   }

   public boolean isEmpty()
   {
	  
      return tree.isEmpty();
   }

   public void insert(E obj)
   {
      tree.add(obj);
      
      int currindex = tree.size() - 1;
      while(currindex > 0)
      {
    	  int parIndex = (currindex - 1) / 2;
    	  
          E parentElement = tree.get(parIndex);
          E childElement = tree.get(currindex);
          if (cmp.compare(childElement, parentElement) < 0)
          {
        	  swap (currindex, parIndex);
        	  currindex = parIndex;
          }
          else
          {
        	  break;
          }
      }

   }

   public E remove() throws PQueueException
   {
     if (tree.isEmpty())
     {
    	 throw new PQueueException("The Queue is Empty!");
     }
     else
     {
    	 E root = tree.get(0);
    	 int last = tree.size() - 1;
    	 tree.set(0, tree.get(last));
    	 tree.remove(last);
    	 
    	 if (!isEmpty())
    	 {
    	 rebuild(0);
    	 } 
 
      return root;
     }
   }
 
   public E peek() throws PQueueException
   {
	  if (isEmpty())
	  {
		  throw new PQueueException("The Queue is Empty!");
	  }
	  else
	  {
		 
      return tree.get(0);
	  }
   }

   public int size()
   {
      return tree.size();
   }
   
   /**
    * Swaps a parent and child elements of this heap at the specified indices
    * @param place an index of the child element on this heap
    * @param parent an index of the parent element on this heap
    */
   private void swap(int place, int parent)
   {
      //implement this method
	   E temp = tree.get(place);
	   tree.set(parent, tree.get(parent));
	   tree.set(parent, temp);
   }

   /**
    * Rebuilds the heap to ensure that the heap property of the tree is preserved.
    * @param root the root index of the subtree to be rebuilt
    */
   private void rebuild(int root)
   {
      //implement this method
	   int leftNode = 2 * root + 1;
	   int rightNode = 2 * root + 2;
	   int least = root;
	   
	   if (leftNode < tree.size() && cmp.compare(tree.get(leftNode), tree.get(least)) < 0);
	   {
		   least = leftNode;
	   }
	   if (rightNode < tree.size() && cmp.compare(tree.get(rightNode), tree.get(least)) < 0);
	   {
		   least = rightNode;
	   }
	   if (least != root)
	   {
		   swap(root, least);
		   rebuild(least);
	   }
   }
}
