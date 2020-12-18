package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import bots.RandomBot;
import bots.RockBot;
import bots.PaperBot;

public class Controller {

	static List<Class<? extends Bot>> bots = Arrays.asList(RandomBot.class, RockBot.class, PaperBot.class);
	static Map<Class<?>, Integer> scores = new HashMap<>();

	public static void main(String[] args) {
		List<List<Bot>> pairings = createPairings(bots);
		System.out.println(pairings);

		for (List<Bot> pairing : pairings) {
			battle(pairing.get(0), pairing.get(1));
		}

		List<Map.Entry<Class<?>, Integer>> sorted = scores.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toList());
		sorted = Lists.reverse(sorted);

		System.out.println(sorted);
	}

	private static void battle(Bot b1, Bot b2) {
		int score1 = scores.getOrDefault(b1.getClass(), 0);
		int score2 = scores.getOrDefault(b2.getClass(), 0);

		for (int i = 0; i < 100000; i++) {
			Move choice1 = b1.announce();
			Move choice2 = b2.announce();
			Move play1 = b1.play(choice2);
			Move play2 = b2.play(choice1);

			boolean honest1 = choice1 == play1;
			boolean honest2 = choice2 == play2;
			score1 += score(play1, play2) + (honest1 ? 1 : 0);
			score2 += score(play2, play1) + (honest2 ? 1 : 0);

			b1.actual(play2);
			b2.actual(play1);
		}

		scores.put(b1.getClass(), score1);
		scores.put(b2.getClass(), score2);
	}

	private static int score(Move m1, Move m2) {
		if (m1 == m2) {
			return 1;
		}

		if ((m1 == Move.ROCK && m2 == Move.SCISSORS) || (m1 == Move.PAPER && m2 == Move.ROCK)
				|| (m1 == Move.PAPER && m2 == Move.ROCK)) {
			return 2;
		}

		return 0;
	}

	private static List<List<Bot>> createPairings(List<Class<? extends Bot>> classes) {
		Set<Set<Class<? extends Bot>>> combinations = Sets.combinations(new HashSet<>(classes), 2);
		List<List<Bot>> results = new ArrayList<>();

		for (Set<Class<? extends Bot>> combination : combinations) {
			Iterator<Class<? extends Bot>> iterator = combination.iterator();
			try {
				Bot bot1 = iterator.next().newInstance();
				Bot bot2 = iterator.next().newInstance();
				results.add(Arrays.asList(bot1, bot2));
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		return results;
	}

}
