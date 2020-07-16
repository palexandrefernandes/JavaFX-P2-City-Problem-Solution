package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Issue;
import models.Solution;
import repository.IssueRepository;
import repository.SolutionRepository;

import java.util.Optional;

public class SolutionAddIssueController implements ManageableController{

    @FXML
    TableView<Issue> table1;
    @FXML
    TableColumn<Issue, String> id1;
    @FXML
    TableColumn<Issue, String> date1;
    @FXML
    TableColumn<Issue, String> title1;
    @FXML
    TableColumn<Issue, String> description1;
    @FXML
    TableView<Issue> table2;
    @FXML
    TableColumn<Issue, String> id2;
    @FXML
    TableColumn<Issue, String> date2;
    @FXML
    TableColumn<Issue, String> title2;
    @FXML
    TableColumn<Issue, String> description2;

    Solution solution;
    SolutionRepository solutionRepository = new SolutionRepository();
    IssueRepository issueRepository = new IssueRepository();
    Issue selectedIssue;

    @Override
    public void start(Optional<Object> data) {
        if(data.isPresent()) {
            solution = (Solution) data.get();
            id1.setCellValueFactory(new PropertyValueFactory<Issue, String>("id"));
            description1.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
            date1.setCellValueFactory(new PropertyValueFactory<Issue, String>("createdAt"));
            title1.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
            table1.getItems().setAll(issueRepository.findAllPending());

            id2.setCellValueFactory(new PropertyValueFactory<Issue, String>("id"));
            description2.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
            date2.setCellValueFactory(new PropertyValueFactory<Issue, String>("createdAt"));
            title2.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                selectedIssue = newSelection;
            });

            table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                selectedIssue = newSelection;
            });

        }
    }

    public void onAddClick(ActionEvent actionEvent) {
        if(selectedIssue != null && table1.getItems().contains(selectedIssue)) {
            table2.getItems().add(selectedIssue);
            table1.getItems().remove(selectedIssue);
        }
    }

    public void onRemoveClick(ActionEvent actionEvent) {
        if(selectedIssue != null && table2.getItems().contains(selectedIssue)) {
            table1.getItems().add(selectedIssue);
            table2.getItems().remove(selectedIssue);
        }
    }

    public void onAddIssuesClick(ActionEvent actionEvent) {
        solution.getIssues().addAll(table2.getItems());

        table2.getItems().forEach(issue -> {
            issue.setSolution(solution);
        });

        solutionRepository.save(solution);
        SceneManager.showScene("show-solution", Optional.of(solution));
    }
}
