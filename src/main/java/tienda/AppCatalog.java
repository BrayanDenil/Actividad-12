package tienda;
import java.util.*;
import tienda.models.Product;
import tienda.models.SimpleProduct;
/**
 *
 * @author Usuario
 */
public class AppCatalog {
    private static Map<Integer,Product> products = new LinkedHashMap<>();
    static {
        products.put(1, new SimpleProduct(1, "Camiseta", 85.00));
        products.put(2, new SimpleProduct(2, "Pantalones", 120.00));
        products.put(3, new SimpleProduct(3, "Zapatos", 250.00));
        products.put(4, new SimpleProduct(4, "Taza", 35.00));
        products.put(5, new SimpleProduct(5, "Auriculares", 160.00));
    }
    public static Product getProductById(int id) { return products.get(id); }
    public static Collection<Product> listAll() { return products.values(); }
    public static Set<Integer> getAllProductIds() { return products.keySet(); }
}
