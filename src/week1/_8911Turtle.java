package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//15분 28초 정답
public class _8911Turtle {

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] xMove = {0,1,0,-1};
        int[] yMove = {1,0,-1,0};

        try {
            int n = Integer.parseInt(br.readLine());

            Set<Point> pSet = new HashSet<>();

            for(int i=0;i<n;i++) {
                int direction = 0;
                String input = br.readLine();
                int x = 0;
                int y = 0;
                pSet.add(new Point(x, y));

                int xMin, xMax, yMin, yMax;
                xMin = xMax = yMin = yMax = 0;

                for(char c : input.toCharArray()) {
                    switch (c) {
                        case 'F':
                            x += xMove[direction];
                            y += yMove[direction];
                            break;
                        case 'B':
                            x -= xMove[direction];
                            y -= yMove[direction];
                            break;
                        case 'L':
                            direction = (direction + 3) % 4;
                            break;
                        case 'R':
                            direction = (direction + 1) % 4;
                            break;
                    }
                    xMin = Math.min(xMin, x);
                    xMax = Math.max(xMax, x);
                    yMin = Math.min(yMin, y);
                    yMax = Math.max(yMax, y);

                }
                System.out.println((yMax - yMin) * (xMax - xMin));
            }
        } catch (Exception e) {

        }
    }
}
