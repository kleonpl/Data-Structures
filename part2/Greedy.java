import java.util.Arrays;
import java.util.List;

public class Greedy {
    private static PQInterface<Processor> processors;
    private static int tasksNumber;
    private static int makespan;

    private static void printStatistics() {
        int processorsSize = processors.size();
        makespan = Integer.MIN_VALUE;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < processorsSize; i++) {
            Processor temp = processors.getmax();
            makespan = Math.max(temp.getActiveTime(), makespan);
            builder.append(temp).append("\n");
        }

        if (tasksNumber <= 50) System.out.println(builder);
        System.out.println("Makespan = " + makespan);
    }

    public static int handleInput(List<String> input) {
        if (input == null) throw new NullPointerException("Error during file reading");
        if (input.isEmpty()) throw new IllegalArgumentException("Input file is empty");
        int processorsNumber = Integer.parseInt(input.get(0));
        tasksNumber = Integer.parseInt(input.get(1));

        processors = new MaxPQ<>(processorsNumber);
        for (int i = 0; i < processorsNumber; i++) processors.insert(ProcessorGenerator.newProcessor());
        for (int i = 2; i < input.size(); i++) {
            String line = input.get(i);
            String[] words = line.split(" ");
            int[] data = Arrays.stream(words).mapToInt(Integer::parseInt).toArray();
            addTask(data);
        }
        printStatistics();
        return makespan;
    }

    private static void addTask(int[] data) {
        Processor temp = processors.getmax();
        temp.addProcessedTask(new Task(data[0], data[1]));
        processors.insert(temp);
    }

    public static void main(String[] args) {
        List<String> input = ReadFile.readFile(args[0]);
        handleInput(input);
        System.out.println("--------------------------------------");
        List<String> sortedInput = Sort.heapSort(input);
        handleInput(sortedInput);
    }
}
