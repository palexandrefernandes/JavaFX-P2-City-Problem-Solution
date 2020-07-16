package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import models.Issue;
import repository.IssueRepository;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

public class IssueController implements ManageableController{
    Issue issue;

    IssueRepository ir = new IssueRepository();

    @FXML
    TextField title;
    @FXML
    TextArea description;
    @FXML
    ImageView image;
    @FXML
    HBox imagePane;
    @FXML
    Button edit;
    @FXML
    Button next;
    @FXML
    Button cancel;
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

    boolean editing = false;

    @Override
    public void start(Optional<Object> data) {
        if(data.isPresent()){
            issue = (Issue)data.get();
            getAddress();
            String temp = issue.getImage_b64();
            String unwantedText = "data:image/png;base64,";
            temp = temp.replace(unwantedText, "");
            byte[] in = Base64.getDecoder().decode(temp);
            ByteArrayInputStream bis = new ByteArrayInputStream(in);
            image.setImage(new Image(bis));
            title.setText(issue.getTitle());
            description.setText(issue.getDescription());

        }
    }

    @FXML
    public void onEditClick(Event event){
        if(editing){
            title.setText(issue.getTitle());
            description.setText(issue.getDescription());
        }
        changeFunction();
    }

    public void onNextClick(){
        if(editing){
            // save info data
            issue.setTitle(title.getText());
            issue.setDescription(description.getText());
            ir.save(issue);
            changeFunction();
        }
        else {
            // go to next screen
            SceneManager.showScene("create-solution", Optional.of(issue));
            changeFunction();
        }
    }

    public void onCancelClick(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Arquivar problema? (Esta opção não pode ser revertida)", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES){
            ir.deny(issue);
            SceneManager.showScene("list-issues", null);
        }
    }


    public void changeFunction(){
        editing = !editing;
        description.setDisable(!editing);
        title.setDisable(!editing);
        cancel.setVisible(!editing);
        if(editing){
            edit.setText("Cancelar");
        }
        else {
            edit.setText("Editar");
        }
    }

    public void getAddress(){
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

            rua.setText(((Map<String,String>)map.get("address")).get("road"));
            freg.setText(((Map<String,String>)map.get("address")).get("suburb"));
            conc.setText(((Map<String,String>)map.get("address")).get("city"));
            cp.setText(((Map<String,String>)map.get("address")).get("postcode"));
            dist.setText(((Map<String,String>)map.get("address")).get("county"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
