import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is an example of how to implement the Partitioner interface to implement
 * a concrete Partitioner. You can use this bad implementation to test your PartitionOracle,
 * to ensure that it works in detecting a bad Partitioner. You should add a correct implementation
 * of a Partitioner here, maybe one from class, to verify that your PartitionOracle also works
 * correctly on good implementations. Once you implement part 2, you can also test those Partitioner
 * implementations here as well.
 * 
 */
class CopyFirstElementPartition implements Partitioner {
    public int partition(String[] strs, int low, int high) {
        if (high - low < 1)
            return 0;
        for (int i = 0; i < strs.length; i += 1) {
            strs[i] = strs[0];
        }
        return 0;
    }
}

public class TestPartitionOracle {
    @Test
    public void testCopyFirstElementPartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
        System.out.println(ce);
        assertNotNull(ce);
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void validPartitionResults() {
    	PartitionOracle simplePartition = new PartitionOracle();
    	String[] o1 = {"1", "2", "3", "4", "5"};
    	String[] r1 = {"1", "2", "3", "4", "5"};
    	assertEquals(null, simplePartition.isValidPartitionResult(o1, 0, o1.length-1, 2, r1));
    	
    	PartitionOracle simplePartition2 = new PartitionOracle();
    	String[] o2 = {"1", "3", "2", "4", "5"};
    	String[] r2 = {"1", "2", "3", "4", "5"};
    	assertEquals(null, simplePartition2.isValidPartitionResult(o2, 0, o2.length, 2, r2));
    }
    
    @Test
    public void invalidPartitionResults() {
    	PartitionOracle p1 = new PartitionOracle();
    	String[] o3 = {"243", "3", "27", "9", "81", "1024"};
    	String[] r3 = {"3", "9", "243", "27", "81"};
    	assertEquals("The after array does not have the same length as the before array.", 
    			     p1.isValidPartitionResult(o3, 0, o3.length-1, 2, r3));
    	
    	PartitionOracle p2 = new PartitionOracle();
    	String[] o4 = {"32", "16", "4", "8", "40"};
    	String[] r4 = {"4", "40", "16", "32", "8"};
    	assertEquals("Element before pivot is bigger.", p2.isValidPartitionResult(o4, 0, o3.length-1, 2, r4));
    }
    
}


