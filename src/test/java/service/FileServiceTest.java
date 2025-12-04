package service; 
import model.Ticket; 
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.io.TempDir; 

import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException; 
import java.nio.file.Path; 
import java.util.List; 

import static org.junit.jupiter.api.Assertions.*; 

class FileServiceTest { 

    @Test 
    void loadTickets_ShouldParseSingleTicketCorrectly(@TempDir Path tempDir) throws IOException {
        // разобрать один билет

        Path testFile = tempDir.resolve("tickets.txt"); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile.toFile()))) {
           
            writer.write("What is 2+2?"); //вопрос и 3 варианта ответа
            writer.newLine();             

            writer.write("4");           
            writer.newLine();

            writer.write("5");            
            writer.newLine();

            writer.write("3");            
            writer.newLine();

            writer.newLine();            
        } 

        FileService fileService = new FileService(); // Создаём экземпляр тестируемого класса

        List<Ticket> tickets = fileService.loadTickets(testFile.toString()); // Вызываем тестируемый метод

        assertEquals(1, tickets.size()); // Проверяем: прочитан ровно 1 билет
        Ticket ticket = tickets.get(0);  // Берём первый (и единственный) билет

        assertEquals("What is 2+2?", ticket.getQuestion()); // Проверяем, что вопрос прочитан верно
        assertEquals("4", ticket.getAnswer());              // Проверяем, что правильный ответ прочитан верно

        List<String> options = ticket.getOptions(); // Получаем список вариантов ответа
        assertNotNull(options);                     // Убеждаемся, что список не null
        assertEquals(3, options.size());            // Проверяем, что вариантов ровно 3 

        assertEquals("4", options.get(0)); 
        assertEquals("5", options.get(1)); 
        assertEquals("3", options.get(2)); 
    }
}