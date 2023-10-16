import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class TheTrueLumberCompany {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW3\\src\\input.txt"));
        PrintWriter print1 = new PrintWriter(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW3\\src\\woodHistory.txt");
        PrintWriter print2 = new PrintWriter(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW3\\src\\woodRemaining.txt");
        LinkedList OakHistory = new LinkedList();
        LinkedList CherryHistory = new LinkedList();
        Node OakHead;
        Node CherryHead;

        double discount = 0;

        String sales;
        String type;
        int amount;
        double price;

        while (sc.hasNext()) {
            sales = sc.next();

            if (Objects.equals(sales, "P")) {

                discount = Double.parseDouble(sc.next().replace("%", ""));


            } else if (Objects.equals(sales, "S")) {
                type = sc.next();
                amount = sc.nextInt();
                double total = 0;
                int totalSold = 0;
                if (Objects.equals(type, "O")) {
                    print1.println(String.format("%-3d", amount) + " Oak wood widgets requested:");
                    Node temp = OakHistory.head;
                    while (temp != null) {
                        if (temp.info.amount >= amount) {
                            double fullPrice = temp.info.getPrice(discount) * amount;
                            String x = "-> " + String.format("%-3s", amount) +
                                    " at " + String.format("%.3f", temp.info.getPrice(discount)) + " each";
                            print1.println(x + (x.length() == 20 ? "     " : "    ") +
                                    "Sales: $ " + String.format("%.3f", fullPrice) +
                                    ((discount == 0) ? "" : ("     " + discount + "% discount applied")));
                            temp.info.amount -= amount;
                            if (temp.info.amount == 0) {
                                OakHistory.freeNode(temp.info);
                            }
                            total += fullPrice;
                            totalSold += amount;
                            break;
                        } else {
                            amount -= temp.info.amount;
                            double fullPrice = temp.info.getPrice(discount) * temp.info.amount;
                            String x = "-> " + String.format("%-3s", temp.info.amount) +
                                    " at " + String.format("%.3f", temp.info.getPrice(discount)) + " each";
                            print1.println(x + (x.length() == 20 ? "     " : "    ") +
                                    "Sales: $ " + String.format("%.3f", fullPrice) +
                                    String.format("%-10s", ((discount == 0) ?
                                            "" : ("     " + discount + "% discount applied"))));
                            totalSold += temp.info.amount;
                            OakHistory.freeNode(temp.info);
                            total += fullPrice;

                        }
                        temp = temp.next;
                        if (temp == null) {
                            print1.println("   Remainder of " + amount +
                                    " pieces of Oak wood not available");
                        }
                    }
                    print1.println(String.format("%37s", "Total Sold: ") + totalSold);
                    print1.println(String.format("%39s", "Total Sale: $ ") + String.format("%.3f", total));

                }
                if (Objects.equals(type, "C")) {
                    print1.println(String.format("%-3d", amount) + " Cherry Maple wood widgets requested:");
                    Node temp = CherryHistory.head;
                    while (temp != null) {
                        if (temp.info.amount >= amount) {
                            double fullPrice = temp.info.getPrice(discount) * amount;
                            String x = "-> " + String.format("%-3s", amount) +
                                    " at " + String.format("%.3f", temp.info.getPrice(discount)) + " each";

                            print1.println(x + (x.length() == 21 ? "    " : "     ") +
                                    "Sales: $ " + String.format("%.3f", fullPrice) +
                                    ((discount == 0) ? "" : ("     " + discount + "% discount applied")));
                            temp.info.amount -= amount;
                            if (temp.info.amount == 0) {
                                CherryHistory.freeNode(temp.info);
                            }
                            total += fullPrice;
                            totalSold += amount;
                            break;
                        } else {

                            amount -= temp.info.amount;
                            double fullPrice = temp.info.getPrice(discount) * temp.info.amount;
                            print1.println("-> " + String.format("%-3s", temp.info.amount) +
                                    " at " + String.format("%.3f", temp.info.getPrice(discount)) + " each" +
                                    "     Sales: $ " + String.format("%.3f", fullPrice) +
                                    ((discount == 0) ? "" : ("     " + discount + "% discount applied")));
                            totalSold += temp.info.amount;
                            CherryHistory.freeNode(temp.info);
                            total += fullPrice;
                        }
                        temp = temp.next;
                        if (temp == null) {
                            print1.println("   Remainder of " + amount +
                                    " pieces of Cherry wood not available");
                        }
                    }
                    print1.println(String.format("%37s", "Total Sold: ") + totalSold);
                    print1.println(String.format("%39s", "Total Sale: $ ") + String.format("%.3f", total));
                }
                discount = 0;
                print1.println();

            } else if (Objects.equals(sales, "R")) {
                type = sc.next();
                amount = sc.nextInt();
                price = Double.parseDouble(sc.next().replace("$", ""));
                Wood wood = new Wood(sales, type, amount, price);
                if (Objects.equals(type, "O")) {
                    OakHistory.append(wood);
                } else {
                    CherryHistory.append(wood);
                }
            }
        }


        print2.println("The remaining Oak wood:");
        OakHead = OakHistory.head;
        while (OakHead != null) {
            print2.println(OakHead.info.amount + " purchased at " + String.format("%.3f", OakHead.info.price));
            OakHead = OakHead.next;
        }
        print2.println("\nThe remaining Cherry Maple wood:");
        CherryHead = CherryHistory.head;
        while (CherryHead != null) {
            print2.println(CherryHead.info.amount + " purchased at " + String.format("%.3f", CherryHead.info.price));
            CherryHead = CherryHead.next;
        }
        sc.close();
        print1.close();
        print2.close();
    }
}