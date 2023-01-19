package encryption.main.encryption;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class EncryptionMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        InputStream icon = getClass().getResourceAsStream("/java-logo-vert-blk.png");
        Image image = new Image(icon);
        stage.getIcons().add(image);
        stage.setTitle("Menu");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Menu.fxml"))); //FXMLLoader создает интерфейс из файла, при помощи load выгружает объект типа Parent
        stage.setScene(new Scene(root)); //передаем в нашу сцену наш извлеченный FXML
        stage.setMaxHeight(700);
        stage.setMaxWidth(600);
        stage.setWidth(600);
        stage.setHeight(700);
        stage.setResizable(false);
        stage.show();
//        final InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream("/localization/ru_RU.properties"), "UTF-8");

    }

    public static void main(String[] args) {
        launch();
    }
}