import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void problem20(int n, int k, int l){
        //amount of received items
        BigInteger sum = BigInteger.valueOf(0);
        //result 
        BigInteger result = BigInteger.valueOf(1);

        for(long i = 1; i<=n; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }

        String str = String.valueOf(result);

        for (int i = 0; i < k; i++){
            System.out.print(str.charAt(i));
        }
        System.out.print(" ");
        StringBuilder str1 = new StringBuilder();
        for (int i = str.length() - 1; i > str.length() - l - 1; i--){
            str1.append(str.charAt(i));
        }
        System.out.println(str1.reverse());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String s1 = br.readLine();
        while (t-- > 0){
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int l = Integer.parseInt(s[2]);
            problem20(n, k, l);
            s1 = br.readLine();
        }
          //число из которого нужно взять факториал
    }
}