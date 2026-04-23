package ssafy_prev;

import java.util.Scanner;

public class _1974 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int[][] map = new int[9][9];
            int[] countMap = new int[10];
            int answer = 1;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    map[i][j] = sc.nextInt();
                    countMap[map[i][j]]++;
                    if (countMap[map[i][j]] > 9)
                        answer = 0;
                }
            }

            for (int i = 0; i < 9 && answer == 1; i++) {
                countMap = new int[10];
                for (int j = 0; j < 9; j++) {
                    //가로
                    countMap[map[i][j]]++;
                    //세로
                    countMap[map[j][i]]++;
                }
                int y = (i / 3) * 3;
                int x = (i % 3) * 3;
                //칸
                for (int j = 0; j < 3; j++)
                    for (int k = 0; k < 3; k++)
                        countMap[map[y + j][x + k]]++;

                for (int j = 1; j <= 9; j++)
                    if (countMap[j] != 3) {
                        answer = 0;
                        break;
                    }

            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }
}
