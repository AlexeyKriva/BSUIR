import java.util.*;
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int p = 3877;
        int g = generator(p);
        System.out.println("Первообразный корень: " + g + " по модулю " + p);
        int a = random.nextInt(20);
        int b = random.nextInt(20);
        System.out.println("a = " + a + "\nb = " + b);
        while (b == a)
            b = random.nextInt(20);
        int A = powmod(g, a, p);
        int B = powmod(g, b, p);
        System.out.println("A = " + A + "\nB = " + B);
        int K = powmod(g, a * b, p);
        System.out.println("K = " + K);
    }

    public static int powmod(int a, int b, int p) {
        int res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (int) ((long) res * a % p);
                b--;
            }
            a = (int) ((long) a * a % p);
            b >>= 1;
        }
        return res;
    }

    public static int generator(int p) {
        ArrayList<Integer> fact = new ArrayList<>();
        int phi = p - 1;
        int n = phi;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                fact.add(i);
                while (n % i == 0)
                    n /= i;
            }
        }
        if (n > 1)
            fact.add(n);
        for (int res = 2; res <= p; ++res) {
            boolean ok = true;
            for (int i = 0; i < fact.size() && ok; ++i)
                ok &= powmod(res, phi / fact.get(i), p) != 1;
            if (ok)
                return res;
        }
        return -1;
    }
}