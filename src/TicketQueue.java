import java.util.Scanner;

// https://www.figma.com/file/7EKS5bJLjZAZQ44VPt7C8a/Super-duper-wireframe

/*
 * Access modifiers
 * The only access modifier needed is public on our main method
 * All other members may have no modifier which gives default access modifier: package-privat

 * Access modifiers Visibility:
 *   public:     class, package, subclass in same package, subclass in other package, world
 *   protected:  class, package, subclass in same package, subclass in other package
 *   none:       class, package, subclass in same package
 *   private:    class
 * */
class TicketQueue {
    /**
     * Ekstra oppgaver:
     * 1. Opprett klassen Debug med
     * statiske metode for å lese konfigurasjon fra filen app.conf hvert minutt
     * statiske metode for å skrive meldinger til console
     * 2. Opprett en app.conf fil som innehoder:
     * debugAtive=true
     * 4. Test at funksjonaliteten fungerer
     * <p>
     * Den ene gruppen har flyttet all kode til klassene TickestSystem og TicketSystem.Ticket
     * Det er ikke en logisk oppdeling av koden med tanke på implementering på en fysisk lokasjon
     * <p>
     * TicketSystem.Ticket og TickestSystem bør kun opprette og initialisere seg selv siden det ved implementering vil
     * være nødvendig å sende meldinger og kommandoer til helt andre steder enn console.
     * Systemet vil da ikke engang ha behov for skjerm og utskrift til console blir meningsløst
     * <p>
     * Våre utskrifter til console er kun for at utvikleren under utviklingen skal se at koden gjør det den skal
     * Ofte brukes det en egen metode til dette som styres av et flag som kan slå utskrift til console av og på
     * Flagget ligger ofte utenfor koden i en egen konfigurasjonsfil som gjør at programmets funksjonalitet kan
     * endres uten å endre koden
     * <p>
     * For at det skal være mulig å lage hele systemet uten å ha tilgang til fysisk utstyr som knapper
     * og info-skjermer har vi til nå brukt en meny for å kunne velge funksjonalitet basert på et brukervalg som kommer fra
     * serverens keyboard
     * <p>
     * Ved implementering av systemet vil imidlertid kommandoene komme fra fysiske knapper som betjenes av kunder
     * eller betjening og som vil være tilkoblet en styringsenhet som tar i mot og videresender alle knappetrykk
     * til programmet og sender meldinger fra programmet til skjermer og skrivere.
     * <p>
     * Programmets struktur bør gjenspeile det fysiske og bør derfor deles opp på en måte som gjør det sømløst
     * og enkelt å implementere systemet på en fysisk lokasjon
     */
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Debug.on();
        Debug.readConfig();

        testCode();

        TicketSystem ticketSystem = new TicketSystem();

        int choice = 0;

        while (choice != 4) {
            printMeny();
            choice = getChoice();

            switch (choice) {
                case 1:
                    ticketSystem.createTicket();
                    break;
                case 2:
                    ticketSystem.serveCustomer();
                    break;
                case 3:
                    ticketSystem.printTickets();
                    break;
                case 4:
                    Debug.console("Programmet avsluttes.");
                    break;
                default:
                    Debug.console("Error: tast 1,2,3 eller 4");
                    break;
            }
        }
    }
    private static void testCode() {
        IO io = new IO();
        String message = "$nncmdxxxx,yyyy,zzz*16\r\n";
        IO.Message msg = io.decode(message);
        boolean checkCRC = io.checkCRC(message);

        /*
        // Simulate Next Customer button click from register
        message = io.message(IO.Sender.BTN0, IO.Commands.NEXTICKET);
        io.tx(message); */

        // Simulate New Ticket button click from customer
        message = io.message(IO.Sender.BTNNEW, IO.Commands.NEWTICKET);
        io.rx(message); // Simulate message from New Ticket Button

        // Simulate Next Customer button click from register
        message = io.message(IO.Sender.BTN1, IO.Commands.NEXTICKET);
        io.tx(message);

        Controller controller = new Controller();

        boolean foo = true;
    }

    static void printMeny() {
        String meny = "** MENY FOR BILLETTSYSTEM **\n";
        meny += "1 - Trekk kølapp\n";
        meny += "2 - Betjen kunde\n";
        meny += "3 - Print kø\n";
        meny += "4 - Avslutt\n";

        System.out.println(meny);
    }

    static int getChoice() {
        String choice = scanner.nextLine();
        choice = choice.replaceAll("[^\\d]", "");
        if (choice.matches("")) {
            return 0;
        }
        final int i = Integer.parseInt(choice);
        return i < 5 ? i : 0;
    }
}