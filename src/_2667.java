import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class _2667 {

    static int[] xDiff = {-1, 0, 0, 1};
    static int[] yDiff = {0,-1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++)
                map[i][j] = input.charAt(j) - '0';
        }

        HashMap<Integer, Integer> cntMap = new HashMap<>();
        int num = -1;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] != 1)
                    continue;
                num--;
                map[i][j] = num;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                int cnt = 1;
                while(!queue.isEmpty()) {
                    int[] p = queue.poll();

                    for(int k=0;k<4;k++) {
                        int y = p[0] + yDiff[k];
                        int x = p[1] + xDiff[k];

                        if(y <0 || y >= N || x < 0 || x >= N || map[y][x] != 1)
                            continue;
                        map[y][x] = num;
                        cnt++;
                        queue.add(new int[]{y, x});
                    }
                }
                cntMap.put(num, cnt);
            }
        }

        System.out.println(cntMap.size());
        cntMap.values().stream().sorted().forEach(System.out::println);
    }
}
