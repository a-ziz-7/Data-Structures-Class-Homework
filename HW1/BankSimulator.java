import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class BankSimulator {

    //                       id       index
    protected static HashMap<Integer, Integer> customerData = new HashMap<>();
    protected static Customer[] allCustomers = new Customer[10];
    protected static int numCustomers = 0;

    public static void main(String[] args) throws Exception {

        Scanner scanner1 = new Scanner(
                new File("C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW1\\src\\input1.txt"));
        scanner1.nextLine(); // ignores the first line
        Scanner scanner2 = new Scanner(
                new File("C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW1\\src\\input2.txt"));
        scanner2.nextLine(); // ignores the first line

        readFile1(scanner1);
        readFile2(scanner2);

        PrintWriter outputFile = new PrintWriter(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW1\\src\\out.txt");

        for (int i = 0; i < numCustomers; i++) {
            // evaluates all the transactions
            allCustomers[i].evaluate();
            // getInvoice returns a proper formatted string
            outputFile.println(allCustomers[i].getInvoice());
        }

        scanner1.close();
        scanner2.close();
        outputFile.close();

    }

    // saves data from master file to Customer object
    public static void readFile1(Scanner sc) {

        while (sc.hasNext()) {

            int customerNum = sc.nextInt();
            // if customer number does not exist then skip it, else save the data
            if (IDOccurrence(customerNum, true)) {
                sc.nextLine();
                continue;
            }
            String name = String.format("%-20s", (sc.next() + " " + sc.next()));
            double balance = sc.nextDouble();
            customerData.put(customerNum, numCustomers);
            // creates new Customer object
            // data passes to the constructor
            allCustomers[numCustomers] = new Customer(customerNum, name, balance);
            numCustomers++;

        }
    }

    public static void readFile2(Scanner sc) {

        while (sc.hasNext()) {

            int num = sc.nextInt();
            // if customer number does not exist in the system, then skip it
            if (!IDOccurrence(num, true)) {
                sc.nextLine();
                continue;
            }
            Customer customer = allCustomers[customerData.get(num)];

            String operation = sc.next();
            // amount and type of data differs for "O" and "P"
            // reading for "O" line
            if (Objects.equals(operation, "O")) {
                String temp = sc.nextLine();
                // temporary scanner
                Scanner tsc = new Scanner(temp);
                double discount = count(temp) == 5 ? tsc.nextDouble() : 0;
                String item = String.format("%-20s", tsc.next());
                double price = tsc.nextInt() * tsc.nextDouble();
                int transaction = tsc.nextInt();
                if (!IDOccurrence(transaction, false)) {
                    continue;
                }
                customer.setTransactions(transaction, operation, item, price, discount);
            }
            // reading for "P" line
            if (Objects.equals(operation, "P")) {
                String temp = sc.nextLine();
                // temporary scanner
                Scanner tsc = new Scanner(temp);
                double discount = count(temp) == 3 ? tsc.nextDouble() : 0;
                double price = tsc.nextDouble();
                int transaction = tsc.nextInt();
                if (!IDOccurrence(transaction, false)) {
                    continue;
                }
                customer.setTransactions(transaction, operation, String.format("%-20s", "Payment"), price, discount);
            }
        }
    }

    // checks if customer number already exists in the bank's system (if b is True)
    // checks if transaction number already exists in the bank's system (if b is False)
    public static boolean IDOccurrence(int num, boolean b) {

        boolean bool = false;
        if (b) {// for customer number
            for (int i = 0; i < numCustomers; i++) {
                if (allCustomers[i].customerNum == num) {
                    bool = true;
                    break;
                }
            }
            return bool;
        } else {// for transaction number
            for (int j = 0; j < numCustomers; j++) {
                Customer customer = allCustomers[j];
                for (int i = 0; i < customer.counter; i++) {
                    if (customer.transactions[i] == num) {
                        return false;
                    }
                }
            }
            return true;
        }

    }

    // default count method
    public static int count(String s) {

        int x = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                x++;
            }
        }
        return x;
    }

}
