import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// code to interact with the database

public class DataAccessLayer extends main {

	//Showing the list of SalesPerson
	public void displaySalespersons(Stage stage) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM Salespersons";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			ObservableList<SalesPerson> salespersons = FXCollections.observableArrayList();
			while (resultSet.next()) {
				int id = resultSet.getInt("SalespersonID");
				String firstName = resultSet.getString("FirstName");
				String lastName = resultSet.getString("LastName");
				String address = resultSet.getString("Address");
				String phone = resultSet.getString("Phone");
				Date startDate = resultSet.getDate("StartDate");
				Date terminationDate = resultSet.getDate("TerminationDate");
				String manager = resultSet.getString("Manager");

				SalesPerson salesperson = new SalesPerson(id, firstName, lastName, address, phone, startDate, terminationDate, manager);
				salespersons.add(salesperson);
			}

			// Create TableView columns
			TableColumn<SalesPerson, Integer> idCol = new TableColumn<>("ID");
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<SalesPerson, String> firstNameCol = new TableColumn<>("First Name");
			firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

			TableColumn<SalesPerson, String> lastNameCol = new TableColumn<>("Last Name");
			lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

			TableColumn<SalesPerson, String> addressCol = new TableColumn<>("Address");
			addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

			TableColumn<SalesPerson, String> phoneCol = new TableColumn<>("Phone");
			phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

			TableColumn<SalesPerson, Date> startDateCol = new TableColumn<>("Start Date");
			startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

			TableColumn<SalesPerson, Date> terminationDateCol = new TableColumn<>("Termination Date");
			terminationDateCol.setCellValueFactory(new PropertyValueFactory<>("terminationDate"));

			TableColumn<SalesPerson, Integer> managerCol = new TableColumn<>("Manager");
			managerCol.setCellValueFactory(new PropertyValueFactory<>("manager"));

			// Add columns to TableView
			TableView<SalesPerson> tableView = new TableView<>();
			tableView.setItems(salespersons);
			tableView.getColumns().addAll(idCol, firstNameCol, lastNameCol, addressCol, phoneCol, startDateCol, terminationDateCol, managerCol);

			// Add TableView to existing scene
			BorderPane root = (BorderPane) stage.getScene().getRoot();
			root.setCenter(tableView);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public SalesPerson getSalesperson(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		SalesPerson salesperson = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM Salespersons WHERE SalespersonID=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				salesperson = new SalesPerson();
				salesperson.setId(resultSet.getInt("SalespersonID"));
				salesperson.setFirstName(resultSet.getString("FirstName"));
				salesperson.setLastName(resultSet.getString("LastName"));
				salesperson.setAddress(resultSet.getString("Address"));
				salesperson.setPhone(resultSet.getString("Phone"));
				salesperson.setStartDate(resultSet.getDate("StartDate"));
				salesperson.setTerminationDate(resultSet.getDate("TerminationDate"));
				salesperson.setManager(resultSet.getString("Manager"));
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return salesperson;
	}

