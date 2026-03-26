package Controller;

import java.util.ArrayList;

import Model.Product.Item;

import Model.Users.Manager;

import javafx.stage.Stage;
import view.ManagerView_ViewManagedCashiers;
import view.ManagerView_ViewManagedSectors;
import view.ManagerView_ViewSectorStock;
import view.ManagerView_ViewSectorTurnover;

public class ManagerController {

    private Manager manager;

    // Constructor to initialize the manager controller with the current manager instance
    public ManagerController(Manager manager) {
        this.manager = manager;
    }

    // Method to view the sector's total turnover
    public void viewSectorTotalTurnover(Stage primaryStage) {
        // Create a view to display the total turnover for sectors
        ManagerView_ViewSectorTurnover viewTurnover = new ManagerView_ViewSectorTurnover(manager);
        viewTurnover.display(primaryStage);  // Pass the primary stage for displaying the view
    }

    // Method to view the stock level in a sector
    public void viewSectorStock(Stage primaryStage) {
        // Retrieve list of items in the manager's managed sector
        ArrayList<Item> sectorItems = new ArrayList<Item>();

        for (int i = 0; i < manager.viewManagedSectors().size(); i++) {
            for (int j = 0; j < manager.viewManagedSectors().get(i).viewStoredItems().size(); j++) {
                sectorItems.add(manager.viewManagedSectors().get(i).viewStoredItems().get(j));
            }
        }

        // Create a view to display the stock level of items in the sector
        ManagerView_ViewSectorStock viewStock = new ManagerView_ViewSectorStock(manager);
        viewStock.display(primaryStage);  // Pass the primary stage for displaying the view
    }

    // Method to view all cashiers managed by this manager
    public void viewManagedCashiers(Stage primaryStage) {
        // Create a view to display the cashiers managed by this manager
        ManagerView_ViewManagedCashiers viewCashiers = new ManagerView_ViewManagedCashiers(manager);
        viewCashiers.display(primaryStage);  // Pass the primary stage for displaying the view
    }

    // Method to view the sectors managed by the manager
    public void viewManagedSectors(Stage primaryStage) {
        // Create a view to display the sectors managed by this manager
        ManagerView_ViewManagedSectors viewSectors = new ManagerView_ViewManagedSectors(manager);
        viewSectors.display(primaryStage);  // Pass the primary stage for displaying the view
    }
}
