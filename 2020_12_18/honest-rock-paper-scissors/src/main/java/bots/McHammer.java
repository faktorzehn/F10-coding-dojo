package bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.Bot;
import controller.Move;

public class McHammer implements Bot {

	private static final int MIN_COUNT_WON = 100;
	private static final int MIN_COUNT_LIABILITY = 10;
	private static final double LIABILITY_THRESHOLD_MIN = 0.4;
	private static final double LIABILITY_THRESHOLD_MAX = 0.6;
	private static final double WON_THRESHOLD = 0.7;

	private Random random = new Random();
	private final List<Move> enemyAnnounces = new ArrayList<>();
	private final List<Move> enemyMoves = new ArrayList<>();
	private double countCorrect = 0;
	private List<Move> ourAnnounces = new ArrayList<>();
	private List<Move> ourMoves = new ArrayList<>();

	@Override
	public Move announce() {
		if (enemyMoves.size() > 0) {
			boolean lastWon = last(ourAnnounces).equals(moveToWin(enemyMoves.get(enemyMoves.size() - 1)));
			
			if (lastWon || getWon() > WON_THRESHOLD) {
				return last(ourMoves);
			}
		}
		ourAnnounces.add(Move.values()[random.nextInt(3)]);
		return last(ourAnnounces);
	}

	@Override
	public Move play(Move enemyAnnounce) {
		enemyAnnounces.add(enemyAnnounce);

		if (getLiability() > LIABILITY_THRESHOLD_MAX) {
			ourMoves.add(moveToWin(enemyAnnounce));
		} else if (getLiability() < LIABILITY_THRESHOLD_MIN) {
			ourMoves.add(Move.values()[((enemyAnnounce.ordinal() + 2) % 3)]);
		} else {
			ourMoves.add(last(ourAnnounces));
		}
		return last(ourMoves);
	}

	private Move last(List<Move> collection) {
		return collection.get(collection.size() - 1);
	}

	private Move moveToWin(Move enemyAnnounce) {
		return Move.values()[((enemyAnnounce.ordinal() + 1) % 3)];
	}

	@Override
	public void actual(Move enemyMove) {
		enemyMoves.add(enemyMove);

		Move lastAnnounce = enemyAnnounces.get(enemyAnnounces.size() - 1);
		countCorrect += lastAnnounce.equals(enemyMove) ? 1 : 0;
	}

	private double getLiability() {
		if (enemyAnnounces.size() > MIN_COUNT_LIABILITY) {
			return countCorrect / enemyAnnounces.size();
		} else {
			return 0;
		}
	}

	private double getWon() {
		int countWon = 0;
		for (int i = Math.max(0, ourMoves.size() - MIN_COUNT_WON); i < ourMoves.size(); i++) {
			if (ourMoves.get(i).equals(moveToWin(enemyMoves.get(i)))) {
				countWon++;
			}
		}
		return countWon / ourMoves.size();
	}
}
