import java.io.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class PhoneBookDir {

public class PhoneDirectoryFileDemo {
   
   private static String DATA_FILE_NAME = ".phone_book_demo";
   
   public static void main(String[] args) {
      
      String name, number;  
      TreeMap<String,String>  phoneBook;   
                                           
      phoneBook = new TreeMap<String,String>();
      
      File userHomeDirectory = new File( System.getProperty("user.home") );
      File dataFile = new File( userHomeDirectory, DATA_FILE_NAME );
      
      if ( ! dataFile.exists() ) {
        System.out.println("Файл данных телефонной книги не найден."); 
        System.out.println("Будет создан новый."); 
        System.out.println("Имя файла: " + dataFile.getAbsolutePath()); 
      }
      else {
        System.out.println("Чтение данных телефонной книги..."); 
         try {
            try (Scanner scanner = new Scanner( dataFile )) {
                while (scanner.hasNextLine()) {
                   String phoneEntry = scanner.nextLine();
                   int separatorPosition = phoneEntry.indexOf('%');
                   if (separatorPosition == -1)
                      throw new IOException("Файл не является файлом данных телефонной книги.");
                   name = phoneEntry.substring(0, separatorPosition);
                   number = phoneEntry.substring(separatorPosition+1);
                   phoneBook.put(name,number);
                }
            }
         }
         catch (IOException e) {
            System.out.println("Ошибка в файле данных телефонной книги."); 
            System.out.println("Имя файла: " + dataFile.getAbsolutePath()); 
            System.out.println("Продолжение этой программы невозможно."); 
            System.exit(1);
         }
      }
      
      try (Scanner in = new Scanner( System.in )) {
        boolean changed = false;  
          
          mainLoop: while (true) {
            System.out.println("\nВыберите действие, которое вы хотите выполнить:"); 
            System.out.println(" 1. Найдите номер телефона."); 
            System.out.println(" 2. Добавьте или измените номер телефона."); 
            System.out.println(" 3. Удалить запись из телефонного справочника."); 
            System.out.println(" 4. Список всего телефонного справочника.");
            System.out.println(" 5. Выйти из программы."); 
            System.out.println("Введите номер действия (1-5): "); 
             int command;
             if ( in.hasNextInt() ) {
                command = in.nextInt();
                in.nextLine();
             }
             else {
                System.out.println("\nНЕВЕРНЫЙ ОТВЕТ. НЕОБХОДИМО ВВЕСТИ НОМЕР.");
                in.nextLine();
                continue;
             }
             switch(command) {
             case 1:
                System.out.print("\nВведите имя, номер которого вы хотите найти: ");
                name = in.nextLine().trim().toLowerCase();
                number = phoneBook.get(name);
                if (number == null)
                   System.out.println("\nИЗВИНИТЕ, НЕ НАЙДЕНО НОМЕРА ДЛЯ " + name);
                else
                   System.out.println("\nНОМЕР ДЛЯ " + name + ":  " + number);
                break;
             case 2:
                System.out.print("\nВведите имя: ");
                name = in.nextLine().trim().toLowerCase();
                if (name.length() == 0)
                   System.out.println("\nИМЯ НЕ МОЖЕТ БЫТЬ ПУСТЫМ.");
                else if (name.indexOf('%') >= 0)
                   System.out.println("\nИМЯ НЕ МОЖЕТ СОДЕРЖАТЬ СИМВОЛОВ \"%\".");
                else { 
                   System.out.print("Введите номер телефона: ");
                   number = in.nextLine().trim();
                   if (number.length() == 0)
                      System.out.println("\nНОМЕР ТЕЛЕФОНА НЕ МОЖЕТ БЫТЬ ПУСТОЙ.");
                   else {
                      phoneBook.put(name,number);
                      changed = true;
                   }
                }
                break;
             case 3:
                System.out.print("\nВведите имя, запись которого вы хотите удалить: ");
                name = in.nextLine().trim().toLowerCase();
                number = phoneBook.get(name);
                if (number == null)
                   System.out.println("\nИЗВИНИТЕ, НЕТ ЗАПИСИ ДЛЯ " + name);
                else {
                   phoneBook.remove(name);
                   changed = true;
                   System.out.println("\nЗАПИСЬ КАТАЛОГА УДАЛЕН ДЛЯ " + name);
                }
                break;
             case 4:
                System.out.println("\nСПИСОК ЗАПИСЕЙ В ВАШЕЙ ТЕЛЕФОННОЙ КНИГЕ:\n");
                for ( Map.Entry<String,String> entry : phoneBook.entrySet() )
                   System.out.println("   " + entry.getKey() + ": " + entry.getValue() );
                break;
             case 5:
                System.out.println("\nВыход из программы.");
                break mainLoop;
             default:
                System.out.println("\nНЕПРАВИЛЬНЫЙ НОМЕР ДЕЙСТВИЯ.");
             }
          }
          
          if (changed) {
             System.out.println("Сохранение изменений телефонного справочника в файл " + 
                   dataFile.getAbsolutePath() + " ...");
             PrintWriter out;
             try {
                out = new PrintWriter( new FileWriter(dataFile) );
             }
             catch (IOException e) {
                System.out.println("ОШИБКА: Не удается открыть файл данных для вывода.");
                return;
             }
             for ( Map.Entry<String,String> entry : phoneBook.entrySet() )
                out.println(entry.getKey() + "%" + entry.getValue() );
             out.close();
             if (out.checkError())
                System.out.println("ОШИБКА: Произошла ошибка при записи файла данных.");
             else
                System.out.println("Готово.");
          }
    }
   }
}
}
