package com.imooc.demo.demo25;

import java.util.Objects;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    public MyLinkedList() {
    }

    public MyLinkedList(E[] Objects) {
        super(Objects);
    }

    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if(index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            current.next.next = temp;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<E> temp = head;
        head = head.next;
        size--;
        if(head == null) {
            tail = null;
        }
        return temp.element;
    }

    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = tail;
            head = tail = null;
            size--;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            current.next = null;
            tail = current;
            size--;
            return temp.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if(index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append("->");
                current = current.next;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        Node<E> current = head;
        for (int i = 0; i <= index; i++){
            if (i != 0) {
                current = current.next;
            }
        }
        return current.element;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ",size:" + size);
        }
    }

    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                current = current.next;
            }
            if (Objects.equals(e, current.element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                current = current.next;
            }
            if (Objects.equals(e, current.element)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public E set(int index, E e) {
        checkElementIndex(index);
        Node<E> current = head;
        for (int i = 0; i <= index; i++){
            if (i != 0) {
                current = current.next;
            }
        }
        current.element = e;
        return current.element;
    }

    private static class Node<E> {

        E element;

        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.addFirst(1);
        myLinkedList.addFirst(2);
        myLinkedList.addFirst(3);
        myLinkedList.addFirst(4);
        myLinkedList.addFirst(5);
        myLinkedList.add(1, 6);
        System.out.println(myLinkedList);
        System.out.println("删除第一位：" + myLinkedList.removeFirst());
        System.out.println(myLinkedList);
        System.out.println("删除最后一位：" + myLinkedList.removeLast());
        System.out.println(myLinkedList);
        System.out.println("删除坐标为1的数据：" + myLinkedList.remove(1));
        System.out.println(myLinkedList);

        myLinkedList.add(1, null);
        System.out.println(myLinkedList);
        System.out.println("6的下标：" + myLinkedList.indexOf(6));
        System.out.println("null的下标：" + myLinkedList.indexOf(null));
        System.out.println("3的下标：" + myLinkedList.indexOf(3));
        System.out.println("2的下标：" + myLinkedList.indexOf(2));
        System.out.println("6的最后下标：" + myLinkedList.lastIndexOf(6));
        System.out.println("null的最后下标：" + myLinkedList.lastIndexOf(null));
        System.out.println("3的最后下标：" + myLinkedList.lastIndexOf(3));
        System.out.println("2的最后下标：" + myLinkedList.lastIndexOf(2));
        myLinkedList.add(4, 6);
        System.out.println(myLinkedList);
        System.out.println("6的下标：" + myLinkedList.indexOf(6));
        System.out.println("6的最后下标：" + myLinkedList.lastIndexOf(6));

        System.out.println("下标为0的元素：" + myLinkedList.get(0));
        System.out.println("下标为1的元素：" + myLinkedList.get(1));
        System.out.println("下标为2的元素：" + myLinkedList.get(2));
        System.out.println("下标为3的元素：" + myLinkedList.get(3));
        System.out.println("下标为4的元素：" + myLinkedList.get(4));

        myLinkedList.set(4, null);
        System.out.println(myLinkedList);
        myLinkedList.set(0, 2);
        System.out.println(myLinkedList);
    }
}
