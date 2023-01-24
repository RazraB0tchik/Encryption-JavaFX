package encryption.main.encryption.utils.textWork;


import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Encryption {
    public void startEncryptionWithKeys(String text, String saveWay, String encryptFile, String openKeyFile) {
        List<BigInteger> open = new ArrayList<>();
        try {
            FileReader fr = new FileReader(String.valueOf(Paths.get(openKeyFile)));
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                open.add(new BigInteger(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> elementsASCI = analysisText(text, open, encryptFile);
        this.saveEncryptionFile(saveWay, elementsASCI);
    }



    public void startEncryptionWithoutKeys(List<BigInteger> openKey, String text, String saveWay, String encryptFile) {
        List<String> elementsASCI = analysisText(text, openKey, encryptFile);
        this.saveEncryptionFile(saveWay, elementsASCI);
    }

    private List<String> analysisText(String text, List<BigInteger> openKey, String encryptFile){
        int asciElement;
        String numbers= " 0123456789!#*(),./:;<>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^-_'\"\'";
        int constant = 96;
        List<String> elementsNewASCI = new ArrayList<>();
        List<Integer> elementsASCI = new ArrayList<>();
        if(!encryptFile.isEmpty()){
            String fileDecr = null;
            try {
                fileDecr = Files.lines(Paths.get(encryptFile)).reduce("", String::concat);
                char fileDecrCh[] = fileDecr.toCharArray();
                for (int i = 0; i < fileDecrCh.length; i++) {
                    if (numbers.contains(String.valueOf(fileDecrCh[i]))) {
                        asciElement = fileDecrCh[i];
                        elementsASCI.add(asciElement);
                    } else {
                        asciElement = fileDecrCh[i];
                        asciElement -= constant;
                        elementsASCI.add(asciElement);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            char charArray[] = text.toCharArray();

            for (int i = 0; i < charArray.length; i++) {
                if (numbers.contains(String.valueOf(charArray[i]))) {
                    asciElement = charArray[i];
                    elementsASCI.add(asciElement);
                } else {
                    asciElement = charArray[i];
                    asciElement -= constant;
                    elementsASCI.add(asciElement);
                }
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

    private void saveEncryptionFile(String path, List<String> elementsASCI){
        String pathDirect = path + "encryptionFiles";
        if (!(new File(pathDirect).exists())) {
            try {
                Files.createDirectory(Path.of(pathDirect));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File encryptionResult = new File(pathDirect + "/newEncryptFile " + LocalTime.now() + ".txt");
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
    }
    }
