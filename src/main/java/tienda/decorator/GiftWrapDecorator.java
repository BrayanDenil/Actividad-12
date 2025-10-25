package tienda.decorator;
import tienda.models.Product;
/**
 *
 * @author Usuario
 */
public class GiftWrapDecorator extends ProductDecorator {
    public GiftWrapDecorator(Product p) { super(p); }
    public String getDescription() { return wrapped.getDescription() + " + GiftWrap"; }
    public double getCost() { return wrapped.getCost() + 5.00; }
}
