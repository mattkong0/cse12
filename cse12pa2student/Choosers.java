
class LongWordChooser implements MyChooser<String> {

	@Override
	public boolean chooseElement(String s) {
		return s.length() > 5;
	}

} 

class OddNumberChooser implements MyChooser<Integer> {
	
	@Override
	public boolean chooseElement(Integer i) {
		if (i % 2 != 0) {
			return true;
		}
		return false;
	}
}

class MultipleofThreeChooser implements MyChooser<Integer> {
	
	@Override
	public boolean chooseElement(Integer s) {
		if (s % 3 == 0) {
			return true;
		}
		return false;
	}
}
