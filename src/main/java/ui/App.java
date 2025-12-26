package ui;

import java.util.Scanner;

import service.DataBaseService;
import service.TicketService;

public class App {
    public static void main(String[] args) {

        System.out.println("=====ЭКЗАМЕНАТОР=====");
        DataBaseService dbService = new DataBaseService();
        TicketService ticketService = new TicketService(dbService);
        Scanner scanner = new Scanner(System.in);
        ConsoleUI consoleUI = new ConsoleUI(ticketService, dbService, scanner);
        consoleUI.menu();
    }
}
