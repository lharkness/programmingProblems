package com.leeharkness.challenges.lists;

/**
 * Represents a LinkedList
 */
public class LeesLinkedList<T> {
    
    private Node<T> head;

    public LeesLinkedList() {}
    public LeesLinkedList(final T head) {
        this.head = new Node<T>(head);
    }

    /**
     *  Adds this value to the end of the list.  Will create a new head if the 
     * list is currently empty
     * @param value the Value to add`
     */
    public void add(final T value) {
        final Node<T> newNode = new Node<>(value);
        // Handle the "empty list" case
        if (head == null) {
            this.head = newNode;
            return;
        }
        final Node<T> lastNode = getLastNode();
        lastNode.setNext(newNode);
    }

    /**
     * Adds the requested value at the requested location.
     * @param value the value to add
     * @param index the location to add the value to
     */
    public void add(final T value, final int index) {
        ensureIndex(index);
        final Node<T> newNode = new Node<>(value);
        // Handle the "new head" case
        if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
            return;
        }
        final NodePair<T> nodes = getNthNode(index);
        final Node<T> nthNode = nodes.getPrevNode(); 
        newNode.setNext(nthNode.getNext());
        nthNode.setNext(newNode);
    }

    /**
     * Adds another list to the end of this list
     * @param newList the list to add
     */
    public void add(final LeesLinkedList<T> newList) {
        if (newList == null) {
            return;
        }
        final Node<T> lastNode = getNthNode(this.size()).getCurNode();
        lastNode.setNext(newList.head);
    }

    /**
     * Adds another list to this list at the requested location
     * @param newList The list to add
     * @param pos the position in this list to add at
     */
    public void add(final LeesLinkedList<T> newList, final int pos) {
        ensureIndex(pos);
        final NodePair<T> nodePair = getNthNode(pos);
        newList.getLastNode().setNext(nodePair.getCurNode().getNext());
        nodePair.getCurNode().setNext(newList.head);
    }

    /**
     * Determines whether a given value can be found in this list
     * @param value the value to check
     * @return true if the value is within the list, false if not
     */
    public boolean contains(final T value) {
        return find(value) != -1;
    }

    /**
     * Finds the index of a given value
     * @param value the value to look for
     * @return the index within the list of this value, -1 if not found
     */
    public int find(final T value) {
        int index = -1;
        Node<T> curNode = head;
        while (curNode != null) {
            index++;
            if (curNode.getValue().equals(value)) {
                return index; 
            }
            curNode = curNode.getNext();
        }
        return -1;
    }

    /**
     * Returns the value found at index
     * @param index The index of the value to retrieve
     * @return the value found at index
     */
    public T get(final int index) {
        ensureIndex(index);
        int curIndex = -1;
        Node<T> curNode = head;
        while (curIndex < index) {
            curIndex++;
            curNode = curNode.getNext();
        }
        return curNode.getValue();
    }

    /**
     * Returns the size of this List
     * @return the size of the list
     */
    public int size() {
        int total = 0;
        Node<T> curNode = head;
        while (curNode != null) {
            curNode = curNode.getNext();
            total++;
        }
        return total;
    }

    /**
     * Removes the first instance of the value found in the list
     * @param value the value to remove
     */
    public void remove(final T value) {
        int pos = find(value);

        if (pos < 0) { return; }

        if (pos == 0) {
            this.head = this.head.getNext();
            return;
        }
        NodePair<T> nodePair = getNthNode(pos);
        if (pos == this.size()) {
            nodePair.curNode.setNext(null);
            return;
        }

        nodePair.prevNode.setNext(nodePair.curNode.getNext());
    }

    /**
     * Convenience method for finding the last node.
     * @return the last node
     */
    private Node<T> getLastNode() {
        if (this.size() == 0) { return null; }
        return getNthNode(size()).getCurNode();
    }

    /**
     * Ensures that index is valid
     * @param index The index to inspect
     * @throws IllegalArgumentException if the index is less than zero or greater than size
     */
    private void ensureIndex(final int index) {
        if (index > this.size() || index < 0) {
            throw new IllegalArgumentException(
                String.format("Cannot add value at location %d as location must be between 0 and %d", 
                    index, this.size()));
        }
    }

    /**
     * Gets the requested node and its predecessor
     * @param index the index of the node to get
     * @return a Node Pair consisting of the requested node and its predecessor.  Prev node will be null for 
     *  the head.
     */
    private NodePair<T> getNthNode(final int index) {
        ensureIndex(index);
        int counter = 0;
        Node<T> prevNode = null;
        Node<T> curNode = head;
        while (curNode.getNext() != null && counter < index) {
            prevNode = curNode;
            curNode = curNode.getNext();
            counter++;
        }
        return new NodePair<T>(prevNode, curNode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> curNode = head;
        while (curNode != null) {
            sb.append(curNode.toString());
            curNode = curNode.getNext();
            if (curNode != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Represents a pair of nodes.  Useful for storing the target of a search and its predecessor
     * @param <T> the type of value
     */
    private static class NodePair<T> {
        private Node<T> prevNode;
        private Node<T> curNode;

        /**
         * intialization ctor
         * @param prevNode the previous node
         * @param curNode the current node
         */
        public NodePair(Node<T> prevNode, Node<T> curNode) {
            this.prevNode = prevNode;
            this.curNode = curNode;
        }

        /**
         * Returns the previous node
         * @return the previous node
         */
        public Node<T> getPrevNode() {
            return this.prevNode;
        }

        /**
         * Returns the current node
         * @return the current node
         */
        public Node<T> getCurNode() {
            return this.curNode;
        }

        @Override
        public String toString() {
            return "Prev Node: " + prevNode.toString() + " Cur Node: " + curNode.toString();
        }
    }

    /**
     * Represents a node
     */
    public static class Node<T> {
        private final T value;
        private Node<T> next;

        /**
         * Initialization ctor
         * @param value the value
         */
        public Node(T value) { this.value = value; }

        public T getValue() { return value; }
        public Node<T> getNext() { return this.next; }
        public void setNext(Node<T> next) { this.next = next; }

        @Override
        public boolean equals(Object o) { return value.equals(o); }
        @Override
        public int hashCode() { return value.hashCode(); }
        @Override
        public String toString() { return value.toString(); }
    }

}