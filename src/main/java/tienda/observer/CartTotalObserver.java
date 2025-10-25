package tienda.observer;

import tienda.Cart;
import java.text.DecimalFormat;
/**
 *
 * @author Usuario
 */
public class CartTotalObserver implements Observer {
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void update(Subject s) {
        if (s instanceof Cart) {
            Cart c = (Cart) s;
            System.out.println("[NOTIFY] Carrito actualizado. Nuevo total: Q" + df.format(c.getTotal()));
        }
    }
}
