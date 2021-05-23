
class UpperCaseTransformer implements MyTransformer<String> {

	public String transformElement(String s) {
		return s.toUpperCase();
	}

}

class DoubleTransformer implements MyTransformer<Integer> {
	
	public Integer transformElement(Integer i) {
		return i * 2;
	}
}

class BooleanTransformer implements MyTransformer<Boolean> {
	
	public Boolean transformElement(Boolean b) {
		if (b == true) {
			return false;
		}
		return true;
	}
}

