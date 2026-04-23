package ssafy_prev;

import java.util.Scanner;

//미완성
public class _1249 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            int[][] answerMap = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    map[i][j] = sc.nextInt();
            for (int i = 1; i < N; i++) {
                answerMap[0][i] = answerMap[0][i - 1] + map[0][i];
                answerMap[i][0] = answerMap[i - 1][0] + map[i][0];
            }
            for (int i = 1; i < N; i++) {
                answerMap[0][i] = answerMap[0][i - 1] + map[0][i];
                answerMap[i][0] = answerMap[i - 1][0] + map[i][0];
            }

        }
    }
}
