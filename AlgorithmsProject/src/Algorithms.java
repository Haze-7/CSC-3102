

//Hayes Chiasson
//897578538

public class Algorithms {
    
    public static void bubbleSort(int[]array) {
        for(int i = 0; i < array.length - 1; i++) //each iteration of this loop results in the largest unsorted element being "bubbled up" to the last unsorted position in the array
            for(int j = 0; j < array.length - i - 1; j++) //this loop performs pair-wise comparisons of elements, swapping elements if the left is larger than the right
                if(array[j] > array[j + 1]) //if left element larger than right, perform swap
                {
                    int temp = array[j]; //temporary variable established to hold j as it is set to next value
                    array[j] = array[j + 1]; //j is set to value above/ swapped
                    array[j + 1]  = temp; // j+1 is then switched to the temp, which holds the previous value of j
                }
    }
    
    //Complete Bubble Sort - Short Circuit (15)
    public static void bubbleSortCS(int[]array) {
    	for (int i = 0; i < array.length - 1; i++)
    	{
    		boolean swap = false; //sets a flag to "trip the circuit" and alert that sort is complete when no sorting has taken place in a pass
    		
    		for (int j = 0; j < array.length - i - 1; j++)
    		{
    			if (array[j] > array[j + 1])
    			{
    				int temp = array[j]; //temporary variable established to hold j as it is set to next value
    				array[j] = array[j+1]; //j is set to value above/ swapped
    				array[j+1] = temp; // j+1 is then switched to the temp, which holds the previous value of j
    				swap = true; // only changes if a swap occurs
    				    			
    			}
    		}
    		
    		if (!swap) //ends /cuts circuit if no sorts have been detected in a pass
    		{
    			break;
    		}
    	}
    }
    
    //Complete Selection Sort (15)
    public static void selectionSort(int[]array) { // sorts an array by repeatedly finding the smallest element and moving it to the front
    
    	for (int i = 0; i < array.length - 1; i++) // loop/ move through array
    	{
    		int minimum = i;
    		
    		for (int j = i + 1; j < array.length;j++) // find the minimum element after the ith element
    		{
    			if (array[j] < array[minimum])
    			{
    				minimum = j;
    				
    				int smallest  = array[minimum]; //perform a swap of thee minimum you found with the ith element
    				array[minimum] = array[i];
    				array[i] = smallest;
    			}
    		}
    	}
    }
    
    //Complete Insertion Sort (15)
    public static void insertionSort(int array[]){

    	for (int i = 1; i < array.length; i++) // iterate/ move through array
    	{
    		int key = array[i]; // key will be the number we follow through / 2nd element
    		int j = i - 1; // j will trail behind i in order to allow for lower numbers to be put below the key // first element
    		
    		while (j >= 0 && array[j] > key) // move elements above/ greater than the key up one index/ move them to the side to make space
    			// above while loop can make this process VERY long if lots of values
    		{
    			array[j + 1] = array[j]; // set to 1st element
    			j = j - 1; // set to 2nd element
    		}
    		array[j + 1] = key; // key is the # being moved and sorted and array[j+1] is the number being tested against it
    	}
    }
 
    //Complete Merge Sort (15)
    public static void mergeSort(int[] array) {  //sorts by splitting into halves, sorting, then recombining them recursively until completion.
    	//uses divide and conquer tactics
    	if (array.length < 2) // if only one element, return since it won't work
    	{
    		return;
    	}
    	
    	int middle = array.length / 2; //find midpoint / middle value of array to split from
    	int [] left = new int[middle]; //create first half array from beginning to middle
    	int [] right = new int [array.length - middle];  //create second half array from middle to the end by subtracting the 1st half
    	for (int i = 0; i < middle; i++)  // fill left/ first half array until it reaches the midpoint
    	{
    		left[i] = array[i]; 
    	}
    	for (int i = middle; i < array.length; i++) //fill the right/ second half array from the midpoint to the end
    	{
    		right[i - middle] = array[i];
    	}
    	mergeSort(left); //sort left half
    	mergeSort(right); //sort right half
    	// do these sorts recursively until complete
    	
    	merge(array, left, right, middle, array.length - middle); //merge left and right halves
    }

    private static void merge(int[] array, int[] l, int[] r, int left, int right) { 
    	int i = 0;
    	int j = 0;
    	int k = 0; // initialize variables to be used for iteration and storing
    	
    	while (i < left && j < right) //each is used to iterate through left and right arrays, keep going until end of section
    	{
    		if (l[i] <= r[j]) //input left or right element depending on size of values into the merged array
    		{
    			array[k] = r[j]; //set main array to left
    			j++; // iterate/ move to next value
    		}
    		else
    		{
    			array[k] = r[j]; //set main array to left
    			j++; // iterate/ move to next value
    		}
    		k++; // move on the next independently of other factors
    	}
    	while (i < left) //fills in the first half of the array with elements from l, then
    	{
    		array[k++] = l[i++];
    	}
    	while (j < right) //fills in the 2nd half once the first half is finished
    	{
    		array[k++] = r[j++]; 
    	}
    	
    		
    	

    }
    //Optionally complete Quicksort (+5 bonus)
    public static void quicksort(int[] array, int from, int to) ///uses divide and conquer strategy like mergesort / changes order of equal values in some cases / narrows in from both sides
    //does not create temporary arrays
    {
    if (from >= to) //from is smaller side, so must be smaller to continue with quick sort // both must be above or below pivot selected
    {
    	return;
    }
    int p = partition(array, from, to); //runs through partition to complete swaps, increments, and decrements until finished
    
    quicksort(array, from, p); //runs if check before running particion again
    
    quicksort(array, p + 1, to);
    }
	
    private static int partition(int[] array, int from, int to) //avoids creating temporary arrays
    {
    	int pivot = array[from]; //first element is chosen to start as pivot
    	int i = from - 1;
    	int j = to + 1;
    	
    	while (i < j) // while unable to find a pivot/ do until you can find one
    	{
    		i++;
    		
    	while (array[i] < pivot) //increment i and decrement j until a swap can occur
    	{
    		i++;
    	}
    	
    	j--;
    	
    	while (array[j] > pivot) //decrement if not able to increment i / all based on pivot value
    	{
    		j--;
    	}
    	
    	if (i < j) // swapping values if i < j
    	{
    		array[i] += array[j];
    		array[j] = array[i] - array[j];
    		array[i] -= array[j];
    	}
    }
    	return j; //return each solved for value to create the final sorted array
    }
    
}