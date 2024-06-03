import java.util.*;

public class SubjectTest {
    private Map<String, Subject> subjectByCodeHashMap = new HashMap<>();
    private Set<Subject> subjectSetHashSet = new HashSet<>();
    private List<Subject> subjectsListArrayList = new ArrayList<>();
    private TreeMap<String, Subject> subjectTreeMap = new TreeMap<>();

    public static class Subject {
        private String name;
        private String code;

        public Subject(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public List<Subject> getAllSubjects() {
        return subjectsListArrayList;
    }

    public Subject getSubjectByCode(String code) {
        return subjectByCodeHashMap.get(code);
    }

    public void addSubject(Subject subject) {
        subjectByCodeHashMap.put(subject.getCode(), subject);
        subjectSetHashSet.add(subject);
        subjectsListArrayList.add(subject);
        subjectTreeMap.put(subject.getCode(), subject);
    }

    public void updateSubject(String code, Subject updatedSubject) {
        subjectByCodeHashMap.put(code, updatedSubject);
        for (int i = 0; i < subjectsListArrayList.size(); i++) {
            if (subjectsListArrayList.get(i).getCode().equals(code)) {
                subjectsListArrayList.set(i, updatedSubject);
                break;
            }
        }
        subjectTreeMap.remove(code);
        subjectTreeMap.put(updatedSubject.getCode(), updatedSubject);
    }

    public void deleteSubject(String code) {
        subjectByCodeHashMap.remove(code);
        Subject subjectToRemove = null;
        for (Subject subject : subjectSetHashSet) {
            if (subject.getCode().equals(code)) {
                subjectToRemove = subject;
                break;
            }
        }
        if (subjectToRemove != null) {
            subjectSetHashSet.remove(subjectToRemove);
        }
        subjectsListArrayList.removeIf(subject -> subject.getCode().equals(code));
        subjectTreeMap.remove(code);
    }

    public static void main(String[] args) {
        SubjectTest service = new SubjectTest();

        int numberOfOperations = 1000; // Number of operations to perform for each method

        // Variables to store total execution time for each operation
        long addTotalTimeHashMap = 0;
        long updateTotalTimeHashMap = 0;
        long deleteTotalTimeHashMap = 0;

        long addTotalTimeHashSet = 0;
        long updateTotalTimeHashSet = 0;
        long deleteTotalTimeHashSet = 0;

        long addTotalTimeArrayList = 0;
        long updateTotalTimeArrayList = 0;
        long deleteTotalTimeArrayList = 0;

        long addTotalTimeTreeMap = 0;
        long updateTotalTimeTreeMap = 0;
        long deleteTotalTimeTreeMap = 0;

        // Add subjects
        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.addSubject(new Subject("Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            addTotalTimeHashMap += (endTime - startTime);
        }
        System.out.println("Total time taken for adding " + numberOfOperations + " subjects using HashMap: " + addTotalTimeHashMap + " ns");

        // Update subjects
        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.updateSubject("CODE" + i, new Subject("Updated Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            updateTotalTimeHashMap += (endTime - startTime);
        }
        System.out.println("Total time taken for updating " + numberOfOperations + " subjects using HashMap: " + updateTotalTimeHashMap + " ns");

        // Delete subjects
        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.deleteSubject("CODE" + i);
            long endTime = System.nanoTime();
            deleteTotalTimeHashMap += (endTime - startTime);
        }
        System.out.println("Total time taken for deleting " + numberOfOperations + " subjects using HashMap: " + deleteTotalTimeHashMap + " ns");

        // Repeat the same process for HashSet
        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.addSubject(new Subject("Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            addTotalTimeHashSet += (endTime - startTime);
        }
        System.out.println("Total time taken for adding " + numberOfOperations + " subjects using HashSet: " + addTotalTimeHashSet + " ns");

        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.updateSubject("CODE" + i, new Subject("Updated Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            updateTotalTimeHashSet += (endTime - startTime);
        }
        System.out.println("Total time taken for updating " + numberOfOperations + " subjects using HashSet: " + updateTotalTimeHashSet + " ns");

        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.deleteSubject("CODE" + i);
            long endTime = System.nanoTime();
            deleteTotalTimeHashSet += (endTime - startTime);
        }
        System.out.println("Total time taken for deleting " + numberOfOperations + " subjects using HashSet: " + deleteTotalTimeHashSet + " ns");

                // Repeat the same process for ArrayList
        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.addSubject(new Subject("Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            addTotalTimeArrayList += (endTime - startTime);
        }
        System.out.println("Total time taken for adding " + numberOfOperations + " subjects using ArrayList: " + addTotalTimeArrayList + " ns");

        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.updateSubject("CODE" + i, new Subject("Updated Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            updateTotalTimeArrayList += (endTime - startTime);
        }
        System.out.println("Total time taken for updating " + numberOfOperations + " subjects using ArrayList: " + updateTotalTimeArrayList + " ns");

        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.deleteSubject("CODE" + i);
            long endTime = System.nanoTime();
            deleteTotalTimeArrayList += (endTime - startTime);
        }
        System.out.println("Total time taken for deleting " + numberOfOperations + " subjects using ArrayList: " + deleteTotalTimeArrayList + " ns");

        // Repeat the same process for TreeMap
        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.addSubject(new Subject("Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            addTotalTimeTreeMap += (endTime - startTime);
        }
        System.out.println("Total time taken for adding " + numberOfOperations + " subjects using TreeMap: " + addTotalTimeTreeMap + " ns");

        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.updateSubject("CODE" + i, new Subject("Updated Subject " + i, "CODE" + i));
            long endTime = System.nanoTime();
            updateTotalTimeTreeMap += (endTime - startTime);
        }
        System.out.println("Total time taken for updating " + numberOfOperations + " subjects using TreeMap: " + updateTotalTimeTreeMap + " ns");

        for (int i = 0; i < numberOfOperations; i++) {
            long startTime = System.nanoTime();
            service.deleteSubject("CODE" + i);
            long endTime = System.nanoTime();
            deleteTotalTimeTreeMap += (endTime - startTime);
        }
        System.out.println("Total time taken for deleting " + numberOfOperations + " subjects using TreeMap: " + deleteTotalTimeTreeMap + " ns");
    }
}


