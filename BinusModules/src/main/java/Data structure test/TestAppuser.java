import java.util.*;
import java.util.logging.Logger;

public class TestAppuser {
    private static final int ELEMENT_COUNT = 1000;
    private static final int SEARCH_COUNT = 100;
    private static final Logger logger = Logger.getLogger(TestAppuser.class.getName());

    public static void main(String[] args) {
        // Generate random AppUser data
        ArrayList<AppUser> randomData = new ArrayList<>();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            randomData.add(new AppUser(
                    "FirstName" + i,
                    "LastName" + i,
                    "email" + i + "@example.com",
                    "password" + i,
                    i % 2 == 0 ? AppUserRole.USER : AppUserRole.ADMIN
            ));
        }

        // Compare performance of ArrayList, HashSet, SingletonList, and Graph
        compareArrayList(randomData);
        compareHashSet(randomData);
        compareSingletonList(randomData);
        compareGraph(randomData);
    }

    private static void compareArrayList(ArrayList<AppUser> data) {
        ArrayList<AppUser> arrayList = new ArrayList<>();

        // Add elements
        long startTime = System.nanoTime();
        for (AppUser item : data) {
            arrayList.add(item);
        }
        long endTime = System.nanoTime();
        logger.info("ArrayList Add: " + (endTime - startTime) / 1e6 + " ms");

        // Search elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            arrayList.contains(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("ArrayList Search: " + (endTime - startTime) / 1e6 + " ms");

        // Remove elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            arrayList.remove(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("ArrayList Remove: " + (endTime - startTime) / 1e6 + " ms");
    }

    private static void compareHashSet(ArrayList<AppUser> data) {
        HashSet<AppUser> hashSet = new HashSet<>();

        // Add elements
        long startTime = System.nanoTime();
        for (AppUser item : data) {
            hashSet.add(item);
        }
        long endTime = System.nanoTime();
        logger.info("HashSet Add: " + (endTime - startTime) / 1e6 + " ms");

        // Search elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            hashSet.contains(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("HashSet Search: " + (endTime - startTime) / 1e6 + " ms");

        // Remove elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            hashSet.remove(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("HashSet Remove: " + (endTime - startTime) / 1e6 + " ms");
    }

    private static void compareSingletonList(ArrayList<AppUser> data) {
        // Create a SingletonList
        List<AppUser> singletonList = Collections.singletonList(data.get(0));

        // Add elements (reassign the list to a new singleton list)
        long startTime = System.nanoTime();
        for (AppUser item : data) {
            singletonList = Collections.singletonList(item);
        }
        long endTime = System.nanoTime();
        logger.info("SingletonList Add (reassign): " + (endTime - startTime) / 1e6 + " ms");

        // Search elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            singletonList.contains(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("SingletonList Search: " + (endTime - startTime) / 1e6 + " ms");

        // Removing elements from a SingletonList is not allowed as it is immutable
        // Log the benefit of immutability for AppUser objects
        logger.info("SingletonList is immutable, which is beneficial for AppUser objects to ensure data integrity.");
    }

    private static void compareGraph(ArrayList<AppUser> data) {
        Graph graph = new Graph();

        // Add elements
        long startTime = System.nanoTime();
        for (AppUser item : data) {
            graph.addNode(item);
        }
        long endTime = System.nanoTime();
        logger.info("Graph Add: " + (endTime - startTime) / 1e6 + " ms");

        // Search elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            graph.containsNode(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("Graph Search: " + (endTime - startTime) / 1e6 + " ms");

        // Remove elements
        startTime = System.nanoTime();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            graph.removeNode(data.get(randomIndex(data.size())));
        }
        endTime = System.nanoTime();
        logger.info("Graph Remove: " + (endTime - startTime) / 1e6 + " ms");
    }

    private static int randomIndex(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }
}

class AppUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AppUserRole appUserRole;

    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return email.equals(appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

enum AppUserRole {
    USER,
    ADMIN
}

class Graph {
    private Map<AppUser, List<AppUser>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addNode(AppUser node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public boolean containsNode(AppUser node) {
        return adjList.containsKey(node);
    }

    public void removeNode(AppUser node) {
        adjList.values().forEach(e -> e.remove(node));
        adjList.remove(node);
    }
}
