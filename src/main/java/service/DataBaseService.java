package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ticket;
import model.TicketStatus;

public class DataBaseService {

    private final String url =
        "jdbc:mysql://localhost:3306/examinator?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Nikita208";

    // Загрузка билетов из базы
    public List<Ticket> loadTickets() {
    List<Ticket> tickets = new ArrayList<>();

    String sql = """
        SELECT question_text,
               option_1, option_2, option_3, option_4,
               correct_option,
               status
        FROM questions
    """;

    try (Connection conn = DriverManager.getConnection(url, user, password);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String question = rs.getString("question_text");

            List<String> options = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                String opt = rs.getString("option_" + i);
                if (opt != null && !opt.isBlank()) {
                    options.add(opt);
                }
            }

            int correctIndex = rs.getInt("correct_option") - 1;
            String correctAnswer = options.get(correctIndex);

            // Получаем статус из базы с безопасным преобразованием
            String statusStr = rs.getString("status");
            TicketStatus status;
            try {
                status = TicketStatus.valueOf(statusStr);
            } catch (IllegalArgumentException | NullPointerException e) {
                status = TicketStatus.ПОВТОРИТЬ; // дефолтный статус
            }

            tickets.add(new Ticket(question, correctAnswer, options, status));
        }

        System.out.println("Загружено билетов: " + tickets.size());

    } catch (SQLException e) {
        System.out.println("Ошибка работы с базой: " + e.getMessage());
    }

    return tickets;
}


    // Обновление статуса билета в базе
    public void updateTicketStatus(Ticket ticket) {
        String sql = "UPDATE questions SET status = ? WHERE question_text = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ticket.getStatus().name());
            ps.setString(2, ticket.getQuestion());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка обновления статуса: " + e.getMessage());
        }
    }
}


