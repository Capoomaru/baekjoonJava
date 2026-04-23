import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int max = 0;
        for (int i = 0; i < input.length(); i++) {
            int[] kmp = new int[input.length() - i];

            int j = 1;
            int k = 0;

            while (j < input.length() - i) {
                if (input.charAt(i + j) == input.charAt(i + k)) {
                    kmp[j] = k + 1;
                    max = Math.max(max, kmp[j]);
                    j++;
                    k++;
                } else if (k > 0)
                    k = kmp[k - 1];
                else {
                    kmp[k] = 0;
                    j++;
                }
            }
        }

        System.out.println(max);
    }
}
