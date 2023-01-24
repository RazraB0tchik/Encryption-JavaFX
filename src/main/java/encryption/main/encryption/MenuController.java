package encryption.main.encryption;

import encryption.main.encryption.mainEncryption.MainEncryptionClass;
import encryption.main.encryption.utils.keyValidation.CheckValidation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class MenuController implements Initializable{

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

    @FXML
    private TextField encoderFile;

    @FXML
    private TextField openkey;

    @FXML
    private TextField privateKey;


    CheckValidation validation = new CheckValidation();
    FileChooser chooser = new FileChooser();
    MainEncryptionClass mainEncryptionClass = new MainEncryptionClass();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    @FXML
    private void generikKey(){
        if(!this.checkSaveFile()){
            alertWarn.setTitle("Directory not specified");
            alertWarn.setContentText("Specify the directory to save");
            alertWarn.showAndWait();
        }
        else {
            mainEncryptionClass.generateKeys(Integer.valueOf(keySize.getSelectionModel().getSelectedItem()), openExponent.getSelectionModel().getSelectedItem(), fileSave.getText());
        }
    }

    @FXML
    private void selectDirectoryForWork(){
        Stage stage = (Stage) textPool.getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose directory for work");
        File selectDirectory = directoryChooser.showDialog(stage);
        if(selectDirectory!=null){
            fileSave.setText(String.valueOf(selectDirectory)+"/");
        }
    }



    @FXML
    private void checkFileOpen(){
        this.selectFile(openkey, "Choose open key");
    }

    @FXML
    private void checkFilePrivate(){
        this.selectFile(privateKey, "Choose private key");
    }

    @FXML
    private void selectFileForEncrypt(){
        this.selectFile(encoderFile, "Choose file for encrypt");
    }

    @FXML
    private void selectFileForDecrypt(){
        this.selectFile(decryptionFile, "Choose file for decrypt");
    }

    @FXML
    private void encryptionText(){
        if(!this.checkSaveFile()){
            alertWarn.setTitle("Directory not specified");
            alertWarn.setContentText("Specify the directory to save");
            alertWarn.showAndWait();
        }
        if(textPool.getText().isEmpty() && encoderFile.getText().isEmpty()){
            alertWarn.setTitle("File or text not found");
            alertWarn.setTitle("Enter file or text for encryption");
            alertWarn.showAndWait();
        }
        else {
            if (openkey.getText().isEmpty() && privateKey.getText().isEmpty()) {
                mainEncryptionClass.encryptionText(textPool.getText(), fileSave.getText(), encoderFile.getText(), openkey.getText(), false);
            } else {
                if (this.checkValidation(openkey, privateKey)) {
                    mainEncryptionClass.encryptionText(textPool.getText(), fileSave.getText(), encoderFile.getText(), openkey.getText(), true);
                }
            }
        }
    }

    @FXML
    private void decryptionText(){
        if(!this.checkSaveFile()){
            alertWarn.setTitle("Directory not specified");
            alertWarn.setContentText("Specify the directory to save");
            alertWarn.showAndWait();
        }
        if(decryptionFile.getText().isEmpty()){
            alertWarn.setTitle("File not specified");
            alertWarn.setContentText("File for decryption not specified");
            alertWarn.showAndWait();
        }
        else {
            if (openkey.getText().isEmpty() && privateKey.getText().isEmpty()) {
                mainEncryptionClass.decryptionText(decryptionFile.getText(), fileSave.getText(), privateKey.getText(), false);
            }
            else{
                if (this.checkValidation(openkey, privateKey)) {
                    mainEncryptionClass.decryptionText(decryptionFile.getText(), fileSave.getText(), privateKey.getText(), true);
                }
            }
        }
    }


    private void selectFile(TextField inputElement, String titleText){
        Stage stage = (Stage) textPool.getScene().getWindow();
        chooser.setTitle(titleText);
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = chooser.showOpenDialog(stage);
        if (selectedFile != null) {
            //Save
            inputElement.setText(String.valueOf(selectedFile));
        }
    }

    private boolean checkValidation(TextField openKey, TextField privateKeyVl){
        boolean valid = false;
        if(!openKey.getText().isEmpty() && !privateKeyVl.getText().isEmpty()){
            valid = validation.checkKeys(openKey.getText(), privateKeyVl.getText());
        }
        else{
            alert.setTitle("Key path not specified");
            alert.setContentText("Fill in the fields for the public and private key!");
            alert.showAndWait();
        }
        if(valid){
            System.out.println("valid - ok");
        }
        else{
            alert.setTitle("Keys don't match");
            alert.setContentText(
                    "Public and private key data do not match! The public and private keys are generated as a pair at the same time, check that the entered keys are correct.");
            alert.showAndWait();
        }
        return valid;
    }

    private boolean checkSaveFile(){
        if(fileSave.getText().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    @FXML
    private void showHelp() throws IOException {
        InputStream icon = getClass().getResourceAsStream("/java-logo-vert-blk.png");
        Image image = new Image(icon);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Helper.fxml")));
        stage.setScene(new Scene(root));
        stage.getIcons().add(image);
        stage.setTitle("Help");
        stage.setMaxHeight(650);
        stage.setMaxWidth(500);
        stage.setWidth(500);
        stage.setHeight(650);
        stage.show();
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
