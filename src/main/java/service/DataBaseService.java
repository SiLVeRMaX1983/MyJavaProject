package service;

import model.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {

    private final String url =
        "jdbc:mysql://localhost:3306/examinator?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Nikita208";

    public List<Ticket> loadTickets() {
        List<Ticket> tickets = new ArrayList<>();

        String sql = """
            SELECT question_text,
                   option_1, option_2, option_3, option_4,
                   correct_option
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

                tickets.add(new Ticket(question, correctAnswer, options));
            }

            System.out.println("Загружено билетов: " + tickets.size());

        } catch (SQLException e) {
            System.out.println("Ошибка работы с базой: " + e.getMessage());
        }

        return tickets;
    }
}

