package assessment8_hotel;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchRoom extends DeleteRoom{
	private static final String QUERY = "select Room_No,Customer_Name,Date,Phone_Number from hotelcustomerdetailsexisting where Customer_Name =?";
	private static final String QUERY2 = "select Room_No,Customer_Name,Date,Phone_Number from hotelcustomerdetailsexisting where Phone_Number =?";

	public void searchbyname() {

		 System.out.println("Enter The Name");
		
		 String Customer_Name =input.next();
		
		try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/hotel_database?", "root", "7397");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
	            preparedStatement.setString(1,Customer_Name);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("Room_No");
	                String name = rs.getString("Customer_Name");
	                String email = rs.getString("Date");
	                String country = rs.getString("Phone_Number");
	              
	                System.out.println("Room NO :"+id + "," +" Name :"+ name + ","+" Date :" + email + ","+"PhoneNumber :" + country + "," );
	            }
	        } catch (SQLException e) {
	            printtheSQLException(e);
	        }
	        // Step 4: try-with-resource statement will auto close the connection.
	}

	    public static void printtheSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                System.out.println("Entered Name Is Not In Our List");
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
}
	
	
	public	void searchbyphonenumber(){
	
		 System.out.println("Enter The Phone Number");
		 BigInteger Phone_Numbers =input.nextBigInteger();
		 String Phone_Number = Phone_Numbers.toString();
		
	try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/hotel_database?", "root", "7397");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(QUERY2);) {
	            preparedStatement.setString(1,Phone_Number);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("Room_No");
	                String name = rs.getString("Customer_Name");
	                String email = rs.getString("Date");
	                String country = rs.getString("Phone_Number");
	              
	                System.out.println("Room NO :"+id + "," +" Name :"+ name + ","+" Date :" + email + ","+"PhoneNumber :" + country + "," );
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        // Step 4: try-with-resource statement will auto close the connection.
	}

	    public static void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                System.out.println("Entered Phone Number Is Not In Our List");
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
}
}