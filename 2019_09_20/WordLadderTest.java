import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordLadderTest {

	private List<String> wordLadder;
	private List<String> wordList;

	@BeforeEach
	public void setup() throws IOException {
		this.wordLadder = Arrays.asList("COLD", "CORD", "CARD", "WARD", "WARM");
		this.wordList = Files.readAllLines(Paths.get("dict.txt"));
	}

	@Test
	public void testValidWords() {
		wordLadder.forEach(w -> assertTrue(wordList.contains(w)));
	}

	@Test
	public void testOnlyOneLetterDifference() {
		for (int i = 0; i < wordLadder.size() - 1; i++) {
			checkPairValid(wordLadder.get(i), wordLadder.get(i + 1));
		}
	}

	private static void checkPairValid(String word1, String word2) {
		assertTrue(word1.length() == word2.length());

		int differences = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				differences += 1;
			}
		}

		assertEquals(1, differences);
	}
}
