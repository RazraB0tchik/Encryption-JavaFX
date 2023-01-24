package encryption.main.encryption.mainEncryption;

import encryption.main.encryption.utils.generationKeys.GenerationSimpleNumbers;
import encryption.main.encryption.utils.textWork.Decryption;
import encryption.main.encryption.utils.textWork.Encryption;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainEncryptionClass {

    GenerationSimpleNumbers generationSimpleNumbers = new GenerationSimpleNumbers();
    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption();
    HashMap<String, List<BigInteger>> keys;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    public void generateKeys(Integer keySize, Integer openExponent, String saveFile){
        keys = generationSimpleNumbers.generateNumbers(keySize, openExponent);

        if(keys.size()!=1) {
            String path = saveFile + "keyFile";
            if (!(new File(path).exists())) {
                try {
                    Files.createDirectory(Path.of(path));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File openKeyFile = new File(path + "/newOpenKey " + LocalTime.now() + ".txt");
            File privateKeyFile = new File(path + "/newPrivateKey " + LocalTime.now() + ".txt");

            try {
                FileWriter fileWriterOpen = new FileWriter(openKeyFile);
                FileWriter fileWriterClose = new FileWriter(privateKeyFile);
                keys.get("open").forEach(e -> {
                    try {
                        fileWriterOpen.write(e.toString() + "\n");
                        System.out.println(e);
                        fileWriterOpen.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                fileWriterOpen.close();

                keys.get("private").forEach(e -> {
                    try {
                        fileWriterClose.write(e.toString()+"\n");
                        fileWriterClose.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                fileWriterClose.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void encryptionText(String text, String saveFile, String encrypFile, String selectOpenKey, Boolean keysNotEmpty){
        if(keys==null && !keysNotEmpty){
            alert.setTitle("Keys do not exist");
            alert.setContentText("Generate keys or specify the path to an existing bundle");
            alert.showAndWait();
        }
        if(keys!=null && !keysNotEmpty){
            encryption.startEncryptionWithoutKeys(keys.get("open"), text, saveFile, encrypFile);
        }
        if(keysNotEmpty){
            encryption.startEncryptionWithKeys(text, saveFile, encrypFile, selectOpenKey);
        }
    }
    public void decryptionText(String getFile, String saveFile, String privateKey, Boolean keysNotEmpty) {
        if(keys==null && !keysNotEmpty){
            alert.setTitle("Keys do not exist");
            alert.setContentText("Generate keys or specify the path to an existing bundle");
            alert.showAndWait();
        }

        if (keys != null && !keysNotEmpty) {
            decryption.startDecryptionWithoutKeys(keys.get("private"), saveFile, getFile);
        }
        if(keysNotEmpty){
            decryption.startDecryptionWithKeys(saveFile, getFile, privateKey);
        }
    }

}
