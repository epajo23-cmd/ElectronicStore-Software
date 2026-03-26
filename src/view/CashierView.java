package view;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Controller.CashierController;
import Controller.LoginController;
import Model.Product.Item;
import Model.Users.Cashier;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CashierView {

    private Cashier opener;
    private CashierController cashierController;
    private ComboBox<String> additems;

    public CashierView(Cashier opener) {
        this.opener = opener;
        this.cashierController = new CashierController(opener); // Initialize CashierController
    }

    @SuppressWarnings("unchecked")
    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
        Image appLogo = new Image("file:src/images/App_Logo.png");
        primaryStage.getIcons().add(appLogo);

        // Load the logo image for the content area
        Image logoImage = new Image("file:src/images/ElectronX logo - Copy.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(350);
        logoImageView.setPreserveRatio(true);

        // Create labels for cashier section
        Label cashierLabel = new Label("Cashier");
        cashierLabel.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        Label nameLabel = new Label(opener.getname());
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        // Create VBox to hold the labels (Cashier + Name)
        VBox textVBox = new VBox(5, cashierLabel, nameLabel);
        textVBox.setAlignment(Pos.CENTER_LEFT);

        // Create HBox to horizontally align the image and text
        HBox topHBox = new HBox(340, logoImageView, textVBox);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");
        topHBox.setPrefWidth(Double.MAX_VALUE);

        // Create TableView and columns for Item class
        TableView<Item> table = new TableView<>();

        // Create columns for Item name, order amount, selling price, and total
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(235);

        TableColumn<Item, Integer> orderAmountColumn = new TableColumn<>("Order Amount");
        orderAmountColumn.setCellValueFactory(new PropertyValueFactory<>("orderAmount"));
        orderAmountColumn.setPrefWidth(235);

        TableColumn<Item, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
        sellingPriceColumn.setCellValueFactory(cellData -> {
            Item item = cellData.getValue();
            return new javafx.beans.property.SimpleDoubleProperty(item.getSellingPrice()).asObject();  // Get actual sellingPrice
        });
        sellingPriceColumn.setPrefWidth(235);

        TableColumn<Item, Double> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(cellData -> {
            Item item = cellData.getValue();
            double total = item.getSellingPrice() * item.getOrderAmount();  // Calculate total based on sellingPrice
            return new javafx.beans.property.SimpleDoubleProperty(total).asObject();
        });
        totalColumn.setPrefWidth(235);

        // Add columns to the table
        table.getColumns().addAll(nameColumn, orderAmountColumn, sellingPriceColumn, totalColumn);

        // Create a ComboBox to select items to add
        additems = new ComboBox<>();
        populateComboBox();

        // Create buttons for +, -, Add, Clear, Confirm, and Logout
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Button addButton = new Button("Add");
        Button clearButton = new Button("Clear");
        Button confirmButton = new Button("Confirm");
        Button logoutButton = new Button("Logout");

        // Create a TextField for the amount
        TextField amountTextField = new TextField("1");  // Start with 1 as the default value
        amountTextField.setStyle("-fx-font-size: 14px;");
        amountTextField.setPrefWidth(50);
        amountTextField.setAlignment(Pos.CENTER);  // Center the text inside the field
        amountTextField.setEditable(false);  // Prevent manual editing, just control via buttons

        // Event handlers for buttons
        plusButton.setOnAction(event -> {
            // Increase the amount in the text field by 1
            int currentAmount = Integer.parseInt(amountTextField.getText());
            amountTextField.setText(String.valueOf(currentAmount + 1));
        });

        minusButton.setOnAction(event -> {
            // Decrease the amount in the text field by 1, but not below 1
            int currentAmount = Integer.parseInt(amountTextField.getText());
            if (currentAmount > 1) {
                amountTextField.setText(String.valueOf(currentAmount - 1));
            }
        });

        addButton.setOnAction(event -> {
            String selectedItemName = additems.getValue();
            if (selectedItemName != null) {
                int orderAmount = Integer.parseInt(amountTextField.getText());  // Get quantity from TextField

                // Look for the selected item in the list of all items (loaded from the .dat file)
                for (Item item : getAllItems()) {
                    if (item.getName().equals(selectedItemName)) {
                        // Check if the item is already in the cart
                        boolean alreadyInCart = false;
                        for (Item cartItem : cashierController.getCart()) {
                            if (cartItem.getName().equals(item.getName())) {
                                // Update the order amount if the item is already in the cart
                                cartItem.setOrderAmount(cartItem.getOrderAmount() + orderAmount);
                                alreadyInCart = true;
                                break;
                            }
                        }

                        // If item is not in the cart, add it to the cart and table
                        if (!alreadyInCart) {
                            // Set the order amount for the selected item
                            item.setOrderAmount(orderAmount);
                            cashierController.addItemToCart(item, orderAmount);  // Add to cart
                            table.getItems().add(item);  // Add to table
                        }

                        break;  // Stop once the item is found and processed
                    }
                }

                // Refresh the table to show the updated list of items
                table.refresh();  
            }
        });

        clearButton.setOnAction(event -> {
            // Clear the entire table and cart
            cashierController.clearCart();
            table.getItems().clear();
        });

        confirmButton.setOnAction(event -> {
            // Generate the bill using the controller
            cashierController.generateBill();
        });

        logoutButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 14px;");
        logoutButton.setOnAction(event -> {
            
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.setLoginController(loginController);  // Pass the controller to the login view
            
            // Switch to the login view
            loginView.display(primaryStage);
        });

        // Create HBox to hold the combo box, text field, buttons, and the new logout button
        HBox comboAndButtonsHBox = new HBox(10, additems, minusButton, amountTextField, plusButton, addButton, clearButton, confirmButton, logoutButton);
        comboAndButtonsHBox.setAlignment(Pos.CENTER);
        comboAndButtonsHBox.setStyle("-fx-padding: 10px;");
        comboAndButtonsHBox.setSpacing(10);

        // Set the color for all buttons, text fields, and combo boxes
        additems.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");
        amountTextField.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");
        plusButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");
        minusButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");
        addButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");
        clearButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");
        confirmButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 14px;");

        // Create a VBox to hold the topHBox, TableView, and comboAndButtonsHBox
        VBox root = new VBox(20);
        root.getChildren().add(topHBox);  // Add topHBox at the top
        root.getChildren().add(table);    // Add the table below topHBox
        root.getChildren().add(comboAndButtonsHBox);  // Add the comboAndButtonsHBox below the table

        // Make sure the VBox stretches the entire height of the window
        root.setAlignment(Pos.TOP_LEFT);
        root.setStyle("-fx-background-color: #2b2e3a;");

        // Set the scene background color and dimensions
        Scene scene = new Scene(root, 960, 540);

        // Set the primaryStage properties
        primaryStage.setTitle("ElectronX - Cashier View");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void populateComboBox() {
        ArrayList<Item> allItems = getAllItems();
        ArrayList<String> itemNames = new ArrayList<>();

        // Add item names to list
        for (Item item : allItems) {
            itemNames.add(item.getName());
        }

        // Use Platform.runLater to update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            // Clear any existing items and add new item names
            additems.getItems().clear();
            additems.getItems().addAll(itemNames);
            additems.setPromptText("Select Item");
        });
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> allItems = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ItemList.dat"))) {
            while (true) {
                try {
                    Item item = (Item) in.readObject();
                    allItems.add(item);
                } catch (java.io.EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allItems;
    }

    @SuppressWarnings("unused")
	private void updateTable(TableView<Item> table) {
        table.getItems().clear();
        table.getItems().addAll(cashierController.getCart());
    }
}
