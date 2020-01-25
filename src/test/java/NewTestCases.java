import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class NewTestCases {
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

    @Test
    public void test() throws SQLException {
        ResultSet resultSet = statement.executeQuery( "select " +
                "concat(first_name, ' ', last_name), country, city, postal_code " +
                "from students" );
        while(resultSet.next()) {
            String name = resultSet.getString( 1 );
            String country = resultSet.getString( 2 );
            String city = resultSet.getString( 3 );
            String postalCode = resultSet.getString( 4 );
            System.out.println( name + ", " + country + ", " + city + ", " + postalCode );
        }
    }

    @Test
    public void testNew() throws SQLException {
        ResultSet resultSet = statement.executeQuery( "select" +
                " * " +
                "from students order by fee DESC limit 20" );
        while(resultSet.next()) {
            String email = resultSet.getString( "email" );
            Double fee = resultSet.getDouble( "fee" );
            String currency = resultSet.getString( "currency" );
            System.out.println( "email: " + email + ", fee:" + fee + " " + currency );
        }
    }

    @Test
    public void testTask4() throws SQLException {
        ResultSet rs = statement.executeQuery( "select" +
                " * " +
                "from students order by fee DESC limit 20" );
        rs.absolute( 5 );
        System.out.println( "fullname: " + rs.getString( "first_name" )
                + " " +  rs.getString( "last_name" )
                + ", fee:" + rs.getString( "fee" )
                + " " + rs.getString( "currency" ) );
        rs.relative( 3 );
        System.out.println( "fullname: " + rs.getString( "first_name" )
                + " " +  rs.getString( "last_name" )
                + ", fee:" + rs.getString( "fee" )
                + " " + rs.getString( "currency" ) );
        rs.last();
        System.out.println( "fullname: " + rs.getString( "first_name" )
                + " " +  rs.getString( "last_name" )
                + ", fee:" + rs.getString( "fee" )
                + " " + rs.getString( "currency" ) );
        rs.first();
        System.out.println( "fullname: " + rs.getString( "first_name" )
                + " " +  rs.getString( "last_name" )
                + ", fee:" + rs.getString( "fee" )
                + " " + rs.getString( "currency" ) );
    }

    @Test
    public void testTask5() throws SQLException {
        ResultSet rs = statement.executeQuery( "select " +
                "avg(fee), currency, country " +
                " from students " +
                "group by currency, country;"
        );
        while(rs.next()) {
            Double avgFee = rs.getDouble( 1 );
            String currency = rs.getString( 2 );
            String country = rs.getString( 3 );
            System.out.println("country: " + country +", currency: " + currency + " avg: " + (avgFee * 1.17) );
        }
    }

    @Test
    public void testTask6() throws SQLException {
        ResultSet rs = statement.executeQuery( "select fee from students " +
                "where gender = 'male' and country = 'United States' " +
                "limit 5" );
        while(rs.next()) {
            Double fee = rs.getDouble( 1 );
            System.out.println("Fee: " + fee);
        }

        statement.executeUpdate( "update students set fee = fee + 10 where gender = 'male' and country = 'United States'" );
        System.out.println("--------------------------------------------");

        rs = statement.executeQuery( "select fee from students " +
                "where gender = 'male' and country = 'United States' " +
                "limit 5" );
        while(rs.next()) {
            Double fee = rs.getDouble( 1 );
            System.out.println("Fee: " + fee);
        }
    }

}
