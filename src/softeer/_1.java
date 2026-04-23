import java.io.*;
import java.util.*;

public class _1 {

    public static int[] xDiff = { -1, 0, 0, 1 };
    public static int[] yDiff = { 0, -1, 1, 0 };
    public static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int[][] visited = new int[N][N];
        int[][] friendPosition = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            friendPosition[i][0] = y;
            friendPosition[i][1] = x;
            visited[y][x] |= (1 << (i));
            sum += map[y][x];
        }
        System.out.println(bdfs(map, friendPosition, visited, 0, 0, sum));
    }

    public static int bdfs(int[][] map, int[][] friendPosition, int[][] visited, int i, int depth, int sum) {
        if (i == friendPosition.length) {
            return bdfs(map, friendPosition, visited, 0, depth + 1, sum);
        }
        if (depth == 3) {
            return sum;
        }

        int originY = friendPosition[i][0];
        int originX = friendPosition[i][1];

        int max = 0;
        for (int j = 0; j < 4; j++) {
            int newY = originY + yDiff[j];
            int newX = originX + xDiff[j];

            if (newX < 0 || newY < 0 || newX >= map.length || newY >= map.length)
                continue;
            if((visited[newY][newX] & (1 << i)) == (1 << i))
                continue;

            friendPosition[i][0] = newY;
            friendPosition[i][1] = newX;
            if (visited[newY][newX] > 0) {
                visited[newY][newX] |= (1 << (i));
                max = Math.max(max, bdfs(map, friendPosition, visited, i + 1, depth, sum));
                visited[newY][newX] ^= (1 << (i));
                continue;
            }
            visited[newY][newX] |= (1 << (i));
            max = Math.max(max, bdfs(map, friendPosition, visited, i + 1, depth, sum + map[newY][newX]));
            visited[newY][newX] ^= (1 << (i));
        }
        friendPosition[i][0] = originY;
        friendPosition[i][1] = originX;


        return max;
    }
}