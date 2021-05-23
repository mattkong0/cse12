package cse12pa4student;

import java.util.*;
import static cse12pa4mysteries.Mysteries.*;

public class Measure {


	public static List<Measurement> measure(String[] toRun, int startN, int stopN) {
		/** TODO **/
		List<Measurement> measurements = new ArrayList<Measurement>();
		for (int i = 0; i < toRun.length; i++) {
			for (int j = startN; j <= stopN; j++) {
				if (toRun[i].equals("A")) {
					long startTime = System.nanoTime();
					mysteryA(j);
					long estimatedTime = System.nanoTime() - startTime;
					measurements.add(new Measurement(toRun[i], j, estimatedTime));
				}
				else if (toRun[i].equals("B")) {
					long startTime = System.nanoTime();
					mysteryB(j);
					long estimatedTime = System.nanoTime() - startTime;
					measurements.add(new Measurement(toRun[i], j, estimatedTime));
				}
				else if (toRun[i].equals("C")) {
					long startTime = System.nanoTime();
					mysteryC(j);
					long estimatedTime = System.nanoTime() - startTime;
					measurements.add(new Measurement(toRun[i], j, estimatedTime));
				}
				else if (toRun[i].equals("D")) {
					long startTime = System.nanoTime();
					mysteryD(j);
					long estimatedTime = System.nanoTime() - startTime;
					measurements.add(new Measurement(toRun[i], j, estimatedTime));
				}
				else if (toRun[i].equals("E")) {
					long startTime = System.nanoTime();
					mysteryE(j);
					long estimatedTime = System.nanoTime() - startTime;
					measurements.add(new Measurement(toRun[i], j, estimatedTime));
				}
				else if (toRun[i].equals("F")) {
					long startTime = System.nanoTime();
					mysteryF(j);
					long estimatedTime = System.nanoTime() - startTime;
					measurements.add(new Measurement(toRun[i], j, estimatedTime));
				}
			}
		}
		return measurements;
		
//		//Example call to mystery method
//		mysteryA(50);
//		return null;
	}
	

	public static String measurementsToCSV(List<Measurement> toConvert) {
		String toCSV = "name, valueOfN, nanoseconds";
		for (int i = 0; i < toConvert.size(); i++) {
			toCSV = toCSV + "\n" + toConvert.get(i).name + ", " + toConvert.get(i).valueOfN + ", " 
					+ toConvert.get(i).nanosecondsToRun;
		}
		return toCSV;
	}
		
}
