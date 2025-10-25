package tienda.decorator;
import tienda.models.Product;

/**
 *
 * @author Usuario
 */
public abstract class ProductDecorator implements Product {
    protected Product wrapped;
    public ProductDecorator(Product p) { this.wrapped = p; }
    public int getId() { return wrapped.getId(); }
}
