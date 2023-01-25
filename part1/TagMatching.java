import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class TagMatching {
    static StringStack<String> tags = new StringStackImpl<>();          // stack where the new opening tags are saved

    public static void main(String[] args) {
        boolean nonMatchingAlert = false;                               // if non matching tags get found, this is true

        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;                                               // read the document line by line
            boolean tagStarted = false;                                // false until the "<" is found

            while ((line = br.readLine()) != null) {
                char currentChar;

                for (int i = 0; i < line.length(); i++) {
                    currentChar = line.charAt(i);                       // anylize char by char 
                    if (currentChar == '<') {
                        tagStarted = true;
                        sb.setLength(0);                                // tag is found, initiate the string builder to catch the tag 
                    } else if (tagStarted && currentChar != '>') {
                        sb.append(currentChar);                         // append every new char 
                    } else if (currentChar == '>' && tagStarted) {
                        tagStarted = false;                             // end char was found
                        if (sb.toString().trim().charAt(0) == '/') {    // if "/" is found, it is a closing tag so pop the last element in stack and check if they are same
                            if (tags.peek().equalsIgnoreCase(sb.toString().trim().substring(1))) {
                                tags.pop();
                                sb.setLength(0);
                            } else {    // non matching tags are found, rise alert and break the loop
                                nonMatchingAlert = true;
                                break;
                            }
                        } else {         // just another opening tag, push it in the stack
                            tags.push(sb.toString().trim());
                            sb.setLength(0);
                        }
                    }
                }
            }
        } catch (NoSuchElementException | IOException e) {
            e.printStackTrace();
            nonMatchingAlert = true;
        }

        if(!nonMatchingAlert || tags.isEmpty()){
            System.out.println("MATCH!");
        } else {
            System.out.println("TAGS ARE MISSING!");
        }
    }
}
