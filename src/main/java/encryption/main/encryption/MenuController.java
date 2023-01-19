package encryption.main.encryption;

import encryption.main.encryption.entity.SelectedParams;
import encryption.main.encryption.mainEncryption.MainEncryptionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class MenuController extends SelectedParams implements Initializable{

    @FXML
    private ChoiceBox<String> keySize;

    @FXML
    private ChoiceBox<Integer> openExponent;

    @FXML
    private TextArea textPool;

    @FXML
    private TextField fileSave;

    @FXML
    private TextField decryptionFile;


    MainEncryptionClass mainEncryptionClass = new MainEncryptionClass();
    @FXML
    private void generikKey(){
//        SelectedParams selectedParamsRSA = new SelectedParams(Integer.valueOf(keySize.getSelectionModel().getSelectedItem()), openExponent.getSelectionModel().getSelectedItem(), textPool.getText());
        mainEncryptionClass.generateKeys(Integer.valueOf(keySize.getSelectionModel().getSelectedItem()), openExponent.getSelectionModel().getSelectedItem(), fileSave.getText());
    }

    @FXML
    private void encryptionText(){
        mainEncryptionClass.encryptionText(textPool.getText(), fileSave.getText());
    }

    @FXML
    private void decryptionText(){
        mainEncryptionClass.decryptionText(fileSave.getText(), decryptionFile.getText());
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keySize.getItems().add("256");
        keySize.getItems().add("512");
        keySize.getItems().add("1024");

        openExponent.getItems().add(3);
        openExponent.getItems().add(5);
        openExponent.getItems().add(17);
        openExponent.getItems().add(257);
        openExponent.getItems().add(65537);
    }
}
