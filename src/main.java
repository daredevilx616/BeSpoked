import java.sql.DriverManager;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
	//add your own credentials to the PostgreSQL server
	final static String url = "jdbc:postgresql://XXX/bespoked_bikes";
	final static String user = "XXX";
	final static String password = "XXX";


	public static void main(String[] args) {


		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// Connect to database
			conn = DriverManager.getConnection(url, user, password);

			// Create a new statement object
			stmt = conn.createStatement();

			// Insert data into Products table
			String insertProducts = "INSERT INTO products (name, manufacturer, style, purchase_price, sale_price, qty_on_hand, commission_percentage, productid) "
					+ "VALUES ('Mountain Bike', 'Brand A', 'Off-road', 1500.00, 2000.00, 10, 10.0, 1), "
					+ "('Road Bike', 'Brand B', 'Race', 2500.00, 3500.00, 5, 12.5, 2), "
					+ "('Hybrid Bike', 'Brand C', 'Commuter', 1000.00, 1500.00, 20, 7.5, 3)";
			stmt.executeUpdate(insertProducts);


			// Insert data into Customers table
			String insertCustomers = "INSERT INTO Customers (Customerid, FirstName, LastName, Address, Phone, StartDate) "
					+ "VALUES (1, 'John', 'Doe', '123 Main St', '123-456-7890', '2022-03-11'), "
					+ "(2, 'Jane', 'Doe', '456 Oak Ave', '987-654-3210', '2022-03-10')";
			stmt.executeUpdate(insertCustomers);

			// Insert data into Salespersons table
			String insertSalespersons = "INSERT INTO Salespersons (Salespersonid, FirstName, LastName, Address, Phone, StartDate, TerminationDate, Manager) "
					+ "VALUES (1, 'Alice', 'Smith', '123 Main St', '555-1234', '2022-01-01', null, 'John Manager'), "
					+ "(2, 'Bob', 'Johnson', '456 Elm St', '555-5678', '2022-02-01', '2022-07-01', 'Jane Manager')";
			stmt.executeUpdate(insertSalespersons);

			// Insert data into Sales table
			String insertSales = "INSERT INTO Sales (ProductID, SalespersonID, CustomerID, SalesDate) "
					+ "VALUES (1, 1, 1, '2022-01-01'), "
					+ "(2, 2, 2, '2022-01-02')";
			stmt.executeUpdate(insertSales);

			String insertDiscount = "INSERT INTO Discount (ProductID, BeginDate, EndDate, DiscountPercentage) "
					+ "VALUES (1, '2023-04-01', '2023-04-30', 0.1), "
					+ "(2, '2023-05-01', '2023-05-31', 0.15)";
			stmt.executeUpdate(insertDiscount);


			// Print out all products in the database
			String query = "SELECT * FROM Products";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("productid");
				String name = rs.getString("name");
				String manufacturer = rs.getString("manufacturer");
				String style = rs.getString("style");
				double purchasePrice = rs.getDouble("purchase_price");
				double salePrice = rs.getDouble("sale_price");
				int qtyOnHand = rs.getInt("qty_on_hand");
				double commissionPercentage = rs.getDouble("commission_percentage");
				System.out.println(id + ", " + name + ", " + manufacturer + ", " + style + ", " + purchasePrice + ", " + salePrice + ", " + qtyOnHand + ", " + commissionPercentage);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}

