import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _5373 {

    public static char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};
    public static int[][][] turnMap = {
            {{3, 2, 1, 0}, {5, 2, 1, 0}, {2, 2, 1, 0}, {4, 2, 1, 0}},
            {{2, 6, 7, 8}, {5, 6, 7, 8}, {3, 6, 7, 8}, {4, 6, 7, 8}},
            {{0, 6, 7, 8}, {5, 0, 3, 6}, {1, 2, 1, 0}, {4, 8, 5, 2}},
            {{0, 2, 1, 0}, {4, 0, 3, 6}, {1, 6, 7, 8}, {5, 8, 5, 2}},
            {{0, 0, 3, 6}, {2, 0, 3, 6}, {1, 0, 3, 6}, {3, 8, 5, 2}},
            {{0, 8, 5, 2}, {3, 0, 3, 6}, {1, 8, 5, 2}, {2, 8, 5, 2}},
    };
    public static int[][][] turnMap2 = {
            {{3, 2, 1, 0}, {5, 2, 1, 0}, {2, 0, 1, 2}, {4, 0, 1, 2}},
            {{2, 6, 7, 8}, {5, 6, 7, 8}, {3, 8, 7, 6}, {4, 8, 7, 6}},
            {{0, 6, 7, 8}, {5, 0, 3, 6}, {1, 0, 1, 2}, {4, 2, 5, 8}},
            {{0, 2, 1, 0}, {4, 0, 3, 6}, {1, 8, 7, 6}, {5, 2, 5, 8}},
            {{0, 0, 3, 6}, {2, 0, 3, 6}, {1, 6, 3, 0}, {3, 2, 5, 8}},
            {{0, 8, 5, 2}, {3, 0, 3, 6}, {1, 2, 5, 8}, {2, 2, 5, 8}},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> opToNum = new HashMap<>();
        opToNum.put('U', 0);
        opToNum.put('D', 1);
        opToNum.put('F', 2);
        opToNum.put('B', 3);
        opToNum.put('L', 4);
        opToNum.put('R', 5);


        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            // 위, 아래, 앞, 뒤, 왼, 오
            int[][][] map = new int[6][3][3];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    Arrays.fill(map[i][j], i);
                }
            }

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                String op = st.nextToken();
                if (op.charAt(1) == '+')
                    turnUp(map, opToNum.get(op.charAt(0)));
                else
                    turnDown(map, opToNum.get(op.charAt(0)));
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    sb.append(color[map[0][i][j]]);
                sb.append('\n');
            }
        }

        System.out.print(sb);

    }

    //시계 방향
    public static void turnUp(int[][][] map, int target) {
        //자기 자신 회전
        int[][] newMap = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newMap[i][j] = map[target][2 - j][i];
            }
        }
        map[target] = newMap;

        //연결된 4개의 회전
        for (int i = 1; i <= 3; i++) {
            int[] tmp = turnMap[target][0];
            int tmpNum = map[tmp[0]][tmp[i] / 3][tmp[i] % 3];
            for (int j = 4; j > 1; j--) {
                int[] p = turnMap[target][j % 4];
                int[] p2 = turnMap[target][(j - 1) % 4];
                map[p[0]][p[i] / 3][p[i] % 3] = map[p2[0]][p2[i] / 3][p2[i] % 3];
            }
            int[] tmp2 = turnMap[target][1];
            map[tmp2[0]][tmp2[i] / 3][tmp2[i] % 3] = tmpNum;
        }
    }

    //반시계 방향
    public static void turnDown(int[][][] map, int target) {
        //자기 자신 회전
        int[][] newMap = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newMap[i][j] = map[target][j][2 - i];
            }
        }
        map[target] = newMap;

        //연결된 4개의 회전
        for (int i = 1; i <= 3; i++) {
            int[] tmp = turnMap[target][0];
            int tmpNum = map[tmp[0]][tmp[i] / 3][tmp[i] % 3];
            for (int j = 0; j < 3; j++) {
                int[] p = turnMap[target][j % 4];
                int[] p2 = turnMap[target][(j + 1) % 4];
                map[p[0]][p[i] / 3][p[i] % 3] = map[p2[0]][p2[i] / 3][p2[i] % 3];
            }
            int[] tmp2 = turnMap[target][3];
            map[tmp2[0]][tmp2[i] / 3][tmp2[i] % 3] = tmpNum;
        }
    }

}
/*
10
1
L-
2
L- U-
3
L- U- L+
4
L- U- L+ U-
5
L- U- L+ U- L-
6
L- U- L+ U- L- U-
7
L- U- L+ U- L- U- U-
8
L- U- L+ U- L- U- U- L+
9
L- U- L+ U- L- U- U- L+ U+
10
L- U- L+ U- L- U- U- L+ U+ U+
 */