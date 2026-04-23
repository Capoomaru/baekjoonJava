import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17141 {

    static int N, M, safeCnt;

    static int[] xDiff = {-1, 0, 0, 1};
    static int[] yDiff = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        ArrayList<int[]> virusList = new ArrayList<>(M);

        safeCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new int[]{i, j});
                    safeCnt++;
                } else if (map[i][j] == 0) {
                    safeCnt++;
                }
            }
        }
        if(safeCnt == M) {
            System.out.println(0);
            return;
        }

        int result = backTrack(map, virusList, 0, 0, 0);


        if (result == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(result);


    }

    public static int backTrack(int[][] map, ArrayList<int[]> virusList, int idx, int depth, int selected) {
        if (depth == M) {
            int cnt = M;
            Queue<int[]> queue = new ArrayDeque<>();
            int[][] visited = new int[N][N];
            for (int i = 0; i < virusList.size(); i++) {
                if (((1 << i) & selected) != (1 << i))
                    continue;
                int[] virus = virusList.get(i);
                queue.add(virus);
                visited[virus[0]][virus[1]] = 1;
            }

            while (!queue.isEmpty()) {
                int[] p = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int y = p[0] + yDiff[i];
                    int x = p[1] + xDiff[i];

                    if (y < 0 || y >= N || x < 0 || x >= N || map[y][x] == 1 || visited[y][x] > 0)
                        continue;

                    visited[y][x] = visited[p[0]][p[1]] + 1;
                    cnt++;
                    if (cnt == safeCnt) {
                        return visited[p[0]][p[1]];
                    }

                    queue.add(new int[]{y, x});
                }
            }
            return Integer.MAX_VALUE;
        }

        if (idx == virusList.size())
            return Integer.MAX_VALUE;

        int min = backTrack(map, virusList, idx + 1, depth, selected);
        min = Math.min(min, backTrack(map, virusList, idx + 1, depth + 1, selected | (1 << idx)));

        return min;
    }
}
