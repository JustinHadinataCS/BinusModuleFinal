package com.example.demo.Subject;

import java.util.*;

public class QuickSortTesting {
    // QuickSort for ArrayList
    public static void quickSortArrayList(List<Subject> list) {
        quickSortArrayList(list, 0, list.size() - 1);
    }

    private static void quickSortArrayList(List<Subject> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionArrayList(list, low, high);
            quickSortArrayList(list, low, pivotIndex - 1);
            quickSortArrayList(list, pivotIndex + 1, high);
        }
    }

    private static int partitionArrayList(List<Subject> list, int low, int high) {
        Subject pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getName().compareTo(pivot.getName()) <= 0) {
                i++;
                swapArrayList(list, i, j);
            }
        }
        swapArrayList(list, i + 1, high);
        return i + 1;
    }

    private static void swapArrayList(List<Subject> list, int i, int j) {
        Subject temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // QuickSort function for LinkedList of Subject objects

    public static void quickSortLinkedList(List<Subject> subjects){
        quickSortLinkedList(subjects, 0, subjects.size() - 1);
    }
    public static void quickSortLinkedList(List<Subject> subjects, int low, int high) {
        if (low < high) {
            int pi = partition(subjects, low, high);

            // Recursively sort elements before and after partition
            quickSortLinkedList(subjects, low, pi - 1);
            quickSortLinkedList(subjects, pi + 1, high);
        }
    }

    // Partition function for LinkedList of Subject objects
    private static int partition(List<Subject> subjects, int low, int high) {
        String pivot = subjects.get(high).getName();
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (subjects.get(j).getName().compareTo(pivot) <= 0) {
                i++;

                // Swap subjects[i] and subjects[j]
                Subject temp = subjects.get(i);
                subjects.set(i, subjects.get(j));
                subjects.set(j, temp);
            }
        }

        // Swap subjects[i + 1] and subjects[high] (or pivot)
        Subject temp = subjects.get(i + 1);
        subjects.set(i + 1, subjects.get(high));
        subjects.set(high, temp);

        return i + 1;
    }

    // QuickSort for Queue
    public static void quickSortQueue(Queue<Subject> queue) {
        if (queue.size() <= 1) return;
        Queue<Subject> smaller = new LinkedList<>();
        Queue<Subject> equal = new LinkedList<>();
        Queue<Subject> larger = new LinkedList<>();
        Subject pivot = queue.peek();
        partitionQueue(queue, pivot, smaller, equal, larger);

        quickSortQueue(smaller);
        quickSortQueue(larger);

        queue.clear();
        queue.addAll(smaller);
        queue.addAll(equal);
        queue.addAll(larger);
    }

    private static void partitionQueue(Queue<Subject> queue, Subject pivot, Queue<Subject> smaller,
                                       Queue<Subject> equal, Queue<Subject> larger) {
        while (!queue.isEmpty()) {
            Subject current = queue.poll();
            if (current.getName().compareTo(pivot.getName()) < 0) {
                smaller.offer(current);
            } else if (current.getName().compareTo(pivot.getName()) == 0) {
                equal.offer(current);
            } else {
                larger.offer(current);
            }
        }
    }

    // QuickSort for Stack
    public static void quickSortStack(Stack<Subject> stack) {
        if (stack.size() <= 1) return;
        Stack<Subject> smaller = new Stack<>();
        Stack<Subject> equal = new Stack<>();
        Stack<Subject> larger = new Stack<>();
        Subject pivot = stack.peek();
        partitionStack(stack, pivot, smaller, equal, larger);

        quickSortStack(smaller);
        quickSortStack(larger);

        while (!smaller.isEmpty()) {
            stack.push(smaller.pop());
        }
        while (!equal.isEmpty()) {
            stack.push(equal.pop());
        }
        while (!larger.isEmpty()) {
            stack.push(larger.pop());
        }
    }

    private static void partitionStack(Stack<Subject> stack, Subject pivot, Stack<Subject> smaller,
                                       Stack<Subject> equal, Stack<Subject> larger) {
        while (!stack.isEmpty()) {
            Subject current = stack.pop();
            if (current.getName().compareTo(pivot.getName()) < 0) {
                smaller.push(current);
            } else if (current.getName().compareTo(pivot.getName()) == 0) {
                equal.push(current);
            } else {
                larger.push(current);
            }
        }
    }
}
