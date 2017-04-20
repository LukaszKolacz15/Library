import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lukasz Kolacz on 20.04.2017.
 */

public class User {

    static void addUser(Connection connection) throws SQLException {

//        addUser(connection, "Łukasz", "Kołacz", "643 132 646");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Imię: ");
        String name = scanner.nextLine();
        System.out.println("Podaj Nazwisko: ");
        String lastName = scanner.nextLine();

        String telephoneNumber = "0";
        Boolean status;
        do {
            System.out.println("Podaj numer telefonu: ");
            System.out.println("Przykładowy wzór: +48 000 000 000");
            telephoneNumber = scanner.nextLine();

            Pattern tele = Pattern.compile("\\+[0-9]{2}( [0-9]{3}){3}");
            Matcher telephone = tele.matcher(telephoneNumber);
            if (telephone.matches()) {
                status = true;
            } else {
                System.out.println("<!> NUMER TELEFONU MUSI ZOSTAĆ WPROWADZONY ZGODNIE ZE WZOREM <!>");
                status = false;
            }
        } while (status == false);

        String sql = "INSERT INTO user(name, lastname, number) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, lastName);
        statement.setString(3, telephoneNumber);
        statement.execute();
        statement.close();

        System.out.println("Dodano użytkownika!");
    }

    static void showUsers(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

        while (resultSet.next()) {
            System.out.println("---------------------------------------");
            System.out.println("Id: " + resultSet.getString("id"));
            System.out.println("Imię: " + resultSet.getString("name"));
            System.out.println("Nazwisko: " + resultSet.getString("lastname"));
            System.out.println("Numer telefonu: " + resultSet.getString("number"));
            System.out.println("---------------------------------------");
        }
        resultSet.close();

    }

}
