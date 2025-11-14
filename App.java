import java.io.File;
import java.util.List;
import model.Ticket;
import service.FileService;
import service.TicketService;
import ui.ConsoleUI;

public class App {
    public static void main(String[] args) {

        System.out.println("=====ЭКЗАМЕНАТОР=====");

        File vopros = new File("vopros.txt");

        FileService fileService = new FileService();

        List<Ticket> tickets = fileService.loadTickets(vopros.getPath());

        if (tickets == null || tickets.isEmpty()) {
            System.out.println("Не удалось загрузить вопросы из файла.");
            return;
        }

        TicketService ticketService = new TicketService(tickets);

        ConsoleUI consoleUI = new ConsoleUI(ticketService);

        consoleUI.menu();

        fileService.saveTickets(vopros.getPath(), tickets);
    }
}