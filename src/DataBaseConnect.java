/*
 * This sample shows how to list all entries from the table dogs in the database
 *
 * It uses the JDBC OCI8 driver.
 */
// You need to import the java.sql package to use JDBC

import java.sql.*;

public class DataBaseConnect   {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{

        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions

        //check Oracle documentation online
        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

        // Connect to the database
        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1021/qeDnwqhU@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");


        String query = "delete from users where LASTNAME = ?";

        // Create a Statement
        Statement stmt = conn.createStatement();
        // Select the all (*) from the table JAVATEST

        //ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        //System.out.println(rset);



        int val = 3;
        String sqlDelete = "DELETE FROM ADDRESSENTRYTABLE WHERE ID = " + val;
        System.out.println("The SQL statement is: " + sqlDelete + "\n");//for debugging
        int countDeleted = stmt.executeUpdate(sqlDelete);
        System.out.println(countDeleted + " records deleted.\n");


/*
        // Check ResultSet's updatability
        if (rset.getConcurrency() == ResultSet.CONCUR_READ_ONLY) {
            System.out.println("ResultSet non-updatable.");
        } else {
            System.out.println("ResultSet updatable.");
        }

        // Iterate through the result and print the employee names
        while (rset.next ()) //get next row of table returned
        {

            for(int i=1; i<=rset.getMetaData().getColumnCount(); i++) //visit each column
            {
                System.out.print(rset.getString(i) + " | ");

            }

            System.out.println(" ");
            System.out.println("==============================================================================================");
        }


        //Close access to everything...will otherwise happen when disconnect
        // from database.
        rset.close();
    */
        stmt.close();
        conn.close();

    }

}