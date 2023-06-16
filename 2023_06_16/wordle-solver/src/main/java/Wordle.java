import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Wordle {

	public static final List<String> VALID_GUESSES = readLines("valid-guesses.txt");
	public static final List<String> POSSIBLE_ANSWERS = readLines("possible-answers.txt");

	private final String answer;
	private int guesses = 0;

	public Wordle() {
		answer = POSSIBLE_ANSWERS.get(new Random().nextInt(POSSIBLE_ANSWERS.size()));
	}

	public Wordle(String answer) {
		this.answer = answer;
	}

	private static List<String> readLines(String file) {
		try {
			URL resource = Wordle.class.getResource(file);
			List<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(resource.toURI())));
			Collections.shuffle(lines);
			return lines;
		} catch (Exception e) {
			throw new RuntimeException("Failed to load " + file, e);
		}
	}

	public Result guess(String word) {
		guesses++;
		if (!VALID_GUESSES.contains(word)) {
			throw new IllegalArgumentException("Invalid guess: " + word);
		}

		Map<Character, Integer> letterCounts = new HashMap<>();
		answer.chars().forEach(letter -> {
			int count = letterCounts.computeIfAbsent((char) letter, c -> 0);
			letterCounts.put((char) letter, count + 1);
		});

		Result result = new Result(word);

		for (int i = 0; i < 5; i++) {
			char letter = word.charAt(i);
			if (answer.contains(String.valueOf(letter)) && letterCounts.get(letter) > 0) {
				result.setLetterContained(i, true);
				letterCounts.put(letter, letterCounts.get(letter) - 1);
			}
			if (answer.charAt(i) == letter) {
				result.setLetterCorrect(i, true);
			}
		}

		return result;
	}

	public int getGuesses() {
		return guesses;
	}

}
