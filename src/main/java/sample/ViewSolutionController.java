package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.Issue;
import models.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

public class ViewSolutionController implements ManageableController{
    @FXML
    Label rua;
    @FXML
    Label freg;
    @FXML
    Label conc;
    @FXML
    Label dist;
    @FXML
    Label cp;

    @FXML
    TableView<Issue> table;
    @FXML
    TableColumn<Issue, String> id;
    @FXML
    TableColumn<Issue, String> title;
    @FXML
    TableColumn<Issue, String> date;
    @FXML
    TableColumn<Issue, String> desc;
    @FXML
    TextArea description;
    @FXML
    TextField name;
    @FXML
    TextField password;
    @FXML
    Button next;

    Solution solution;

    @Override
    public void start(Optional<Object> data) {
        if(data.isPresent()){
            solution = (Solution)data.get();
            next.setDisable(solution.getClosedAt() != null);
            getAddress(solution.getIssues().get(0));
            table.getItems().setAll(solution.getIssues());
            description.setText(solution.getComment());
            name.setText(solution.getName());
            password.setText(solution.getPassword());

            id.setCellValueFactory(new PropertyValueFactory<Issue, String>("id"));
            title.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
            desc.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
            date.setCellValueFactory(new PropertyValueFactory<Issue, String>("createdAt"));
            table.getItems().setAll(((Solution) data.get()).getIssues());
        }
    }

    public void getAddress(Issue issue){
        try {
            System.out.println(issue.getLatitude());
            System.out.println(issue.getLongitude());
            URL url = new URL("https://nominatim.openstreetmap.org/reverse?format=json&lat="+issue.getLatitude()+"&lon="+issue.getLongitude()+"&addressdetails=1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(content.toString(), Map.class);


            try{
                rua.setText(((Map<String,String>)map.get("address")).get("road"));
                freg.setText(((Map<String,String>)map.get("address")).get("suburb"));
                conc.setText(((Map<String,String>)map.get("address")).get("city"));
                cp.setText(((Map<String,String>)map.get("address")).get("postcode"));
                dist.setText(((Map<String,String>)map.get("address")).get("county"));
            } catch (NullPointerException exception) {
                System.out.println("The coordinates are janked!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarOnClick(ActionEvent actionEvent) {
        SceneManager.showScene("solution-add-issue", Optional.of(solution));
    }
}
