import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//solved
public class _3184 {

    static char[][] map;
    static boolean[][] visited;
    static int[] moveX = new int[]{-1, 1, 0, 0};
    static int[] moveY = new int[]{0, 0, -1, 1};
    static boolean isOut = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int aSum = 0;
        int bSum = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '#' || visited[i][j]) continue;
                visited[i][j] = true;
                isOut = false;
                int a = 0;
                int b = 0;
                if (map[i][j] == 'o') a++;
                if (map[i][j] == 'v') b++;
                int[] sum = searchMap(i, j, a, b);
                if (isOut) {
                    aSum += sum[0];
                    bSum += sum[1];
                } else if (sum[0] > sum[1])
                    aSum += sum[0];
                else
                    bSum += sum[1];
            }
        }

        System.out.println(aSum + " " + bSum);

    }

    public static int[] searchMap(int y, int x, int aSum, int bSum) {
        int a = aSum;
        int b = bSum;

        for (int i = 0; i < 4; i++) {
            int newY = moveY[i] + y;
            int newX = moveX[i] + x;

            if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length) {
                isOut = true;
                continue;
            }
            if (visited[newY][newX] || map[newY][newX] == '#') continue;

            visited[newY][newX] = true;
            if (map[newY][newX] == 'o') a++;
            if (map[newY][newX] == 'v') b++;
            int[] tmp = searchMap(newY, newX, a, b);
            a = Math.max(a, tmp[0]);
            b = Math.max(b, tmp[1]);
        }

        return new int[]{a, b};
    }
}
