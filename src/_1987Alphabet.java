import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _1987Alphabet {

    static char[][] map;
    static boolean[] set;
    static boolean[][] visited;
    static int[] xMove = {0,-1,1,0};
    static int[] yMove = {-1,0,0,1};
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map = new char[y][x];
            visited = new boolean[y][x];
            set = new boolean[26];

            for(int i=0;i<y;i++) {
                String input = br.readLine();
                for(int j=0;j<x;j++) {
                    map[i][j] = input.charAt(j);
                }
            }

            System.out.println(bfs(0,0,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int bfs(int i, int j, int max) {
        if(set[map[i][j] - 'A'])
            return max;

        max += 1;

        set[map[i][j] - 'A'] = true;
        visited[i][j] = true;

        int tmp = max;

        for(int turn=0;turn<4;turn++) {
            int newY = i+yMove[turn];
            int newX = j+xMove[turn];

            if(newY >=0 && newY < map.length
                && newX >=0 && newX < map[0].length) {
                if(!visited[newY][newX])
                    tmp = Math.max(bfs(newY, newX, max), tmp);
            }
        }

        set[map[i][j] - 'A'] = false;
        visited[i][j] = false;

        return tmp;
    }
}
