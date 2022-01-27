import static org.junit.jupiter.api.Assertions.*;

class TicketSystemTest {
    TicketSystem ticketSystem;
    TicketSystemTest() {
        ticketSystem = new TicketSystem();
    }
    @org.junit.jupiter.api.Test
    void createTicket() {
        ticketSystem.createTicket();
        final TicketSystem.Ticket ticket0 = ticketSystem.tickets.get(0);
        assertEquals(1, ticketSystem.ticketCount());
        // Starts at 1
        assertEquals(1, ticket0.getNumber());
    }

    @org.junit.jupiter.api.Test
    void serveCustomer() {
        ticketSystem.createTicket();
        final int ticketCount = ticketSystem.ticketCount();
        ticketSystem.serveCustomer();
        assertEquals(0, ticketCount-1);
    }

    @org.junit.jupiter.api.Test
    void ticketCount() {
        final int ticketCount = ticketSystem.ticketCount();
        ticketSystem.createTicket();
        ticketSystem.createTicket();
        assertEquals(ticketCount+2, ticketSystem.ticketCount());
    }

    @org.junit.jupiter.api.Test
    void printTickets() {
    }
}