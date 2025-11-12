package ui;
import java.util.Scanner;

public class ConsoleUI {
    public void menu(){
    System.out.println("Выберите действие\n 1. Случайный вопрос\n" + 
                "2. Все билеты\n" + 
                "3. Повторение\n" + 
                "4. Прогресс\n" + 
                "5. Выход");
                
        Scanner scanner = new Scanner(System.in);
        int menu = scanner.nextInt();

           switch (menu) {
                case 1:
                    System.out.println("Показываю случайный вопрос...");
                    break;
                case 2:
                    System.out.println("Вот список всех билетов...");
                    break;
                case 3:
                    System.out.println("Показываю вопросы для повторения...");
                    break;
                case 4:
                    System.out.println("Показать прогресс: 0%");
                    break;
                case 5:
                    System.out.println("Выход из программы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный ввод! Попробуйте снова.");
            }
            scanner.close();
    }

}
