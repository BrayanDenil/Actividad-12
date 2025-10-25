package tienda.facade;
import tienda.Cart;
import tienda.Inventory;
import tienda.models.Product;
import tienda.singleton.AppLogger;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class CheckoutFacade {
    private Cart cart;
    private Inventory inventory;
    public CheckoutFacade(Cart cart, Inventory inventory) {
        this.cart = cart; this.inventory = inventory;
    }

    public boolean reviewCart() {
        System.out.println("Facade: Revisando carrito...");
        boolean ok = true;
        for (Map.Entry<Product,Integer> e : cart.getItems().entrySet()) {
            int stock = inventory.getStock(e.getKey().getId());
            if (stock < e.getValue()) {
                System.out.println("ERROR: No hay suficiente stock para " + e.getKey().getDescription() + " (solicitado=" + e.getValue() + ", stock=" + stock + ")");
                ok = false;
            } else {
                System.out.println("OK: " + e.getKey().getDescription() + " x" + e.getValue());
            }
        }
        return ok;
    }

    public void applyDiscounts() {
        System.out.println("Facade: Aplicando descuentos (si existen políticas)...");
        double total = cart.getTotal();
        if (total > 200.0) {
            System.out.println("Se aplica descuento del 5% al carrito por compra superior a Q200.");
            Map<Product,Integer> items = new LinkedHashMap<>(cart.getItems());
            cart.clear();
            for (Map.Entry<Product,Integer> e : items.entrySet()) {
                Product p = new tienda.decorator.DiscountDecorator(e.getKey(), 0.05);
                cart.addItem(p, e.getValue());
            }
        }
    }

    public boolean chargePayment(String dummyCard) {
        System.out.println("Facade: Procesando pago con tarjeta: " + (dummyCard.length()>0?"[OCULTO]":"[VACIO]")); 
        try { Thread.sleep(300); } catch (InterruptedException ex) {}
        System.out.println("Pago procesado correctamente.");
        AppLogger.getInstance().log("Pago realizado por Q" + String.format(java.util.Locale.US, "%.2f", cart.getTotal()));
        return true;
    }

    public void generateOrder() {
        System.out.println("Facade: Generando orden y actualizando inventario...");
        for (Map.Entry<Product,Integer> e : cart.getItems().entrySet()) {
            inventory.reduceStock(e.getKey().getId(), e.getValue());
        }
        AppLogger.getInstance().log("Orden generada. Items: " + cart.getItems().size());
    }

    public void sendReceipt() {
        System.out.println("Facade: Enviando recibo (simulado).\n--- RECIBO ---");
        for (Map.Entry<Product,Integer> e : cart.getItems().entrySet()) {
            System.out.printf("%s x%d -> Q%.2f\n", e.getKey().getDescription(), e.getValue(), e.getKey().getCost()*e.getValue());
        }
        System.out.printf("TOTAL: Q%.2f\n", cart.getTotal());
        System.out.println("--- FIN RECIBO ---");
    }

    public boolean checkout(String dummyCard) {
        System.out.println("Iniciando proceso de checkout via Facade...");
        if (!reviewCart()) return false;
        applyDiscounts();
        if (!chargePayment(dummyCard)) return false;
        generateOrder();
        sendReceipt();
        cart.clear();
        return true;
    }
}
