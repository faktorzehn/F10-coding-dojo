package bots;

import controller.Bot;
import controller.Move;

public class HonestLoserBot implements Bot {

	private Move enemyMove = Move.ROCK;
	
	@Override
	public Move announce() {
		return beat(beat(enemyMove));
	}

	@Override
	public Move play(Move enemyAnnouncement) {
		return beat(beat(enemyMove));
	}
	
	@Override
	public void actual(Move enemyMove) {
		this.enemyMove = enemyMove;
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
			return Move.ROCK;
		}
	}
	
	
}
