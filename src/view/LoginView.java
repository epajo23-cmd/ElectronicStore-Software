package view;

import Controller.LoginController;
import Model.Users.Administrator;
import Model.Users.Cashier;
import Model.Users.Manager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginView {

    private Button loginButton;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label errorMessageLabel; // Added for displaying error messages
    private LoginController loginController;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;
    }
    public LoginView() {
        // Default constructor to create the LoginView instance without needing a controller
    }
    
    

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }
    
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    
    public void display(Stage primaryStage) {
        // Initialize components
        loginButton = new Button("Login");
        usernameField = new TextField();
        passwordField = new PasswordField();
        usernameLabel = new Label("Username:");
        passwordLabel = new Label("Password:");
        errorMessageLabel = new Label(""); // Initialize the error message label
        errorMessageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        // Load the application icon image for the title bar
        Image appLogo = new Image("file:src/images/App_Logo.png");
        primaryStage.getIcons().add(appLogo);

        // Load the logo image for the content area
        Image logoImage = new Image("file:src/images/ElectronX logo - Copy.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(400);
        logoImageView.setPreserveRatio(true);

        // Styling for labels and text fields
        usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
        usernameField.setStyle("-fx-background-color: #9de7f6; -fx-font-size: 15px;");
        passwordLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
        passwordField.setStyle("-fx-background-color: #9de7f6; -fx-font-size: 15px;");
        
        // Styling for the login button
        loginButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 20px;");
        loginButton.setScaleX(0.7);
        loginButton.setScaleY(0.7);

        // Create the grid pane for the labels and text fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Add the logo image to the top of the grid
        grid.add(logoImageView, 0, 0, 2, 1);

        // Add components to the grid
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        // Add the error message label
        grid.add(errorMessageLabel, 0, 4, 2, 1); // Error message is placed below the password fields

        // Create a StackPane to position the button below the logo
        StackPane buttonPane = new StackPane();
        buttonPane.getChildren().add(loginButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setStyle("-fx-padding: 20px 0px 0px 0px;");

        // Add the button pane below the text fields in the grid
        grid.add(buttonPane, 0, 3, 2, 1);

        // Set the background color of the pane
        grid.setStyle("-fx-background-color: #2b2e3a;");

        // Use a StackPane as the root to center the grid in the window
        StackPane root = new StackPane();
        root.getChildren().add(grid);

        // Create the scene with the grid and set its properties
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));
        primaryStage.setTitle("ElectronX");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add login button action
        loginButton.setOnAction(e -> loginController.handleLogin(usernameField.getText(), passwordField.getText(), primaryStage));
    }

    // Method to show login error
    public void showLoginError(String message) {
        errorMessageLabel.setText(message);
    }

    // Method to display Cashier view
    public void displayCashierView(Cashier cashier, Stage primaryStage) {
        // Switch to Cashier view
        CashierView cashierView = new CashierView(cashier);
        cashierView.display(primaryStage);
    }

    // Method to display Admin view
    public void displayAdminView(Administrator admin, Stage primaryStage) {
        // Switch to Admin view
        AdministratorView adminView = new AdministratorView(admin);
        adminView.display(primaryStage);
    }

    // Method to display Manager view
    public void displayManagerView(Manager manager, Stage primaryStage) {
        // Switch to Manager view
        ManagerView managerView = new ManagerView(manager);
        managerView.display(primaryStage);
    }
}
