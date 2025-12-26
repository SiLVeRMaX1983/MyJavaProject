package service; 
import model.Ticket; 
import service.FileService;
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.io.TempDir; 
import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; 
import java.util.List; 
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*; 

class FileServiceTest { 

    @Test 
    void zagruzka_odbogo_bileta_i_ego_proverka(@TempDir Path tempDir) throws IOException {

        Path testFile = tempDir.resolve("tickets.txt"); 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile.toFile()))) {
           
            writer.write("What is 2+2?"); 
            writer.newLine();             

            writer.write("4");           
            writer.newLine();

            writer.write("5");            
            writer.newLine();

            writer.write("3");            
            writer.newLine();

            writer.newLine();            
        } 

        FileService fileService = new FileService();

        List<Ticket> tickets = fileService.loadTickets(testFile.toString()); 

        assertEquals(1, tickets.size());
        Ticket ticket = tickets.get(0);  

        assertEquals("What is 2+2?", ticket.getQuestion()); 
        assertEquals("4", ticket.getAnswer());              // Проверяем, что правильный ответ прочитан верно

        List<String> options = ticket.getOptions(); 
        assertNotNull(options);                    
        assertEquals(3, options.size());         

        assertEquals("4", options.get(0)); 
        assertEquals("5", options.get(1)); 
        assertEquals("3", options.get(2)); 
    }

    @Test
    void proverka_pystogo_file_i_vernut_pustoi_spisok() {
        FileService fileService = new FileService();//создали экземпляр
        List<Ticket> tickets = fileService.loadTickets("pystoi_file.txt");//вызвали метод к несуществующему файлу
        assertNotNull(tickets);//проверка, вернул объект, а не null 
        assertTrue(tickets.isEmpty());//проверяем на пустоту
}

@Test
void sohranenieIZagruzkaBiletov_dolzhnyBytSovmestimy(@TempDir Path tempDir) throws IOException {
    //создаём список билетов в памяти
    List<String> options1 = List.of("Париж", "Лондон", "Берлин");
    Ticket ticket1 = new Ticket("Столица Франции?", "Париж", options1);

    List<String> options2 = List.of("4", "5", "6");
    Ticket ticket2 = new Ticket("Сколько будет 2+2?", "4", options2);

    List<Ticket> originalTickets = List.of(ticket1, ticket2);

    //cохраняем билеты в файл
    Path testFile = tempDir.resolve("roundtrip_tickets.txt");
    FileService fileService = new FileService();
    fileService.saveTickets(testFile.toString(), originalTickets);

    //pагружаем билеты из того же файла
    List<Ticket> loadedTickets = fileService.loadTickets(testFile.toString());

    //количество билетов совпадает
    assertEquals(2, loadedTickets.size());

    //проверяем первый билет
    Ticket loaded1 = loadedTickets.get(0);
    assertEquals("Столица Франции?", loaded1.getQuestion());
    assertEquals("Париж", loaded1.getAnswer());
    assertEquals(List.of("Париж", "Лондон", "Берлин"), loaded1.getOptions());

    //проверяем второй билет
    Ticket loaded2 = loadedTickets.get(1);
    assertEquals("Сколько будет 2+2?", loaded2.getQuestion());
    assertEquals("4", loaded2.getAnswer());
    assertEquals(List.of("4", "5", "6"), loaded2.getOptions()); 
}

@Test
void loadTicketsFromMalformedFile_returnsEmptyList(@TempDir Path tempDir) throws IOException {
    Path testFile = tempDir.resolve("malformed.txt");
    
    // Создаем битый файл (только вопрос без ответа)
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile.toFile()))) {
        writer.write("Вопрос без ответа");
        // Нет ответа и вариантов
    }
    
    FileService fileService = new FileService();
    List<Ticket> tickets = fileService.loadTickets(testFile.toString());
    
    assertNotNull(tickets);
    assertTrue(tickets.isEmpty()); // Игнорирует битые данные
}

@Test
void saveTickets_createsCorrectFileFormat(@TempDir Path tempDir) throws IOException {
    List<String> options = List.of("A", "B", "C");
    Ticket ticket = new Ticket("Тест?", "A", options);
    List<Ticket> tickets = List.of(ticket);
    
    Path testFile = tempDir.resolve("saved.txt");
    FileService fileService = new FileService();
    fileService.saveTickets(testFile.toString(), tickets);
    
    // Проверяем, что файл создался и содержит данные
    assertTrue(Files.exists(testFile));
    List<String> lines = Files.readAllLines(testFile);
    assertTrue(lines.size() >= 5); // вопрос + ответ + 3 варианта + пустая строка
    assertEquals("Тест?", lines.get(0).trim());
}

@Test
void loadTickets_handlesEmptyFile_returnsEmptyList() {
    FileService fileService = new FileService();
    List<Ticket> tickets = fileService.loadTickets("nonexistent.txt");
    
    assertNotNull(tickets);
    assertTrue(tickets.isEmpty());
}
}