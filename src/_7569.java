import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//46분 9초 실패 -> 동시에 전파가 시작되어야 함을 놓쳤었음
//51분 51초 성공
public class _7569 {

    static int[] moveX = new int[]{-1, 1, 0, 0, 0, 0};
    static int[] moveY = new int[]{0, 0, -1, 1, 0, 0};
    static int[] moveZ = new int[]{0, 0, 0, 0, -1, 1};

    static int[][][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    map[k][i][j] = tmp;
                }
            }
        }

        visited = new boolean[H][N][M];

        int answer = DFS(new LinkedList<>(), H, N, M);


        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(map[i][j][k] == 0)
                        answer = -1;
                }
            }
        }

        System.out.println(answer);
    }

    public static int DFS(Queue<int[]> queue, int H, int N, int M) {

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 1)
                        queue.add(new int[]{i, j, k});
                }
            }
        }

        int max = 1;

        while (!queue.isEmpty()) {
            int[] target = queue.poll();

            visited[target[0]][target[1]][target[2]] = true;

            for (int i = 0; i < 6; i++) {
                int newZ = target[0] + moveZ[i];
                int newY = target[1] + moveY[i];
                int newX = target[2] + moveX[i];

                if (newZ < 0 || newZ >= map.length
                        || newY < 0 || newY >= map[0].length
                        || newX < 0 || newX >= map[0][0].length) {
                    continue;
                }

                if (visited[newZ][newY][newX] || map[newZ][newY][newX] != 0) {
                    continue;
                }

                visited[newZ][newY][newX] = true;
                map[newZ][newY][newX] = map[target[0]][target[1]][target[2]] + 1;

                max = Math.max(max, map[newZ][newY][newX]);

                queue.add(new int[]{newZ, newY, newX});
            }
        }

        return max - 1;
    }
}
