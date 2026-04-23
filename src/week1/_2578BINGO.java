package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//28분 틀림
//56분 통과
public class _2578BINGO {

    public static class Geo {
        int x;
        int y;

        Geo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Geo> map = new HashMap<>();
        int sum = 0;
        int answer = 0;

        boolean[][] board = new boolean[5][5];

        try {
            for (int i = 0; i < 5; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 5; j++) {
                    map.put(st.nextToken(), new Geo(j, i));
                }
            }
            int cnt = 1;
            for (int i = 0; i < 5; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 5; j++, cnt++) {
                    String target = st.nextToken();
                    Geo geo = map.get(target);
                    board[geo.y][geo.x] = true;
                    sum += check(board, geo.x, geo.y);
                    if(answer == 0 && sum >= 3) {
                        answer = cnt;
                    }
                }
            }

            System.out.print(answer);


        } catch (Exception e) {

        }

    }

    public static int check(boolean[][] board, int x, int y) {
        int cnt = 0;
        boolean xSum = false;
        boolean ySum = false;
        for(int i=0;i<5;i++) {
            xSum |= !board[y][i];
            ySum |= !board[i][x];
        }
        if(!xSum) {
            cnt++;
        }
        if(!ySum) {
            cnt++;
        }

        if(x == y) {
            xSum = false;
            for (int i = 0; i < 5; i++) {
                xSum |= !board[i][i];
            }
            if (!xSum) {
                cnt++;
            }
        }
        if(x + y == 4) {
            xSum = false;
            for (int i = 0; i < 5; i++) {
                xSum |= !board[4 - i][i];
            }
            if (!xSum) {
                cnt++;
            }
        }

        return cnt;

    }
}
