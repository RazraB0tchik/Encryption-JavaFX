package encryption.main.encryption.utils.textWork;

import encryption.main.encryption.entity.SelectedParams;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Encryption {
    public void startEncryption(List<BigInteger> openKey, String text, String saveWay){
        List<String> elementsASCI = analysisText(text, openKey);
        elementsASCI.forEach(System.out::println);
//        String way = saveWay+"encryptionFiles/newEncryptFile:"+ LocalTime.now()+".txt";
        String path = saveWay+"encryptionFiles";
        if(!(new File(path).exists())) {
            try {
                Files.createDirectory(Path.of(path));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File encryptionResult = new File(path+"/newEncryptFile "+ LocalTime.now()+".txt");
        try {
            FileWriter fileWriter = new FileWriter(encryptionResult, true);
            elementsASCI.forEach(e -> {
                try {
                    fileWriter.write(e);
                    fileWriter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        elementsASCI.forEach(System.out::println);
    }

    private List<String> analysisText(String text, List<BigInteger> openKey){
        int asciElement;
        String numbers= " 0123456789!#*(),./:;<>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^-_'";
        int constant = 96;
        char charArray[] = text.toCharArray();
        List<Integer> elementsASCI = new ArrayList<>();
        List<String> elementsNewASCI = new ArrayList<>();
        for(int i = 0; i<charArray.length; i++){
            if (numbers.contains(String.valueOf(charArray[i]))){
                asciElement = charArray[i];
                elementsASCI.add(asciElement);
            }
            else {
                asciElement = charArray[i];
                asciElement -= constant;
                elementsASCI.add(asciElement);
            }
        }

        elementsASCI.forEach(elem -> {
            BigInteger encryptNumber = BigInteger.valueOf(elem).modPow(openKey.get(1), openKey.get(0));
            String stringEncrypt = encryptNumber.toString();
            while (stringEncrypt.length() < (openKey.get(0)).toString().length()) {
                stringEncrypt = "0" + stringEncrypt;
            }
            elementsNewASCI.add(stringEncrypt);
        });



        return elementsNewASCI;
    }
}
