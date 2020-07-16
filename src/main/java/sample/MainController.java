package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import models.Admin;

public class MainController {
    @FXML
    MenuBar menu;
    @FXML
    MenuItem adminOp;

    public void setLogged(boolean state, boolean sudo){
        menu.setDisable(!state);
        adminOp.setDisable(!sudo);
    }

    public void onSolucoesAbertasClick(ActionEvent actionEvent) {
        SceneManager.showScene("list-solutions", null);
    }

    public void onSolucoesFinalizadasClick(ActionEvent actionEvent) {
        SceneManager.showScene("list-solutions-archived", null);
    }

    public void onProblemasArquivadosClick(ActionEvent actionEvent) {
        //SceneManager.showScene("");
    }

    public void onProblemaVerClick(ActionEvent actionEvent) {
        SceneManager.showScene("list-issues", null);
    }

    public void onSairClick(ActionEvent actionEvent) {
        AdminSingleton.logout();
        SceneManager.showScene("login", null);
    }

    public void onCriarClick(ActionEvent actionEvent) {
        SceneManager.showScene("create-user", null);
    }

    public void onContaClick(ActionEvent actionEvent) {
        //SceneManager.showScene("");
    }
}
