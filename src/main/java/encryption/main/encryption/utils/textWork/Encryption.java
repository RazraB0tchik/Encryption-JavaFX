package encryption.main.encryption.utils.textWork;

import encryption.main.encryption.entity.SelectedParams;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Encryption {
    public void startEncryption(List<BigInteger> openKey, String text){
        text = text.toLowerCase();
        List<String> elementsASCI = analysisText(text, openKey);
        elementsASCI.forEach(System.out::println);

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
            System.out.println((openKey.get(0)).toString().length() + " mainLength");
            while (stringEncrypt.length() < (openKey.get(0)).toString().length()) {
                stringEncrypt = "0" + stringEncrypt;
            }
            System.out.println(stringEncrypt.length() + " elemLeng");
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
