package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;
import Controller.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the LoginView object and pass the controller to it
        LoginView loginView = new LoginView();
        
        // Create the LoginController and pass loginView to it
        LoginController loginController = new LoginController(loginView);

        // Now set the controller to the LoginView
        loginView.setLoginController(loginController);

        // Display the LoginView
        loginView.display(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }
}
