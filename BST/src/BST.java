/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author TODO
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int N;             // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	/**
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key) { return get(root, key); }

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}





	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Theta(3n) 
	 * I chose Theta(3n) as my height function contains three if statements thus the worst case running time of this function would by Theta(n)
	 * as the code will run through each if statement once and moving on if the if statement does not fit the criteria. In my if statement I make
	 * reference to two outside functions size() and maxHeight(), therefore to calculate the amortised running time we must take these two into
	 * consideration. Again, looking at the size() function it contains a singular if statement, therefore making the running time of this function also Theta(n).
	 * Finally, looking at the maxHeight() function we again have two if statements present, therefore the running time of this function being Theta(n).
	 * To calculate the amortised running time which is the running time across all of the functions, we add the worst case running times of each of the functions.
	 * Thus we add Theta(n) + Theta(n) + Theta(n) and we get Theta(3n) as our answer.
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */
	public int height() {
		//max height of a binary search tree is n-1
		if(root == null) {
			return -1;
		} else if(size(root) == 1) {
			return 0;
		} else {
			return maxHeight(root);
		}

	}
	
	private int maxHeight(Node root) {

		if(root == null) {
			return -1;
		} else {
			int leftH = maxHeight(root.left);
			int rightH = maxHeight(root.right);

			if(leftH > rightH)
				return (leftH + 1);
			else 
				return (rightH + 1);
		}
	}

	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	//TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.
	public Key median() {

		if (isEmpty()) {
			return null;
		} else {
			return calcMedian(root, (root.N + 1)/2);
		}

	}

	private Key calcMedian(Node root, int N) {
		int count = size(root.left) +1;

		if(count == N) {
			return root.key;

		} else if(count > N) {
			return calcMedian(root.left, N);
		}
		return calcMedian(root.right, N - count);

	}


	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		if (isEmpty()) {
			return "()";
		} else {
			return calcKeysInOrder(root);
		}
	}


	private String calcKeysInOrder(Node root) {

		if(root == null) {
			return "()";

		} else {
			return("(" + calcKeysInOrder(root.left) + root.key + calcKeysInOrder(root.right) + ")");
		}
	}





	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {
		return printingKeys(root, "");
	}


	private String printingKeys(Node root, String pre) {

		if(root == null) {
			return pre + "-null\n";
		}
		return (pre
				+ "-"
				+ root.key
				+ "\n"
				+ printingKeys(root.left, pre + " |")
				+ printingKeys(root.right, pre + "  "));

	}
	/**
	 * Deteles a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */
	public void delete(Key key) {
		root = deleteNode(root, key);

	}

	private Node deleteNode(Node root, Key key) {
		if(root == null) {
			return null;
		}

		int cmp = key.compareTo(root.key);
		if(cmp > 0) {
			root.right = deleteNode(root.right, key);

		} else if(cmp < 0) {
			root.left = deleteNode(root.left, key);
			
		}else {
			if(root.left == null) {
				return root.right;
			}
			else if(root.right == null) {
				return root.left;
			}

			Node newNode = root;
			root = end(newNode.left);
			root.left = deletion(newNode.left);
			root.right = newNode.right;
		}

		root.N = size(root.left) + size(root.right) + 1;
		return root;

	}

	private Node deletion(Node root) {
		
		return deleteNode(root, end(root).key);

	}

	private Node end(Node root) {
		
		if(root.right == null) {
			return root;
		} else {
			return end(root.right);
		}
	}

}