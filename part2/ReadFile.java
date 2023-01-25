import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static List<String> readFile(String filename) throws IllegalArgumentException {
        FileReader fileReader;
        List<String> response = null;
        try {
            fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            response = new ArrayList<>();

            /*
            Read processors number and put it in response[0]
             */
            String processorsNumber = bufferedReader.readLine();
            if (processorsNumber != null) {
                response.add(processorsNumber);
            } else {
                throw new IllegalArgumentException("Invalid processors number!");
            }

            /*
            Read tasks number and put it in response[0]
             */
            String tasksNumber = bufferedReader.readLine();
            if (tasksNumber != null) {
                response.add(tasksNumber);
            } else {
                throw new IllegalArgumentException("Invalid tasks number!");
            }

            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                response.add(line);
                count++;
            }
            if (count != Integer.parseInt(tasksNumber))
                throw new IllegalArgumentException(String.format("Expected tasks: %s, Found: %d", tasksNumber, count));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
