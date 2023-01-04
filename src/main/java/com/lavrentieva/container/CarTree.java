package com.lavrentieva.container;

import com.lavrentieva.model.Car;
import lombok.Getter;
import lombok.Setter;

public class CarTree<V extends Car> {
    private V value;
    private final CarComparator carComparator = new CarComparator();
    private Node head;
    private Branch branch;
    private int sum = 0;

    public CarTree(final V value) {
        this.value = value;
        head = null;
    }

    public void add(final V value) {
        head = addRecursive(head, value);
    }

    public int sumCount(Branch branch) {
        sum = head.getCar().getCount();
        if (branch == Branch.LEFT) {
            int result = sumCountRecursive(head.left);
            return result;
        } else if (branch == Branch.RIGHT) {
            int result = sumCountRecursive(head.right);
            return result;
        }
        return sum;
    }

    private int sumCountRecursive(Node node) {
        if (node != null) {
            sum = sum + node.getCar().getCount();
            sumCountRecursive(node.left);
            sumCountRecursive(node.right);
        }
        return sum;
    }

    private Node addRecursive(final Node head, final V value) {
        if (head == null) {
            final Node newNode = new Node(value);
            return newNode;
        }
        if (carComparator.compare(head.getCar(), value) >= 0) {
            head.left = addRecursive(head.left, value);
        } else if (carComparator.compare(head.getCar(), value) < 0) {
            head.right = addRecursive(head.right, value);
        } else {
            return head;
        }
        return head;
    }

    @Getter
    @Setter
    private static class Node {
        private final Car car;
        private Node left;
        private Node right;

        private Node(Car car) {
            this.car = car;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return " Value: " + car;
        }
    }
}
