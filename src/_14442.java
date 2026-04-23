import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14442 {


    public static int[] xDiff = {-1, 0, 0, 1};
    public static int[] yDiff = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        boolean[][][] visited = new boolean[K+1][N][M];

        visited[K][0][0] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, K, 1});

        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            if (p[0] == N - 1 && p[1] == M - 1) {
                System.out.println(p[3]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int y = p[0] + yDiff[i];
                int x = p[1] + xDiff[i];

                if (y < 0 || y >= N || x < 0 || x >= M)
                    continue;

                if (map[y][x] == 1) {
                    if (p[2] == 0 || visited[p[2] - 1][y][x])
                        continue;
                    visited[p[2] - 1][y][x] = true;
                    queue.add(new int[]{y, x, p[2] - 1, p[3] + 1});
                } else {
                    if (visited[p[2]][y][x])
                        continue;
                    visited[p[2]][y][x] = true;
                    queue.add(new int[]{y, x, p[2], p[3] + 1});
                }

            }
        }

        System.out.println(-1);
    }
}
