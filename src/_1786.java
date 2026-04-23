import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class _1786 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();
        String P = br.readLine();

        int[] kmp = new int[P.length()];

        int i = 1;
        int j = 0;

        while (i < P.length()) {
            if (P.charAt(i) == P.charAt(j)) {
                kmp[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = kmp[j - 1];
            } else {
                kmp[j] = 0;
                i++;
            }
        }

        i = 0;
        j = 0;

        ArrayList<Integer> matchList = new ArrayList<>();
        int emptyCnt = 0;
        while(i<T.length()) {
            if(T.charAt(i) == ' ') {
                i++;
                emptyCnt++;
                continue;
            }
            if(T.charAt(i) == P.charAt(j)) {
                if(j == P.length() - 1) {
                    i++;
                    matchList.add(i - P.length() + 1 - emptyCnt);
                    j = 0;
                    emptyCnt = 0;
                    continue;
                }
                i++;
                j++;
            }
            else if(j > 0) {
                j = kmp[j - 1];
            }
            else {
                i++;
            }
        }

        System.out.println(matchList.size());
        matchList.forEach(System.out::println);
    }
}
