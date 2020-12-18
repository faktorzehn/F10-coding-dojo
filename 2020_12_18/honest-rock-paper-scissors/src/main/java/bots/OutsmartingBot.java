package bots;

import java.util.Random;

import controller.Bot;
import controller.Move;

public class OutsmartingBot implements Bot {

	private Move ourAnnouncement;
	private Random random = new Random();
	
	@Override
	public Move announce() {
		ourAnnouncement = random();
		return ourAnnouncement;
	}

	@Override
	public Move play(Move enemyAnnouncement) {
		return beat(beat(ourAnnouncement));
	}

	private Move beat(Move move) {
		switch (move) {
		case ROCK:
			return Move.PAPER;
		case PAPER:
			return Move.SCISSORS;
		case SCISSORS:
			return Move.ROCK;
		default:
			return random();
		}
	}

	private Move random() {
		return Move.values()[random.nextInt(Move.values().length)];
	}
	
	
}
