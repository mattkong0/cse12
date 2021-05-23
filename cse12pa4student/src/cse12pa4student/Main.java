package cse12pa4student;
import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		List<Measurement> everything = Measure.measure(new String[]{"A", "B", "C", "D", "E", "F"}, 1, 20);
		String toCSV = Measure.measurementsToCSV(everything);
		System.out.println(toCSV);
		
		System.out.println();
	}
}
