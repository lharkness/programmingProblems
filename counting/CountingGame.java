import java.util.Arrays;

/**
 *  Initial implementation of the Counting Out problem found at
 *  http://icpc.baylor.edu/download/worldfinals/problems/1974-Texas.pdf
 *  (Lower Division, problem 3)
 *  It is a counting out problem where you pass the program the number of
 *  entries and the step value (number of entries to skip each time) and 
 *  it outputs the order that the entries were counted out.
 */
public class CountingGame {

    public static void main(String[] args) {
        int numEntries = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[1]);

        // Add 2 because we want to have a 1-based index
        int[] entries = new int[numEntries + 2];
        int[] removed = new int[numEntries + 2];

        int numRemoved = 0;

        // Initialize our entry array
        for (int i = 0; i <= numEntries; i++) {
            entries[i] = i;
        }

        int curEntryIndex = 1;
        int curStep = 1;
        // Do while there are more entries to remove
        while(numRemoved < numEntries) {
            // The case where the entry under consideration has already been removed
            if (removed[curEntryIndex] != 0) {
                curEntryIndex = incrementEntryIndex(curEntryIndex, numEntries, removed);
                continue;
            }
            // the case where we want to remove the entry under consideration
            if (curStep % step == 0) {
                numRemoved++;
                removed[curEntryIndex] = numRemoved;
                curStep = 1;
                curEntryIndex = incrementEntryIndex(curEntryIndex, numEntries, removed);
                continue;
            }
            // The case where we want to skip the entry under consideration
            curStep++;
            curEntryIndex++;

            if (curEntryIndex > numEntries) {
                int nextIndex = 1;
                while(removed[nextIndex] != 0) {
                    nextIndex++;
                }
                curEntryIndex = nextIndex;
            }
            if (curStep > step) {
                curStep = 1;
            }
        }

        // output our list, skipping the first and last entries (they will be 0)
        for (int i = 1; i < removed.length - 1; i++) {
            System.out.print(removed[i] + " ");
        }
        System.out.println();

    }

    /**
     *  Used to handle incrementing the index - skips removed items and wraps around the end
     * @param curEntryIndex the current index (the one to increment)
     * @param numEntries the number of entries in the list (I suppose this could be removed.length but that's a little hackish)
     * @param removed the entries that have been removed (non-zero entries are removed)
     * @return the next index
     */
    private static int incrementEntryIndex(int curEntryIndex, int numEntries, int[] removed) {
        curEntryIndex++;
        if (curEntryIndex > numEntries) {
            int nextIndex = 1;
            while(removed[nextIndex] != 0) {
                nextIndex++;
            }
            curEntryIndex = nextIndex;
        }

        return curEntryIndex;
    }
}