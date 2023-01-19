package encryption.main.encryption.utils.generationKeys;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerationKeys { //класс генерации ключей
    public HashMap<String, List<BigInteger>> generationKeys(BigInteger p, BigInteger q, Integer openExponent){
        List<BigInteger> openKey = new ArrayList<>();
        List<BigInteger> privateKey = new ArrayList<>();

        BigInteger a1 = BigInteger.ZERO;
        BigInteger a2 = BigInteger.ONE;
        BigInteger b1 = BigInteger.ONE;
        BigInteger b2 = BigInteger.ZERO;

        BigInteger module = p.multiply(q);
        BigInteger eulerFunc = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        List<BigInteger> resultsEuclid = euclidAlgorithm(eulerFunc, BigInteger.valueOf(openExponent), a1, a2, b1, b2);
        HashMap keys = new HashMap();

        if(resultsEuclid.size() != 1){
        //открытый ключ
        openKey.add(module); //n
        openKey.add(BigInteger.valueOf(openExponent)); //e

        //закрытый ключ
        privateKey.add(p);//p
        privateKey.add(q);//q
        privateKey.add(resultsEuclid.get(2));//d
        privateKey.add(module);//n
        privateKey.add(BigInteger.valueOf(openExponent));//e
        keys.put("open", openKey);
        keys.put("private", privateKey);
        }
        else {
            keys.put("invalid_exponent", null);
        }
        return keys;
    }
    //расширенный алгоритм Евклида
    public List<BigInteger> euclidAlgorithm(BigInteger x, BigInteger y, BigInteger a1, BigInteger a2, BigInteger b1, BigInteger b2) throws RuntimeException {
        List<BigInteger> resultsAlgorithm = new ArrayList<>();
        BigInteger r;
        BigInteger a;
        BigInteger b;
        BigInteger q;
        while (y != BigInteger.ZERO) {
            q = x.divide(y);
            r = x.subtract(q.multiply(y));
            a = a2.subtract(q.multiply(a1));
            b = b2.subtract(q.multiply(b1));
            x = y;
            y = r;
            a2 = a1;
            a1 = a;
            b2 = b1;
            b1 = b;
        }
        if (x.compareTo(BigInteger.ONE) != 0) {
            System.out.println("Change openExponent!");
            resultsAlgorithm.add(x); //m
        }
        else{
            System.out.println("openExponent - OK");
            resultsAlgorithm.add(x); //m
            resultsAlgorithm.add(a2); //a
            resultsAlgorithm.add(b2); //b
        }

        return resultsAlgorithm;

    }
}
