import java.util.Objects;

public class Customer {

    protected int customerNum;
    protected String name;
    protected double balance;
    protected double balanceDue;

    protected int counter = 0;

    protected int[] transactions = new int[5];
    protected String[] operations = new String[5];
    protected String[] items = new String[5];
    protected double[] prices = new double[5];
    protected double[] discounts = new double[5];

    protected Customer(int customerNum, String fullName, double balance) {

        this.customerNum = customerNum;
        this.name = fullName;
        this.balance = balance;
        this.balanceDue = balance;

    }

    protected void setTransactions(int transaction, String operation, String item, double price, double discount) {

        operations[counter] = operation;
        items[counter] = item;
        prices[counter] = price;
        transactions[counter] = transaction;
        discounts[counter] = discount;

        counter++;

    }

    protected void evaluate() {
        // counter represents the amount of transactions
        for (int i = 0; i < counter; i++) {
            double temp;
            if (Objects.equals(operations[i], "P")) {
                temp = prices[i] * (1 + (discounts[i] / 100));
                // subtraction because it is a payment, so balanceDue goes down
                balanceDue -= temp;
            } else {
                // price array stores the whole price (amount_of_units * price_of_one_unit)
                temp = prices[i] * (1 - (discounts[i] / 100));
                // balanceDue stores the balance after all the transactions were processed
                // addition because it is an order, so balanceDue goes up
                balanceDue += temp;
            }
            // other method deals with the cases where the value of balanceDue is negative
            // temp stores the price after all the discounts were evaluated
            prices[i] = temp;
        }

    }

    public String getInvoice() {

        String ans = "******************************************\n\n";

        ans += (name + "    " + customerNum + "\n\n");

        ans += (String.format("%25s", "Previous Balance:") +
                String.format("%15s", ("$" + String.format("%.4f", balance) + "\n\n")));

        if (counter == 0) {
            ans += ("No transactions yet.\n");
        }
        for (int i = 0; i < counter; i++) {
            ans += (transactions[i] + String.format("%24s", items[i]) +
                    String.format("%10s", String.format("%.4f", prices[i])) + "\n");
        }

        ans += (balanceDue > 0 ?
                (String.format("\n%20s", "Balance Due:") +
                        String.format("%19s", ("$" + String.format("%.4f", balanceDue) + "\n"))) :
                (String.format("\n%22s", "Balance Owned:") +
                        String.format("%17s", ("$" + String.format("%.4f", -1 * balanceDue) + "\n"))));

        ans += ("\n******************************************\n\n\n");

        return ans;

    }

}
