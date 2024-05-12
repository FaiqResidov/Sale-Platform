public class Product {
    public Product(String name, String code, int count, double price, double amount) {
        this.name = name;
        this.code = code;
        this.count = count;
        this.price = price;
        this.amount = amount;
    }
    @Override
    public String toString(){
        return "Name: %s Code: %s Count: %d Price: %.2f Amount %.2f".formatted(name,code,count,price,amount);
    }




    public String name;
    public String code;
    public int count;
    public double price;
    public double amount;
}
