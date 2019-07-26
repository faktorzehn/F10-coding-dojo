package caveman;

import java.util.ArrayList;
import java.util.List;

public class CavemanTest {

	enum Action {
		BLOCK, POKE, SHARPEN;

		public static List<Action> parse(String string) {
			List<Action> result = new ArrayList<>();
			for (char c : string.toCharArray()) {
				switch (c) {
				case 'B':
					result.add(BLOCK);
					break;
				case 'P':
					result.add(POKE);
					break;
				case 'S':
					result.add(SHARPEN);
					break;
				}
			}
			return result;
		}
	}

	public static int getSharpness(List<Action> actions) {
		int sharpness = 0;
		for (int i = 0; i < actions.size() - 1; i++) {
			Action action = actions.get(i);
			if (action == Action.SHARPEN) {
				sharpness++;
			} else if (action == Action.POKE && sharpness > 0) {
				sharpness--;
			}
		}
		// System.out.println(actions + " " + sharpness);
		return sharpness;
	}

	public static void main(String[] args) {
		test("PP");
	}

	public static void test(String opponent) {
		String myActions = "";
		String opponentsActions = "";

		for (char c : opponent.toCharArray()) {
			String input = myActions.isEmpty() ? null : myActions + "," + opponentsActions;

			myActions += call(input);
			opponentsActions += c;

			String result = battle(Action.parse(myActions), Action.parse(opponentsActions));
			if (result != null) {
				System.out.print(myActions + "," + opponentsActions);
				System.out.println(" - " + result);
				return;
			}
		}

		System.out.print(myActions + "," + opponentsActions);
		System.out.println(" - Stalemate");
	}

	public static String call(String input) {
		return Caveman.run(input);
	}

	public static String battle(List<Action> actions1, List<Action> actions2) {
		int sharpness1 = getSharpness(actions1);
		int sharpness2 = getSharpness(actions2);
		Action current1 = actions1.get(actions1.size() - 1);
		Action current2 = actions2.get(actions1.size() - 1);

		if (current1 == Action.POKE && current2 == Action.POKE) {
			if (sharpness1 == 0 && sharpness2 > 0) {
				return "Opponent wins";
			} else if (sharpness2 == 0 && sharpness1 > 0) {
				return "Player wins";
			} else {
				return null;
			}
		} else if (current1 == Action.POKE && current2 == Action.SHARPEN) {
			if (sharpness1 > 0) {
				return "Player wins";
			} else {
				return null;
			}
		} else if (current2 == Action.POKE && current1 == Action.SHARPEN) {
			if (sharpness2 > 0) {
				return "Opponent wins";
			} else {
				return null;
			}
		} else if (current1 == Action.POKE && current2 == Action.BLOCK) {
			if (sharpness1 >= 5) {
				return "Player wins";
			} else {
				return null;
			}
		} else if (current2 == Action.POKE && current1 == Action.BLOCK) {
			if (sharpness2 >= 5) {
				return "Opponent wins";
			} else {
				return null;
			}
		}

		return null;
	}

}
