public class Result {

	private boolean[] letterContained = new boolean[5];
	private boolean[] letterCorrect = new boolean[5];

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	private static final String ANSI_YELLOW_BACKGROUND = "\u001B[103m";

	private final String guess;

	public Result(String guess) {
		this.guess = guess;
	}

	public boolean isLetterContained(int i) {
		checkValidIndex(i);
		return letterContained[i];
	}

	public void setLetterContained(int i, boolean contained) {
		checkValidIndex(i);
		letterContained[i] = contained;
	}

	public boolean isLetterCorrect(int i) {
		checkValidIndex(i);
		return letterCorrect[i];
	}

	public void setLetterCorrect(int i, boolean correct) {
		checkValidIndex(i);
		letterCorrect[i] = correct;
	}

	private void checkValidIndex(int i) {
		if (i < 0 || i > 4) {
			throw new IllegalArgumentException("Invalid index: " + i);
		}
	}

	public boolean isCorrect() {
		for (boolean value : letterCorrect) {
			if (!value) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			if (isLetterCorrect(i)) {
				builder.append(ANSI_GREEN_BACKGROUND);
			} else if (isLetterContained(i)) {
				builder.append(ANSI_YELLOW_BACKGROUND);
			} else {
				builder.append(ANSI_RESET);
			}
			builder.append(guess.charAt(i));
		}
		builder.append(ANSI_RESET);

		return builder.toString();

	}
}
