package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//9분 38초 실패
//11분 53초 시간초과(StringBuilder 안써서)
//13분 30초 통과
public class _1996Minesweeper {

    public static int[] xDiff = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static int[] yDiff = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int n = Integer.parseInt(br.readLine());

            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                for (int j = 0; j < n; j++) {
                    char c = input.charAt(j);
                    if (c >= '1' && c <= '9') {
                        setMines(map, i, j, c - '0');
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(
                            map[i][j] == -1 ? "*" :
                                    map[i][j] >= 10 ? "M" : map[i][j]
                    );
                }
                sb.append("\n");
            }

            System.out.println(sb);
        } catch (Exception e) {

        }
    }

    public static void setMines(int[][] map, int i, int j, int cnt) {
        map[i][j] = -1;

        for (int idx = 0; idx < 8; idx++) {
            int nextX = j + xDiff[idx];
            int nextY = i + yDiff[idx];

            if (nextX >= 0 && map[0].length > nextX
                    && nextY >= 0 && map.length > nextY && map[nextY][nextX] != -1) {
                map[nextY][nextX] += cnt;
            }
        }

    }
}
