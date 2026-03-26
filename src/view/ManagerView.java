package view;

import Controller.LoginController;
import Controller.ManagerController;
import Model.Users.Manager;
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

public class ManagerView {
    private Button logoutButton;
    private Button viewManagedCashiersButton;
    private Button viewManagedSectorsButton;
    private Button viewSectorStockButton;
    private Button viewSectorTotalTurnoverButton;
    private Button viewCashiersTodayBillsButton;
    private Button viewCashiersAllTimeBillsButton;
    private Button viewSectorsTodayBillsButton;
    private Button viewSectorsAllTimeBillsButton;
    Manager opener;
    @SuppressWarnings("unused")
	private ManagerController managerController;  // Add reference to ManagerController

    public ManagerView(Manager opener) {
        this.logoutButton = new Button("LOG OUT");
        this.viewManagedCashiersButton = new Button("View Managed Cashiers");
        this.viewManagedSectorsButton = new Button("View Managed Sectors");
        this.viewSectorStockButton = new Button("View Sector Stock");
        this.viewSectorTotalTurnoverButton = new Button("View Sector Total Turnover");
        this.viewCashiersTodayBillsButton = new Button("View Cashiers Today Bills");
        this.viewCashiersAllTimeBillsButton = new Button("View Cashiers All Time Bills");
        this.viewSectorsTodayBillsButton = new Button("View Sectors Today Bills");
        this.viewSectorsAllTimeBillsButton = new Button("View Sectors All Time Bills");

        // Set button styles
        String buttonStyle = "-fx-background-color: #95dbea; -fx-text-fill: #2b2e3a; -fx-font-size: 15px; -fx-padding: 10px 20px;";
        this.logoutButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px 20px;");
        this.viewManagedCashiersButton.setStyle(buttonStyle);
        this.viewManagedSectorsButton.setStyle(buttonStyle);
        this.viewSectorStockButton.setStyle(buttonStyle);
        this.viewSectorTotalTurnoverButton.setStyle(buttonStyle);
        this.viewCashiersTodayBillsButton.setStyle(buttonStyle);
        this.viewCashiersAllTimeBillsButton.setStyle(buttonStyle);
        this.viewSectorsTodayBillsButton.setStyle(buttonStyle);
        this.viewSectorsAllTimeBillsButton.setStyle(buttonStyle);

        this.opener = opener;

        // Set all buttons to the same width
        setButtonWidth(270);

        // Set button actions (we'll use the controller here once it's set)
            }

