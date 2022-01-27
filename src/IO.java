import java.util.HashMap;

public class IO {
    enum SENDER {
        APP,
        CONTROLLER,
        BTN0,
        BTN1,
        BTN2,
        BTN3,
        BTNNEW,
        SCR0,
        SCR1,
        SCR2,
        SCR3,
        SCRMAIN,
        PRINT
    }
    HashMap<SENDER, String> sender = new HashMap<>();

    enum CMD {
        NEWTICKET,
        NEXTICKET,
        MSGNEXT,
        MSGMAIN,
        PRINT
    }
    HashMap<CMD, String> cmd = new HashMap<>();
    String name;

    IO(String name) {
        sender.put(SENDER.APP, "01");
        sender.put(SENDER.CONTROLLER, "02");
        sender.put(SENDER.BTN0, "03");
        sender.put(SENDER.BTN1, "04");
        sender.put(SENDER.BTN2, "05");
        sender.put(SENDER.BTN3, "06");
        sender.put(SENDER.BTNNEW, "07");
        sender.put(SENDER.SCR0, "08");
        sender.put(SENDER.SCR1, "09");
        sender.put(SENDER.SCR2, "10");
        sender.put(SENDER.SCR3, "11");
        sender.put(SENDER.SCRMAIN, "12");
        sender.put(SENDER.PRINT, "13");

        cmd.put(CMD.NEWTICKET, "001");
        cmd.put(CMD.NEXTICKET, "002");
        cmd.put(CMD.MSGNEXT, "003");
        cmd.put(CMD.MSGMAIN, "004");
        cmd.put(CMD.PRINT, "005");

        this.name = name;
    }

    class Message {
        String nn;
        String cmd;
        String[] data;
        String crc;
    }

    private boolean tx(String data) {
        return true;
    }

    private String rx(String message) {
        //Read data from USB(thread), calling this on complete message
        Message msg =  decode(message);
        //msg.nn;
        //msg.cmd;
        return "";
    }

    private int createCRC(String message) {
        int crc = 0;
        for (int i = 1; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == '*') {
                break;
            }
            crc ^= c;
        }
        return crc;
    }

    boolean checkCRC(String message, int crc) {
        return createCRC(message) == crc;
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
            String data = message.substring(6, asteriskNdx);
            ret.crc = message.substring(asteriskNdx + 1);
            ret.data = data.split(",");
        }
        return ret;
    }
}
