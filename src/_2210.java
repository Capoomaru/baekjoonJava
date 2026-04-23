import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _2210 {

    public static int[] xDiff = {-1, 0, 0, 1};
    public static int[] yDiff = {0, -1, 1, 0};
    public static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        set = new HashSet<>();

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                backTrack(map, i, j, 0, 0);
            }
        }

        System.out.println(set.size());
    }

    public static void backTrack(int[][] map, int y, int x, int depth, int num) {
        num = num * 10 + map[y][x];
        if (depth == 5) {
            set.add(num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newY = y + yDiff[i];
            int newX = x + xDiff[i];

            if (newY < 0 || newY >= 5 || newX < 0 || newX >= 5)
                continue;

            backTrack(map, newY, newX, depth + 1, num);
        }
    }
}
