package com.lavrentieva.container;

import com.lavrentieva.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

public class CarList<E extends Car> {
    private E element;
    private Node<E> head;
    private Node<E> tail;


    public CarList(final E element) {
        this.element = element;
    }

    public void addTopOfList(final E element) {
        final Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.prev = null;
            tail.next = null;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            head.prev = null;
        }
    }

    public void addEndOfList(final E element) {
        final Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.prev = null;
            tail.next = null;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        tail.next = null;
    }

    public void findPositionByElement(final E element) {
        int index = 0;
        boolean isElementFound = false;
        Node<E> current = head;
        if (head == null) {
            System.out.println("CarList is Empty");
        }
        while (current != null) {
            if (checkIdHashCodeEquals(element, current.getElement())) {
                System.out.println("The item's position you are looking for in CarList is: "
                        + index++);
                current = current.next;
                isElementFound = true;
            } else {
                index++;
                current = current.next;
            }
        }
        if (!isElementFound) {
            System.out.println("The item's position you are looking for in CarList" +
                    "is not found");
        }
    }

    private boolean checkIdHashCodeEquals(E element, E current) {
        if (element.getId().hashCode() != current.getId().hashCode()) {
            return false;
        } else if (element.getId().equals(current.getId())) {
            return true;
        } else {
            return false;
        }
    }

    public void addElementByPosition(final int position, final E element) {
        int index = 0;
        Node<E> current = head;
        if (head == null) {
            addTopOfList(element);
            System.out.println("CarList was Empty, element was added " +
                    "to the top of CarList");
            return;
        }
        if (position == index) {
            addTopOfList(element);
            return;
        }
        while (current.next != null) {
            if (position == index) {
                final Node<E> newNode = new Node<>(element);
                Node<E> temp = current.next;
                current.next = newNode;
                newNode.next = temp;
                newNode.prev = current;
                temp.prev = newNode;
                break;
            } else {
                index++;
                current = current.next;
            }
        }
        if (position > index) {
            addEndOfList(element);
            System.out.println("Position is bigger than CarList's size, element was added " +
                    "to the end of CarList");
        }
        if (current.next == null) {
            addEndOfList(element);
        }
    }

    public void deleteElementByPosition(final int position) {
        int index = 0;
        Node<E> del = head;
        if (head == null) {
            System.out.println("CarList is Empty, nothing to delete");
            return;
        }
        if (position == index) {
            head = del.next;
            return;
        }
        while (del.next != null) {
            if (position == index) {
                del.next.prev = del.prev;
                del.prev.next = del.next;
                break;
            }
            index++;
            del = del.next;
        }
        if (del.next == null) {
            tail = del.prev;
            del.prev.next = null;
        }
    }

    public void printCarList() {
        int index = 0;
        Node<E> current = head;
        if (head == null) {
            System.out.println("CarList is Empty");
        }
        while (current != null) {
            System.out.println(index + " " + current);
            index++;
            current = current.next;
        }
    }

    public void iterateCarList() {
        CarListIterator carListIterator = new CarListIterator();
        int number = 0;
        while (carListIterator.hasNext()) {
            Car car = carListIterator.next();
            System.out.println(number + " " + car);
            number++;
        }
    }

    public int sumCountCarsFromCarList() {
        CarListIterator carListIterator = new CarListIterator();
        int sum = 0;
        while (carListIterator.hasNext()) {
            int carCount = carListIterator.next().getCount();
            sum = sum + carCount;
        }
        return sum;
    }

    private class CarListIterator implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                element = current.getElement();
                current = current.next;
                return element;
            }
            return null;
        }
    }

    @Setter
    @Getter
    private static class Node<E> {

        Node<E> prev;
        E element;
        Node<E> next;

        private Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return " Element: " + element;
        }
    }
}


