import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Lukasz Kolacz on 20.04.2017.
 */

public class DataBase {

    private static final String DB = "jdbc:mysql://5.135.218.27:3306/lukaszKolacz?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "lukaszKolacz";
    private static final String USERPW = "akademiakodu";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    static Connection baseConnection() {

        Connection connection = null;

        try {
            Class.forName(DRIVER).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = (Connection) DriverManager.getConnection(DB, USER, USERPW);
//            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Połączono z bazą danych");
        return connection;
    }

}
