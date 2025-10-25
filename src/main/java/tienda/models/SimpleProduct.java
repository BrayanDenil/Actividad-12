package tienda.models;
/**
 *
 * @author Usuario
 */
public class SimpleProduct implements Product {
    private int id;
    private String name;
    private double price;

    public SimpleProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getDescription() { return name; }
    public double getCost() { return price; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return String.format("[%d] %s - Q%.2f", id, name, price);
    }
}
