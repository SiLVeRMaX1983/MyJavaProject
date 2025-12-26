package ui;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        ConsoleUI consoleUI = new ConsoleUI(ticketService, dbService, scanner);

        // запускаем меню
        consoleUI.menu();
    }
}
