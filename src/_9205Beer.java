import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _9205Beer {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int t = Integer.parseInt(br.readLine());
            for(int i=0;i<t;i++) {
                Solution s = new Solution(br);
                System.out.println(s.bfs());
            }
        } catch (IOException e) {}


    }

    static class Solution {
        Point home, festival;
        List<Point> storeList;

        final int beerSize = 20;
        final int beerMeter = 50;
        Set<Point> visitedSet;
        Set<Point> lastPointList;

        final int moveRange = beerSize * beerMeter;


        Solution(BufferedReader br) {
            try {
                int n = Integer.parseInt(br.readLine());
                StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());
                home = new Point(x, y);
                storeList = new ArrayList<>();
                lastPointList = new HashSet<>();
                visitedSet = new HashSet<>();

                for(int i=0;i<n;i++) {
                    stk = new StringTokenizer(br.readLine(), " ");
                    x = Integer.parseInt(stk.nextToken());
                    y = Integer.parseInt(stk.nextToken());
                    storeList.add(new Point(x, y));
                }

                stk = new StringTokenizer(br.readLine(), " ");
                x = Integer.parseInt(stk.nextToken());
                y = Integer.parseInt(stk.nextToken());
                festival = new Point(x, y);

            } catch (Exception e) {

            }

            lastPointList = storeList.stream().filter(store -> Point.diff(festival, store) <= beerMeter * beerSize)
                    .collect(Collectors.toSet());

        }

        public String bfs() {
            Queue<Point> queue = new LinkedList<>();
            queue.add(home);

            while(!queue.isEmpty()) {
                Point p = queue.poll();

                if (Point.diff(p, festival) <= moveRange)
                    return "happy";

                storeList.stream().filter(store -> Point.diff(p, store) <= moveRange && !visitedSet.contains(store))
                        .forEach(point -> {
                            visitedSet.add(point);
                            queue.add(point);
                        });
            }

            return "sad";
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static int diff(Point p1, Point p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
        }
    }
}
