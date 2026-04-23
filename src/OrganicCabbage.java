import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrganicCabbage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int t = 0; t < T; t++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();

            Solution s = new Solution(m, n, k);


            for (int i = 0; i < k; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                s.putPoint(x, y);

            }

            int answer = 0;

            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(s.map[i][j] == 1 && !s.isVisited[i][j]) {
                        s.allPath(i, j);
                        answer++;
                    }
                }
            }

            System.out.println(answer);


        }

    }

}

class Solution {
    int[][] map;
    boolean[][] isVisited;

    Solution(int m, int n, int k) {
        this.map = new int[n][m];
        this.isVisited = new boolean[n][m];
    }

    public void putPoint(int x, int y) {
        this.map[y][x] = 1;
    }

    public void allPath(int x, int y) {
        if(x < 0 || y < 0 || x >= map[0].length || y >= map.length)
            return;
        isVisited[y][x] = true;

        if(map[y][x+1] != 1 || isVisited[y][x+1])
            allPath(x+1, y);
        if(map[y][x-1] != 1 || isVisited[y][x-1])
            allPath(x-1, y);
        if(map[y+1][x] != 1 || isVisited[y+1][x])
        allPath(x, y+1);
        if(map[y-1][x] != 1 || isVisited[y-1][x])
            allPath(x, y-1);


    }
}