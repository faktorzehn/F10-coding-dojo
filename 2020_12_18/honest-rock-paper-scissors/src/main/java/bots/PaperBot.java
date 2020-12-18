package bots;

import controller.Bot;
import controller.Move;

public class PaperBot implements Bot {

	@Override
	public Move announce() {
		return Move.PAPER;
	}

	@Override
	public Move play(Move enemyMove) {
		return Move.PAPER;
	}
}
