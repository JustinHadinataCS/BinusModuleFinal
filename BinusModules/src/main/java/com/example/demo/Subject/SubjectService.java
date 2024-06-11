package com.example.demo.Subject;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectService {


    private List<Subject> subjects = new ArrayList<>();

    public SubjectService() {
        addInitialSubjects();

//        mergeSort(subjects);
    }

    private void addInitialSubjects() {
        subjects.add(new Subject("CHAR6013001", "Character Building: Pancasila"));
        subjects.add(new Subject("COMP6798001", "Program Design Methods*"));
        subjects.add(new Subject("COMP6800001", "Human and Computer Interaction"));
        subjects.add(new Subject("COMP6047001", "Algorithm and Programming* (Block)"));
        subjects.add(new Subject("MATH6025001", "Discrete Mathematics"));
        subjects.add(new Subject("MATH6183001", "Scientific Computing"));
        subjects.add(new Subject("COMP6048001", "Data Structures* (Block)"));
        subjects.add(new Subject("ENGL6171001", "Academic English I"));
        subjects.add(new Subject("ENTR6091005", "Project Hatchery (Block)"));
        subjects.add(new Subject("COMP6699001", "Object Oriented Programming"));
        subjects.add(new Subject("MATH6031001", "Calculus"));
        subjects.add(new Subject("MATH6030001", "Linear Algebra"));
        subjects.add(new Subject("ENGL6172001", "Academic English II"));
        subjects.add(new Subject("CHAR6014001", "Character Building: Kewarganegaraan"));
        subjects.add(new Subject("COMP6049001", "Algorithm Design and Analysis"));
        subjects.add(new Subject("COMP6784001", "Fundamentals of Data Science"));
        subjects.add(new Subject("STAT6171001", "Basic Statistics"));
        subjects.add(new Subject("ENTR6486005", "Entrepreneurship Hatchery"));
        subjects.add(new Subject("COMP6799001", "Database Technology*"));
        subjects.add(new Subject("LANG6027001", "Indonesian"));
        subjects.add(new Subject("CHAR6015001", "Character Building: Agama"));
        subjects.add(new Subject("CPEN6247001", "Computer Networks"));
        subjects.add(new Subject("COMP6703001", "Web Application Development and Security"));
        subjects.add(new Subject("COMP6697001", "Operating Systems*"));
        subjects.add(new Subject("SCIE6063001", "Computational Physics"));
        subjects.add(new Subject("COMP6210001", "Ethical Hacking and Penetration Testing"));
        subjects.add(new Subject("GAME6048001", "Games Design and Programming"));
        subjects.add(new Subject("SCIE6062001", "Computational Biology"));
        subjects.add(new Subject("COMP6062001", "Compilation Techniques"));
        subjects.add(new Subject("COMP6065001", "Artificial Intelligence"));
        subjects.add(new Subject("COMP6100001", "Software Engineering*"));
        subjects.add(new Subject("COMP6705001", "Distributed Systems"));
        subjects.add(new Subject("COMP6696001", "Research Methodology in Computer Science"));
        subjects.add(new Subject("COMP6348001", "Network Forensics"));
        subjects.add(new Subject("GAME6046001", "Advanced Games Design and Programming"));

    }

    public List<Subject> getAllSubjects(String keyword) {
        if (keyword != null) {
            List<Subject> result = new ArrayList<>();
            for (Subject subject : subjects) {
                if (subject.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        subject.getCode().toLowerCase().contains(keyword.toLowerCase())) {
                    result.add(subject);
                }
            }
            return result;
        }
        return subjects;
    }


    public Subject getSubjectByName(String name) {
        return linearSearchByName(name);
    }

    public Subject getSubjectByNameBinary(String name) {
        return binarySearchByName(name);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void updateSubject(String code, Subject subject) {
        for (int i = 0; i < subjects.size(); i++) {
            Subject s = subjects.get(i);
            if (s.getCode().equals(code)) {
                subjects.set(i, subject);
                Collections.sort(subjects, Comparator.comparing(Subject::getName));
                return;
            }
        }
    }

    public void deleteSubject(String code) {
        subjects.removeIf(s -> s.getCode().equals(code));
        Collections.sort(subjects, Comparator.comparing(Subject::getName));
    }

    private Subject linearSearchByName(String name) {
        for (Subject subject : subjects) {
            if (subject.getName().equalsIgnoreCase(name)) {
                return subject;
            }
        }
        return null;
    }

    private Subject binarySearchByName(String name) {
        int left = 0;
        int right = subjects.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Subject midSubject = subjects.get(mid);
            int cmp = midSubject.getName().compareTo(name);

            if (cmp == 0) {
                return midSubject;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

//    private void quickSort(List<Subject> subjects, int low, int high) {
//        if (low < high) {
//            int pi = partition(subjects, low, high);
//
//            quickSort(subjects, low, pi - 1);
//            quickSort(subjects, pi + 1, high);
//        }
//    }

//    private int partition(List<Subject> subjects, int low, int high) {
//        String pivot = subjects.get(high).getName();
//        int i = low - 1;
//
//        for (int j = low; j < high; j++) {
//            if (subjects.get(j).getName().compareTo(pivot) < 0) {
//                i++;
//                Collections.swap(subjects, i, j);
//            }
//        }
//
//        Collections.swap(subjects, i + 1, high);
//        return i + 1;
//    }
//
//    public void quickSortByName() {
//        quickSort(subjects, 0, subjects.size() - 1);
//    }


    // Merge sort for ArrayList
    public static void mergeSortArrayList(List<Subject> list, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSortArrayList(list, low, mid);
            mergeSortArrayList(list, mid + 1, high);
            mergeArrayList(list, low, mid, high);
        }
    }

    public static void mergeArrayList(List<Subject> list, int low, int mid, int high) {
        ArrayList<Subject> temp = new ArrayList<>();
        int i = low;
        int j = mid + 1;

        while (i <= mid && j <= high) {
            if (list.get(i).getName().compareTo(list.get(j).getName()) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) {
            temp.add(list.get(i++));
        }
        while (j <= high) {
            temp.add(list.get(j++));
        }

        for (int k = low; k <= high; k++) {
            list.set(k, temp.get(k - low));
        }
    }

    // Merge sort for LinkedList
    public static void mergeSortLinkedList(List<Subject> list) {
        if (list.size() <= 1) {
            return;
        }

        LinkedList<Subject> leftHalf = new LinkedList<>();
        LinkedList<Subject> rightHalf = new LinkedList<>();
        int middle = list.size() / 2;
        for (int i = 0; i < middle; i++) {
            leftHalf.add(list.removeFirst());
        }
        while (!list.isEmpty()) {
            rightHalf.add(list.removeFirst());
        }

        mergeSortLinkedList(leftHalf);
        mergeSortLinkedList(rightHalf);

        mergeLinkedList(leftHalf, rightHalf, list);
    }

    public static void mergeLinkedList(LinkedList<Subject> left, LinkedList<Subject> right, List<Subject> result) {
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.getFirst().getName().compareTo(right.getFirst().getName()) <= 0) {
                result.add(left.removeFirst());
            } else {
                result.add(right.removeFirst());
            }
        }
        while (!left.isEmpty()) {
            result.add(left.removeFirst());
        }
        while (!right.isEmpty()) {
            result.add(right.removeFirst());
        }
    }

    // Merge sort for Queue
    public static void mergeSortQueue(Queue<Subject> queue) {
        if (queue.size() <= 1) {
            return;
        }

        // Split the queue into two halves
        Queue<Subject> left = new LinkedList<>();
        Queue<Subject> right = new LinkedList<>();
        int size = queue.size();
        for (int i = 0; i < size / 2; i++) {
            left.offer(queue.poll());
        }
        while (!queue.isEmpty()) {
            right.offer(queue.poll());
        }

        // Recursively sort both halves
        mergeSortQueue(left);
        mergeSortQueue(right);

        // Merge the sorted halves
        merge(queue, left, right);
    }

    private static void merge(Queue<Subject> queue, Queue<Subject> left, Queue<Subject> right) {
        while (!left.isEmpty() && !right.isEmpty()) {
            assert right.peek() != null;
            if (left.peek().getName().compareTo(right.peek().getName()) <= 0) {
                queue.offer(left.poll());
            } else {
                queue.offer(right.poll());
            }
        }
        while (!left.isEmpty()) {
            queue.offer(left.poll());
        }
        while (!right.isEmpty()) {
            queue.offer(right.poll());
        }
    }

    // Merge sort for Stack
    public static void mergeSortStack(Stack<Subject> stack) {
        if (stack.size() <= 1) {
            return;
        }

        // Split the stack into two halves
        Stack<Subject> left = new Stack<>();
        Stack<Subject> right = new Stack<>();
        int size = stack.size();
        for (int i = 0; i < size / 2; i++) {
            left.push(stack.pop());
        }
        while (!stack.isEmpty()) {
            right.push(stack.pop());
        }

        // Recursively sort both halves
        mergeSortStack(left);
        mergeSortStack(right);

        // Merge the sorted halves
        merge(stack, left, right);
    }

    private static void merge(Stack<Subject> stack, Stack<Subject> left, Stack<Subject> right) {
        Stack<Subject> temp = new Stack<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.peek().getName().compareTo(right.peek().getName()) <= 0) {
                temp.push(left.pop());
            } else {
                temp.push(right.pop());
            }
        }
        while (!left.isEmpty()) {
            temp.push(left.pop());
        }
        while (!right.isEmpty()) {
            temp.push(right.pop());
        }
        // Reverse the order back into the original stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }





    public void performanceTest() {
        // Adjustable sample data size
        int size = 5000;

        // ARRAY LIST & LINKED LIST TESTING
        List<Subject> subjectsArrayList = new ArrayList<>();
        List<Subject> subjectsLinkedList = new LinkedList<>();
        Queue<Subject> subjectsQueue = new LinkedList<>();
        Stack<Subject> subjectsStack = new Stack<>();

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Subject subject = new Subject("CODE" + i, "Subject " + random.nextInt(size));
            subjectsArrayList.add(subject);
            subjectsLinkedList.add(subject);
            subjectsQueue.add(subject);
            subjectsStack.push(subject);
        }
//
//        // ARRAY LIST MERGE SORT
//        long startTime = System.nanoTime();
//        mergeSortArrayList(subjectsArrayList,0, subjectsArrayList.size() - 1);
//        long endTime = System.nanoTime();
//        double arrayListTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
//        System.out.printf("Merge Sort by Name in ArrayList time: %.3f ms%n", arrayListTime);
//
//        // LINKED LIST MERGE SORT
//        startTime = System.nanoTime();
//        mergeSortLinkedList(subjectsLinkedList);
//        endTime = System.nanoTime();
//        double linkedListTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
//        System.out.printf("Merge Sort by Name in LinkedList time: %.3f ms%n", linkedListTime);
//
//        // QUEUE MERGE SORT
//        startTime = System.nanoTime();
//        mergeSortQueue(subjectsQueue);
//        endTime = System.nanoTime();
//        double queueTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
//        System.out.printf("Merge Sort by Name in Queue time: %.3f ms%n", queueTime);
//
//        // STACK MERGE SORT
//        startTime = System.nanoTime();
//        mergeSortStack(subjectsStack);
//        endTime = System.nanoTime();
//        double stackTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
//        System.out.printf("Merge Sort by Name in Stack time: %.3f ms%n", stackTime);

        // Measure time for ArrayList
        long startTime = System.nanoTime();
        QuickSortTesting.quickSortArrayList(subjectsArrayList);
        long endTime = System.nanoTime();
        double arrayListTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("QuickSort in ArrayList time: %.3f ms%n", arrayListTime);

        // Measure time for Queue
        startTime = System.nanoTime();
        QuickSortTesting.quickSortQueue(subjectsQueue);
        endTime = System.nanoTime();
        double queueTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("QuickSort in Queue time: %.3f ms%n", queueTime);

        // Measure time for Stack
        startTime = System.nanoTime();
        QuickSortTesting.quickSortStack(subjectsStack);
        endTime = System.nanoTime();
        double stackTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("QuickSort in Stack time: %.3f ms%n", stackTime);

        // Measure time for LinkedList

        startTime = System.nanoTime();
        QuickSortTesting.quickSortLinkedList(subjectsLinkedList);
        endTime = System.nanoTime();
        double linkedListTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("QuickSort in LinkedList time: %.3f ms%n", linkedListTime);

    }


}
