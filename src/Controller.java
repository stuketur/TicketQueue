public class Controller {
    Button btn0 = new Button("Kasse 1", Button.Type.newTicket, IO.Sender.BTN0);
    Button btn1 = new Button("Kasse 2", Button.Type.newTicket, IO.Sender.BTN1);
    Button btn2 = new Button("Kasse 3", Button.Type.newTicket, IO.Sender.BTN2);
    Button btn3 = new Button("Kasse 4", Button.Type.newTicket, IO.Sender.BTN3);

    Button btnNew = new Button("Ny billett", Button.Type.nextCustomer, IO.Sender.BTNNEW);

    Screen screen0 = new Screen("Kasse 1", Screen.Type.small, IO.Receiver.SCR0);
    Screen screen1 = new Screen("Kasse 2", Screen.Type.small, IO.Receiver.SCR1);
    Screen screen2 = new Screen("Kasse 3", Screen.Type.small, IO.Receiver.SCR2);
    Screen screen3 = new Screen("Kasse 4", Screen.Type.small, IO.Receiver.SCR3);

    Screen screenMain = new Screen("Hovedskjerme", Screen.Type.main, IO.Receiver.SCRMAIN);

    Register register0 = new Register();
    Register register1 = new Register();
    Register register2 = new Register();
    Register register3 = new Register();
}


