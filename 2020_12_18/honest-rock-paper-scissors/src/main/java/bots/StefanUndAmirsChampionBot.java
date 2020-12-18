package bots;

import java.util.Random;

import controller.Bot;
import controller.Move;

public class StefanUndAmirsChampionBot implements Bot {

	private Random random = new Random();
	private Move myAnnouncement;
	private Move enemyAnnouncement;

	private int enemyMoves;
	private int enemyHonest;
	private int enemyCounter;
	private double enemyCounterCounter;

	@Override
	public Move announce() {
		myAnnouncement = Move.values()[random.nextInt(3)];
		return myAnnouncement;
	}

	@Override
	public Move play(Move enemyAnnouncement) {
		this.enemyAnnouncement = enemyAnnouncement;
		if (enemyAnnouncement == null) {
			return getRandom();
		}
		if (isEnemyHonest()) {
			return getCounterMove(enemyAnnouncement);
		}
		if (isCounterPlayer()) {
			return getCounterMove(getCounterMove(myAnnouncement));
		}
		if (isCounterCounterPlayer()) {
			return myAnnouncement;
		}
		return random.nextBoolean() ? Move.values()[random.nextInt(3)] : myAnnouncement;
	}

	private boolean isEnemyHonest() {
		if (enemyMoves == 0) {
			return false;
		}
		return enemyHonest / (double) enemyMoves > 0.49;
	}

	private boolean isCounterPlayer() {
		if (enemyMoves == 0) {
			return false;
		}
		return enemyCounter / (double) enemyMoves > 0.421;
	}

	private boolean isCounterCounterPlayer() {
		if (enemyMoves == 0) {
			return false;
		}
		return enemyCounterCounter / (double) enemyMoves > 0.35;
	}

	@Override
	public void actual(Move enemyMove) {
		enemyMoves++;
		if (enemyMove == enemyAnnouncement) {
			enemyHonest++;
		} else {
			if (enemyMove.equals(getCounterMove(myAnnouncement)))
				enemyCounter++;

			if (enemyMove.equals(getCounterMove(getCounterMove(myAnnouncement))))
				enemyCounterCounter++;

		}

	}

	private Move getRandom() {
		return Move.values()[random.nextInt(3)];
	}

	private Move getCounterMove(Move move) {
		if (move == null) {
			return null;
		}
		switch (move) {
		case ROCK:
			return Move.PAPER;
		case PAPER:
			return Move.SCISSORS;
		case SCISSORS:
			return Move.ROCK;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "Stefan & Amirs Champion Bot";
	}

}
