package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Ticket;
import model.TicketStatus;

public class TicketService {


    private List<Ticket> tickets;

    private Random random;

    public TicketService(List<Ticket> tickets) {
        this.tickets = tickets;
        this.random = new Random();
    }

    public List<Ticket> getAllTickets(){
        return tickets;
    }

    public List<Ticket> getTicketsByStatus(TicketStatus status) {
        List<Ticket> result = new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            if (t.getStatus() == status) {
                result.add(t);
            }
        }
        return result;
    }

       public Ticket getRandomTicket() {
        if (tickets == null || tickets.size() == 0) {
            System.out.println("Нет билетов!");
            return null;
        } else {
            int index = random.nextInt(tickets.size());
            return tickets.get(index);
        }
    }
    
    public double getProgress() {
        if (tickets == null || tickets.size() == 0) {
            return 0;
        }

        int learned = 0;
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getStatus() == TicketStatus.LEARNED) {
                learned++;
            }
        }
        double percent = (double) learned / tickets.size() * 100;
        return percent;
    }

      public void updateStatus(Ticket ticket, TicketStatus newStatus) {
        if (ticket != null) {
            ticket.setStatus(newStatus);
        } 
        else {
            System.out.println("вам повезло, пустой билет");
        }
    }


}
