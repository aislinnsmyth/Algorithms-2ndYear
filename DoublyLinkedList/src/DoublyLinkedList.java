import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 * Test does not cover everything - 43%
 *  @AislinnSmyth 
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */

public class DoublyLinkedList<T extends Comparable<T>>{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;
		public int size;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;

		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;
	private int size;
	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
		size = 0;

	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  As there is a singular use of the if and else statements - it is just running through all the elements in constant time
	 */
	public boolean isEmpty()	//passes test
	{
		if(this.head == null) {
			return true; 
		} else
			return false;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: Theta(n)
	 *
	 * Justification:
	 * Theta(n) as there is a calling to an outside function sizeOfList(), therefore the running time of this function is now the same running time of the function called.
	 *  TODO
	 */


	public void insertBefore( int pos, T data ) //passes test  
	{
		DLLNode newNode = new DLLNode(data, null, null);
		DLLNode temp = head; 

		if(head == null || tail == null) {	// if head = null/ tail = null
			head = tail = newNode;

		} else if( pos <= 0) {				// if insert at head this function is correct
			newNode.prev = null;
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			return;

		} else if (pos >= sizeOfList(head)) {	//if insert at tail
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
			return;

		} else {								// if insert into the middle
			for(int i = 0; i < pos-1; i++) {
				temp = temp.next;
				if(temp == null) {
					return;
				}

			}
			newNode.next = temp.next;
			newNode.prev = temp;
			temp.next = newNode;
			temp.next.prev = newNode;

		}
	}

	// running time: Theta(n)
	
	// justifications: the use of a singular loop, the while loop as theta n is with any one loop in it that loops around once.

	public int sizeOfList(DLLNode nodeList) {	//no test created

		int lengthOfList = 0;
		while(nodeList != null) {
			lengthOfList++;
			nodeList = nodeList.next;
		}
		return lengthOfList;




	}
	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Theta(n)
	 *
	 * Justification:
	 *  if else statements present and also the use of a singular loop and there is an access point to another function, sizeOfList. So therefore it is the same running time
	 *
	 */
	public T get(int pos) 		//pass test
	{
		DLLNode current = head;
		int i = 0;

		if(head == null || tail == null) {
			return null;
		} else if(pos < 0 || pos >= sizeOfList(head)) {
			return null;
		} else {
			for(i = 0; i < pos; i++) {
				current = current.next;
			}
		}
		return current.data;
	}


	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: Theta(n)
	 *
	 * Justification:
	 *  Theta(n) is the running time as again the function has an access point to an outside function, sizeOfList().
	 */
	public boolean deleteAt(int pos) 	//passes test
	{


		DLLNode current = head;

		if(head == null) {
			return false;

		} else if(pos < 0 || pos > sizeOfList(head)) {
			return false;
		}
		if(pos == 0) {
			if(this.size == 1) {
				this.head = null;
				this.tail = null;
				return true;
			}
		}
		if(pos == 1) {
			if(head.next == null) {
				head.next.prev = null;
			}
			return true;
		} 
		while(current != null && pos >1) {
			current = current.next;
			pos--;
		} if(current == null) {
			return false;
		} if(current.next != null) {
			current.next.prev = current.prev;
		}
		current.prev.next = current.next;
		return true;
	}



	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  There is a singular while loop present in the code
	 */
	public void reverse()		//passes test
	{
		DLLNode temp = null;		// temporary Node
		DLLNode position = head;	// node of current position starting at head

		while(position != null) {
			temp = position.prev;
			position.prev = position.next;
			position.next = temp;
			position = position.prev;

		} if(temp != null) {
			head = temp.prev;
		}

	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements uniqueue.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost:  Theta(n^2)
	 * 
	 *
	 * Justification:
	 *  Theta(n^2) more than one loop, teo for loops and if, else statements present.
	 */
	public void makeUnique()		//works
	{

		DLLNode current, index, temp;

		if(head == null) {
			return;
		}
		else {

			for(current = head; current != null; current = current.next) {
				int i = 0;
				for(index = current.next; index != null; index = index.next) {  
					if(current.data == index.data) {  

						deleteAt(i);
					}
					i++;
				}
			}
		}
	}


	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  if statements are present so the running time is a linear running time, it is going through all of the elements
	 */
	public void push(T item) 
	{
		DLLNode newNode = new DLLNode(item, null, null);
		if(this.size == 0) {
			newNode.next = head;
			newNode.prev =null;
			if(head != null) {
				head.prev = newNode;
				head=newNode;
			}

		}

		this.size++;
	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(n)
	 *
	 * Justification:
	 *  There is an access point to the sizeOfList function so the running time will be the same
	 */
	public T pop() 
	{
		T data = null;
		if(isEmpty()) {
			return null;
		} 
		if(sizeOfList(head) == 1) {
			data = this.head.data;
			return data;
		}
		else {
			DLLNode nodeOne = this.head.next;
			data = this.head.data;
			this.head = nodeOne;
		}
		this.size--;
		return data;
		}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  Linear running time as only if stetements present, goes through all of the elements
	 */
	public void enqueue(T item) 
	{
		DLLNode queueNode = new DLLNode(item, null, null);

		if(head == null && tail == null) {
			head = queueNode;
			tail = queueNode;
			return;
		} else {
			tail.next = queueNode;
			tail = queueNode;
		}
	}

	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an enqueue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  Theta(1) as only if statements present soruns through all elements
	 */
	public T dequeue() 
	{
		T item;
		if(this.head == null) {
			return null;
		}
		else {
			item = this.head.data;
			this.head = this.head.next;
			this.size--;
			if(this.size ==0) {
				this.tail = null;
			}
			return item;
		}


	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}



}
