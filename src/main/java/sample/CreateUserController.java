package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Admin;
import repository.AdminRepository;

import java.util.Optional;

public class CreateUserController implements ManageableController{
    @FXML
    CheckBox superAdmin;
    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    PasswordField password;

    AdminRepository adminRepository = new AdminRepository();


    @Override
    public void start(Optional<Object> data) {

    }

    public void onCreateClick(ActionEvent actionEvent) {
        Admin admin = new Admin(name.getText(), password.getText(), email.getText(), superAdmin.isSelected());
        adminRepository.save(admin);
        SceneManager.showScene("list-issues", null);
    }
}
