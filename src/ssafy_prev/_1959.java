package ssafy_prev;

import java.util.Scanner;

public class _1959 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int sizeA = sc.nextInt();
            int sizeB = sc.nextInt();
            int[] aMap = new int[sizeA];
            int[] bMap = new int[sizeB];
            for (int i = 0; i < sizeA; i++)
                aMap[i] = sc.nextInt();
            for (int i = 0; i < sizeB; i++)
                bMap[i] = sc.nextInt();

            int max = 0;
            if (sizeA > sizeB) {
                for (int i = 0; i + sizeB < sizeA; i++) {
                    int sum = 0;
                    for (int j = 0; j < sizeB; j++) {
                        sum += bMap[j] * aMap[i + j];
                    }
                    max = Math.max(max, sum);
                }
            } else {
                for (int i = 0; i + sizeA <= sizeB; i++) {
                    int sum = 0;
                    for (int j = 0; j < sizeA; j++) {
                        sum += aMap[j] * bMap[i + j];
                    }
                    max = Math.max(max, sum);
                }
            }

            System.out.printf("#%d %d\n", test_case, max);
        }
    }
}
