import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	double [] b = {};
    	double [] expectedResult = {};
    	
    	SortComparison.insertionSort(b);
    	Arrays.equals(b, expectedResult);
    	SortComparison.selectionSort(b);
    	Arrays.equals(b, expectedResult);
    	SortComparison.quickSort(b, 0, 0);
    	Arrays.equals(b,  expectedResult);
    	SortComparison.mergeSort(b, 0);
    	Arrays.equals(b, expectedResult);
    	
    	
    	//assertNull(Arrays.toString(expectedResult) , SortComparison.insertionSort(b));
    	//assertEquals("This array is empty",  Arrays.toString(expectedResult) , SortComparison.selectionSort(new double[0]));
    	//assertEquals("This array is empty",  Arrays.toString(expectedResult) , SortComparison.quickSort(new double[0]));
    	//assertEquals("This array is empty",  Arrays.toString(expectedResult) , SortComparison.mergeSort(new double[0]));
    }

    @Test
    public void testInsertionSort()
    {
    	double [] a = {5.7, 2.3, 7, 19, 27.3, 1, 11, 21.9};
    	
    	double [] expectedResult = {1, 2.3, 5.7, 7, 11, 19, 21.9, 27.3};
    	
    	SortComparison.insertionSort(a);
    	
    	assertArrayEquals(expectedResult, a, 0);		//expected double, actual double, delta double.
    	
    }
    
    @Test
    public void testSelectionSort() {
    	
    	double [] a = {5.7, 2.3, 7, 19, 27.3, 1, 11, 21.9};
    	
    	double [] expectedResult = {1, 2.3, 5.7, 7, 11, 19, 21.9, 27.3};
    	
    	SortComparison.selectionSort(a);
    	
    	assertArrayEquals(expectedResult, a, 0);		//expected double, actual double, delta double.
    	
    	
    }
    
    @Test
    public void testQuickSort() {
    	
    	double [] a = {5.7, 2.3, 7, 19, 27.3, 1, 11, 21.9};
    	
    	double [] expectedResult = {1, 2.3, 5.7, 7, 11, 19, 21.9, 27.3};
    	
    	int n = a.length;
    	
    	SortComparison.quickSort(a, 0, n-1);
    	
    	assertArrayEquals(expectedResult, a, 0);		//expected double, actual double, delta double.
    	
    	
    }
    
    @Test
    public void testMergeSort() {

    	double [] a = {5.7, 2.3, 7, 19, 27.3, 1, 11, 21.9};
    	
    	int n = a.length;
    	
    	double [] expectedResult = {1, 2.3, 5.7, 7, 11, 19, 21.9, 27.3};
    	
    	SortComparison.mergeSort(a, n);
    	
    	assertArrayEquals(expectedResult, a, 0);		//expected double, actual double, delta double.
    	
    	
    }

    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        //TODO: implement this method
    }

}

