import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] map;
    static int R, C, cnt;
    static int[] start = new int[]{-1, -1};
    static int[] dictx = {-1, 1, 0, 0};
    static int[] dicty = {0, 0, -1, 1};
    static boolean[][] v;
    static boolean[][] move;
    // 땅을 지우는데 사용할 Queue
    static Queue<int[]> q = new LinkedList<>();
    static Queue<int[]> moveQ = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        init();
        while (true) {
            print();
            System.out.println("------------------------------------");
            if(find())
                break;
            erase();
            cnt++;
        }
    }
    public static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        v = new boolean[R][C];
        move = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = input[j];
                if (input[j] == '.' || input[j] == 'L') {
                    if (input[j] == 'L' && start[0] == -1 && start[1] == -1) {
                        move[i][j] = true;
                        start[0] = i;
                        start[1] = j;
                        moveQ.add(start);
                    }
                    q.add(new int[]{i, j});
                    v[i][j] = true;
                }
            }
        }
    }
    public static boolean find() {
        Queue<int[]> keep = new LinkedList<>();
        while (!moveQ.isEmpty()) {
            int[] cur = moveQ.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur[0] + dictx[i];
                int nextY = cur[1] + dicty[i];
                if (0 <= nextX && nextX < R && 0 <= nextY && nextY < C && !move[nextX][nextY]) {
                    System.out.println("data = " + map[nextX][nextY]);
                    // 땅인 경우 q에 넣고 계속 이동
                    if (map[nextX][nextY] == '.') {
                        moveQ.add(new int[]{nextX, nextY});
                        move[nextX][nextY] = true;
                    }
                    // 빙하인 경우 다음 q에 넣기 위해 keep에 저장
                    else if (map[nextX][nextY] == 'X'){
                        keep.add(new int[] {nextX, nextY});
                        move[nextX][nextY] = true;
                    }
                    // 만난 경우 종료
                    else if(map[nextX][nextY] == 'L'){
                        System.out.println(cnt);
                        return true;
                    }
                }
            }
        }
        moveQ = keep;
        return false;
    }
    public static void erase() {
        Queue<int[]> next = new LinkedList<>();
        int qSize = q.size();
        for (int i = 0; i < qSize; i++) {
            int[] nextq = q.poll();
            for (int check = 0; check < 4; check++) {
                int nextX = nextq[0] + dictx[check];
                int nextY = nextq[1] + dicty[check];
                if (0 <= nextX && nextX < R && 0 <= nextY && nextY < C && !v[nextX][nextY] && map[nextX][nextY] == 'X') {
                    next.add(new int[]{nextX, nextY});
                    v[nextX][nextY] = true;
                    map[nextX][nextY] = '.';
                }
            }
        }
        q = next;
    }
    public static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}