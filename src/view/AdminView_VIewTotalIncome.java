package view;

import Model.Users.Administrator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminView_VIewTotalIncome {
	private Button backButton;
	Administrator opener;
    

    public AdminView_VIewTotalIncome() {
        this.backButton = new Button("Back");
        this.backButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 25px; -fx-padding: 10px 20px;");
        this.backButton.setPrefWidth(270);
        this.opener=null;
    }
    public AdminView_VIewTotalIncome(Administrator opener) {
        this.backButton = new Button("Back");
        this.backButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 25px; -fx-padding: 10px 20px;");
        this.backButton.setPrefWidth(270);
        this.opener=opener;
    }


    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
    	Administrator admin = new Administrator();
    	
        Image appLogo = new Image("file:src/images/App_Logo.png");  // Update the path as needed
        primaryStage.getIcons().add(appLogo);

        // Load the logo image for the content area
        Image logoImage = new Image("file:src/images/ElectronX logo - Copy.png");  // Update the path as needed
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(350);  // Increased width of the image (adjust as needed)
        logoImageView.setPreserveRatio(true);  // Preserve aspect ratio

        // Create labels for administrator section
        Label incomeLabel = new Label("Income: " + admin.getTotalEarnings() + "$");
        incomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px; -fx-font-weight: bold;");
        
        Label adminLabel = new Label("Administrator");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        Label nameLabel = new Label("");  // You can change "John Doe" dynamically later
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

        VBox buttonGrid = new VBox(20);
        buttonGrid.setAlignment(Pos.CENTER);
        
        buttonGrid.getChildren().add(incomeLabel);
        buttonGrid.getChildren().add(backButton);

        // Add the buttonGrid to the root VBox
        root.getChildren().add(buttonGrid);  // Add buttons below the topHBox

        // Set the scene background color and dimensions (960x540 resolution)
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));

        // Set the primaryStage properties
        primaryStage.setTitle("ElectronX - Administrator View - Total earnings");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();
        
        backButton.setOnAction(event->{
        	if (opener != null) {
        	    AdministratorView managerView = new AdministratorView(opener);
        	    managerView.display(primaryStage);
        	} else {
        	    System.out.println("Manager object is null, cannot navigate back.");
        	}
        });
    }

    // Getter methods for all buttons
    public Button getBackButton() {
        return backButton;
    }
}

