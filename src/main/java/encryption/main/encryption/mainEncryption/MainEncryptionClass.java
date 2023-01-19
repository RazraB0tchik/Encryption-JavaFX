package encryption.main.encryption.mainEncryption;

import encryption.main.encryption.entity.SelectedParams;
import encryption.main.encryption.utils.generationKeys.GenerationSimpleNumbers;
import encryption.main.encryption.utils.textWork.Decryption;
import encryption.main.encryption.utils.textWork.Encryption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;


public class MainEncryptionClass {

    GenerationSimpleNumbers generationSimpleNumbers = new GenerationSimpleNumbers();
    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption();
    HashMap<String, List<BigInteger>> keys;
    public void generateKeys(Integer keySize, Integer openExponent, String saveFile){ //генерация ключей
        keys = generationSimpleNumbers.generateNumbers(keySize, openExponent); //вызов метода генерации простых чисел
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
                        fileWriterOpen.write(e.toString());
                        fileWriterClose.write("\n");
                        fileWriterOpen.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                fileWriterOpen.close();

                keys.get("private").forEach(e -> {
                    try {
                        fileWriterClose.write(e.toString());
                        fileWriterClose.write("\n");
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

    public void encryptionText(String text, String saveFile){ //вызов метода шифрования сообщения
        encryption.startEncryption(keys.get("open"), text, saveFile);
    }

    public void decryptionText(String getFile, String saveFile){ //вызов метода дешифрования сообщения
        decryption.startDecryption(keys.get("private"), getFile, saveFile);
    }

}
