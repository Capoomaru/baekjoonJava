package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//21분37초 실패
//45분35초 실패
//73분23초 실패
//78분23초 풀이 확인
//2시간 풀이
public class _16918BomberMan {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[R][C];

            for (int i = 0; i < R; i++) {
                String input = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = input.charAt(j) == 'O' ? 2 : 0;
                }
            }


            StringBuilder sb = new StringBuilder();
            for(int time=2;time<=N;time++) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        map[i][j]--;
                        if(time % 2 == 0) {
                            if(map[i][j] <= 0)
                                map[i][j] = 3;
                        }
                        else {
                            bomb(map, i, j);
                        }
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(map[i][j] <= 0 ? '.' : 'O');
                }
                sb.append("\n");
            }


            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bomb(int[][] map, int i, int j) {
        if(map[i][j] != 0) return;
        if (i > 0 && map[i - 1][j] != 0)
            map[i - 1][j] = -1;
        if (map.length > i + 1 && map[i + 1][j] != 1)
            map[i + 1][j] = -1;
        if (j > 0 && map[i][j - 1] != 0)
            map[i][j - 1] = -1;
        if (map[0].length > j + 1 && map[i][j + 1] != 1)
            map[i][j + 1] = -1;

    }
}
