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
        //No params needed currently
    }

    //General connect to database method to be used in all data calls
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

    public Boolean insertTransactionAndItems(int idUser, String transactionTitle, String transactionDescription, Timestamp datePosted, String itemName,
                                             String itemDescription, float weight, float height, float width, float length, byte[] picture,
                                                String startAddress, String endAddress)
    {
        try{
            insertTransactionAndItemsAsync iti =  new insertTransactionAndItemsAsync(idUser, transactionTitle, transactionDescription, datePosted,
                                                                            itemName, itemDescription, weight, height, width, length, picture, startAddress, endAddress);
            return iti.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertTransactionAndItemsAsync extends AsyncTask<Void, Void, Boolean>
    {

        private int idUser; private String transactionTitle; private String transactionDescription;
        private Timestamp datePosted; private String itemName; private String itemDescription; private float weight; private float height;
        private float width; private float length; private byte[] picture; private String startAddress; private String endAddress;

        public insertTransactionAndItemsAsync(int idUser, String transactionTitle, String transactionDescription, Timestamp datePosted,
                                              String itemName, String itemDescription, float weight, float height, float width, float length, byte[] picture,
                                                String startAddress, String endAddress)
        {
            this.idUser = idUser; this.transactionTitle = transactionTitle; this.transactionDescription = transactionDescription;
            this.datePosted = datePosted; this.itemName = itemName; this.itemDescription = itemDescription;
            this.weight = weight; this.height = height; this.width = width; this.length = length;
            this.picture = picture; this.startAddress = startAddress; this.endAddress = endAddress;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertTransaction @iduser = ?, @transactionTitle = ?, @transactionDescription = ?, @datePosted = ?," +
                                    " @startLocation = ?, @endLocation = ?, @itemName = ?, @itemDescription = ?, @weight = ?, @height = ?, " +
                                    " @width = ?, @length = ?, @picture = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idUser);
                pstmt.setString(2, this.transactionTitle);
                pstmt.setString(3, this.transactionDescription);
                pstmt.setTimestamp(4, this.datePosted);
                pstmt.setString(5, this.startAddress);
                pstmt.setString(6, this.endAddress);
                pstmt.setString(7, this.itemName);
                pstmt.setString(8, this.itemDescription);
                pstmt.setFloat(9, this.weight);
                pstmt.setFloat(10, this.height);
                pstmt.setFloat(11, this.width);
                pstmt.setFloat(12, this.length);
                pstmt.setBytes(13, this.picture);

                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Transaction: " + e.toString());}
            return false;
        }
    }

    public Boolean insertDriverProfile(int idUser, String driverLicenseNumber, String carMake, String carModel, String licensePlateNumber)
    {
        try{
            insertDriverProfileAsync idp =  new insertDriverProfileAsync(idUser, driverLicenseNumber, carMake, carModel, licensePlateNumber);
            return idp.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertDriverProfileAsync extends AsyncTask<Void, Void, Boolean>
    {

        private int idUser;
        private String driverLicenseNumber;
        private String carMake;
        private String carModel;
        private String licensePlateNumber;

        public  insertDriverProfileAsync(int idUser, String driverLicenseNumber, String carMake, String carModel, String licensePlateNumber)
        {
            this.idUser = idUser;
            this.driverLicenseNumber = driverLicenseNumber;
            this.carMake = carMake;
            this.carModel = carModel;
            this.licensePlateNumber = licensePlateNumber;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertDriver @idUser = ?, @DriverLiscenseNumber = ?, @CarMake = ?, @CarModel = ?, @LiscensePlateNumber = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idUser);
                pstmt.setString(2, this.driverLicenseNumber);
                pstmt.setString(3, this.carMake);
                pstmt.setString(4, this.carModel);
                pstmt.setString(5, this.licensePlateNumber);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Driver: " + e.toString());}
            return false;
        }
    }

    public Boolean insertDriverRating(int idDriver, int idUserWhoRated, int idTransaction, int rating)
    {
        try{
            insertDriverRatingAsync idr =  new insertDriverRatingAsync(idDriver, idUserWhoRated, idTransaction, rating);
            return idr.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertDriverRatingAsync extends AsyncTask<Void, Void, Boolean>
    {
        private int idDriver;
        private int idUserWhoRated;
        private int idTransaction;
        private int rating;

        public insertDriverRatingAsync(int idDriver, int idUserWhoRated, int idTransaction, int rating)
        {
            this.idDriver = idDriver;
            this.idUserWhoRated = idUserWhoRated;
            this.idTransaction = idTransaction;
            this.rating = rating;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertDriverRating @idDriver = ?, @idUserWhoRated = ?, @idTransaction = ?, @rating = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idDriver);
                pstmt.setInt(2, this.idUserWhoRated);
                pstmt.setInt(3, this.idTransaction);
                pstmt.setInt(4, this.rating);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Driver Rating: " + e.toString());}
            return false;
        }

    }


}
