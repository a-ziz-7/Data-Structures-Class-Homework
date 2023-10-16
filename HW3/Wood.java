public class Wood{

    public String sales;
    public String type ;
    public int amount;
    public double price;
    public double sellPrice;

    public Wood(String sales, String type, int amount, double price){

        this.sales = sales;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.sellPrice = price * 1.40;

    }

    public double getPrice(double discount){
        return (sellPrice) * (1-(discount/100));
    }

}
