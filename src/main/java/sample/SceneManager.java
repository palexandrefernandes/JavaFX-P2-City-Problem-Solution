package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SceneManager {
    static private Stage rootStage;
    static private Map<String, URL> scenes = new HashMap<>();
    static private Scene activeScene;
    static private VBox mainScene;
    static private MainController mainController;

    public SceneManager(Stage stage){
        rootStage = stage;
        rootStage.setMaximized(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        try {
            mainScene = loader.load();
            mainController = loader.getController();
            rootStage.setScene(new Scene(mainScene));
            rootStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void addScene(String scenePath, String name){
        scenes.put(name, getClass().getResource(scenePath));
    }



    public static void showScene(String scenePath, Optional<Object> data){
        FXMLLoader loader = new FXMLLoader(scenes.get(scenePath));
        try {
            Parent scene = loader.load();
            ManageableController controller = loader.getController();
            controller.start(data);
            ScrollPane scrollPane = (ScrollPane)mainScene.getChildren().get(1);
            scrollPane.setContent(scene);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            Admin a = AdminSingleton.getCurrentAdmin();
            mainController.setLogged(a != null, a!= null && a.isSudo());


        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
