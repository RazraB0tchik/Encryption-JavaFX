package encryption.main.encryption;

import encryption.main.encryption.generatorKeys.GenerationSimpleNumbers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable{

    @FXML
    private ChoiceBox<String> keySize;

    @FXML
    private void generikKey(){
        GenerationSimpleNumbers generationSimpleNumbers = new GenerationSimpleNumbers();
        generationSimpleNumbers.generateNumbers(keySize.getSelectionModel().getSelectedItem());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keySize.getItems().add("256");
        keySize.getItems().add("512");
        keySize.getItems().add("1024");
    }
}
