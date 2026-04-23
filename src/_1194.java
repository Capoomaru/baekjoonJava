import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _1194 {
    public static char[][] map;
    public static int[][][] minMap;
    public static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int startY, startX;
        startY = startX = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '0') {
                    startX = j;
                    startY = i;
                }
            }
        }

        minMap = new int[N][M][64];

        System.out.println(bfs(startY, startX));


    }

    public static int bfs(int startY, int startX) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 0});

        int[] xDiff = new int[]{-1, 0, 0, 1};
        int[] yDiff = new int[]{0, -1, 1, 0};
        boolean[][][] visited = new boolean[N][M][64];

        visited[startY][startX][0] = true;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            char c = map[p[0]][p[1]];

            if (c == '1') {
                return minMap[p[0]][p[1]][p[2]];
            }

            if (Character.isAlphabetic(c)) {
                if (Character.isLowerCase(c)) {
                    int tmp = p[2] | 1 << (c - 'a');
                    if(!visited[p[0]][p[1]][tmp]) {
                        minMap[p[0]][p[1]][tmp] = minMap[p[0]][p[1]][p[2]];
                        visited[p[0]][p[1]][tmp] = true;
                        p[2] = tmp;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int newY = p[0] + yDiff[i];
                int newX = p[1] + xDiff[i];

                if (newY < 0 || newY >= N || newX < 0 || newX >= M) {
                    continue;
                }

                char newC = map[newY][newX];

                if (newC == '#')
                    continue;

                if (visited[newY][newX][p[2]])
                    continue;

                visited[newY][newX][p[2]] = true;

                if (Character.isAlphabetic(newC)) {
                    if (Character.isUpperCase(newC)) {
                        if((p[2] & (1 << newC - 'A')) == 0)
                            continue;
                    }
                }

                minMap[newY][newX][p[2]] = minMap[p[0]][p[1]][p[2]] + 1;
                queue.add(new int[]{newY, newX, p[2]});

            }

        }

        return -1;
    }
}
