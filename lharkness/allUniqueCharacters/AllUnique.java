import java.util.Set;
import java.util.HashSet;

public class AllUnique {
	public static void main(String[] args) {
		System.out.print("[" + args[0] + "] Does ");
		if (containsRepeatedCharacters(args[0])) {
			System.out.print("not ");
		}
		System.out.println("contain only unique characters");
	}

	protected static boolean containsRepeatedCharacters(String targetString) {
		Set<Character> usedCharacters = new HashSet<Character>();
		for (char c : targetString.toCharArray()) {
			if (usedCharacters.contains(c)) {
				return true;
			}
			usedCharacters.add(c);
		}

		return false;
	}

}