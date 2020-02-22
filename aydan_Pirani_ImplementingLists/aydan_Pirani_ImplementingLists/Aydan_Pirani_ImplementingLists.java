/*
 * Name: Aydan Pirani
 * Assignment: Implementing Lists
 * Assignment Desc: Make a program that makes a LinkedList and 
 */
package aydan_Pirani_ImplementingLists;

public class Aydan_Pirani_ImplementingLists {

	public static void main(String[] args) {
		String[] colleges = {"UCB", "UCLA", "UCSB", "UCSD", "UC Irvine", "UCSC", "UCR", 
				"UC Merced", "UCSF", "UC Davis"};
		MyLinkedList<String> testingList = new MyLinkedList<>();
		for (int i = 0; i < 10; i++) {
			testingList.add(colleges[i]);
		}

		//toString
		System.out.println("Initial List: " + testingList.toString());

		//add
		testingList.add("UCLA");
		System.out.println("List after adding UCLA: " + testingList.toString());

		//add(index)
		testingList.add(2, "UCSD");
		System.out.println("List after adding UCSD at 2: "+ testingList.toString());

		//addFirst
		testingList.addFirst("UCR");
		System.out.println("List after adding UCR to front: " + testingList.toString());

		//addLast
		testingList.addLast("UCB");
		System.out.println("List after adding UCB to back: " + testingList.toString());

		//removeFirst
		testingList.removeFirst();
		System.out.println("List after removing first term:" + testingList.toString());

		//removeLast
		testingList.removeLast();
		System.out.println("List after removing last term: " + testingList.toString());

		//remove(index)
		Object var= testingList.remove(9);
		System.out.println("List after removing index 9(" + var + "): "+ testingList.toString());

		//remove

		//getFirst
		System.out.println("List's new first element: " + testingList.getFirst());

		//getLast
		System.out.println("List's new last element: " + testingList.getLast());

		//get
		System.out.println("College at Index 4: " + testingList.get(4));

		//size
		System.out.println("List size: " + testingList.size());

		//indexOf
		System.out.println("Index of UCR: " + testingList.indexOf("UCR"));

		//lastIndexOf
		System.out.println("Last Index of UCLA:" + testingList.lastIndexOf("UCLA"));

		//contains
		System.out.println("List contains EVC: " + testingList.contains("EVC"));

		//clear
		testingList.clear();
		System.out.println("List after clearing: " + testingList.toString());

		//isEmpty
		System.out.println("List is empty: " + testingList.isEmpty());

	}

}


class MyLinkedList<E> extends MyAbstractList<E> {
	private Node<E> head, tail;

	/** Create a default list */
	public MyLinkedList() {
	}

	/** Create a list from an array of objects */
	public MyLinkedList(E[] objects) {
		super(objects);
	}

	/** Return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		}
		else {
			return head.element;
		}
	}

	/** Return the last element in the list */
	public E getLast() {
		if (size == 0) {
			return null;
		}
		else {
			return tail.element;
		}
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new node
		newNode.next = head; // link the new node with the head
		head = newNode; // head points to the new node
		size++; // Increase list size

		if (tail == null) // the new node is the only node in list
			tail = head;
	}

	/** Add an element to the end of the list */
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new for element e

		if (tail == null) {
			head = tail = newNode; // The new node is the only node in list
		}
		else {
			tail.next = newNode; // Link the new with the last node
			tail = tail.next; // tail now points to the last node
		}

