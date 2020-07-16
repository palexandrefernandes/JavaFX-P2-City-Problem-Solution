package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Solution;

import java.util.Optional;

public class SharingLinkController implements ManageableController{
    @FXML
    TextField password;
    @FXML
    Button end;

    Solution solution;

    @Override
    public void start(Optional<Object> data) {
        if(data.isPresent()){
            solution = (Solution) data.get();
            password.setText("http://127.0.0.1:8080/solver/issue?id="+((Solution)data.get()).getId()+"&code="+((Solution)data.get()).getPassword());
        }
    }


    public void onEndClick(ActionEvent actionEvent) {
        SceneManager.showScene("show-solution", Optional.of(solution));
    }
}
