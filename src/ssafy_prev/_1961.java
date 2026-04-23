package ssafy_prev;

import java.util.Scanner;

public class _1961 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            System.out.printf("#%d\n", test_case);
            for (int i = 0; i < N; i++) {
                for (int j = N - 1; j >= 0; j--)
                    System.out.print(map[j][i]);
                System.out.print(" ");
                for (int j = N - 1; j >= 0; j--)
                    System.out.print(map[N - 1 - i][j]);
                System.out.print(" ");
                for (int j = 0; j < N; j++)
                    System.out.print(map[j][N - 1 - i]);
                System.out.println(" ");
            }
        }
    }
}
