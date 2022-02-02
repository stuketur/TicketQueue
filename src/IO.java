public class IO {
    static final class Commands {
        static final String NEWTICKET = "NEW"; // In from controller: Customer clicked on a New ticket button
        static final String NEXTICKET = "NXT"; // In from controller: Staff clicked on a Next customer button
        static final String MSGNEXT = "MNS";   // Out to controller: Show next ticket on a register screen
        static final String MSGMAIN = "MNM";   // Out to controller: Show all tickets currently serviced
        static final String PRINT = "PRT";    // Out to controller: Print next ticket
    }
    static final class Sender {
        static final String BTN0 = "00";
        static final String BTN1 = "01";
        static final String BTN2 = "02";
        static final String BTN3 = "03";
        static final String BTNNEW = "04";
        static final String APP = "05";

    }
    static final class Receiver {
        static final String REG0 = "00";
        static final String REG1 = "01";
        static final String REG2 = "02";
        static final String REG3 = "03";
        static final String SCR0 = "04";
        static final String SCR1 = "05";
        static final String SCR2 = "06";
        static final String SCR3 = "07";
        static final String SCRMAIN = "08";
        static final String PRINT = "09";
    }

    String name;
    private TicketSystem ticketSystem = new TicketSystem();

    // "$nncmdxxxx,yyyy,zzz*16";
    public String message(String sender, String cmd, String data) {
        return "$" + sender + cmd + data + "*" + createCRC(sender + cmd + data) + "\r\n";
    }
    public String message(String sender, String cmd) {
        return "$" + sender + cmd + "*" + createCRC(sender + cmd) + "\r\n";
    }

    class Message {
        String nn;
        String cmd;
        String data;
        String[] values;
        Integer crc;
    }

    protected TicketSystem getTicketSystem() {
        return ticketSystem;
    }

    protected boolean tx(String message) {
        Debug.console("IO.tx() sending message: " + message);
        return true;
    }

    // Called from class USB (not existing) running in a separate thread
    // class USB listens on an usb-port and calls io.rx(message) when a complete message has been received
    // io.rx(message) may be called directly to simulate that a message has arrived
    protected void rx(String message) {
        Debug.console("IO.rx() received message: " + message);
        Message msg =  decode(message);
        Integer ticketNumber;
        switch (msg.cmd) {
            //Commands.NEWTICKET
            case "NEW":
                // Generate new ticket, send message to controller about updating screen
                ticketNumber = ticketSystem.createTicket();
                tx(message(Sender.APP, Commands.PRINT, ticketNumber.toString()));
                break;
            //Commands.NEXTICKET
            case "NXT":
                ticketNumber = ticketSystem.serveCustomer();
                final int senderNumber = Integer.parseInt(msg.nn) + 5; // 4 + 5 = 9
                //final int registerNumber =
                final String registerScreen = "0" + String.valueOf(senderNumber); // "09"
                switch (senderNumber) {
                    case 9:
                }
                tx(message(Sender.APP, Commands.MSGNEXT, ticketNumber.toString() + "," + registerScreen));
                break;
            default: Debug.console("IO.rx: Invalid message " + msg.cmd);
                break;
        }
    }

    // message:
    //      1: No leading $ or !
    //      2: No trailing *nnn (CRC)
    private int createCRC(String message) {
        int crc = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            crc ^= c;
        }
        return crc;
    }

    // message:
    //      1: leading $ or !
    //      2: trailing *nnn (CRC)
    boolean checkCRC(String message) {
        final Message decoded = decode(message);
        return decoded.crc==null ? true : createCRC(decoded.nn + decoded.cmd + decoded.data) == decoded.crc;
    }

    Message decode(String message) {
        // Start char: $ or !
        // End char: *
        //          01234567890123456789012
        // message: $nncmdxxxx,yyyy,zzz*crc
        // Check start of message
        Message ret = new Message();
        if (message.charAt(0) == '$' || message.charAt(0) == '!') {
            ret.nn = message.substring(1, 3);
            ret.cmd = message.substring(3, 6);
            int asteriskNdx = message.indexOf('*');
            ret.data = message.substring(6, asteriskNdx);
            ret.crc = asteriskNdx > -1 ? Integer.parseInt(message.substring(asteriskNdx + 1, message.length() - 2)) : null;
            ret.values = ret.data.split(",");
        }
        return ret;
    }
}
