package com.example.mdo3.overhaul;
import java.sql.*;
import java.sql.PreparedStatement;
import android.os.AsyncTask;
import java.util.*;

/**
 * Created by Royal on 3/23/2018.
 */

public class DataAccess {

    final private String dbHost = "overhauldb.cweh7b1mnc5s.us-east-1.rds.amazonaws.com";
    final private String dbUserName = "OverHaul";
    final private String dbPassWord = "overhaul123";
    final private String dbName = "OverHaul_Main";
    final private String dbClass = "net.sourceforge.jtds.jdbc.Driver";

    public DataAccess()
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

    public Customer checkCustomerLogin(String UserName, String PassWord)
    {
        try{
            checkCustomerLoginAsync cl =  new checkCustomerLoginAsync(UserName, PassWord);
            return cl.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class checkCustomerLoginAsync extends AsyncTask<Void, Void, Customer>
    {
        String userName;
        String passWord;
        public checkCustomerLoginAsync(String UserName, String PassWord)
        {
            this.userName = UserName;
            this.passWord = PassWord;
        }

        @Override
        protected Customer doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query = "SELECT id, UserName, Name, PhoneNumber, DateRegistered, isActive FROM CustomerInfo WHERE UserName = ? AND PassWord = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() ) {
                    int UserId = rs.getInt("id");
                    String UserName = rs.getString("UserName");
                    String Name = rs.getString("Name");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    Timestamp DateRegistered = rs.getTimestamp("DateRegistered");
                    boolean isActive = rs.getBoolean("isActive");
                    Customer customer = new Customer(UserId, UserName, Name, PhoneNumber, DateRegistered, isActive);
                    return customer;
                }

                conn.close();
            } catch (Exception e) {System.out.println("Error Logging In: " + e.toString());}
            return null;
        }
    }

    public Driver checkDriverLogin(String UserName, String PassWord)
    {
        try{
            checkDriverLoginAsync dl =  new checkDriverLoginAsync(UserName, PassWord);
            return dl.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class checkDriverLoginAsync extends AsyncTask<Void, Void, Driver>
    {
        String userName;
        String passWord;
        public checkDriverLoginAsync(String UserName, String PassWord)
        {
            this.userName = UserName;
            this.passWord = PassWord;
        }

        @Override
        protected Driver doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query = "SELECT id, UserName, Name, PhoneNumber, DriverLicenseNumber, Picture, DateRegistered, isActive, AverageRating, NumberRatings FROM DriverInfo WHERE UserName = ? AND PassWord = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() ) {
                    int id = rs.getInt("id");
                    String UserName = rs.getString("UserName");
                    String Name = rs.getString("Name");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    String DriverLicenseNumber = rs.getString("DriverLicenseNumber");
                    byte[] Picture = rs.getBytes("Picture");
                    Timestamp DateRegistered = rs.getTimestamp("DateRegistered");
                    boolean isActive = rs.getBoolean("isActive");
                    float AvgRating = rs.getFloat("AverageRating");
                    int NumRating = rs.getInt("NumberRatings");

                    Vehicle vehicle = null;
                    query = "SELECT id, CarMake, CarModel, CarYear, LicensePlateNumber, LoadCapacity, Picture FROM Vehicle WHERE id_Driver = ?";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, id);
                    rs = pstmt.executeQuery();
                    if (rs.next() )
                    {
                        int idVehicle = rs.getInt("id");
                        String CarMake = rs.getString("CarMake");
                        String CarModel = rs.getString("CarModel");
                        int CarYear = rs.getInt("CarYear");
                        String LicensePlate = rs.getString("LicensePlateNumber");
                        float LoadCapacity = rs.getFloat("LoadCapacity");
                        byte[] CarPicture = rs.getBytes("Picture");
                        vehicle = new Vehicle(idVehicle, id, CarMake, CarModel, CarYear, LicensePlate, LoadCapacity, CarPicture);
                    }
                    Driver driver = new Driver(id, UserName, Name, PhoneNumber, DriverLicenseNumber, Picture, DateRegistered, isActive, AvgRating, NumRating, vehicle);
                    return driver;
                }

                conn.close();
            } catch (Exception e) {System.out.println("Error Logging In: " + e.toString());}
            return null;
        }
    }

    public Boolean insertCustomer(String UserName, String PassWord, String Name, String PhoneNumber, Timestamp DateRegistered, String CardNumber, String BillingAddress,
                                    String ExpMonth, String ExpYear, String CVV, String BillingName)
    {
        try{
            insertCustomerAsync ic =  new insertCustomerAsync(UserName, PassWord, Name, PhoneNumber, DateRegistered, CardNumber, BillingAddress, ExpMonth, ExpYear, CVV, BillingName);
            return ic.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertCustomerAsync extends AsyncTask<Void, Void, Boolean>
    {
        private String userName;
        private String passWord;
        private String name;
        private String phoneNumber;
        private Timestamp dateRegistered;
        private String cardNumber;
        private String billingAddress;
        private String expMonth;
        private String expYear;
        private String CVV;
        private String billingName;

        public insertCustomerAsync(String UserName, String PassWord, String Name, String PhoneNumber, Timestamp DateRegistered, String CardNumber, String BillingAddress,
                                   String ExpMonth, String ExpYear, String CVV, String BillingName)
        {
            this.userName = UserName; this.passWord = PassWord; this.name = Name; this.phoneNumber = PhoneNumber; this.dateRegistered = DateRegistered;
            this.cardNumber = CardNumber; this.billingAddress = BillingAddress; this.expMonth = ExpMonth; this.expYear = ExpYear; this.CVV = CVV; this.billingName = BillingName;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertCustomer @UserName = ?, @PassWord = ?, @Name = ?, @PhoneNumber = ?, @DateRegistered = ?, " +
                                " @CardNumber = ?, @BillingAddress = ?, @ExpirationMonth = ?, @ExpirationYear = ?, @CVV = ?, @BillingName = ? ";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                pstmt.setString(3, this.name);
                pstmt.setString(4, this.phoneNumber);
                pstmt.setTimestamp(5, this.dateRegistered);
                pstmt.setString(6, this.cardNumber);
                pstmt.setString(7, this.billingAddress);
                pstmt.setString(8, this.expMonth);
                pstmt.setString(9, this.expYear);
                pstmt.setString(10, this.CVV);
                pstmt.setString(11, this.billingName);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding User: " + e.toString());}
            return false;
        }

    }

    public Boolean insertDriver(String UserName, String PassWord, String Name, String PhoneNumber, String DriverLicenseNumber, Timestamp DateRegistered,
                                    String CarMake, String CarModel, int CarYear, String LicensePlateNumber, float LoadCapacity,
                                    String BankAccountNumber, String RoutingNumber, String BillingName)
    {
        try{
            insertDriverAsync id =  new insertDriverAsync(UserName, PassWord, Name, PhoneNumber,DriverLicenseNumber, DateRegistered, CarMake, CarModel,
                                        CarYear, LicensePlateNumber, LoadCapacity, BankAccountNumber, RoutingNumber, BillingName);
            return id.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertDriverAsync extends AsyncTask<Void, Void, Boolean>
    {
        private String userName;
        private String passWord;
        private String name;
        private String phoneNumber;
        private String driverLicenseNumber;
        private Timestamp dateRegistered;
        private String make;
        private String model;
        private int year;
        private String licensePlate;
        private float loadCapacity;
        private String bankAccountNumber;
        private String routingNumber;
        private String billingName;

        public insertDriverAsync(String UserName, String PassWord, String Name, String PhoneNumber, String DriverLicenseNumber, Timestamp DateRegistered,
                                 String CarMake, String CarModel, int CarYear, String LicensePlateNumber, float LoadCapacity,
                                 String BankAccountNumber, String RoutingNumber, String BillingName)
        {
            this.userName = UserName; this.passWord = PassWord; this.name = Name; this.phoneNumber = PhoneNumber; this.driverLicenseNumber = DriverLicenseNumber; this.dateRegistered = DateRegistered;
            this.make = CarMake; this.model = CarModel; this.year = CarYear; this.licensePlate = LicensePlateNumber; this.bankAccountNumber = BankAccountNumber; this.routingNumber = RoutingNumber;
            this.billingName = BillingName;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertDriver @UserName = ?, @PassWord = ?, @Name = ?, @PhoneNumber = ?, @DriverLicenseNumber = ?, @DateRegistered = ?, " +
                        " @CarMake = ?, @CarModel = ?, @CarYear = ?, @LicensePlateNumber = ?, @LoadCapacity = ?, @BankAccountNumber = ?, @RoutingNumber = ?, @BillingName = ? ";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.userName);
                pstmt.setString(2, this.passWord);
                pstmt.setString(3, this.name);
                pstmt.setString(4, this.phoneNumber);
                pstmt.setString(5, this.driverLicenseNumber);
                pstmt.setTimestamp(6, this.dateRegistered);
                pstmt.setString(7, this.make);
                pstmt.setString(8, this.model);
                pstmt.setInt(9, this.year);
                pstmt.setString(10, this.licensePlate);
                pstmt.setFloat(11, this.loadCapacity);
                pstmt.setString(12, this.bankAccountNumber);
                pstmt.setString(13, this.routingNumber);
                pstmt.setString(14, this.billingName);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding User: " + e.toString());}
            return false;
        }
    }

    public Boolean insertServiceRequest(int idCustomer, String Title, String Description, float Weight, Timestamp DatePosted, float Price,
                                             boolean LoadHelp, boolean UnloadHelp, byte[] Picture, String StartAddress, String EndAddress)
    {
        try{
            insertServiceRequestAsync isr =  new insertServiceRequestAsync(idCustomer, Title, Description, Weight, DatePosted, Price, LoadHelp, UnloadHelp,
                                                    Picture, StartAddress, EndAddress);
            return isr.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertServiceRequestAsync extends AsyncTask<Void, Void, Boolean>
    {

        private int idCustomer; private String title; private String description; private float weight; private Timestamp datePosted;
        private float price; private boolean loadHelp; private boolean unloadHelp; private byte[] picture; private String startAddress;
        private String endAddress;

        public insertServiceRequestAsync(int idCustomer, String Title, String Description, float Weight, Timestamp DatePosted, float Price,
                                         boolean LoadHelp, boolean UnloadHelp, byte[] Picture, String StartAddress, String EndAddress)
        {
            this.idCustomer = idCustomer; this.title = Title; this.description = Description; this.weight = Weight; this.datePosted = DatePosted;
            this.price = Price; this.loadHelp = LoadHelp; this.unloadHelp = UnloadHelp; this.picture = Picture; this.startAddress = StartAddress;
            this.endAddress = EndAddress;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertServiceRequest @idCustomer = ?, @Title = ?, @Description = ?, @TotalWeight = ?, @DatePosted = ?," +
                                    " @Price = ?, @LoadHelp = ?, @UnloadHelp = ?, @Picture = ?, @StartLocation = ?, @EndLocation = ? ";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idCustomer);
                pstmt.setString(2, this.title);
                pstmt.setString(3, this.description);
                pstmt.setFloat(4, this.weight);
                pstmt.setTimestamp(5, this.datePosted);
                pstmt.setFloat(6, this.price);
                pstmt.setBoolean(7, this.loadHelp);
                pstmt.setBoolean(8, this.unloadHelp);
                pstmt.setBytes(9, this.picture);
                pstmt.setString(10, this.startAddress);
                pstmt.setString(11, this.endAddress);

                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Transaction: " + e.toString());}
            return false;
        }
    }



    public Boolean insertDriverRating(int idDriver, int idCustomerWhoRated, int idServiceRequest, int rating, String comment, float newDriverAvg, int newDriverCount)
    {
        try{
            insertDriverRatingAsync idr =  new insertDriverRatingAsync(idDriver, idCustomerWhoRated, idServiceRequest, rating, comment, newDriverAvg, newDriverCount);
            return idr.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class insertDriverRatingAsync extends AsyncTask<Void, Void, Boolean>
    {
        private int idDriver;
        private int idCustomerWhoRated;
        private int idServiceRequest;
        private int rating;
        private String comment;
        private float newDriverAvg;
        private int newDriverCount;

        public insertDriverRatingAsync(int idDriver, int idCustomerWhoRated, int idServiceRequest, int rating, String comment, float newDriverAvg, int newDriverCount)
        {
            this.idDriver = idDriver;
            this.idCustomerWhoRated = idCustomerWhoRated;
            this.idServiceRequest = idServiceRequest;
            this.rating = rating;
            this.comment = comment;
            this.newDriverAvg = newDriverAvg;
            this.newDriverCount = newDriverCount;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query = "EXEC dbo.usp_InsertDriverRating @idDriver = ?, @idCustomerWhoRated = ?, @idServiceRequest = ?, @Rating = ?, @Comment = ?, @NewDriverAverage = ?, " +
                        "@NewDriverCount = ?";

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, this.idDriver);
                pstmt.setInt(2, this.idCustomerWhoRated);
                pstmt.setInt(3, this.idServiceRequest);
                pstmt.setInt(4, this.rating);
                pstmt.setString(5, this.comment);
                pstmt.setFloat(6, this.newDriverAvg);
                pstmt.setInt(7, this.newDriverCount);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next())
                    return true;

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Driver Rating: " + e.toString());}
            return false;
        }

    }

    public ArrayList<ServiceRequest> getServiceRequests()
    {
        try{
            getServiceRequestsAsync gsr =  new getServiceRequestsAsync();
            return gsr.execute().get();
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    private class getServiceRequestsAsync extends AsyncTask<Void, Void, ArrayList<ServiceRequest>>
    {

        public getServiceRequestsAsync()
        {

        }

        @Override
        protected ArrayList<ServiceRequest> doInBackground(Void... params)
        {
            try {
                Connection conn = DataAccess.this.ConnectToDB();

                String query =
                        "SELECT ServiceRequest.id, id_Customer, id_DriverWhoCompleted, Title, Description, TotalWeight, DatePosted, DateClosed, Price, LoadHelp, " +
                                "UnloadHelp, isCompleted, inProgress, StartLocation, EndLocation " +
                        "FROM ServiceRequest LEFT JOIN Location on Location.id_ServiceRequest = ServiceRequest.id";

                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();

                ArrayList<ServiceRequest> ServiceRequests = new ArrayList<ServiceRequest>();

                while(rs.next())
                {
                    int id = rs.getInt("id");
                    int idCustomer = rs.getInt("id_Customer");
                    Integer idDriverWhoCompleted = rs.getInt("id_DriverWhoCompleted");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    float weight = rs.getFloat("TotalWeight");
                    Timestamp datePosted = rs.getTimestamp("DatePosted");
                    Timestamp dateClosed = rs.getTimestamp("DateClosed");
                    float price = rs.getFloat("Price");
                    boolean loadHelp = rs.getBoolean("LoadHelp");
                    boolean unloadHelp = rs.getBoolean("UnloadHelp");
                    byte[] picture = rs.getBytes("Picture");
                    boolean isCompleted = rs.getBoolean("isCompleted");
                    boolean inProgress = rs.getBoolean("inProgress");
                    String startLocation = rs.getString("StartLocation");
                    String endLocation = rs.getString("EndLocation");

                    ServiceRequest sr = new ServiceRequest(id, idCustomer, idDriverWhoCompleted, title, description, weight, datePosted, dateClosed, price,
                                                                loadHelp, unloadHelp, picture, isCompleted, inProgress, startLocation, endLocation);
                    ServiceRequests.add(sr);
                }

                conn.close();
            } catch (Exception e) {System.out.println("Error Adding Driver Rating: " + e.toString());}
            return null;
        }
    }

}
