package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Issue;
import repository.IssueRepository;

import java.util.Optional;

public class IssueListController implements ManageableController {

    @FXML
    TableView<Issue> table;
    @FXML
    TableColumn<Issue, String> id;
    @FXML
    TableColumn<Issue, String> title;
    @FXML
    TableColumn<Issue, String> date;
    @FXML
    Button next;

    Issue selectedIssue;



    @Override
    public void start(Optional<Object> data) {
        next.setDisable(true);
        IssueRepository issueRepository = new IssueRepository();
        id.setCellValueFactory(new PropertyValueFactory<Issue, String>("id"));
        title.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
        date.setCellValueFactory(new PropertyValueFactory<Issue, String>("createdAt"));
        table.getItems().setAll(issueRepository.findAllPending());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            next.setDisable(newSelection == null);
            selectedIssue = newSelection;
        });
    }

    public void onButtonClick(ActionEvent event){
        if(selectedIssue != null){
            SceneManager.showScene("issue", Optional.of(selectedIssue));
        }
    }
}
