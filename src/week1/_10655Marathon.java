package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _10655Marathon {
    public static class Point {
        int x;
        int y;
        int distance;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static int getDistance(Point p1, Point p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
        }

        public void setDistance(Point prev) {
            distance = Math.abs(prev.x - this.x) + Math.abs(prev.y - this.y);
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int n = Integer.parseInt(br.readLine());
            List<Point> list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int prevX = Integer.parseInt(st.nextToken());
            int prevY = Integer.parseInt(st.nextToken());
            Point p = new Point(prevX, prevY);
            p.distance = 0;
            list.add(p);

            for (int i = 1; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Point(x, y));
                list.get(i).setDistance(p);
                p = list.get(i);
            }

            if(n == 1) {
                System.out.println(0);
                return;
            }
            if(n == 2) {
                System.out.println(Point.getDistance(list.get(0), list.get(1)));
                return;
            }

            int max = 0;
            int maxIdx = 0;

            for(int i=1;i<n-1;i++) {
                int sum = Point.getDistance(list.get(i-1), list.get(i+1));
                if((list.get(i).distance + list.get(i+1).distance - sum) > max) {
                    max = list.get(i).distance + list.get(i+1).distance - sum;
                    maxIdx = i;
                }
            }

            int sum = 0;
            sum += Point.getDistance(list.get(maxIdx-1), list.get(maxIdx+1));
            for(int i=1;i<n;i++) {
                if(maxIdx + 1 == i || maxIdx == i)
                    continue;
                sum += list.get(i).distance;
            }

            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
