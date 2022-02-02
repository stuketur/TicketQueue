import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Debug {
    private void Debug() {}
    // Empty privat constructor to prevent instances from being created (singleton)
    static String NL = System.getProperty("line.separator");
    private static boolean debugAtive = false;

    static void on() {
        debugAtive = true;
    }
    static void off() {
        debugAtive = false;
    }
    static void readConfig() {
        String isActive = "";
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("app.config")) {
            prop.load(fis);
            isActive = prop.getProperty("debugActive");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        debugAtive = isActive!= null && isActive.matches("true") ? true : false;
    }
    static void console(String msg) {
        if (debugAtive) {
            System.out.println(msg);
        }
    }
}
