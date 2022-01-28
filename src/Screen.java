public class Screen {
    static final class Type {
        static final String small = "0";
        static final String main = "1";
    }
    String name;
    String type;
    String sender;

    Screen(String name, String type, String sender) {
        this.name = name;
        this.type = type;
        this.sender = sender;
    }
}
