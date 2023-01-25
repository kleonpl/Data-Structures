import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {
    public static List<String> heapSort(List<String> input) {
        String processors = input.remove(0);
        String tasks = input.remove(0);
        String[] objects = new String[input.size()];
        for (int i = 0; i < input.size(); i++) {
            objects[i] = input.get(i);
        }
        int size = objects.length;

        sort(objects);
        objects = reverse(objects);

        List<String> response = new ArrayList<>();
        response.add(processors);
        response.add(tasks);
        response.addAll(Arrays.asList(objects).subList(0, size));
        return response;
    }

    private static String[] reverse(String[] objects) {
        String[] temp = new String[objects.length];
        int j = 0;
        for (int i = objects.length - 1; i >= 0; i--) {
            temp[j++] = objects[i];
        }
        return temp;
    }

    public static void sort(String[] arr) {
        buildHeap(arr);
        int size = arr.length;
        for (int i = size; i >= 1; i--) {
            exch(arr, 1, i);
            size--;
            heap(arr, size, i);
        }
    }

    private static void buildHeap(String[] arr) {
        int size = arr.length -1 ;

        for (int i = size / 2; i >= 1; i--) {
            heap(arr, size, i);
        }
    }

    private static void exch(String[] objects, int i, int j) {
        String temp = objects[0];
        objects[0] = objects[i];
        objects[i] = temp;
    }

    private static void heap(String[] arr, int size, int root) {
        int max = root;
        int left = 2 * root;
        int right = 2 * root + 1;

        if (left <= size && getTime(arr[left]) > getTime(arr[max])) {
            max = left;
        }

        if (right < size && getTime(arr[right]) > getTime(arr[max])) {
            max = right;
        }

        if (max != root) {
            exch(arr, root, max);

            heap(arr, size, max);
        }

    }

    private static int getTime(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }
}
