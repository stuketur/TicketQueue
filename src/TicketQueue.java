import java.util.Scanner;

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
        IO io = new IO("test");
        //boolean test = io.checkCRC("$nncmdxxxx,yyyy,zzz*16", 16);
        IO.Message msg = io.decode("$nncmdxxxx,yyyy,zzz*16");

        TicketSystem ticketSystem = new TicketSystem();
        //Debug.on();
        Debug.readConfig();
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
        if (choice.matches(choice)) {
            return 0;
        }
        return Integer.parseInt(scanner.nextLine());
    }
}