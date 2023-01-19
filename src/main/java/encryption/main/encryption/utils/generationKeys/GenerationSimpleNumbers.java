package encryption.main.encryption.utils.generationKeys;

import encryption.main.encryption.entity.SelectedParams;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GenerationSimpleNumbers {
    public HashMap<String, List<BigInteger>> generateNumbers(Integer keySize, Integer openExponent){

        GenerationKeys generationKeys = new GenerationKeys();

        BigInteger numberP;
        BigInteger numberQ;
        do {
            numberP = generateNumber(keySize);
        }
        while (!MillerTest(numberP, 100));

        do {
            numberQ = generateNumber(keySize);
        }
        while (!MillerTest(numberQ, 100));


        HashMap<String, List<BigInteger>> keys = generationKeys.generationKeys(numberP, numberQ, openExponent);
        return keys;
    }

    public BigInteger generateNumber(int size){
        StringBuilder string = new StringBuilder();
        for(int i =0; i<size; i++){
            if(i==0){
                string.append("1");
            }
            if(i==size-1){
                string.append("1");
            }
            else {
                string.append((int) (Math.random() * 2));
            }
        }
        BigInteger number = new BigInteger(string.toString(), 2);
        return number;

    }

    public boolean MillerTest(BigInteger n, int k){ //

        if(n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0){
            return false;
        }
        BigInteger t = n.subtract(BigInteger.ONE);
        int s = 0;

        while (t.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0){
            t=t.divide(BigInteger.TWO);
            s++;
        }
        SecureRandom random = new SecureRandom();
        BigInteger a;
        for (int i = 0; i<k; i++){
            byte start_a[] = new byte[n.bitLength()/8];
            do{
                random.nextBytes(start_a);
                a = new BigInteger(start_a);
            }
            while (a.compareTo(BigInteger.TWO) == -1 || a.compareTo(n.subtract(BigInteger.TWO)) == 1 || a.compareTo(n.subtract(BigInteger.TWO)) == 0);

            BigInteger x = a.modPow(t, n);

            if(x.compareTo(BigInteger.ONE) == 0 || x.compareTo(n.subtract(BigInteger.ONE)) == 0){
                continue;
            }
            for(int r = 1; r<s; r++){
                x = x.modPow(BigInteger.TWO, n);

                if(x.compareTo(BigInteger.ONE) == 0){
                    return false;
                }

                if(x.compareTo(n.subtract(BigInteger.ONE)) == 0){
                    break;
                }
            }

            if(x.compareTo(n.subtract(BigInteger.ONE)) != 0){
                return false;
            }
        }
        return true;
    }

}
