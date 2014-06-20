import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *  Implementation of the Counting Out problem found at
 *  http://icpc.baylor.edu/download/worldfinals/problems/1974-Texas.pdf
 *  (Lower Division, problem 3)
 *  It is a counting out problem where you pass the program the number of
 *  entries and the step value (number of entries to skip each time) and 
 *  it outputs the order that the entries were counted out.
 *  This version uses method names chosen to make it read as closely to the 
 *  solution to the problem as it might be stated in English as possible
 */
public class CountingGameWithIterator {

    private static int numEntries;
    private static int numRemoved;
    private static List<Integer> entries;
    private static CountingProblemIterator entryIterator;
    private static int[] removed;
    private static int curEntryIndex;
    private static int step;
    private static int curStep;

    public static void main(String[] args) {
        initializeProblem(args);
       
        while(moreEntriesToRemove()) {
            int entry = entryIterator.next();
            
            if (curStep % step == 0) {
                numRemoved++;
                entryIterator.remove();
                curStep = 1;
                curEntryIndex = incrementEntryIndex(curEntryIndex, numEntries, removed);
                continue;
            }

        }

        // output our list, skipping the first and last entries (they will be 0)
        for (int i = 1; i < removed.length - 1; i++) {
            System.out.print(removed[i] + " ");
        }
        System.out.println();

    }

    private static void initializeProblem(String[] args) {
        numEntries = Integer.parseInt(args[0]);
        step = Integer.parseInt(args[1]);

        // Add 2 because we want to have a 1-based index
        entries = new LinkedList<Integer>();
        removed = new int[numEntries + 2];

        numRemoved = 0;

        // Initialize our entry array
        for (int i = 0; i <= numEntries; i++) {
            entries.add(i);
        }

        curEntryIndex = 1;
        curStep = 1;

        entryIterator = new CountingProblemIterator(entries);
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

    /**
     *  Used to determine if there are more entries to remove
     * @return true if there are more entries to remove, false if not
     */
    private static boolean moreEntriesToRemove() {
        return numRemoved < numEntries;
    }

    /**
     *  Encapsulates the counting needed to get the next non-removed item from a list
     *  also wraps around the end of the list
     */
    private static class CountingProblemIterator implements Iterator<Integer> {

        private List<Integer> entries;

        public CountingProblemIterator(List<Integer> entryList) {
            this.entries = entryList;
        }

        public boolean hasNext() {
            return entries.size() > 0;
        }

        public Integer next() {
            curEntryIndex++;
            if (curEntryIndex > numEntries) {
                int nextIndex = 1;
                while(removed[nextIndex] != 0) {
                    nextIndex++;
                }
                curEntryIndex = nextIndex;
            }

            return entries.get(curEntryIndex);
        }

        public void remove() {
            numRemoved++;
            removed[curEntryIndex] = numRemoved;
            curStep = 1;
            curEntryIndex = incrementEntryIndex(curEntryIndex, numEntries, removed);
        }
    }
}