
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection; 
import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestLists {

	public static Collection<Object[]> LISTNUMS =
			Arrays.asList(new Object[][] { {"Linked"}, {"Array"} });
	private String listType;

	public TestLists(String listType) {
		super();
		this.listType = listType;
	}

	@Parameterized.Parameters(name = "{0}List")
	public static Collection<Object[]> bags() {
		return LISTNUMS;
	}

	private <E> MyList<E> makeList(E[] contents) {
		switch (this.listType) {
		case "Linked":
			return new LinkedGL<E>(contents);
		case "Array":
			return new ArrayGL<E>(contents);
		}
		return null;
	}

  // Don't change code above this line, it ensures the autograder works as
  // expected


  // This is a sample test; you can keep it, change it, or remove it as you like.
  // Note that it uses the method `assertArrayEquals`, which you should use to
  // test equality of arrays in this PA.
	@Test
	public void testSimpleToArray() {
		// Using the generic list to create an Integer list
		Integer[] int_input = {1, 2, 3};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(int_input, int_s.toArray());
		
		// Using the generic list to create a String list
		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(string_input, string_s.toArray());
	}
	
	@Test
	public void testEmptyArray() {
		String[] input = {};
		MyList<String> in = makeList(input);
		assertArrayEquals(input, in.toArray());
	}
	
	@Test
	public void transformAll() {
		Integer[] int_input2 = {1, 3, 5, 7};
		MyList<Integer> int_s = makeList(int_input2);
		int_s.transformAll(new DoubleTransformer());
		Integer[] expectedArray = {2, 6, 10, 14};
		assertArrayEquals(expectedArray, int_s.toArray());
		
		Boolean[] boolean_input = {true, false, true};
		MyList<Boolean> boolean_b = makeList(boolean_input);
		boolean_b.transformAll(new BooleanTransformer());
		Boolean[] expectedArray2 = {false, true, false};
		assertArrayEquals(expectedArray2, boolean_b.toArray());
		
		String[] string_input2 = {"ab", "bc", "cd"};
		MyList<String> string_s2 = makeList(string_input2);
		string_s2.transformAll(new UpperCaseTransformer());
		String[] expectedArray3 = {"AB", "BC", "CD"};
		assertArrayEquals(expectedArray3, string_s2.toArray());
	}

	@Test
	public void twoElementArray() {
		Integer[] int_input3 = {8, 3};
		MyList<Integer> int_s2 = makeList(int_input3);
		int_s2.chooseAll(new OddNumberChooser());
		Integer[] expectedArray4 = {3};
		assertArrayEquals(expectedArray4, int_s2.toArray());
		
		String[] string_input3 = {"super", "banana"};
		MyList<String> string_s3 = makeList(string_input3);
		string_s3.chooseAll(new LongWordChooser());
		String[] expectedArray5 = {"banana"};
		assertArrayEquals(expectedArray5, string_s3.toArray());
		
		Integer[] int_input4 = {4, 12};
		MyList<Integer> int_s3 = makeList(int_input4);
		int_s3.chooseAll(new MultipleofThreeChooser());
		Integer[] expectedArray6 = {12};
		assertArrayEquals(expectedArray6, int_s3.toArray());
	}
	
	@Test 
	public void chooseFirstAndLast() {
		Integer[] int_input5 = {1, 2, 4, 6, 8, 11};
		MyList<Integer> int_s4 = makeList(int_input5);
		int_s4.chooseAll(new OddNumberChooser());
		Integer[] expectedArray7 = {1, 11};
		assertArrayEquals(expectedArray7, int_s4.toArray());
	}
	
	@Test
	public void nullArray() {
		String[] string_input4 = {null, null, null};
		MyList<String> string_s4 = makeList(string_input4);
		string_s4.transformAll(new DoubleTransformer());
		String[] expectedArray8 = {null, null, null};
		assertArrayEquals(expectedArray8, string_s4.toArray());
	}

}