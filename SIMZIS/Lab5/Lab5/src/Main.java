import java.math.*;
import java.security.*;
import java.util.*;
public class Main {
    public static int T = 1;
    public static SecureRandom secureRandom;
    public static Random random;
    public static void main(String[] args) {
        secureRandom = new SecureRandom();
        random = new Random();
        while (T-- > 0){
            String s1 = "a";
            BigInteger p = new BigInteger(1024, secureRandom);
            while (!p.isProbablePrime(100))
                p = new BigInteger(1024, secureRandom);
            BigInteger q = new BigInteger(1024, secureRandom);
            while (!q.isProbablePrime(100))
                q = new BigInteger(1024, secureRandom);
            BigInteger n = p.multiply(q);
            BigInteger phi = (p.subtract(BigInteger.valueOf(1))).
                    multiply(q.subtract(BigInteger.valueOf(1)));
            BigInteger e = BigInteger.valueOf(1);
            for (long i = 2; i < Integer.MAX_VALUE; i++){
                BigInteger number = BigInteger.valueOf(i);
                if (coprime(phi, number)){
                    e = number;
                    break;
                }
            }
            BigInteger d = e.modInverse(phi);
            BigInteger m = BigInteger.valueOf((int) s1.charAt(0));
            BigInteger c = m.modPow(e, n);
            BigInteger m1 = c.modPow(d, n);
            BigInteger s = m1.modPow(d, n);
            BigInteger m2 = s.modPow(e, n);
            bigOutput(p, q, n, phi, e, d, m, c, m1, s, m2);
        }
    }

    public static void bigOutput(BigInteger p, BigInteger q, BigInteger n,
                                 BigInteger phi, BigInteger e, BigInteger d,
                                 BigInteger m, BigInteger c, BigInteger m1,
                                 BigInteger s, BigInteger m2){
        smallOutput("p", p);
        smallOutput("q", q);
        smallOutput("n", n);
        smallOutput("phi(n)", phi);
        smallOutput("e", e);
        smallOutput("d", d);
        System.out.println("Open key: {" + e + "\n" + n + "}");
        System.out.println("Closed key: {" + d + "\n" + n + "}");
        smallOutput("m", m);
        System.out.println("Encryption: " + c);
        System.out.println("Decryption: " + m1);
        System.out.println("Digital signature: " + s);
        System.out.println("Message prototype: " + m2);
        if (m1.equals(m2))
            System.out.println("Signature is valid");
        else
            System.out.println("Missigned");
    }

    public static void smallOutput(String s, BigInteger a){
        String stringFormat = String.format("%s = %s", s, a.toString());
        System.out.println(stringFormat);
    }

    private static boolean coprime(BigInteger a, BigInteger b) {
        return a.gcd(b).equals(BigInteger.ONE);
    }
}