import java.util.*;
import java.io.*;

public class _17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++) {
            String input = br.readLine();

            int answer = solution(input);
            System.out.println(answer);

        }
    }

    static int solution(String input) {
        int left = 0;
        int right = input.length() - 1;
        boolean isUsed = false;

        while(left <= right) {
            char l = input.charAt(left);
            char r = input.charAt(right);
            if(l == r) {
                left++;
                right--;
            }
            else if(isUsed) {
                return 2;
            }
            else {
                if (input.charAt(left + 1) == r) {
                    left++;
                    isUsed = true;
                    continue;
                }
                if (input.charAt(right - 1) == l) {
                    right--;
                    isUsed = true;
                    continue;
                }
                return 2;
            }
        }

        return isUsed ? 1 : 0;
    }
}