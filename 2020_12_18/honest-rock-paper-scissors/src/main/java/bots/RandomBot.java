package bots;

import java.util.Random;

import controller.Bot;
import controller.Move;

public class RandomBot implements Bot {

	private Random random = new Random();

	@Override
	public Move announce() {
		return Move.values()[random.nextInt(3)];
	}

	@Override
	public Move play(Move enemyMove) {
		return Move.values()[random.nextInt(3)];
	}
}
