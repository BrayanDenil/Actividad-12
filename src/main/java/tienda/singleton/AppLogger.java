package tienda.singleton;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class AppLogger {
    private static AppLogger instance;
    private List<String> logs = new ArrayList<>();
    private AppLogger() {}
    public static synchronized AppLogger getInstance() {
        if (instance == null) instance = new AppLogger();
        return instance;
    }
    public void log(String s) {
        String entry = String.format("[%s] %s", new Date().toString(), s);
        logs.add(entry);
        System.out.println("[LOG] " + entry);
    }
    public List<String> getLogs() { return Collections.unmodifiableList(logs); }
}
