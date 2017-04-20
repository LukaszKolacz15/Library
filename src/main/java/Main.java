import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */


// TODO: OBSŁUŻYĆ PARAMETR END RENT TIME


public class Main {

    private static final String DB = "jdbc:mysql://5.135.218.27:3306/lukaszKolacz?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "lukaszKolacz";
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

// TODO: PRZENIESC TA CZESC KODU DO LACZENIA DO OSOBNEJ METODY (najlepiej statycznej)

            try {
                Connection connection = (Connection) DriverManager.getConnection(DB, USER, USERPW);
                Statement statement = connection.createStatement();
//                System.out.println("Połączono z bazą danych");

                switch (numb) {
                    case 1:

                        addUser(connection);

                        break;

                    case 2:

                        addBook(connection);

                        break;

                    case 3:

                        addRent(connection);

                        break;

                    case 4:

                        showUsers(connection);

                        break;

                    case 5:

                        showBooks(connection);

                        break;

                    case 6:

                        showRents(connection);

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


    private static void addRent(Connection connection) throws SQLException {

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


    private static void addUser(Connection connection) throws SQLException {

//        addUser(connection, "Łukasz", "Kołacz", "643 132 646");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Imię: ");
        String name = scanner.nextLine();
        System.out.println("Podaj Nazwisko: ");
        String lastName = scanner.nextLine();

        String telephoneNumber = "";
        Boolean status;
        do {
            System.out.println("Podaj numer telefonu: ");
            System.out.println("Przykładowy wzór: +48 000 000 000");
            telephoneNumber = scanner.nextLine();

            Pattern tele = Pattern.compile("\\+[0-9]{2}(\\ [0-9]{3}){3}");
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


    private static void addBook(Connection connection) throws SQLException {

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

    private static void showUsers(Connection connection)throws SQLException{

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


    private static void showBooks(Connection connection)throws SQLException{

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

    private static void showRents(Connection connection)throws SQLException{

        Statement statement = connection.createStatement();
        ResultSet rentResult = statement.executeQuery("SELECT * FROM rent");
        while (rentResult.next()) {
            System.out.println("---------------------------------------");
            System.out.println("Id: " + rentResult.getString("id"));
            System.out.println("Id książki: " + rentResult.getString("book"));
            System.out.println("Id użytkownika: " + rentResult.getString("user"));
            System.out.println("Data wypożyczenia: " + rentResult.getDate("startRent"));
            System.out.println("Dokładny czas wypożyczenia: " + rentResult.getTime("startRent"));

            if (rentResult.getInt("endRent") == 0) {
                System.out.println("Książka w trakcie wypożyczenia");
            } else {
                System.out.println("Data oddania: " + rentResult.getInt("endRent"));
            }

            System.out.println("Czas wypożyczeni (w dniach): " + rentResult.getInt("rentTime"));
            System.out.println("---------------------------------------");
        }
        rentResult.close();
    }

}
