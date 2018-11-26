public class CircularDoublyLinkedList
{
	
	private Node tail;
	private int size = 0;

	public CircularDoubly() {
		
			tail = new Node();
	}

	public void addHead(int value) {
			Node newest = new Node();
 			newest.value = value;
 			newest.next = tail.next;
 			tail.next = newest;
 			newest.prev = tail;
 			newest.next.prev = newest;
 			size += 1;
	}
	public void addTail(int value) {
		 if (size == 0){
				tail.value = value;
				tail.prev = tail;
				tail.next = tail;
				size += 1;
		 }
		 else{
				Node newest = new Node();
				newest.value = value;
		    newest.prev = tail;
		    newest.next = tail.next;
		    tail.next = newest;
		    newest.next.prev = newest;
		    tail = newest;
		    size += 1;
	 	 }
	}

	public Node getHead() {
		 if (size == 0)
		 		return null;
	 	 else
		 		return tail.next;
	}

	public Node getTail() {
		 if (size == 0)
				return null;
	   else
				return tail;
	}

	public void deleteHead() throws EmptyListException {
		 if (size == 0)
				throw new EmptyListException();
		 else{
				tail.next.next.prev = tail;
				tail.next = tail.next.next;
				size -= 1;
		 }
	}
	public void deleteTail() throws EmptyListException {
		 if (size == 0)
				throw new EmptyListException();
		 else{
			  if (size == 1) {
					tail = null;
			  }
			  else {
					tail.next.prev = tail.prev;
					tail.prev.next = tail.next;
					tail = tail.prev;
					size -= 1;
				}
		 }
	}

	public int size() {
			return size;
	}

	public boolean empty() {
		 if (size == 0)
				return true;
	   else
				return false;
	}

	public void clear() {
		/*
		 * Empty the list.
		 *
		 */
		 if (size > 1) {
				while (size != 1) {
						tail.next.next.prev = tail;
						tail.next = tail.next.next;
						size -= 1;
			  }
		}
		if (size == 1) {
				tail.next = null;
				tail.prev = null;
				size -= 1;
		}
	}

	public boolean find(int value) {
		 Node node = tail.next;
	   for (int i = 0; i < size; i++) {
			 if (value == node.value)
					return true;
			 node = node.next;
		}
		return false;
	}

	public void rotateForward() {
		 tail = tail.next;
	}

	public void rotateBackward() {
		 tail = tail.prev;
	}
}