	//Updating a salesperson
	public void updateSalesperson(SalesPerson salesperson) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE Salespersons SET FirstName=?, LastName=?, Address=?, Phone=?, StartDate=?, TerminationDate=?, Manager=? WHERE SalespersonID=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, salesperson.getFirstName());
			statement.setString(2, salesperson.getLastName());
			statement.setString(3, salesperson.getAddress());
			statement.setString(4, salesperson.getPhone());
			statement.setDate(5, new java.sql.Date(salesperson.getStartDate().getTime()));
			if (salesperson.getTerminationDate() == null) {
				statement.setNull(6, Types.DATE);
			} else {
				statement.setDate(6, Date.valueOf(((Date) salesperson.getTerminationDate()).toLocalDate()));
			}
			statement.setString(7, salesperson.getManager());
			statement.setInt(8, salesperson.getId());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated == 0) {
				throw new SQLException("Salesperson update failed, no rows affected.");
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	//Showing the list of products
	public void displayProducts(Stage stage) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM Products";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			ObservableList<product> products = FXCollections.observableArrayList();
			while (resultSet.next()) {
				int id = resultSet.getInt("ProductID");
				String name = resultSet.getString("Name");
				String manufacturer = resultSet.getString("Manufacturer");
				String style = resultSet.getString("Style");
				double purchasePrice = resultSet.getDouble("Purchase_price");
				double salePrice = resultSet.getDouble("Sale_price");
				int qtyOnHand = resultSet.getInt("Qty_On_Hand");
				double commissionPercentage = resultSet.getDouble("Commission_percentage");
				product product = new product(id, name, manufacturer, style, purchasePrice, salePrice, qtyOnHand, commissionPercentage);
				products.add(product);
			}
			// Create TableView columns
			TableColumn<product, Integer> idCol = new TableColumn<>("ID");
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<product, String> nameCol = new TableColumn<>("Name");
			nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

			TableColumn<product, String> manufacturerCol = new TableColumn<>("Manufacturer");
			manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

			TableColumn<product, String> styleCol = new TableColumn<>("Style");
			styleCol.setCellValueFactory(new PropertyValueFactory<>("style"));

			TableColumn<product, Double> purchasePriceCol = new TableColumn<>("Purchase Price");
			purchasePriceCol.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

			TableColumn<product, Double> salePriceCol = new TableColumn<>("Sale Price");
			salePriceCol.setCellValueFactory(new PropertyValueFactory<>("salePrice"));

			TableColumn<product, Integer> qtyOnHandCol = new TableColumn<>("Qty On Hand");
			qtyOnHandCol.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

			TableColumn<product, Double> commissionPercentageCol = new TableColumn<>("Commission Percentage");
			commissionPercentageCol.setCellValueFactory(new PropertyValueFactory<>("commissionPercentage"));

			// Add columns to TableView
			TableView<product> tableView = new TableView<>();
			tableView.setItems(products);
			tableView.getColumns().addAll(idCol, nameCol, manufacturerCol, styleCol, purchasePriceCol, salePriceCol, qtyOnHandCol, commissionPercentageCol);

			// Create a new Scene and set it to the stage
			BorderPane root = (BorderPane) stage.getScene().getRoot();
			root.setCenter(tableView);

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public product getProductID(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		product product = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM Products WHERE ProductID=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				product = new product();
				product.setId(resultSet.getInt("ProductID"));
				product.setName(resultSet.getString("Name"));
				product.setManufacturer(resultSet.getString("manufacturer"));
				product.setStyle(resultSet.getString("style"));
				product.setPurchasePrice(resultSet.getDouble("purchase_price"));
				product.setSalePrice(resultSet.getDouble("Sale_Price"));
				product.setQtyOnHand(resultSet.getInt("Qty_On_Hand"));
				product.setCommissionPercentage(resultSet.getDouble("Commission_Percentage"));
			}

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return product;
	}

	public List<product> getAllProducts() throws SQLException {
	    List<product> products = new ArrayList<>();
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	    try {
	        connection = DriverManager.getConnection(url, user, password);
	        statement = connection.createStatement();
	        String sql = "SELECT * FROM Products";
	        resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	            int id = resultSet.getInt("productid");
	            String name = resultSet.getString("Name");
	            double price = resultSet.getDouble("sale_Price");
	            product product = new product(id, name, price);
	            products.add(product);
	        }
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return products;
	}

	//Updating the product
	public void updateProduct(product product) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE Products SET Name=?, Manufacturer=?, Style=?, Purchase_Price=?, Sale_Price=?, Qty_On_Hand=?, Commission_Percentage=? WHERE ProductID=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setString(2, product.getManufacturer());
			statement.setString(3, product.getStyle());
			statement.setDouble(4, product.getPurchasePrice());
			statement.setDouble(5, product.getSalePrice());
			statement.setInt(6, product.getQtyOnHand());
			statement.setDouble(7, product.getCommissionPercentage());
			statement.setInt(8, product.getId());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated == 0) {
				throw new SQLException("Product update failed, no rows affected.");
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	//Display a list of Customer
	public void displayCustomers(Stage stage) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM Customers";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			ObservableList<Customer> customers = FXCollections.observableArrayList();
			while (resultSet.next()) {
				int id = resultSet.getInt("CustomerID");
				String firstName = resultSet.getString("FirstName");
				String lastName = resultSet.getString("LastName");
				String address = resultSet.getString("Address");
				String phone = resultSet.getString("Phone");
				LocalDate startDate = resultSet.getDate("StartDate").toLocalDate();
				Customer customer = new Customer(id, firstName, lastName, address, phone, startDate);
				customers.add(customer);
			}

			// Create TableView columns
			TableColumn<Customer, Integer> idCol = new TableColumn<>("ID");
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
			nameCol.setCellValueFactory(cellData -> {
				String firstName = cellData.getValue().getFirstName();
				String lastName = cellData.getValue().getLastName();
				return new SimpleStringProperty(firstName + " " + lastName);
			});

			TableColumn<Customer, String> addressCol = new TableColumn<>("Address");
			addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

			TableColumn<Customer, String> phoneCol = new TableColumn<>("Phone");
			phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

			TableColumn<Customer, LocalDate> startDateCol = new TableColumn<>("Start Date");
			startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

			// Add columns to TableView
			TableView<Customer> tableView = new TableView<>();
			tableView.setItems(customers);
			tableView.getColumns().addAll(idCol, nameCol, addressCol, phoneCol, startDateCol);

			// Create a new Scene and set it to the stage
			BorderPane root = (BorderPane) stage.getScene().getRoot();
			root.setCenter(tableView);

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public List<Customer> getAllCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<>();
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	    try {
	        connection = DriverManager.getConnection(url, user, password);
	        statement = connection.createStatement();
	        String sql = "SELECT * FROM Customers";
	        resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	            ;
	            String name = resultSet.getString("firstname");
	            Customer customer = new Customer(name);
	            customers.add(customer);
	        }
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return customers;
	}
	
	public List<SalesPerson> getAllSalespersons() throws SQLException {
	    List<SalesPerson> salespersons = new ArrayList<>();
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	    try {
	        connection = DriverManager.getConnection(url, user, password);
	        String sql = "SELECT * FROM Salespersons";
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	            int id = resultSet.getInt("SalesPersonID");
	            String firstName = resultSet.getString("FirstName");
	            String lastName = resultSet.getString("LastName");
	            SalesPerson salesperson = new SalesPerson(id, firstName, lastName);
	            salespersons.add(salesperson);
	        }
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return salespersons;
	}

	//Display a list of sales
	public void displaySales(Stage stage) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT Sales.ID, Sales.ProductID, Sales.CustomerID, Sales.SalesDate, Salespersons.SalespersonID, Products.Name, Customers.FirstName, Customers.LastName, Salespersons.FirstName, Salespersons.LastName, Sales.Price, Sales.SalespersonCommission FROM Sales " +
	                "JOIN Salespersons ON Sales.SalespersonID = Salespersons.SalespersonID " +
	                "JOIN Products ON Sales.ProductID = Products.ProductID " +
	                "JOIN Customers ON Sales.CustomerID = Customers.CustomerID";



			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			ObservableList<Sale> sales = FXCollections.observableArrayList();
					while (resultSet.next()) {
						int id = resultSet.getInt("ID");
						int productId = resultSet.getInt("ProductID");
						String productName = resultSet.getString("Name");
						product product = new product(productId, productName);
		
						int customerId = resultSet.getInt("CustomerID");
						String customerFirstName = resultSet.getString("FirstName");
						String customerLastName = resultSet.getString("LastName");
						Customer customer = new Customer(customerId, customerFirstName, customerLastName);
		
						LocalDate saleDate = resultSet.getDate("SalesDate").toLocalDate();
		
						int salespersonId = resultSet.getInt("SalespersonID");
						String salespersonFirstName = resultSet.getString("FirstName");
						String salespersonLastName = resultSet.getString("LastName");
						SalesPerson salesperson = new SalesPerson(salespersonId, salespersonFirstName);
		
						double price = resultSet.getDouble("Price");
						double salespersonCommission = resultSet.getDouble("SalespersonCommission");
		
						Sale sale = new Sale(id, product, customer, saleDate, salesperson, price, salespersonCommission);
						sales.add(sale);
					}

			// Create TableView columns
					TableView<Sale> salesTable = new TableView<>();

					TableColumn<Sale, String> productNameCol = new TableColumn<>("Product Name");
					productNameCol.setCellValueFactory(cellData -> {
					    String productName = cellData.getValue().getProduct().getName();
					    return new SimpleStringProperty(productName);
					});

					TableColumn<Sale, String> customerNameCol = new TableColumn<>("Customer Name");
					customerNameCol.setCellValueFactory(cellData -> {
					    String customerName = cellData.getValue().getCustomer().getFirstName() + " " + cellData.getValue().getCustomer().getLastName();
					    return new SimpleStringProperty(customerName);
					});

					TableColumn<Sale, LocalDate> dateCol = new TableColumn<>("Sale Date");
					dateCol.setCellValueFactory(new PropertyValueFactory<>("saleDate"));

					TableColumn<Sale, Double> priceCol = new TableColumn<>("Price");
					priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

					TableColumn<Sale, SalesPerson> salesPersonCol = new TableColumn<>("Salesperson");
					salesPersonCol.setCellValueFactory(new PropertyValueFactory<>("salesperson"));

					TableColumn<Sale, Double> commissionCol = new TableColumn<>("Salesperson Commission");
					commissionCol.setCellValueFactory(new PropertyValueFactory<>("salespersonCommission"));

					salesTable.getColumns().addAll(productNameCol, customerNameCol, dateCol, priceCol, salesPersonCol, commissionCol);


			salesTable.setItems(sales);

			// Create a new Scene and set it to the stage
			BorderPane root = (BorderPane) stage.getScene().getRoot();
			root.setCenter(salesTable);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//Creating a sale

	public void createSale(Sale sale) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    try {
	        connection = DriverManager.getConnection(url, user, password);
	        String sql = "INSERT INTO Sales (Id, CustomerID, SaleDate, Price, SalespersonID, SalespersonCommission) VALUES (?, ?, ?, ?, ?, ?)";
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, sale.getProduct().getId());
	        statement.setInt(2, sale.getCustomer().getId());
	        statement.setDate(3, Date.valueOf(sale.getSaleDate()));
	        statement.setDouble(4, sale.getPrice());
	        statement.setInt(5, sale.getSalesperson().getId());
	        statement.setDouble(6, sale.getSalespersonCommission());
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted == 0) {
	            throw new SQLException("Sale creation failed, no rows affected.");
	        }
	    } finally {
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}


	//quaterly sale report

	public VBox displayQuarterlyCommissionReport(SalesPerson salesperson, int year, int quarter) {
	    double totalCommission = 0;
	    LocalDate startDate = LocalDate.of(year, (quarter - 1) * 3 + 1, 1);
	    LocalDate endDate = LocalDate.of(year, quarter * 3, 31);
	    List<Sale> sales = salesperson.getSales();
	    for (Sale sale : sales) {
	        LocalDate saleDate = sale.getSaleDate();
	        if (saleDate.isAfter(startDate) && saleDate.isBefore(endDate)) {
	            totalCommission += sale.getSalespersonCommission();
	        }
	    }
	    // Create the VBox and add the report information as Label objects
	    VBox reportBox = new VBox();
	    reportBox.getChildren().add(new Label("Salesperson: " + salesperson.getFirstName() + " " + salesperson.getLastName()));
	    reportBox.getChildren().add(new Label("Quarter: Q" + quarter + " " + year));
	    reportBox.getChildren().add(new Label("Total Commission: " + totalCommission));
	    return reportBox;
	}


}
