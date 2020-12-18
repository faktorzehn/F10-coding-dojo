package bots;

import controller.Bot;
import controller.Move;

public class ScissorBot implements Bot {

	@Override
	public Move announce() {
		return Move.SCISSORS;
	}

	@Override
	public Move play(Move enemyMove) {
		return Move.SCISSORS;
	}
}
