package assessment8_hotel;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main_Hotel {
	private static final String INSERT_hotelcustomerdetailsexisting_SQL = "INSERT INTO hotelcustomerdetailsexisting"
			+ "  (Room_No,Customer_Name,Date,Phone_Number) VALUES " + " (?, ?, ?,?);";

	static BigInteger Phone_Numbers = new BigInteger("9087313266");
	String Phone_Number = Phone_Numbers.toString();

	static int Room_Nos = 1;
	static String name = "Sridhar";
	static String Date = "2023-01-11";
	static Scanner input = new Scanner(System.in);
	static Thread t = new Thread();
	static int rooms = 10;

	public static void main(String[] args) throws SQLException, InterruptedException {

		System.out.println("**************************************************************************************");

		System.out.println("<<<Hi Welcome To Sea Horizon Hotel>>>>");
		System.out.println("**************************************************************************************");
		System.out.println("Hotel Address:");
		System.out.println("Alandur,Chennai");
		System.out.println("For Contact Details:9876542345");
		System.out.println("**************************************************************************************");
		Thread.sleep(1000);
		System.out.println("Enter 1 for viewing the hotel facilites and know about hotel");
		System.out.println("If know about our HOTEL and facilities Enter 2 for the Service");
		System.out.println("Enter Your Choice");

		System.out.println("**************************************************************************************");

		while (true) {

			try {
				System.out.println("Enter Your Service Choice");
				Integer optionMenu = input.nextInt();
				if (optionMenu == 1) {
					Hotel_Services hotel_services = new Hotel_Services();
					hotel_services.services();
				} else if (optionMenu == 2) {

					process();
					break;
				}

				else {
					System.out.println("Entered service is Not Available ");
					System.out.println("Please Enter The Available Services");
				}

			}

			catch (Exception e) {
				System.out.println("I think You Have Entered A Value That Does'nt Match");
				System.out.println("Please Enter Correctly");
			}
		}

		// createTableExample.insertRecord();
	}

	public void insertRecord() throws SQLException {
		System.out.println(INSERT_hotelcustomerdetailsexisting_SQL);
		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database?", "root",
				"7397");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_hotelcustomerdetailsexisting_SQL)) {

			preparedStatement.setInt(1, Room_Nos);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, Date);
			preparedStatement.setString(4, Phone_Number);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			// print SQL exception information
			printSQLException(e);
		}

		// Step 4: try-with-resource statement will auto close the connection.
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
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

	public static void process() throws InterruptedException {

		while (true) {
			System.out
					.println("**************************************************************************************");
			System.out.println("We Hope you are vaccinated and sanitized");
			System.out.println(" We have the facilities which are given below ");
			Thread.sleep(1000);
			System.out.println("Booking A Room ,Vacating a room ,Search The Guests Are The Process Available  ");
			Thread.sleep(1000);
			System.out.println("If you want to book a room  Enter-1 ");
			System.out.println("If you Vacate to book a room  Enter-2 ");
			System.out.println("If you want to search a guest Enter-3 ");
			System.out
					.println("**************************************************************************************");

			try {
				Integer option = input.nextInt();
				if (option == 1) {
					AddRoom addroom = new AddRoom();
					addroom.booking();

				} else if (option == 2) {
					DeleteRoom deleteStatementExample = new DeleteRoom();
					deleteStatementExample.deleteRecord();

				} else if (option == 3) {
					SearchRoom searchroom = new SearchRoom();
					System.out.println("Enter 1 for search by Name");
					System.out.println("Enter 1 for search by PhoneNumber");
					int answer = input.nextInt();
					if (answer == 2) {

						searchroom.searchbyphonenumber();

					} else if (answer == 1) {
						searchroom.searchbyname();
					} else {
						System.out.println("Please enter the correct option");
					}
				} else {
					System.out.println("The Service you have entered is not available...");
				}

			} catch (Exception e) {
				System.out.println("Please Enter The Correct Match");

				break;
			}
		}
	}
}
