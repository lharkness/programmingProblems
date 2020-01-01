
import com.leeharkness.challenges.lists.*;

public class LeesLinkedListTest {

    private static final String FIRST_VALUE = "first value";
    private static final String SECOND_VALUE = "second value";
    private static final String FIRST_HALF_VALUE = "first half value";
    private static final String NEW_HEAD_VALUE = "new head value";
    private static final String NEW_LIST_HEAD = "new list head";
    private static final String NEW_LIST_SECOND_VALUE = "new list second value";

    public static void main(String[] args) {
        LeesLinkedList<String> linkedList = new LeesLinkedList<>();
        boolean initialSizeTest = linkedList.size() == 0;
        System.out.println("Initial size test: " + initialSizeTest);
        linkedList.add(FIRST_VALUE);
        System.out.println("First value insert test: " + linkedList.toString().contains(FIRST_VALUE));
        linkedList.add(SECOND_VALUE);
        linkedList.add(FIRST_HALF_VALUE, 1);
        int firstHalfValuePosition = linkedList.find(FIRST_HALF_VALUE);
        boolean correctLocation = firstHalfValuePosition == 1;
        System.out.println("Add at position test: " + correctLocation);
        linkedList.add(NEW_HEAD_VALUE, 0);
        System.out.println("New Head test: " + linkedList.toString().startsWith("[" + NEW_HEAD_VALUE));

        LeesLinkedList<String> newList = new LeesLinkedList<>();
        newList.add(NEW_LIST_HEAD);
        newList.add(NEW_LIST_SECOND_VALUE);
        int prevSize = linkedList.size();
        int newListSize = newList.size();

        linkedList.add(newList);

        boolean newListAddedSizeTest = linkedList.size() == prevSize + newListSize;

        System.out.println("New List Add size test: " + newListAddedSizeTest);

        System.out.println("New List add contains test 1: " + linkedList.contains(NEW_LIST_HEAD));
        System.out.println("New List add contains test 2: " + linkedList.contains(NEW_LIST_SECOND_VALUE));
        
        System.out.println("Head test: " + Boolean.toString(linkedList.find(NEW_HEAD_VALUE) == 0));
        System.out.println("Second Value test: " + Boolean.toString(linkedList.find(FIRST_VALUE) == 1));
        System.out.println("Third Value test: " + Boolean.toString(linkedList.find(FIRST_HALF_VALUE) == 2));
        System.out.println("Fourth Value test: " + Boolean.toString(linkedList.find(SECOND_VALUE) == 3));
        System.out.println("Fifth Value test: " + Boolean.toString(linkedList.find(NEW_LIST_HEAD) == 4));
        System.out.println("Sixth Value test: " + Boolean.toString(linkedList.find(NEW_LIST_SECOND_VALUE) == 5));

        linkedList.remove(NEW_HEAD_VALUE);
        boolean removeHeadTest = linkedList.find(NEW_HEAD_VALUE) == -1;
        System.out.println("Remove Head test: " + removeHeadTest);
        
        linkedList.remove(NEW_LIST_SECOND_VALUE);
        boolean removeTailTest = linkedList.find(NEW_LIST_SECOND_VALUE) == -1;
        System.out.println("Remove Tail test: " + removeTailTest);
        linkedList.remove(FIRST_HALF_VALUE);
        boolean removeMiddleTest = linkedList.find(FIRST_HALF_VALUE) == -1;
        System.out.println("Remove Middle test: " + removeMiddleTest);

        newList = new LeesLinkedList<>();
        newList.add(NEW_LIST_HEAD);
        newList.add(NEW_LIST_SECOND_VALUE);

        linkedList.add(newList, 1);

        boolean newListAddTest = linkedList.find(NEW_LIST_HEAD) == 2 &&
            linkedList.find(NEW_LIST_SECOND_VALUE) == 3;
        
        System.out.println("New List add at position test: " + newListAddTest);

    }
}