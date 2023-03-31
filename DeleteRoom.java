package assessment8_hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRoom extends AddRoom {
	 private static final String DELETE_hotelcustomerdetailsexisting_SQL = "delete from hotelcustomerdetailsexisting where Room_No = ?;";
	 
      
      public void deleteRecord() throws SQLException {
    	  
    	  System.out.println("Enter the Room number you Want to Vacate");
			Integer options=input.nextInt();
          System.out.println(DELETE_hotelcustomerdetailsexisting_SQL);

          // no need to register driver manually

          // Step 1: Establishing a Connection
          try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database?", "root", "7397"
                  );

              // Step 2:Create a statement using connection object
              PreparedStatement statement = connection.prepareStatement(DELETE_hotelcustomerdetailsexisting_SQL);) {
           
        	  
        	  statement.setInt(1, options);
              // Step 3: Execute the query or update query
              int result = statement.executeUpdate();
             
              if(result==0) {
            	  System.out.println("The Room number you have entered is un occupied you cannot vacate");
              }
              else {
            	  System.out.println("Number of records affected :: " + result);
              }
          } catch (SQLException e) {

              // print SQL exception information
              printSQLException(e);
          }

          // Step 4: try-with-resource statement will auto close the connection.
      }

      public static void printSQLException(SQLException ex) {
          for (Throwable e: ex) {
              if (e instanceof SQLException) {
                  // e.printStackTrace(System.err);
                  System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                  System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                  System.err.println("Message: " + e.getMessage());
                  Throwable t = ex.getCause();
                  while (t != null) {
                      System.out.println("Cause: " + t);
                      t = t.getCause();
                  }
              }
          }
      }
}
