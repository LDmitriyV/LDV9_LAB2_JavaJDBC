    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ldv9_lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class LDV9_LAB2 {

    public static void main(String[] args) {
        try {
            // Адрес нашей базы данных "tsn_demo" на локальном компьютере (localhost)
            String url = "jdbc:mysql://localhost:3306/ldv9_lab2?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            // Создание свойств соединения с базой данных
            Properties authorization = new Properties();
            authorization.put("user", "root"); // Зададим имя пользователя БД
            authorization.put("password", "root"); // Зададим пароль доступа в БД

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, authorization);

            // Создание оператора доступа к базе данных
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Выполнение запроса к базе данных, получение набора данных
            ResultSet table = statement.executeQuery("SELECT * FROM phones ");

            System.out.println("Начальная БД:");
            table.first(); // Выведем имена полей
            for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                System.out.print(table.getMetaData().getColumnName(j) + "\t\t");
            }
            System.out.println();

            table.beforeFirst(); // Выведем записи таблицы
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Введите параметры нового поля таблицы:");
            System.out.print("brand - ");
            String scannedBrand = sc.nextLine();
            System.out.print("model - ");
            String scannedModel = sc.nextLine();
            System.out.print("storage - ");
            int scannedStorage = sc.nextInt();
            System.out.print("price - ");
            int scannedPrice = sc.nextInt();
            
            System.out.println("После добавления:");
            statement.execute("INSERT phones(brand, model, storage, price) VALUES ('" + scannedBrand + "', '" + scannedModel + "', '" + scannedStorage + "', '" + scannedPrice + "')");
            table = statement.executeQuery("SELECT * FROM phones");

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            System.out.println("Строку с каким id хотите удалить?");
            System.out.print("id - ");
            int scannedId = sc.nextInt();
            statement.execute("DELETE FROM phones WHERE Id = " + scannedId);
            
            System.out.println("После удаления:");
            table = statement.executeQuery("SELECT * FROM phones");
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            
            System.out.println("На что изменить в первую строку?");
            System.out.print("brand - ");
            String scannedBrandUp = sc.nextLine();
            sc.nextLine();
            System.out.print("model - ");
            String scannedModelUp = sc.nextLine();
            System.out.print("storage - ");
            String scannedStorageUp = sc.nextLine();
            System.out.print("price - ");
            String scannedPriceUp = sc.nextLine();
            statement.executeUpdate("UPDATE phones SET Brand = '" + scannedBrandUp + "' WHERE Id = 1");
            statement.executeUpdate("UPDATE phones SET model = '" + scannedModelUp + "' WHERE id = 1");
            statement.executeUpdate("UPDATE phones SET storage = '" + scannedStorageUp + "' WHERE id = 1");
            statement.executeUpdate("UPDATE phones SET price = '" + scannedPriceUp + "' WHERE id = 1");
            System.out.println("После изменения:");
            table = statement.executeQuery("SELECT * FROM phones");

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            System.out.println("Введите максимальную цену для фильтрации:");
            int scannedPriceMax = sc.nextInt();
            table = statement.executeQuery("SELECT * FROM phones WHERE Price < " + scannedPriceMax);

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

             while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
             
            if (table != null) {
                table.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            } // Отключение от базы данных

        } catch (Exception e) {
            System.err.println("Error accessing database!");
            e.printStackTrace();
        }
    }

}
