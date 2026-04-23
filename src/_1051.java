import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 8분 31초 성공
// NO IDE
public class _1051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int maxLen = Math.min(N, M);

        for (int len = maxLen - 1; len >= 1; len--) {
            for (int i = 0; i + len < N; i++) {
                for (int j = 0; j + len < M; j++) {
                    int tmp = map[i][j];
                    if (tmp != map[i + len][j])
                        continue;
                    if (tmp != map[i][j + len])
                        continue;
                    if (tmp != map[i + len][j + len])
                        continue;

                    System.out.println((len + 1) * (len + 1));
                    return;
                }
            }
        }

        System.out.println(1);


    }
}
