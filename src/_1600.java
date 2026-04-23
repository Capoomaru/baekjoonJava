import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1600 {

    public static int[] x1Diff = { -2, -1, 1, 2, -2, -1, 1, 2 };
    public static int[] y1Diff = { 1, 2, 2, 1, -1, -2, -2, -1 };
    public static int[] x2Diff = { -1, 0, 0, 1 };
    public static int[] y2Diff = { 0, -1, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] { 0, 0, K });

        int[][][] visited = new int[H][W][K + 1];
        visited[0][0][K] = 1;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            if(p[0] == H - 1 && p[1] == W - 1) {
                System.out.println(visited[p[0]][p[1]][p[2]] - 1);
                return;
            }

            for (int i = 0; i < x2Diff.length; i++) {
                int y = p[0] + y2Diff[i];
                int x = p[1] + x2Diff[i];

                if (y < 0 || y >= H || x < 0 || x >= W || map[y][x] == 1 || visited[y][x][p[2]] > 0)
                    continue;

                visited[y][x][p[2]] = visited[p[0]][p[1]][p[2]] + 1;

                queue.add(new int[] { y, x, p[2] });
            }

            if (p[2] == 0)
                continue;

            for (int i = 0; i < x1Diff.length; i++) {
                int y = p[0] + y1Diff[i];
                int x = p[1] + x1Diff[i];

                if (y < 0 || y >= H || x < 0 || x >= W || map[y][x] == 1 || visited[y][x][p[2] - 1] > 0)
                    continue;

                visited[y][x][p[2] - 1] = visited[p[0]][p[1]][p[2]] + 1;

                queue.add(new int[] { y, x, p[2] - 1});

            }
        }

        System.out.println(-1);

    }
}
