package view;


import Controller.LoginController;
import Model.Users.Administrator;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdministratorView {

    private Button logoutButton;
    private Button totalEarningsButton;
    private Button totalSpendingsButton;
    private Button totalTurnoverButton;
    private Button createEmployeeButton;
    private Button deleteEmployeeButton;
    private Button viewManagersButton;
    private Button viewCashiersButton;
    private Button viewAdminsButton;
    private Administrator opener;
    //private AdministratorController controller;

    public AdministratorView(Administrator administrator) {
        this.opener = administrator;
        //this.controller = controller;

        // Initialize buttons and other components
        this.logoutButton = new Button("LOG OUT");
        this.totalEarningsButton = new Button("View total earnings");
        this.totalSpendingsButton = new Button("View total spendings");
        this.totalTurnoverButton = new Button("View total turnover");
        this.createEmployeeButton = new Button("Create employee");
        this.deleteEmployeeButton = new Button("Delete employee");
        this.viewManagersButton = new Button("View all managers");
        this.viewCashiersButton = new Button("View all cashiers");
        this.viewAdminsButton = new Button("View all admins");

        // Set button styles
        String buttonStyle = "-fx-background-color: #95dbea; -fx-text-fill: #2b2e3a; -fx-font-size: 25px; -fx-padding: 10px 20px;";
        this.logoutButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 25px; -fx-padding: 10px 20px;");
        this.totalEarningsButton.setStyle(buttonStyle);
        this.totalSpendingsButton.setStyle(buttonStyle);
        this.totalTurnoverButton.setStyle(buttonStyle);
        this.createEmployeeButton.setStyle(buttonStyle);
        this.deleteEmployeeButton.setStyle(buttonStyle);
        this.viewManagersButton.setStyle(buttonStyle);
        this.viewCashiersButton.setStyle(buttonStyle);
        this.viewAdminsButton.setStyle(buttonStyle);

        // Set all buttons to the same width
        setButtonWidth(270);
    }

    private void setButtonWidth(int width) {
        this.logoutButton.setPrefWidth(width);
        this.totalEarningsButton.setPrefWidth(width);
        this.totalSpendingsButton.setPrefWidth(width);
        this.totalTurnoverButton.setPrefWidth(width);
        this.createEmployeeButton.setPrefWidth(width);
        this.deleteEmployeeButton.setPrefWidth(width);
        this.viewManagersButton.setPrefWidth(width);
        this.viewCashiersButton.setPrefWidth(width);
        this.viewAdminsButton.setPrefWidth(width);
    }

    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
        Image appLogo = new Image("file:src/images/App_Logo.png");  // Update the path as needed
        primaryStage.getIcons().add(appLogo);

        // Load the logo image for the content area
        Image logoImage = new Image("file:src/images/ElectronX logo - Copy.png");  // Update the path as needed
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(350);  // Increased width of the image (adjust as needed)
        logoImageView.setPreserveRatio(true);  // Preserve aspect ratio

        // Create labels for administrator section
        Label adminLabel = new Label("Administrator");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        Label nameLabel = new Label(opener.getname());  
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        // Create VBox to hold the labels (Administrator + Name)
        VBox textVBox = new VBox(5, adminLabel, nameLabel);
        textVBox.setAlignment(Pos.CENTER_LEFT);

        // Create HBox to horizontally align the image and text
        HBox topHBox = new HBox(300, logoImageView, textVBox);
        topHBox.setAlignment(Pos.CENTER_LEFT);  // Align to the left side of the window
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");  // Set background color and padding
        topHBox.setPrefWidth(Double.MAX_VALUE);  // Make the HBox stretch across the entire width of the window

        // Create VBox to hold the topHBox, without vertical centering
        VBox root = new VBox(60);
        root.getChildren().add(topHBox);  // Add topHBox at the top

        // Make sure the VBox stretches the entire height of the window
        root.setAlignment(Pos.TOP_LEFT);  // Align content to the top of the window
        root.setStyle("-fx-background-color: #2b2e3a;");  // Set the background color of the root

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(20);  // Horizontal gap between buttons
        buttonGrid.setVgap(20);  // Vertical gap between buttons
        buttonGrid.setAlignment(Pos.CENTER);

        buttonGrid.add(createEmployeeButton, 0, 0);
        buttonGrid.add(deleteEmployeeButton, 1, 0);
        buttonGrid.add(viewManagersButton, 2, 0);

        buttonGrid.add(viewCashiersButton, 0, 1);
        buttonGrid.add(viewAdminsButton, 1, 1);
        buttonGrid.add(totalTurnoverButton, 2, 1);

        buttonGrid.add(totalEarningsButton, 0, 2);
        buttonGrid.add(totalSpendingsButton, 1, 2);
        buttonGrid.add(logoutButton, 2, 2);

        // Add the buttonGrid to the root VBox
        root.getChildren().add(buttonGrid);  // Add buttons below the topHBox

        // Set the scene background color and dimensions (960x540 resolution)
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));

        // Set the primaryStage properties
        primaryStage.setTitle("ElectronX - Administrator View");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();

        // Button Actions
        initializeButtonActions(primaryStage);
    }

    private void initializeButtonActions(Stage primaryStage) {
        // Logout action
logoutButton.setOnAction(event -> {
            
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.setLoginController(loginController);  // Pass the controller to the login view
            
            // Switch to the login view
            loginView.display(primaryStage);
        });

        // View Total Earnings action
        this.totalEarningsButton.setOnAction(e -> {
            AdminView_VIewTotalIncome viewTotalEarnings = new AdminView_VIewTotalIncome(opener); // Pass opener
            viewTotalEarnings.display(primaryStage); // Pass primaryStage
        });

        // View Total Spendings action
        this.totalSpendingsButton.setOnAction(e -> {
            AdminView_ViewTotalSpendings viewTotalSpendings = new AdminView_ViewTotalSpendings(opener); // Pass opener
            viewTotalSpendings.display(primaryStage); // Pass primaryStage
        });

        // View Total Turnover action
        this.totalTurnoverButton.setOnAction(e -> {
            AdminView_ViewTotalTurnover viewTotalTurnover = new AdminView_ViewTotalTurnover(opener); // Pass opener
            viewTotalTurnover.display(primaryStage); // Pass primaryStage
        });

        // Create Employee action
        this.createEmployeeButton.setOnAction(e -> {
            AdminView_CreateEmployee createEmployeeView = new AdminView_CreateEmployee(opener); // Pass opener
            createEmployeeView.display(primaryStage); // Pass primaryStage
        });

        // Delete Employee action
        this.deleteEmployeeButton.setOnAction(e -> {
            AdminView_DeleteEmployee deleteEmployeeView = new AdminView_DeleteEmployee(opener); // Pass opener
            deleteEmployeeView.display(primaryStage); // Pass primaryStage
        });

        // View Managers action
        this.viewManagersButton.setOnAction(e -> {
            AdminVIew_ViewManagers viewManagersView = new AdminVIew_ViewManagers(opener); // Pass opener
            viewManagersView.display(primaryStage); // Pass primaryStage
        });

        // View Cashiers action
        this.viewCashiersButton.setOnAction(e -> {
            AdminView_ViewCashiers viewCashiersView = new AdminView_ViewCashiers(opener); // Pass opener
            viewCashiersView.display(primaryStage); // Pass primaryStage
        });

        // View Admins action
        this.viewAdminsButton.setOnAction(e -> {
            AdminView_ViewAdmins viewAdminsView = new AdminView_ViewAdmins(opener); // Pass opener
            viewAdminsView.display(primaryStage); // Pass primaryStage
        });
    }
}
