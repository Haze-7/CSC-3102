package dendrologist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A testbed for an augmented implementation of an AVL tree
 * @author William Duncan, Hayes Chiasson
 * @see AVLTreeAPI, AVLTree
 * <pre>
 * Date: 10-16-2023
 * Course: csc 3102 
 * Programming Project # 2
 * Instructor: Dr. Duncan 
 * </pre>
 */
public class Dendrologist
{
    public static void main(String[] args) throws FileNotFoundException, AVLTreeException 
    {
        String usage = "Dendrologist <order-code> <command-file>\n";
        usage += "  <order-code>:\n";
        usage += "  0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  -1 for reverse lexicographical order\n";
        usage += "  1 for lexicographical order\n";
        usage += "  -2 ordered by decreasing string\n";
        usage += "  2 ordered by increasing string\n";
        usage += "  -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  3 ordered by increasing string length, primary key, and lexicographical order, secondary key\n";      
        if (args.length != 2)
        {
            System.out.println(usage);
            throw new IllegalArgumentException("There should be 2 command line arguments.");
        }
        int orderCode = Integer.parseInt(args[0]); //set order code, and translate it into an integer from string in text file
        String commandFile = args[1]; //
        
        Comparator<String> comparator; // establish string comparator 
        
        switch (orderCode) // order comparator codes
        {
        case 0:
        	comparator = Comparator.<String, Integer>comparing(String::length).thenComparing(Comparator.reverseOrder());
        	break;
        case -1:
        	comparator = Comparator.reverseOrder();
        	break;
        case 1:
        	comparator = Comparator.naturalOrder();
        	break;
        case -2:
        	comparator = Comparator.<String, Integer>comparing(String::length).reversed();
        	break;
        case 2:
        	comparator = Comparator.<String, Integer>comparing(String::length);
        	break;
        case -3:
        	comparator = Comparator.<String, Integer>comparing(String::length).reversed().thenComparing(Comparator.reverseOrder());
        	break;
        case 3:
        	comparator = Comparator.<String, Integer>comparing(String::length).thenComparing(Comparator.naturalOrder());
        	break;
        default:
        	System.out.println("Invalid order code. Using default lexicographical order."); // backup option when no order if fulfilled
        	comparator = Comparator.naturalOrder();
        }
        
        AVLTree<String> tree = new AVLTree<>(comparator); // create AVL Tree to modify
        
        Scanner in = new Scanner(new FileReader("src/" + commandFile)); // create a file reader scanner
        
        while (in.hasNextLine()) // while there are lines left in file
        {
        	String[] split = in.nextLine().split("\\s+"); //splits between spaces to read each part individually
        	
        	if(split.length == 2) // check for insert, delete, and gen to do work on
        	{
        		String command = split[0]; //first part = command(insert/ delete/ gen)
        		String value = split[1]; // second part = value 
        		
        		switch(command) //picks which one is being used/ identifies command 
        		{
        		case "insert": // insert case/ when insert
        			tree.insert(value); // inserts value
        			System.out.printf("Inserted: %s\n", value); //display inserted value
        			break;
        			
        		case "delete":
        			tree.remove(value); //delete value
        			System.out.printf("Deleted %s\n", value); //display deleted value
        			break;
        			
        		case "gen":
        			System.out.printf("Geneology: %s", value); // print out, then solve
        			//solving for genealogy
        			if (tree.inTree(value)) // if value is in the tree
        			{
        				String parent = tree.getParent(value); // set parent
        				ArrayList<String> children = tree.getChildren(value); // create arraylist to fill with children and do actions on
        				String getLeftChild = children.get(0); // gets the left child from the ArrayList
        				String getRightChild = children.get(1); // gets the right child from the ArrayList
        				String numLeftChild = (getLeftChild != null) ? getLeftChild : "NONE"; // if left child isn't null, if left child is true, get it, or display NONE
        				String numRightChild = (getRightChild != null) ? getRightChild : "NONE"; //
        				
        				int numOfAncestors = tree.ancestors(value); // gets the # of ancestors
        				int numOfDescendants = tree.descendants(value); //gets the # of descendants
        				
        				System.out.println();
        				System.out.printf("parent = %s, left-child = %s, right-child = %s, #ancestors = %d, #descendants = %d ", parent, numLeftChild, numRightChild, numOfAncestors, numOfDescendants);
        			}
        			else
        			{
        				System.out.print(" UNDEFINED"); // if not in tree, it is undefined
        			}
        			System.out.println();
        			break;
  	
        			default: 
        				throw new IllegalArgumentException("File <- Parsing Error.");
        		}
        	}
        	else if (split.length == 1) //check for props or traverse commands
        		{
        			String command = split[0]; //command is found in first half
        			
        			switch(command) // define/ detect commands and run them 
        			{
        			case "props": // find tree properties and display them
            			int size = tree.size(); // find tree size
            			int height = tree.height(); // find tree height
            			int diameter = tree.diameter(); // find tree diameter
            			boolean isFibonacci = tree.isFibonacci(); // check if fibonacci tree
            			boolean isComplete = tree.isComplete(); //check if complete tree
            			
            			System.out.printf("Properties:\nsize = %d, height = %d, diameter = %d\n", size, (height - 1), diameter); // check if works (diameter may be wrong)
            			System.out.printf("fibonacci? = %b, complete? = %b\n", isFibonacci, isComplete);
            			break;
            		
        			case "traverse": // traversal of the tree, do in 3 different orders
        				System.out.println("Pre-Order Traversal:"); // pre order, top -> bot, R->L
        				tree.preorderTraverse(data -> { //find data and put in order
        					System.out.println(data + " "); // display data in pre-order
        					return null;
        				});
        				
        				System.out.println("In-Order Traversal:"); // in order, through tree from bot -> top, L->R
        				tree.traverse(data -> { //use normal traversal method
        					System.out.println(data + " "); //display data in order
        					return null;
        				});
        				
        				System.out.println("Post-Order Traversal:"); //post order, from bot -> top, L -> R
        				tree.postorderTraverse(data -> { //use post order traversal method
        					System.out.println(data + " "); // display order in post-order
        					return null;
        				});
        				
        				break;
        			
        			default: 
        				throw new IllegalArgumentException("File <- Parsing Error."); // throw an exception if there is a parsing error/ unable to identify
        			
        		}
        	}
        }
    }
}
