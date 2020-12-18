package bots;

import controller.Bot;
import controller.Move;

public class MirrorBot implements Bot {

	private Move enemyMove = Move.ROCK;
	
	@Override
	public Move announce() {
		return enemyMove;
	}

	@Override
	public Move play(Move enemyAnnouncement) {
		return enemyAnnouncement;
	}
	
	@Override
	public void actual(Move enemyMove) {
		this.enemyMove = enemyMove;
	}

}
