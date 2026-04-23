import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//16분 성공
public class _2178 {

    static int[] moveX = new int[]{-1, 0, 0, 1};
    static int[] moveY = new int[]{0, -1, 1, 0};

    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        BFS(new LinkedList<int[]>(), 0, 0);

        System.out.println(map[N-1][M-1]);

    }

    public static void BFS(Queue<int[]> queue, int y, int x) {
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while(!queue.isEmpty()) {
            int[] target = queue.poll();

            for(int i=0;i<4;i++) {
                int newY = target[0] + moveY[i];
                int newX = target[1] + moveX[i];

                if(newY < 0 || newX < 0 || newY >= map.length || newX >= map[0].length)
                    continue;

                if(map[newY][newX] == 0 || map[newY][newX] > 1)
                    continue;

                visited[newY][newX] = true;
                map[newY][newX] = map[target[0]][target[1]] + 1;
                queue.add(new int[]{newY, newX});
            }
        }

    }
}
