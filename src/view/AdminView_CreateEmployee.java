package view;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import Model.Users.AccessLevel;
import Model.Users.Administrator;
import Model.Users.Sector;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminView_CreateEmployee {
    private Button backButton;
    private Button createButton;
    private ComboBox<String> sectorComboBox;
    private TextField nameTF, phoneNrTF, emailTF, usernameTF, passwordTF, salaryTF;
    private DatePicker birthDatePicker;
    private RadioButton cashierRB, managerRB, adminRB;
    private ToggleGroup accessLevelGroup;
    private Administrator opener;

    public AdminView_CreateEmployee() {
        this.backButton = new Button("Back");
        this.createButton = new Button("Create");
        this.nameTF = new TextField();
        this.phoneNrTF = new TextField();
        this.emailTF = new TextField();
        this.usernameTF = new TextField();
        this.passwordTF = new TextField();
        this.salaryTF = new TextField();
        this.birthDatePicker = new DatePicker();
        this.sectorComboBox = new ComboBox<>();

        // Initialize RadioButtons for Access Level with user-friendly display names
        this.cashierRB = new RadioButton();
        cashierRB.setText("Cashier");
        cashierRB.setStyle("fx-text-fill: white;");
        
        this.managerRB = new RadioButton();
        managerRB.setText("Manager");
        managerRB.setStyle("fx-text-fill: white;");
        
        this.adminRB = new RadioButton();
        adminRB.setText("Admin");
        adminRB.setStyle("fx-text-fill: white;");
        
        // Group the radio buttons for Access Level selection
        this.accessLevelGroup = new ToggleGroup();
        this.cashierRB.setToggleGroup(accessLevelGroup);
        this.managerRB.setToggleGroup(accessLevelGroup);
        this.adminRB.setToggleGroup(accessLevelGroup);

        // Default selection - Cashier
        this.cashierRB.setSelected(true);

        // Style buttons and fields
        this.backButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 22px; -fx-padding: 8px 18px;");
        this.createButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 22px; -fx-padding: 8px 18px;");
    }

    public AdminView_CreateEmployee(Administrator opener) {
        this();
        this.opener = opener;
    }

    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
        Image appLogo = new Image("file:src/images/App_Logo.png");
        primaryStage.getIcons().add(appLogo);

        // Load the logo image for the content area
        Image logoImage = new Image("file:src/images/ElectronX logo - Copy.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(320);
        logoImageView.setPreserveRatio(true);

        // Create labels for the form
        Label adminLabel = new Label("Administrator");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 32px; -fx-font-weight: bold;");
        Label nameLabel = new Label("Name: ");
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label phoneNrLabel = new Label("Phone Number: ");
        phoneNrLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label emailLabel = new Label("Email: ");
        emailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label sectorLabel = new Label("Sector: ");
        sectorLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label birthdayLabel = new Label("Birth date: ");
        birthdayLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label salaryLabel = new Label("Salary: ");
        salaryLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label accesLvlLabel = new Label("Access Level: ");
        accesLvlLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        
        // Info Label
        Label infoLabel = new Label("Note: if you are creating a cashier you MUST choose a sector");
        infoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        // Create VBox to hold the labels (Administrator + Name)
        VBox textVBox = new VBox(5, adminLabel, nameLabel);
        textVBox.setAlignment(Pos.CENTER_LEFT);

        // Create HBox to horizontally align the image and text
        HBox topHBox = new HBox(250, logoImageView, textVBox);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");
        topHBox.setPrefWidth(Double.MAX_VALUE);

        // Create VBox to hold the topHBox
        VBox root = new VBox(5);
        root.getChildren().add(topHBox);

        root.setAlignment(Pos.TOP_LEFT);
        root.setStyle("-fx-background-color: #2b2e3a;");

        // Create GridPane to hold the form fields
        GridPane employeeInfo = new GridPane();
        employeeInfo.setHgap(8);
        employeeInfo.setVgap(8);
        employeeInfo.setAlignment(Pos.CENTER);

        // Create a standard width for all form fields
        double fieldWidth = 300;

        // Add fields and labels to the grid
        employeeInfo.add(sectorLabel, 0, 0);
        employeeInfo.add(sectorComboBox, 1, 0);
        sectorComboBox.setPrefWidth(fieldWidth);

        employeeInfo.add(nameLabel, 0, 1);
        employeeInfo.add(nameTF, 1, 1);
        nameTF.setPrefWidth(fieldWidth);

        employeeInfo.add(phoneNrLabel, 0, 2);
        employeeInfo.add(phoneNrTF, 1, 2);
        phoneNrTF.setPrefWidth(fieldWidth);

        employeeInfo.add(emailLabel, 0, 3);
        employeeInfo.add(emailTF, 1, 3);
        emailTF.setPrefWidth(fieldWidth);

        employeeInfo.add(birthdayLabel, 0, 4);
        employeeInfo.add(birthDatePicker, 1, 4);
        birthDatePicker.setPrefWidth(fieldWidth);

        employeeInfo.add(salaryLabel, 0, 5);
        employeeInfo.add(salaryTF, 1, 5);
        salaryTF.setPrefWidth(fieldWidth);

        employeeInfo.add(accesLvlLabel, 0, 6);

        // Create an HBox to hold the radio buttons and their corresponding text labels
        HBox accessLevelHBox = new HBox(10);
        accessLevelHBox.setAlignment(Pos.CENTER_LEFT);
        // Adding radio buttons and their respective labels
        Label cashierLbl = new Label("Cashier");
        cashierLbl.setStyle("-fx-text-fill: white;");
        Label managerLbl = new Label("Manager");
        managerLbl.setStyle("-fx-text-fill: white;");
        Label adminLbl = new Label("Admin");
        adminLbl.setStyle("-fx-text-fill: white;");
        accessLevelHBox.getChildren().addAll(cashierRB,cashierLbl, managerRB,managerLbl, adminRB,adminLbl);
        // Set spacing between the items and align correctly
        accessLevelHBox.setSpacing(10);
        employeeInfo.add(accessLevelHBox, 1, 6); 

        employeeInfo.add(usernameLabel, 0, 7);
        employeeInfo.add(usernameTF, 1, 7);
        usernameTF.setPrefWidth(fieldWidth);

        employeeInfo.add(passwordLabel, 0, 8);
        employeeInfo.add(passwordTF, 1, 8);
        passwordTF.setPrefWidth(fieldWidth);

        // Create HBox to place Create and Back buttons horizontally
        HBox buttonHBox = new HBox(10, createButton, backButton);
        buttonHBox.setAlignment(Pos.CENTER);

        // Create VBox to hold the info label, form, and buttons
        VBox nodeGrid = new VBox(15);
        nodeGrid.setAlignment(Pos.CENTER);
        nodeGrid.getChildren().add(infoLabel);
        nodeGrid.getChildren().add(employeeInfo);
        nodeGrid.getChildren().add(buttonHBox);

        root.getChildren().add(nodeGrid);

        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));

        primaryStage.setTitle("ElectronX - Administrator View - Create employee");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Populate the ComboBox with sector names
        populateSectorComboBox();

        // Set event handlers for the buttons
        createButton.setOnAction(e -> handleCreateButtonClick());
        backButton.setOnAction(event->{
        	if (opener != null) {
        	    AdministratorView managerView = new AdministratorView(opener);
        	    managerView.display(primaryStage);
        	} else {
        	    System.out.println("Manager object is null, cannot navigate back.");
        	}
        });
    }

    private void populateSectorComboBox() {
        ArrayList<Sector> sectors = loadSectorsFromFile();
        ArrayList<String> sectorNames = new ArrayList<>();

        for (Sector sector : sectors) {
            sectorNames.add(sector.getName());
        }

        sectorComboBox.getItems().addAll(sectorNames);
        sectorComboBox.setPromptText("Select Sector");
    }

    private void handleCreateButtonClick() {
        if (isFormValid()) {
            String selectedSectorName = sectorComboBox.getValue();
            Sector selectedSector = null;

            ArrayList<Sector> sectors = loadSectorsFromFile();
            for (Sector sector : sectors) {
                if (sector.getName().equals(selectedSectorName)) {
                    selectedSector = sector;
                    break;
                }
            }

            if (selectedSector != null) {
                String name = nameTF.getText();
                String phoneNumber = phoneNrTF.getText();
                String email = emailTF.getText();
                String username = usernameTF.getText();
                String password = passwordTF.getText();
                double salary = Double.parseDouble(salaryTF.getText());
                Date birthDate = convertLocalDateToDeprecatedDate(birthDatePicker.getValue());
                AccessLevel accessLevel = getAccessLevelFromRadioButton();

                opener.CreateEmployee(selectedSector, name, birthDate, email, phoneNumber, salary, accessLevel, username, password);
            }
        }
    }

    private Date convertLocalDateToDeprecatedDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        // Convert LocalDate to Instant, then to java.util.Date
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private boolean isFormValid() {
        // Perform validation logic here, e.g. check if all fields are filled in.
        return true;
    }


    private ArrayList<Sector> loadSectorsFromFile() {
        ArrayList<Sector> sectors = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_SectorList.dat"))) {
            while (true) {
                try {
                    Sector sector = (Sector) in.readObject();
                    sectors.add(sector);
                } catch (java.io.EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sectors;
    }

    private AccessLevel getAccessLevelFromRadioButton() {
        if (cashierRB.isSelected()) {
            return AccessLevel.CASHIER;
        } else if (managerRB.isSelected()) {
            return AccessLevel.MANAGER;
        } else {
            return AccessLevel.ADMIN;
        }
    }
}
