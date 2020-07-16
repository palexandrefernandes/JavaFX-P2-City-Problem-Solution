package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Optional;

public class Controller implements ManageableController{
    SceneManager sceneManager;

    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    Label erro;

    @Override
    public void start(Optional<Object> data) {

    }

    @FXML
    public void onLoginClick(){
        if(AdminSingleton.login(email.getText(), password.getText())){
            SceneManager.showScene("list-issues", null);
        }
        else{
            erro.setVisible(true);
        }
    }
}
