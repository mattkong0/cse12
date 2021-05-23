// These are some imports that the course staff found useful, feel free to use them
// along with other imports from java.util.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    public static String isValidPartitionResult(String[] before, 
    											int low, 
    											int high, 
    											int pivot, 
    											String[] after) {
    	
    	if (after.length == 0 && before.length == 0) {// both arrays have nothing
			return null;
		}
    	else if (after.length != before.length) {// different lengths
    		return "The after array does not have the same length as the before array.";
    	}
    	
    	// before and after have the same length and are non-zero
    	// invalid conditions for low, high, and pivot
    	if (low > high) {
    		return "Low is bigger than high.";
    	}
    	else if (pivot > high || pivot < low) {
    		return "Invalid pivot.";
    	}
    	
    	// low < pivot < high
    	for (int i = low; i < pivot; i++) {
    		if (Integer.parseInt(after[i]) >= Integer.parseInt(after[pivot])) {
    			return "Element before pivot is bigger.";
    		}
    	}
    	for (int i = pivot + 1; i < after.length; i++) {
    		if (Integer.parseInt(after[i]) <= Integer.parseInt(after[pivot])) {
    			return "Element after pivot is smaller.";
    		}
    	}
    	for (int i = 0; i < after.length; i++) {// check before and after have same elements
    		if(!after[i].contains(before[i])) {// elements in after are different than before
    			return "Not all elements are present.";
    		}
    		if(Integer.parseInt(after[i]) > Integer.parseInt(after[i + 1])) {
    			return "Elements are in reverse order.";
    		}
    	}
    	
    	return null; //array is properly sorted and all elements are present
    }

    public static String[] generateInput(int n) {
    	String[] newList = new String[n];
    	for (int i = 0; i < newList.length; i++) {
    		newList[i] = Integer.toString(i);
    	}
        return newList;
    }

    @SuppressWarnings("static-access")
	public static CounterExample findCounterExample(Partitioner p) {
    	Random rand = new Random();
    	int ilength = rand.nextInt(11);
    	PartitionOracle p1 = new PartitionOracle();
    	String[] result = p1.generateInput(ilength);
    	String[] original = Arrays.copyOf(result, ilength);
    	int low = rand.nextInt(11);
    	int high = rand.nextInt(11);
    	int pivot;
    	String validResult;
    	if (ilength == 0) {// length is 0
    		pivot = p.partition(result, 0, 0);
    		validResult = p1.isValidPartitionResult(original, 0, 0, pivot, result);
    	}
    	else {// length is not 0
    		pivot = p.partition(result, low, high);
    		validResult = p1.isValidPartitionResult(original, low, high, pivot, result);
    	}
    	
    	// after array is sorted
    	if (validResult == null) {// sorted correctly
    		return null;
    	}
    	else {// not sorted correctly
    		CounterExample ce = new CounterExample(original, low, high, 
    											   pivot, result, validResult);
    		return ce;
    	}
    }

}
