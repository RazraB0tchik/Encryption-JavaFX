package encryption.main.encryption.utils.keyValidation;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CheckValidation {
    public boolean checkKeys(String openKey, String privateKey){
        List<BigInteger> openKeyList = new ArrayList<>();
        List<BigInteger> privateKeyList = new ArrayList<>();
        openKeyList = readingFile(openKeyList, openKey);
        privateKeyList = readingFile(privateKeyList, privateKey);

        if(openKeyList.get(0).compareTo(privateKeyList.get(3)) == 0 && openKeyList.get(1).compareTo(privateKeyList.get(4)) == 0){
            return true;
        }
        else{
            return false;
        }
    }

    private List<BigInteger> readingFile(List<BigInteger> list, String key){
        String elem;
        System.out.println("asd");
        try {
            FileReader fileReaderOpen = new FileReader(String.valueOf(Paths.get(key)));
            BufferedReader bufferReaderOpen = new BufferedReader(fileReaderOpen);
            while ((elem = bufferReaderOpen.readLine())!=null){
                list.add(new BigInteger(elem));
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid path file.");
            alert.setContentText("File on path: " + key + " is not found!");
            alert.showAndWait();
        }
        return list;
    }
}
