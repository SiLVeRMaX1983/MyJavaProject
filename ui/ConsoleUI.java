package ui;

import java.util.List;
import java.util.Scanner;
import model.Ticket;
import model.TicketStatus;
import service.TicketService;

public class ConsoleUI {

    private TicketService ticketService;

    public ConsoleUI(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {

            System.out.println("Выберите действие\n" +
                    "1. Случайный вопрос\n" +
                    "2. Все билеты\n" +
                    "3. Повторение\n" +
                    "4. Прогресс\n" +
                    "5. Выход");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.println("=== Случайный вопрос ===");
                    Ticket random = ticketService.getRandomTicket();

                    if (random == null) {
                        System.out.println("Билетов нет!");
                        break;
                    }

                    System.out.println("Вопрос: " + random.getQuestion());
                    scanner.nextLine();

                    System.out.println("Ответ: " + random.getAnswer());

                    System.out.println("Статус билета?\n1. Выучено\n2. Повторить\n3. Пропустить");
                    int stat = scanner.nextInt();
                    scanner.nextLine();

                    if (stat == 1)
                        ticketService.updateStatus(random, TicketStatus.LEARNED);
                    else if (stat == 2)
                        ticketService.updateStatus(random, TicketStatus.REPEAT);

                    break;

                case 2:
                    System.out.println("=== Все билеты ===");
                    List<Ticket> all = ticketService.getAllTickets();
                    for (Ticket t : all) {
                        System.out.println(t.getQuestion() + " | " + t.getStatus());
                    }
                    break;

                case 3:
                    System.out.println("=== Билеты для повторения ===");
                    List<Ticket> repeatList = ticketService.getTicketsByStatus(TicketStatus.REPEAT);
                    for (Ticket t : repeatList) {
                        System.out.println(t.getQuestion());
                    }
                    break;

                case 4:
                    double progress = ticketService.getProgress();
                    System.out.println("Ваш прогресс: " + progress + "%");
                    break;

                case 5:
                    System.out.println("Выход...");
                    break;

                default:
                    System.out.println("Неверный ввод!");
            }
        }

        scanner.close();
    }
}
