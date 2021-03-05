import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;


// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @ Aislinn Smyth
 *  @version HT 2020
 *  
 *  		
		/*	MILLISECONDS	   Insert		  Selection    Quick 	  Merge
Numbers 1000					12.6			8.6			8.3			3
Numbers 1000 Duplicate			13.3			8			8			1.6
Numbers 10000					116				45.3		46.3		18.6		
Numbers nearly ordered 1000     8.3				8.3			8			2	
Numbers reverse 1000			16.6			8.6			10.3		3.6
Numbers sorted 1000				8.3				8			9.3			2.3

 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */	
	static double [] insertionSort (double a[]){	//done


		for(int i =1; i < a.length; i++) {		//for i = 1, the second element so we can compare to the initial element
			double position = a[i];				//storing the value of the ith element in the current position
			int j = i - 1;						//

			while(j >= 0 && a[j] > position) {		//comparing element, if element is smaller than position - compare to before elements.
				a[j+1] = a[j];						//swapping elements
				j = j-1;
			}
			a[j+1] = position;
		}
		return a;

	}//end insertionsort

	/**
	 * Sorts an array of doubles using Selection Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] selectionSort (double a[]){	//done

		for(int j=0; j<a.length-1; j++) {			//for loop to loop through all elements in the array

			int min = j;							//smallest element initialize
			for(int k=j+1; k<a.length; k++) 		//for loop through elements one ahead of smallest element j
				if(a[k] < a[min]) 					//comparing smallest element to element in front, if a[k] is smaller, swap
					min =k;

			double temp = a[min];
			a[min] = a[j];
			a[j] = temp;
		}

		return a;

	}//end selectionsort

	/**
	 * Sorts an array of doubles using Quick Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] quickSort (double a[], int end, int start)
	{
		if(end < start) {
			int pivot = (int) partition(a, end, start);		//cast as int as it was a double, storing the values from partition in pivot

			quickSort(a, end, pivot-1);						//sorting array end
			quickSort(a, pivot+1, start);					//sorting array start
		}
		return a;




	}//end quicksort

	private static double partition(double a[], int end, int start) {		//partition function to split up the array
		double pivot = a[start];  						//initializing the pivot element as the starting element which all other elements of the array will be compared to
		int i = (end-1); 								// end-1 so no stack overflow and we compare every element.
		for (int j=end; j<start; j++) {					//for loop so that the counter can run to the correct amount of elements it contains. 
			if (a[j] <= pivot) { 						//checking if element is less than or equal to the pivot element
				i++; 									

				double temp = a[i]; 					// swap values at a[i] and a[j] 
				a[i] = a[j]; 
				a[j] = temp; 
			} 
		} 


		double temp = a[i+1]; 							// swap a[i+1] and pivot element (or pi) 
		a[i+1] = a[start]; 
		a[start] = temp; 

		return i+1; 
	} 
	/**
	 * Sorts an array of doubles using Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a).
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */

	static double[] mergeSort (double a[], int n) {


		if (n < 2) { 								//ensure no stack overflow.
			return a; 
		}

		int mid = n / 2;							//cutting midpoint in half.
		double[] start = new double[mid];			//initialize arrays start and end
		double[] end = new double[n - mid];

		for (int i = 0; i < mid; i++) {
			start[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			end[i - mid] = a[i];
		}
		mergeSort(start, mid);						//sorting the start to the middle of the array.
		mergeSort(end, n - mid);					//sorting middle+1 to the end of the array.

		merge(a, start, end, mid, n - mid);			//merge the two sorted arrays
		return a;
	}
	//end mergesort


	public static void merge(
			double[] a, double[] start, double[] end, int left, int right) {

		int i=0;								//initializing counters
		int j=0;
		int k=0;

		while (i < left && j < right) {
			if (start[i] <= end[j]) {
				a[k++] = start[i++];
			}
			else {
				a[k++] = end[j++];
			}
		}
		while (i < left) {					//this is copying the remaining elements if there are any into start[]
			a[k++] = start[i++];
		}
		while (j < right) {					//this is copying the remaining elements if there are any into end[]
			a[k++] = end[j++];
		}
	}

}

//	public static void main(String[] args) throws Exception {


