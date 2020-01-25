
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestCases {
    @Test
    public void  test() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/haticetosuntr_studens_database";
        String user = "haticetosuntr";
        String password = "haticetosuntr@gmail.com";
        Connection connection = DriverManager.getConnection(url, user, password);

    }
}
