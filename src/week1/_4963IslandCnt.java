package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//19분 29초 정답
public class _4963IslandCnt {

    static boolean[][] visited;

    static int[] xRoot = {-1,0,1,-1,1,-1,0,1};
    static int[] yRoot = {1,1,1,0,0,-1,-1,-1};

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = br.readLine();
            while(!input.equals("0 0")) {
                StringTokenizer st = new StringTokenizer(input, " ");
                int w = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());

                int[][] map = new int[h][w];

                for(int i=0;i<h;i++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    for(int j=0;j<w;j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                visited = new boolean[h][w];

                int cnt = 0;

                for(int i=0;i<h;i++) {
                    for(int j=0;j<w;j++) {
                        if(map[i][j] == 1 && !visited[i][j]) {
                            visit(map, i, j);
                            cnt++;
                        }
                    }
                }

                System.out.println(cnt);

                input = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visit(int[][] map, int i, int j) {
        if(visited[i][j])
            return;
        visited[i][j] = true;

        for(int idx=0;idx<8;idx++) {
            int nextY = i + yRoot[idx];
            int nextX = j + xRoot[idx];
            if(nextX >= 0 && nextX < map[0].length && nextY >=0 && nextY < map.length
            && map[nextY][nextX] == 1)
                visit(map, nextY, nextX);
        }
    }
}
