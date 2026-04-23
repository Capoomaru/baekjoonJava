import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _21610 {

    static int[] xDiff = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] yDiff = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] yDiagonalDiff = { -1, -1, 1, 1 };
    static int[] xDiagonalDiff = { -1, 1, -1, 1 };

    static ArrayList<Pair> cloudList;

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            Pair other = (Pair) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        cloudList = new ArrayList<>();
        cloudList.add(new Pair(N - 1, 0));
        cloudList.add(new Pair(N - 1, 1));
        cloudList.add(new Pair(N - 2, 0));
        cloudList.add(new Pair(N - 2, 1));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            move(d, s, N);
            rain(map);
            copyWater(map);
            removeAndMakeCloud(map);
        }

        System.out.println(Arrays.stream(map).mapToInt(arr -> Arrays.stream(arr).sum()).sum());
    }

    public static void move(int direction, int s, int N) {
        for (Pair cloud : cloudList) {
            cloud.y = (s * N + cloud.y + s * yDiff[direction]) % N;
            cloud.x = (s * N + cloud.x + s * xDiff[direction]) % N;
        }
    }

    public static void rain(int[][] map) {
        for (Pair cloud : cloudList)
            map[cloud.y][cloud.x]++;
    }

    public static void copyWater(int[][] map) {
        int[] plusList = new int[cloudList.size()];
        for (int i = 0; i < cloudList.size(); i++) {
            Pair cloud = cloudList.get(i);
            for (int j = 0; j < 4; j++) {
                int y = cloud.y + yDiagonalDiff[j];
                int x = cloud.x + xDiagonalDiff[j];

                if (y < 0 || y >= map.length || x < 0 || x >= map[0].length)
                    continue;

                plusList[i] += (map[y][x] > 0) ? 1 : 0;
            }
        }

        for (int i = 0; i < cloudList.size(); i++) {
            Pair cloud = cloudList.get(i);
            map[cloud.y][cloud.x] += plusList[i];
        }
    }

    public static void removeAndMakeCloud(int[][] map) {
        HashSet<Pair> originList = new HashSet<>(cloudList);
        cloudList.clear();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Pair p = new Pair(i, j);
                if (map[i][j] < 2 || originList.contains(p))
                    continue;

                map[i][j] -= 2;
                cloudList.add(p);
            }
        }
    }

}
