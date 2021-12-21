package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {

    private ImmutableLinkedList linkedList = new ImmutableLinkedList();

    public Object peek() {
        return linkedList.getFirst();
    }

    public Object dequeue() {
        Object value = peek();
        linkedList = linkedList.removeFirst();
        return value;
    }

    public void enqueue(Object e) {
        linkedList = linkedList.addLast(e);
    }
}
