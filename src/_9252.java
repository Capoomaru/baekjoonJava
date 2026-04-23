import java.io.*;

public class _9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String B = br.readLine();

        int[][] dp = new int[A.length() + 1][B.length() + 1];

        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        char[] answer = new char[dp[A.length()][B.length()]];

        int i = A.length();
        int j = B.length();

        int idx = dp[A.length()][B.length()];
        while (idx != 0) {
            while (dp[i - 1][j] == dp[i][j])
                i--;

            while (dp[i][j - 1] == dp[i][j])
                j--;

            answer[idx - 1] = A.charAt(i - 1);
            i--;
            j--;
            idx--;
        }

        bw.append(Integer.toString(dp[A.length()][B.length()]));
        bw.newLine();
        for (char c : answer)
            bw.append(c);

        bw.flush();

    }

}
