import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class NetBenefit {
    static IntQueue<Integer> quantity = new IntQueueImpl<>(); // contains the information about the stocks quantity for a single transaction
    static IntQueue<Integer> price = new IntQueueImpl<>();    // contains the information about the stocks bought price for a single transaction

    static int benefit = 0;       // the result of the buying - selling proccess

    public static void main(String[] args) {
        BufferedReader br;

        try {
            FileReader fr = new FileReader(args[0]);
            br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");               // for a single line, split it in every space
                int newQuantity = Integer.parseInt(words[1]);   //quantity of the stocks taking place in this transaction
                int value = Integer.parseInt(words[3]);         //the price of the stocks taking place in this transaction

                if (line.matches("[ ]*[bB][uU][yY][ ]+[0-9]+[ ][pP][rR][iI][cC][eE][ ]+[0-9]+[ ]*")) { //regexp matching: e.g. buy 50 price 20 
                    quantity.put(newQuantity);
                    price.put(value);
                } else if (line.matches("[ ]*[sS][eE][lL][lL][ ]+[0-9]+[ ]+[pP][rR][iI][cC][eE][ ]+[0-9]+[ ]*")) {// regexp matching: e.g. sell 40 price 30
                    try {

                        // algorithm counting the profit of the current transaction
                        while (newQuantity > 0) {
                            if (newQuantity >= quantity.peek()) {           
                                newQuantity -= quantity.peek();
                                benefit += quantity.get() * (value - price.get()); // sell all the stocks of a specific genre
                            } else {
                                quantity.setData(quantity.peek() - newQuantity); //update the remaining stocks of a specific genre
                                benefit += newQuantity * (value - price.peek());
                                newQuantity = 0;
                            }
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Insufficient stocks number");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The Net Benefit is " + benefit);

    }
}
