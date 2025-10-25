package tienda.util;
import java.util.*;
import tienda.Cart;
import tienda.models.Product;
import tienda.decorator.GiftWrapDecorator;
import tienda.decorator.WarrantyDecorator;
import tienda.decorator.DiscountDecorator;
import tienda.singleton.AppLogger;


/**
 *
 * @author Usuario
 */

public class UiHelpers {
    public static void viewAndDecorateCart(Scanner sc, Cart cart) {
        if (cart.getItems().isEmpty()) { System.out.println("Carrito vacío."); return; }
        System.out.println("Contenido del carrito:");
        int idx = 1;
        List<Product> itemList = new ArrayList<>();
        for (Map.Entry<Product,Integer> e : cart.getItems().entrySet()) {
            System.out.printf("%d) %s x%d -> Q%.2f\n", idx, e.getKey().getDescription(), e.getValue(), e.getKey().getCost()*e.getValue());
            itemList.add(e.getKey());
            idx++;
        }
        System.out.println("Total: Q" + String.format(java.util.Locale.US, "%.2f", cart.getTotal()));
        System.out.print("¿Desea aplicar un decorador a algún producto? (s/n): ");
        String resp = sc.nextLine().trim().toLowerCase();
        if (!resp.equals("s")) return;
        System.out.print("Seleccione número de item: ");
        int sel = readInt(sc);
        if (sel < 1 || sel > itemList.size()) { System.out.println("Selección invalida."); return; }
        Product original = itemList.get(sel-1);
        System.out.println("Decoradores disponibles:");
        System.out.println("1) Envoltorio de regalo (+Q5.00)");
        System.out.println("2) Garantía extendida (+10%)");
        System.out.println("3) Descuento (-X%)");
        System.out.print("Elija decorador: ");
        int d = readInt(sc);
        Product decorated = original;
        switch (d) {
            case 1:
                decorated = new GiftWrapDecorator(original);
                break;
            case 2:
                decorated = new WarrantyDecorator(original);
                break;
            case 3:
                System.out.print("Ingrese porcentaje (ej 10 para 10%): ");
                int pct = readInt(sc);
                decorated = new DiscountDecorator(original, pct/100.0);
                break;
            default:
                System.out.println("Decorador inválido.");
                return;
        }
        int qty = 0;
        for (Map.Entry<Product,Integer> e : cart.getItems().entrySet()) {
            if (e.getKey() == original) { qty = e.getValue(); break; }
        }
        cart.removeItem(original, qty);
        cart.addItem(decorated, qty);
        System.out.println("Decorador aplicado: " + decorated.getDescription() + ". Nuevo costo unidad: Q" + String.format(java.util.Locale.US, "%.2f", decorated.getCost()));
        AppLogger.getInstance().log("Decorador aplicado a producto id=" + original.getId() + ": " + decorated.getDescription());
    }

    private static int readInt(Scanner sc) {
        try {
            String s = sc.nextLine().trim();
            return Integer.parseInt(s);
        } catch (Exception ex) {
            return 0;
        }
    }
}
