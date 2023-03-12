import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalesTrackingApp extends Application {
	DataAccessLayer access = new DataAccessLayer();

	public void showUpdateSalespersonScene() {
		// Create fields for the user to enter data
		TextField idField = new TextField();
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField addressField = new TextField();
		TextField phoneField = new TextField();
		DatePicker startDatePicker = new DatePicker();
	    TextField managerField = new TextField();
		DatePicker terminationDate = new DatePicker();


		// Create labels for the fields
		Label idLabel = new Label("ID*:");
		Label firstNameLabel = new Label("First Name:");
		Label lastNameLabel = new Label("Last Name:");
		Label addressLabel = new Label("Address:");
		Label phoneLabel = new Label("Phone:");
		Label startDateLabel = new Label("Start Date:");
	    Label managerLabel = new Label("Manager:");
	    Label terminationLabel = new Label("Termination Date:");
		// Create a save button
		Button saveButton = new Button("Save");

		// Create a VBox to hold the labels and fields
	    VBox vBox = new VBox();
	    vBox.getChildren().addAll(idLabel, idField, firstNameLabel, firstNameField, lastNameLabel, lastNameField,
	            addressLabel, addressField, phoneLabel, phoneField, managerLabel, managerField, startDateLabel, startDatePicker, terminationLabel, terminationDate, saveButton);

		// Create a new scene and add the VBox to it
		Scene updateSalespersonScene = new Scene(vBox, 400, 400);

		// Create a new stage and set the scene
		Stage updateSalespersonStage = new Stage();
		updateSalespersonStage.setScene(updateSalespersonScene);
		updateSalespersonStage.show();

		// Add event handler for saveButton
		saveButton.setOnAction(event -> {
			// Get the values from the fields and update the salesperson object
			int id = Integer.parseInt(idField.getText());
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String address = addressField.getText();
			String phone = phoneField.getText();
			LocalDate startDate = startDatePicker.getValue();
	        String manager = managerField.getText();
			LocalDate termination_Date = terminationDate.getValue();


			// Update the salesperson in the database
			try {
				SalesPerson salesperson = access.getSalesperson(id);

				if (salesperson == null) {
					// Show an alert to indicate that the salesperson was not found
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Update Failed");
					alert.setHeaderText(null);
					alert.setContentText("No salesperson found with ID " + id);
					alert.showAndWait();
				} else {
					// Update the salesperson fields
					if (!firstName.isEmpty()) {
						salesperson.setFirstName(firstName);
					}
					if (!lastName.isEmpty()) {
						salesperson.setLastName(lastName);
					}
					if (!address.isEmpty()) {
						salesperson.setAddress(address);
					}
					if (!phone.isEmpty()) {
						salesperson.setPhone(phone);
					}
					if (startDate != null) {
						salesperson.setStartDate(Date.valueOf(startDate));
					}
					if (!manager.isEmpty()) {
	                    salesperson.setManager(manager);
	                }
					if (termination_Date != null) {
						salesperson.setTerminationDate(Date.valueOf(termination_Date));			
					}

					access.updateSalesperson(salesperson);

					// Show an alert to indicate the update was successful
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Update Successful");
					alert.setHeaderText(null);
					alert.setContentText("Salesperson updated successfully");
					alert.showAndWait();

					// Close the update salesperson stage
					updateSalespersonStage.close();
				}
			} catch (SQLException e) {
				// Show an alert to indicate the update failed
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Update Failed");
				alert.setHeaderText(null);
				alert.setContentText("An error occurred while updating the salesperson");
				alert.showAndWait();
				e.printStackTrace();
			}
		});
	}
	
	public void showUpdateProductScene() {
	    // Create fields for the user to enter data
	    TextField idField = new TextField();
	    TextField nameField = new TextField();
	    TextField manufacturerField = new TextField();
	    TextField styleField = new TextField();
	    TextField purchasePriceField = new TextField();
	    TextField salePriceField = new TextField();
	    TextField qtyOnHandField = new TextField();
	    TextField commissionPercentageField = new TextField();

	    // Create labels for the fields
	    Label idLabel = new Label("Product ID*:");
	    Label nameLabel = new Label("Name:");
	    Label manufacturerLabel = new Label("Manufacturer:");
	    Label styleLabel = new Label("Style:");
	    Label purchasePriceLabel = new Label("Purchase Price:");
	    Label salePriceLabel = new Label("Sale Price:");
	    Label qtyOnHandLabel = new Label("Quantity on Hand:");
	    Label commissionPercentageLabel = new Label("Commission Percentage:");

	    // Create a save button
	    Button saveButton = new Button("Save");

	    // Create a VBox to hold the labels and fields
	    VBox vBox = new VBox();
	    vBox.getChildren().addAll(idLabel, idField, nameLabel, nameField, manufacturerLabel, manufacturerField,
	            styleLabel, styleField, purchasePriceLabel, purchasePriceField, salePriceLabel, salePriceField,
	            qtyOnHandLabel, qtyOnHandField, commissionPercentageLabel, commissionPercentageField, saveButton);

	    // Create a new scene and add the VBox to it
	    Scene updateProductScene = new Scene(vBox, 400, 400);

	    // Create a new stage and set the scene
	    Stage updateProductStage = new Stage();
	    updateProductStage.setScene(updateProductScene);
	    updateProductStage.show();

	    // Add event handler for saveButton
	    saveButton.setOnAction(event -> {
	        // Get the values from the fields and update the product object
	        int id = Integer.parseInt(idField.getText());
	        String name = nameField.getText();
	        String manufacturer = manufacturerField.getText();
	        String style = styleField.getText();

	        // Validate fields before parsing to numbers
	        double purchasePrice = 0;
	        if (!purchasePriceField.getText().isEmpty()) {
	            purchasePrice = Double.parseDouble(purchasePriceField.getText());
	        }

	        double salePrice = 0;
	        if (!salePriceField.getText().isEmpty()) {
	            salePrice = Double.parseDouble(salePriceField.getText());
	        }

	        int qtyOnHand = 0;
	        if (!qtyOnHandField.getText().isEmpty()) {
	            qtyOnHand = Integer.parseInt(qtyOnHandField.getText());
	        }

	        double commissionPercentage = 0;
	        if (!commissionPercentageField.getText().isEmpty()) {
	            commissionPercentage = Double.parseDouble(commissionPercentageField.getText());
	        }
	        
	        // rest of the code
	   

	        // Update the product in the database
	        try {
	            product product = access.getProductID(id);

	            if (product == null) {
	                // Show an alert to indicate that the product was not found
	                Alert alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Update Failed");
	                alert.setHeaderText(null);
	                alert.setContentText("No product found with ID " + id);
	                alert.showAndWait();
	            } else {
	                // Update the product fields
	                if (!name.isEmpty()) {
	                    product.setName(name);
	                }
	                if (!manufacturer.isEmpty()) {
	                    product.setManufacturer(manufacturer);
	                }
	                if (!style.isEmpty()) {
	                    product.setStyle(style);
	                }
	                if (purchasePrice != 0) {
	                    product.setPurchasePrice(purchasePrice);
	                }
	                if (salePrice != 0) {
	                    product.setSalePrice(salePrice);
	                }
	                if (qtyOnHand != 0) {
	                    product.setQtyOnHand(qtyOnHand);
	                }
	                if (commissionPercentage != 0) {
	                    product.setCommissionPercentage(commissionPercentage);
	                }

	                access.updateProduct(product);

	                // Show an alert to indicate the update was successful
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Update Successful");
	                alert.setHeaderText(null);
	                alert.setContentText("Product updated successfully");
	                alert.showAndWait();

	                // Close the update product stage
	                updateProductStage.close();
	            }
	        } catch (SQLException e) {
	            // Show an alert to indicate the update failed
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Update Failed");
	            alert.setHeaderText(null);
	            alert.setContentText("An error occurred while updating the product");

	            alert.showAndWait();
	            e.printStackTrace();
	        }
	    });
	}
	
	private void showCreateSaleScene() throws SQLException {
	    // Create labels and form fields
	    Label productLabel = new Label("Product:");
	    ChoiceBox<product> productChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(access.getAllProducts()));
	    productChoiceBox.getSelectionModel().selectFirst();

	    Label customerLabel = new Label("Customer:");
	    ChoiceBox<Customer> customerChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(access.getAllCustomers()));
	    customerChoiceBox.getSelectionModel().selectFirst();

	    Label salespersonLabel = new Label("Salesperson:");
	    ChoiceBox<SalesPerson> salespersonChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(access.getAllSalespersons()));
	    salespersonChoiceBox.getSelectionModel().selectFirst();

	    Label priceLabel = new Label("Price:");
	    TextField priceTextField = new TextField();

	    // Create the dialog
	    Dialog<Sale> dialog = new Dialog<>();
	    dialog.setTitle("Create Sale");
	    dialog.setHeaderText(null);
	    dialog.setResizable(true);

	    // Set the button types
	    ButtonType createButtonType = new ButtonType("Create", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

	    // Create the layout
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(20, 150, 10, 10));
	    grid.add(productLabel, 0, 0);
	    grid.add(productChoiceBox, 1, 0);
	    grid.add(customerLabel, 0, 1);
	    grid.add(customerChoiceBox, 1, 1);
	    grid.add(salespersonLabel, 0, 2);
	    grid.add(salespersonChoiceBox, 1, 2);
	    grid.add(priceLabel, 0, 3);
	    grid.add(priceTextField, 1, 3);

	    dialog.getDialogPane().setContent(grid);

	    // Show the dialog and create the sale if the user clicks the create button
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == createButtonType) {
	            try {
	                product product = productChoiceBox.getSelectionModel().getSelectedItem();
	                Customer customer = customerChoiceBox.getSelectionModel().getSelectedItem();
	                SalesPerson salesperson = salespersonChoiceBox.getSelectionModel().getSelectedItem();
	                double price = Double.parseDouble(priceTextField.getText());
	                LocalDate saleDate = LocalDate.now();
	                double commissionRate = Sale.getCommissionRate();
	                double salespersonCommission = price * commissionRate / 100.0;
	                Sale sale = new Sale(product, customer, salesperson, price, saleDate, salespersonCommission);
	                access.createSale(sale);
	                return sale;
	            } catch (SQLException e) {
	                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to create sale: " + e.getMessage(), ButtonType.OK);
	                alert.showAndWait();
	            } catch (NumberFormatException e) {
	                Alert alert = new Alert(Alert.AlertType.ERROR, "Price must be a number.", ButtonType.OK);
	                alert.showAndWait();
	            }
	        }
	        return null;
	    });

	    dialog.showAndWait();
	}

	
	@Override
	public void start(Stage primaryStage) {
	    // Create a menu bar
        primaryStage.setTitle("Sales Management System");

	    MenuBar menuBar = new MenuBar();

	    // Create file menu
	    Menu fileMenu = new Menu("File");
	    MenuItem exitMenuItem = new MenuItem("Exit");
	    fileMenu.getItems().add(exitMenuItem);

	    // Create salespersons menu
	    Menu salespersonsMenu = new Menu("Salespersons");
	    MenuItem listSalespersonsMenuItem = new MenuItem("List Salespersons");
	    MenuItem updateSalespersonMenuItem = new MenuItem("Update Salesperson");
	    salespersonsMenu.getItems().addAll(listSalespersonsMenuItem, updateSalespersonMenuItem);

	    // Create products menu
	    Menu productsMenu = new Menu("Products");
	    MenuItem listProductsMenuItem = new MenuItem("List Products");
	    MenuItem updateProductMenuItem = new MenuItem("Update Product");
	    productsMenu.getItems().addAll(listProductsMenuItem, updateProductMenuItem);

	    // Create customers menu
	    Menu customersMenu = new Menu("Customers");
	    MenuItem listCustomersMenuItem = new MenuItem("List Customers");
	    customersMenu.getItems().add(listCustomersMenuItem);

	    // Create sales menu
	    Menu salesMenu = new Menu("Sales");
	    MenuItem listSalesMenuItem = new MenuItem("List Sales");
	    MenuItem createSaleMenuItem = new MenuItem("Create Sale");
	    MenuItem quarterlyReportMenuItem = new MenuItem("Quarterly Report");
	    salesMenu.getItems().addAll(listSalesMenuItem, createSaleMenuItem, quarterlyReportMenuItem);

	    // Add menus to menu bar
	    menuBar.getMenus().addAll(fileMenu, salespersonsMenu, productsMenu, customersMenu, salesMenu);

	    // Create a border pane for the main window
	    BorderPane root = new BorderPane();
	    root.setTop(menuBar);

	    // Add event handler for listSalespersonsMenuItem
	    listSalespersonsMenuItem.setOnAction(event -> {
	        try {
	            access.displaySalespersons(primaryStage);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    });

	    // Add event handler for updateSalespersonMenuItem
	    updateSalespersonMenuItem.setOnAction(event -> {
	        showUpdateSalespersonScene();
	    });

	    // Add event handler for listProductsMenuItem
	    listProductsMenuItem.setOnAction(event -> {
	        try {
	            access.displayProducts(primaryStage);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    });

	    // Add event handler for updateProductMenuItem
	    updateProductMenuItem.setOnAction(event -> {
	        showUpdateProductScene();
	    });
	    listCustomersMenuItem.setOnAction(event -> {
	        try {
	            access.displayCustomers(primaryStage);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    });
	    
	    listSalesMenuItem.setOnAction(event -> {
	        try {
	            access.displaySales(primaryStage);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    });
	    createSaleMenuItem.setOnAction(event -> {
	        try {
	        	showCreateSaleScene();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    });
	    

	    // Create a scene and set it for the primary stage
	    Scene scene = new Scene(root, 800, 600);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
