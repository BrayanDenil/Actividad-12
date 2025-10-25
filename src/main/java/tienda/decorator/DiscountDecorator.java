package tienda.decorator;
import tienda.models.Product;
/**
 *
 * @author Usuario
 */
public class DiscountDecorator extends ProductDecorator {
    private double percent;
    public DiscountDecorator(Product p, double percent) { super(p); this.percent = percent; }
    public String getDescription() { return wrapped.getDescription() + String.format(" + Discount(%.0f%%)", percent*100); }
    public double getCost() { return wrapped.getCost() * (1.0 - percent); }
}
