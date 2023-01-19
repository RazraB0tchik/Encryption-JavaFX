package encryption.main.encryption.utils.textWork;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Decryption {
    public void startDecryption(List<BigInteger> privateKey, String saveWay, String getWay){

        String path = saveWay+"decryptionFiles";
        if(!(new File(path).exists())) {
            try {
                Files.createDirectory(Path.of(path));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File decryptionFile = new File(path+"/newDecryptFile "+ LocalTime.now()+".txt");
        System.out.println(saveWay);
        List<String> numberLine = new ArrayList<String>();
        BigInteger n = privateKey.get(0).multiply(privateKey.get(1));
        Integer lengthN = n.toString().length();
        List<String> decryptFinal = new ArrayList<String>();
        try {
            String fileDecr = Files.lines(Paths.get(getWay)).reduce("", String::concat);
            char fileDecrCh[] = fileDecr.toCharArray();
            String number = "";
            int count = 0;
            for(int i =0; i<fileDecrCh.length; i++){
                count++;
                if(count<lengthN){
                    number+=fileDecrCh[i];
                }
                if(count == lengthN){
                    count=0;
                    number+=fileDecrCh[i];
                    numberLine.add(number);
                    number = "";
                }

            }
            System.out.println(privateKey.get(2)+ " inc");
            numberLine.forEach(elem -> {
                while (elem.startsWith("0") && elem!="0"){
                    elem = elem.substring(1);
                }
                System.out.println(elem + " !!!!!!!");
                BigInteger e = new BigInteger(elem);
                BigInteger result = e.modPow(privateKey.get(2), n);
                if(result.intValue() >=32 && result.intValue() <97){
                    decryptFinal.add(String.valueOf((char) (result.intValue())));
                }
                else{
                    decryptFinal.add(String.valueOf((char) (result.intValue()+96)));
                }
            });
            numberLine.forEach(System.out::println);
            decryptFinal.forEach(System.out::println);
            FileWriter fileWriter = new FileWriter(decryptionFile);
            decryptFinal.forEach(e -> {
                try {
                    fileWriter.write(e);
                    fileWriter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
