public class Button {
    static final class Type {
        static final String newTicket = "0";
        static final String nextCustomer = "1";
    }
    String name;
    String type;
    String sender;
    Button(String name, String type, String sender) {
        this.name = name;
        this.type = type;
        this.sender = sender;
    }
}