		size++; // Increase size
	}


	@Override /** Add a new element at the specified index 
	 * in this list. The index of the head element is 0 */
	public void add(int index, E e) {
		if (index == 0) {
			addFirst(e);
		}
		else if (index >= size) {
			addLast(e);
		}
		else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			(current.next).next = temp;
			size++;
		}
	}

	/** Remove the head node and
	 *  return the object that is contained in the removed node. */
	public E removeFirst() {
		if (size == 0) {
			return null;
		}
		else {
			Node<E> temp = head;
			head = head.next;
			size--;
			if (head == null) {
				tail = null;
			}
			return temp.element;
		}
	}

	/** Remove the last node and
	 * return the object that is contained in the removed node. */
	public E removeLast() {
		if (size == 0) {
			return null;
		}
		else if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		}
		else {
			Node<E> current = head;

			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}

			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	@Override /** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public E remove(int index) {   
		if (index < 0 || index >= size) {
			return null;
		}
		else if (index == 0) {
			return removeFirst();
		}
		else if (index == size - 1) {
			return removeLast();
		}
		else {
			Node<E> previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}

			Node<E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}

	@Override /** Override toString() to return elements in the list */
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", "); // Separate two elements with a comma
			}
		}
		result.append("]"); // Insert the closing ] in the string
		return result.toString();
	}

	@Override /** Clear the list */
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override /** Return true if this list contains the element e */
	public boolean contains(E e) {

		for (int j = 0; j < size-1; j++) {
			if (head.next.element.equals(e)) {
				return true;
			}
			head = head.next;
		}
		return false;
	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		Node <E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;
	}

	@Override /** Return the index of the head matching element in 
	 *  this list. Return -1 if no match. */
	public int indexOf(E e) {
		for (int i = 0; i < size; i++) {
			if (get(i) == e) {
				return i;
			}
		}
		return -1;
	}

	@Override /** Return the index of the last matching element in 
	 *  this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		int index = 0;
		int finalIndex = -1;
		Node<E> temp = head;
		do {
			if (temp.element == e) {
				finalIndex = index;
			}
			index++;
			temp = temp.next;
		} while (index < size);
		return finalIndex;
	}

	@Override /** Replace the element at the specified position 
	 *  in this list with the specified element. */
	public E set(int index, E e) {
		Node<E> returnNode;
		for (int i = 0; i < index; i++) {
			head= head.next;
		}
		returnNode = head;
		head.element = e;
		return returnNode.element;
	}

	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException
			("Index: " + index + ", Size: " + size);
	}

	private class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head; // Current index 

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}

		@Override
		public void remove() {
			System.out.println("Implementation left as an exercise");
		}
	}

	private static class Node<E> {
		E element;
		Node<E> next;

		public Node(E element) {
			this.element = element;
		}
	}
}


abstract class MyAbstractList<E> implements MyList<E> {
	protected int size = 0; // The size of the list

	/** Create a default list */
	protected MyAbstractList() {
	}

	/** Create a list from an array of objects */
	protected MyAbstractList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	@Override /** Add a new element at the end of this list */
	public void add(E e) {
		add(size, e);
	}

	@Override /** Return true if this list contains no elements */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override /** Return the number of elements in this list */
	public int size() {
		return size;
	}

	@Override /** Remove the first occurrence of the element e 
	 *  from this list. Shift any subsequent elements to the left.
	 *  Return true if the element is removed. */
	public boolean remove(E e) {
		if (indexOf(e) >= 0) {
			remove(indexOf(e));
			return true;
		}
		else
			return false;
	}
}


interface MyList<E> extends java.lang.Iterable<E> {
	/** Add a new element at the end of this list */
	public void add(E e);

	/** Add a new element at the specified index in this list */
	public void add(int index, E e);

	/** Clear the list */
	public void clear();

	/** Return true if this list contains the element */
	public boolean contains(E e);

	/** Return the element from this list at the specified index */
	public E get(int index);

	/** Return the index of the first matching element in this list.
	 *  Return -1 if no match. */
	public int indexOf(E e);

	/** Return true if this list contains no elements */
	public boolean isEmpty();

	/** Return the index of the last matching element in this list
	 *  Return -1 if no match. */
	public int lastIndexOf(E e);

	/** Remove the first occurrence of the element o from this list.
	 *  Shift any subsequent elements to the left.
	 *  Return true if the element is removed. */
	public boolean remove(E e);

	/** Remove the element at the specified position in this list
	 *  Shift any subsequent elements to the left.
	 *  Return the element that was removed from the list. */
	public E remove(int index);

	/** Replace the element at the specified position in this list
	 *  with the specified element and returns the new set. */
	public Object set(int index, E e);

	/** Return the number of elements in this list */
	public int size();
}