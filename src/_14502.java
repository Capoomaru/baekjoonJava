import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14502 {

    public static int N, M;

    public static int[] xDiff = {-1, 0, 0, 1};
    public static int[] yDiff = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        ArrayList<int[]> virusList = new ArrayList<>();

        int safeCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virusList.add(new int[]{i, j});
                else if (map[i][j] == 0)
                    safeCnt++;
            }
        }

        int result = backTrack(map, virusList, 0, 0, 0);

        System.out.println(safeCnt - result - 3);

    }

    public static int backTrack(int[][] map, ArrayList<int[]> virusList, int startY, int startX, int depth) {
        if (depth == 3) {
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> queue = new ArrayDeque<>(virusList);
            for (int[] virus : virusList)
                visited[virus[0]][virus[1]] = true;
            int cnt = 0;

            while (!queue.isEmpty()) {
                int[] p = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int y = p[0] + yDiff[i];
                    int x = p[1] + xDiff[i];

                    if (y < 0 || y >= N || x < 0 || x >= M || visited[y][x] || map[y][x] != 0)
                        continue;
                    visited[y][x] = true;
                    cnt++;
                    queue.add(new int[]{y, x});
                }
            }
            return cnt;
        }
        if (startY == N)
            return Integer.MAX_VALUE;
        if (startX == M) {
            return backTrack(map, virusList, startY + 1, 0, depth);
        }

        int min = Integer.MAX_VALUE;
        for (int j = startX; j < M; j++) {
            if (map[startY][j] != 0)
                continue;
            map[startY][j] = 3;
            min = Math.min(min, backTrack(map, virusList, startY, j + 1, depth + 1));
            map[startY][j] = 0;
        }
        for (int i = startY + 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0)
                    continue;
                map[i][j] = 3;
                min = Math.min(min, backTrack(map, virusList, i, j + 1, depth + 1));
                map[i][j] = 0;
            }
        }

        return min;
    }
}