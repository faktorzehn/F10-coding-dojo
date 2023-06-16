
public class Wordle {

	public static void main(String[] args) {
		System.out.println(validate("cards"));
	}	
	
	private static Result validate(String word) {
		String expected = "crane";
		
		Result result = new Result(word);
		for(int i = 0; i < 5; i++) {
			if(expected.contains(String.valueOf(word.charAt(i)))) {
				result.setLetterContained(i, true);
			}
			if(expected.charAt(i) == word.charAt(i)) {
				result.setLetterCorrect(i, true);
			}
		}
		return result;
	}
}
