package bots;

import java.util.Random;

import controller.Bot;
import controller.Move;

public class SimpleHonestyBot implements Bot {

	private Move ourAnouncement;
	private Move enemyAnouncement;
	private Random random = new Random();
	private Move ourMove = random();
	int honesty = 5;

	@Override
	public Move announce() {
		ourAnouncement = ourMove;
		return ourAnouncement;
	}

	@Override
	public Move play(Move enemyAnnouncement) {
		this.enemyAnouncement = enemyAnnouncement;
		if (honesty >= 5) {
			ourMove = beat(enemyAnnouncement);
		} else {
			ourMove = beat(beat(ourAnouncement));
		}

		return ourMove;
	}

	@Override
	public void actual(Move enemyMove) {
		if (enemyAnouncement == enemyMove) {
			honesty++;
		} else {
			honesty--;
		}

		honesty = Math.max(Math.min(honesty, 10), 0);
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

	private Move next(Move move) {
		return Move.values()[(move.ordinal() + 1) % (Move.values().length - 1)];
	}

	private Move random() {
		return Move.values()[random.nextInt(Move.values().length)];
	}

}
