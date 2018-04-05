package com.example.mdo3.overhaul;
import java.sql.*;
import java.sql.PreparedStatement;
import android.os.AsyncTask;

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

                String query = "SELECT id, UserName, FirstName, LastName, PhoneNumber, Picture, isActive FROM UserInfo WHERE UserName = ? AND PassWord = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() ) {
                    int UserId = rs.getInt("id");
                    String UserName = rs.getString("UserName");
                    String FirstName = rs.getString("FirstName");
                    String LastName = rs.getString("LastName");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    byte[] Picture = rs.getBytes("Picture");
                    boolean isActive = rs.getBoolean("isActive");
                    UserDetails userDetails = new UserDetails(UserId, UserName, FirstName, LastName, PhoneNumber, Picture, isActive);
                    return userDetails;
                }

                conn.close();
            } catch (Exception e) {System.out.println("Error Logging In: " + e.toString());}
            return null;
        }
    }

    public Boolean insertUser(String UserName, String PassWord, String FirstName, String LastName, String PhoneNumber)
    {
        try{
            insertUserAsync iu =  new insertUserAsync(UserName, PassWord, FirstName, LastName, PhoneNumber);
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
        String phoneNumber;

        public insertUserAsync(String UserName, String PassWord, String FirstName, String LastName, String PhoneNumber)
        {
            this.userName = UserName;
            this.passWord = PassWord;
            this.firstName = FirstName;
            this.lastName = LastName;
            this.phoneNumber = PhoneNumber;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertUser @UserName = ?, @PassWord = ?, @FirstName = ?, @LastName = ?, @PhoneNumber = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                pstmt.setString(3, this.firstName);
                pstmt.setString(4, this.lastName);
                pstmt.setString(5, this.phoneNumber);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding User: " + e.toString());}
            return false;
        }
    }

    
    public Boolean insertTransactionAndItems(ServiceRequest transaction)
    {
        try{
            insertTransactionAndItemsAsync iti =  new insertTransactionAndItemsAsync(transaction.getIdCustomer(),
                                                    transaction.getTitle(), transaction.getDescription(),
                                                    transaction.getDatePosted(), transaction.getWeight(),
                                                    transaction.getPickupLocation(), transaction.getDestination(),
                                                    transaction.needLoadHelp(), transaction.needUnloadHelp(),
                                                    transaction.getPrice());
            return iti.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
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
        private float price; private boolean loadHelp; private boolean unloadHelp;

        public insertTransactionAndItemsAsync(int idUser, String transactionTitle, String transactionDescription, Timestamp datePosted,
                                              String itemName, String itemDescription, float weight, float height, float width, float length, byte[] picture,
                                                String startAddress, String endAddress)
        {
            this.idUser = idUser; this.transactionTitle = transactionTitle; this.transactionDescription = transactionDescription;
            this.datePosted = datePosted; this.itemName = itemName; this.itemDescription = itemDescription;
            this.weight = weight; this.height = height; this.width = width; this.length = length;
            this.picture = picture; this.startAddress = startAddress; this.endAddress = endAddress;
        }

        public insertTransactionAndItemsAsync(int idUser, String transactionTitle, String transactionDescription, Timestamp datePosted,
                                              float itemWeight, String pickupLocation, String destination,
                                              boolean loadHelp, boolean unloadHelp, float price)
        {
            this.idUser = idUser; this.transactionTitle = transactionTitle; this.transactionDescription = transactionDescription;
            this.datePosted = datePosted; this.weight = itemWeight; /*this.picture = picture;*/
            this.startAddress = pickupLocation; this.endAddress = destination;
            this.loadHelp = loadHelp; this.unloadHelp = unloadHelp; this.price = price;
        }


        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertTransaction @iduser = ?, @Title = ?, @Description = ?, @DatePosted = ?," +
                                    " @TotalWeight = ?, @LoadHelp = ?, @UnloadHelp = ?, @Price = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idUser);
                pstmt.setString(2, this.transactionTitle);
                pstmt.setString(3, this.transactionDescription);
                pstmt.setTimestamp(4, this.datePosted);
                pstmt.setFloat(5, this.weight);
                pstmt.setBoolean(6, this.loadHelp);
                pstmt.setBoolean(7, this.unloadHelp);
                pstmt.setFloat(8, this.price);
                //pstmt.setBytes(8, this.picture);

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

    public Boolean insertUserPaymentInfo(int idUser, String cardNumber, String billingAddress, String expirationMonth, String expirationYear, String CVV, String billingName)
    {
        try{
            insertUserPaymentInfoAsync idr =  new insertUserPaymentInfoAsync(idUser, cardNumber, billingAddress, expirationMonth, expirationYear, CVV, billingName);
            return idr.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertUserPaymentInfoAsync extends AsyncTask<Void, Void, Boolean>
    {

        private int idUser;
        private String cardNumber;
        private String billingAddress;
        private String expirationMonth;
        private String expirationYear;
        private String CVV;
        private String billingName;

        public insertUserPaymentInfoAsync(int idUser, String cardNumber, String billingAddress, String expirationMonth, String expirationYear, String CVV, String billingName)
        {
            this.idUser = idUser;
            this.cardNumber = cardNumber;
            this.billingAddress = billingAddress;
            this.expirationMonth = expirationMonth;
            this.expirationYear = expirationYear;
            this.CVV = CVV;
            this.billingName = billingName;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAcess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertUserPaymentInfo @idUser = ?, @idUserWhoRated = ?, @idTransaction = ?, @rating = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idUser);
                pstmt.setString(2, this.cardNumber);
                pstmt.setString(3, this.billingAddress);
                pstmt.setString(4, this.expirationMonth);
                pstmt.setString(5, this.expirationYear);
                pstmt.setString(6, this.CVV);
                pstmt.setString(7, this.billingName);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Driver Rating: " + e.toString());}
            return false;
        }

    }

}
