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
    public void startDecryptionWithoutKeys(List<BigInteger> privateKey, String saveWay, String getWay){
        String path = saveWay+"decryptionFiles";
        if(!(new File(path).exists())) {
            try {
                Files.createDirectory(Path.of(path));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.fileDecryption(path, privateKey, getWay);
    }

    public void startDecryptionWithKeys(String saveWay, String getWay, String privateKeyFile) {
        System.out.println(privateKeyFile);
        List<BigInteger> privateFileData = new ArrayList<>();
        String path = saveWay+"decryptionFiles";
        if(!(new File(path).exists())) {
            try {
                Files.createDirectory(Path.of(path));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            try {
                FileReader fr = new FileReader(String.valueOf(Paths.get(privateKeyFile)));
                BufferedReader reader = new BufferedReader(fr);
                String line;
                while ((line = reader.readLine()) != null) {
                    privateFileData.add(new BigInteger(line));
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.fileDecryption(path, privateFileData, getWay);
    }
        private void fileDecryption(String path, List<BigInteger> privateFileData, String getWay) {
            File decryptionFile = new File(path + "/newDecryptFile " + LocalTime.now() + ".txt");
            List<String> numberLine = new ArrayList<String>();
            BigInteger n = privateFileData.get(0).multiply(privateFileData.get(1));
            Integer lengthN = n.toString().length();
            BigInteger bElem = privateFileData.get(2);
            List<String> decryptFinal = new ArrayList<String>();
            try {
                String fileDecr = Files.lines(Paths.get(getWay)).reduce("", String::concat);
                char fileDecrCh[] = fileDecr.toCharArray();
                String number = "";
                int count = 0;
                for (int i = 0; i < fileDecrCh.length; i++) {
                    count++;
                    if (count < lengthN) {
                        number += fileDecrCh[i];
                    }
                    if (count == lengthN) {
                        count = 0;
                        number += fileDecrCh[i];
                        numberLine.add(number);
                        number = "";
                    }

                }
                numberLine.forEach(elem -> {
                    while (elem.startsWith("0") && elem != "0") {
                        elem = elem.substring(1);
                    }
                    BigInteger e = new BigInteger(elem);
                    BigInteger result = e.modPow(bElem, n);
                    if (result.intValue() >= 32 && result.intValue() < 97) {
                        decryptFinal.add(String.valueOf((char) (result.intValue())));
                    } else {
                        decryptFinal.add(String.valueOf((char) (result.intValue() + 96)));
                    }
                });
//            numberLine.forEach(System.out::println);
//            decryptFinal.forEach(System.out::println);
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
