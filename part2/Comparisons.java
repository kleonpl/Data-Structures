import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Comparisons {
    public static final int TASKS_100 = 100;
    public static final int TASKS_250 = 250;
    public static final int TASKS_500 = 500;
    private static int id = 0;
    private static final Random random = new Random();

    private static final List<List<Integer>> tasks100 = new ArrayList<>();
    private static final List<List<Integer>> tasks250 = new ArrayList<>();
    private static final List<List<Integer>> tasks500 = new ArrayList<>();

    static void populateTasks() {
        for (int i = 0; i < 10; i++) {
            tasks100.add(new ArrayList<>());
            for (int j = 0; j < TASKS_100; j++) {
                tasks100.get(i).add(random.nextInt());
            }
        }
        for (int i = 0; i < 10; i++) {
            tasks250.add(new ArrayList<>());
            for (int j = 0; j < TASKS_250; j++) {
                tasks250.get(i).add(random.nextInt());
            }
        }

        for (int i = 0; i < 10; i++) {
            tasks500.add(new ArrayList<>());
            for (int j = 0; j < TASKS_500; j++) {
                tasks500.get(i).add(random.nextInt());
            }
        }
    }

    static List<Long> calculateFor100Tasks() {
        int sumOfMakespans = 0;
        int sumOfSortedMakespans = 0;
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> timeValues = tasks100.get(i);
            input.add("10");
            input.add("100");
            for (int j = 0; j < 100; j++) {
                input.add(id++ + " " + timeValues.get(i));
            }
            sumOfMakespans += Greedy.handleInput(input);
            Sort.heapSort(input);
            sumOfSortedMakespans += Greedy.handleInput(input);
        }
        List<Long> results = new ArrayList<>();
        results.add((long) sumOfMakespans / 10);
        results.add((long) sumOfSortedMakespans / 10);
        return results;
    }

    static List<Long> calculateFor250Tasks() {
        int sumOfMakespans = 0;
        int sumOfSortedMakespans = 0;
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            input.add("15");
            input.add("250");
            List<Integer> timeValues = tasks250.get(i);
            for (int j = 0; j < 250; j++) {
                input.add(id++ + " " + timeValues.get(i));
            }
            sumOfMakespans += Greedy.handleInput(input);
            Sort.heapSort(input);
            sumOfSortedMakespans += Greedy.handleInput(input);
        }
        List<Long> results = new ArrayList<>();
        results.add((long) sumOfMakespans / 10);
        results.add((long) sumOfSortedMakespans / 10);
        return results;
    }

    static List<Long> calculateFor500Tasks() {
        int sumOfMakespans = 0;
        int sumOfSortedMakespans = 0;
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            input.add("22");
            input.add("500");
            List<Integer> timeValues = tasks250.get(i);
            for (int j = 0; j < 500; j++) {
                input.add(id++ + " " + timeValues.get(i));
            }
            sumOfMakespans += Greedy.handleInput(input);
            Sort.heapSort(input);
            sumOfSortedMakespans += Greedy.handleInput(input);
        }
        List<Long> results = new ArrayList<>();
        results.add((long) sumOfMakespans / 10);
        results.add((long) sumOfSortedMakespans / 10);
        return results;
    }

    public static void main(String[] args) {
        populateTasks();
        List<Long> results = calculateFor100Tasks();
        System.out.println(results.get(0) + " " + results.get(1));
    }
}
