package tienda.observer;

import java.util.*;
import tienda.Inventory;
import tienda.AppCatalog;
import tienda.singleton.AppLogger;
/**
 *
 * @author Usuario
 */
public class InventoryAlert implements Observer {
    private Inventory inv;
    public InventoryAlert(Inventory inv) { this.inv = inv; }
    @Override
    public void update(Subject s) {
        if (s instanceof Inventory) {
            System.out.println("[NOTIFY] Inventario modificado. Verificando agotados...");
            for (Integer id : AppCatalog.getAllProductIds()) {
                if (inv.getStock(id) <= 0) {
                    System.out.println("[ALERTA] Producto id=" + id + " está agotado.");
                    AppLogger.getInstance().log("InventoryAlert: product " + id + " is out of stock.");
                }
            }
        }
    }
}
