package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import Model.Users.Administrator;
import Model.Users.Cashier;
import Model.Users.Manager;
import javafx.stage.Stage;
import view.LoginView;

public class LoginController {

    private LoginView loginView;
    private List<Administrator> adminList;
    private List<Manager> managerList;
    private List<Cashier> cashierList;

    // Constructor
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.adminList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.cashierList = new ArrayList<>();
        loadData();  // Load user data (admins, managers, cashiers)
    }

    // Load user data from saved files (or any persistence method you're using)
    private void loadData() {
        try {
            // Load administrators
            loadAdmins();
            // Load managers
            loadManagers();
            // Load cashiers
            loadCashiers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Load admins from the file
    private void loadAdmins() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_AdminList.dat"))) {
            while (true) {
                Administrator admin = (Administrator) in.readObject();
                adminList.add(admin);
            }
        } catch (IOException e) {
            // End of file reached or error in loading data
        }
    }

    // Load managers from the file
    private void loadManagers() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ManagerList.dat"))) {
            while (true) {
                Manager manager = (Manager) in.readObject();
                managerList.add(manager);
            }
        } catch (IOException e) {
            // End of file reached or error in loading data
        }
    }

    // Load cashiers from the file
    private void loadCashiers() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_CashierList.dat"))) {
            while (true) {
                Cashier cashier = (Cashier) in.readObject();
                cashierList.add(cashier);
            }
        } catch (IOException e) {
            // End of file reached or error in loading data
        }
    }

    // Authenticate the user by checking username and password
    public boolean authenticateUser(String username, String password, Stage primaryStage) {
        // Check if user is an Admin
        for (Administrator admin : adminList) {
            if (admin.getusername().equals(username) && admin.getpassword().equals(password)) {
                loginView.displayAdminView(admin, primaryStage);  // Pass primaryStage here
                return true;
            }
        }

        // Check if user is a Manager
        for (Manager manager : managerList) {
            if (manager.getusername().equals(username) && manager.getpassword().equals(password)) {
                loginView.displayManagerView(manager, primaryStage);  // Pass primaryStage here
                return true;
            }
        }

        // Check if user is a Cashier
        for (Cashier cashier : cashierList) {
            if (cashier.getusername().equals(username) && cashier.getpassword().equals(password)) {
                loginView.displayCashierView(cashier, primaryStage);  // Pass primaryStage here
                return true;
            }
        }

        return false;  // No matching user found
    }

    // Handle login attempt
    public void handleLogin(String username, String password, Stage primaryStage) {
        boolean authenticated = authenticateUser(username, password, primaryStage);  // Pass primaryStage here
        if (authenticated) {
            // Redirect to corresponding view (handled inside authenticateUser method)
            System.out.println("Login successful!");
        } else {
            // Show error message in the login view
            loginView.showLoginError("Invalid username or password. Please try again.");
        }
    }
}
