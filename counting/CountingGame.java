iimport java.util.Arrays;

public class CountingGame {
    public static void main(String[] args) {
        int numEntries = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[1]);

        int[] entries = new int[numEntries + 2];
        int[] removed = new int[numEntries + 2];

        int numRemoved = 0;

        for (int i = 0; i <= numEntries; i++) {
            entries[i] = i;
        }

        int curEntryIndex = 1;
        int curStep = 1;
        while(numRemoved < numEntries) {
            if (removed[curEntryIndex] != 0) {
                curEntryIndex++;
                if (curEntryIndex > numEntries) {
                    int nextIndex = 1;
                    while(removed[nextIndex] != 0) {
                        nextIndex++;
                    }
                    curEntryIndex = nextIndex;
                }
                continue;
            }
            if (curStep % step == 0) {
                numRemoved++;
                removed[curEntryIndex] = numRemoved;
                curStep = 1;
                curEntryIndex++;
                if (curEntryIndex > numEntries) {
                    int nextIndex = 1;
                    while(removed[nextIndex] != 0) {
                        nextIndex++;
                    }
                    curEntryIndex = nextIndex;
                }
                continue;
            }
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

        for (int i : removed) {
            System.out.print(i + " ");
        }


    }
}