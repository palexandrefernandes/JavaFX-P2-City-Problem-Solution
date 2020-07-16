package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Solution;
import repository.SolutionRepository;

import java.util.List;
import java.util.Optional;

public class ListSolutionsController implements ManageableController{
    @FXML
    TableView<Solution> table;
    @FXML
    TableColumn<Solution, String> id;
    @FXML
    TableColumn<Solution, String> name;
    @FXML
    TableColumn<Solution, String> date;
    @FXML
    Button next;

    Solution selectedSolution;
    SolutionRepository solutionRepository = new SolutionRepository();

    @Override
    public void start(Optional<Object> data) {
        List<Solution> solutions = solutionRepository.findAllOpen();
        next.setDisable(true);
        id.setCellValueFactory(new PropertyValueFactory<Solution, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Solution, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Solution, String>("createdAt"));
        System.out.println(solutions.size());
        if(solutions != null && solutions.size() > 0) {
            table.getItems().setAll(solutions);

            table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                next.setDisable(newSelection == null);
                selectedSolution = newSelection;
            });
        }

    }

    public void onVerClick(ActionEvent actionEvent) {
        SceneManager.showScene("show-solution", Optional.of(selectedSolution));
    }
}
