package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Ticket;
import model.TicketStatus;

public class TicketService {
    private List<Ticket> tickets;
    private Random random;

    public TicketService(DataBaseService dbService) {
    this.tickets = dbService.loadTickets(); 
    this.random = new Random();
    }


    public List<Ticket> getAllTickets() {
        return tickets;
    }

    public List<Ticket> getTicketsByStatus(TicketStatus status) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getStatus() == status) {
                result.add(t);
            }
        }
        return result;
    }

    public Ticket getRandomTicket() {
        if (tickets == null || tickets.isEmpty()) {
            System.out.println("Нет билетов!");
            return null;
        }
        int index = random.nextInt(tickets.size());
        return tickets.get(index);
    }

    public double getProgress() {
        if (tickets == null || tickets.isEmpty()) {
            return 0;
        }
        int learned = 0;
        for (Ticket t : tickets) {
            if (t.getStatus() == TicketStatus.LEARNED) {
                learned++;
            }
        }
        return (double) learned / tickets.size() * 100;
    }

    public void updateStatus(Ticket ticket, TicketStatus newStatus) {
        if (ticket != null) {
            ticket.setStatus(newStatus);
        } else {
            System.out.println("вам повезло, пустой билет");
        }
    }

   public List<String> getAnswerOptions(Ticket ticket) {
    List<String> options = new ArrayList<>(ticket.getOptions());
    java.util.Collections.shuffle(options);
    return options;
}

}
