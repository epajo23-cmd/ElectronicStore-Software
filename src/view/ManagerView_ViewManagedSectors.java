package view;

import java.util.ArrayList;

import Model.Users.Manager;
import Model.Users.Sector;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ManagerView_ViewManagedSectors {
    private Button backButton;
    private ComboBox<String> sectorComboBox;  // ComboBox for sectors
    Manager opener;

    public ManagerView_ViewManagedSectors() {
        // Initialize the back button with a fixed width
        this.backButton = new Button("Back");
        this.backButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 25px; -fx-padding: 10px 20px;");
        this.backButton.setPrefWidth(270);

        // Initialize the ComboBox for sectors
        this.sectorComboBox = new ComboBox<>();
        this.sectorComboBox.setPromptText("Select Sector");
        this.sectorComboBox.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");
        this.sectorComboBox.setPrefWidth(270);  // Set the same width as the back button
        
        this.opener = null;
    }

    public ManagerView_ViewManagedSectors(Manager opener) {
        this();
        this.opener = opener;
    }

    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/images/App_Logo.png"));  // Update path as needed

        // Create the VBox for the root layout
        VBox root = new VBox(70);
        root.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 20px;");
        
        // Create HBox for header (logo and text)
        HBox topHBox = new HBox(350);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");
        topHBox.setPrefWidth(Double.MAX_VALUE);  // Stretch across full width

        // Add the logo to the header (this will match the previous style)
        Image logo = new Image("file:src/images/ElectronX logo - Copy.png");  // Use the logo path from the other class
        ImageView logoImageView = new ImageView(logo);
        logoImageView.setFitWidth(350);  // Match the logo width to 350px (as in the other class)
        logoImageView.setPreserveRatio(true);  // Keep the aspect ratio intact

        // Create the label for the manager title
        Label adminLabel = new Label("Manager");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");

        // Add logo and label to the topHBox
        topHBox.getChildren().addAll(logoImageView, adminLabel);

        // Create VBox for the ComboBox components and Back Button
        VBox buttonGrid = new VBox(20);
        buttonGrid.setAlignment(Pos.CENTER);

        // Add ComboBox to the VBox
        buttonGrid.getChildren().add(sectorComboBox);
        populateSectorComboBox();
        buttonGrid.getChildren().add(backButton);

        // Add the buttonGrid to the root VBox
        root.getChildren().add(topHBox);
        root.getChildren().add(buttonGrid);

        // Set the scene background color and dimensions (960x540 resolution)
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));
        primaryStage.setTitle("ElectronX - Manager View - Managed Sectors");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();
        backButton.setOnAction(event->{
        	if (opener != null) {
        	    ManagerView managerView = new ManagerView(opener);
        	    managerView.display(primaryStage);
        	} else {
        	    System.out.println("Manager object is null, cannot navigate back.");
        	}
        });
    }

    // Getter methods for all buttons and ComboBox
    public Button getBackButton() {
        return backButton;
    }

    public ComboBox<String> getSectorComboBox() {
        return sectorComboBox;
    }
    
    private void populateSectorComboBox() {
        ArrayList<Sector> sectors = opener.viewManagedSectors();  // Get the list of sectors
        ArrayList<String> sectorNames = new ArrayList<>();
        
        // Add sector names to list
        for (Sector sector : sectors) {
            sectorNames.add(sector.getName()); 
        }
        
        // Use Platform.runLater to update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            // Clear any existing items and add new sector names
            sectorComboBox.getItems().clear();
            sectorComboBox.getItems().addAll(sectorNames);
            sectorComboBox.setPromptText("Select sector");
        });
    }
}
