import java.util.Arrays;

public class CountingGame {
	public static void main(String[] args) {
		int numEntries = Integer.parseInt(args[0]);
		int step = Integer.parseInt(args[1]);
		int[] entries = new int[numEntries];
		int[] removed = new int[numEntries];
		int numRemoved = 0;
		for (int i = 0; i < entries.length; i++) {
			entries[i] = i + 1;
		}
		
		int curIndex = 0;
		while (numRemoved < numEntries) {
			while (curIndex % step != 0) {
				if (!Arrays.asList(removed).contains(entries[curIndex])) {
					curIndex++;
					if (curIndex >= numEntries) {
						curIndex = 1;
					}
				}
			}
			
			removed[numRemoved] = entries[curIndex];
			numRemoved++;
			curIndex++;
		}
		
		for (int removedEntry : removed) {
			System.out.println(removedEntry + " ");
		}
	}
}
