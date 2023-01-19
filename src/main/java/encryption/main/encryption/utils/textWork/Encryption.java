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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Encryption {
    public void startEncryption(List<BigInteger> openKey, String text, String saveWay){
        text = text.toLowerCase();
        List<String> elementsASCI = analysisText(text, openKey);
        elementsASCI.forEach(System.out::println);
        System.out.println(saveWay);
//        File encryptionResult = new File(saveWay+"/newEncrypt"+ LocalDateTime.now()+".txt");
//        try {
//            FileWriter fileWriter = new FileWriter(encryptionResult, false);
//            elementsASCI.forEach(e -> {
//                try {
//                    fileWriter.write(e);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        elementsASCI.forEach(System.out::println);
    }

    private List<String> analysisText(String text, List<BigInteger> openKey){
        int asciElement;
        String stringElement;
        int constant = 96;
        char charArray[] = text.toCharArray();
        List<Integer> elementsASCI = new ArrayList<>();
        List<String> elementsNewASCI = new ArrayList<>();
        for(int i = 0; i<charArray.length; i++){
            asciElement = charArray[i];
            asciElement-=constant;
            elementsASCI.add(asciElement);
        }
        System.out.println(openKey.get(0));

        elementsASCI.forEach(elem -> {
            BigInteger encryptNumber = BigInteger.valueOf((long) Math.pow(elem, openKey.get(1).intValue())).mod(openKey.get(0));
            String stringEncrypt = encryptNumber.toString();
//            System.out.println((openKey.get(0)).toString().length() + " mainLength");
            while (stringEncrypt.length() < (openKey.get(0)).toString().length()) {
                stringEncrypt = "0" + stringEncrypt;
            }
//            System.out.println(stringEncrypt.length() + " elemLeng");
            elementsNewASCI.add(stringEncrypt);
        });

//        if(asciElement % 100 >=1 && asciElement/100==0){
//            asciElement*=10;
//        }
//        if(asciElement % 100 < 1 && asciElement/100==0){
//            asciElement*=100;
//        }

        return elementsNewASCI;
    }
}
