public class ArrayGL<E> implements MyList<E> {

    E[] elements;
    int size;

    public ArrayGL(E[] initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    @Override
    public E[] toArray() {
    	E[] array = (E[]) new Object[this.size];
    	
    	for(int i = 0; i < this.size; i++) {
    		array[i] = this.elements[i];
    	}
    	this.elements = array;
    	
    	return array;
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
    	E[] contents = this.elements;
    	for (int i = 0; i < this.size; i++) {
    		if (contents[i] != null) {
    			contents[i] = (E)mt.transformElement(contents[i]);
    		}
    		else if (contents[i] == null) {
    			return;
    		}
    	}
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void chooseAll(MyChooser mc) {
    	int chosenCount = 0;
    	
    	for (int i = 0; i < this.size; i++) {
    		if (elements[i] != null && mc.chooseElement(this.elements[i])) {
    			chosenCount++;
    		}
    	}
    	
    	E[] newChosenArray = (E[]) new Object[chosenCount];// for elements that were chosen
    	int indexPicked = 0;
    	for (int j = 0; j < elements.length; j++) {
    		if (elements[j] != null && mc.chooseElement(this.elements[j])) {
    			newChosenArray[indexPicked] = elements[j];
    			indexPicked++;
    		}
    		else if (elements[j] == null) {
    			continue;
    		}
    	}
    	elements = newChosenArray;
    	size = chosenCount; //update size to total amount of chosen elements
    }
    
}