<<<<<<< Updated upstream
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
            scanner.nextLine(); // считывание остатка

            switch (choice) {
                case 1:
    System.out.println("=== Случайный вопрос ===");
    Ticket random = ticketService.getRandomTicket();

    if (random == null) {
        System.out.println("Билетов нет!");
        break;
    }

    System.out.println("Вопрос: " + random.getQuestion());

    List<String> options = ticketService.getAnswerOptions(random, 3);
    for (int i = 0; i < options.size(); i++) {
        System.out.println((i + 1) + ") " + options.get(i));
    }

    // Проверка ответа пользователя
    int userAnswer = -1;
    while (true) {
        System.out.println("Введите номер вашего ответа:");
        String input = scanner.nextLine();
        try {
            userAnswer = Integer.parseInt(input);
            if (userAnswer >= 1 && userAnswer <= options.size()) {
                break;
            } else {
                System.out.println("Пожалуйста, введите число от 1 до " + options.size());
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Введите число.");
        }
    }

    String selectedAnswer = options.get(userAnswer - 1);
    if (selectedAnswer.equals(random.getAnswer())) {
        System.out.println("Правильно!");
    } else {
        System.out.println("Неправильно.");
        System.out.println("Правильный ответ: " + random.getAnswer());
    }

    // Выбор статуса билета
    System.out.println("Статус билета?\n1. Выучено\n2. Повторить\n3. Пропустить ");
    int stat = -1;
    while (true) {
        String input = scanner.nextLine();
        try {
            stat = Integer.parseInt(input);
            if (stat >= 1 && stat <= 3) {
                break;
            } else {
                System.out.println("Введите 1, 2 или 3");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Введите число.");
        }
    }

    switch (stat) {
        case 1:
            ticketService.updateStatus(random, TicketStatus.LEARNED);
            break;
        case 2:
            ticketService.updateStatus(random, TicketStatus.REPEAT);
            break;
        case 3:
            ticketService.updateStatus(random, TicketStatus.NOT_STUDIED);
            break;
    }
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
||||||| Stash base
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
            scanner.nextLine(); // считывание остатка

            switch (choice) {
                case 1:
    System.out.println("=== Случайный вопрос ===");
    Ticket random = ticketService.getRandomTicket();

    if (random == null) {
        System.out.println("Билетов нет!");
        break;
    }

    System.out.println("Вопрос: " + random.getQuestion());

    List<String> options = ticketService.getAnswerOptions(random, 3);
    for (int i = 0; i < options.size(); i++) {
        System.out.println((i + 1) + ") " + options.get(i));
    }

    int userAnswer = -1;
    while (true) {
        System.out.println("Введите номер вашего ответа:");
        String input = scanner.nextLine();
        try {
            userAnswer = Integer.parseInt(input);
            if (userAnswer >= 1 && userAnswer <= options.size()) {
                break;
            } else {
                System.out.println("Пожалуйста, введите число от 1 до " + options.size());
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Введите число.");
        }
    }

    String selectedAnswer = options.get(userAnswer - 1);
    if (selectedAnswer.equals(random.getAnswer())) {
        System.out.println("Правильно!");
        ticketService.updateStatus(random, TicketStatus.LEARNED);
    } else {
        System.out.println("Неправильно.");
        System.out.println("Правильный ответ: " + random.getAnswer());
        ticketService.updateStatus(random, TicketStatus.REPEAT);
    }
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
=======
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
            scanner.nextLine(); // считывание остатка

            switch (choice) {
                case 1:
    System.out.println("=== Случайный вопрос ===");
    Ticket random = ticketService.getRandomTicket();

    if (random == null) {
        System.out.println("Билетов нет!");
        break;
    }

    System.out.println("Вопрос: " + random.getQuestion());

    List<String> options = ticketService.getAnswerOptions(random, 3);
    for (int i = 0; i < options.size(); i++) {
        System.out.println((i + 1) + ") " + options.get(i));
    }

    int userAnswer = -1;
    while (true) {
        System.out.println("Введите номер вашего ответа:");
        String input = scanner.nextLine();
        try {
            userAnswer = Integer.parseInt(input);
            if (userAnswer >= 1 && userAnswer <= options.size()) {
                break;
            } else {
                System.out.println("Пожалуйста, введите число от 1 до " + options.size());
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Введите число.");
        }
    }

    String selectedAnswer = options.get(userAnswer - 1);
    if (selectedAnswer.equals(random.getAnswer())) {
        System.out.println("Правильно!");
        ticketService.updateStatus(random, TicketStatus.LEARNED);
    } else {
        System.out.println("Неправильно.");
        System.out.println("Правильный ответ: " + random.getAnswer());
        ticketService.updateStatus(random, TicketStatus.REPEAT);
    }
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
>>>>>>> Stashed changes
