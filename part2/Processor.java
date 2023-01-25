import java.util.ArrayList;
import java.util.List;

public class Processor implements Comparable<Processor> {
    private final int id;
    private final List<Task> processedTasks;

    public int getId() {
        return id;
    }

    public List<Task> getProcessedTasks() {
        return processedTasks;
    }

    public Processor(int id) {
        this.id = id;
        this.processedTasks = new ArrayList<>();
    }

    public void addProcessedTask(Task task) {
        processedTasks.add(task);
    }

    @Override
    public int compareTo(Processor o) {
        // if this active time is more, this processor has worked more
        if (this.getActiveTime() > o.getActiveTime()) {
            return -1;
        }
        // if this active time is less, this processor has worked less, so this should be selected
        else if (this.getActiveTime() < o.getActiveTime()) {
            return 1;
        }
        // select the processor with the min id
        else {
            return this.id < o.getId() ? 1 : -1;
        }
    }

    public int getActiveTime() {
        if (processedTasks.isEmpty()) {
            return 0;
        } else {
            int sum = 0;
            for (Task task : processedTasks) {
                sum += task.time();
            }
            return sum;
        }
    }

    private String getAnalyticalTime() {
        if (processedTasks.isEmpty()) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(":");
            for (Task task : processedTasks) {
                builder.append(" ").append(task.time());
            }
            return builder.toString();
        }
    }

    @Override
    public String toString() {
        return "id " + id + ", load=" + getActiveTime() + getAnalyticalTime();
    }
}
