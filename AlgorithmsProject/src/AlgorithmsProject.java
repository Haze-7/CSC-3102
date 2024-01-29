//Hayes Chiasson
//897578538

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class AlgorithmsProject {

    public static void main(String[] args) {
        
        
        //Read array size from user
        System.out.print("Enter array size: ");
        Scanner in = new Scanner(System.in);
			int[] array = new int[in.nextInt()]; 
			
			//populate array
			Random rand = new Random();
			for(int i = 0; i < array.length; i++)
			    array[i] = rand.nextInt(1000);
			
			//Bubble sort the array and measure the execution time
			long startTime = System.currentTimeMillis();
			Algorithms.bubbleSort(array.clone());
			long elapsedTime = System.currentTimeMillis() - startTime;   
			System.out.println("Bubble sort time: " + elapsedTime + " (ms)");
			
			startTime = System.currentTimeMillis();
			Algorithms.bubbleSortCS(array.clone());
			elapsedTime = System.currentTimeMillis() - startTime;   
			System.out.println("Bubble sort CS time: " + elapsedTime + " (ms)");
			   
			startTime = System.currentTimeMillis();
			Algorithms.selectionSort(array.clone());
			elapsedTime = System.currentTimeMillis() - startTime;   
			System.out.println("Selection sort time: " + elapsedTime + " (ms)");
			
			startTime = System.currentTimeMillis();
			Algorithms.insertionSort(array.clone());
			elapsedTime = System.currentTimeMillis() - startTime;   
			System.out.println("Insertion sort time: " + elapsedTime + " (ms)");

			startTime = System.currentTimeMillis();
			Algorithms.mergeSort(array.clone());
			elapsedTime = System.currentTimeMillis() - startTime;   
			System.out.println("Merge sort time: " + elapsedTime + " (ms)");
			
			startTime = System.currentTimeMillis();
			Arrays.sort(array.clone());
			elapsedTime = System.currentTimeMillis() - startTime;   
			System.out.println("Java (quick) sort time: " + elapsedTime + " (ms)");
			
			//Optionally implement driver code for Quicksort here	
			startTime = System.currentTimeMillis();
	        Algorithms.quicksort(array.clone(), array[0], array.length - 1);
	        elapsedTime = System.currentTimeMillis() - startTime;   
	        System.out.println("Quick sort time: " + elapsedTime + " (ms)");
			
        }
		
       
        
        
    }
    

