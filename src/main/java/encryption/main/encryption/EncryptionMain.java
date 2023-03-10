package encryption.main.encryption;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class EncryptionMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        InputStream icon = getClass().getResourceAsStream("/java-logo-vert-blk.png");
        Image image = new Image(icon);
        stage.getIcons().add(image);
        stage.setTitle("Menu");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Menu.fxml")));
        stage.setScene(new Scene(root));
        stage.setMaxHeight(1000);
        stage.setMaxWidth(700);
        stage.setWidth(700);
        stage.setHeight(1000);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}