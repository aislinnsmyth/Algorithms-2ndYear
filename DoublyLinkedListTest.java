import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @AislinnSmyth 
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new DoublyLinkedList<Integer>();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
		@Test //works
	public void testInsertBefore()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);

		testDLL.insertBefore(0,4);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString());
		testDLL.insertBefore(1,5);
		assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
		testDLL.insertBefore(2,6);       
		assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(-1,7);        
		assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(7,8);        
		assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
		testDLL.insertBefore(700,9);        
		assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);        
		assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10,1);        
		assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10,1);        
		assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
	}

		@Test //works
	public void testIsEmpty() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		DoublyLinkedList<Integer> testNode = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(3, 8);
		testDLL.insertBefore(4, 10);
		
		boolean testIsEmpty = testDLL.isEmpty();
		assertFalse("Checking if isEmpty", testIsEmpty);
		
		boolean testIsEmpty2 = testNode.isEmpty();
		assertTrue("Checking if isEmpty", testIsEmpty2);
		
		
	}
	
	@Test  //works
	public void testGet() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		DoublyLinkedList<Integer> testNode = new DoublyLinkedList<Integer>();
		assertNull("Checking if get at pos 0 is null", testDLL.get(0));
		
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		
		int expectedResult = 3;
		assertEquals("Checking get to a list of 3 elements", expectedResult, (int) testDLL.get(2));
		int expectedResult1 = 2;
		assertEquals("Checking get to a list of 3 elements", expectedResult1, (int) testDLL.get(1));
		int expectedResult2 = 1;
		assertEquals("Checking get to a list of 3 elements", expectedResult2,(int) testDLL.get(0));
		String expectedResult3 = null;
		assertEquals("Checking get to a list of 3 elements,", expectedResult3, testDLL.get(-2));
		testNode.insertBefore(0, 1);
		String expectedResult4 = null;
		assertEquals("Checking get to a list of 1 element", expectedResult4, testNode.get(1));
		


	}
	
		@Test //not working
	public void testDeleteAt() {
			DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
			DoublyLinkedList<Integer> testNode = new DoublyLinkedList<Integer>();
			DoublyLinkedList<Integer> tNode = new DoublyLinkedList<Integer>();
			boolean testdeleteAt = testDLL.deleteAt(0);
			assertFalse("Checking if isEmpty", testdeleteAt);
			
			testDLL.insertBefore(0,1);
			testDLL.insertBefore(1,2);
			testDLL.insertBefore(2,3);

			boolean expectedResult = true;
			assertEquals("Checking deleteAt to a doubly linked list with 3 positions", expectedResult, testDLL.deleteAt(1));
			boolean expectedResult1 = false;
			assertEquals("checking deleteAt to a doubly linked list w 3 positions", expectedResult1, testDLL.deleteAt(4));
			testNode.insertBefore(0,2);
			assertEquals("Checking deleteAt at list w 1 element", expectedResult1, testNode.deleteAt(-2));
			
			//assertEquals("Checking deleteAt at list w 1 element", expectedResult, testNode.deleteAt(0));
			//assertEquals("Checking deleteAt at list w 1 element", expectedResult1, testNode.deleteAt(1));
			
		
	}

	
	
	 @Test //works
	public void testReverse() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(3, 8);
		testDLL.insertBefore(4, 10);

		testDLL.reverse();
		assertEquals("Checking reverse to a doubly linked list with 5 positions given ", "10,8,3,2,1", testDLL.toString());

	}
		@Test //works
	public void testMakeUnique() { 
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(3, 8);
		testDLL.insertBefore(4, 10);
		testDLL.insertBefore(5, 2);

		testDLL.makeUnique();
		assertEquals("Checking makeUnique to a list with 6 positions", "1,2,8,10,2", testDLL.toString());
	}
	
	
	@Test
	public void testPush() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);

		testDLL.push(4);
		assertEquals("Checking push to a list w 3 positions", "4,1,2,3", testDLL.toString());
	
		
		
	}
	@Test
	public void testPop() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.pop();
		assertEquals("Check on empty List", "", testDLL.toString());
		testDLL.insertBefore(0, 1);
		testDLL.pop();
		assertEquals("check pop 1 element", "1", testDLL.toString());
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.pop();
		assertEquals("Checking pop to a list w 3 elements", "2,3", testDLL.toString());

		
		
		
		
	}
	@Test
	public void testEnqueue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		
		testDLL.enqueue(2);
		assertEquals("Checking push to a list w 3 positions", "1,2,3,2", testDLL.toString());
		
		
		
	}
	@Test
	public void testDequeue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.dequeue();
		assertEquals("cCheck on empty List", "", testDLL.toString());
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(3,4);
		
		testDLL.dequeue();
		assertEquals("Checking dequeue to a list w 3 positions", "2,3,4", testDLL.toString());
		testDLL.dequeue();
		assertEquals("Checking dequeue to a list w 4 positions", "3,4", testDLL.toString());
		
	}
}

