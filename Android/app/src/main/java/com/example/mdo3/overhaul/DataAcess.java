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

    public UserDetails checkUserLogin(String UserName, String PassWord)
    {
        try{
            checkUserLoginAsync ul =  new checkUserLoginAsync(UserName, PassWord);
            return ul.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    ///check if a login attempt is valid, if so return the user object, else return null
    private class checkUserLoginAsync extends AsyncTask<Void, Void, UserDetails>
    {
        String userName;
        String passWord;
        public checkUserLoginAsync(String UserName, String PassWord)
        {
            this.userName = UserName;
            this.passWord = PassWord;
        }

        @Override
        protected UserDetails doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "SELECT id, UserName, FirstName, LastName, PassWord, isActive FROM UserInfo WHERE UserName = ? AND PassWord = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() ) {
                    int UserId = rs.getInt("id");
                    String UserName = rs.getString("UserName");
                    String FirstName = rs.getString("FirstName");
                    String LastName = rs.getString("LastName");
                    boolean isActive = rs.getBoolean("isActive");
                    UserDetails userDetails = new UserDetails(UserId, UserName, FirstName, LastName, isActive);
                    return userDetails;
                }

                conn.close();
            } catch (Exception e) {System.out.println("Error Logging In: " + e.toString());}
            return null;
        }
    }

    public Boolean insertUserAsync(String UserName, String PassWord, String FirstName, String LastName)
    {
        try{
            insertUserAsync iu =  new insertUserAsync(UserName, PassWord, FirstName, LastName);
            return iu.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    //Insert a new user into the database
    private class insertUserAsync extends AsyncTask<Void, Void, Boolean>
    {
        String userName;
        String passWord;
        String firstName;
        String lastName;
        boolean isActive;

        public insertUserAsync(String UserName, String PassWord, String FirstName, String LastName)
        {
            this.userName = UserName;
            this.passWord = PassWord;
            this.firstName = FirstName;
            this.lastName = LastName;
            this.isActive = true;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertUser @UserName = ?, @PassWord = ?, @FirstName = ?, @LastName = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                pstmt.setString(3, this.firstName);
                pstmt.setString(4, this.lastName);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding User: " + e.toString());}
            return false;
        }
    }

    public Boolean insertTransactionAndItemsAsync()
    {
        try{
            insertTransactionandItems iti =  new insertTransactionandItems();
            return iti.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertTransactionandItems extends AsyncTask<Void, Void, Boolean>
    {
        public insertTransactionandItems()
        {

        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            return false;
        }
    }

    public Boolean insertDriverProfile()
    {
        try{
            insertDriverProfileAsync idp =  new insertDriverProfileAsync();
            return idp.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertDriverProfileAsync extends AsyncTask<Void, Void, Boolean>
    {
        public  insertDriverProfileAsync()
        {

        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            return false;
        }
    }
}
