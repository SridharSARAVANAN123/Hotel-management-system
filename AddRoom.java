package assessment8_hotel;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddRoom extends Main_Hotel {
	private static final String INSERT_hotelcustomerdetailsexisting_SQL = "INSERT INTO hotelcustomerdetailsexisting"
			+ "  (Room_No,Customer_Name,Date,Phone_Number) VALUES " + " (?, ?, ?,?);";
	private static final String QUERY = "select Room_No, Customer_Name,Date,Phone_Number from hotelcustomerdetailsexisting where Room_No =?";

	void booking() throws InterruptedException, SQLException {
		System.out.println("Total Available Rooms" + " " + rooms);

		System.out.println("Do You Wanna Book A Room Please Enter 1 or Enter 2 For Exiting");
		// Booking Ah Room

		int option = input.nextInt();
		if (option == 1) {
			System.out.println("Enter the room you wanna book");
			int Room_No = input.nextInt();
			if (Room_No <= 10) {
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database?",
						"root", "7397");

						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
					preparedStatement.setInt(1, Room_No);
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery(); 
					// Step 4: Process the ResultSet object.

					if (rs.next()) {
						int id = rs.getInt("Room_No");
						String name = rs.getString("Customer_Name");
						String email = rs.getString("Date");
						String country = rs.getString("Phone_Number");

						System.out.println("Room NO :" + id + "," + " Name :" + name + "," + " Date :" + email + ","
								+ "PhoneNumber :" + country + ",");

						if (Room_No == id) {
							System.out.println("Checking If The Room is Available....");
							Thread.sleep(1000);
							System.out.println(
									"You Cannot Book This Room ,Please Enter A Different Room Because It Is Alraedy Occupied ");
						}

					}

					else {
						System.out.println("Please Enter Your Name And PhoneNumber");
						String names = input.next();
						BigInteger phoneNumbers = input.nextBigInteger();
						String Phone_Number = phoneNumbers.toString();
						System.out.println("How MAny Days Do want to stay");
						Integer options = input.nextInt();
						
						//Integer option3 = input.nextInt();
						//if (options == 1) {
							System.out.println("You are Addedd into our hotel");

							LocalDate nowDate = LocalDate.now();
							LocalTime nowTime = LocalTime.now();
							String Date = nowDate.toString();

							System.out.println(INSERT_hotelcustomerdetailsexisting_SQL);
							try (Connection connections = DriverManager
									.getConnection("jdbc:mysql://localhost:3306/hotel_database?", "root", "7397");

									// Step 2:Create a statement using connection object
									PreparedStatement preparedStatements = connections
											.prepareStatement(INSERT_hotelcustomerdetailsexisting_SQL)) {

								preparedStatements.setInt(1, Room_No);
								preparedStatements.setString(2, names);
								preparedStatements.setString(3, Date);
								preparedStatements.setString(4, Phone_Number);
								preparedStatements.executeUpdate();

							} catch (SQLException e) {

								// print SQL exception information
								printSQLException(e);
							} finally {

							}

//	        			else {
//	        				System.out.println("Entered Type Of Room is not Available ");
//	        			}

//	        			else {
//	        				System.out.println("Our Hotel Contains Only Ten Rooms You Cannot Book Other Than the Available Rooms");
//	        			}
						} 

					}

				

				catch (SQLException e) {
					printtheSQLException(e);
				}
			}

			else {
				System.out.println("Our Hotel Contains Only 10 Rooms Please Book Available Rooms");


			}
		} else {
			System.out.println("ThankYoufor Visiting");

		}

	}

	public static void printtheSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
}