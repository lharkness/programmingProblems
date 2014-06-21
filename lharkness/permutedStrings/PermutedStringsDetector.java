import java.util.Arrays;

public class PermutedStringsDetector {
	public static void main(String[] args) {
		String firstString = args[0];
		String secondString = args[1];

		boolean isPermutation = isPermutation(firstString, secondString);
		String connector = "is ";
		if (!isPermutation) {
			connector = "is not ";
		}

		System.out.println("[" + firstString + "] " + connector + 
			"a permutation of [" + secondString + "]");
	}

	protected static boolean isPermutation(String firstString, String secondString) {
		char[] firstStringChars = firstString.toCharArray();
		char[] secondStringChars = secondString.toCharArray();
		Arrays.sort(firstStringChars);
		Arrays.sort(secondStringChars);
		firstString = new String(firstStringChars);
		secondString = new String(secondStringChars);

		return firstString.equals(secondString);
	}
}