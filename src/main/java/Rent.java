import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 20.04.2017.
 */

// TODO: DODAĆ ROZSZERZENIE ZAPYTANIA WYŚWIETLANIA WYPOŻYCZEŃ (Dane o userze oraz dane o książce)

public class Rent {

    static void addRent(Connection connection) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id książki");
        int book = scanner.nextInt();

        System.out.println("Podaj id użytkownika");
        int user = scanner.nextInt();

        System.out.println("Podaj czas wypożyczenia (w dniach)");
        int rentTime = scanner.nextInt();

        String sgl = "INSERT INTO rent(book, user, rentTime) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sgl);
        statement.setInt(1, book);
        statement.setInt(2, user);
        statement.setInt(3, rentTime);
        statement.execute();
        statement.close();

        System.out.println("Dodano wypożyczenie!");
    }

    static void showRents(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rentResult = statement.executeQuery("SELECT * FROM rent");
        while (rentResult.next()) {
            System.out.println("---------------------------------------");
            System.out.println("Id: " + rentResult.getString("id"));
            System.out.println("Id książki: " + rentResult.getString("book"));
            System.out.println("Id użytkownika: " + rentResult.getString("user"));
            System.out.println("Data wypożyczenia: " + rentResult.getDate("startRent"));
//            System.out.println("Dokładny czas wypożyczenia: " + rentResult.getTime("startRent"));

            if (rentResult.getInt("endRent") == 0) {
                System.out.println("Książka w trakcie wypożyczenia");
            } else {
                System.out.println("Data oddania: " + rentResult.getDate("endRent"));
            }

            System.out.println("Czas wypożyczeni (w dniach): " + rentResult.getInt("rentTime"));
            System.out.println("---------------------------------------");
        }
        rentResult.close();
    }

    static void addEndRent(Connection connection) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id wyporzyczenia");
        int idRent = scanner.nextInt();
//        int idRent = 2;
        String sgl = "UPDATE `rent` SET `endRent`=LOCALTIMESTAMP WHERE `id`=" + Integer.valueOf(idRent);
        PreparedStatement statement = connection.prepareStatement(sgl);
        statement.execute();
        statement.close();

        System.out.println("Oddano książkę");
    }

}
