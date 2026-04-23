package ssafy_prev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12712 {
    public static int[][] xMove = new int[][]{{-1, 0, 0, 1}, {-1, -1, 1, 1}};
    public static int[][] yMove = new int[][]{{0, 1, -1, 0}, {-1, 1, -1, 1}};

    public enum Type {
        PLUS, MULTI;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int testcaseN = Integer.parseInt(br.readLine());

            for (int testcase = 0; testcase < testcaseN; testcase++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int N = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());

                int[][] map = new int[N][N];

                for (int i = 0; i < N; i++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    for (int j = 0; j < N; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                int max = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        max = Math.max(max, spray(Type.PLUS, map, M, i, j));
                        max = Math.max(max, spray(Type.MULTI, map, M, i, j));
                    }
                }

                System.out.println("#" + (testcase + 1) + " " + max);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int spray(Type type, int[][] map, int size, int i, int j) {
        int sum = map[i][j];
        for (int diff = 1; diff < size; diff++) {
            for (int testcase = 0; testcase < 4; testcase++) {
                int newX = j + diff * xMove[type.ordinal()][testcase];
                int newY = i + diff * yMove[type.ordinal()][testcase];

                if (newX < 0 || newX >= map.length || newY < 0 || newY >= map.length)
                    continue;

                sum += map[newY][newX];
            }
        }

        return sum;
    }
}
