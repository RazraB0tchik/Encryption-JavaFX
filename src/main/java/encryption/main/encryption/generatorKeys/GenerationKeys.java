package encryption.main.encryption.generatorKeys;

import java.math.BigInteger;

public class GenerationKeys {
    public void generationKeys(BigInteger p, BigInteger q, Object openExponent){
        BigInteger module = p.multiply(q);
        BigInteger eulerFunc = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println(openExponent);

    }
}
