import java.util.ArrayList;
// https://trix.ifi.uio.no/assignments/519
class TicketSystem {

    ArrayList<Ticket> tickets = new ArrayList<>();
    int teller = 0;

    /**
     * Lager et nytt objekt av type TicketSystem.Ticket, skriver ut informasjon om den og legger den til i tickets listen
     */
    int createTicket(){
        teller++;
        Ticket ticket = new Ticket(teller);
        tickets.add(ticket);

        Debug.console("Du har fått tildelt billettnr " + ticket.getNumber() + ".");
        Debug.console("Det står "+(tickets.size()-1) +" foran deg.\n");

        return teller;
    }

    /**
     * Henter og skriver ut informasjon om første kølapp og fjerner den
     * Gir feilmelding dersom kø er tom
     */
    int serveCustomer(){
        Ticket betjenes = null;
        int ticketNumber = 0;
        if(tickets.isEmpty()){
            Debug.console("ingen i kø");
        } else {
            betjenes = tickets.remove(0);
            ticketNumber = betjenes.number;
            Debug.console("Kunde med billettnr: ");
            Debug.console(betjenes.getNumber() + " betjenes.\n");
        }
        return ticketNumber;
    }

    /**
     * Returnerer antall kunder i kø
     */
    int ticketCount(){
        return tickets.size();
    }
    /**
     * skriver ut alle kunder i kø
     */
    void printTickets(){
        Debug.console(ticketCount() + " kunder i kø: ");

        for(Ticket ticket : tickets){
            Debug.console("nr." + ticket.getNumber() + ", ");
        }
        Debug.console(Debug.NL);
    }

    static class Ticket {

        int number;

        /**
         * konstruktor setter nummer på kølapp
         */
        Ticket(int number){
            this.number = number;
        }

        /**
         * returnerer kølapp-nummeret
         */
        int getNumber(){
            return number;
        }
    }
}