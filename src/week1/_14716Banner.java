package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//7분 20초
public class _14716Banner {
    static boolean[][] visited;

    static int[] xDiff = {-1,0,1,-1,1,-1,0,1};
    static int[] yDiff = {1,1,1,0,0,-1,-1,-1};
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[][] map = new int[m][n];
            visited = new boolean[m][n];

            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<n;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;

            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(map[i][j] == 1 && !visited[i][j]) {
                        bfs(map, i, j);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bfs(int[][] map, int i, int j) {
        if(visited[i][j])
            return;

        visited[i][j] = true;

        for(int idx=0;idx<8;idx++) {
            int nextX = j + xDiff[idx];
            int nextY = i + yDiff[idx];

            if(nextX >= 0 && nextX < map[0].length
            && nextY >= 0 && nextY < map.length
            && map[i][j] == 1)
                bfs(map, nextY, nextX);
        }
    }
}
