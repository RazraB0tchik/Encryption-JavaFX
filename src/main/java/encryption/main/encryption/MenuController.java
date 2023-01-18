package encryption.main.encryption;

import encryption.main.encryption.generatorKeys.GenerationSimpleNumbers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable{

    @FXML
    private ChoiceBox<String> keySize;

    @FXML
    private TextField openExponent;

    @FXML
    private void generikKey(){
        System.out.println(openExponent.getText());
        GenerationSimpleNumbers generationSimpleNumbers = new GenerationSimpleNumbers();
        generationSimpleNumbers.generateNumbers(Integer.valueOf(keySize.getSelectionModel().getSelectedItem()), openExponent.getText());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keySize.getItems().add("256");
        keySize.getItems().add("512");
        keySize.getItems().add("1024");
    }
}