/*	MILLISECONDS	Insert		 Selection 		Quick 		Merge
Numbers 1000					12.6			8.6			8.3			  3
Numbers 1000 Duplicate			13.3			8			8			1.6
Numbers 10000					116				45.3		46.3		18.6		
Numbers nearly ordered 1000     8.3				8.3			8			2	
Numbers reverse 1000			16.6			8.6			10.3		3.6
Numbers sorted 1000				8.3				8			9.3			2.3

 * 
 * 
 * 
 * 
 *

/*		double [] a = new double[1000];		//initilise array to size 1000; numbers1000
		int n = a.length;
		double [] b = new double[1000];		//duplicate numbers 1000
		int n1 = b.length;
		double [] c = new double[10000];	//numbers 10000
		int n2 = c.length;
		double [] d = new double[1000];		//nearly ordered 1000
		int n3 =d.length;
		double [] e = new double[1000];		//numbers reverse 1000
		int n4 = e.length;
		double [] f = new double[1000];		// numbers sorted 1000
		int n5 = f.length; 



		//numbers1000 
		File file = new File("C:\\Users\\aisli\\OneDrive\\Documents\\CSY2\\Algorithms Y2\\Assignment 1\\numbers1000.txt");
		Scanner sc = new Scanner(file);
		for(int i=0; i<a.length; i++) {
			a[i] = sc.nextDouble();
		}  
		long start = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.insertionSort(a)));		// 13		/12		/13			AVERAGE: 12.6
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		System.out.println(elapsedTime);

		long start1 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.selectionSort(a)));		//8			/9		/9			AVERAGE: 8.6
		long end1 = System.currentTimeMillis();
		long elapsedTime1 = end1 - start1;
		System.out.println(elapsedTime1);

		long start2 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.quickSort(a, 0, n-1)));	//8			/9		/8			AVERAGE: 8.3
		long end2 = System.currentTimeMillis();
		long elapsedTime2 = end2 - start2;
		System.out.println(elapsedTime2);

		long start3 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.mergeSort(a, n))); 		//3	 		/3		/3			AVERAGE: 3
		long end3 = System.currentTimeMillis();
		long elapsedTime3 = end3 - start3;
		System.out.println(elapsedTime3); 


	//numbers1000Duplicate 
		 File newfile = new File("C:\\Users\\aisli\\OneDrive\\Documents\\CSY2\\Algorithms Y2\\Assignment 1\\numbers1000Duplicates.txt"); 
		 Scanner sc1 = new Scanner(newfile); 
		 for(int j=0; j<b.length; j++) { 
			 b[j] = sc1.nextDouble(); 
		 }  
		 long start4 = System.currentTimeMillis();
		 System.out.println(Arrays.toString(SortComparison.insertionSort(b)));		//13		//17		//10	AVERAGE: 13.3
		 long end4 = System.currentTimeMillis();
		 long elapsedTime4 = end4 - start4;
		 System.out.println(elapsedTime4);

		 long start5 = System.currentTimeMillis();
		 System.out.println(Arrays.toString(SortComparison.selectionSort(b)));		//9			//8			//7		AVERAGE: 8
		 long end5 = System.currentTimeMillis();
		 long elapsedTime5 = end5 - start5;
		 System.out.println(elapsedTime5);

		 long start7 = System.currentTimeMillis();
		 System.out.println(Arrays.toString(SortComparison.quickSort(b, 0, n1-1))); //8			//9			//7		AVERAGE: 8
		 long end7 = System.currentTimeMillis();
		 long elapsedTime7 = end7 - start7;
		 System.out.println(elapsedTime7);

		 long start8 = System.currentTimeMillis();
		 System.out.println(Arrays.toString(SortComparison.mergeSort(b, n1)));		//2			//2			//1		AVERAGE: 1.6
		 long end8 = System.currentTimeMillis();
		 long elapsedTime8 = end8 - start8;
		 System.out.println(elapsedTime8); 


		//numbers 10000
		File newfile1 = new File("C:\\Users\\aisli\\OneDrive\\Documents\\CSY2\\Algorithms Y2\\Assignment 1\\numbers10000.txt"); 
		Scanner sc2 = new Scanner(newfile1); 
		for(int k=0; k<c.length; k++) { 
			c[k] = sc2.nextDouble(); 
		}
		long start9 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.insertionSort(c)));	// 143		//99		//106		AVERAGE: 116
		long end9 = System.currentTimeMillis();
		long elapsedTime9 = end9 - start9;
		System.out.println(elapsedTime9); 

		long start10 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.selectionSort(c)));	//48		//41		//47		AVERAGE: 45.3
		long end10 = System.currentTimeMillis();
		long elapsedTime10 = end10 - start10;
		System.out.println(elapsedTime10);

		long start11 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.quickSort(c, 0, n2-1)));	//44	//52		//45		AVERAGE: 46.3
		long end11 = System.currentTimeMillis();
		long elapsedTime11 = end11 - start11;
		System.out.println(elapsedTime11); 

		long start12 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.mergeSort(c, n2)));		//19	//22		//15		AVERAGE: 18.6
		long end12 = System.currentTimeMillis();
		long elapsedTime12 = end12 - start12;
		System.out.println(elapsedTime12);	


		//numbers nearly ordered 10000 
		File newfile2 = new File("C:\\Users\\aisli\\OneDrive\\Documents\\CSY2\\Algorithms Y2\\Assignment 1\\numbersNearlyOrdered1000.txt"); 
		Scanner sc3 = new Scanner(newfile2); 
		for(int l=0; l<d.length; l++) { 
			d[l] = sc3.nextDouble(); 
		}
		long start13 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.insertionSort(d)));	//7		//10	//8			AVERAGE:8.3
		long end13 = System.currentTimeMillis();
		long elapsedTime13 = end13 - start13;
		System.out.println(elapsedTime13);

		long start14 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.selectionSort(d)));	//8		//9		//8			AVERAGE:8.3
		long end14 = System.currentTimeMillis();
		long elapsedTime14 = end14 - start14;
		System.out.println(elapsedTime14);

		long start15 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.quickSort(d, 0, n3-1)));	//7	//8		//9			AVERAGE: 8
		long end15 = System.currentTimeMillis();
		long elapsedTime15 = end15 - start15;
		System.out.println(elapsedTime15);

		long start16 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.mergeSort(d, n3)));		//2	//2		//2			AVERAGE: 2
		long end16 = System.currentTimeMillis();
		long elapsedTime16 = end16 - start16 ;
		System.out.println(elapsedTime16); 

		//numbers reverse 1000 
		File newfile3 = new File("C:\\Users\\aisli\\OneDrive\\Documents\\CSY2\\Algorithms Y2\\Assignment 1\\numbersReverse1000.txt");
		Scanner sc4 = new Scanner(newfile3);
		for(int m=0; m<e.length; m++) {
			e[m] = sc4.nextDouble(); 
		}
		long start17 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.insertionSort(e)));	//16		//13	//21	AVERAGE:16.6
		long end17 = System.currentTimeMillis();
		long elapsedTime17 = end17 - start17;
		System.out.println(elapsedTime17);

		long start18 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.selectionSort(e)));	//7			//8		//11	AVERAGE: 8.6
		long end18 = System.currentTimeMillis();
		long elapsedTime18 = end18 - start18;
		System.out.println(elapsedTime18);

		long start19 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.quickSort(e, 0, n4-1))); //9		//9		//13	AVERAGE:10.3
		long end19 = System.currentTimeMillis();
		long elapsedTime19 = end19 - start19;
		System.out.println(elapsedTime19);

		long start20 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.mergeSort(e, n4)));		//4		//3		//4		AVERAGE: 3.6
		long end20 = System.currentTimeMillis();
		long elapsedTime20 = end20 - start20;
		System.out.println(elapsedTime20); 

		//numbers sorted 1000
		File newfile4 = new File("C:\\Users\\aisli\\OneDrive\\Documents\\CSY2\\Algorithms Y2\\Assignment 1\\numbersSorted1000.txt");
		Scanner sc5 = new Scanner(newfile4);
		for(int o=0; o<f.length; o++) {
			f[o] = sc5.nextDouble();
		}

		long start21 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.insertionSort(f)));	//7		//9		/9		AVERAGE: 8.3
		long end21 = System.currentTimeMillis();
		long elapsedTime21 = end21 - start21;
		System.out.println(elapsedTime21);

		long start22 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.selectionSort(f)));	//9		//8		//7		AVERAGE: 8
		long end22 = System.currentTimeMillis();
		long elapsedTime22 = end22 - start22;
		System.out.println(elapsedTime22);

		long start23 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.quickSort(f, 0, n5-1))); //9	//10	//9		AVERAGE: 9.3
		long end23 = System.currentTimeMillis();
		long elapsedTime23 = end23 - start23;
		System.out.println(elapsedTime23);

		long start24 = System.currentTimeMillis();
		System.out.println(Arrays.toString(SortComparison.mergeSort(f, n5)));	//3		//3		//2		AVERAGE: 2.3
		long end24 = System.currentTimeMillis();
		long elapsedTime24 = end24 - start24;
		System.out.println(elapsedTime24); */

//TO DO:  1) FIX MERGE SORT AS THERE IS A STACK OVERFLOW		// DONE
//2) DO THE EMPTY TEST IN JUNIT			
//3) DO TABLE FOR TIME IT TAKES TO SORT 

//end class

//while(sc.hasNextLine()) {
//System.out.println(sc.nextLine());
//}


/*
 * double [] a = new double[1000]; 
 * Scanner newScanner = new Scanner(System.in);
 * 
 * File fileName = new File("numbers1000.txt"); Scanner inFile = new
 * Scanner(fileName); for(int i=0; i < a.length; i++) { a[i] =
 * inFile.nextDouble(); } System.out.println(Arrays.toString(a));
 */



//Testing with small numbers before importing large numbers
//double a[] = {3, 7, 2, 1, 11, 9, 32, 14, 4, 19, 5, 20};
//double b[] = {};


//System.out.println(Arrays.toString(SortComparison.insertionSort(a)));
//System.out.println(Arrays.toString(SortComparison.selectionSort(a)));
//System.out.println(Arrays.toString(SortComparison.quickSort(a, 0, n-1)));
//		System.out.println(Arrays.toString(SortComparison.mergeSort(a, n)));

//todo: do experiments as per assignment instructions

