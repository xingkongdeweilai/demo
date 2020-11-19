package com.imooc.demo.demo25;

public class MyArrayList<E> extends MyAbstractList<E> {

    public static final int INITIAL_CAPACITY = 16;

    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    public MyArrayList() {
    }

    public MyArrayList(E[] objects) {
        super();
        for(E e : objects) {
            add(e);
        }
    }

    @Override
    public void add(int index, E e) {
        ensureCapacity();
        for(int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void ensureCapacity() {
        if(size >= data.length) {
            E[] newData = (E[]) new Object[size * 2 + 1];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }

    }

    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public int indexOf(E e) {
        if(e == null) {
            for (int i = 0; i < size; i++) {
                if(data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if(e.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        if(e == null) {
            for (int i = size - 1; i >= 0; i--) {
                if(data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if(e.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkRange(index);
        E oldValue = data[index];
        int numMoved = size - index - 1;
        System.arraycopy(data, index + 1, data, index, numMoved);
        data[size--] = null;
        return oldValue;
    }

    private void checkRange(int index) {
        if(index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ",size:" + size);
        }
    }

    @Override
    public E set(int index, E e) {
        checkRange(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) {
                result.append(",");
            }
        }
        return result.append("]").toString();
    }

    public void trimToSize() {
        if(size != data.length) {
            E[] newData = (E[]) (new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public static void main(String[] args) {
        MyList<String> myList = new MyArrayList<>(new String[]{"肖彤", "刘力健"});
        System.out.println(myList);
        System.out.println(myList.size());
//        myList.add("刘力健");
//        System.out.println(myList);
//        myList.add(0, "肖彤");
//        for(int i = 0; i < 16; i++){
//            myList.add("肖彤" + i);
//        }
//        System.out.println(myList);
//        System.out.println(myList.get(100));
    }
}
