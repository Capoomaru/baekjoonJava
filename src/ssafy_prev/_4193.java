package ssafy_prev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _4193 {

    public static int[] xDiff = new int[] { -1, 0, 0, 1 };
    public static int[] yDiff = new int[] { 0, -1, 1, 0 };
    static class Point {
        int y;
        int x;
        int time;

        Point(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        /*
         * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
         */

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];

            StringTokenizer st = null;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine(), " ");
            Point startPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            int[] end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int answer = bfs(startPoint, end, map);

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    public static int bfs(Point startPoint, int[] end, int[][] map) {
        boolean[][] visited = new boolean[map.length][map.length];

        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            visited[point.y][point.x] = true;

            for (int i = 0; i < 4; i++) {
                int newY = point.y + yDiff[i];
                int newX = point.x + xDiff[i];
                if (isDone(newY, newX, end))
                    return point.time + 1;

                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map.length || visited[newY][newX])
                    continue;
                if (map[newY][newX] == 1)
                    continue;

                if(map[newY][newX] == 0) {
                    queue.add(new Point(newY, newX, point.time + 1));
                    continue;
                }

                if((point.time + 1) % 3 == 0)
                    queue.add(new Point(newY, newX, point.time + 1));
                else
                    queue.add(new Point(point.y, point.x, point.time + 1));
            }
        }


        return -1;
    }

    public static boolean isDone(int y, int x, int[] end) {
        return end[0] == y && end[1] == x;
    }

}
