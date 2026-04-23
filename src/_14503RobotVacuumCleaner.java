import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _14503RobotVacuumCleaner {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.cleaning();
        System.out.println(s.cnt);
    }

    static class Solution {
        int[][] map;
        int M;
        int N;
        int x;
        int y;
        int direction;
        int[] xDelta = {0, 1, 0, -1};
        int[] yDelta = {-1, 0, 1, 0};
        int cnt;

        Solution() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            cnt = 0;

            try {
                String[] read = br.readLine().split(" ");
                N = Integer.parseInt(read[0]);
                M = Integer.parseInt(read[1]);

                map = new int[N][M];

                read = br.readLine().split(" ");
                y = Integer.parseInt(read[0]);
                x = Integer.parseInt(read[1]);
                direction = Integer.parseInt(read[2]);

                for (int i = 0; i < N; i++) {
                    read = br.readLine().split(" ");
                    for (int j = 0; j < M; j++) {
                        map[i][j] = Integer.parseInt(read[j]);
                    }
                }

            } catch (Exception e) {

            }
        }

        public void cleaning() {
            while(true) {
                System.out.printf("x : %d y : %d\n", x, y);
                if (map[y][x] == 0) {
                    map[y][x] = -1;
                    cnt++;
                }

                if (hasDirty()) {
                    direction = (direction + 3) % 4;
                    moveForward();

                }
                else {
                    if(!optimalMoveBackward())
                        return;
                }
            }
        }

        public boolean hasDirty() {
            for(int i=0;i<4;i++) {
                int tmpX = x+xDelta[i];
                int tmpY = y+yDelta[i];
                if(tmpX>=0 && tmpX < M && tmpY >=0 && tmpY < N)
                    if(map[tmpY][tmpX] == 0)
                        return true;
            }

            return false;
        }

        public void moveForward() {
            int tmpX = x+xDelta[direction];
            int tmpY = y+yDelta[direction];
            if(tmpX>=0 && tmpX < M && tmpY >=0 && tmpY < N)
                if(map[tmpY][tmpX] == 0) {
                    x = tmpX;
                    y = tmpY;
                }
        }

        public boolean optimalMoveBackward() {
            int tmpX = x+xDelta[(direction+2) % 4];
            int tmpY = y+yDelta[(direction+2) % 4];
            if(tmpX>=0 && tmpX < M && tmpY >=0 && tmpY < N) {
                if (map[tmpY][tmpX] == 1)
                    return false;
                x = tmpX;
                y = tmpY;
            }

            return true;
        }

    }
}
