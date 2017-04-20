import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 20.04.2017.
 */
public class Book {

    static void addBook(Connection connection) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj tytuł: ");
        String title = scanner.nextLine();
        System.out.println("Podaj autora: ");
        String author = scanner.nextLine();
        System.out.println("Podaj ilość stron: ");
        int pages = scanner.nextInt();


        String sql = "INSERT INTO book(title, author, pages) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, author);
        statement.setInt(3, pages);
        statement.execute();
        statement.close();

        System.out.println("Dodano książkę!");
    }

    static void showBooks(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet bookResult = statement.executeQuery("SELECT * FROM book");
        while (bookResult.next()) {
            System.out.println("---------------------------------------");
            System.out.println("Id: " + bookResult.getString("id"));
            System.out.println("Tutuł: " + bookResult.getString("title"));
            System.out.println("Autor: " + bookResult.getString("author"));
            System.out.println("Ilość stron: " + bookResult.getInt("pages"));
            System.out.println("---------------------------------------");
        }
        bookResult.close();

    }

}
