// package service;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import model.Ticket;

// public class FileService {
// public List<Ticket> loadTickets(String path) {
//     List<Ticket> tickets = new ArrayList<>();

//     try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//         String question;
//         while ((question = reader.readLine()) != null) {
//             String correctAnswer = reader.readLine();
//             if (correctAnswer == null) break;
//             String option2 = reader.readLine();
//             String option3 = reader.readLine();

//             List<String> options = new ArrayList<>();
//             options.add(correctAnswer);
//             if (option2 != null) options.add(option2);
//             if (option3 != null) options.add(option3);

//             tickets.add(new Ticket(question, correctAnswer, options));

//             reader.readLine(); 
//         }
//     } catch (IOException e) {
//         System.out.println("Ошибка чтения файла: " + e.getMessage());
//     }
//     return tickets;
// }

//     public void saveTickets(String path, List<Ticket> tickets) {
//     try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
//         for (Ticket ticket : tickets) {
//             writer.write(ticket.getQuestion());
//             writer.newLine();

//             List<String> options = ticket.getOptions();
//             if (options != null && !options.isEmpty()) {
//                 for (String option : options) {
//                     writer.write(option);
//                     writer.newLine();
//                 }
//             } else {
//                 writer.write(ticket.getAnswer());
//                 writer.newLine();
//                 writer.newLine();
//                 writer.newLine();
//             }
//             writer.newLine(); 
//         }
//     } catch (IOException e) {
//         System.out.println("Ошибка записи файла: " + e.getMessage());
//     }
// }
// }