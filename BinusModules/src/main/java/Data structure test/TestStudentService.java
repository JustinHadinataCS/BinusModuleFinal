import java.util.*;
import java.util.logging.Logger;

public class TestStudentService {
    private static final int ELEMENT_COUNT = 1000;
    private static final int SEARCH_COUNT = 100;
    private static final Logger logger = Logger.getLogger(TestStudentService.class.getName());

    public static void main(String[] args) {
        // Generate random Student data
        List<Student> randomData = generateMockData();

        // Create StudentService instance
        StudentService studentService = new StudentService(new StudentRepository());

        // Compare performance with ArrayList
        comparePerformance(studentService, randomData, "ArrayList");

        // Compare performance with HashSet
        Set<Student> studentHashSet = new HashSet<>(randomData);
        comparePerformance(studentService, new ArrayList<>(studentHashSet), "HashSet");

        // Compare performance with LinkedList
        comparePerformance(studentService, new LinkedList<>(randomData), "LinkedList");


        // Compare performance with HashMap
        Map<Long, Student> studentHashMap = new HashMap<>();
        for (Student student : randomData) {
            studentHashMap.put(student.getStudentId(), student);
        }
        comparePerformance(studentService, new ArrayList<>(studentHashMap.values()), "HashMap");

    }

    private static List<Student> generateMockData() {
        List<Student> mockData = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < ELEMENT_COUNT; i++) {
            long id = generateRandomId();
            String name = generateRandomName();
            mockData.add(new Student(id, name));
        }

        return mockData;
    }

    private static long generateRandomId() {
        Random random = new Random();
        return 10000000L + random.nextInt(90000000); // Generate an 8-digit number
    }

    private static String generateRandomName() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = random.nextInt(10) + 5; // Random name length between 5 and 14 characters
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    private static void comparePerformance(StudentService studentService, List<Student> data, String dataStructure) {
        // Add elements
        long startTime = System.nanoTime();
        for (Student student : data) {
            studentService.addNewStudent(student);
        }
        long endTime = System.nanoTime();
        logger.info(dataStructure + " Add: " + (endTime - startTime) / 1e6 + " ms");

        // Search elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            studentService.getStudent();
        }
        endTime = System.nanoTime();
        logger.info(dataStructure + " Search: " + (endTime - startTime) / 1e6 + " ms");

        // Remove elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            studentService.deleteStudent(data.get(randomIndex(data.size())).getStudentId());
        }
        endTime = System.nanoTime();
        logger.info(dataStructure + " Remove: " + (endTime - startTime) / 1e6 + " ms");
    }

    static class Student {
        private Long studentId;
        private String name;

        public Student(Long studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }

        public Long getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }
    }

    static class StudentRepository {
        public void save(Student student) {
            // Simulate saving student to database
        }

        public List<Student> findAll() {
            // Simulate fetching all students from database
            return new ArrayList<>();
        }

        public void deleteById(Long studentId) {
            // Simulate deleting a student by ID from the database
        }
    }

    static class StudentService {
        private final StudentRepository studentRepository;

        public StudentService(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        public void addNewStudent(Student student) {
            studentRepository.save(student);
        }

        public List<Student> getStudent() {
            return studentRepository.findAll();
        }

        public void deleteStudent(Long studentId) {
            studentRepository.deleteById(studentId);
        }
    }

    static class Graph<T> {
        private Map<T, List<T>> adjList;

        public Graph() {
            adjList = new HashMap<>();
        }

        public void addNode(T node) {
            adjList.putIfAbsent(node, new ArrayList<>());
        }

        // Add more graph operations if needed

        public boolean containsNode(T node) {
            return adjList.containsKey(node);
        }
    }

    private static int randomIndex(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }
}
