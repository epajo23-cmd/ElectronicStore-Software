package Controller;

import Model.Users.Administrator;
import javafx.stage.Stage;
import view.AdminVIew_ViewManagers;

import view.AdminView_DeleteEmployee;
import view.AdminView_ViewCashiers;

public class AdministratorController {

    @SuppressWarnings("unused")
	private Administrator admin;

    public AdministratorController(Administrator admin) {
        this.admin = admin;
    }

    
    // Method to delete an employee
    public void deleteEmployee(int employeeId, Stage primaryStage) {
        // Logic to delete an employee from the system
        // You would need to find the employee by their ID or other identifier
        // Here you could remove the employee from a list or database

        // Show the delete employee view
        AdminView_DeleteEmployee deleteEmployeeView = new AdminView_DeleteEmployee();
        deleteEmployeeView.display(primaryStage);
    }

    // Method to view cashiers
    public void viewCashiers(Stage primaryStage) {
        // Logic to retrieve and display a list of cashiers
        // Fetch cashiers from the system or database

        // Show the view cashiers screen
        AdminView_ViewCashiers viewCashiersView = new AdminView_ViewCashiers();
        viewCashiersView.display(primaryStage);
    }

    // Method to view managers
    public void viewManagers(Stage primaryStage) {
        // Logic to retrieve and display a list of managers
        // Fetch managers from the system or database

        // Show the view managers screen
        AdminVIew_ViewManagers viewManagersView = new AdminVIew_ViewManagers();
        viewManagersView.display(primaryStage);
    }

    // Method to generate reports (income, turnover, etc.)
    public void generateReports() {
        // Logic for generating reports (income, turnover, etc.)
        // You might call appropriate methods in the `Administrator` class
    }
}
