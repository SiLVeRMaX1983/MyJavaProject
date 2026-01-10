package ui;

import java.util.List;
import java.util.Scanner;

import model.Ticket;
import model.TicketStatus;
import service.DataBaseService;
import service.TicketService;

public class ConsoleUI {
    private final TicketService ticketService;
    private final DataBaseService dbService;
    private final Scanner scanner;

    public ConsoleUI(TicketService ticketService, DataBaseService dbService, Scanner scanner) {
        this.ticketService = ticketService;
        this.dbService = dbService;
        this.scanner = scanner;
    }

    public void menu() {
        int choice = 0;

        while (choice != 6) {
            System.out.println("\nВыберите действие:\n" +
                    "1. Случайный вопрос\n" +
                    "2. Все билеты\n" +
                    "3. Билеты для повторения\n" +
                    "4. Прогресс\n" +
                    "5. Выученные билеты\n" +
                    "6. Выход");

            choice = readUserChoice(1, 6, "Введите номер действия:");

            switch (choice) {
                case 1 -> handleRandomQuestion();
                case 2 -> showAllTickets();
                case 3 -> showTicketsByStatus(TicketStatus.ПОВТОРИТЬ, "Билеты для повторения");
                case 4 -> showProgress();
                case 5 -> showTicketsByStatus(TicketStatus.ВЫУЧЕНО, "Выученные билеты");
                case 6 -> System.out.println("Выход...");
                default -> System.out.println("Неверный ввод!");
            }
        }
    }

    private void handleRandomQuestion() {
        System.out.println("=== Случайный вопрос ===");
        Ticket random = ticketService.getRandomTicket();
        if (random == null) {
            System.out.println("Билетов нет!");
            return;
        }

        System.out.println("Вопрос: " + random.getQuestion());

        List<String> options = ticketService.getAnswerOptions(random);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }

        int userAnswer = readUserChoice(1, options.size(), "Введите номер вашего ответа:");

        String selectedAnswer = options.get(userAnswer - 1).trim();
        String correctAnswer = random.getAnswer().trim();

        if (selectedAnswer.equals(correctAnswer)) {
            System.out.println("Правильно!");
        } else {
            System.out.println("Неправильно. Правильный ответ: " + correctAnswer);
        }

        System.out.println("Статус билета?\n1. Выучено\n2. Повторить\n3. Пропустить");
        int stat = readUserChoice(1, 3, "Введите 1, 2 или 3");

        switch (stat) {
            case 1 -> ticketService.updateStatus(random, TicketStatus.ВЫУЧЕНО);
            case 2 -> ticketService.updateStatus(random, TicketStatus.ПОВТОРИТЬ);
            case 3 -> ticketService.updateStatus(random, TicketStatus.НЕ_ИЗУЧЕНО);
        }

        if (dbService != null) {
            try {
                dbService.updateTicketStatus(random);
            } catch (Exception e) {
                System.out.println("Не удалось сохранить статус билета: " + e.getMessage());
            }
        }
    }

    private int readUserChoice(int min, int max, String prompt) {
        int choice;
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) break;
                else System.out.println("Введите число от " + min + " до " + max);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите число.");
            }
        }
        return choice;
    }

    private void showAllTickets() {
        System.out.println("=== Все билеты ===");
        List<Ticket> all = ticketService.getAllTickets();
        if (all.isEmpty()) System.out.println("Билетов нет.");
        else all.forEach(t -> System.out.println(t.getQuestion() + " | " + t.getStatus().getLabel()));
    }

    private void showTicketsByStatus(TicketStatus status, String title) {
        System.out.println("=== " + title + " ===");
        List<Ticket> list = ticketService.getTicketsByStatus(status);
        if (list.isEmpty()) System.out.println("Билетов нет.");
        else list.forEach(t -> System.out.println(t.getQuestion()));
    }

    private void showProgress() {
        double progress = ticketService.getProgress();
        int bars = (int) (progress / 5); 
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 20; i++) sb.append(i < bars ? "█" : " ");
        sb.append("]");
        System.out.println("Ваш прогресс: " + sb + " " + String.format("%.2f", progress) + "%");
    }
}



