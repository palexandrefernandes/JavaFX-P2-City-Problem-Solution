package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Issue;
import models.Solution;
import repository.IssueRepository;
import repository.SolutionRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class CreateSolutionController implements ManageableController{
    SolutionRepository sr = new SolutionRepository();

    private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @FXML
    TextField name;

    @FXML
    TextArea descriptionText;

    @FXML
    RadioButton u1;
    @FXML
    RadioButton u2;
    @FXML
    RadioButton u3;
    @FXML
    RadioButton u4;
    @FXML
    RadioButton u5;

    @FXML
    TableView<Issue> table;
    @FXML
    TableColumn<Issue, String> title;
    @FXML
    TableColumn<Issue, String> description;

    @FXML
    TableView<Issue> tableAdded;
    @FXML
    TableColumn<Issue, String> titleAdded;
    @FXML
    TableColumn<Issue, String> descriptionAdded;

    ToggleGroup radioGroup = new ToggleGroup();

    Issue selectedIssue;

    Issue baseIssue;

    @Override
    public void start(Optional<Object> data) {
        if(data.isPresent()) {
            baseIssue = (Issue)data.get();
            u1.setToggleGroup(radioGroup);
            u2.setToggleGroup(radioGroup);
            u3.setToggleGroup(radioGroup);
            u4.setToggleGroup(radioGroup);
            u5.setToggleGroup(radioGroup);

            IssueRepository issueRepository = new IssueRepository();
            title.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
            description.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));

            titleAdded.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
            descriptionAdded.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));

            List<Issue> issues = issueRepository.findAllPending();
            issues.remove(baseIssue);

            table.getItems().setAll(issues);
            tableAdded.getItems().add(baseIssue);

            table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                selectedIssue = newSelection;
            });

            tableAdded.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                selectedIssue = newSelection;
            });
        }
    }

    @FXML
    public void onAddClick(){
        if(selectedIssue != null && table.getItems().contains(selectedIssue)){
            tableAdded.getItems().add(selectedIssue);
            table.getItems().remove(selectedIssue);
        }
    }

    @FXML
    public void onRemoveClick(){
        if(selectedIssue != null && selectedIssue != baseIssue && tableAdded.getItems().contains(selectedIssue)){
            table.getItems().add(selectedIssue);
            tableAdded.getItems().remove(selectedIssue);
        }
    }

    @FXML
    public void onCriarClick(){
        //Verificar se campos estão preenchidos
        if(true){
            String password = randomAlphaNumeric(5);
            Solution solution = new Solution(AdminSingleton.getCurrentAdmin(), name.getText(), descriptionText.getText(), password, Integer.parseInt(((RadioButton)radioGroup.getSelectedToggle()).getText()));
            tableAdded.getItems().forEach(item -> {
                solution.addIssue(item);
            });
            if(sr.save(solution)){
                //go to view password
                SceneManager.showScene("share-link", Optional.of(solution));
            }
        }
    }

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
