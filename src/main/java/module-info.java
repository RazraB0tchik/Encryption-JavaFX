module encryption.main.encryption {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens encryption.main.encryption to javafx.fxml;
    exports encryption.main.encryption;
}