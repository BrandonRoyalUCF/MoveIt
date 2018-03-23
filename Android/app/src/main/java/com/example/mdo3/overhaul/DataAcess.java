package com.example.mdo3.overhaul;
import java.sql.*;
import java.sql.PreparedStatement;
import android.os.AsyncTask;
import android.os.StrictMode;

/**
 * Created by Royal on 3/23/2018.
 */

public class DataAcess {

    final private String dbHost = "overhauldb.cweh7b1mnc5s.us-east-1.rds.amazonaws.com";
    final private String dbUserName = "OverHaul";
    final private String dbPassWord = "overhaul123";
    final private String dbName = "OverHaul_Main";
    final private String dbClass = "net.sourceforge.jtds.jdbc.Driver";

    public DataAcess()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private Connection ConnectToDB()
    {
        Connection connection = null;
        String ConnURL = null;
        try {
            Class.forName(this.dbClass);
            ConnURL = "jdbc:jtds:sqlserver://" + this.dbHost + ";"
                    + "databaseName=" + this.dbName + ";user=" + this.dbUserName + ";password="
                    + this.dbPassWord + ";";
            connection = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return connection;
    }

    //DataAccess for when a user is logging in to validate that the credentials are correct
    public boolean checkUserLogin(String UserName, String Password)
    {
        try {
            Connection conn = this.ConnectToDB();

            String query = "SELECT 1 FROM UserInfo WHERE UserName = ? AND PassWord = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, UserName);
            pstmt.setString(2, Password);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
                return true;

            conn.close();
        } catch (Exception e) {System.out.println("Error Logging In: " + e.toString());}


        return false;
    }
}
