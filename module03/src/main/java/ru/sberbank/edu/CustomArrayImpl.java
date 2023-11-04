package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CustomArrayImpl<T>  implements CustomArray<T> {
    private ArrayList<T> arrayList;

    private int capacity;
    public CustomArrayImpl() {
        this.arrayList = new ArrayList<>();
        this.capacity = 10;
    }
    public CustomArrayImpl(int initialCapacity) {
        this.arrayList = new ArrayList<>(initialCapacity);
        this.capacity = initialCapacity;
    }


    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public boolean add(T item) {
        return arrayList.add(item);
    }

    @Override
    public boolean addAll(T[] items) {
        if (items==null){
            throw new IllegalArgumentException();
        }
        arrayList.addAll(Arrays.asList(items));
        return true;
    }

    @Override
    public boolean addAll(Collection<T> items) {
        if (items==null){
            throw new IllegalArgumentException();
        }
        arrayList.addAll(items);
        return true;
    }

    @Override
    public boolean addAll(int index, T[] items) {
        if (items==null){
            throw new IllegalArgumentException();
        }
        if(index>arrayList.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        for (T item : items) {
            arrayList.add(index++, item);
        }
        return true;

    }

    @Override
    public T get(int index) {
        if(index>arrayList.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return arrayList.get(index);
    }

    @Override
    public T set(int index, T item) {
        if(index>arrayList.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return arrayList.set(index,item);
    }

    @Override
    public void remove(int index) {
        if(index>arrayList.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        arrayList.remove(index);
    }

    @Override
    public boolean remove(T item) {
        return arrayList.remove(item);
    }

    @Override
    public boolean contains(T item) {
        return arrayList.contains(item);
    }

    @Override
    public int indexOf(T item) {
        return arrayList.indexOf(item);
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > capacity) {
            arrayList.ensureCapacity(newElementsCount);
            capacity = newElementsCount;
        }
    }

    @Override
    public int getCapacity() {
        if(capacity<arrayList.size()){
           capacity= capacity + (capacity >> 1)+1;
        }
        return capacity;
    }

    @Override
    public void reverse() {
        Collections.reverse(arrayList);
    }
    @Override
    public String toString() {
        return arrayList.toString();
    }

    @Override
    public Object[] toArray() {
        return
        arrayList.toArray();
    }
}
