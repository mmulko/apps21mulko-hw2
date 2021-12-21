package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {

    private ImmutableLinkedList linkedList = new ImmutableLinkedList();

    public void push(Object e) {
        linkedList = linkedList.addFirst(e);
    }

    public Object pop() {
        Object value = peek();
        linkedList = linkedList.removeFirst();
        return value;
    }

    public Object peek() {
        return linkedList.getFirst();
    }
}
