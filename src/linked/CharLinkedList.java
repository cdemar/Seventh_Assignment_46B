package linked;

import java.util.*;

public class CharLinkedList {
	private CharNode head; // Empty if head and
	private CharNode tail; // tail are null

	public CharLinkedList() {
	}

	public CharLinkedList(String s) {
		for (int i = s.length() - 1; i >= 0; i--)
			insertAtHead(s.charAt(i));
	}

	public void insertAtHead(char ch) {
		assert hasIntegrity(); // Precondition

		CharNode node = new CharNode(ch);
		node.setNext(head);
		head = node;
		if (tail == null)
			tail = node; // Corner case: inserting into empty node

		assert hasIntegrity(); // Postcondition
	}

	public String toString() {
		String s = "";
		CharNode node = head;
		while (node != null) {
			s += node.getData();
			node = node.getNext();
		}
		return s;
	}

	// start of my work returns the first node (closest to the head) that is
	// equal to ch if there is no such node, then it returns null
	public CharNode find(char ch) {

		// set it to the head because thats where we want to start
		CharNode c = head;

		while (c != null) {
			// sop("hi");

			if (c.getData() == ch) {
				sop("yes");
				return c;
			}

			c = c.getNext();
		}

		return null;

	}

	public void duplicate(char ch) {

		assert hasIntegrity();

		CharNode orig = find(ch);

		if (orig == head) {
			// if it's at the head we already have a method that does this
			sop("head");
			insertAtHead(ch);
			return; // we're done
		}

		if (orig == null) { // if there's nothing there
			return; // we're done we have nothing to do
		}

		CharNode dupe = new CharNode(ch); // create a new char node
		CharNode next1 = orig.getNext(); // the next node in the string

		if (orig == tail) { // if you're at the tail
			justBeforeTail(ch); // go through this method
			assert hasIntegrity(); // see if it still has integrity
			return; // get out of the method because you're done with it
		}

		// if it's not the tail
		orig.setNext(dupe); // insert the duplicate node after the
		dupe.setNext(next1); // if there was something after it, set the next
								// element after dupe to that

		assert hasIntegrity();

	}

	// adding a size method -- never actually used but tried to at one point
	public int size() {
		int n = 0;
		CharNode charnode = head;

		while (charnode != null) {
			n++;
			charnode = charnode.getNext();
		}
		return n; // returns the integer size of the linked list

	}

	// adding a just before tail method
	public void justBeforeTail(char ch) {
		CharNode n = new CharNode(ch); // creates the new node
		CharNode n1 = head; // creates this node to be the head and uses this to
							// traverse through the list

		while (n1.getNext() != tail) { // while the next node after is not the
										// tail
			n1 = n1.getNext(); // move on
		}

		n1.setNext(n); // if it is then set the next node to the dupe
		n.setNext(tail); // and then set the dupe's next to the tail
	}

	//
	// Returns true if this list has emptiness integrity, has tail integrity,
	// has no loops, and tail is reachable from head.
	//
	// Caution: this checks for most but not all common integrity problems.
	//
	boolean hasIntegrity() {
		// Check emptiness. If either head or tail is null, the other must also
		// be null. Different logic from what you saw in lecture. Returns
		// immediately if this list is empty.
		if (head == null || tail == null) {
			sop("head tail error"); // i added
			return head == null && tail == null;
		}

		// Check tail integrity (tail.next must be null).
		if (tail.getNext() != null) {
			sop("tail.getnext error"); // i added
			return false;
		}

		// Check for loops.
		Set<CharNode> visitedNodes = new HashSet<>();
		CharNode node = head;
		while (node != null) {
			if (visitedNodes.contains(node)) {
				sop("contains error");
				return false; // Current node has been visited before, we must
								// have a loop
			}
			visitedNodes.add(node); // First visit to this node
			node = node.getNext();
		}

		// Make sure tail is reachable from head.
		node = head;
		while (node != null && node != tail)
			node = node.getNext();
		return node == tail;
	}

	static void sop(Object x) {
		System.out.println(x);
	}

}
