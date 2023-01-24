package encryption.main.encryption;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelpController implements Initializable{

    @FXML
    private TextArea areaHelp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        areaHelp.setEditable(false);
    }
}
