package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableLinkedList implements ImmutableList {

    private Node head;
    private final Node tail;

    public ImmutableLinkedList(Object[] elements) {
        Node previousNode = null;
        for (Object element : elements) {
            Node currentNode = new Node();
            currentNode.setPrevious(previousNode);
            if (previousNode != null) {
                previousNode.setNext(currentNode);
            } else {
                head = currentNode;
            }
            currentNode.setValue(element);
            previousNode = currentNode;
        }
        tail = previousNode;

    }

    public ImmutableLinkedList() {
        head = null;
        tail = null;
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(size(), new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        int counter = 0;
        if (index > size() || c == null) {
            throw new IllegalArgumentException();
        }

        Node currentNode = getHead();
        Object[] full = new Object[size() + c.length];

        for (int i = 0; i < index; i++) {
            full[counter] = currentNode.getValue();
            currentNode = currentNode.getNext();
            counter++;
        }

        for (Object element : c) {
            full[counter] = element;
            counter++;
        }

        while (currentNode != null) {
            full[counter] = currentNode.getValue();
            currentNode = currentNode.getNext();
            counter++;
        }
        return new ImmutableLinkedList(full);
    }

    @Override
    public Object get(int index) {
        if (size() == 0 || index > size()) {
            throw new IllegalArgumentException();
        }
        Node currentNode = getHead();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        if (size() == 0 || index > size()) {
            throw new IllegalArgumentException();
        }
        Node currentNode = getHead();
        Object[] full = new Object[size() - 1];
        for (int i = 0; i < index; i++) {
            full[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        currentNode = currentNode.getNext();
        for (int i = index; i < full.length; i++) {
            full[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return new ImmutableLinkedList(full);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (size() == 0 || index > size()) {
            throw new IllegalArgumentException();
        }
        Object[] array = toArray();
        array[index] = e;
        return new ImmutableLinkedList(array);
    }

    @Override
    public int indexOf(Object e) {
        int result = 0;
        Node currentNode = getHead();
        while (currentNode.getValue() != e) {
            result++;
            currentNode = currentNode.getNext();
        }
        if (result == size()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public int size() {
        int result = 0;
        Node currentNode = getHead();
        while (currentNode != null) {
            currentNode = currentNode.getNext();
            result++;
        }
        return result;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList(new Object[size()]);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node currentNode = getHead();
        int index = 0;
        while (currentNode != null) {
            array[index] = currentNode.getValue();
            currentNode = currentNode.getNext();
            index++;
        }
        return array;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) addAll(size(), new Object[] {e});
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public Object getFirst() {
        if (size() == 0) {
            throw new IllegalArgumentException();
        }
        return head.getValue();
    }

    public Object getLast() {
        if (size() == 0) {
            throw new IllegalArgumentException();
        }
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        if (size() == 0) {
            throw new IllegalArgumentException();
        }
        Object[] modified = Arrays.copyOfRange(toArray(), 1, size());
        return new ImmutableLinkedList(modified);
    }

    public ImmutableLinkedList removeLast() {
        if (size() == 0) {
            throw new IllegalArgumentException();
        }
        Object[] modified = Arrays.copyOf(toArray(), size() - 1);
        return new ImmutableLinkedList(modified);
    }
}