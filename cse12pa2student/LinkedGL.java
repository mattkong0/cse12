public class LinkedGL<E> implements MyList<E> {

    class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node front;
    int size;

	public LinkedGL(E[] contents) {
    	this.front = new Node(null, null);
    	this.size = 0;
    	Node currNode = this.front;
    	for (int i = 0; i < contents.length; i++) {
    		currNode.next = new Node(contents[i], null);
    		currNode = currNode.next;
    		this.size += 1;
    	}
    }

    @Override
    public E[] toArray() {
    	@SuppressWarnings("unchecked")
		E[] arrayCopy = (E[])new Object[this.size];
    	Node currNode = this.front;
    	for (int i = 0; i < this.size; i++) {
    		currNode = currNode.next;
    		arrayCopy[i] = currNode.value;
    	}
    	
    	return arrayCopy;
    }
    
    @Override
    public boolean isEmpty() {
    	if (this.size > 0) {
    		return false;
    	}
    	return true;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void transformAll(MyTransformer mt) {
    	Node currNode = this.front;
    	for (int i = 0; i < this.size; i++) {
    		currNode = currNode.next;
    		if (currNode.value != null) {
    			currNode.value = (E)mt.transformElement(currNode.value);
    		}
    		else if (currNode.next == null) {
    			return;
    		}
    	}
    }

    @SuppressWarnings("unchecked")
	@Override
    public void chooseAll(MyChooser mc) {
    	int chosenCount = 0;
    	Node currNode = this.front;
    	
    	currNode = currNode.next;
    	for (int i = 0; i < this.size; i++) {
    		if (currNode.value != null) {
    			if (mc.chooseElement(currNode.value)) {
    				chosenCount++;
        			currNode = currNode.next;
    			}
    			else if (!mc.chooseElement(currNode.value)){
        			currNode = currNode.next;
        		}
    		}
    	}
    	
    	currNode = this.front.next; // set currNode to zero index for new iteration
    	E[] newChosenArray = (E[])new Object[chosenCount];// for elements that were chosen
    	for (int j = 0; j < chosenCount; j++) {
    		for (int k = 0; k < this.size; k++) {
    			if (currNode.value != null) {
    				if (mc.chooseElement(currNode.value)) {
    					newChosenArray[k] = currNode.value;
        				currNode.value = null;
        				currNode = currNode.next;
        				break;
    				}
    				else if (!mc.chooseElement(currNode.value)){
        				currNode = currNode.next;
        			}
    				
    			}
    		}
    	}
    	
    	LinkedGL<E> newCopy = new LinkedGL<E>(newChosenArray);
    	this.front = newCopy.front;
    	this.size = newCopy.size;
    }

}