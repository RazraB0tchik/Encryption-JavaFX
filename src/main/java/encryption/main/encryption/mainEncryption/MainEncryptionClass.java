package encryption.main.encryption.mainEncryption;

import encryption.main.encryption.entity.SelectedParams;
import encryption.main.encryption.utils.generationKeys.GenerationSimpleNumbers;
import encryption.main.encryption.utils.textWork.Encryption;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;


public class MainEncryptionClass {

    GenerationSimpleNumbers generationSimpleNumbers = new GenerationSimpleNumbers();
    Encryption encryption = new Encryption();
    HashMap<String, List<BigInteger>> keys;
    public void generateKeys(Integer keySize, Integer openExponent){
        keys = generationSimpleNumbers.generateNumbers(keySize, openExponent);
    }

    public void encryptionText(String text){
//        System.out.println(text);
//        System.out.println(keys.get("open"));
//        System.out.println("im here!");
        encryption.startEncryption(keys.get("open"), text);
    }

}
