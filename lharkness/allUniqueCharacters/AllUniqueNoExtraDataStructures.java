import java.util.Arrays;

public class AllUniqueNoExtraDataStructures {
	public static void main(String[] args) {
		System.out.print("[" + args[0] + "] Does ");
		if (containsRepeatedCharacters(args[0])) {
			System.out.print("not ");
		}
		System.out.println("contain only unique characters");
	}

	protected static boolean containsRepeatedCharacters(String targetString) {
		char[] characters = targetString.toCharArray();
		Arrays.sort(characters);
		for (int i = 0; i < characters.length - 1; i++) {
			if (characters[i] == characters[i + 1]) {
				return true;
			}
		}

		return false;
	}

}