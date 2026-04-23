package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//29분 5초 통과
public class _5212GlobalWarming {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[][] map = new int[H+2][W+2];

            for(int i=1;i<=H;i++) {
                String input = br.readLine();
                for(int j=1;j<=W;j++) {
                    map[i][j] = input.charAt(j-1) == 'X' ? 1 : 0;
                }
            }

            int xMin = 0;
            int xMax = 0;
            int yMin = 0;
            int yMax = 0;

            int[][] answerMap = new int[H+1][W+1];

            boolean isFirst = true;

            for(int i=1;i<=H;i++) {
                for(int j=1;j<=W;j++) {
                    if(map[i][j] == 1) {
                        if(!isRemove(map, i, j)) {
                            answerMap[i-1][j-1] = 1;
                            if(isFirst) {
                                xMin = j;
                                xMax = j;
                                yMin = i;
                                yMax = i;
                                isFirst = false;
                            }
                            else {
                                xMin = Math.min(j, xMin);
                                xMax = Math.max(j, xMax);
                                yMin = Math.min(i, yMin);
                                yMax = Math.max(i, yMax);
                            }
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<=(yMax - yMin);i++) {
                for(int j=0;j<=(xMax - xMin);j++) {
                    if(answerMap[i+yMin-1][j+xMin-1] == 1)
                        sb.append('X');
                    else
                        sb.append('.');
                }
                sb.append('\n');
            }
            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isRemove(int[][] map, int i, int j) {
        int[] xMove = {0, 1, 0, -1};
        int[] yMove = {1, 0, -1, 0};

        int sum = 0;
        for(int idx=0;idx<4;idx++) {
            int y = i+yMove[idx];
            int x = j+xMove[idx];
            if(y >= 0 && y < map.length && x >=0 && x < map[0].length) {
                sum += map[y][x] == 0 ? 1 : 0;
            }
        }
        return sum >= 3;
    }
}
