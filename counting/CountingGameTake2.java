import java.util.Arrays;

/**
 *  Initial implementation of the Counting Out problem found at
 *  http://icpc.baylor.edu/download/worldfinals/problems/1974-Texas.pdf
 *  (Lower Division, problem 3)
 *  It is a counting out problem where you pass the program the number of
 *  entries and the step value (number of entries to skip each time) and 
 *  it outputs the order that the entries were counted out.
 */
public class CountingGameTake2 {

    private static int numEntries;
    private static int step;
    private static int[] entries;
    private static int[] removed;
    private static int numRemoved;
    private static int curEntryIndex;
    private static int curStep;

    public static void main(String[] args) {
        initializeProblem(args);
        
        while(moreEntriesToRemove()) {
            boolean eligibleForRemoval = processNextEntry();
            // the case where we want to remove the entry under consideration
            if (eligibleForRemoval) {
                if (curStep % step == 0) {
                    removeEntry();              
                }
            }
            
        }

        // output our list, skipping the first and last entries (they will be 0)
        for (int i = 1; i < removed.length - 1; i++) {
            System.out.print(removed[i] + " ");
        }
        System.out.println();

    }

    /**
     *  Used to initialize program state
     */
    private static void initializeProblem(String[] args) {
        numEntries = Integer.parseInt(args[0]);
        step = Integer.parseInt(args[1]);

        // Add 2 because we want to have a 1-based index
        entries = new int[numEntries + 2];
        removed = new int[numEntries + 2];

        numRemoved = 0;

        // Initialize our entry array
        for (int i = 0; i <= numEntries; i++) {
            entries[i] = i;
        }

        curEntryIndex = 1;
        curStep = 1;
    }

    /**
     *  Used to handle incrementing the index - skips removed items and wraps around the end
     * @param curEntryIndex the current index (the one to increment)
     * @param numEntries the number of entries in the list (I suppose this could be removed.length but that's a little hackish)
     * @param removed the entries that have been removed (non-zero entries are removed)
     */
    private static void incrementEntryIndex() {
        curEntryIndex++;
        if (curEntryIndex > numEntries) {
            int nextIndex = 1;
            while(removed[nextIndex] != 0) {
                nextIndex++;
            }
            curEntryIndex = nextIndex;
        }
    }

    /**
     *  Used to process each entry - responsible for skipping removed items and 
     *  wrapping the "next entry" counter around the entry list
     * @return true if this entry wasn't previously removed or skipped
     */
    private static boolean processNextEntry() {
        // The case where the entry under consideration has already been removed
        if (removed[curEntryIndex] != 0) {
            incrementEntryIndex();
            return false;
        }
        else if (curStep % step != 0) {
            // The case where we want to skip the entry under consideration
            incrementEntryIndex();
            curStep++;
            if (curStep > step) {
                curStep = 1;
            }
            return false;
        }
        return true;
    }

    /**
     *  used to determine if there are more entries to remove 
     *  @return true if we haven't added all entries to the "removed" array
     */
    private static boolean moreEntriesToRemove() {
        return numRemoved < numEntries;
    }

    /**
     *  Used to remove an entry
     */
    private static void removeEntry() {
        numRemoved++;
        removed[curEntryIndex] = numRemoved;
        curStep = 1;
        incrementEntryIndex();  
    }
}