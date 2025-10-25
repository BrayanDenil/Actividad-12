package tienda_proyect;
import java.util.Scanner;
import tienda.Cart;
import tienda.AppCatalog;
import tienda.Inventory;
import tienda.facade.CheckoutFacade;
import tienda.observer.CartTotalObserver;
import tienda.singleton.AppLogger;
import tienda.models.Product;
/**
 *
 * @author Usuario
 */
public class Tienda_Proyect {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();
       
        for (Integer id : AppCatalog.getAllProductIds()) inv.setStock(id, 10);

        Cart cart = new Cart();

        // Observers
        CartTotalObserver totalDisplay = new CartTotalObserver();
        cart.register(totalDisplay);
   
        inv.register(new tienda.observer.InventoryAlert(inv));

        AppLogger.getInstance().log("Aplicación iniciada.");

        CheckoutFacade facade = new CheckoutFacade(cart, inv);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Tienda Online (CLI) ===");
            System.out.println("1) Listar productos disponibles");
            System.out.println("2) Agregar producto al carrito");
            System.out.println("3) Eliminar producto del carrito");
            System.out.println("4) Ver carrito y aplicar decoradores");
            System.out.println("5) Checkout");
            System.out.println("6) Ver logs");
            System.out.println("7) Salir");
            System.out.print("Seleccione una opción: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    System.out.println("Productos:");
                    for (Product p : AppCatalog.listAll()) {
                        System.out.println(p.toString() + " | Stock=" + inv.getStock(p.getId()));
                    }
                    break;
                case "2":
                    System.out.print("Id del producto a agregar: ");
                    int idAdd = readInt(sc);
                    Product prodAdd = AppCatalog.getProductById(idAdd);
                    if (prodAdd == null) { System.out.println("Producto no encontrado."); break; }
                    System.out.print("Cantidad: ");
                    int qtyAdd = readInt(sc);
                    if (inv.getStock(idAdd) < qtyAdd) {
                        System.out.println("No hay suficiente stock (stock="+inv.getStock(idAdd)+")");
                        break;
                    }
                    cart.addItem(prodAdd, qtyAdd);
                    AppLogger.getInstance().log("Agregado al carrito: " + prodAdd.getDescription() + " x" + qtyAdd);
                    break;
                case "3":
                    System.out.print("Id del producto a eliminar: ");
                    int idRem = readInt(sc);
                    Product prodRem = AppCatalog.getProductById(idRem);
                    if (prodRem == null) { System.out.println("Producto no encontrado."); break; }
                    System.out.print("Cantidad a eliminar: ");
                    int qtyRem = readInt(sc);
                    cart.removeItem(prodRem, qtyRem);
                    AppLogger.getInstance().log("Eliminado del carrito: " + prodRem.getDescription() + " x" + qtyRem);
                    break;
                case "4":
                    tienda.util.UiHelpers.viewAndDecorateCart(sc, cart);
                    break;
                case "5":
                    System.out.print("Ingrese número de tarjeta (simulado): ");
                    String card = sc.nextLine().trim();
                    boolean ok = facade.checkout(card);
                    if (ok) System.out.println("Checkout completado.");
                    else System.out.println("Checkout falló. Revise mensajes.");
                    break;
                case "6":
                    System.out.println("Logs del sistema:");
                    for (String l : AppLogger.getInstance().getLogs()) System.out.println(l);
                    break;
                case "7":
                    running = false;
                    System.out.println("Saliendo...");
                    AppLogger.getInstance().log("Aplicación finalizada por el usuario.");
                    break;
                default:
                    System.out.println("Opción desconocida.");
            }
        }
        sc.close();
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
