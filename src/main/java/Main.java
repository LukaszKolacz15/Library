import java.sql.SQLException;

/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */


// TODO: OBSŁUŻYĆ PARAMETR END RENT TIME
// TODO: DODAĆ ROZSZERZENIE ZAPYTANIA WYŚWIETLANIA WYPOŻYCZEŃ


public class Main {


    public static void main(String[] args) {

        do {
            switch (Menu.showMenu()) {
                case 1:
                    try {
                        User.addUser(DataBase.baseConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        Book.addBook(DataBase.baseConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        Rent.addRent(DataBase.baseConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        User.showUsers(DataBase.baseConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        Book.showBooks(DataBase.baseConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    try {
                        Rent.showRents(DataBase.baseConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    System.exit(0);
                    break;
            }

        } while (true);
    }
}
