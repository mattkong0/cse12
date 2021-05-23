import java.io.*;
import java.util.*;
/*
 * Class to implement SearchWorklist as a Stack and a Queue.
 * You can use any built-in Java collections for this class.
 */

@SuppressWarnings("unused")
class StackWorklist<E> implements SearchWorklist {
	Stack<E> stack;
	
	public StackWorklist() {
		this.stack = new Stack<E>();
	}
	
	//add element to top of the stack
	@SuppressWarnings("unchecked")
	public void add(Square c) {
		this.stack.add((E)c);
	}
	
	//remove element from the top of the stack
	public Square remove() {
		return (Square)this.stack.remove(this.stack.size() - 1);
	}
	
	//check if stack is empty
	public boolean isEmpty() {
		if (this.stack.size() == 0) {
			return true;
		}
		return false;
	}
}

class QueueWorklist<E> implements SearchWorklist {
	ArrayList<E> queue;
	
	public QueueWorklist() {
		this.queue = new ArrayList<E>();
	}
	
	//add element to back of the queue
	@SuppressWarnings("unchecked")
	public void add(Square c) {
		this.queue.add(this.queue.size(), (E)c);
	}
	
	//remove element from queue
	public Square remove() {
		Square removed = (Square)this.queue.get(0);
		this.queue.remove(0);
		return removed;
	}
	
	//check if queue is empty
	public boolean isEmpty() {
		if (this.queue.size() == 0) {
			return true;
		}
		return false;
	}
}

public interface SearchWorklist {
	void add(Square c);
	Square remove();
	boolean isEmpty();
}