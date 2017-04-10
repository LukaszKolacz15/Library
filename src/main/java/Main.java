import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */
public class Main {

    private static final String DB = "jdbc:mysql://5.135.218.27:3306/lukaszKolacz?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "oskar";
    private static final String USERPW = "akademiakodu";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {


        try {
            Class.forName(DRIVER).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        do {
            menu();
            int numb = scanner.nextInt();

            try {
                Connection connection = (Connection) DriverManager.getConnection(DB, USER, USERPW);
                Statement statement = connection.createStatement();
//                System.out.println("Połączono z bazą danych");

                switch (numb) {
                    case 1:

                        System.out.println("Podaj Imię: ");
                        String name1 = scanner.next();

                        System.out.println("Podaj Nazwisko: ");
                        String lastName1 = scanner.next();
//                    Numer telefonu nie może miec spacji! Sprawdź jak to ominąć ?! REGEX?
                        System.out.println("Podaj numer telefonu: ");
                        String telephoneNumber1 = scanner.next();


//                        addUser(connection, "Łukasz","Kołacz", "643 132 646");
                        addUser(connection, name1, lastName1, telephoneNumber1);

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
                        while (resultSet.next()) {
                            System.out.println("------------------");
                            System.out.println("Id: " + resultSet.getString("id"));
                            System.out.println("Imię: " + resultSet.getString("name"));
                            System.out.println("Nazwisko: " + resultSet.getString("lastname"));
                            System.out.println("Numer telefonu: " + resultSet.getString("number"));
                            System.out.println("------------------");
                        }
                        resultSet.close();

                        break;

                    case 5:

                        break;

                    case 6:

                        break;

                    case 7:
                        System.exit(0);
                        break;

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while (true);

    }

    private static void menu() {

        System.out.println(" ");
        System.out.println("--------- WITAMY W BIBLIOTECE ---------");
        System.out.println("WYBIERZ DZIAŁANIE");
        System.out.println("1. Dodaj użytkownika");
        System.out.println("2. Dodaj książkę");
        System.out.println("3. Dodaj wypożyczenie");
        System.out.println("4. Wyświetl wszystkich użytkowników");
        System.out.println("5. Wyświetl wszystkie książki");
        System.out.println("6. Wyświetl wszystkie wypożyczenia");
        System.out.println("7. Exit");
        System.out.println("---------------------------------------");
        System.out.println(" ");

    }

    public static void addUser(Connection connection, String name, String lastName, String telephoneNumber) throws SQLException {

        String sql = "INSERT INTO user(name, lastname, number) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, lastName);
        statement.setString(3, telephoneNumber);

        statement.execute();

        statement.close();

        System.out.println("Dodałem użytkownika!");
    }

}
