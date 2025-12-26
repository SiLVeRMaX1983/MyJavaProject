package ui;

import service.DataBaseService;
import service.TicketService;

public class App {
    public static void main(String[] args) {

        System.out.println("=====ЭКЗАМЕНАТОР=====");

        // создаём сервис для работы с базой
        DataBaseService dbService = new DataBaseService();

        // загружаем билеты из базы
        TicketService ticketService = new TicketService(dbService);

        // создаём консольный интерфейс
        ConsoleUI consoleUI = new ConsoleUI(ticketService);

        // запускаем меню
        consoleUI.menu();
    }
}
