package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Issue;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneManager sceneManager = new SceneManager(primaryStage);

        sceneManager.addScene("/list-issues.fxml", "list-issues");
        sceneManager.addScene("/sample.fxml", "login");
        sceneManager.addScene("/issue.fxml", "issue");
        sceneManager.addScene("/create-solution.fxml", "create-solution");
        sceneManager.addScene("/sharing-link.fxml", "share-link");
        sceneManager.addScene("/view-solution.fxml", "show-solution");
        sceneManager.addScene("/list-solutions.fxml", "list-solutions");
        sceneManager.addScene("/solution-add-issue.fxml", "solution-add-issue");
        sceneManager.addScene("/list-solutions-archive.fxml", "list-solutions-archived");
        sceneManager.addScene("/create-user.fxml", "create-user");



        sceneManager.showScene("login", null);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
