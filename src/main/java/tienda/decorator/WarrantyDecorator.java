package tienda.decorator;
import tienda.models.Product;
/**
 *
 * @author Usuario
 */
public class WarrantyDecorator extends ProductDecorator {
    public WarrantyDecorator(Product p) { super(p); }
    public String getDescription() { return wrapped.getDescription() + " + Extended Warranty"; }
    public double getCost() { return wrapped.getCost() * 1.10; }
}
