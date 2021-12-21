package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {

    private final Object[] list;

    public ImmutableArrayList(Object[] elements) {
        list = Arrays.copyOf(elements, elements.length);
    }

    public ImmutableArrayList() {
        list = new Object[0];
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(size(), new Object[] {e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        int counter = 0;
        if (size() == 0 || index > size() || c == null) {
            throw new IllegalArgumentException();
        }

        Object[] full = new Object[list.length + c.length];

        for (int i = 0; i < index; i++) {
            full[counter] = list[i];
            counter++;
        }

        for (Object element : c) {
            full[counter] = element;
            counter++;
        }

        for (int i = index; i < list.length; i++) {
            full[counter] = list[i];
            counter++;
        }
        return new ImmutableArrayList(full);
    }

    @Override
    public Object get(int index) {
        if (index > size()) {
            throw new IllegalArgumentException();
        }
        return list[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (size() == 0 || index > size()) {
            throw new IllegalArgumentException();
        }
        int idx = index + 1;
        Object[] full = new Object[size() - 1];
        if (index >= 0) System.arraycopy(list, 0, full, 0, index);

        for (int i = index; i < full.length; i++) {
            full[i] = list[idx];
            idx++;
        }
        return new ImmutableArrayList(full);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (size() == 0 || index > size()) {
            throw new IllegalArgumentException();
        }
        Object[] full = new Object[size()];
        System.arraycopy(list, 0, full, 0, list.length);
        full[index] = e;
        return new ImmutableArrayList(full);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == e) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList(new Object[size()]);
    }

    @Override
    public boolean isEmpty() {
        return list.length == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[list.length];
        System.arraycopy(list, 0, array, 0, list.length);
        return array;
    }
}