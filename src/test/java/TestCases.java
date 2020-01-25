import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class TestCases {

    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/haticetosuntr_studens_database";
        String user = "haticetosuntr";
        String password = "haticetosuntr@gmail.com";
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    @AfterClass
    public void disconnect() throws SQLException {
        connection.close();
    }
    @Test
    public void  test() throws SQLException {
        ResultSet rs= statement.executeQuery("SELECT first_name, last_name, email FROM students;");
        while (rs.next()){
            String name =rs.getString(1);
            String lastName =rs.getString(2);
            String email =rs.getString(3);
            System.out.println(name + " " + lastName + " " + email);
        }

    }
}