    // Setter for ManagerController (so it doesn't have to be set in the constructor)
    public void setManagerController(ManagerController managerController) {
        this.managerController = managerController;
    }

    
    private void setButtonWidth(int width) {
        this.logoutButton.setPrefWidth(width);
        this.viewManagedCashiersButton.setPrefWidth(width);
        this.viewManagedSectorsButton.setPrefWidth(width);
        this.viewSectorStockButton.setPrefWidth(width);
        this.viewSectorTotalTurnoverButton.setPrefWidth(width);
        this.viewCashiersTodayBillsButton.setPrefWidth(width);
        this.viewCashiersAllTimeBillsButton.setPrefWidth(width);
        this.viewSectorsTodayBillsButton.setPrefWidth(width);
        this.viewSectorsAllTimeBillsButton.setPrefWidth(width);
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

        // Create labels for manager section
        Label adminLabel = new Label("Manager");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        Label nameLabel = new Label(opener.getname());  // Dynamically set the manager's name
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        // Create VBox to hold the labels (Manager + Name)
        VBox textVBox = new VBox(5, adminLabel, nameLabel);
        textVBox.setAlignment(Pos.CENTER_LEFT);

        // Create HBox to horizontally align the image and text
        HBox topHBox = new HBox(340, logoImageView, textVBox);
        topHBox.setAlignment(Pos.CENTER_LEFT);  // Align to the left side of the window
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");
        topHBox.setPrefWidth(Double.MAX_VALUE);  // Make the HBox stretch across the entire width of the window

        // Create VBox to hold the topHBox, without vertical centering
        VBox root = new VBox(60);
        root.getChildren().add(topHBox);  // Add topHBox at the top

        // Make sure the VBox stretches the entire height of the window
        root.setAlignment(Pos.TOP_LEFT);  // Align content to the top of the window
        root.setStyle("-fx-background-color: #2b2e3a;");

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(20);  // Horizontal gap between buttons
        buttonGrid.setVgap(20);  // Vertical gap between buttons
        buttonGrid.setAlignment(Pos.CENTER);

        buttonGrid.add(viewSectorTotalTurnoverButton, 0, 0);
        buttonGrid.add(viewSectorStockButton, 1, 0);
        buttonGrid.add(viewManagedCashiersButton, 0, 1);
        buttonGrid.add(viewManagedSectorsButton, 1, 1);

        // Add the buttonGrid to the root VBox
        root.getChildren().add(buttonGrid);

        // Create an HBox for the logoutButton to center it
        HBox logoutButtonBox = new HBox(logoutButton);
        logoutButtonBox.setAlignment(Pos.CENTER);  // Center the logout button
        root.getChildren().add(logoutButtonBox);  // Add logoutButton centered below the other buttons

        // Set the scene background color and dimensions (960x540 resolution)
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));

        // Set the primaryStage properties
        primaryStage.setTitle("ElectronX - Manager View");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
     // Set the action for logging out 
        logoutButton.setOnAction(event -> {
            
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.setLoginController(loginController);  // Pass the controller to the login view
            
            // Switch to the login view
            loginView.display(primaryStage);
        });
        
        
        
     // Set the action for viewing sector total turnover
        viewSectorTotalTurnoverButton.setOnAction(event -> {
            
            // Create an instance of the view and display it
            ManagerView_ViewSectorTurnover viewTurnover = new ManagerView_ViewSectorTurnover(opener);
            viewTurnover.display(primaryStage);  // Display the view on the primary stage
        });

        // Set the action for viewing sector stock
        viewSectorStockButton.setOnAction(event -> {
            
            // Create an instance of the view and display it
            ManagerView_ViewSectorStock viewStock = new ManagerView_ViewSectorStock(opener);
            viewStock.display(primaryStage);  // Display the view on the primary stage
        });

        // Set the action for viewing managed cashiers
        viewManagedCashiersButton.setOnAction(event -> {
            
            // Create an instance of the view and display it
            ManagerView_ViewManagedCashiers viewCashiers = new ManagerView_ViewManagedCashiers(opener);
            viewCashiers.display(primaryStage);  // Display the view on the primary stage
        });

        // Set the action for viewing managed sectors
        viewManagedSectorsButton.setOnAction(event -> {
            
            // Create an instance of the view and display it
            ManagerView_ViewManagedSectors viewSectors = new ManagerView_ViewManagedSectors(opener);
            viewSectors.display(primaryStage);  // Display the view on the primary stage
        });
    }

    // Getter methods for all buttons
    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getViewManagedCashiersButton() {
        return viewManagedCashiersButton;
    }

    public Button getViewManagedSectorsButton() {
        return viewManagedSectorsButton;
    }

    public Button getViewSectorStockButton() {
        return viewSectorStockButton;
    }

    public Button getViewSectorTotalTurnoverButton() {
        return viewSectorTotalTurnoverButton;
    }

    public Button getViewCashiersTodayBillsButton() {
        return viewCashiersTodayBillsButton;
    }

    public Button getViewCashiersAllTimeBillsButton() {
        return viewCashiersAllTimeBillsButton;
    }

    public Button getViewSectorsTodayBillsButton() {
        return viewSectorsTodayBillsButton;
    }

    public Button getViewSectorsAllTimeBillsButton() {
        return viewSectorsAllTimeBillsButton;
    }
}
