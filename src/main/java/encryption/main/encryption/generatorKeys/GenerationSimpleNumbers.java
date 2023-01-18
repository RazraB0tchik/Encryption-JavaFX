package encryption.main.encryption.generatorKeys;

import javafx.scene.control.SplitPane;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class GenerationSimpleNumbers {
    GenerationKeys generationKeys;
    public void generateNumbers(Integer size, String openExp){
        Random rand = new Random();
        BigInteger numberP;
        BigInteger numberQ;
        do {
            numberP = new BigInteger(size, rand);
        }
        while (!MillerTest(numberP, 10));

        do {
            numberQ = new BigInteger(size, rand);
        }
        while (!MillerTest(numberQ, 10));
//        System.out.println(numberP);

        generationKeys.generationKeys(numberP, numberQ, openExp);
//        System.out.println( MillerTest(numberP, 10));
//        System.out.println(numberP);
//        System.out.println( MillerTest(numberQ, 10));
//        System.out.println(numberQ);
//        System.out.println(openExp);
    }

    public boolean MillerTest(BigInteger n, int k){ //

        if(n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0){
//
            return false;
        }
        BigInteger t = n.subtract(BigInteger.ONE);
//        System.out.println(t);
        int s = 0;

        while (t.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0){
            t=t.divide(BigInteger.TWO);
            s++;
        }
        SecureRandom random = new SecureRandom();
        BigInteger a = BigInteger.ONE;
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
