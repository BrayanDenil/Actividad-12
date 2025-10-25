package tienda;
import java.util.*;
import tienda.observer.Observer;
import tienda.observer.Subject;
import tienda.models.Product;
/**
 *
 * @author Usuario
 */
public class Cart implements Subject {
    private Map<Product,Integer> items = new LinkedHashMap<>();
    private List<Observer> observers = new ArrayList<>();

    public void addItem(Product p, int qty) {
        items.put(p, items.getOrDefault(p,0) + qty);
        notifyObservers();
    }

    public void removeItem(Product p, int qty) {
        if (!items.containsKey(p)) return;
        int current = items.get(p);
        int remain = current - qty;
        if (remain <= 0) items.remove(p);
        else items.put(p, remain);
        notifyObservers();
    }

    public Map<Product,Integer> getItems() { return Collections.unmodifiableMap(items); }

    public double getTotal() {
        double sum = 0;
        for (Map.Entry<Product,Integer> e : items.entrySet()) {
            sum += e.getKey().getCost() * e.getValue();
        }
        return sum;
    }

    public void clear() { items.clear(); notifyObservers(); }

    public void register(Observer o) { observers.add(o); }
    public void unregister(Observer o) { observers.remove(o); }
    public void notifyObservers() { for (Observer o : observers) o.update(this); }
}
