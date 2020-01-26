import javafx.beans.binding.ObjectExpression;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class RepresentativeTest {
    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/daulet2030_studens_database";
        String user = "daulet2030";
        String password = "daulet2030@gmail.com";
        connection = DriverManager.getConnection( url, user, password );
        statement = connection.createStatement();
    }

    @AfterClass
    public void disconnect() throws SQLException {
        connection.close();
    }

    @DataProvider(name = "representative")
    public Object[][] data() throws SQLException {
        ResultSet resultSet = statement.executeQuery( "select * from representative" );
        resultSet.last();
        int rowNumbers = resultSet.getRow();
        resultSet.beforeFirst();
        int i = 0;
        Object[][] result = new Object[rowNumbers][4];
        while(resultSet.next()){
            String name = resultSet.getString( "firstname" );
            String lastname = resultSet.getString( "lastname" );
            String country = resultSet.getString( "country" );
            String phone = resultSet.getString( "phone" );
            result[i][0] = name;
            result[i][1] = lastname;
            result[i][2] = country;
            result[i][3] = phone;
            i++;
        }
        return result;
    }

    @Test(dataProvider = "representative")
    public void test(String c1, String c2, String c3, String c4){
        System.out.print(c1 + c2 + c3 + c4);
    }
}
