import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14502_2 {

    public static int N, M;

    public static int[] xDiff = {-1, 0, 0, 1};
    public static int[] yDiff = {0, -1, 1, 0};

    static int why = 0, safeCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        ArrayList<int[]> virusList = new ArrayList<>();
        ArrayList<int[]> safeList = new ArrayList<>();


        safeCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virusList.add(new int[]{i, j});
                else if (map[i][j] == 0) {
                    safeCnt++;
                    safeList.add(new int[]{i, j});
                }
            }
        }

        int result = backTrack(map, safeList, virusList, 0, new int[3][3], 0);


        System.out.println(result);
        System.out.println(safeCnt);
        System.out.println(safeCnt * (safeCnt - 1) * (safeCnt - 2) / 6);
        System.out.println(why);


    }

    public static int backTrack(int[][] map, ArrayList<int[]> safeList, ArrayList<int[]> virusList, int idx, int[][] selected, int depth) {
        if (depth == 3) {
            why++;
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < 3; i++)
                visited[selected[i][0]][selected[i][1]] = true;
            Queue<int[]> queue = new ArrayDeque<>();
            for (int[] virus : virusList) {
                visited[virus[0]][virus[1]] = true;
                queue.add(virus);
            }
            int cnt = 0;
            cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (!visited[i][j] && map[i][j] == 0)
                        cnt++;
            if (safeCnt != cnt + 3) {
                System.out.println("error" + cnt);
            }

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

            cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (!visited[i][j] && map[i][j] == 0)
                        cnt++;

            return cnt;
        }
        if (idx == safeList.size())
            return 0;

        int min = backTrack(map, safeList, virusList, idx + 1, selected, depth);
        selected[depth][0] = safeList.get(idx)[0];
        selected[depth][1] = safeList.get(idx)[1];
        min = Math.max(min, backTrack(map, safeList, virusList, idx + 1, selected, depth + 1));


        return min;
    }
}
