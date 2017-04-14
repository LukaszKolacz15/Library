import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */


// TODO: OBSŁUŻYĆ PARAMETR END RENT TIME


public class Main {

    private static final String DB = "jdbc:mysql://5.135.218.27:3306/lukaszKolacz?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "lukaszKolacz";
    private static final String USERPW = "akademiakodu";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static String title = "0";

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

                        System.out.println("Podaj Imię: ");
                        String name1 = scanner.next();

                        System.out.println("Podaj Nazwisko: ");
                        String lastName1 = scanner.next();

// TODO: Numer telefonu nie może miec spacji! Sprawdź jak to ominąć ?! REGEX?

//        Pattern bigChars = Pattern.compile(".*[A-Z].*");
//        Pattern specjalChars = Pattern.compile(".*[\\W].*");
//        Pattern intchar = Pattern.compile(".*[\\d].*");
//
//        Matcher matcherBig = bigChars.matcher("Aasd48as%dsd");
//        Matcher matcherSpecial = specjalChars.matcher("Aasd48as%dsd");
//        Matcher matcherInt = intchar.matcher("Aasd48as%dsd");

//        if(matcherBig.matches() && matcherSpecial.matches() && matcherInt.matches()){
//            System.out.println("Hasło jest poprawne");
//        }

                        System.out.println("Podaj numer telefonu: ");
                        System.out.println("<!> NUMER TELEFONU MUSI BYĆ JEDNYM CIĄGIEM ZNAKÓW <!>");
                        String telephoneNumber1 = scanner.next();


//                        addUser(connection, "Łukasz","Kołacz", "643 132 646");
                        addUser(connection, name1, lastName1, telephoneNumber1);

                        break;

                    case 2:
// TODO: Tytuł oraz autor nie może miec spacji! Sprawdź jak to ominąć ?! REGEX?
// TODO: Rozdzielić autora na imię i nazwisko ??



                        System.out.println("Podaj tytuł: ");
//                        try{
                        String title = scanner.next();
//                        }catch (InputMismatchException e){
//                            System.out.println("<!> Tytuł musi zostać podany bez spacji <!>");
//                        }

                        System.out.println("Podaj autora: ");
                        String author = scanner.next();

                        System.out.println("Podaj ilość stron: ");
                        int pages = scanner.nextInt();


                        addBook(connection, title, author, pages);

                        break;

                    case 3:
                        System.out.println("Podaj id książki");
                        int book = scanner.nextInt();

                        System.out.println("Podaj id użytkownika");
                        int user = scanner.nextInt();

                        System.out.println("Podaj czas wypożyczenia (w dniach)");
                        int rentTime = scanner.nextInt();


                        addRent(connection, book, user, rentTime);

                        break;

                    case 4:

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

                        break;

                    case 5:

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

                        break;

                    case 6:

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

    private static void addRent(Connection connection, int book, int user, int rentTime) throws SQLException {
        String sgl = "INSERT INTO rent(book, user, rentTime) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sgl);
        statement.setInt(1, book);
        statement.setInt(2, user);
        statement.setInt(3, rentTime);


        statement.execute();

        statement.close();

        System.out.println("Dodano wypożyczenie!");
    }

    private static void addUser(Connection connection, String name, String lastName, String telephoneNumber) throws SQLException {

        String sql = "INSERT INTO user(name, lastname, number) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, lastName);
        statement.setString(3, telephoneNumber);

        statement.execute();

        statement.close();

        System.out.println("Dodano użytkownika!");
    }

    private static void addBook(Connection connection, String title, String author, int pages) throws SQLException {

        String sql = "INSERT INTO book(title, author, pages) VALUES(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, author);
        statement.setInt(3, pages);

        statement.execute();

        statement.close();

        System.out.println("Dodano książkę!");
    }

}
