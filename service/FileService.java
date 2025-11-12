package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Ticket;

public class FileService {
    public List<Ticket> loadTickets(String path) {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String question;
            while ((question = reader.readLine()) != null) {
                String answer = reader.readLine();
                if (answer == null) break;
                tickets.add(new Ticket(question, answer));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return tickets;
    }

    public void saveTickets(String path, List<Ticket> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Ticket ticket : tickets) {
                writer.write(ticket.getQuestion());
                writer.newLine();
                writer.write(ticket.getAnswer());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}

