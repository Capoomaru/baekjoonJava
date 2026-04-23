import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10835 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];

        StringTokenizer stA = new StringTokenizer(br.readLine(), " ");
        StringTokenizer stB = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stA.nextToken());
            B[i] = Integer.parseInt(stB.nextToken());
        }

        int i, j;
        i = j = 1;

        int[][] dp = new int[N + 1][N + 1];

        while(i < N && j < N) {
            dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            if(A[i] < B[i]) {
                dp[i][j] += B[j++];
            }
        }
    }

}
