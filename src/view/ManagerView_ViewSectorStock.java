package view;

import Model.Users.Manager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ManagerView_ViewSectorStock {
    private Button backButton;
    private TextArea stockTextArea;  // TextArea for displaying stock info
    Manager opener;

    public ManagerView_ViewSectorStock() {
        // Initialize the back button with a fixed width
        this.backButton = new Button("Back");
        this.backButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 25px; -fx-padding: 10px 20px;");
        this.backButton.setPrefWidth(270);

        // Initialize the TextArea for stock information
        this.stockTextArea = new TextArea();
        this.stockTextArea.setEditable(false);  // Make it non-editable
        this.stockTextArea.setPrefWidth(500);  // Set width to 600
        this.stockTextArea.setPrefHeight(200);  // Set height to 200

        // Set the custom colors for the TextArea
        this.stockTextArea.setStyle(
            "-fx-font-size: 16px; " + 
            "-fx-padding: 10px; " +
            "-fx-background-color: #95dbea; " +  // Set background color
            "-fx-text-fill: #2b2e3a;"  // Set text color
        );

        this.opener = null;
    }

    public ManagerView_ViewSectorStock(Manager opener) {
        this();
        this.opener = opener;
    }

    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
        Image appLogo = new Image("file:src/images/App_Logo.png");  // Update the path as needed
        primaryStage.getIcons().add(appLogo);

        // Load the logo image for the content area
        Image logoImage = new Image("file:src/images/ElectronX logo - Copy.png");  // Update the path as needed
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(350);  // Set logo width as needed
        logoImageView.setPreserveRatio(true);  // Keep aspect ratio intact

        // Create labels for administrator section
        Label adminLabel = new Label("Manager");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        // Create HBox for header (logo and text)
        HBox topHBox = new HBox(350, logoImageView, adminLabel);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");
        topHBox.setPrefWidth(Double.MAX_VALUE);  // Stretch across full width

        // Create the VBox for the root layout
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 20px;");

        // Create VBox for the TextArea and Back Button
        VBox buttonGrid = new VBox(20);
        buttonGrid.setAlignment(Pos.CENTER);

        // Add TextArea for stock information and Back button to the VBox
        buttonGrid.getChildren().add(stockTextArea);  // Add the TextArea for stock information
        buttonGrid.getChildren().add(backButton);

        // Add the topHBox and buttonGrid to the root VBox
        root.getChildren().addAll(topHBox, buttonGrid);

        // Set the scene background color and dimensions (960x540 resolution)
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));
        primaryStage.setTitle("ElectronX - Manager View - Sector Stock");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();

        // Immediately populate the TextArea with stock information
        updateStockInfo();
        backButton.setOnAction(event -> {
            if (opener != null) {
                ManagerView managerView = new ManagerView(opener);
                managerView.display(primaryStage);
            } else {
                System.out.println("Manager object is null, cannot navigate back.");
            }
        });
    }

    // Getter methods for all buttons and TextArea
    public Button getBackButton() {
        return backButton;
    }

    public TextArea getStockTextArea() {
        return stockTextArea;
    }

    // Method to populate the TextArea with sector stock information
    public void updateStockInfo() {
        // Call the opener's method to get the sector stock information and set it to the TextArea
        String stockInfo = opener.viewSectorStock();  // Assuming this method returns stock info as a String
        stockTextArea.setText("Sector Stock Information:\n" + stockInfo);
    }
}
