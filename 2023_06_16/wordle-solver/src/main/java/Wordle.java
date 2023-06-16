import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Wordle {

	public static final List<String> VALID_GUESSES = readLines("valid-guesses.txt");
	public static final List<String> POSSIBLE_ANSWERS = readLines("possible-answers.txt");
	
	private final String answer;
	private int guesses = 0;
	
	public Wordle() {
		answer = POSSIBLE_ANSWERS.get(new Random().nextInt(POSSIBLE_ANSWERS.size()));
	}
	
	private static List<String> readLines(String file) {
		try {
			URL resource = Wordle.class.getResource(file);
			return Files.readAllLines(Paths.get(resource.toURI()));
		} catch (Exception e) {
			throw new RuntimeException("Failed to load " + file, e);
		}
	}
	
	public Result guess(String word) {
		guesses++;
		if(!POSSIBLE_ANSWERS.contains(word)) {
			throw new IllegalArgumentException("Invalid word: " + word);
		}
		
		Result result = new Result(word);
		for(int i = 0; i < 5; i++) {
			if(answer.contains(String.valueOf(word.charAt(i)))) {
				result.setLetterContained(i, true);
			}
			if(answer.charAt(i) == word.charAt(i)) {
				result.setLetterCorrect(i, true);
			}
		}
		return result;
	}
	
	public int getGuesses() {
		return guesses;
	}
	
}
