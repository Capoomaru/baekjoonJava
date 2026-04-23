import java.io.*;
import java.util.*;

public class _16946 {

    public static int[] xDiff = {-1, 0, 0, 1};
    public static int[] yDiff = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        Queue<int[]> readyQueue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                if (map[i][j] != 0)
                    readyQueue.add(new int[]{i, j});
            }
        }
        boolean[][] visited = new boolean[N][M];
        int setNumber = -1;
        HashMap<Integer, Integer> setCntMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 || visited[i][j])
                    continue;
                setNumber--;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                map[i][j] = setNumber;
                int cnt = 1;
                while (!queue.isEmpty()) {
                    int[] p = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int y = p[0] + yDiff[k];
                        int x = p[1] + xDiff[k];

                        if (y < 0 || y >= N || x < 0 || x >= M || visited[y][x] || map[y][x] != 0)
                            continue;

                        visited[y][x] = true;
                        map[y][x] = setNumber;
                        cnt++;
                        queue.add(new int[]{y, x});
                    }
                }

                setCntMap.put(setNumber, cnt);
            }
        }

        int[][] answerMap = new int[N][M];

        while (!readyQueue.isEmpty()) {
            int[] wall = readyQueue.poll();

            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                int y = wall[0] + yDiff[i];
                int x = wall[1] + xDiff[i];

                if (y < 0 || y >= N || x < 0 || x >= M || map[y][x] >= 0)
                    continue;

                set.add(map[y][x]);
            }

            answerMap[wall[0]][wall[1]] = (1 + set.stream().mapToInt(num -> setCntMap.getOrDefault(num, 0)).sum()) % 10;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                bw.append(Integer.toString(answerMap[i][j]));
            bw.newLine();
        }

        bw.flush();
    }
}
