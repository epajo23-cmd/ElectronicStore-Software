package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Users.Administrator;
import Model.Users.*;
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

public class AdminView_DeleteEmployee {
    private Button backButton;
    private Button deleteButton;  // Change from create to delete button
    private ComboBox<String> employeeComboBox;  // ComboBox to display employees' names
	Administrator opener;

    public AdminView_DeleteEmployee() {
        this.backButton = new Button("Back");
        this.deleteButton = new Button("Delete");  // Change from create to delete button
        this.employeeComboBox = new ComboBox<>();
        this.opener=null;
    }
    
    public AdminView_DeleteEmployee(Administrator opener) {
        this.backButton = new Button("Back");
        this.deleteButton = new Button("Delete");  // Change from create to delete button
        this.employeeComboBox = new ComboBox<>();
        this.opener=opener;
    }

    public void display(Stage primaryStage) {
        // Load the application icon image for the title bar
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/images/App_Logo.png"));

        // Load the logo image for the content area
        Image appLogo = new Image("file:src/images/ElectronX logo - Copy.png");  // Adjust logo path as needed
        ImageView logoImageView = new ImageView(appLogo);
        logoImageView.setFitWidth(200);  // Adjust width as needed
        logoImageView.setPreserveRatio(true);  // Preserve aspect ratio

        // Create labels for the form
        Label adminLabel = new Label("Administrator");
        adminLabel.setStyle("-fx-text-fill: white; -fx-font-size: 32px; -fx-font-weight: bold;");
        Label employeeLabel = new Label("Select Employee to Delete:");
        employeeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;"); // Larger label

        // Style for Back button
        this.backButton.setStyle("-fx-background-color: #dd5f5f; -fx-text-fill: white; -fx-font-size: 22px; -fx-padding: 8px 18px;");
        this.backButton.setPrefWidth(230);  // Set identical width for both buttons
        
        // Style for Delete button (Lime Green)
        this.deleteButton.setStyle("-fx-background-color: #9de7f6; -fx-text-fill: #2b2e3a; -fx-font-size: 22px; -fx-padding: 8px 18px;");
        this.deleteButton.setPrefWidth(230);  // Set identical width for both buttons

        // Create VBox to hold the top section (logo + title)
        VBox textVBox = new VBox(5, adminLabel);
        textVBox.setAlignment(Pos.CENTER_LEFT);

        // Create HBox for top layout with logo and title
        HBox topHBox = new HBox(450, logoImageView, textVBox);
        topHBox.setAlignment(Pos.CENTER_LEFT);  // Align to the left side of the window
        topHBox.setStyle("-fx-background-color: #2b2e3a; -fx-padding: 10px 20px;");

        // Create VBox for the form layout
        VBox root = new VBox(50);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #2b2e3a;");

        // Create VBox for employee selection layout
        VBox formVBox = new VBox(15);
        formVBox.setAlignment(Pos.CENTER);
        
        // Adjust the employee label and comboBox size
        employeeComboBox.setPrefWidth(400);  // Larger combo box
        employeeComboBox.setStyle("-fx-font-size: 18px; -fx-padding: 10px; -fx-pref-height: 40px;"); // Thicker ComboBox
        
        // Place the label above the ComboBox
        formVBox.getChildren().addAll(employeeLabel, employeeComboBox);
        
        root.getChildren().add(topHBox);  // Add top section to root
        root.getChildren().add(formVBox);  // Add employee selection layout to root

        // Create HBox for the buttons
        HBox buttonHBox = new HBox(10, deleteButton, backButton);  // Buttons side by side
        buttonHBox.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonHBox);  // Add buttons to root

        // Create and set the scene
        Scene scene = new Scene(root, 960, 540, Color.web("#2b2e3a"));
        primaryStage.setTitle("ElectronX - Administrator View - Delete Employee");
        primaryStage.setResizable(false);  // Lock the window size
        primaryStage.setScene(scene);
        primaryStage.show();

        // Populate ComboBox with employees
        populateEmployeeComboBox();
        
        backButton.setOnAction(event->{
        	if (opener != null) {
        	    AdministratorView managerView = new AdministratorView(opener);
        	    managerView.display(primaryStage);
        	} else {
        	    System.out.println("Manager object is null, cannot navigate back.");
        	}
        });
        
        deleteButton.setOnAction(event->{
        	ArrayList<Employee> employees = new ArrayList<Employee>();
        	employees=viewAllEmployees();
        	String compare;
        	
        	for(int i=0;i< employees.size();i++) {
        		compare = employees.get(i).getname() +" - "+ employees.get(i).getaccessLevel();
        		if(compare.equals(employeeComboBox.getValue())) {
        			employees.remove(i);
        			break;
        		}
        	}
        	
        	updateAllEmployees(employees);
        	populateEmployeeComboBox();
        	
        	
        });
    }

    // Method to populate ComboBox with employee names
    private void populateEmployeeComboBox() {
        ArrayList<Employee> employees = viewAllEmployees();  // Get the list of employees
        ArrayList<String> employeeNames = new ArrayList<>();
        
        // Add employee names to list (or another unique identifier, such as employee ID)
        for (Employee emp : employees) {
            employeeNames.add(emp.getname() +" - "+ emp.getaccessLevel());  // Use `getName` or another method to get the employee name
            System.out.println(emp.getname());
        }
        
        // Use Platform.runLater to update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            // Clear any existing items and add new employee names
            employeeComboBox.getItems().clear();
            employeeComboBox.getItems().addAll(employeeNames);
            employeeComboBox.setPromptText("Select Employee to Delete");
        });
    }

    // Method to load employees from files
    public ArrayList<Employee> viewAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_CashierList.dat"))) {
            while (true) {
                try {
                    Employee emp = (Employee) in.readObject();
                    employees.add(emp);
                } catch (java.io.EOFException e) {
                    break;  // End of file reached
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ManagerList.dat"))) {
            while (true) {
                try {
                    Employee emp = (Employee) in.readObject();
                    employees.add(emp);
                } catch (java.io.EOFException e) {
                    break;  // End of file reached
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_AdminList.dat"))) {
            while (true) {
                try {
                    Employee emp = (Employee) in.readObject();
                    employees.add(emp);
                } catch (java.io.EOFException e) {
                    break;  // End of file reached
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return employees;
    }

    // Method to update employee data in all files (Cashiers, Managers, and Administrators)
    public void updateAllEmployees(ArrayList<Employee> employees) {
        ArrayList<Employee> cashiers = new ArrayList<>();
        ArrayList<Employee> administrators = new ArrayList<>();
        ArrayList<Employee> managers = new ArrayList<>();

        // Separate employees by type
        for (Employee emp : employees) {
            if (emp instanceof Cashier) {
                cashiers.add(emp);
            } else if (emp instanceof Administrator) {
                administrators.add(emp);
            } else if (emp instanceof Manager) {
                managers.add(emp);
            }
        }

        // Write all Cashiers to file
        try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_CashierList.dat");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            // Write the full list of cashiers
            for (Employee emp : cashiers) {
                out.writeObject(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all Administrators to file
        try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_AdminList.dat");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            // Write the full list of administrators
            for (Employee emp : administrators) {
                out.writeObject(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all Managers to file
        try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_ManagerList.dat");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            // Write the full list of managers
            for (Employee emp : managers) {
                out.writeObject(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Getter methods for all buttons and ComboBox
    public Button getBackButton() {
        return backButton;
    }

    public Button getDeleteButton() {
        return deleteButton;  // Getter for Delete button
    }

    public ComboBox<String> getEmployeeComboBox() {
        return employeeComboBox;  // Getter for employee ComboBox
    }
}
