package bots;

import controller.Bot;
import controller.Move;

public class RockBot implements Bot {

	@Override
	public Move announce() {
		return Move.ROCK;
	}

	@Override
	public Move play(Move enemyMove) {
		return Move.ROCK;
	}
}
